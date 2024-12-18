FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr24

2. Opis primera
---------------
Primer MDB-a, uz citanje poruka iz JMS queuea i topica.

3. Sadrzaj primera
------------------
queue.*   - primer MDB-a (i klijenta) koji cita poruke iz JMS queue-a
topic.*   - primer MDB-a (i klijenta) koji cita poruke iz JMS topic-a

4. Pokretanje primera
---------------------
[] ant deploy
[dist/pr24] java -jar queue-client.jar
[dist/pr24] java -jar topic-client.jar
