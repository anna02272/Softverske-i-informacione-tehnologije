package rs.ac.uns.ftn.informatika.osa.vezbe07.server.session;

import rs.ac.uns.ftn.informatika.osa.vezbe07.server.entity.Korisnik;

public interface KorisnikDaoLocal extends GenericDaoLocal<Korisnik, Integer> {

	public Korisnik findKorisnikSaKorisnickimImenomILozinkom(
			String korisnickoIme, String lozinka);

}
