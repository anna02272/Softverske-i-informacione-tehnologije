package rs.ac.uns.ftn.informatika.osa.vezbe08.server.session;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Gorivo;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Korisnik;
import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Menjac;

@Stateless
@Remote(Init.class)
public class InitBean implements Init {

	@PersistenceContext(unitName = "Vezbe08")
	EntityManager em;
	
	public void init() {
		Korisnik korisnik = new Korisnik("Admin", "Admin", "admin", "admin");
		em.persist(korisnik);
		
		Gorivo dizel = new Gorivo();
		dizel.setTipGoriva("Dizel");
		em.persist(dizel);
		
		Gorivo benzin = new Gorivo();
		benzin.setTipGoriva("Benzin");
		em.persist(benzin);
		
		Menjac automatski = new Menjac();
		automatski.setTipMenjaca("Automatski");
		em.persist(automatski);
		
		Menjac rucni = new Menjac();
		rucni.setTipMenjaca("Manuelni");
		em.persist(rucni);
	}
}
