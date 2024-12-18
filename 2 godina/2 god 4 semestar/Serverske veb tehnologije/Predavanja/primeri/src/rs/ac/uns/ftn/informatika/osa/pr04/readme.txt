FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr04

2. Opis primera
---------------
Primer implementacije CORBA servera i klijenta na Java platformi. Interfejs
serverskog objekta (servanta) dat je u fajlu Hello.idl. Na osnovu tog fajla se
generisu pomocne klase za klijent i server. Pored ovih klasa potrebno je jos
napisati implementaciju servanta, implementaciju servera (koji registruje
servanta i pokrece CORBA ORB) i implementaciju klijenta (koji poziva servanta).

3. Sadrzaj primera
------------------
Hello.idl             - IDL definicija CORBA interfejsa
HelloImpl.java        - implementacija CORBA servanta
HelloServer.java      - implementacija CORBA servera
HelloClient.java      - implementacija CORBA klijenta

Ostale klase se generisu alatom idlj:
[src]$ idlj -fall rs/ac/uns/ftn/informatika/osa/pr04/Hello.idlj

(poziv idlj alata je ugradjen u ant build).

4. Pokretanje primera
---------------------
[dist/pr04]$ orbd -ORBInitialHost localhost -ORBInitialPort 1050
[dist/pr04]$ java -jar Server.jar
[dist/pr04]$ java -jar Client.jar
