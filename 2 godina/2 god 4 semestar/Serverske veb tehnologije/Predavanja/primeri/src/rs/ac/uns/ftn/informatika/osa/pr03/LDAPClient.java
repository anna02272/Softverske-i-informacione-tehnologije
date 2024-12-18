package rs.ac.uns.ftn.informatika.osa.pr03;

import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchResult;

public class LDAPClient {
  public static void main(String[] args) {
    try {
      Properties env = new Properties();
      // klasa za kreiranje inicijalnog konteksta
      env.put("java.naming.factory.initial",
        "com.sun.jndi.ldap.LdapCtxFactory");
      // adresa LDAP servera i inicijalni kontekst
      env.put("java.naming.provider.url",
        "ldap://localhost:389/o=ftn");

      // kreiramo inicijalni kontekst
      DirContext ctx = new InitialDirContext(env);

      // Navodimo koje atribute trazimo
      Attributes match = new BasicAttributes(true);
      match.put(new BasicAttribute("sn", "Petrovic"));
      match.put(new BasicAttribute("email"));

      // Postavljamo upit u okviru podkonteksta
      // ou=People
      NamingEnumeration<SearchResult> answer = ctx.search(
        "ou=ccd", match);

      // ispisujemo rezultate upita
      while (answer.hasMore()) {
        SearchResult sr = answer.next();
        System.out.println("Objekat: " + sr.getName());
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
