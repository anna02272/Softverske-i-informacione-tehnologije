package com.ftn.PrviMavenVebProjekat.controller;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/TestAjaxHTML")
public class TestAjaxHTMLController {

	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	@GetMapping
	@ResponseBody
	public String index(@RequestParam(required = false) String tekst) {
		System.out.println("Get:"+tekst);
		String htmlText = 
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base ahref=\""+baseURL+"\">\r\n" + 
				"	<title>TestAjaxHTML</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>TestAjaxHTML</h2>\r\n" + 
				"	<div id='mojDiv'>\r\n" + 
				"		<p>"+tekst+"</p>\r\n" + 
				"		<ul>\r\n" + 
				"			<li><a href=\""+baseURL+"\">početna</a></li>\r\n" + 
				"		</ul>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		return htmlText;
	}
	
	@PostMapping
	@ResponseBody
	public String indexPost(@RequestParam(required = false) String tekst) {
		System.out.println("Post:"+tekst);
		String htmlText = 
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base ahref=\""+baseURL+"\">\r\n" + 
				"	<title>TestAjaxHTML</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>TestAjaxHTML</h2>\r\n" + 
				"	<div id='mojDiv'>\r\n" + 
				"		<p>"+tekst+"</p>\r\n" + 
				"		<ul>\r\n" + 
				"			<li><a href=\""+baseURL+"\">početna</a></li>\r\n" + 
				"		</ul>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		
		return htmlText;
	}
}
