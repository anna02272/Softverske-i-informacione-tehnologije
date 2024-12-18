package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmBrojac;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Zanr;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;
import com.ftn.PrviMavenVebProjekat.service.ZanrService;

@Controller
@RequestMapping(value="/Filmovi")
public class FilmoviController implements ServletContextAware {

	public static final String STATISTIKA_FILMOVA_KEY = "statistikaFilmova";
	public static final String POSECENI_FILMOVI_ZA_KORISNIKA_KEY = "poseceniFilmoviZaKorisnika";
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ProjekcijaService projekcijaService;

	@Autowired
	private ZanrService zanrService;
	
	@Autowired
	private ServletContext servletContext;
	private String baseURL;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	} 

	@PostConstruct
	public void init() {
		baseURL = servletContext.getContextPath() + "/";
	}

	// nema @ResponseBody
	// tip povratne vrednosti je ModelAndView
	@GetMapping
	public ModelAndView index(@RequestParam(name="zanrId", required=false) Long zanrId, 
			HttpSession session)  throws IOException {
		// čitanje
		// sledeće ne mora (a može) da se šalje template-u jer podacima iz sesije i iz context-a template može direktno da pristupa
		//Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		//FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
		//List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		List<Film> filmovi = filmService.findByZanrId(zanrId);

		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("filmovi"); // naziv template-a
		rezultat.addObject("filmovi", filmovi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
//	@GetMapping
//	@ResponseBody
//	@SuppressWarnings("unchecked")
//	public String index(@RequestParam(name="zanrId", required=false) Long zanrId, 
//			HttpSession session) throws IOException {
//		// čitanje
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
//
//		List<Film> filmovi = filmService.findByZanrId(zanrId);
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
//				"	<title>Filmovi</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\r\n"
//			);
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
//			);
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
//			);
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
//				"	</ul>\r\n" + 
//				"	<table class=\"tabela\">\r\n" + 
//				"		<caption>Filmovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<th>r. br.</th>\r\n" + 
//				"			<th>naziv</th>\r\n" + 
//				"			<th>žanr</th>\r\n" + 
//				"			<th>trajanje</th>\r\n" + 
//				"			<th></th>\r\n" + 
//				"		</tr>\r\n"
//			);
//	// filmovi
//	for (int it = 0; it < filmovi.size(); it++) {
//		out.append(
//				"		<tr>\r\n" + 
//				"			<td class=\"broj\">" + (it + 1) + "</td>\r\n" + 
//				"			<td><a href=\"Filmovi/Details?id=" + filmovi.get(it).getId() + "\">" + filmovi.get(it).getNaziv() + "</a></td>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n"
//				);
//	// žanrovi za film
//	for (Zanr itZanr: filmovi.get(it).getZanrovi()) {
//		out.append(
//				"					<li><a href=\"Zanrovi/Details?id=" + itZanr.getId() +"\">" + itZanr.getNaziv() + "</a></li>\r\n"
//				);
//		}
//		out.append(		
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"			<td class=\"broj\">" + filmovi.get(it).getTrajanje() + "</td>\r\n" + 
//				"			<td>\r\n" + 
//				"				<a href=\"Projekcije?filmId=" + filmovi.get(it).getId() + "\">projekcije</a>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n"
//				);
//	}
//		out.append(
//				"	</table>\r\n"
//				);	
//	// meni: administrator
//	if (prijavljeniKorisnik != null && prijavljeniKorisnik.isAdministrator()) {
//		out.append(
//				"	<ul>\r\n" + 
//				"		<li><a href=\"Filmovi/Create\">dodavanje filma</a></li>\r\n" + 
//				"	</ul>\r\n"
//				);
//	}
//// popularni filmovi
//if (!statistikaFilmova.isEmpty()) {
//		out.append(
//				"	<table class=\"horizontalni-meni\">\r\n" + 
//				"		<caption>Popularni filmovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n"
//				);
//	for (FilmBrojac itBrojac: statistikaFilmova.getFilmovi()) {
//		out.append(
//				"					<li>\r\n" + 
//				"						<a href=\"Filmovi/Details?id=" + itBrojac.getFilm().getId() + "\">" + itBrojac.getFilm().getNaziv() + "</a>\r\n" + 
//				"						<progress value=\"" + itBrojac.getBrojac() + "\" max=\"" + statistikaFilmova.getMax() +  "\"></progress>\r\n" + 
//				"						<span>" + itBrojac.getBrojac() + "</span>\r\n" + 
//				"					</li>\r\n"
//				);
//	}
//
//		out.append(
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n"
//				);
//}
//// posećeni filmovi
//if (!poseceniFilmovi.isEmpty()) {
//		out.append(
//				"	<table class=\"horizontalni-meni\">\r\n" + 
//				"		<caption>Posećeni filmovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n"
//				);
//	for (Film itFilm: poseceniFilmovi) {
//		out.append(
//				"					<li><a href=\"Filmovi/Details?id=" + itFilm.getId() + "\">" + itFilm.getNaziv() + "</a></li>\r\n"
//				);
//	}
//
//		out.append(
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n"
//				);
//}
//		out.append(
//				"</body>\r\n" + 
//				"</html>"
//				);
//
//		return out.toString();
//	}

	// nema @ResponseBody
	// tip povratne vrednosti je ModelAndView
	@GetMapping(value="/Details")
	@SuppressWarnings("unchecked")
	public ModelAndView details(@RequestParam Long id, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// čitanje
		// sledeće ne mora (a može) da se šalje template-u jer podacima iz sesije i iz context-a template može direktno da pristupa
		//Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);

		Film film = filmService.findOne(id);
		List<Zanr> zanrovi = zanrService.findAll();

		// obrada
		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
		statistikaFilmova.incrementBrojac(film);

		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		if (!poseceniFilmovi.contains(film)) {
			poseceniFilmovi.add(film);
		}

		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("film"); // naziv template-a
		rezultat.addObject("film", film); // podatak koji se šalje template-u
		rezultat.addObject("zanrovi", zanrovi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
//	@GetMapping(value="/Details")
//	@ResponseBody
//	@SuppressWarnings("unchecked")
//	public String details(@RequestParam Long id, 
//			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		// čitanje
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//
//		Film film = filmService.findOne(id);
//		List<Zanr> zanrovi = zanrService.findAll();
//
//		// obrada
//		FilmStatistika statistikaFilmova = (FilmStatistika) servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		statistikaFilmova.incrementBrojac(film);
//
//		List<Film> poseceniFilmovi = (List<Film>) session.getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
//		if (!poseceniFilmovi.contains(film)) {
//			poseceniFilmovi.add(film);
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
//				"	<title>Film</title>\r\n" + 
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
//			);
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
//				"	<form method=\"post\" action=\"Filmovi/Edit\">\r\n" + 
//				"		<input type=\"hidden\" name=\"id\" value=\"" + film.getId() + "\"/>\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<caption>Film</caption>\r\n" + 
//				"			<tr><th>naziv:</th><td><input type=\"text\" value=\"" +  film.getNaziv() + "\" name=\"naziv\"/></td></tr>\r\n" + 
//				"			<tr>\r\n" + 
//				"				<th>žanr:</th>\r\n" + 
//				"				<td>\r\n"
//				);
//// žanrovi za film
//for (Zanr itZanr: zanrovi) {
//	if (film.getZanrovi().contains(itZanr)) {
//		// žanr koji odgovara filmu
//		out.append(
//				"					<input type=\"checkbox\" name=\"zanrId\" value=\"" + itZanr.getId() + "\" checked/><span>" + itZanr.getNaziv() + "</span><br/>\r\n"
//				);
//	} else {
//		// ostali žanrovi
//		out.append(
//				"					<input type=\"checkbox\" name=\"zanrId\" value=\"" + itZanr.getId() + "\"/><span>" + itZanr.getNaziv() + "</span><br/>\r\n"
//				);
//	}
//}
//		out.append(
//				"				</td>\r\n" + 
//				"			</tr>\r\n" + 
//				"			<tr><th>trajanje:</th><td><input type=\"number\" min=\"5\" value=\"" +  film.getTrajanje() + "\" name=\"trajanje\"/></td></tr>\r\n" +
//				"			<tr><th></th><td><a href=\"Projekcije?filmId=" + film.getId() + "\">projekcije</a></td></tr>\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\"/></td></tr>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n" + 
//				"	<form method=\"post\" action=\"Filmovi/Delete\">\r\n" + 
//				"		<input type=\"hidden\" name=\"id\" value=\"" + film.getId() + "\"/>\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"/></td></tr>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n"
//				);
//	} else {
//		// običan prikaz
//		out.append(
//				"	<table class=\"forma\">\r\n" + 
//				"		<caption>Film</caption>\r\n" + 
//				"		<tr><th>naziv:</th><td>" + film.getNaziv() + "</td></tr>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<th>žanr:</th>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n"
//				);
//	// žanrovi za film
//	for (Zanr itZanr: film.getZanrovi()) {
//		out.append(
//				"					<li><a href=\"Zanrovi/Details?id=" + itZanr.getId() + "\">" + itZanr.getNaziv() + "</a></li>\r\n"
//				);
//	}
//		out.append(
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"		<tr><th>trajanje:</th><td>" + film.getTrajanje() + "</td></tr>\r\n" + 
//				"		<tr><th></th><td><a href=\"Projekcije?filmId=" + film.getId() + "\">projekcije</a></td></tr>\r\n" + 
//				"	</table>"
//				);
//	}
//		out.append(
//				"</body>\r\n" + 
//				"</html>"
//				);
//			
//		return out.toString();
//	}

	// nema @ResponseBody
	// tip povratne vrednosti je ModelAndView
	@GetMapping(value="/Create")
	public ModelAndView create(HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return null;
		}

		// čitanje
		List<Zanr> zanrovi = zanrService.findAll();

		// podaci sa nazivom template-a
		ModelAndView rezultat = new ModelAndView("dodavanjeFilma"); // naziv template-a
		rezultat.addObject("zanrovi", zanrovi); // podatak koji se šalje template-u

		return rezultat; // prosleđivanje zahteva zajedno sa podacima template-u
	}
	
//	@GetMapping(value="/Create")
//	@ResponseBody
//	public String create(HttpSession session, HttpServletResponse response) throws IOException {
//		// autentikacija, autorizacija
//		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
//		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
//			response.sendRedirect(baseURL + "Filmovi");
//			return "";
//		}
//
//		// čitanje
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
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
//				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviKorisnik.css\"/>\r\n" + 
//				"	<title>Dodavanje filma</title>\r\n" + 
//				"</head>\r\n" + 
//				"<body>\r\n" + 
//				"	<h2>Bioskop</h2>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"\">početna</a></li>\r\n" + 
//				"	</ul>\n\r" + 
//				"	<table class=\"korisnik\">\r\n" + 
//				"		<tr><th>Prijavljeni ste kao</th><td><a href=\"Korisnici/Details?korisnickoIme=" + prijavljeniKorisnik.getKorisnickoIme() + "\">" + prijavljeniKorisnik.getKorisnickoIme() + ".</a></td></tr>\r\n" + 
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
//				"	</ul>\n\r" + 
//				"	<form method=\"post\" action=\"Filmovi/Create\">\r\n" + 
//				"		<table class=\"forma\">\r\n" + 
//				"			<caption>Dodavanje filma</caption>\r\n" + 
//				"			<tr><th>naziv:</th><td><input type=\"text\" name=\"naziv\"/></td>\r\n" + 
//				"			<tr>\r\n" + 
//				"				<th>žanr:</th>\r\n" + 
//				"				<td>\r\n"
//				);
//	// žanrovi
//	for (Zanr itZanr: zanrovi) {
//		out.append(
//				"					<input type=\"checkbox\" name=\"zanrId\" value=\"" + itZanr.getId() + "\"/><span>" + itZanr.getNaziv() + "</span><br/>\r\n"
//				);
//	}
//		out.append(
//				"				</td>\r\n" + 
//				"			</tr>\r\n" + 
//				"			<tr><th>trajanje:</th><td><input type=\"number\"  min=\"5\" value=\"120\" name=\"trajanje\"/></td>\r\n" + 
//				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\"/></td>\r\n" + 
//				"		</table>\r\n" + 
//				"	</form>\r\n" + 
//				"	<ul>\r\n" + 
//				"		<li><a href=\"Filmovi\">povratak</a></li>\r\n" + 
//				"	</ul>\r\n" + 
//				"</body>\r\n" + 
//				"</html>"
//				);
//			
//		return out.toString();
//	}

	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// kreiranje
		Film film = new Film(naziv, trajanje);
		film.setZanrovi(zanrService.find(zanrIds));
		filmService.save(film);

		response.sendRedirect(baseURL + "Filmovi");
	}

	@PostMapping(value="/Edit")
	public void edit(@RequestParam Long id, 
			@RequestParam String naziv, @RequestParam int trajanje, @RequestParam(name="zanrId", required=false) Long[] zanrIds, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// validacija
		Film film = filmService.findOne(id);
		if (film == null) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}	
		if (naziv == null || naziv.equals("") || trajanje < 5) {
			response.sendRedirect(baseURL + "Filmovi/Details?id=" + id);
			return;
		}

		// izmena
		film.setNaziv(naziv);
		film.setTrajanje(trajanje);
		film.setZanrovi(zanrService.find(zanrIds));
		filmService.update(film);

		response.sendRedirect(baseURL + "Filmovi");
	}

	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, 
			HttpSession session, HttpServletResponse response) throws IOException {
		// autentikacija, autorizacija
		Korisnik prijavljeniKorisnik = (Korisnik) session.getAttribute(KorisnikController.KORISNIK_KEY);
		if (prijavljeniKorisnik == null || !prijavljeniKorisnik.isAdministrator()) {
			response.sendRedirect(baseURL + "Filmovi");
			return;
		}

		// brisanje
		Film obrisaniFilm = filmService.delete(id);
		// potrebno je obrisati i sve projekcije za film
		projekcijaService.deleteAll(obrisaniFilm);
	
		response.sendRedirect(baseURL + "Filmovi");
	}

}
