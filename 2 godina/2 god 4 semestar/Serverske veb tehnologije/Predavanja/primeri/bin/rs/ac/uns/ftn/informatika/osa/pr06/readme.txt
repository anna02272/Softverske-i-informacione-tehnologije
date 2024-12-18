FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr06

2. Opis primera
---------------
Pristup RMI serverskom objektu, gde klijent koristi JNDI API. Jedina razlika
u odnosu na prvi primer je u klijentu.

3. Sadrzaj primera
------------------
ServerI.java    - RMI interfejs
Server.java     - implementacija RMI interfejsa
Client.java     - klijentski program

4. Pokretanje primera
---------------------
[dist/pr06]$ rmiregistry
[dist/pr06]$ java -jar Server.jar
[dist/pr06]$ java -jar Client.jar
