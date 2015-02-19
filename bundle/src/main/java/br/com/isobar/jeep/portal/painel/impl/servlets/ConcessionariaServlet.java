package br.com.isobar.jeep.portal.painel.impl.servlets;

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
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.ServletResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.isobar.jeep.portal.painel.ResourceService;
import br.com.isobar.jeep.portal.painel.impl.ResourceServiceImpl;
import br.com.isobar.jeep.portal.painel.model.Concessionaria;

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
	private Logger logger = LoggerFactory.getLogger(ConcessionariaServlet.class);
	
	@Reference
	private ServletResolver servletResolver;
	
	@Reference
	private ResourceService resourceService;
	
	private Gson gson = new GsonBuilder().create();
	
	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		logger.info("Iniciando ConcessionariaServlet > GET");
		
//		logger.info("Salvando JSON no DAM");
//		final String savedJsonPath = resourceService.writeToDam(ResourceServiceImpl.CONCESSIONARIA_JSON, "[ { \"chave5\" : \"valor5\" } ]");
//		logger.info("JSON salvo no DAM com sucesso [" + savedJsonPath + "]");
		
		final String json = resourceService.readFromDam(ResourceServiceImpl.CONCESSIONARIA_JSON);
		List<Concessionaria> concessionarias = null;
		
		if(StringUtils.isNotBlank(json)) {
			concessionarias = gson.fromJson(json, new TypeToken<List<Concessionaria>>(){}.getType());
		}
		
		Servlet servlet = servletResolver.resolveServlet(request.getResource(), "/apps/jeep-painel/templates/concessionarias.jsp");
	    request.setAttribute("concessionarias", concessionarias);
	    servlet.service(request, response);
	}

	@Override
	protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		final String msg = "Hello Broccaaaaaa!!! Im a Servlet!! METHOD POST";
		System.out.println(msg);
		
		final Resource resource = request.getResource();
		response.getOutputStream().println(resource.toString());
		response.getOutputStream().println(msg);
	}

	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {
		
		final String msg = "Hello Broccaaaaaa!!! Im a Servlet!! METHOD PUT";
		System.out.println(msg);
		
		final Resource resource = request.getResource();
		response.getOutputStream().println(resource.toString());
		response.getOutputStream().println(msg);
	}

	@Override
	protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) 
			throws ServletException, IOException {

		final String msg = "Hello Broccaaaaaa!!! Im a Servlet!! METHOD DELETE";
		System.out.println(msg);
		
		final Resource resource = request.getResource();
		response.getOutputStream().println(resource.toString());
		response.getOutputStream().println(msg);
	}
}
