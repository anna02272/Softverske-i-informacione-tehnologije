package com.ftn.PrviMavenVebProjekat.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.stereotype.Component;

import com.ftn.PrviMavenVebProjekat.controller.FilmoviController;
import com.ftn.PrviMavenVebProjekat.model.FilmStatistika;
import com.ftn.PrviMavenVebProjekat.model.Filmovi;


//@Component
public class InitServletContextListener implements ServletContextListener {

	/** kod koji se izvrsava po nakon inicijalizacije ServletContext objekta */
    public void contextInitialized(ServletContextEvent event)  {
    	System.out.println("Azuriranje konteksta ServletContextListener...");
    	
//    	servletContext.setAttribute(FilmoviController.FILMOVI_KEY, new Filmovi());	
//		servletContext.setAttribute(FilmoviController.STATISTIKA_FILMOVA_KEY, new FilmStatistika());
    	
    	System.out.println("Uspeh ServletContextListener!");
    }
    
    /** kod koji se izvrsava po nakon uništavanja ServletContext objekta */
	public void contextDestroyed(ServletContextEvent event)  { 
    	System.out.println("Brisanje konteksta ServletContextListener...");
    		
    	System.out.println("Uspeh ServletContextListener!");
    }
}

