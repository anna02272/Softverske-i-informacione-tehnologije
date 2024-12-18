FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2017.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr25

2. Opis primera
---------------
Primer kompletne veb aplikacije koja se sastoji iz sledecih delova:
- entity klase kao model podataka aplikacije
- repository (data access object) Spring JPA repository koji implementiraju operacije nad 
  entitetima (tj. nad bazom podataka)
- service Spring servisi koji pozivaju osnovne CRUD operacije repozitorijuma
- controller  predstavljaju Spring REST kontrolere u veb aplikaciji, 
  pristupaju servisima i entitetima
- web folder - html, js, css fajlovi koje predstavljaju front end
- konfiguracioni fajlovi za razlicite delove sistema (application.properties)

3. Sadrzaj primera
------------------
entity.*      			- entity klase - model podataka
repository.*  			- JPA repository koji implementiraju pristup bazi
service.*     			- Spring servisi koji pozivaju CRUD operacije repozitorijuma
controller.*     		- Spring REST kontroleri
web         			- HTML stranice kao front-end
application.properties	- konfiguracija

4. Pokretanje primera
---------------------
[] ant deploy
Pokrenuti Tomcat server
U veb brauzeru: http://localhost:8080/pr25/
(obratite paznju na sadrzaj fajla build.properties i application.properties, 
treba podesiti direktorijume i podatke o bazi, u categories.js treba podesiti 
putanju do end-point-a back-end aplikacije)
