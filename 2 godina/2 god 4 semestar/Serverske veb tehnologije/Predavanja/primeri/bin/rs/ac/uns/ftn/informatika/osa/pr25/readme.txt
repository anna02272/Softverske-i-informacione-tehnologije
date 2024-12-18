FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr25

2. Opis primera
---------------
Primer kompletne web aplikacije koja se sastoji iz sledecih delova:
- entity klase kao model podataka aplikacije
- DAO (data access object) SLSB-ovi koji implementiraju operacije nad 
  entitijima (tj. nad bazom podataka)
- SLSB-ovi koji implementiraju posebne funkcije obrade (npr. procesiranje 
  kreditne kartice)
- REST servisi koji predstavljaju kontrolere u web aplikaciji sa MVC arhitekturom, 
  pristupaju SLSB-ovima i entitijima
- HTML stranica i js datoteke koje predstavljaju prikaze (view) u MVC modelu
- konfiguracioni fajlovi za razlicite delove sistema

3. Sadrzaj primera
------------------
entity.*      - entity klase - model podataka
dao.*         - SLSB-ovi koji implementiraju pristup bazi
session.*     - SLSB-ovi za procesiranje placanja
web.*         - html, js, jquery client kao view iz MVC modela
service.*     - RESTful servisi kao kontroleri iz MVC modela
manifest.*    - konfiguracioni fajlovi

4. Pokretanje primera
---------------------
[] ant deploy
http://localhost:8080/pr25/
(obratite paznju na sadrzaj fajla build.properties, treba podesiti direktorijume)
