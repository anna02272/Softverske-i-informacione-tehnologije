package rs.ac.uns.ftn.informatika.osa.pr03;

import java.util.Properties;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DNSClient2 {
  public static void main(String[] args) {
    try {
      Properties env = new Properties();
      // klasa za kreiranje inicijalnog konteksta
      env.put("java.naming.factory.initial",
        "com.sun.jndi.dns.DnsContextFactory");

      // adresa DNS servera; inicijalni kontekst je
      // sada domen uns.ac.rs
      env.put("java.naming.provider.url",
        "dns://147.91.173.3/ac.rs");

      // kreiramo inicijalni kontekst
      DirContext ctx = new InitialDirContext(env);

      // trazimo A slog za ime uns.ac.rs
      Attributes attrs = ctx.getAttributes("uns");
      System.out.println("Addresa hosta: " +
        attrs.get("A").get());

      // trazimo MX slog za ime eunet.rs
      // posto ime eunet.rs.ac.rs ne postoji, 
      // izazvacemo gresku
      attrs = ctx.getAttributes("eunet.rs");
      System.out.println("Mail exchange: " +
        attrs.get("MX").get());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
