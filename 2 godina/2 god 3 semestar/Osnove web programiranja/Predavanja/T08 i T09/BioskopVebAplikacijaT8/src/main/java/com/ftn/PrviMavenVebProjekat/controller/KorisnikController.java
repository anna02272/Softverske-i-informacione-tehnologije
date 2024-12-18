package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.service.KorisnikService;

@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikController {

	public static final String KORISNIK_KEY = "prijavljeniKorisnik";
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {	
		baseURL = servletContext.getContextPath() + "/";			
	}

	@GetMapping
	@ResponseBody
	public String index(HttpSession session, HttpServletResponse response) throws IOException {		
		// autentikacija, autorzacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL);
			return "";
		}

		// čitanje
		List<Korisnik> korisnici = korisnikService.findAll();

		// prikaz
		StringBuilder out = new StringBuilder();
		out.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\"" + baseURL + "\">\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
				"	<title>Korisnici</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td colspan=\"2\">\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<table class=\"tabela\">\r\n" + 
				"		<caption>Korisnici</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>r. br.</th>\r\n" + 
				"			<th>korisničko ime</th>\r\n" + 
				"			<th>e-mail</th>\r\n" + 
				"			<th>pol</th>\r\n" + 
				"			<th>administrator</th>\r\n" + 
				"			<th>prijavljen</th>\r\n" + 
				"		</tr>\r\n"
				);
		// korisnici
		for (int it = 0; it < korisnici.size(); it++) {
			out.append(
				"		<tr>\r\n" + 
				"			<td class=\"broj\">" + (it + 1) + "</td>\r\n" + 
				"			<td><a href=\"Korisnici/Details?korisnickoIme=" + korisnici.get(it).getKorisnickoIme() + "\">" + korisnici.get(it).getKorisnickoIme() + "</a></td>\r\n" +
				"			<td>" + korisnici.get(it).getEMail() + "</td>\r\n" + 
				"			<td>" + korisnici.get(it).getPol() + "</td>\r\n" + 
				"			<td>" + (korisnici.get(it).isAdministrator()? "da": "ne") + "</td>\r\n" + 
				"			<td>" + (korisnici.get(it).isPrijavljen()? "da": "ne") + "</td>\r\n" + 
				"		</tr>\r\n"
				);
		}
			out.append(
				"	</table>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Korisnici/Create\">dodavanje korisnika</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"</body>\r\n" + 
				"</html>"
				);
	
		return out.toString();
	}

	@GetMapping(value="/Details")
	@ResponseBody
	public String details(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da vidi druge korisnike; svaki korisnik može da vidi sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return "";
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return "";
		}

		// prikaz
		StringBuilder out = new StringBuilder();
		out.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\"" + baseURL + "\">\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
				"	<title>Žanr</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td colspan=\"2\">\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n"
				);
	// meni: administrator
	if (prijavljeniKorisnik.isAdministrator()) {
		out.append(
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
				);
	}
		out.append(
				"	</ul>\r\n" + 
				"	<form method=\"post\" action=\"Korisnici/Edit\">\r\n" + 
				"		<input type=\"hidden\" name=\"korisnickoIme\" value=\"" + korisnik.getKorisnickoIme() + "\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<caption>Žanr</caption>\r\n" + 
				"			<tr><th>korisničko ime:</th><td>" + korisnik.getKorisnickoIme() + "</td></tr>\r\n" + 
				"			<tr><th>lozinka:</th><td><input type=\"password\" name=\"lozinka\"/></td></tr>\r\n" + 
				"			<tr><th>e-mail:</th><td><input type=\"text\" name=\"eMail\" value=\"" + korisnik.getEMail() + "\"/></td></tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>pol:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"radio\" name=\"pol\" value=\"muški\"" + (korisnik.getPol().equals("muški")? " checked": "") + "/><span>muški</span>&nbsp;\r\n" + 
				"					<input type=\"radio\" name=\"pol\" value=\"ženski\"" + (korisnik.getPol().equals("ženski")? " checked": "") + "/><span>ženski</span>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n"
				);
