package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;

import com.ftn.PrviMavenVebProjekat.bean.SecondConfiguration.MemorijaAplikacije;
import com.ftn.PrviMavenVebProjekat.model.Film;
import com.ftn.PrviMavenVebProjekat.model.FilmBrojac;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Filmovi;

@Controller
@RequestMapping(value="/Filmovi")
public class FilmoviController /* implements */ /*ServletContextAware , */ /* ApplicationContextAware*/ {

	public static final String FILMOVI_KEY = "filmovi";
//	public static final String STATISTIKA_FILMOVA_KEY = "StatistikaFilmova";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
//	@Autowired
//	private ApplicationContext applicationContext;
	
	@Autowired
	private MemorijaAplikacije memorijaAplikacije;
	
//	/** pristup ServletContext */
//	@Override
//	public void setServletContext(ServletContext servletContext) {
//		this.servletContext = servletContext;
//	} 
	
//	/** pristup ApplicationContext */
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//	    this.applicationContext = applicationContext;    
//	}
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
	    
//		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";
		
		//WebApplicationContext je klasa naslednica ApplicationContext
//	    if (applicationContext instanceof WebApplicationContext) {
//	    	servletContext = ((WebApplicationContext) applicationContext).getServletContext();
////			//Specify the base URL for all relative URLs in a document
//			bURL = servletContext.getContextPath()+"/";
//	    }    
//	    System.out.println(bURL);
//	    bURL = applicationContext.getEnvironment().getProperty("server.servlet.contextPath")+"/";
//	    System.out.println(bURL);
		
//		memorijaAplikacije = applicationContext.getBean(MemorijaAplikacije.class);
//	    memorijaAplikacije = (MemorijaAplikacije) applicationContext.getBean("memorijaAplikacije");
	    
	    
		Filmovi filmovi = new Filmovi();
//		FilmStatistika statistikaFilmova = new FilmStatistika();
		
//		servletContext.setAttribute(FilmoviController.FILMOVI_KEY,filmovi);	
//		servletContext.setAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY,statistikaFilmova);
		
		memorijaAplikacije.put(FilmoviController.FILMOVI_KEY,filmovi);
//		memorijaAplikacije.put(FilmoviController.STATISTIKA_FILMOVA_KEY,statistikaFilmova);
	}
	
	/** pribavnjanje HTML stanice za prikaz svih entiteta, get zahtev */
	// GET: Filmovi
	@GetMapping
	@ResponseBody
	public String index() {	
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
		
//		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
				"	<title>Filmovi</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body> "+
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Filmovi</caption>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>r. br.</th>\r\n" + 
				"				<th>naziv</th>\r\n" + 
				"				<th>trajanje</th>\r\n" + 
				"				<th>projekcije</th>\r\n" +
				"			</tr>\r\n");
		
		List<Film> films = filmovi.findAll();
		for (int i=0; i < films.size(); i++) {
			retVal.append(
				"			<tr>\r\n" + 
				"				<td class=\"broj\">"+ (i+1) +"</td>\r\n" + 
				"				<td><a href=\"Filmovi/Details?id="+films.get(i).getId()+"\">" +films.get(i).getNaziv() +"</a></td>\r\n" +
				"				<td class=\"broj\">"+ films.get(i).getTrajanje() +"</td>\r\n" +
				"				<td>\r\n"+				
				"					<a href=\"Projekcije?filmID="+films.get(i).getId()+"\">projekcije</a>\r\n" + 
				"					<form action=\"Projekcije\" method=\"get\">\r\n" + 
				"						<input type=\"hidden\" name=\"filmID\" value=\""+films.get(i).getId()+"\"/>\r\n" + 
				"						<input type=\"submit\" value=\"Projekcije\"/>\r\n" + 
				"					</form>\r\n" + 
				"				</td>\r\n"+
				"			</tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n");
		retVal.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi/Create\">dodavanje filma</a></li>\r\n" + 
				"	</ul>\r\n");
		
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** pribavnjanje HTML stanice za prikaz određenog entiteta , get zahtev */
	// GET: Filmovi/Details?id=1
	@GetMapping(value="/Details")
	@ResponseBody
	public String details(@RequestParam Long id) {	
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
		
//		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
		
		Film film = filmovi.findOne(id);
//		if(film!=null)
//			statistikaFilmova.incrementBrojac(film);
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
				"	<title>Film</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 				
				"	<form method=\"post\" action=\"Filmovi/Edit\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+film.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Film</caption>\r\n" + 
				"			<tr><th>naziv:</th><td><input type=\"text\" "+
				 				"value=\""+film.getNaziv()+"\" name=\"naziv\"/></td></tr>\r\n" + 
				"			<tr><th>trajanje:</th><td><input type=\"number\" min=\"5\" "+
				 				"value=\""+film.getTrajanje()+"\" name=\"trajanje\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Izmeni\" /></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<br/>\r\n" + 
				"	<form method=\"post\" action=\"Filmovi/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+film.getId()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n");
