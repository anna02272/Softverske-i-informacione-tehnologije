package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Component
@RequestMapping(value="/Zanrovi")
public class ZanroviController {

	@Autowired
	private ZanrService zanrService;

	@Autowired
	private FilmService filmService;

	@Autowired
	private ProjekcijaService projekcijaService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	@GetMapping
	public ModelAndView index(HttpSession session) throws IOException {
		List<Zanr> zanrovi = zanrService.findAll();

		ModelAndView rezultat = new ModelAndView("zanrovi");
		rezultat.addObject("zanrovi", zanrovi);

		return rezultat;
	}
	
//	@GetMapping
//	@ResponseBody
//	public String index(HttpSession session) throws IOException {
//		// čitanje
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//
//		List<Zanr> zanrovi = zanrService.findAll();
//
//		// prikaz
//		StringBuilder out = new StringBuilder();
//		out.append(
//				"<!DOCTYPE html>\r\n" + 
//				"<html>\r\n" + 
//				"<head>\r\n" + 
//				"	<meta charset=\"UTF-8\">\r\n" + 
//				"	<base href=\"" + baseURL + "\">\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
//				"	<title>Žanrovi</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\r\n"
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
//			out.append(
//				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
//				);
//	}
//			out.append(
//				"	</ul>\r\n" + 
//				"	<table class=\"tabela\">\r\n" + 
//				"		<caption>Žanrovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<th>r. br.</th>\r\n" + 
//				"			<th>naziv</th>\r\n" + 
//				"			<th></th>\r\n" + 
//				"		</tr>\r\n"
//				);
//	// meni: žanrovi
//	for (int it = 0; it < zanrovi.size(); it++) {
//		out.append(
//				"		<tr>\r\n" + 
//				"			<td class=\"broj\">" + (it + 1) + "</td>\r\n" + 
//				"			<td><a href=\"Zanrovi/Details?id=" + zanrovi.get(it).getId() + "\">" + zanrovi.get(it).getNaziv() + "</a></td>\r\n" + 
//				"			<td><a href=\"Filmovi?zanrId=" + zanrovi.get(it).getId() + "\">filmovi</a></td>\r\n" + 
//				"		</tr>"
//				);
//	}
//		out.append(
//				"	</table>\r\n"
//				);
//	// meni: administrator
//	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
//		out.append(
//				"	<ul>\r\n" + 		
//				"		<li><a href=\"Zanrovi/Create\">dodavanje žanra</a></li>\r\n" + 
//				"	</ul>\r\n"
//				);
//	}
//		out.append(
//				"</body>\r\n" + 
//				"</html>"
//				);
//
//		return out.toString();
//	}

	@GetMapping(value="/Details")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Zanr zanr = zanrService.findOne(id);

		ModelAndView rezultat = new ModelAndView("zanr");
		rezultat.addObject("zanr", zanr);

		return rezultat;
	}
	
