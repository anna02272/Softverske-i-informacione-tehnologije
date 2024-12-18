package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

////high-level overview of the semantics of forward vs. redirect:
////redirect will respond with a 302 and the new URL in the Location header; 
////	the browser/client will then make another request to the new URL, uses HTTTP GET method 
////forward happens entirely on a server side; the Servlet container forwards the same request to the target URL; 
////	the URL won't change in the browser, uses HTTTP POST method
////More on redirection and forward method on https://www.baeldung.com/spring-redirect-and-forward

@Controller
@RequestMapping(value="/Prosledjivanje")
public class ProsledjivanjeController {

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
	// GET: Prosledjivanje
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
				"	<title>Prosleđivanje i uključivanje osnovna stranica</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n"+
				"	<ul>\r\n" + 
				"		<li><a href=\"Prosledjivanje/Prosledjivanje1\">Prosleđivanje primer 01 - povrata vrednost kontrolera je String tip - putanja do resursa i parametar HttpServletRequest i ModelMap</a></li>\r\n" + 
				"		<li><a href=\"Prosledjivanje/Prosledjivanje2\">Prosleđivanje primer 02 - koriscenjem RequestDispatcher i metoda forward i parametar HttpServletRequest i ModelMap</a></li>\r\n" + 
				"		<li><a href=\"Prosledjivanje/Prosledjivanje3\">Prosleđivanje primer 03 - povrata vrednost kontrolera je ModelAndView i parametar HttpServletRequest i ModelMap</a></li>\r\n" + 
				"		<li><a href=\"Prosledjivanje/Prosledjivanje4\">Prosleđivanje primer 04 - povrata vrednost kontrolera je String URL u obliku forward:URL i parametar HttpServletRequest i ModelMap</a></li>\r\n" +
				"	</ul>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"index.html\">povratak</a></li>\r\n" + 
				"	</ul>\r\n"+
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	// GET: Prosledjivanje/FinalDestnation
	@GetMapping(value="/FinalDestnation")
	@ResponseBody
    public String getFinalDestnation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tekst = (String) request.getAttribute("tekst");
		Korisnik korisnik = (Korisnik) request.getAttribute("korisnik1");
		Korisnik korisnik2 = (Korisnik) request.getAttribute("korisnik2");
		Korisnik korisnik3 = (Korisnik) request.getAttribute("korisnik3");
		System.out.println("Preuzeti tekst je "+ tekst);
		System.out.println("Preuzeti korisnik je "+ korisnik);
		System.out.println("Preuzeti korisnik 2 je "+ korisnik2);
		System.out.println("Preuzeti korisnik 3 je "+ korisnik3);
		System.out.println("--------------------------------");
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Prosleđivanje i uključivanje finalna stranica</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n"+
				"	<p>Tekst je:"+tekst+"</p>\r\n" +
				"	<p>Korisnik 1 je:"+korisnik+"</p>\r\n" +
				"	<p>Korisnik 2 je:"+korisnik2+"</p>\r\n" +
				"	<p>Korisnik 3 je:"+korisnik3+"</p>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Prosledjivanje\">povratak</a></li>\r\n" + 
				"	</ul>\r\n"+
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
    }
	
//	When using String as a method return type the result may be name of view template
//	da bi vracao staticke HTML stranice iskljucicemo thymeleaf templejte 
//		u fajlu application.properties
	//return name of the template je od Spring API
	//ModelMap je od Spring API
	// GET: Prosledjivanje/Prosledjivanje1 
	@GetMapping(value="/Prosledjivanje1")
    public String getProsledjivanje1(HttpServletRequest request, ModelMap map) {
		
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik1", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		map.put("korisnik2", new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
		//može relativni url
//        return ("../"+"final.html");
        //može apsolutni url, nije potrebno uključiti ime aplikacije tj. bURL
		//nema potrebe za bURL
//        return ("/"+"final.html");
		
		//može apsolutni ili relativni url do kontrolera
        return "/Prosledjivanje/FinalDestnation";
    }
	
	//return name of the template je od Spring API
	//RequestDispatcher je od Servlet API
	// GET: Prosledjivanje/Prosledjivanje2 
	@GetMapping(value="/Prosledjivanje2")
    public void getProsledjivanje2(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws ServletException, IOException  {
		
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik1", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		map.put("korisnik2", new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
		
//		kada se radi posleđivanje zabranjeno je korišćenje Writer objekta
//		ili da je povratna vrednost metode String sa anotacijom @ResponseBody
		
//		response.setContentType("text/html");    
//	    PrintWriter out = response.getWriter();
//	    
//	    out.println(
//				"<!DOCTYPE html>\r\n" + 
//				"<html>\r\n" + 
//				"<head>\r\n" + 
//				"	<meta charset=\"UTF-8\">\r\n" + 
//	    		"	<base href=\""+bURL+"\">\r\n" + 
//				"	<title>Prosleđivanje i uključivanje osnovna stranica</title>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
//				"</head>\r\n" + 
//				"<body>\r\n");
//	    
//	    // Hello 1 nece se ispisati ako je metoda forwad 
//	    out.println("<h1>Hello 1</h1>"); 
				
	    // preuzima se objekat RequestDispatcher
		//može apsolutni url, nije potrebno uključiti ime aplikacije tj. bURL
	    RequestDispatcher rd = request.getRequestDispatcher("/Prosledjivanje/FinalDestnation");
	    rd.forward(request, response);
	    
//	    // Hello 2 nece se ispisati ako je metoda forwad 
//	    out.println("<h1>Hello 2</h1>"); 
//	    out.println(
//				"</body>\r\n"+
//				"</html>\r\n");	
//	    
//	    out.close();
    }

//	When using ModelAndView as a method return type the result may be name of view or forward to a URL
//	ModelAndView combines name of view or forward URL with ModelMap as a single value
	//ModelAndView je od Spring API
	//ModelMap je od Spring API
	// GET: Prosledjivanje/Prosledjivanje3
	@GetMapping(value="/Prosledjivanje3")
    public ModelAndView getProsledjivanje3(HttpServletRequest request, ModelMap map) {
		
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik1", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		map.put("korisnik2", new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
		
//		apsolutni url, nije potrebno uključiti ime aplikacije tj. bURL
		ModelAndView view = new ModelAndView("/Prosledjivanje/FinalDestnation");
		view.addObject("korisnik3", new Korisnik("Jova", "Jović", "muški", "jova", "jova", 1000));
		
		return view;
    }
	
//	Forward with prefix "forward:" and additional attributes by using ModelAndView and ModelMap 
//	UrlBasedViewResolver (and all its subclasses) will recognize "forward:" 
//			as a special indication that a forward needs to happen
	//return name of the template je od Spring API
	//ModelMap je od Spring API
	@GetMapping(value="/Prosledjivanje4")
    public String getProsledjivanje4(HttpServletRequest request, ModelMap map) {
		
		request.setAttribute("tekst", "Tekst");
		request.setAttribute("korisnik1", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
		map.put("korisnik2", new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
		
		//može apsolutni url, nije potrebno uključiti ime aplikacije tj. bURL
		return "forward:/Prosledjivanje/FinalDestnation";
    }
}
