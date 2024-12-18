package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.model.Film;

//GET: Internacionalizacija
@Controller
@RequestMapping(value="/Internacionalizacija")
public class InternacionalizacijaController {

	public static final String LOKALIYACIJA_KEY = "lokalizacija";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private MessageSource messageSource;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";
	}
	
	// GET: Internacionalizacija
	@GetMapping
	@ResponseBody
	public String index(HttpSession session) {	
		
		System.out.println(messageSource.getMessage("test1", 
				null, Locale.forLanguageTag("sr")));
		System.out.println(messageSource.getMessage("test2",
				new Object[] {"Pera" , "Mika", "Žika"}, Locale.ENGLISH));
		
		Locale lokalizacija = (Locale) session.getAttribute(LOKALIYACIJA_KEY);
		
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
				"	<title>"+messageSource.getMessage("inter.title", 
						null, lokalizacija) + "</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>"+messageSource.getMessage("inter.h2",null, lokalizacija) + "</h2>\r\n" + 
				"	<p>"+messageSource.getMessage("inter.test1",null, lokalizacija) + "</p>\r\n" + 
				"	<p>"+messageSource.getMessage("inter.test2",new Object[] {"Pera" , "Mika", "Žika"}, lokalizacija) + "</p>\r\n" +  
				"	<ul>\r\n" + 
				"		<li><a href=\"\">"+messageSource.getMessage("inter.pocetna",null, lokalizacija) +"</a></li>\r\n" + 
				"	</ul>\n\r" + 
				"	<p> " + messageSource.getMessage("lang.change",null, lokalizacija) + ":</p>\n\r" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Internacionalizacija/PromeniJezik?jezik=en\">"+messageSource.getMessage("lang.eng",null, lokalizacija) +"</a></li>\r\n" +
				"		<li><a href=\"Internacionalizacija/PromeniJezik?jezik=sr\">"+messageSource.getMessage("lang.sr",null, lokalizacija) +"</a></li>\r\n" +
				"	</ul>\n\r" + 
				"</body>\r\n" + 
				"</html>"
				);

		return out.toString();
	}

	// GET: Internacionalizacija/PromeniJezik?jezik=en
	@GetMapping("PromeniJezik")
	public void index(@RequestParam(defaultValue="sr") String jezik, HttpSession session, HttpServletResponse response) throws IOException {
		System.out.println(jezik);
		
		Locale lokalizacija = (Locale) session.getAttribute(LOKALIYACIJA_KEY);
		
		if(lokalizacija.getLanguage().equals(jezik)) {
			response.sendRedirect(bURL+"Internacionalizacija");
			return;
		}
		
		if(jezik.equals("sr")) {
			lokalizacija = Locale.forLanguageTag("sr");
		} else if (jezik.equals("en")) {
			lokalizacija = Locale.ENGLISH;
		}
		
		session.setAttribute(InternacionalizacijaController.LOKALIYACIJA_KEY, lokalizacija);
		response.sendRedirect(bURL+"Internacionalizacija");
	}
}
