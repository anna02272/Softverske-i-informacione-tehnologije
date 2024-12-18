package rs.ac.uns.ftn.informatika.osa.vezbe07.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.osa.vezbe07.server.entity.Korisnik;

@Stateless
@Local(KorisnikDaoLocal.class)
public class KorisnikDaoBean extends GenericDaoBean<Korisnik, Integer>
		implements KorisnikDaoLocal {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka) {
		Query q = em
				.createNamedQuery("findKorisnikSaKorisnickimImenomILozinkom");
		q.setParameter("korisnickoIme", korisnickoIme);
		q.setParameter("lozinka", lozinka);
		Korisnik result = (Korisnik) q.getSingleResult();
		return result;
	}

}
