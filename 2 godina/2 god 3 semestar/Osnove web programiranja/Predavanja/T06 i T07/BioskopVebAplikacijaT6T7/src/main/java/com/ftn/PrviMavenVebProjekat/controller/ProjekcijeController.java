package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmBrojac;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Filmovi;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.FilmService;
import com.ftn.PrviMavenVebProjekat.service.ProjekcijaService;

@Controller
@RequestMapping(value="/Projekcije")
public class ProjekcijeController {

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	
	@Autowired
	private ProjekcijaService projekcijaService;
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
//	@Autowired
//	private MemorijaAplikacije memorijaAplikacije;
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";	
	}
	
	// GET: Projekcije
	@SuppressWarnings("unchecked")
	@GetMapping
	public void Index(@RequestParam(name="filmID", required=false) Long filmID, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);

		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		//preuzimanje vrednosti iz sesije	
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) request.getSession().getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		
		if(filmID!=null && filmID<=0) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		Film film = null;
		if(filmID!=null && filmService.findOne(filmID)!=null) {
			film = filmService.findOne(filmID);
		}
		
		response.setContentType("text/html;charset=UTF-8");
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Projekcije</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body> "+
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n");
		if(film!=null) {
			retVal.append(
				"	<a href=\"Filmovi/Details?id="+film.getId()+"\">"+film.getNaziv()+ "</a></li>\r\n");
			if(korisnik.isAdministrator())
				retVal.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Projekcije/Create?filmID="+film.getId()+"\">dodavanje projekcije za film</a></li>\r\n" + 
				"	</ul>\r\n");
		}
				
		retVal.append(	
				"		<table>\r\n" + 
				"			<caption>Projekcije</caption>\r\n" + 
				"			<tr bgcolor=\"DodgerBlue\">\r\n" + 
				"				<th>r. br.</th>\r\n" + 
				"				<th>datum i vreme</th>\r\n" + 
				"				<th>film</th>\r\n" + 
				"				<th>tip</th>\r\n" +
				"				<th>sala</th>\r\n" + 
				"				<th>cena karte</th>\r\n" +
				"			</tr>\r\n");
		
		List<Projekcija> projekcije;
		if(film==null) {
			projekcije = projekcijaService.findAll();
		}
		else {
			projekcije = projekcijaService.findByFilm(film);
		}
		
		
		for (int i=0; i < projekcije.size(); i++) {
			retVal.append(
				"			<tr>\r\n" + 
				"				<td class=\"broj\">"+ (i+1) +"</td>\r\n" + 
				"				<td class=\"broj\"><a href=\"Projekcije/Details?id="+ projekcije.get(i).getId()+"\">" +
											projekcije.get(i).getDatumIVreme().format(formatter) +"</td>\r\n" +
				"				<td><a href=\"Filmovi/Details?id="+projekcije.get(i).getFilm().getId()+"\">" 
											+projekcije.get(i).getFilm().getNaziv()+ "</a></td>\r\n" +
				"				<td>"+ projekcije.get(i).getTipProjekcije() +"</td>\r\n" +
				"				<td class=\"broj\">"+ projekcije.get(i).getSala() +"</td>\r\n" +
				"				<td class=\"broj\">"+ projekcije.get(i).getCenaKarte() +"</td>\r\n" +
				"			</tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n");
		
		if(korisnik.isAdministrator()) {
			retVal.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Projekcije/Create\">dodavanje projekcije</a></li>\r\n" + 
				"	</ul>\r\n");
		}
		
		//rad se objektom pribavljenim iz ServletContext
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
				"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
				
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		response.getWriter().write(retVal.toString());
	}
	
	// GET: Projekcije/Details
	@SuppressWarnings("unchecked")
	@GetMapping(value="/Details")
	@ResponseBody
	public void Details(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);

		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		//preuzimanje vrednosti iz sesije	
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) request.getSession().getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);

		Projekcija projekcija = projekcijaService.findOne(id);
		if(projekcija==null) {
			response.sendRedirect(bURL+"Projekcije");
			return;
		}
		
		response.setContentType("text/html;charset=UTF-8");
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Projekcija</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 			
				"	<form method=\"post\" action=\"Projekcije/Edit\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+projekcija.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Projekcija</caption>\r\n" + 
				"			<tr><th>datum i vreme:</th>"+
				 				"<td>" +
				 					"<input type=\"date\" value=\""+projekcija.getDatumIVreme().toLocalDate()+"\" name=\"datum\"/>&nbsp;"+
				 					"<input type=\"time\" value=\""+projekcija.getDatumIVreme().toLocalTime()+"\" name=\"vreme\"/>"+
				 				"</td>"+
				 			"</tr>\r\n"); 
		retVal.append(
				"			<tr>\r\n"+
				"				<th>film:</th>\r\n"+
				"				<td>\r\n"+		
				"					<select name=\"filmID\">\r\n");
		for (Film film : filmService.findAll()) {
			retVal.append(
				"						<option value=\""+film.getId()+"\" "+(film.equals(projekcija.getFilm())?"selected":"")+">"+film.getNaziv()+"</option>\r\n");
		}
		retVal.append(
				"					</select>\r\n"+
				"				</td>\n\r"+
				"			</tr>\r\n");
		
		retVal.append(
				"			<tr>\r\n" + 
				"				<th>tip:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"tipProjekcije\">\r\n" + 
				"						<option value=\"2D\" "+(projekcija.getTipProjekcije().equals("2D")?"selected":"")+">2D</option>\r\n" + 
				"						<option value=\"3D\" "+(projekcija.getTipProjekcije().equals("3D")?"selected":"")+">3D</option>\r\n" +
				"						<option value=\"4D\" "+(projekcija.getTipProjekcije().equals("4D")?"selected":"")+">4D</option>\r\n" + 
				"					</select>	\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n");
		
		retVal.append(
				"			<tr><th>sala:</th><td><input type=\"number\" min=\"1\" value=\""+projekcija.getSala()+"\" name=\"sala\"/></td></tr>\r\n" + 
				"			<tr><th>cena:</th><td><input type=\"number\" min=\"0\" step=\"0.1\" value=\""+projekcija.getCenaKarte()+"\" name=\"cena\"/></td></tr>\r\n");
		if(korisnik.isAdministrator()) {
			retVal.append(
				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\" /></td></tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n" +
				"	</form>\r\n" +
				"	<br/>\r\n");
		if(korisnik.isAdministrator()) {
			retVal.append(
				"	<form method=\"post\" action=\"Projekcije/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+projekcija.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n");
		}
		
		//rad se objektom pribavljenim iz ServletContext
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
				"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		response.getWriter().write(retVal.toString());
	}
	
	
	// GET: Projekcije/Create
	@GetMapping(value="/Create")
	public void Create(@RequestParam(name="filmID", required=false) Long filmID, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz konteksta
		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);

		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		//preuzimanje vrednosti iz sesije	
		@SuppressWarnings("unchecked")
		List<Film> poseceniFilmovi = (List<Film>) request.getSession().getAttribute(FilmoviController.POSECENI_FILMOVI_ZA_KORISNIKA_KEY);
		
		if(filmID!=null && filmID<=0) {
			response.sendRedirect(bURL+"Projekcije");
			return;
		}
		Film film = null;
		if(filmID!=null && filmService.findOne(filmID)!=null) {
			film = filmService.findOne(filmID);
		}

		
		response.setContentType("text/html;charset=UTF-8");
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
//				"	<META HTTP-EQUIV=\"Cache-Control\" CONTENT=\"no-cache\">\r\n" + 
//				"	<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\"> \r\n" + 
//				"	<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">\r\n" +
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">\r\n" + 
				"	<title>Projekcija</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<div> Prijavljen je <a href=\"Korisnici/Details?korisnickoIme="+korisnik.getKorisnickoIme()+"\">"+ korisnik.getIme() +" "+ korisnik.getPrezime()+ "</a> <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 			
				"	<form method=\"post\" action=\"Projekcije/Create\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Projekcija</caption>\r\n" + 
				"			<tr><th>datum i vreme:</th>"+
				 				"<td>" +
				 					"<input type=\"date\" value=\""+LocalDate.now()+"\" name=\"datum\"/>&nbsp;"+
				 					"<input type=\"time\" value=\""+LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))+"\" name=\"vreme\"/>"+
				 				"</td>"+
				 			"</tr>\r\n"); 
		retVal.append(
				"			<tr>\r\n"+
				"				<th>film:</th>\r\n"+
				"				<td>\r\n"+		
				"					<select name=\"filmID\">\r\n");	
		for (Film f : filmService.findAll()) {
			retVal.append(
				"						<option value=\""+f.getId()+"\" "+(f.equals(film)?"selected":"")+">"+f.getNaziv()+"</option>\r\n");
		}	
		retVal.append(
				"					</select>\r\n"+
				"				</td>\n\r"+
				"			</tr>\r\n");
		
		retVal.append(
				"			<tr>\r\n" + 
				"				<th>tip:</th>\r\n" + 
				"				<td>\r\n" + 
				"					<select name=\"tipProjekcije\">\r\n" + 
				"						<option value=\"2D\">2D</option>\r\n" + 
				"						<option value=\"3D\">3D</option>\r\n" +
				"						<option value=\"4D\">4D</option>\r\n" + 
				"					</select>	\r\n" + 
				"				</td>\r\n" + 
				"			</tr>\r\n");
		
		retVal.append(
				"			<tr><th>sala:</th><td><input type=\"number\" min=\"1\" value=\"1\" name=\"sala\"/></td></tr>\r\n" + 
				"			<tr><th>cena:</th><td><input type=\"number\" min=\"0\" step=\"0.1\" value=\"0\" name=\"cena\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\" /></td></tr>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" +
				"	<br/>\r\n");
		
		//rad se objektom pribavljenim iz ServletContext
		if(!statistikaFilmova.isEmpty()) {
			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
			
			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
			
			retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Popularni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
			for (int i=0; i < popularniFilmovi.size(); i++) {
				retVal.append(
				"					<li>\n\r"+
				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
				"					</li>\r\n");
			}
			retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");			
		}
		//rad se objektom pribavljenim iz sesije
		retVal.append(
				"	<table class=\"horizontalni-meni\">\r\n" +		
				"		<caption>Posećeni filmovi</caption>\r\n" + 
				"		<tr>\r\n" + 
				"			<td>\r\n" + 
				"				<ul>\r\n");
		if(poseceniFilmovi.isEmpty()) {
			retVal.append(
				"					<li>Nema posećenih filmova</li>\r\n");
		}
		for (int i=0; i < poseceniFilmovi.size(); i++) {
			retVal.append(
				"					<li><a href=\"Filmovi/Details?id="+poseceniFilmovi.get(i).getId()+"\">"
											+poseceniFilmovi.get(i).getNaziv()+ "</a></li>\r\n");
		}
		retVal.append(
				"				</ul>\r\n" + 
				"			</td>\r\n" + 
				"		</tr>\r\n" + 
				"	</table>\r\n");
	
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		response.getWriter().write(retVal.toString());
	}

