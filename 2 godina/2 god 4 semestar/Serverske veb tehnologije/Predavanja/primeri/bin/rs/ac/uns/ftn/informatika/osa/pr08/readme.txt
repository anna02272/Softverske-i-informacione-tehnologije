FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr08

2. Opis primera
---------------
Primer elementarnog stateful session beana. Radi ilustracije moze se pokrenuti
vise klijenata istovremeno, gde ce svaki od njih pozivati svoj primerak beana.

3. Sadrzaj primera
------------------
Count.java       - remote interfejs
CountLocal.java  - local interfejs
CountBean.java   - bean klasa
CountClient.java - klijentski program

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/pr08]$ java -jar Client.jar
