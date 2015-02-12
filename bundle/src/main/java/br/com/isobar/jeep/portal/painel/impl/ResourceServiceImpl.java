package br.com.isobar.jeep.portal.painel.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Repository;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.isobar.jeep.portal.painel.ResourceService;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;

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
	public static final String CONCESSIONARIA_JSON = "ccs.json";
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
     * 
     * @param is
     * @param fileName
     * @return
     */
    public String readFromDam(InputStream is, String fileName) {
		try {
			
			ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(createAuthInfoMap());

			// Use AssetManager to place the file into the AEM DAM
			AssetManager assetMgr = resourceResolver.adaptTo(AssetManager.class);
			String newFile = JSON_PATH + fileName;
			assetMgr.createAsset(newFile, is, "image/jpeg", true);
			
			// Return the path to the file was stored
			return newFile;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private Map<String, Object> createAuthInfoMap() {
		final Map<String, Object> authInfo = new HashMap<String, Object>();
		authInfo.put(ResourceResolverFactory.USER, USERNAME);
		authInfo.put(ResourceResolverFactory.PASSWORD, PASSWORD);
		return authInfo;
	}
}