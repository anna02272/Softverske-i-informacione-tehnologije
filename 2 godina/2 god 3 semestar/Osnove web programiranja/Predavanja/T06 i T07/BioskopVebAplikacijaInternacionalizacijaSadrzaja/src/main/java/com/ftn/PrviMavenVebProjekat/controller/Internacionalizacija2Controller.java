package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.ftn.PrviMavenVebProjekat.model.Film;

//GET: Internacionalizacija2
@Controller
@RequestMapping(value="/Internacionalizacija2")
public class Internacionalizacija2Controller {
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";
	}
	
	// GET: Internacionalizacija2
	@GetMapping
	@ResponseBody
	public String index(HttpServletRequest request) {	
		
		
		Locale lokalizacija = localeResolver.resolveLocale(request);
		System.out.println(lokalizacija);
		
		// prikaz
		StringBuilder out = new StringBuilder();
		out.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\"" + bURL + "\">\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"	<title>"+messageSource.getMessage("inter2.title", 
						null, lokalizacija) + "</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>"+messageSource.getMessage("inter2.h2",null, lokalizacija) + "</h2>\r\n" + 
				"	<p>"+messageSource.getMessage("inter2.test1",null, lokalizacija) + "</p>\r\n" + 
				"	<p>"+messageSource.getMessage("inter2.test2",new Object[] {"Pera" , "Mika", "Žika"}, lokalizacija) + "</p>\r\n" +  
				"	<ul>\r\n" + 
				"		<li><a href=\"\">"+messageSource.getMessage("inter2.pocetna",null, lokalizacija) +"</a></li>\r\n" + 
				"	</ul>\n\r" + 
				"	<p> " + messageSource.getMessage("lang.change",null, lokalizacija) + ":</p>\n\r" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Internacionalizacija2?locale=en\">"+messageSource.getMessage("lang.eng",null, lokalizacija) +"</a></li>\r\n" +
				"		<li><a href=\"Internacionalizacija2?locale=sr\">"+messageSource.getMessage("lang.sr",null, lokalizacija) +"</a></li>\r\n" +
				"	</ul>\n\r" + 
				"</body>\r\n" + 
				"</html>"
				);

		return out.toString();
	}
}
