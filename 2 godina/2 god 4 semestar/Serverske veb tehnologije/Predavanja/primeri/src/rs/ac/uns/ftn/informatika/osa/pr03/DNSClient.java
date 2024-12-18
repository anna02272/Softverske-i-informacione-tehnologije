package rs.ac.uns.ftn.informatika.osa.pr03;

import java.util.Properties;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DNSClient {
  public static void main(String[] args) {
    try {
      Properties env = new Properties();
      // klasa za kreiranje inicijalnog konteksta
      env.put("java.naming.factory.initial",
        "com.sun.jndi.dns.DnsContextFactory");
      // adresa DNS servera
      env.put("java.naming.provider.url",
        "dns://147.91.173.3/");

      // kreiramo inicijalni kontekst
      DirContext ctx = new InitialDirContext(env);

      // trazimo A slog za ime uns.ac.rs
      Attributes attrs =
        ctx.getAttributes("uns.ac.rs");
      Attribute attr = attrs.get("A");
      System.out.println("Adresa hosta: " +
        attr.get());

      // trazimo MX slog za ime eunet.rs
      attrs = ctx.getAttributes("eunet.rs");
      attr = attrs.get("MX");
      System.out.println("Mail exchange: " +
        attr.get());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
