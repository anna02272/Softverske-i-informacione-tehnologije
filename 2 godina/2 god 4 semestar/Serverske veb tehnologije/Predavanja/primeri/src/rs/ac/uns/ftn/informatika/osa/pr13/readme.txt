FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr13

2. Opis primera
---------------
Stateful session bean sa callback klasom za obradu life-cycle dogadjaja. 
Callback klasa je i u ovom slucaju interceptor, samo se ne presrecu pozivi
metoda beana, nego dogadjaji u toku zivotnog ciklusa beana. 

3. Sadrzaj primera
------------------
Count.java          - remote interfejs
CountLocal.java     - local interfejs
CountBean.java      - bean klasa
CountClient.java    - klijentski program
CountCallbacks.java - klasa sa life-cycle callback metodama

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/pr13]$ java -jar Client.jar
