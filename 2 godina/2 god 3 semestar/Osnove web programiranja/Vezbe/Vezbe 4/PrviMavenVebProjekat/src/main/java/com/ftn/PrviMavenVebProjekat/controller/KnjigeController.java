package com.ftn.PrviMavenVebProjekat.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
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
	    
		
//		servletContext.setAttribute(KnjigeController.KNJIGE_KEY, knjige);	
		
		memorijaAplikacije.put(KnjigeController.KNJIGE_KEY, knjige);
	}
	
	/** pribavnjanje HTML stanice za prikaz svih entiteta, get zahtev */
	// GET: knjige
	@GetMapping
	@ResponseBody
	public void index(HttpServletResponse response)throws IOException {	
		File template = new ClassPathResource("static/template.html").getFile();
		Document doc = Jsoup.parse(template, "UTF-8");
		
		Element body = doc.getElementsByTag("body").first();
		Element link = new Element(Tag.valueOf("a"), "").attr("href", "/PrviMavenVebProjekat/knjige").text("KNJIGE");
		
		body.appendChild(link);
		
		body.appendChild(new Element(Tag.valueOf("br"), ""));
		body.append("<br/>");
		
		Element table = new Element(Tag.valueOf("table"),"").attr("border", "1");
		
		Element headingRow = new Element(Tag.valueOf("tr"),"");
		Element redniBrojTh = new Element(Tag.valueOf("th"),"").text("Redni broj");
		Element nazivTh = new Element(Tag.valueOf("th"),"").text("Naziv");
		Element registarskiBrojTh = new Element(Tag.valueOf("th"),"").text("Registarski broj");
		Element jezikTh = new Element(Tag.valueOf("th"),"").text("Jezik");
		Element brojStranicaTh = new Element(Tag.valueOf("th"),"").text("Broj stranica");
		
		headingRow.appendChild(redniBrojTh);
		headingRow.appendChild(nazivTh);
		headingRow.appendChild(registarskiBrojTh);
		headingRow.appendChild(jezikTh);
		headingRow.appendChild(brojStranicaTh);
		table.appendChild(headingRow);
		
		Knjige sveKnjige = (Knjige)memorijaAplikacije.get(KNJIGE_KEY);
		List<Knjiga> listaKnjiga = sveKnjige.findAll();	
		for (int i=0; i < listaKnjiga.size(); i++) {
			Element row = new Element(Tag.valueOf("tr"),"");
			Element redniBrojTd = new Element(Tag.valueOf("td"),"").text(new Integer(i+1 ).toString());
			Element nazivTd = new Element(Tag.valueOf("td"),"").text(listaKnjiga.get(i).getNaziv());
			Element registarskiBrojTd = new Element(Tag.valueOf("td"),"").text(listaKnjiga.get(i).getRegistarskiBrojPrimerka());
			Element jezikTd = new Element(Tag.valueOf("td"),"").text(listaKnjiga.get(i).getJezik());
			Element brojStranicaTd = new Element(Tag.valueOf("td"),"").text(new Integer(listaKnjiga.get(i).getBrojStranica()).toString());
			
			row.appendChild(redniBrojTd);
			row.appendChild(nazivTd);
			row.appendChild(registarskiBrojTd);
			row.appendChild(jezikTd);
			row.appendChild(brojStranicaTd);
			table.appendChild(row);
		}
		body.appendChild(table);
		
		PrintWriter out = response.getWriter();
		out.write(doc.html());
		
		return;
	}
	
	/** pribavnjanje HTML stanice za unos novog entiteta, get zahtev */
	// GET: knjige/dodaj
	@GetMapping(value="/add")
	@ResponseBody
	public void create() {
		return;
	}
	
	/** obrada podataka forme za unos novog entiteta, post zahtev */
	// POST: knjige/add
	@PostMapping(value="/add")
	public void create(@RequestParam String naziv, @RequestParam String registarskiBrojPrimerka, 
			@RequestParam String jezik, @RequestParam int brojStranica, HttpServletResponse response) throws IOException {		
	
	}
	
	/** obrada podataka forme za izmenu postojećeg entiteta, post zahtev */
	// POST: knjige/edit
	@PostMapping(value="/edit")
	public void edit(@ModelAttribute Knjiga knjigaEdited , HttpServletResponse response) throws IOException {	
	
	}
	
	/** obrada podataka forme za za brisanje postojećeg entiteta, post zahtev */
	// POST: knjige/delete
	@PostMapping(value="/delete")
	public void delete(@RequestParam Long id, HttpServletResponse response) throws IOException {
		
	}
	
	/** pribavnjanje HTML stanice za prikaz određenog entiteta , get zahtev */
	// GET: knjige/details?id=1
	@GetMapping(value="/details")
	@ResponseBody
	public void details(@RequestParam Long id) {	
		return;
	}
}
