package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Controller
@RequestMapping(value="/Redirekcija")
public class RedirekcijaController {

	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";				
	}
	
	/** pribavljanje HTML stanice za prikaz svih opcija, get zahtev */
	// GET: Redirekcija
	@GetMapping
	@ResponseBody
	public String index(HttpSession session, HttpServletResponse response) throws IOException {	
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Redirekcija osnovna stranica</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n"+
				"	<ul>\r\n" + 
				"		<li><a href=\"Redirekcija/Redirect1\">Redirekcija primer 01 - parametar kontrolera je HttpServletResponse i RedirectAttributes</a></li>\r\n" + 
				"		<li><a href=\"Redirekcija/Redirect2\">Redirekcija primer 02 - povrata vrednost kontrolera je RedirectView i parametar RedirectAttributes</a></li>\r\n" + 
				"		<li><a href=\"Redirekcija/Redirect3\">Redirekcija primer 03 - povrata vrednost kontrolera je String URL u obliku redirect:URL, i parametar RedirectAttributes</a></li>\r\n" +
				"	</ul>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"index.html\">povratak</a></li>\r\n" + 
				"	</ul>\r\n"+
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	// GET: Redirekcija/FinalDestnation
	@GetMapping(value="/FinalDestnation")
	@ResponseBody
    public String getFinalDestnation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tekst = (String) request.getAttribute("tekst");
		Korisnik korisnik = (Korisnik) request.getAttribute("korisnik");
		String parametar1URL = request.getParameter("parametar1URL");
		String parametar2URL = request.getParameter("parametar2URL");
		System.out.println("Preuzeti tekst iz atributa je "+ tekst);
		System.out.println("Preuzeti korisnik atributa je "+ korisnik);
		System.out.println("Preuzeti parametar 1 iz URL je "+ parametar1URL);
		System.out.println("Preuzeti parametar 2 iz URL je "+ parametar1URL);
		System.out.println("--------------------------------");
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Redirekcija finalna stranica</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n"+
				"	<p>Tekst je:"+tekst+"</p>\r\n" +
				"	<p>Korisnik je:"+korisnik+"</p>\r\n" +
				"	<p>Parametar 1 iz URL je:"+parametar1URL+"</p>\r\n" +
				"	<p>Parametar 2 iz URL je:"+parametar2URL+"</p>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Redirekcija\">povratak</a></li>\r\n" + 
				"	</ul>\r\n"+
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
    }
	
	//Redirection with HttpServletResponse and additional attributes by using RedirectAttributes
	//attribute – which will be exposed as HTTP query parameter.
	//HttpServletResponse je od Servlet API
	//RedirectAttributes je od Spring API
	// GET: Redirekcija/Redirect1
	@GetMapping(value="/Redirect1")
    public void getRedirect1(HttpServletRequest request, HttpServletResponse response, RedirectAttributes ra) throws IOException {
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));

//		RedirectAttributes ne može da se koristi sa sendRedirect
		ra.addAttribute("parametar1URL", "parametar URL verzija 1");
		response.sendRedirect(bURL+"Redirekcija/FinalDestnation?parametar2URL=parametar URL verzija 2");

    }
	
	//Redirection with RedirectView and additional attributes by using RedirectAttributes
	//attribute – which will be exposed as HTTP query parameter.
	//RedirectView je od Spring API
	//RedirectAttributes je od Spring API
	// GET: Redirekcija/Redirect2
	@GetMapping(value="/Redirect2")
	public RedirectView getRedirect2(HttpServletRequest request, RedirectAttributes ra) {
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		
		ra.addAttribute("parametar1URL", "parametar URL verzija 1");
//		apsolutni URL u kome je potrebno ukljuciti ime apliacije tj. bURL
		RedirectView view = new RedirectView(bURL+"Redirekcija/FinalDestnation?parametar2URL=parametar URL verzija 2");
		
		return view;
    }
	
//	Redirection with prefix "redirect:" and additional attributes by using URL Query
//	UrlBasedViewResolver (and all its subclasses) will recognize "redirect:" as a special indication that a redirect needs to happen
//	When using String as a method return type the result may be name of view template or redirect to a URL	
	//return name of the template je od Spring API
	//RedirectAttributes je od Spring API
	// GET: Redirekcija/Redirect3
	@GetMapping(value="/Redirect3")
	public String getRedirect3(HttpServletRequest request, RedirectAttributes ra) {
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		
		ra.addAttribute("parametar1URL", "parametar URL verzija 1");
//		apsolutni URL u kome je nije potrebno ukljuciti ime apliacije tj. bURL
		return "redirect:/Redirekcija/FinalDestnation?parametar2URL=parametar URL verzija 2";
    }
}