// korisniku ne treba informacija da li je administrator i da li je prijavljen ako posmatra sebe
if (!prijavljeniKorisnik.equals(korisnik)) {
	if (korisnik.isAdministrator()) {
		out.append(
				"			<tr><th>administrator:</th><td><input type=\"checkbox\" name=\"administrator\" checked/></td></tr>\r\n"
				);
	} else {
		out.append(
				"			<tr><th>administrator:</th><td><input type=\"checkbox\" name=\"administrator\"/></td></tr>\r\n"
				);
	}
		out.append(
				"			<tr><th>prijavljen:</th><td>" + (korisnik.isPrijavljen()? "da": "ne") + "</td></tr>\r\n"
				);
}
		out.append(
				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\"/></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n"
				);
	// meni: administrator
	// samo administrator može da briše korisnike, ali ne i sebe
	if (prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.equals(korisnik)) {
		out.append(
				"	<form method=\"post\" action=\"Korisnici/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"korisnickoIme\" value=\"" + korisnik.getKorisnickoIme() + "\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"/></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n"
				);
	}
		out.append(
				"</body>\r\n" + 
				"</html>"
				);
			
		return out.toString();
	}

	@GetMapping(value="/Create")
	@ResponseBody
	public String create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return "";
		}

		// prikaz
		StringBuilder out = new StringBuilder();
		out.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
				"	<base href=\"" + baseURL + "\">\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
				"	<title>Registracija</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td colspan=\"2\">\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<form method=\"post\" action=\"Korisnici/Create\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<caption>Dodavanje korisnika</caption>\r\n" + 
				"			<tr><th>korisničko ime:</th><td><input type=\"text\" name=\"korisnickoIme\"/></td>\r\n" + 
				"			<tr><th>lozinka:</th><td><input type=\"password\" name=\"lozinka\"/></td>\r\n" + 
				"			<tr><th>e-mail:</th><td><input type=\"text\" name=\"eMail\"/></td></tr>" + 
				"			<tr>\r\n" + 
				"				<th>pol:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"radio\" name=\"pol\" value=\"muški\" checked/><span>muški</span>&nbsp;\r\n" + 
				"					<input type=\"radio\" name=\"pol\" value=\"ženski\"/><span>ženski</span>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr><th>administrator:</th><td><input type=\"checkbox\" name=\"administrator\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\"/></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Korisnici\">povratak</a></li>\r\n" + 
				"	</ul>" + 
				"</body>\r\n" + 
				"</html>"
				);
		
		return out.toString();
	}
	
	@PostMapping(value="/Create")
	public void create(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da kreira korisnike
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// validacija
		Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
		if (postojeciKorisnik != null) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (korisnickoIme.equals("") || lozinka.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (eMail.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}
		if (!pol.equals("muški") && !pol.equals("ženski")) {
			response.sendRedirect(baseURL + "Korisnici/Create");
			return;
		}

		// kreiranje
		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator != null);
		korisnikService.save(korisnik);

		response.sendRedirect(baseURL + "Korisnici");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam String korisnickoIme, 
			@RequestParam String lozinka, String eMail, @RequestParam String pol, @RequestParam(required=false) String administrator,
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da menja druge korisnike; svaki korisnik može da menja sebe
		if (prijavljeniKorisnik == null || (!prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme))) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// validacija
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		if (korisnik == null) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}
		if (eMail.equals("")) {
			response.sendRedirect(baseURL + "Korisnici/Edit?korisnicoIme=" + korisnickoIme);
			return;
		}
		if (!pol.equals("muški") && !pol.equals("ženski")) {
			response.sendRedirect(baseURL + "Korisnici/Edit?korisnicoIme=" + korisnickoIme);
			return;
		}

		// izmena
		if (!lozinka.equals("")) {
			korisnik.setLozinka(lozinka);
		}
		korisnik.setEMail(eMail);
		korisnik.setPol(pol);
		// privilegije može menjati samo administrator i to drugim korisnicima
		if (prijavljeniKorisnik.isAdministrator() && !prijavljeniKorisnik.equals(korisnik)) {
			korisnik.setAdministrator(administrator != null);
		}
		korisnikService.update(korisnik);

		// sigurnost
		if (!prijavljeniKorisnik.equals(korisnik)) {
			// TODO odjaviti korisnika
		}

		if (prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Korisnici");
		} else {
			response.sendRedirect(baseURL);
		}
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam String korisnickoIme, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		// samo administrator može da briše korisnike, ali ne i sebe
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator() || prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme)) {
			response.sendRedirect(baseURL + "Korisnici");
			return;
		}

		// brisanje
		korisnikService.delete(korisnickoIme);

		// sigurnost
		// TODO odjaviti korisnika
		
		response.sendRedirect(baseURL + "Korisnici");
	}

	@PostMapping(value="/Register")
	@ResponseBody
	public String register(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			@RequestParam String eMail, @RequestParam String pol, @RequestParam String ponovljenaLozinka,
			HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik postojeciKorisnik = korisnikService.findOne(korisnickoIme);
			if (postojeciKorisnik != null) {
				throw new Exception("Korisničko ime već postoji!");
			}
			if (korisnickoIme.equals("") || lozinka.equals("")) {
				throw new Exception("Korisničko ime i lozinka ne smeju biti prazni!");
			}
			if (!lozinka.equals(ponovljenaLozinka)) {
				throw new Exception("Lozinke se ne podudaraju!");
			}
			if (eMail.equals("")) {
				throw new Exception("E-mail ne sme biti prazan!");
			}
			if (!pol.equals("muški") && !pol.equals("ženski")) {
				throw new Exception("Morate odabrati pol!");
			}

			// registracija
			Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol);
			korisnikService.save(korisnik);

			response.sendRedirect(baseURL + "prijava.html");
			return "";
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna registracija!";
			}

			StringBuilder out = new StringBuilder();
			out.append(
					"<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"	<meta charset=\"UTF-8\">\r\n" + 
					"	<base href=\"" + baseURL + "\">\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n" + 
					"	<title>Registracija</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<h2>Bioskop</h2>\r\n" + 
					"	<ul>\r\n" + 
					"		<li><a href=\"\">početna</a></li>\r\n" + 
					"	</ul>\n\r" + 
					"	<table class=\"korisnik\">\r\n" + 
					"		<tr><th>Niste prijavljeni</th></tr>\r\n" + 
					"		<tr>\r\n" + 
					"			<td>\r\n" + 
					"				<ul>\r\n" + 
					"					<li><a href=\"registracija.html\">registracija</a></li>\r\n" + 
					"					<li><a href=\"prijava.html\">prijava</a></li>\r\n" + 
					"				</ul>\r\n" + 
					"			</td>\r\n" + 
					"		</tr>\r\n" + 
					"	</table>\r\n" + 
					"	<ul>\r\n" + 
					"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
					"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
					"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
					"	</ul>\r\n" + 
					"	<form method=\"post\" action=\"Korisnici/Register\">\r\n" + 
					"		<table class=\"forma\">\r\n" + 
					"			<caption>Registracija</caption>\r\n" + 
					"			<tr><th>korisničko ime:</th><td><input type=\"text\" name=\"korisnickoIme\"/></td></tr>\r\n" + 
					"			<tr><th>lozinka:</th><td><input type=\"password\" name=\"lozinka\"/></td></tr>\r\n" + 
					"			<tr><th>ponovljena lozinka:</th><td><input type=\"password\" name=\"ponovljenaLozinka\"/></td></tr>\r\n" +
					"			<tr><th>e-mail:</th><td><input type=\"text\" name=\"eMail\"/></td></tr>" + 
					"			<tr>\r\n" + 
					"				<th>pol:</th>\r\n" + 
					"				<td>\r\n" + 
					"					<input type=\"radio\" name=\"pol\" value=\"muški\" checked/><span>muški</span>&nbsp;\r\n" + 
					"					<input type=\"radio\" name=\"pol\" value=\"ženski\"/><span>ženski</span>\r\n" + 
					"				</td>\r\n" + 
					"			</tr>\r\n" + 
					"			<tr><th></th><td><input type=\"submit\" value=\"Registracija\"/></td></tr>\r\n" + 
					"		</table>\r\n" + 
					"	</form>\r\n" + 
					"	<p class=\"greska\">" + poruka + "</p>\r\n" + 
					"</body>\r\n" + 
					"</html>"
					);
			
			return out.toString();
		}
	}
	
	@PostMapping(value="/Login")
	@ResponseBody
	public String postLogin(@RequestParam String korisnickoIme, @RequestParam String lozinka, 
			HttpSession session, HttpServletResponse response) throws IOException {
		try {
			// validacija
			Korisnik korisnik = korisnikService.findOne(korisnickoIme, lozinka);
			if (korisnik == null) {
				throw new Exception("Neispravno korisničko ime ili lozinka!");
			}			
			if (korisnik.isPrijavljen()) {
				throw new Exception("Već ste prijavljeni!");
			}

			// prijava
			korisnik.setUlogovan(true);
			session.setAttribute(KorisnikController.KORISNIK_KEY, korisnik);
			
			response.sendRedirect(baseURL);
			return "";
		} catch (Exception ex) {
			// ispis greške
			String poruka = ex.getMessage();
			if (poruka == "") {
				poruka = "Neuspešna prijava!";
			}
			
			StringBuilder out = new StringBuilder();
			out.append(
					"<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"	<meta charset=\"UTF-8\">\r\n" + 
					"	<base href=\"" + baseURL + "\">\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
					"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
					"	<title>Prijava</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<h2>Bioskop</h2>\r\n" + 
					"	<ul>\r\n" + 
					"		<li><a href=\"\">početna</a></li>\r\n" + 
					"	</ul>\n\r" + 
					"	<table class=\"korisnik\">\r\n" + 
					"		<tr>\r\n" + 
					"			<th>Niste prijavljeni.</th>\r\n" + 
					"		</tr>\r\n" + 
					"		<tr>\r\n" + 
					"			<td>\r\n" + 
					"				<ul>\r\n" + 
					"					<li><a href=\"registracija.html\">registracija</a></li>\r\n" + 
					"					<li><a href=\"prijava.html\">prijava</a></li>\r\n" + 
					"				</ul>\r\n" + 
					"			</td>\r\n" + 
					"		</tr>\r\n" + 
					"	</table>\r\n" + 
					"	<ul>\r\n" + 
					"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
					"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
					"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
					"	</ul>\r\n" + 
					"	<form method=\"post\" action=\"Korisnici/Login\">\r\n" + 
					"		<table class=\"forma\">\r\n" + 
					"			<caption>Prijava</caption>\r\n" + 
					"			<tr><th>korisničko ime:</th><td><input type=\"text\" name=\"korisnickoIme\"/></td></tr>\r\n" + 
					"			<tr><th>lozinka:</th><td><input type=\"password\" name=\"lozinka\"/></td></tr>\r\n" + 
					"			<tr><th></th><td><input type=\"submit\" value=\"Prijava\"/></td></tr>\r\n" + 
					"		</table>\r\n" + 
					"	</form>\r\n" + 
					"	<p class=\"greska\">" + poruka + "</p>\r\n" + 
					"</body>\r\n" + 
					"</html>"
					);
			
			return out.toString();
		}
	}

	@GetMapping(value="/Logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		// čitanje
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);

		// odjava
		if (prijavljeniKorisnik != null)
			prijavljeniKorisnik.setUlogovan(false);
	
		session.invalidate();
		
		response.sendRedirect(baseURL);
	}
	
}
