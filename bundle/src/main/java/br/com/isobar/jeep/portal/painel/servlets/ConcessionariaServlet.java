package br.com.isobar.jeep.portal.painel.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.ServletResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.isobar.jeep.portal.importacao.ImportacaoConcessionaria;
import br.com.isobar.jeep.portal.painel.model.ConcessionariaVO;
import br.com.isobar.jeep.portal.painel.service.ResourceService;
import br.com.isobar.jeep.portal.painel.service.ResourceServiceImpl;
import br.com.isobar.jeep.portal.utils.FileUploadJeepUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Component(immediate = true, metatype = true, label = "Concessionarias Servlet", description = "Concessionarias Servlet")
@Service
@Properties({
		@Property(name = "sling.servlet.paths", value = { "/bin/painel/concessionarias", "/painel/concessionarias" }),
		@Property(name = "sling.servlet.methods", value = { "GET", "POST", "PUT", "DELETE" }) })
public class ConcessionariaServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 8542332876733799623L;
	private static final Logger logger = LoggerFactory.getLogger(ConcessionariaServlet.class);
	
	@Reference
	private ServletResolver servletResolver;
	
	@Reference
	private ResourceService resourceService;
	
	private Gson gson = new GsonBuilder().create();
	
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		logger.info("Iniciando ConcessionariaServlet > GET");
		
		final List<ConcessionariaVO> concessionarias = getConcessionariasJson();
		
		Servlet servlet = servletResolver.resolveServlet(request.getResource(), "/apps/jeep-painel/templates/concessionarias.jsp");
	    request.setAttribute("concessionarias", concessionarias);
	    servlet.service(request, response);
	}

	/**
	 * Efetua a Importação do CSV de Concessionarias
	 * 
	 */
	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		File arquivoCSV = FileUploadJeepUtil.getFileFromRequest(request);
		List<ConcessionariaVO> listaConcessionaria = ImportacaoConcessionaria.convert(arquivoCSV);
		
	}

	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		logger.info("Iniciando ConcessionariaServlet > PUT");
		
		final String idConcessionaria = request.getParameter("idConcessionaria");
		logger.debug("idConcessionaria [{}]", idConcessionaria);
		
		final List<ConcessionariaVO> concessionarias = getConcessionariasJson();
		final ConcessionariaVO ccrToEdit = concessionarias.get(concessionarias.indexOf(new ConcessionariaVO(Long.valueOf(idConcessionaria))));
		
		logger.info("Editando " + ccrToEdit);
		boolean removed = concessionarias.remove(ccrToEdit);
		final String jsonPath = saveConcessionariasJson(concessionarias);
		
		final String msg;
		if(removed && StringUtils.isNotBlank(jsonPath)) {
			msg = "Concessionária " + ccrToEdit.getCodigo() + " - " + ccrToEdit.getNomeFantasia() + " removida com sucesso!";
		}
		else {
			msg = "Ocorreu um erro ao remover a concessionária " + ccrToEdit.getCodigo() + " - " + ccrToEdit.getNomeFantasia() + "! Tente novamente.";
		}
		
		Servlet servlet = servletResolver.resolveServlet(request.getResource(), "/apps/jeep-painel/templates/concessionarias.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("concessionarias", concessionarias);
	    servlet.service(request, response);
	}

	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {

		logger.info("Iniciando ConcessionariaServlet > DELETE");
		
		final String idConcessionaria = request.getParameter("idConcessionaria");
		logger.debug("idConcessionaria [{}]", idConcessionaria);
		
		final List<ConcessionariaVO> concessionarias = getConcessionariasJson();
		final ConcessionariaVO ccrToDelete = concessionarias.get(concessionarias.indexOf(new ConcessionariaVO(Long.valueOf(idConcessionaria))));
		
		logger.info("Removendo " + ccrToDelete);
		boolean removed = concessionarias.remove(ccrToDelete);
		final String jsonPath = saveConcessionariasJson(concessionarias);
		
		final String msg;
		if(removed && StringUtils.isNotBlank(jsonPath)) {
			msg = "Concessionária " + ccrToDelete.getCodigo() + " - " + ccrToDelete.getNomeFantasia() + " removida com sucesso!";
		}
		else {
			msg = "Ocorreu um erro ao remover a concessionária " + ccrToDelete.getCodigo() + " - " + ccrToDelete.getNomeFantasia() + "! Tente novamente.";
		}
		
		Servlet servlet = servletResolver.resolveServlet(request.getResource(), "/apps/jeep-painel/templates/concessionarias.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("concessionarias", concessionarias);
	    servlet.service(request, response);
	}
	
	private String saveConcessionariasJson(final List<ConcessionariaVO> jsonListObject) {
		return saveConcessionariasJson(gson.toJson(jsonListObject));
	}
	
	private String saveConcessionariasJson(final String json) {
		return resourceService.writeToDam(ResourceServiceImpl.CONCESSIONARIA_JSON, json);
	}
	
	private List<ConcessionariaVO> getConcessionariasJson() {
		
		final String json = resourceService.readFromDam(ResourceServiceImpl.CONCESSIONARIA_JSON);
		List<ConcessionariaVO> concessionarias = null;
		
		if(StringUtils.isNotBlank(json)) {
			concessionarias = gson.fromJson(json, new TypeToken<List<ConcessionariaVO>>(){}.getType());
		}
		return concessionarias;
	}
}