//		if(!statistikaFilmova.isEmpty()) {
//			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
//			
//			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
//			
//			retVal.append(
//				"	<table class=\"horizontalni-meni\">\r\n" +		
//				"		<caption>Popularni filmovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n");
//			for (int i=0; i < popularniFilmovi.size(); i++) {
//				retVal.append(
//				"					<li>\n\r"+
//				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
//													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
//				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
//												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
//				"					</li>\r\n");
//			}
//			retVal.append(
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n");			
//		}
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** pribavnjanje HTML stanice za unos novog entiteta, get zahtev */
	// GET: Filmovi/Create
	@GetMapping(value="/Create")
	@ResponseBody
	public String create() {
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
		
//		FilmStatistika statistikaFilmova = (FilmStatistika)servletContext.getAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY);
//		FilmStatistika statistikaFilmova = (FilmStatistika)memorijaAplikacije.get(FilmoviController.STATISTIKA_FILMOVA_KEY);
				
		StringBuilder retVal = new StringBuilder();
		retVal.append(
				"<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"<head>\r\n" + 
				"	<meta charset=\"UTF-8\">\r\n" + 
	    		"	<base href=\""+bURL+"\">" + 
				"	<title>Film kreiranje</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"	</ul>\r\n" + 
				"	<form method=\"post\" action=\"Filmovi/Create\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Film</caption>\r\n" + 
				"			<tr><th>naziv:</th><td><input type=\"text\" value=\"\" name=\"naziv\"/></td></tr>\r\n" + 
				"			<tr><th>trajanje:</th><td><input type=\"number\" min=\"5\" "+
				 					"value=\"5\" name=\"trajanje\"/></td></tr>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Dodaj\" /></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n" +
				"	<br/>\r\n");
//		if(!statistikaFilmova.isEmpty()) {
//			List<FilmBrojac> popularniFilmovi = statistikaFilmova.getFilmovi();
//			
//			int najvisePopularan = popularniFilmovi.get(0).getBrojac();
//			
//			retVal.append(
//				"	<table class=\"horizontalni-meni\">\r\n" +		
//				"		<caption>Popularni filmovi</caption>\r\n" + 
//				"		<tr>\r\n" + 
//				"			<td>\r\n" + 
//				"				<ul>\r\n");
//			for (int i=0; i < popularniFilmovi.size(); i++) {
//				retVal.append(
//				"					<li>\n\r"+
//				"						<a href=\"Filmovi/Details?id="+popularniFilmovi.get(i).getFilm().getId()+"\">"
//													+popularniFilmovi.get(i).getFilm().getNaziv()+ "</a><br/>\r\n"+
//				"						<progress value=\""+popularniFilmovi.get(i).getBrojac()+"\" "
//												+ "max=\""+najvisePopularan+"\"></progress>"+popularniFilmovi.get(i).getBrojac()+"\r\n"+
//				"					</li>\r\n");
//			}
//			retVal.append(
//				"				</ul>\r\n" + 
//				"			</td>\r\n" + 
//				"		</tr>\r\n" + 
//				"	</table>\r\n");			
//		}
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** obrada podataka forme za unos novog entiteta, post zahtev */
	// POST: Filmovi/Create
	@PostMapping(value="/Create")
	public void create(@RequestParam String naziv, @RequestParam int trajanje, HttpServletResponse response) throws IOException {		
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
			
		Film film = new Film(naziv, trajanje);
		Film saved = filmovi.save(film);
		response.sendRedirect(bURL+"Filmovi");
	}

	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	// POST: Filmovi/Edit
	@PostMapping(value="/Edit")
	public void Edit(@ModelAttribute Film filmEdited , HttpServletResponse response) throws IOException {	
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
		
		Film film = filmovi.findOne(filmEdited.getId());
		if(film!=null) {
			if(filmEdited.getNaziv()!=null && !filmEdited.getNaziv().trim().equals(""))
				film.setNaziv(filmEdited.getNaziv());
			if(filmEdited.getTrajanje() > 0)
				film.setTrajanje(filmEdited.getTrajanje());
		}
		Film saved = filmovi.save(film);
		response.sendRedirect(bURL+"Filmovi");
	}
	
	/** obrada podataka forme za za brisanje postojećeg entiteta, post zahtev */
	// POST: Filmovi/Delete
	@PostMapping(value="/Delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		//preuzimanje vrednosti iz konteksta
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
		Filmovi filmovi = (Filmovi)memorijaAplikacije.get(FilmoviController.FILMOVI_KEY);
		
		Film deleted = filmovi.delete(id);
		response.sendRedirect(bURL+"Filmovi");
	}
}
