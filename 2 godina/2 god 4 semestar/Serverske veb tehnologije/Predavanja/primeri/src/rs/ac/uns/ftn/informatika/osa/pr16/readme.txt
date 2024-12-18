FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr16

2. Opis primera
---------------
Primer O/R mapiranja - klasa Admin se mapira na tabelu ADMINS u bazi podataka.
Za operacije nad podacima u bazi koristi se objektni model i entity manager.

3. Sadrzaj primera
------------------
Admin.java      - anotirana perzistentna klasa
AdminTest.java  - primer rada sa perzistentnom klasom i entity managerom
persistence.xml - konfiguracija entity managera

4. Pokretanje primera
---------------------
[dist/pr16] java -jar pr16.jar
(Napomena: ovaj jar ce koristiti jarove i /runtime-libs direktorijuma)