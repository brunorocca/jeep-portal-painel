package br.com.isobar.jeep.portal.painel.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Repository;
import javax.jcr.Session;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

/**
 * <p>Classe de serviço responsável por gerenciar os resources dentro do AEM.</p>
 * 
 * @author bfranzin
 */
@Service
@Component
public class ResourceServiceImpl implements ResourceService {
    
	private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";
	public static final String JSON_PATH = "/content/dam/jeep-portal/json/";
	public static final String CONCESSIONARIA_JSON = "concessionarias.json";
	public static final String HEADER_JSON = "header.json";
	public static final String VEICULOS_JSON = "veiculos.json";
	
    @Reference
    private SlingRepository repository;

    @Reference
    private ResourceResolverFactory resolverFactory;
    
    public String getRepositoryName() {
        return repository.getDescriptor(Repository.REP_NAME_DESC);
    }
    
    /**
     * <p>Método responsável por gravar um JSON no DAM do AEM.</p>
     * <p>Caminho pré-definido onde o JSON será salvo: "/content/dam/jeep-portal/json/"</p>
     * 
     * @param fileName Nome do arquivo json que será salvo
     * @param json Conteúdo do json que será salvo
     * 
     * @return Caminho completo onde o JSON foi salvo ou <b>null</b> se houve algum erro ao salvá-lo.
     */
	public String writeToDam(final String fileName, final String json) {
		try {
			logger.info("Iniciando gravacao do JSON [{}] no DAM. FileName [{}]", json, fileName);
			
			final Map<String, Object> authInfo = createAuthInfoMap();
			logger.info("Obtendo AdministrativeResourceResolver [{}]", authInfo);
			ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(authInfo);
			InputStream stream = new ByteArrayInputStream(json.getBytes("UTF-8"));
			
			AssetManager assetMgr = resourceResolver.adaptTo(AssetManager.class);
			final String newFile = JSON_PATH + fileName;
			logger.info("Arquivo JSON_PATH [{}]", newFile);
			
			Asset asset = assetMgr.createAsset(newFile, stream, "application/json", true);
			logger.info("Asset criado [{}]", asset);
			
			return newFile;
		} 
		catch (Exception e) {
			logger.error("Erro ao gravar json no DAM", e);
		}
		
		return null;
	}
    
	/**
     * <p>Método responsável por ler um JSON no DAM do AEM.</p>
     * <p>Caminho pré-definido onde o JSON será salvo: "/content/dam/jeep-portal/json/"</p>
     * 
     * @param fileName Nome do arquivo json que será lido
     * 
     * @return JSON ou <b>null</b> se houve algum erro de leitura.
     */
    public String readFromDam(String fileName) {
		try {
			logger.info("Iniciando busca no DAM do JSON no path [{}]", JSON_PATH);
			
			final Map<String, Object> authInfo = createAuthInfoMap();
			logger.info("Obtendo AdministrativeResourceResolver [{}]", authInfo);
			ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(authInfo);
			
			logger.debug("Iniciando JCR Session");
			Session session = resourceResolver.adaptTo(Session.class);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "dam:Asset");
	        map.put("path", JSON_PATH);
	        map.put("name", fileName);
//	        map.put("mime-type", "application/json");
			
	        logger.debug("Iniciando QueryBuilder");
	        QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
	        
	        logger.debug("Criando Query");
	        Query query = builder.createQuery(PredicateGroup.create(map), session);
	        
	        logger.debug("Buscando...");
	        SearchResult searchResult = query.getResult();
	        logger.info("Search Results: [{}]", searchResult.getTotalMatches()) ;
	         
	        for (Hit hit : searchResult.getHits()) {
	             
	            String path = hit.getPath();
	            logger.info("Path [{}]", path);
	            Resource rs = resourceResolver.getResource(path);
	            Asset asset = rs.adaptTo(Asset.class);  
	               
	            InputStream data = asset.getOriginal().getStream();
	            String name = asset.getName();
	            logger.info("Name [{}]", name);

	            if(name.equals(fileName)) {
	            	return getStringFromInputStream(data);
	            }
	        }
	        
	        return null;
		} 
		catch (Exception e) {
			logger.error("Erro ao buscar json no DAM", e);
		}
		
		return null;
	}

	private Map<String, Object> createAuthInfoMap() {
		final Map<String, Object> authInfo = new HashMap<String, Object>();
		authInfo.put(ResourceResolverFactory.USER, USERNAME);
		authInfo.put(ResourceResolverFactory.PASSWORD, PASSWORD);
		return authInfo;
	}
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
}