package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//GET: ZdravoSvete
@Controller
@RequestMapping(value="/ZdravoSvete")
public class ZdravoSveteController {

	//Specify the application URL
	@Value("${server.servlet.contextPath}")
	private String contextPath; 
			
	// GET: ZdravoSvete
	@GetMapping
	@ResponseBody
    public String getZdravo1() {

		//Specify the base URL for all relative URLs in a document
		String bURL = contextPath+"/";
		
    	String retHTML = 
    		"<html>\r\n" + 
    		"<head>\r\n" + 
    		"	<meta charset=\"UTF-8\">\r\n" + 
    		"	<base href=\""+bURL+"\">" + 
    		"	<title>Zdravo Svete 1</title>\r\n" + 
    		"</head>\r\n" + 
    		"<body>\r\n" + 
    		"	<h1>Zdravo svete 1 - slovo ć</h1>\r\n" + 
    		"	<a href=\"index.html\">nazad</a>\r\n" + 
    		"</body>\r\n" + 
			"</html>";		
  	 
        return retHTML;
    }
	
	// GET: ZdravoSvete/Zdravo2
	// GET: ZdravoSvete/Zdravo2-DrugaPutanja
	@GetMapping(value= {"/Zdravo2", "/Zdravo2-DrugaPutanja"})
    public void getZdravo2(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Specify the base URL for all relative URLs in a document
			String bURL = contextPath+"/";
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;	
			out = response.getWriter();
			out.write( 
	    		"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Zdravo Svete 2</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Zdravo svete 2 - slovo ć</h1>\r\n" + 
	    		"	<a href=\"ZdravoSvete/Zdravo2-DrugaPutanja\">preko drugog URL</a><br/>\r\n" +
	    		"	<a href=\"index.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	// GET: ZdravoSvete/Zdravo3
	@GetMapping(value="/Zdravo3")
    public void getZdravo3(Reader in, Writer out) {
		//Specify the base URL for all relative URLs in a document
		String bURL = contextPath+"/";
		
		try {
			out.write( 
	    		"<html>\r\n" + 
	    		"<head>\r\n" + 
	    		"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
	    		"	<title>Zdravo Svete 3</title>\r\n" + 
	    		"</head>\r\n" + 
	    		"<body>\r\n" + 
	    		"	<h1>Zdravo svete 3 - slovo ć</h1>\r\n" + 
	    		"	<a href=\"index.html\">nazad</a>\r\n" + 
	    		"</body>\r\n" + 
				"</html>");	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
	
	// GET: ZdravoSvete/Zdravo4
	@GetMapping(value="/Zdravo4")
    public void getZdravo4(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(contextPath+"/"+"zdravo4.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
//	When using String as a method return type the result may be name of view template
//	da bi vracao staticke HTML stranice iskljucicemo thymeleaf templejte 
//		u fajlu application.properties
	// GET: ZdravoSvete/Zdravo5
	@RequestMapping(value="/Zdravo5")
    public String getZdravo5() {
		//ocekuje relativni url
//		return ("../"+"zdravo5.html");
		//ocekuje apsolutni url, vec ukljuceno ime apliacije
        return ("/"+"zdravo5.html");
    }
}