//	// POST: Projekcije/Create
	@PostMapping(value="/Create")
	public void Create(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmID, @RequestParam(defaultValue = "4D") String tipProjekcije, @RequestParam int sala,
			@RequestParam(name="cena") double cenaKarte, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		if(filmID!=null && filmID<=0) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		Film film = null;
		if(filmID!=null && filmService.findOne(filmID)!=null) {
			film = filmService.findOne(filmID);
		}
		if(film==null) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
				
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
	
		Projekcija projekcija = new Projekcija(datumIVreme, film, sala, tipProjekcije, cenaKarte);
		Projekcija saved = projekcijaService.save(projekcija);
		
		response.sendRedirect(bURL+"Projekcije");
	}
	
	// POST: Projekcije/Edit
	@PostMapping(value="/Edit")
	public void Edit(
			@RequestParam Long id, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datum, 
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime vreme, 
			@RequestParam Long filmID, @RequestParam String tipProjekcije, @RequestParam int sala,
			@RequestParam(name="cena") double cenaKarte, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		if(filmID!=null && filmID<=0) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		Film film = null;
		if(filmID!=null && filmService.findOne(filmID)!=null) {
			film = filmService.findOne(filmID);
		}
		if(film==null) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		
		LocalDateTime datumIVreme = LocalDateTime.of(datum, vreme);
		
		
		Projekcija projekcija = projekcijaService.findOne(id);
		if(projekcija!=null) {
			projekcija.setDatumIVreme(datumIVreme);
			projekcija.setFilm(film);
			projekcija.setTipProjekcije(tipProjekcije);
			projekcija.setSala(sala);
			projekcija.setCenaKarte(cenaKarte);
		}
		Projekcija updated = projekcijaService.update(projekcija);
		
		response.sendRedirect(bURL+"Projekcije");
	}
		
	// POST: Projekcije/Delete
	@SuppressWarnings("unchecked")
	@PostMapping(value="/Delete")
	public void Delete(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik korisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(korisnik==null || korisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		Projekcija deleted = projekcijaService.delete(id);
		response.sendRedirect(bURL+"Projekcije");
	}
}
