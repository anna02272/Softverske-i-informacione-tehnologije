package com.ftn.PrviMavenVebProjekat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ftn.PrviMavenVebProjekat.model.KategorijaLeka;
import com.ftn.PrviMavenVebProjekat.model.KategorijeLekova;
import com.ftn.PrviMavenVebProjekat.model.Knjiga;
import com.ftn.PrviMavenVebProjekat.model.Knjige;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;


@Controller
@RequestMapping(value="/kategorijeLeka")
public class KategorijeController implements ApplicationContextAware {

	public static final String KATEGORIJE_KEY = "kategorije";
	
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
	    KategorijeLekova kategorijeLekova = new KategorijeLekova(); 
	    		
		memorijaAplikacije.put(KategorijeController.KATEGORIJE_KEY, kategorijeLekova);
	}
	
	
	@GetMapping 
	@ResponseBody
	public void index(HttpServletResponse response) throws IOException {	
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		
		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorije");
		Element trZaglavlje = new Element(Tag.valueOf("tr"), "");
		Element thRedniBroj = new Element(Tag.valueOf("th"), "").text("R. br.");
		Element thDetails = new Element(Tag.valueOf("th"), "").text("Details");
		
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		
		trZaglavlje.appendChild(thRedniBroj);
		trZaglavlje.appendChild(thDetails);
		trZaglavlje.appendChild(thNaziv);
		trZaglavlje.appendChild(thNamena);
		trZaglavlje.appendChild(thOpis);
		
		table.appendChild(caption);
		table.appendChild(trZaglavlje);
		
		List<KategorijaLeka> listaKategorija = kategorijeLekova.findAll();
		for(int i=0; i < listaKategorija.size(); i++) {
			Element trKategorija = new Element(Tag.valueOf("tr"), "");
			Element tdRedniBroj = new Element(Tag.valueOf("td"), "").text(String.valueOf(i+1));
			Element tdDetails = new Element(Tag.valueOf("td"), "");
			Element aDetails = new Element(Tag.valueOf("a"), "").attr("href", "kategorijeLekova/details?id=" +
			listaKategorija.get(i).getId()).text(listaKategorija.get(i).getNaziv());
			Element tdNaziv = new Element(Tag.valueOf("td"), "").text(listaKategorija.get(i).getNaziv());
			Element tdNamena = new Element (Tag.valueOf("td"), "").text(listaKategorija.get(i).getNamena());
			Element tdOpis = new Element(Tag.valueOf("td"), "").text(listaKategorija.get(i).getOpis());
			
			tdDetails.appendChild(aDetails);
			
			trKategorija.appendChild(tdRedniBroj);
			trKategorija.appendChild(tdDetails);
		
			trKategorija.appendChild(tdNaziv);
			trKategorija.appendChild(tdNamena);
			trKategorija.appendChild(tdOpis);
			
			Element tdForm = new Element(Tag.valueOf("td"), "");
			Element form = new Element(Tag.valueOf("form"),"").attr("method", "post").attr("action","kategorijeLeka/delete");
			Element inputHidden = new Element(Tag.valueOf("input"), "").attr("type", "hidden")
					.attr("name", "id").attr("value", String.valueOf(listaKategorija.get(i).getId()));
			Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Obrisi");
			
			form.appendChild(inputHidden);
			form.appendChild(inputSubmit);
			tdForm.appendChild(form);
			trKategorija.appendChild(tdForm);
			table.appendChild(trKategorija);
			
		} 
	
		Element ulDodajKategoriju = new Element(Tag.valueOf("ul"),"");
		Element liDodajKategoriju = new Element(Tag.valueOf("li"),"");
		Element aDodajKategoriju = new Element(Tag.valueOf("a"), "")
				.attr("href",  "kategorijeLekova/add").text("Dodaj kategoriju leka");
		
		liDodajKategoriju.appendChild(aDodajKategoriju);
		ulDodajKategoriju.appendChild(liDodajKategoriju);
		
		body.appendChild(table);
		body.appendChild(ulDodajKategoriju);
		
		
		out.write(doc.html());
		return;
	}
	
	
	@GetMapping(value="/add")
	@ResponseBody
	public void create(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKategorije = new Element(Tag.valueOf("li"),"");
		Element aTagKategorije = new Element(Tag.valueOf("a"), "").attr("href", "PrviVebMavenProjekat/kategorijeLeka").text("Kategorije Knjige");
		
		liTagKategorije.appendChild(aTagKategorije);
		ulTag.appendChild(liTagKategorije);
		
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "add");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorije Leka");
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv");
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trNamena = new Element(Tag.valueOf("tr"), "");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element tdNamena = new Element(Tag.valueOf("td"), "");
		Element inputNamena = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "namena");
		
		tdNamena.appendChild(inputNamena);
		trNamena.appendChild(thNamena);
		trNamena.appendChild(tdNamena);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis");
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Dodaj");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trNamena);
		table.appendChild(trOpis);
		table.appendChild(trSubmit);
		
		form.appendChild(table);
		
		body.appendChild(ulTag);
		body.appendChild(form);
		
		out.write(doc.html());
		return;
	}
		
	
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String namena, 
			@RequestParam String opis, HttpServletResponse response) throws IOException {		
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		
		KategorijaLeka kategorijaLeka = new KategorijaLeka(naziv, namena, opis);
		
		
		KategorijaLeka saved = kategorijeLekova.save(kategorijaLeka);
		response.sendRedirect(bURL+"kategorijeLeka");
	}
	
	@PostMapping(value="/edit")
	public void edit(@ModelAttribute KategorijaLeka kategorijeLekovaEdited , HttpServletResponse response) throws IOException {	
	KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		
		KategorijaLeka kategorijaLeka = kategorijeLekova.findOne(kategorijeLekovaEdited.getId());
		if(kategorijaLeka != null) {
			
			if(kategorijeLekovaEdited.getNaziv() != null && !kategorijeLekovaEdited.getNaziv().trim().equals(""))
				kategorijaLeka.setNaziv(kategorijeLekovaEdited.getNaziv());
			
			if(kategorijeLekovaEdited.getNamena() != null && !kategorijeLekovaEdited.getNamena().trim().equals(""))
				kategorijaLeka.setNamena(kategorijeLekovaEdited.getNamena());
			
			if(kategorijeLekovaEdited.getOpis() != null && !kategorijeLekovaEdited.getOpis().trim().equals(""))
				kategorijaLeka.setOpis(kategorijeLekovaEdited.getOpis());
			
		}
		KategorijaLeka saved = kategorijeLekova.save(kategorijaLeka);
		response.sendRedirect(bURL+"kategorijeLekova");
	}
	
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		//preuzimanje vrednosti iz konteksta
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		
		KategorijaLeka deleted = kategorijeLekova.delete(id);
		response.sendRedirect(bURL+"kategorijeLekova");
	}
	
	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam Long id, HttpServletResponse response) throws IOException {	
		KategorijeLekova kategorijeLekova = (KategorijeLekova) memorijaAplikacije.get(KategorijeController.KATEGORIJE_KEY);
		
		KategorijaLeka kategorijalekova = kategorijeLekova.findOne(id);

		PrintWriter out;
		out = response.getWriter();
		File htmlFile = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(htmlFile, "UTF-8");

		Element body = doc.select("body").first();
		
		Element ulTag = new Element(Tag.valueOf("ul"), "");
		Element liTagKategorije = new Element(Tag.valueOf("li"), "");
		Element aTagKategorije = new Element(Tag.valueOf("a"), "").attr("href", "PrviMavenVebProjekat/details").text("KategorijeKnjige");
	
		liTagKategorije.appendChild(aTagKategorije);
		ulTag.appendChild(liTagKategorije);
		
		Element form = new Element(Tag.valueOf("form"), "").attr("method", "post").attr("action", "add");
		Element table = new Element(Tag.valueOf("table"), "");
		Element caption = new Element(Tag.valueOf("caption"), "").text("Kategorije Leka");
		
		Element trNaziv = new Element(Tag.valueOf("tr"), "");
		Element thNaziv = new Element(Tag.valueOf("th"), "").text("Naziv");
		Element tdNaziv = new Element(Tag.valueOf("td"), "");
		Element inputNaziv = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "naziv");
		
		tdNaziv.appendChild(inputNaziv);
		trNaziv.appendChild(thNaziv);
		trNaziv.appendChild(tdNaziv);
		
		Element trNamena = new Element(Tag.valueOf("tr"), "");
		Element thNamena = new Element(Tag.valueOf("th"), "").text("Namena");
		Element tdNamena = new Element(Tag.valueOf("td"), "");
		Element inputNamena = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "namena");
		
		tdNamena.appendChild(inputNamena);
		trNamena.appendChild(thNamena);
		trNamena.appendChild(tdNamena);
		
		Element trOpis = new Element(Tag.valueOf("tr"), "");
		Element thOpis = new Element(Tag.valueOf("th"), "").text("Opis");
		Element tdOpis = new Element(Tag.valueOf("td"), "");
		Element inputOpis = new Element(Tag.valueOf("input"), "").attr("type", "text").attr("name", "opis");
		
		tdOpis.appendChild(inputOpis);
		trOpis.appendChild(thOpis);
		trOpis.appendChild(tdOpis);
		
		Element trSubmit = new Element(Tag.valueOf("tr"), "");
		Element thSubmit = new Element(Tag.valueOf("th"), "");
		Element tdSubmit = new Element(Tag.valueOf("td"), "");
		Element inputSubmit = new Element(Tag.valueOf("input"), "").attr("type", "submit").attr("value", "Dodaj");
		
		tdSubmit.appendChild(inputSubmit);
		trSubmit.appendChild(thSubmit);
		trSubmit.appendChild(tdSubmit);
		
		table.appendChild(caption);
		
		table.appendChild(trNaziv);
		table.appendChild(trNamena);
		table.appendChild(trOpis);
		table.appendChild(trSubmit);
		
		form.appendChild(table);
		
		body.appendChild(ulTag);
		body.appendChild(form);
		
		out.write(doc.html());
		return;		
	}
	

}

