package rs.ac.uns.ftn.informatika.osa.pr25.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.osa.pr25.entity.PurchaseOrder;
import rs.ac.uns.ftn.informatika.osa.pr25.entity.User;


@Stateless
@Local(UserDao.class)
public class UserDaoBean extends GenericDaoBean<User, Integer> implements UserDao {

  @SuppressWarnings("unchecked")
  public User login(String username, String password) {
    Query q = em.createQuery(
        "SELECT u FROM User u WHERE u.username=:username AND u.password=:password");
    q.setParameter("username", username);
    q.setParameter("password", password);
    List<User> list = q.getResultList();
    if (list.size() == 0)
      return null;
    else
      return list.get(0);
  }
  
  public void add(User user, PurchaseOrder order) {
    user = em.merge(user);
    user.add(order);
  }
}
