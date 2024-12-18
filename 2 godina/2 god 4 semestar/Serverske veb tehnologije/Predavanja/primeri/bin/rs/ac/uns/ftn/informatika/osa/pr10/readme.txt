FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr10

2. Opis primera
---------------
Primer kada jedan bean poziva drugog (prethodno ga pronalazi preko JNDI).
U okviru EJB komponenti koristi se InitialContext() konstruktor bez parametara.
Sekvenca pozivanja je sledeca:
Client --Order--> Purchase --CreditCard--> Payment

3. Sadrzaj primera
------------------
CreditCard.java      - podaci o kreditnoj kartici
Order.java           - podaci o porudzbini
Payment.java         - remote interfejs 
PaymentLocal.java    - local interfejs
PaymentBean.java     - bean klasa za procesiranje kreditne kartice
Purchase.java        - remote interfejs
PurchaseLocal.java   - local interfejs
PurchaseBean.java    - bean klasa za procesiranje porudzbine
PurchaseClient.java  - klijentski program

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/pr10]$ java -jar Client.jar
