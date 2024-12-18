package rs.ac.uns.ftn.informatika.osa.pr20.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import rs.ac.uns.ftn.informatika.osa.pr20.bean.Purchase;


/**
 * Rucno upravljanje transakcijom sa strane klijenta.
 */
public class ClientTx {

  public static void main(String[] args) {
    System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
        "org.ow2.easybeans.component.smartclient.spi.SmartContextFactory");
    System.setProperty(Context.PROVIDER_URL, "smart://localhost:2503");
    try {
      Context ctx = new InitialContext();
      Purchase purchase = (Purchase) ctx.lookup(
          "rs.ac.uns.ftn.informatika.osa.pr20.bean.PurchaseBean_rs.ac.uns.ftn.informatika.osa.pr20.bean.Purchase@Remote");

      UserTransaction tx = (UserTransaction)ctx.lookup("java:comp/UserTransaction");
      tx.begin();
      boolean status = purchase.processOrder(null, null);
      if (status)
        tx.commit();
      else
        tx.rollback();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
