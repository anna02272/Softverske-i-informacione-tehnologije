FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr01

2. Opis primera
---------------
Primer elementarnog RMI klijenta i servera. Server klijentima nudi jednu
metodu i broji ukupan broj poziva metode.

3. Sadrzaj primera
------------------
ServerI.java    - definicija RMI interfejsa
Server.java     - implementacija RMI interfejsa
ServerMain.java - klasa za pokretanje serverskog programa
Client.java     - klijentski program

4. Pokretanje primera
---------------------
[dist/pr01]$ rmiregistry
[dist/pr01]$ java -jar Server.jar
[dist/pr01]$ java -jar Client.jar
