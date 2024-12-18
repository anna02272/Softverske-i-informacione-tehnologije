package rs.ac.uns.ftn.informatika.osa.vezbe06.primer02.server.session;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Menjac;
import rs.ac.uns.ftn.informatika.osa.vezbe06.primer01.server.entity.Vozilo;

@Stateless(name = "Auto")
@Local(TestLocal.class)
@Remote(Test.class)
public class TestBean implements Test {

	@PersistenceUnit(unitName = "Vezbe06Primer02")
	protected EntityManagerFactory factory;

	public void test() {

		try {
			// Create an entity manager instance
			EntityManager manager = factory.createEntityManager();
			
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
			
			// Persists entities
			manager.persist(dizel);
			manager.persist(benzin);
			manager.persist(automatski);
			manager.persist(rucni);
			manager.persist(fiatPunto);
			manager.persist(zastavaYugo);

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
			
			manager.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
