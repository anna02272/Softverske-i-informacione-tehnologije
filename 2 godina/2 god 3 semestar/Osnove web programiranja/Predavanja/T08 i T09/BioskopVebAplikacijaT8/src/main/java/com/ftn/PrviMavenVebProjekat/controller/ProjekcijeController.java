package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Controller
@RequestMapping(value="/Projekcije")
public class ProjekcijeController {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
	
	@Autowired
	private ProjekcijaService projekcijaService;

	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL; 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";	
	}

	@GetMapping
	@ResponseBody
	public String Index(@RequestParam(name="filmId", required=false) Long filmId, 
			HttpSession session) throws IOException {
		// čitanje
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);

		List<Projekcija> projekcije = projekcijaService.findByFilmId(filmId);

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
				"	<title>Projekcije</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\r\n"
				);
	// prijavljeni korisnik
	if (prijavljeniKorisnik != null) {
		// prijavljen
		out.append(
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td colspan=\"2\">\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n"
				);
	} else {
		// neprijavljen
		out.append(
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Niste prijavljeni.</th></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"registracija.html\">registracija</a></li>\r\n" + 
				"					<li><a href=\"prijava.html\">prijava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n"
				);
	}
		// meni: svi korisnici
		out.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n"
				);
	// meni: administrator
	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
			out.append(
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
				);
	}
			out.append(
				"	</ul>\r\n" + 
				"	<table class=\"tabela\">\r\n" + 
				"		<caption>Projekcije</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>r. br.</th>\r\n" + 
				"			<th>datum i vreme</th>\r\n" + 
				"			<th>film</th>\r\n" + 
				"			<th>tip</th>\r\n" + 
				"			<th>sala</th>\r\n" + 
				"			<th>cena karte</th>\r\n" + 
				"		</tr>\r\n"
				);
	// projekcije
	for (int it = 0; it < projekcije.size(); it++) {
		out.append(
				"		<tr>\r\n" + 
				"			<td class=\"broj\">" + (it + 1) + "</td>\r\n" + 
				"			<td><a href=\"Projekcije/Details?id=" + projekcije.get(it).getId() + "\">" + projekcije.get(it).getDatumIVreme().format(formatter) + "</a></td>\r\n" + 
				"			<td><a href=\"Filmovi/Details?id=" + projekcije.get(it).getFilm().getId() + "\">" + projekcije.get(it).getFilm().getNaziv() + "</a></td>\r\n" + 
				"			<td>" + projekcije.get(it).getTip() + "</td>\r\n" + 
				"			<td class=\"broj\">" + projekcije.get(it).getSala() + "</td>\r\n" + 
				"			<td class=\"broj\">" + projekcije.get(it).getCenaKarte() + "</td>\r\n" + 
				"		</tr>\r\n"
				);
	}
		out.append(
				"	</table>\r\n"
				);	
	// meni: administrator
	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
		out.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Projekcije/Create\">dodavanje projekcije</a></li>\r\n" + 
				"	</ul>\r\n"
				);
			}
		out.append(
				"</body>\r\n" + 
				"</html>"
				);
		
		return out.toString();
	}

	@GetMapping(value="/Details")
	@ResponseBody
	public String Details(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// čitanje
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
	
		Projekcija projekcija = projekcijaService.findOne(id);
		if (projekcija == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return "";
		}

		List<Film> filmovi = filmService.findAll();

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
				"	<title>Projekcije</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\r\n"
				);
	// prijavljeni korisnik
	if (prijavljeniKorisnik != null) {
		// prijavljen
		out.append(
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td colspan=\"2\">\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"Korisnici/Logout\">odjava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n"
				);
	} else {
		// neprijavljen
		out.append(
				"	<table class=\"korisnik\">\r\n" + 
				"		<tr><th>Niste prijavljeni.</th></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n" + 
				"					<li><a href=\"registracija.html\">registracija</a></li>\r\n" + 
				"					<li><a href=\"prijava.html\">prijava</a></li>\r\n" + 
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n"
				);
	}
		// meni: svi korisnici
		out.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Zanrovi\">žanrovi</a></li>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n"
				);
	// meni: administrator
	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
		out.append(
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n"
				);
	}
		out.append(
				"	</ul>\r\n"
				);
	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
		// izmena, brisanje
		out.append(
				"	<form method=\"post\" action=\"Projekcije/Edit\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\"" + projekcija.getId() + "\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<caption>Projekcija</caption>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>datum i vreme:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"date\" value=\"" + projekcija.getDatumIVreme().toLocalDate() + "\" name=\"datum\"/>&nbsp;\r\n" + 
				"					<input type=\"time\" value=\"" + projekcija.getDatumIVreme().toLocalTime() + "\" name=\"vreme\"/>\r\n" + 
				"				</td>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>film:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"filmId\">\r\n"
				);
