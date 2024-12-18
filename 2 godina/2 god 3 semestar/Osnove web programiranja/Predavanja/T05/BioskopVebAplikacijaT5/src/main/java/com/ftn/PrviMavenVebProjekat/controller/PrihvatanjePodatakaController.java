package com.ftn.PrviMavenVebProjekat.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;

@Controller
@RequestMapping(value="/PrihvatanjePodataka")
public class PrihvatanjePodatakaController { 
	
	@Autowired
	private Environment env;
		
	// GET: PrihvatanjePodataka/preuzmi1
	@GetMapping(value="/preuzmi1")
	@ResponseBody
	public String preuzmi(HttpServletRequest request, HttpServletResponse response) {
		//Specify the base URL for all relative URLs in a document
		String bURL = env.getProperty("server.servlet.contextPath")+"/";
				
		StringBuilder retval = new StringBuilder();
		retval.append(
				"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Prihvatanje podataka</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Prihvatanje podataka koristeći HttpServletRequest</h1>\r\n" + 
	    		"	<p>Uneli ste ime <b>"+request.getParameter("ime")+"</b> i prezime <b>"+request.getParameter("prezime")+"</b></p>\r\n" +
	    		"	<a href=\"forma.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");
		return retval.toString();
	}
	
	// GET: PrihvatanjePodataka/preuzmi2
	@GetMapping(value="/preuzmi2")
	@ResponseBody
	public String preuzmi2(@RequestParam String ime, 
			@RequestParam String prezime) {
		//Specify the base URL for all relative URLs in a document
		String bURL = env.getProperty("server.servlet.contextPath")+"/";
		
		StringBuilder retval = new StringBuilder();
		retval.append(
				"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Prihavtanje podataka</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Prihvatanje podataka koristeći @RequestParam anotaciju argumenata Handler Metode Metode</h1>\r\n" + 
	    		"	<p>Uneli ste ime <b>"+ime+"</b> i prezime <b>"+prezime+"</b></p>\r\n" +
	    		"	<a href=\"forma.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");
		return retval.toString();
	}
		
	// GET: PrihvatanjePodataka/preuzmi3
	@GetMapping(value="/preuzmi3")
	@ResponseBody
	public String preuzmi3(@RequestParam(name="ime") String firstName, 
			@RequestParam(name="prezime" ) String lastName, 
			@RequestParam(name="pol", required = false ) String gender,
			@RequestParam(name="plata", defaultValue="0") Double sallary) {
			
		if(gender==null)
			gender="neizjašnjen";
		
		//Specify the base URL for all relative URLs in a document
		String bURL = env.getProperty("server.servlet.contextPath")+"/";
				
		StringBuilder retval = new StringBuilder();
		retval.append(
				"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Prihvatanje podataka</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Prihvatanje podataka koristeći @RequestParam anotaciju sa dodatnim opisom atributa</h1>\r\n" + 
	    		"	<p>Uneli ste ime <b>"+firstName+"</b> i prezime <b>"+lastName+"</b></p>\r\n" +
	    		"	<p>Uneli ste pol <b>"+gender+"</b></p>\r\n" +
	    		"	<p>Uneli ste platu <b>"+sallary+"</b></p>\r\n" +
	    		"	<a href=\"forma.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");
		return retval.toString();
	}  
	
	// GET: PrihvatanjePodataka/preuzmi4
	@GetMapping(value="/preuzmi4")
	@ResponseBody
	public String preuzmi4(@RequestParam Map<String,String> allParameters) {
		
		String firstName=allParameters.get("ime");
		String lastName=allParameters.get("prezime");
		String gender=allParameters.get("pol")==null?"neizjašnjen": allParameters.get("pol");
		String sallary=allParameters.get("plata")==null?"0": allParameters.get("plata"); 
		
		//Specify the base URL for all relative URLs in a document
		String bURL = env.getProperty("server.servlet.contextPath")+"/";
		
		StringBuilder retval = new StringBuilder();
		retval.append(
				"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Prihvatanje podataka</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Prihvatanje podataka koristeći @RequestParam anotaciju anotaciju i argumenta metode tipa Map&lt;String,String&gt;</h1>\r\n" + 
	    		"	<p>Uneli ste ime <b>"+firstName+"</b> i prezime <b>"+lastName+"</b></p>\r\n" +
	    		"	<p>Uneli ste pol <b>"+gender+"</b></p>\r\n" +
	    		"	<p>Uneli ste platu <b>"+sallary+"</b></p>\r\n" +
	    		"	<a href=\"forma.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");
		return retval.toString();
	}
	
	// GET: PrihvatanjePodataka/preuzmi5
	@GetMapping(value="/preuzmi5")
	@ResponseBody
	public String preuzmi5(@ModelAttribute Korisnik korisnik, BindingResult result) {
		String error="";
		if (result.hasErrors()) {
			error = "greška prilikom preuzimanja podataka";
        }
		//Specify the base URL for all relative URLs in a document
		String bURL = env.getProperty("server.servlet.contextPath")+"/";
		StringBuilder retval = new StringBuilder();
		retval.append(
				"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Prihvatanje podataka</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Prihvatanje podataka koristeći @ModelAttribute anotaciju anotaciju sa proizvoljnom klasom iz sloja Model</h1>\r\n"); 
		if(result.hasErrors())
			retval.append("<p>"+error+"</b>");
		else
			retval.append(		
				"	<p>Uneli ste ime <b>"+korisnik.getIme()+"</b> i prezime <b>"+korisnik.getPrezime()+"</b></p>\r\n" +
	    		"	<p>Uneli ste pol <b>"+korisnik.getPol()+"</b></p>\r\n" +
	    		"	<p>Uneli ste platu <b>"+korisnik.getPlata()+"</b></p>\r\n");
		
		retval.append(
	    		"	<a href=\"forma.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");
		return retval.toString();
	}
}
 
