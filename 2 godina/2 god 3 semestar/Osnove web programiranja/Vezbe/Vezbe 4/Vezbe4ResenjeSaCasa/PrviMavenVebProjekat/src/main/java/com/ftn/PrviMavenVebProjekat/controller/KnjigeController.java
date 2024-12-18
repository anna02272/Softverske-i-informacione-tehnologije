package com.ftn.PrviMavenVebProjekat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ftn.PrviMavenVebProjekat.bean.SecondConfiguration.ApplicationMemory;
import com.ftn.PrviMavenVebProjekat.model.Knjiga;
import com.ftn.PrviMavenVebProjekat.model.Knjige;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

@Controller
@RequestMapping(value="/knjige")
public class KnjigeController implements ApplicationContextAware {

	public static final String KNJIGE_KEY = "knjige";
	
	@Autowired
	private ServletContext servletContext;
	private  String bURL; 
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private ApplicationMemory memorijaAplikacije;

	/** pristup ApplicationContext */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	    this.applicationContext = applicationContext;    
	}
	
	/** inicijalizacija podataka za kontroler */
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {	
		bURL = servletContext.getContextPath()+"/";
		memorijaAplikacije = applicationContext.getBean(ApplicationMemory.class);
	    Knjige knjige = new Knjige(); 
		
		memorijaAplikacije.put(KnjigeController.KNJIGE_KEY, knjige);
	}
	
	/** pribavnjanje HTML stanice za prikaz svih entiteta, get zahtev 
	 * @throws IOException */
	// GET: knjige
	@GetMapping
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {	
		//preuzimanje vrednosti iz konteksta
		Knjige knjige = (Knjige) memorijaAplikacije.get(KnjigeController.KNJIGE_KEY);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKnjige = new Element(Tag.valueOf("li"), "");
		Element liTagClanskeKarte = new Element(Tag.valueOf("li"), "");
		Element aTagKnjige = new Element(Tag.valueOf("a"), "").attr("href", "knjige").text("Knjige");
		Element aTagClanskeKarte = new Element(Tag.valueOf("a"), "").attr("href", "clanskeKarte").text("Clanske karte");
		liTagKnjige.appendChild(aTagKnjige);
		liTagClanskeKarte.appendChild(aTagClanskeKarte);
		ulTag.appendChild(liTagKnjige);
		ulTag.appendChild(liTagClanskeKarte);
		
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Knjige");
		Element trZaglavlje = new Element(Tag.valueOf("tr"), "");
		Element thRedniBroj = new Element(Tag.valueOf("th"), "").text("R. br.");
		Element thDetails = new Element(Tag.valueOf("th"), "").text("Details");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element thRBPrimerka = new Element(Tag.valueOf("th"), "").text("Registarski broj primerka");
		Element thJezik = new Element(Tag.valueOf("th"), "").text("Jezik");
		Element thBrStranica = new Element(Tag.valueOf("th"), "").text("Broj stranica");
		
		trZaglavlje.appendChild(thRedniBroj);
		trZaglavlje.appendChild(thDetails);
		trZaglavlje.appendChild(thNaziv);
		trZaglavlje.appendChild(thRBPrimerka);
		trZaglavlje.appendChild(thJezik);
		trZaglavlje.appendChild(thBrStranica);
		
		table.appendChild(caption);
		table.appendChild(trZaglavlje);
			
		List<Knjiga> listaKnjiga = knjige.findAll();
		for (int i=0; i < listaKnjiga.size(); i++) {
			if (!listaKnjiga.get(i).isIzdata()) {				
				Element trKnjiga = new Element(Tag.valueOf("tr"), "");
				Element tdRedniBroj = new Element(Tag.valueOf("td"), "").text(String.valueOf(i + 1));
				Element tdDetails = new Element(Tag.valueOf("td"), "");
				Element aDetails = new Element(Tag.valueOf("a"), "").attr("href","knjige/details?id="+listaKnjiga.get(i).getId()).text(listaKnjiga.get(i).getNaziv());
				Element tdNaziv = new Element(Tag.valueOf("td"), "").text(listaKnjiga.get(i).getNaziv());
				Element tdRBPrimerka = new Element(Tag.valueOf("td"), "").text(listaKnjiga.get(i).getRegistarskiBrojPrimerka());
				Element tdJezik = new Element(Tag.valueOf("td"), "").text(listaKnjiga.get(i).getJezik());
				Element tdBrStranica = new Element(Tag.valueOf("td"), "").text(String.valueOf(listaKnjiga.get(i).getBrojStranica()));
				
				tdDetails.appendChild(aDetails);
				trKnjiga.appendChild(tdRedniBroj);
				trKnjiga.appendChild(tdDetails);
				trKnjiga.appendChild(tdNaziv);
				trKnjiga.appendChild(tdRBPrimerka);
				trKnjiga.appendChild(tdJezik);
				trKnjiga.appendChild(tdBrStranica);
				
				Element tdForm = new Element(Tag.valueOf("td"), "");
				Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "knjige/delete");
				Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(listaKnjiga.get(i).getId()));
				Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
				form.appendChild(inputHidden);
				form.appendChild(inputSubmit);
				tdForm.appendChild(form);
				trKnjiga.appendChild(tdForm);
				table.appendChild(trKnjiga);
			}
		}
		
		Element ulTagDodajKnjigu = new Element(Tag.valueOf("ul"), "");
		Element liTagDodajKnjigu = new Element(Tag.valueOf("li"), "");
		Element aTagDodajKnjigu = new Element(Tag.valueOf("a"), "").attr("href", "knjige/add").text("Dodaj knjigu");
		liTagDodajKnjigu.appendChild(aTagDodajKnjigu);
		ulTagDodajKnjigu.appendChild(liTagDodajKnjigu);
		
		body.appendChild(ulTag);
		body.appendChild(table);
		body.appendChild(ulTagDodajKnjigu);
		
		out.write(doc.html());
		return;
	}
	
	/** pribavnjanje HTML stanice za unos novog entiteta, get zahtev 
	 * @throws IOException */
	// GET: knjige/dodaj
	@GetMapping(value="/add")
	@ResponseBody
	public void create(HttpServletResponse response) throws IOException {		
		PrintWriter out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKnjige = new Element(Tag.valueOf("li"), "");
		Element liTagClanskeKarte = new Element(Tag.valueOf("li"), "");
		Element aTagKnjige = new Element(Tag.valueOf("a"), "").attr("href", "/PrviMavenVebProjekat/knjige").text("Knjige");
		Element aTagClanskeKarte = new Element(Tag.valueOf("a"), "").attr("href", "/PrviMavenVebProjekat/clanskeKarte").text("Clanske karte");
		liTagKnjige.appendChild(aTagKnjige);
		liTagClanskeKarte.appendChild(aTagClanskeKarte);
		ulTag.appendChild(liTagKnjige);
		ulTag.appendChild(liTagClanskeKarte);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "add");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Knjiga");
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv");
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trRBPrimerka = new Element(Tag.valueOf("tr"), "");
		Element thRBPrimerka = new Element(Tag.valueOf("th"), "").text("Redni broj primerka");
		Element tdRBPrimerka = new Element(Tag.valueOf("td"), "");
		Element inputRBPrimerka = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "registarskiBrojPrimerka");
		
		tdRBPrimerka.appendChild(inputRBPrimerka);
		trRBPrimerka.appendChild(thRBPrimerka);
		trRBPrimerka.appendChild(tdRBPrimerka);
		
		Element trJezik = new Element(Tag.valueOf("tr"), "");
		Element thJezik = new Element(Tag.valueOf("th"), "").text("Jezik");
		Element tdJezik = new Element(Tag.valueOf("td"), "");
		Element inputJezik = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "jezik");
		
		tdJezik.appendChild(inputJezik);
		trJezik.appendChild(thJezik);
		trJezik.appendChild(tdJezik);
		
		Element trBrStranica = new Element(Tag.valueOf("tr"), "");
		Element thBrStranica = new Element(Tag.valueOf("th"), "").text("Broj stranica");
		Element tdBrStranica = new Element(Tag.valueOf("td"), "");
		Element inputBrStranica = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "brojStranica").attr("min", "1");
		
		tdBrStranica.appendChild(inputBrStranica);
		trBrStranica.appendChild(thBrStranica);
		trBrStranica.appendChild(tdBrStranica);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Dodaj");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trRBPrimerka);
		table.appendChild(trJezik);
		table.appendChild(trBrStranica);
		table.appendChild(trSubmit);
		
		form.appendChild(table);
		
		body.appendChild(ulTag);
		body.appendChild(form);
		
		out.write(doc.html());
		return;
	}
	
	/** obrada podataka forme za unos novog entiteta, post zahtev */
	// POST: knjige/add
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String registarskiBrojPrimerka, 
			@RequestParam String jezik, @RequestParam int brojStranica, HttpServletResponse response) throws IOException {		
		//preuzimanje vrednosti iz konteksta
		Knjige knjige = (Knjige) memorijaAplikacije.get(KnjigeController.KNJIGE_KEY);
			
		Knjiga knjiga = new Knjiga(naziv, registarskiBrojPrimerka, jezik, brojStranica);
		Knjiga saved = knjige.save(knjiga);
		response.sendRedirect(bURL+"knjige");
	}
	
	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	// POST: knjige/edit
	@PostMapping(value="/edit")
	public void Edit(@ModelAttribute Knjiga knjigaEdited , HttpServletResponse response) throws IOException {	
		//preuzimanje vrednosti iz konteksta
		Knjige knjige = (Knjige) memorijaAplikacije.get(KnjigeController.KNJIGE_KEY);
		
		Knjiga knjiga = knjige.findOne(knjigaEdited.getId());
		if(knjiga != null) {
			if(knjigaEdited.getNaziv() != null && !knjigaEdited.getNaziv().trim().equals(""))
				knjiga.setNaziv(knjigaEdited.getNaziv());
			if(knjigaEdited.getJezik() != null && !knjigaEdited.getJezik().trim().equals(""))
				knjiga.setJezik(knjigaEdited.getJezik());
			if(knjigaEdited.getBrojStranica() > 0)
				knjiga.setBrojStranica(knjigaEdited.getBrojStranica());
		}
		Knjiga saved = knjige.save(knjiga);
		response.sendRedirect(bURL+"knjige");
	}
	
	/** obrada podataka forme za za brisanje postojećeg entiteta, post zahtev */
	// POST: knjige/delete
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		//preuzimanje vrednosti iz konteksta
		Knjige knjige = (Knjige) memorijaAplikacije.get(KnjigeController.KNJIGE_KEY);
		
		Knjiga deleted = knjige.delete(id);
		response.sendRedirect(bURL+"knjige");
	}
	
	/** pribavnjanje HTML stanice za prikaz određenog entiteta , get zahtev 
	 * @throws IOException */
	// GET: knjige/details?id=1
	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam Long id, HttpServletResponse response) throws IOException {	
		//preuzimanje vrednosti iz konteksta
		Knjige knjige = (Knjige) memorijaAplikacije.get(KnjigeController.KNJIGE_KEY);
	
		Knjiga knjiga = knjige.findOne(id);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKnjige = new Element(Tag.valueOf("li"), "");
		Element liTagClanskeKarte = new Element(Tag.valueOf("li"), "");
		Element aTagKnjige = new Element(Tag.valueOf("a"), "").attr("href", "/PrviMavenVebProjekat/knjige").text("Knjige");
		Element aTagClanskeKarte = new Element(Tag.valueOf("a"), "").attr("href", "/PrviMavenVebProjekat/clanskeKarte").text("Clanske karte");
		liTagKnjige.appendChild(aTagKnjige);
		liTagClanskeKarte.appendChild(aTagClanskeKarte);
		ulTag.appendChild(liTagKnjige);
		ulTag.appendChild(liTagClanskeKarte);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "edit");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Knjiga");
		
		Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(knjiga.getId()));
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv").attr("value", knjiga.getNaziv());
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trRBPrimerka = new Element(Tag.valueOf("tr"), "");
		Element thRBPrimerka = new Element(Tag.valueOf("th"), "").text("Redni broj primerka");
		Element tdRBPrimerka = new Element(Tag.valueOf("td"), "");
		Element inputRBPrimerka = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "registarskiBrojPrimerka").attr("value", knjiga.getRegistarskiBrojPrimerka());
		
		tdRBPrimerka.appendChild(inputRBPrimerka);
		trRBPrimerka.appendChild(thRBPrimerka);
		trRBPrimerka.appendChild(tdRBPrimerka);
		
		Element trJezik = new Element(Tag.valueOf("tr"), "");
		Element thJezik = new Element(Tag.valueOf("th"), "").text("Jezik");
		Element tdJezik = new Element(Tag.valueOf("td"), "");
		Element inputJezik = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "jezik").attr("value", knjiga.getJezik());
		
		tdJezik.appendChild(inputJezik);
		trJezik.appendChild(thJezik);
		trJezik.appendChild(tdJezik);
		
		Element trBrStranica = new Element(Tag.valueOf("tr"), "");
		Element thBrStranica = new Element(Tag.valueOf("th"), "").text("Broj stranica");
		Element tdBrStranica = new Element(Tag.valueOf("td"), "");
		Element inputBrStranica = new Element(Tag.valueOf("input"), "").attr("type", "number").attr("name", "brojStranica").attr("min", "1").attr("value", String.valueOf(knjiga.getBrojStranica()));
		
		tdBrStranica.appendChild(inputBrStranica);
		trBrStranica.appendChild(thBrStranica);
		trBrStranica.appendChild(tdBrStranica);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Izmeni");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trRBPrimerka);
		table.appendChild(trJezik);
		table.appendChild(trBrStranica);
		table.appendChild(trSubmit);

		form.appendChild(inputHidden);
		form.appendChild(table);
		
		Element formBrisanje = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "delete");
		Element inputSubmitBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
		Element inputHiddenBrisanje = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "id").attr("value", String.valueOf(knjiga.getId()));;
		formBrisanje.appendChild(inputHiddenBrisanje);
		formBrisanje.appendChild(inputSubmitBrisanje);
		

		if (!knjiga.isIzdata()) {
			Element formIzdajKnjigu = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "clanskeKarte/izdajKnjigu");
			
			Element inputHiddenIzdajKnjigu = new Element(Tag.valueOf("input"), "").attr("type", "hidden").attr("name", "idKnjige").attr("value", String.valueOf(knjiga.getId()));
			
			Element tableIzdajKnjigu = new Element(Tag.valueOf("table"), "");
			Element captionIzdajKnjigu = new Element(Tag.valueOf("caption"), "").text("Izdaj primerak knjige");
			
			Element trBrClanskeKarte = new Element(Tag.valueOf("tr"), "");
			Element thBrClanskeKarte = new Element(Tag.valueOf("th"), "").text("Registarski broj clanske karte");
			Element tdBrClanskeKarte = new Element(Tag.valueOf("td"), "");
			Element inputBrClanskeKarte = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "registarskiBroj");
			
			tdBrClanskeKarte.appendChild(inputBrClanskeKarte);
			trBrClanskeKarte.appendChild(thBrClanskeKarte);
			trBrClanskeKarte.appendChild(tdBrClanskeKarte);
			
			Element inputSubmitIzdajKnjigu = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Izdaj");
			
			tableIzdajKnjigu.appendChild(captionIzdajKnjigu);
			tableIzdajKnjigu.appendChild(trBrClanskeKarte);
			tableIzdajKnjigu.appendChild(inputSubmitIzdajKnjigu);
			
			formIzdajKnjigu.appendChild(inputHiddenIzdajKnjigu);
			formIzdajKnjigu.appendChild(tableIzdajKnjigu);
		}
		
		body.appendChild(ulTag);
		body.appendChild(form);
		body.appendChild(formBrisanje);
		
		out.write(doc.html());
		return;		
	}
}