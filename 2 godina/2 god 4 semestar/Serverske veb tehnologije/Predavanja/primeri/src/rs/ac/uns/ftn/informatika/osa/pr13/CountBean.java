package rs.ac.uns.ftn.informatika.osa.pr13;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

@Stateful
@Remote(Count.class)
@Local(CountLocal.class)
@Interceptors(CountCallbacks.class)
public class CountBean implements Count {
  
  private int value = 0;

  public int count() {
    return ++value;
  }
  
  public void set(int value) {
    this.value = value;
  }
  
  @Remove
  public void remove() {
    System.out.println("[CountBean] remove()");
  }
  
}
