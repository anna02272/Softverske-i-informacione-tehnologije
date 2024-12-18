package rs.ac.uns.ftn.informatika.osa.vezbe07.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.osa.vezbe07.server.entity.Menjac;

@Stateless
@Local(MenjacDaoLocal.class)
public class MenjacDaoBean extends GenericDaoBean<Menjac, Integer> implements MenjacDaoLocal {

}