// filmovi
for (Film itFilm: filmovi) {
	if (itFilm.equals(projekcija.getFilm())) {
		// film koji odgovara projekciji
		out.append(
				"						<option value=\"" + itFilm.getId() + "\" selected>" + itFilm.getNaziv() + "</option>\r\n"
				);
	} else {
		// drugi filmovi
		out.append(
				"						<option value=\"" + itFilm.getId() + "\">" + itFilm.getNaziv() + "</option>\r\n"
				);
	}
}
		out.append(
				"					</select>	\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>tip:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"tip\">\r\n" + 
				"						<option value=\"2D\"" + (projekcija.getTip().equals("2D")? " selected": "") + ">2D</option>\r\n" + 
				"						<option value=\"3D\"" + (projekcija.getTip().equals("3D")? " selected": "") + ">3D</option>\r\n" + 
				"						<option value=\"4D\"" + (projekcija.getTip().equals("4D")? " selected": "") + ">4D</option>\r\n" + 
				"					</select>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr><th>sala:</th><td><input type=\"number\" value=\"" + projekcija.getSala() + "\" min=\"1\" name=\"sala\"/></td></tr>\r\n" + 
				"			<tr><th>cena karte:</th><td><input type=\"number\" value=\"" + projekcija.getCenaKarte() + "\" min=\"0\" step=\"0.1\" name=\"cenaKarte\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\"/></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<form method=\"post\" action=\"Projekcije/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\"" + projekcija.getId() + "\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"/></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n"
				);
	} else {
		// običan prikaz
		out.append(
				"	<table class=\"forma\">\r\n" + 
				"		<caption>Projekcija</caption>\r\n" + 
				"		<tr><th>datum i vreme:</th><td>" + projekcija.getDatumIVreme().format(formatter) + "</td></tr>\r\n" + 
				"		<tr>\r\n" + 
				"			<th>film:</th><td><a href=\"Filmovi/Details?id=" + projekcija.getFilm().getId() + "\">" + projekcija.getFilm().getNaziv() + "</a></td>\r\n" + 
				"		</tr>\r\n" + 
				"		<tr><th>tip:</th><td>" + projekcija.getTip() + "</td></tr>\r\n" + 
				"		<tr><th>sala:</th><td>" + projekcija.getSala() + "</td></tr>\r\n" + 
				"		<tr><th>cena karte:</th><td>" + projekcija.getCenaKarte() + "</td></tr>\r\n" + 
				"	</table>\r\n"
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
	public String Create(@RequestParam(name="filmId", required=false) Long filmId, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || prijavljeniKorisnik.isAdministrator() == false) {
			response.sendRedirect(baseURL + "prijava.html");
			return "";
		}

		// čitanje
		List<Film> filmovi = filmService.findAll();

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
				"	<title>Projekcija</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<h2>Bioskop</h2>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"\">početna</a></li>\r\n" + 
				"	</ul>\n\r" + 
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
				"	<form method=\"post\" action=\"Projekcije/Create\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\"1\">\r\n" + 
				"		<table class=\"forma\">\r\n" + 
				"			<caption>Projekcija</caption>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>datum i vreme:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<input type=\"date\" value=\"" + LocalDate.now() + "\" name=\"datum\"/>&nbsp;\r\n" + 
				"					<input type=\"time\" value=\"" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + "\" name=\"vreme\"/>\r\n" + 
				"				</td>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>film:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"filmId\">\r\n"
				);
	// filmovi
	for (Film itFilm: filmovi) {
		out.append(
				"						<option value=\"" + itFilm.getId() + "\">" + itFilm.getNaziv() + "</option>\r\n"
				);
	}
		out.append(
				"					</select>\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>tip:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"tip\">\r\n" + 
				"						<option value=\"2D\">2D</option>\r\n" + 
				"						<option value=\"3D\">3D</option>\r\n" + 
				"						<option value=\"4D\">4D</option>\r\n" + 
				"					</select>	\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n" + 
				"			<tr><th>sala:</th><td><input type=\"number\" value=\"1\" min=\"1\" name=\"sala\"/></td></tr>\r\n" + 
				"			<tr><th>cena karte:</th><td><input type=\"number\" value=\"400.0\" min=\"0\" step=\"0.1\" name=\"cenaKarte\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\"/></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Projekcije\">povratak</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"</body>\r\n" + 
				"</html>"
				);
		
		return out.toString();
	}

	@PostMapping(value="/Create")
	public void Create(
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmId, @RequestParam(defaultValue="4D") String tip, @RequestParam int sala,
			@RequestParam double cenaKarte, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// validacija
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
		Film film = filmService.findOne(filmId);
		if (film == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// kreiranje
		Projekcija projekcija = new Projekcija(datumIVreme, film, sala, tip, cenaKarte);
		projekcijaService.save(projekcija);
		
		response.sendRedirect(baseURL + "Projekcije");
	}

	@PostMapping(value="/Edit")
	public void Edit(
			@RequestParam Long id, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmId, @RequestParam String tip, @RequestParam int sala,
			@RequestParam double cenaKarte, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// validacija
		Projekcija projekcija = projekcijaService.findOne(id);
		if (projekcija == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}
		
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
		Film film = filmService.findOne(filmId);
		if (film == null) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// izmena
		projekcija.setDatumIVreme(datumIVreme);
		projekcija.setFilm(film);
		projekcija.setTip(tip);
		projekcija.setSala(sala);
		projekcija.setCenaKarte(cenaKarte);
		projekcijaService.update(projekcija);
	
		response.sendRedirect(baseURL + "Projekcije");
	}

	@PostMapping(value="/Delete")
	public void Delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Projekcije");
			return;
		}

		// brisanje
		projekcijaService.delete(id);

		response.sendRedirect(baseURL + "Projekcije");
	}

}
