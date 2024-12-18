package com.ftn.PrviMavenVebProjekat.listeners;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import com.ftn.PrviMavenVebProjekat.controller.FilmoviController;
import com.ftn.PrviMavenVebProjekat.controller.KorisnikController;
import com.ftn.PrviMavenVebProjekat.controller.ProjekcijeController;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Filmovi;
import com.ftn.PrviMavenVebProjekat.model.Korisnik;
import com.ftn.PrviMavenVebProjekat.model.Projekcija;

@Component
public final class InitServletContextInitializer implements ServletContextInitializer {
 
	/** kod koji se izvrsava po pokretanju aplikacije kada je ServletContext kreiran */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
    	System.out.println("Inicijalizacija konteksta pri ServletContextInitializer...");
    	
//		servletContext.setAttribute(FilmoviController.FILMOVI_KEY, new Filmovi());	
		servletContext.setAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY, new FilmStatistika());
		
//		HashMap<String, Korisnik> korisnici = new HashMap<String, Korisnik>();
//		korisnici.put("pera", new Korisnik("Petar", "Petarović", "muški", "pera", "pera", 60000, true));
//		korisnici.put("steva", new Korisnik("Steva", "Stević", "muški", "steva", "steva", 50000));
//		korisnici.put("jova", new Korisnik("Jova", "Jović", "muški", "jova", "jova", 45000));
//		servletContext.setAttribute(KorisnikController.KORISNICI_KEY,korisnici);
		
//		Filmovi filmovi = (Filmovi)servletContext.getAttribute(FilmoviController.FILMOVI_KEY);
//		
//		Map<Long, Projekcija> projekcije = new HashMap<>();
//		projekcije.put(1L, new Projekcija(1L, LocalDateTime.parse("2020-03-20 14:00", ProjekcijeController.formatter),
//				filmovi.findOne(1L), 1, "2D", 380.00));
//		projekcije.put(2L, new Projekcija(2L, LocalDateTime.parse("2020-03-20 14:00", ProjekcijeController.formatter),
//				filmovi.findOne(1L), 2, "3D", 420.00));
//		projekcije.put(3L, new Projekcija(3L, LocalDateTime.parse("2020-03-21 10:00", ProjekcijeController.formatter),
//				filmovi.findOne(2L), 1, "2D", 380.00));
//		projekcije.put(4L, new Projekcija(4L, LocalDateTime.parse("2020-03-21 18:00", ProjekcijeController.formatter),
//				filmovi.findOne(1L), 31, "4D", 580.00));
//		servletContext.setAttribute(ProjekcijeController.PROJEKCIJE_KEY, projekcije);
		
    	System.out.println("Uspeh ServletContextInitializer!");
    }
}