//	@GetMapping(value="/Details")
//	@ResponseBody
//	public String details(@RequestParam Long id, 
//			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		// čitanje
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//
//		Zanr zanr = zanrService.findOne(id);
//
//		// prikaz
//		StringBuilder out = new StringBuilder();
//		out.append(
//				"<!DOCTYPE html>\r\n" + 
//				"<html>\r\n" + 
//				"<head>\r\n" + 
//				"	<meta charset=\"UTF-8\">\r\n" + 
//				"	<base href=\"" + baseURL + "\">\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
//				"	<title>Žanr</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\r\n"
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
//				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
//				);
//	}
//		out.append(
//				"	</ul>\r\n"
//				);
//	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
//		// izmena, brisanje
//		out.append(
//				"	<form method=\"post\" action=\"Zanrovi/Edit\">\r\n" + 
//				"		<input type=\"hidden\" name=\"id\" value=\"" + zanr.getId() + "\">\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<caption>Žanr</caption>\r\n" + 
//				"			<tr><th>naziv:</th><td><input type=\"text\" value=\"" + zanr.getNaziv() + "\" name=\"naziv\"/></td></tr>\r\n" +
//				"			<tr><th></th><td><a href=\"Filmovi?zanrId=" + zanr.getId() + "\">filmovi</a></td></tr>\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\"/></td></tr>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n" + 
//				"	<form method=\"post\" action=\"Zanrovi/Delete\">\r\n" + 
//				"		<input type=\"hidden\" name=\"id\" value=\"" + zanr.getId() + "\">\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"/></td></tr>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n"
//				);
//	} else {
//		// običan prikaz
//		out.append(
//				"	<table class=\"forma\">\r\n" + 
//				"		<caption>Žanr</caption>\r\n" + 
//				"		<tr><th>naziv:</th><td>" + zanr.getNaziv() + "</td></tr>\r\n" + 
//				"		<tr><th></th><td><a href=\"Filmovi?zanrId=" + zanr.getId() + "\">filmovi</a></td></tr>\r\n" + 
//				"	</table>\r\n"
//				);
//	}
//		out.append(
//				"</body>\r\n" + 
//				"</html>"
//				);
//			
//		return out.toString();
//	}

	@GetMapping(value="/Create")
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			System.out.println("nije prijavljen");
			response.sendRedirect(baseURL + "Zanrovi");
			return "zanrovi";
		}

		return "dodavanjeZanra";
	}
	
//	@GetMapping(value="/Create")
//	@ResponseBody
//	public String create(HttpSession session, HttpServletResponse response) throws IOException {
//		// autentikacija, autorizacija
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
//			response.sendRedirect(baseURL + "Zanrovi");
//			return "";
//		}
//
//		// prikaz
//		StringBuilder out = new StringBuilder();
//		out.append(
//				"<!DOCTYPE html>\r\n" + 
//				"<html>\r\n" + 
//				"<head>\r\n" + 
//				"	<meta charset=\"UTF-8\">\r\n" + 
//				"	<base href=\"" + baseURL + "\">\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
//				"	<title>Dodavanje žanra</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\n\r" + 
//				"	<table class=\"korisnik\">\r\n" + 
//				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td colspan=\"2\">\r\n" + 
//				"				<ul>\r\n" + 
//				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
//				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
//				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
//				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n" + 
//				"	</ul>\r\n" + 
//				"	<form method=\"post\" action=\"Zanrovi/Create\">\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<caption>Dodavanje žanra</caption>\r\n" + 
//				"			<tr><th>naziv:</th><td><input type=\"text\" name=\"naziv\"/></td></tr>\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\"/></td></tr>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"Zanrovi\">povratak</a></li>\r\n" + 
//				"	</ul>\r\n" + 
//				"</body>\r\n" + 
//				"</html>"
//				);
//		
//		return out.toString();
//	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// validacija
		if (naziv.equals("")) {
			response.sendRedirect(baseURL + "Zanrovi/Create");
			return;
		}

		// kreiranje
		Zanr zanr = new Zanr(naziv);
		zanrService.save(zanr);

		response.sendRedirect(baseURL + "Zanrovi");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// validacija
		Zanr zanr = zanrService.findOne(id);
		if (zanr == null) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}	
		if (naziv == null || naziv.equals("")) {
			response.sendRedirect(baseURL + "Zanrovi/Details?id=" + id);
			return;
		}

		// izmena
		zanr.setNaziv(naziv);
		zanrService.update(zanr);

		response.sendRedirect(baseURL + "Zanrovi");
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik korisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (korisnik == null || !korisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Zanrovi");
			return;
		}

		// brisanje
		Zanr obrisaniZanr = zanrService.delete(id);
		// potrebno je obrisati i sve filmove za žanr
		List<Film> obrisaniFilmovi = filmService.deleteAll(obrisaniZanr);
		// potrebno je obrisati i sve projekcije za filmove
		projekcijaService.deleteAll(obrisaniFilmovi);

		response.sendRedirect(baseURL + "Zanrovi");
	}
	
}
