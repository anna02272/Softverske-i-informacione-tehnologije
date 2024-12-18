package rs.ac.uns.ftn.informatika.osa.pr19.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.osa.pr19.entity.OrderItem;


@Stateless
@Local(OrderItemDao.class)
public class OrderItemDaoBean extends GenericDaoBean<OrderItem, Integer> implements OrderItemDao {

}
