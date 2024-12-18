package com.ftn.PrviMavenVebProjekat.controller;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@GetMapping
	public String index() {
				
		// naziv template-a na koga se zahtev prosleđuje
		return "index";
	}
	
//	@GetMapping
//	@ResponseBody
//	public String index(HttpSession session) {
//		// čitanje
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//
//		// prikaz
//		StringBuilder out = new StringBuilder();
//		out.append(
//				"<!DOCTYPE html>\r\n" + 
//				"<html>\r\n" + 
//				"<head>\r\n" + 
//				"	<meta charset=\"UTF-8\">\r\n" + 
//				"	<base href=\"" + baseURL + "\">\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
//				"	<title>Prijava</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\n\r"
//				);
//	// prijavljeni korisnik
//	if (prijavljeniKorisnik != null) {
//		// prijavljen
//		out.append(
//				"	<table class=\"korisnik\">\r\n" + 
//				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td colspan=\"2\">\r\n" + 
//				"				<ul>\r\n" + 
//				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n"
//				);
//	} else {
//		// neprijavljen
//		out.append(
//				"	<table class=\"korisnik\">\r\n" + 
//				"		<tr><th>Niste prijavljeni.</th></tr>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n" + 
//				"					<li><a href=\"registracija.html\">registracija</a></li>\r\n" + 
//				"					<li><a href=\"prijava.html\">prijava</a></li>\r\n" + 
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n"
//				);
//	}
//		// meni: svi korisnici
//		out.append(
//				"	<ul>\r\n" + 
//				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
//				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
//				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n"
//				);
//	// meni: administrator
//	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
//		out.append(
//					"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
//				);
//	}
//		out.append(
//				"	</ul>\r\n" + 
//				"</body>\r\n" + 
//				"</html>"
//				);
//
//		return out.toString();
//	}
	
}
