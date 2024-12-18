FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr05

2. Opis primera
---------------
Pristup RMI serverskom objektu preko IIOP protokola (RMI-over-IIOP). Razlika u
odnosu na klasicni RMI je 
(1) na strani klijenta:
- klijent pronalazi objekat preko odgovarajuceg JNDI provajdera
- mora da se uradi eksplicitan cast pronadjenog objekta u RMI interfejs
(2) na strani servera:
- implementacija interfejsa mora da nasledi PortableRemoteObject umesto
  UnicastRemoteObject
(3) Mora se eksplicitno pozvati RMI kompajler koji generise RMI-over-IIOP stub
i tie klase (ugradjeno u ant build). 

3. Sadrzaj primera
------------------
HelloI.java        - definicija RMI interfejsa
HelloImpl.java     - implementacija RMI interfejsa
HelloServer.java   - serverski program
HelloClient.java   - klijentski program

4. Pokretanje primera
---------------------
[dist/pr05]$ orbd -ORBInitialHost localhost -ORBInitialPort 1050
[dist/pr05]$ java -jar Server.jar
[dist/pr05]$ java -jar Client.jar
