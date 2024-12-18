package rs.ac.uns.ftn.informatika.osa.vezbe08.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.vezbe08.server.entity.Vozilo;

public interface VoziloDaoLocal extends GenericDaoLocal<Vozilo, Integer> {

	public List<Vozilo> findVozilaSaKlimom();

}
