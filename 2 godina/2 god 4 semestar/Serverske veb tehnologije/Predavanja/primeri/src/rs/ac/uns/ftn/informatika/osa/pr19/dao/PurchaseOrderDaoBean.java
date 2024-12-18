package rs.ac.uns.ftn.informatika.osa.pr19.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.PurchaseOrder;


@Stateless
@Local(PurchaseOrderDao.class)
public class PurchaseOrderDaoBean extends GenericDaoBean<PurchaseOrder, Integer> implements PurchaseOrderDao {

}
