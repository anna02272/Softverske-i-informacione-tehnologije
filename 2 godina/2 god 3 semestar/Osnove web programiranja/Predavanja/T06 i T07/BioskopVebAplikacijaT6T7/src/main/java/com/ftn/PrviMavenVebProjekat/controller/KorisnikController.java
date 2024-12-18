package com.ftn.PrviMavenVebProjekat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;
import com.ftn.PrviMavenVebProjekat.service.KorisnikService;

@Controller
@RequestMapping(value="/Korisnici")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	/** inicijalizacija podataka za kontroler */
	@PostConstruct
	public void init() {	
		//Specify the base URL for all relative URLs in a document
		bURL = servletContext.getContextPath()+"/";			
	}
	
	/** pribavljanje HTML stanice za prikaz svih entiteta, get zahtev */
	// GET: Korisnici
	@GetMapping
	@ResponseBody
	public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {	
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik prijavljeniKorisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(prijavljeniKorisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return "";
		}
		if(prijavljeniKorisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"Filmovi");
			return "";
		}
		
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
				"	<title>Korisnici</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviTabela.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body> "+
				"	<div> Prijavljen je "+ prijavljeniKorisnik.getIme() +" "+ prijavljeniKorisnik.getPrezime()+" <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n" +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n" +
				"	</ul>\r\n" + 
				"		<table>\r\n" + 
				"			<caption>Korisnici</caption>\r\n" + 
				"			<tr>\r\n" + 
				"				<th>r. br.</th>\r\n" +
				"				<th>ime</th>\r\n" + 
				"				<th>prezime</th>\r\n" + 
				"				<th>korisničko ime</th>\r\n" + 
				"			</tr>\r\n");
		
		List<Korisnik> users = new ArrayList<Korisnik>(korisnikService.findAll());
		for (int i=0; i < users.size(); i++) {
			retVal.append(
				"			<tr>\r\n" + 
				"				<td class=\"broj\">"+ (i+1) +"</td>\r\n" +
				"				<td>"+ users.get(i).getIme() +"</td>\r\n" +		
				"				<td>"+ users.get(i).getPrezime() +"</td>\r\n" +
				"				<td><a href=\"Korisnici/Details?korisnickoIme="+
									users.get(i).getKorisnickoIme()+"\">" +users.get(i).getKorisnickoIme() +"</a></td>\r\n" +
				"			</tr>\r\n");
		}
		retVal.append(
				"		</table>\r\n");
		retVal.append(
				"	<ul>\r\n" + 
				"		<li><a href=\"Korisnici/Create\">registracija korisnika</a></li>\r\n" + 
				"	</ul>\r\n");
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
	}
	
	/** pribavljanje HTML stanice za prikaz određenog entiteta , get zahtev */
	// GET: Korisnici/Details?korisnickoIme=pera
	@GetMapping(value="/Details")
	@ResponseBody
	public String details(@RequestParam String korisnickoIme, HttpServletRequest request, HttpServletResponse response) throws IOException {		
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik prijavljeniKorisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(prijavljeniKorisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return "";
		}
		//zastita da obican korisnik ne može videti detalje od drugog korisnika
		if(!prijavljeniKorisnik.getKorisnickoIme().equals(korisnickoIme) && prijavljeniKorisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"Filmovi");
			return "";
		}
		
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);
		//ako ne postoji korisnik za pregled prikazi stanicu za registraciju korisnika
		if(korisnik==null) {
			korisnik = new Korisnik();
			StringBuilder retVal = new StringBuilder();
			retVal.append(vratiHTMLZaKorisnika("Create", null, korisnik, korisnik.getKorisnickaSifra(), "Korisnik", "Korisnik", "Korisnici/Create", null));	
			return retVal.toString();
		}
		
		StringBuilder retVal = new StringBuilder();
		retVal.append(vratiHTMLZaKorisnika("Details", null, korisnik, korisnik.getKorisnickaSifra(), "Korisnik", "Korisnik", "Korisnici/Edit", prijavljeniKorisnik));	
		return retVal.toString();
	}
	
	/** pribavljanje HTML stanice za unos novog entiteta, get zahtev */
	// GET: Korisnici/Create
	@GetMapping(value="/Create")
	@ResponseBody
	public String create() {
		
		Korisnik korisnik = new Korisnik();
		StringBuilder retVal = new StringBuilder();
		retVal.append(vratiHTMLZaKorisnika("Create", null, korisnik, korisnik.getKorisnickaSifra(), "Korisnik", "Korisnik", "Korisnici/Create", null));	
		return retVal.toString();
	}
	
	/** obrada podataka forme za unos novog entiteta, post zahtev  */
	@SuppressWarnings("unchecked")
	// POST: Korisnici/Create
	@PostMapping(value="/Create")
	public void create(@ModelAttribute Korisnik korisnik, @RequestParam(defaultValue="") String ponovljenaSifra, 
			BindingResult result, HttpServletResponse response) throws IOException {
		
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		String greska = "";
		if (result.hasErrors()) {
			greska = "greška prilikom preuzimanja podataka<br/>";
        }
		if(korisnik.getKorisnickoIme().trim().equals(""))
			greska+="korisničko ime nije uneseno<br/>";
		if(korisnikService.findOne(korisnik.getKorisnickoIme())!=null)
			greska+="korisničko ime nije jedinstveno<br/>";
		if(korisnik.getKorisnickaSifra().trim().equals(""))
			greska+="korisnička šifra nije unesena<br/>";
		if(!korisnik.getKorisnickaSifra().equals(ponovljenaSifra))
			greska+="korisničke šifre se ne podudaraju<br/>";
		if(korisnik.getPlata()<0)
			greska+="korisnička plata je ne negativna vrednost<br/>";
		if(!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;	
			out = response.getWriter();
			out.write(vratiHTMLZaKorisnika("Create", greska, korisnik, ponovljenaSifra, "Korisnik", "Korisnik", "Korisnici/Create", null));
			return;
		}
		
		korisnikService.save(korisnik);
		response.sendRedirect(bURL+"Korisnici");
	}
	 
	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	@SuppressWarnings("unchecked")
	// POST: Korisnici/Edit
	@PostMapping(value="/Edit")
	public void edit(@ModelAttribute Korisnik korisnikEdited, @RequestParam String ponovljenaSifra, 
			BindingResult result, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik prijavljeniKorisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(prijavljeniKorisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		
		if(!prijavljeniKorisnik.getKorisnickoIme().equals(korisnikEdited.getKorisnickoIme()) && prijavljeniKorisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		
		String greska = "";
		if (result.hasErrors() || korisnikEdited==null) {
			greska = "greška prilikom preuzimanja podataka<br/>";
        }
		if(korisnikService.findOne(korisnikEdited.getKorisnickoIme())==null)
			greska+="korisnik ne postoji u evidenciji<br/>";
		if(!korisnikEdited.getKorisnickaSifra().equals(ponovljenaSifra))
			greska+="korisničke šifre se ne podudaraju<br/>";
		if(korisnikEdited.getPlata()<0)
			greska+="korisnička plata je ne negativna vrednost<br/>";
		if(!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;	
			out = response.getWriter();
			out.write(vratiHTMLZaKorisnika("Details", greska, korisnikEdited, ponovljenaSifra, "Korisnik", "Korisnik", "Korisnici/Edit", prijavljeniKorisnik));
			return;
		}
		
		//zasto se korisnik preuzima iz repozitorijuma, a mu se menjaju podaci
		//zasto se preuzeti korsnik iz forme direktno ne sačuva u repoyitorijuma
		Korisnik korisnik = korisnikService.findOne(korisnikEdited.getKorisnickoIme());
		if(korisnik!=null && korisnik.getKorisnickaSifra().equals(ponovljenaSifra)) {
			if(korisnikEdited.getIme()!=null && !korisnikEdited.getIme().trim().equals(""))
				korisnik.setIme(korisnikEdited.getIme());
			if(korisnikEdited.getPrezime()!=null && !korisnikEdited.getPrezime().trim().equals(""))
				korisnik.setPrezime(korisnikEdited.getPrezime());
			if(korisnikEdited.getPol()!=null && !korisnikEdited.getPol().trim().equals(""))
				korisnik.setPol(korisnikEdited.getPol());
			if(korisnikEdited.getPlata() > 0)
				korisnik.setPlata(korisnikEdited.getPlata());
			if(korisnikEdited.getKorisnickaSifra()!=null && !korisnikEdited.getKorisnickaSifra().trim().equals(""))
				korisnik.setKorisnickaSifra(korisnikEdited.getKorisnickaSifra());
		}
		Korisnik updated = korisnikService.update(korisnik);
		response.sendRedirect(bURL+"Korisnici");
	}
	
	/** obrada podataka forme za brisanje postojećeg entiteta, post zahtev */
	@SuppressWarnings("unchecked")
	// POST: Korisnici/Delete
	@PostMapping(value="/Delete")
	public void delete(@RequestParam String korisnickoIme, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		response.setHeader("Cache-Control", "private, no-cache, no-store, must-revalidate"); // HTTP 1.1
//		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//		response.setDateHeader("Expires", 0); // Proxies.
		
		//preuzimanje vrednosti iz sesije za klijenta
		Korisnik prijavljeniKorisnik = (Korisnik) request.getSession().getAttribute(PrijavaOdjavaController.KORISNIK_KEY);
		if(prijavljeniKorisnik==null) {
			response.sendRedirect(bURL+"login.html");
			return;
		}
		if(prijavljeniKorisnik.isAdministrator()==false) {
			response.sendRedirect(bURL+"Filmovi");
			return;
		}
		
		Korisnik korisnik = korisnikService.findOne(korisnickoIme);	
		String greska = "";
		if(korisnik!=null && korisnik.isAdministrator()==true)
			greska+="korisnik se ne može obristi jer je administrator<br/>";
		if(!greska.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;	
			out = response.getWriter();
			out.write(vratiHTMLZaKorisnika("Details", greska, korisnik, korisnik.getKorisnickaSifra(), "Korisnik", "Korisnik", "Korisnici/Edit", prijavljeniKorisnik));
			return;
		}
		
		Korisnik deleted = korisnikService.delete(korisnickoIme);
		response.sendRedirect(bURL+"Korisnici");
	}
	
	private String vratiHTMLZaKorisnika(String rezimPrikaza, String greska, Korisnik korisnik, String ponovljenaSifra, 
			String title, String caption, String formURL, Korisnik prijavljeniKorisnik) {
		String dugme = "";
		if(rezimPrikaza.equals("Create"))
			dugme="Dodaj";
		else if (rezimPrikaza.equals("Details"))
			dugme="Izmeni";
		
		String prijavljeniTekst = "";
		if(prijavljeniKorisnik!=null) {
			prijavljeniTekst = "	<div> Prijavljen je "+ korisnik.getIme() +" "+ korisnik.getPrezime()+" <a href=\"PrijavaOdjava/Logout\">Odjavi se</a></li></div>\r\n";
					
		}
		
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
				"	<title>"+title+"</title>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviForma.css\"/>\r\n" + 
				"	<link rel=\"stylesheet\" type=\"text/css\" href=\"css/StiloviHorizontalniMeni.css\"/>\r\n"+
				"</head>\r\n" + 
				"<body>\r\n" + 
				prijavljeniTekst +
				"	<ul>\r\n" + 
				"		<li><a href=\"Filmovi\">filmovi</a></li>\r\n" + 
				"		<li><a href=\"Projekcije\">projekcije</a></li>\r\n" + 
				"		<li><a href=\"Korisnici\">korisnici</a></li>\r\n" +
				"	</ul>\r\n");
		if(greska!=null)
			retVal.append(
				"	<div>"+greska+"</div>\r\n");		
		retVal.append(		
				"	<form method=\"post\" action=\""+formURL+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<caption>"+caption+"</caption>\r\n" + 
				"			<tr><th>ime:</th><td><input type=\"text\" "+
 								"value=\""+korisnik.getIme()+"\" name=\"ime\"/></td></tr>\r\n" +
 				"			<tr><th>prezime:</th><td><input type=\"text\" "+
 								"value=\""+korisnik.getPrezime()+"\" name=\"prezime\"/></td></tr>\r\n" +
 				"			<tr><th>pol:</th><td><input type=\"radio\" name=\"pol\" value=\"muški\" "+ (korisnik.getPol().equals("muški")?"checked":"") +"/>M " + 
 							"<input type=\"radio\" name=\"pol\" value=\"ženski\" "+ (korisnik.getPol().equals("ženski")?"checked":"") +"/>Ž</td></tr>\r\n" +
 				"			<tr><th>plata:</th><td><input type=\"number\" min=\"0\" step=any "+
								"value=\""+korisnik.getPlata()+"\" name=\"plata\"/></td></tr>\r\n" + 				
				"			<tr><th>korisničko ime:</th><td><input type=\"text\" "+
 								"value=\""+korisnik.getKorisnickoIme()+"\" name=\"korisnickoIme\" "+(rezimPrikaza.equals("Details")?"readonly":"")+ "/></td></tr>\r\n" +
 				"			<tr><th>korisničko šifra:</th><td><input type=\"password\" "+
 								"value=\""+korisnik.getKorisnickaSifra()+"\" name=\"korisnickaSifra\"/></td></tr>\r\n" +
 				"			<tr><th>ponovljena šifra:</th><td><input type=\"password\" "+
 								"value=\""+ponovljenaSifra+"\" name=\"ponovljenaSifra\"/></td></tr>\r\n");
		retVal.append(  
 				"			<tr><th></th><td><input type=\"submit\" value=\""+dugme+"\" /></td></tr>\r\n");
		
		retVal.append(		
				"		</table>\r\n" + 
				"	</form>\r\n" + 
				"	<br/>\r\n");
		
		if (rezimPrikaza.equals("Details") && prijavljeniKorisnik!=null && prijavljeniKorisnik.isAdministrator())
			retVal.append(
				"	<form method=\"post\" action=\"Korisnici/Delete\">\r\n" + 
				"		<input type=\"hidden\" name=\"korisnickoIme\" value=\""+korisnik.getKorisnickoIme()+"\">\r\n" + 
				"		<table>\r\n" + 
				"			<tr><th></th><td><input type=\"submit\" value=\"Obriši\"></td>\r\n" + 
				"		</table>\r\n" + 
				"	</form>\r\n");
		
		retVal.append(
				"</body>\r\n"+
				"</html>\r\n");		
		return retVal.toString();
		
	}
}
