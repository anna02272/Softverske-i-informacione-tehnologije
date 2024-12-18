FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr09

2. Opis primera
---------------
Stateful session bean sa metodama za obradu life-cycle dogadjaja. 

3. Sadrzaj primera
------------------
Count.java          - remote interfejs
CountLocal.java     - local interfejs
CountBean.java      - bean klasa
CountClient.java    - klijentski program

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/pr09]$ java -jar Client.jar
