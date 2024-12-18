FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2017.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.spring.pr19

2. Opis primera
---------------
Primer kompletne veb aplikacije koja se sastoji iz sledecih delova:
- entity klase kao model podataka aplikacije
- repository (data access object) Spring JPA repository koji implementiraju operacije nad 
  entitetima (tj. nad bazom podataka)
- service Spring servisi koji pozivaju osnovne CRUD operacije repozitorijuma
- controller  predstavljaju Spring kontrolere u veb aplikaciji sa MVC arhitekturom, 
  pristupaju servisima i entitetima
- JSP stranice koje predstavljaju prikaze (view) u MVC modelu
- konfiguracioni fajlovi za razlicite delove sistema (application.properties)

3. Sadrzaj primera
------------------
entity.*      			- entity klase - model podataka
repository.*  			- JPA repository koji implementiraju pristup bazi
service.*     			- Spring servisi koji pozivaju CRUD operacije repozitorijuma
controller.*     		- Spring kontroleri kao kontroleri iz MVC modela
web.*         			- JSP stranice kao view iz MVC modela
application.properties	- konfiguracija

4. Pokretanje primera
---------------------
[] ant deploy
Pokrenuti Tomcat server
U veb brauzeru: http://localhost:8080/pr19/
(obratite paznju na sadrzaj fajla build.properties i application.properties, 
treba podesiti direktorijume i podatke o bazi)
