package rs.ac.uns.ftn.informatika.osa.pr25.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.Supplier;


@Stateless
@Local(SupplierDao.class)
public class SupplierDaoBean extends GenericDaoBean<Supplier, Integer> implements SupplierDao {

}
