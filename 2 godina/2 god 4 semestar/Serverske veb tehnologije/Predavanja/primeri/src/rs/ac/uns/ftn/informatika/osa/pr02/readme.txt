FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr02

2. Opis primera
---------------
Primer prenosenja programskog koda preko RMI. Serverska metoda kao parametar
ima interfejs, a klijentski program prilikom poziva metode prosledjuje instancu
podtipa (klase koja implementira interfejs, i koja nije poznata serveru).

Za RMI dynamic code downloading potrebno je ukljuciti RMISecurityManager i na
klijentu i na serveru. Ovaj security manager ne podrazumeva pravo aplikacije da
uspostavlja konekcije i cita fajlove iz fajl-sistema pa se ta prava moraju
eksplicitno dodeliti programima kroz "security policy" fajlove. Prilikom poziva
servera i klijenta definise se i security policy fajl.

3. Sadrzaj primera
------------------
Compute.java        - definicija RMI interfejsa
ComputeEngine.java  - implementacija RMI interfejsa
Task.java           - interfejs koji predstavlja parametar metode RMI interfejsa
Pi.java             - implementacija Task interfejsa
ComputePi.java      - klijentski program
server.policy       - security policy fajl za serverski program
client.policy       - security policy fajl za klijentski program

4. Pokretanje primera
---------------------
[dist/pr02]$ rmiregistry
[dist/pr02]$ java -Djava.security.policy=server.policy -jar Server.jar
[dist/pr02]$ java -Djava.security.policy=client.policy -jar Client.jar
