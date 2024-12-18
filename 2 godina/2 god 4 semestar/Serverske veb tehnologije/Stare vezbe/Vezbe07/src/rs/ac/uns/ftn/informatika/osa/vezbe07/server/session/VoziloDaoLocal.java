package rs.ac.uns.ftn.informatika.osa.vezbe07.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.vezbe07.server.entity.Vozilo;

public interface VoziloDaoLocal extends GenericDaoLocal<Vozilo, Integer> {

	public List<Vozilo> findVozilaSaKlimom();

}
