FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr14

2. Opis primera
---------------
Primer upotrebe JDBC API-ja za pristup relacionim bazama podataka (u primeru
se koristi MySQL).

3. Sadrzaj primera
------------------
Db1.java   - slanje SELECT upita (Statement) i citanje rezultata (ResultSet)
Db2.java   - azuriranje sadrzaja tabele pomocu PreparedStatement
Db3.java   - pozivanje uskladistene procedure/funkcije (CallableStatement)
Db4.java   - primer (loseg) rukovanja konekcijom u okviru servleta
Db5.java   - programsko rukovanje transakcijama i nivoom izolacije transakcija

4. Pokretanje primera
---------------------
Svaka klasa ima main() pa se moze nezavisno pokrenuti. U classpathu mora biti
prisutan JDBC drajver za MySQL.