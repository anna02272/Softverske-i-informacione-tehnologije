package com.ftn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.BootstrapWith;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SpringSessionController {

	@GetMapping("/")
	@ResponseBody
	public String index(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> poruke = (List<String>) session.getAttribute("poruke");

		if (poruke == null) {
			poruke = new ArrayList<>();
			session.setAttribute("poruke", poruke);
		}
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<title>Primer rada sa Spring Session koji koristi Redis repozitorijum</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div>\r\n" + 
				"		<form action=\"/sacuvajPoruku\" method=\"post\">\r\n" + 
				"			<textarea name=\"poruka\" cols=\"40\" rows=\"2\"></textarea><br/>\r\n" + 
				"			<input type=\"submit\" value=\"Sačuvaj poruku\" />\r\n" + 
				"		</form>\r\n" + 
				"	</div>\r\n" +
				"	<div>\r\n" + 
				"		<h2>Messages</h2>\r\n");
		for (String poruka : poruke) {
			retVal.append(
				"		<ul>\r\n" + 
				"			<li>"+poruka+"</li>\r\n" + 
				"		</ul>\r\n"); 
		}
		retVal.append(
				"	</div>\r\n" +
				"	<div>\r\n" + 
				"		<h2>ID Sesije</h2>\r\n" + 
				"		Trenutni ID sesije je "+ session.getId() +"\r\n" + 
				"	</div>\r\n" + 
				"	<div>\r\n" + 
				"		<form action=\"/obrisiSve\" method=\"post\">\r\n" + 
				"			<input type=\"submit\" value=\"Obrisi sve\" />\r\n" + 
				"		</form>\r\n" + 
				"	</div>\r\n" + 
				"</body>\r\n" + 
				"</html>");

		return retVal.toString();
	}

	@PostMapping("/sacuvajPoruku")
	public String sacuvajPoruku(@RequestParam("poruka") String poruka, HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<String> poruke = (List<String>) request.getSession().getAttribute("poruke");
		if (poruke == null) {
			poruke = new ArrayList<>();
			request.getSession().setAttribute("poruke", poruke);
		}
		poruke.add(poruka);
		//mora jer se sesija svaki put cita iz Redis repozitorijuma
		//da je samo in memory sesija ovo ne bi bilo potrebno
		request.getSession().setAttribute("poruke", poruke);
		return "redirect:/";
	}

	@PostMapping("/obrisiSve")
	public String destroySession(HttpServletRequest request) {
//		request.getSession().removeAttribute("poruke");
		request.getSession().invalidate();
		return "redirect:/";
	}
}