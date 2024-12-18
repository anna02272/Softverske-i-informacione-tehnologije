FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr15

2. Opis primera
---------------
Primer pravilnog rukovanja JDBC konekcijama u serverskim aplikacijama: ukoliko
je za obradu zahteva potrebna konekcija, ona se dobija iz poola i vraca se u
pool odmah po zavrsetku obrade.

3. Sadrzaj primera
------------------
ConnectionPool.java  - singleton klasa koja obezbedjuje pooliranje konekcija
DBServlet.java       - servlet kao primer koriscenja poola
ConnectionPool.properties - konfiguracija poola

4. Pokretanje primera
---------------------
Instalirati servlet i pool u Tomcat i pristupiti odgovarajucem URL-u.