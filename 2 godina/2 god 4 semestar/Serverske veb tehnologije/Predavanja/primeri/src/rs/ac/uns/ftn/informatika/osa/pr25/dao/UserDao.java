package rs.ac.uns.ftn.informatika.osa.pr25.dao;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.pr25.entity.User;

public interface UserDao extends GenericDao<User, Integer> {

  public User login(String username, String password);
  public void add(User user, PurchaseOrder order);

}
