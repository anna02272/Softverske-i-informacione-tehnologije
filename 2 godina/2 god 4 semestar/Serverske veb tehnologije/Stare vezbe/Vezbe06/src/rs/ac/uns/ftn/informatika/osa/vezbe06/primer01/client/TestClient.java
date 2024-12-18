package rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.client;

import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Vozilo;

public class TestClient {

	public static void main(String args[]) {
		Logger.getLogger("").setLevel(Level.OFF);
		// Creates an Entity Manager Factory (it is thread safe)
		Properties prop = new Properties();
		prop.put("openjpa.DynamicEnhancementAgent","false");
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Vezbe06Primer01");

		// Creates an Entity Manager (it is not thread safe)
		EntityManager manager = factory.createEntityManager();

		try {
			// Creates object model
			Gorivo dizel = new Gorivo();
			dizel.setTipGoriva("dizel");
			
			Gorivo benzin = new Gorivo();
			benzin.setTipGoriva("benzin");
			
			Menjac automatski = new Menjac();
			automatski.setTipMenjaca("automatski");
			
			Menjac rucni = new Menjac();
			rucni.setTipMenjaca("rucni");
			
			Vozilo fiatPunto = new Vozilo(5, 5, 3, 4500, new Date(), 1999, 	benzin, 1000, rucni, "Fiat Punto", 90000l, 60, 1200, false);
			Vozilo zastavaYugo = new Vozilo(5, 4, 3, 1200, new Date(), 1989, benzin, 900, rucni, "Zastava Yugo", 97000l, 45, 726, false);
			
			// Starts a transaction
			EntityTransaction tx = manager.getTransaction();
			tx.begin();
			System.out.println("1");
			// Persists entities
			manager.persist(dizel);
			System.out.println(2);
			manager.persist(benzin);
//			manager.persist(automatski);
//			manager.persist(rucni);
//			manager.persist(fiatPunto);
//			manager.persist(zastavaYugo);

			// Commits the transaction
			tx.commit();
			
			System.out.println("Entities persisted:\n" + fiatPunto + "\n" + zastavaYugo);

			// Finds an entity (ID = 1)
			tx.begin();
			Vozilo vozilo = manager.find(Vozilo.class, 1);
			System.out.println("Found:\n" + vozilo);
			
			// Updates the entity
			vozilo.setNazivVozila("Fiat");
			manager.merge(vozilo);
			System.out.println("Updated:\n" + vozilo);
			
			// Removes the entity
			vozilo = manager.find(Vozilo.class, 2);
			manager.remove(vozilo);
			tx.commit();
			System.out.println("Removed:\n" + vozilo);
			
			// Executes a query
			tx.begin();
			Query query = manager.createQuery("SELECT v FROM Vozilo v WHERE v.id > ?1");
			query.setParameter(1, 0);
			@SuppressWarnings("unchecked")
			List<Vozilo> vozila = (List<Vozilo>) query.getResultList();
			tx.commit();
			
			System.out.println("Entities retrieved:");
			for (Vozilo v : vozila)
				System.out.println(v);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			manager.close();
			factory.close();
		}
	}
}
