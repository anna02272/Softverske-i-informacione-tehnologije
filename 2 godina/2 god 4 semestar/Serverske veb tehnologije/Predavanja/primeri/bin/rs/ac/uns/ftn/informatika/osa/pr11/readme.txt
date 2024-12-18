FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr11

2. Opis primera
---------------
Primer za dependency injection: slicno kao prethodni primer, samo PurchaseBean
dobija referencu na PaymentBean preko DI mehanizma, odnosno @EJB anotacije.
Napomena: injekcija i stateless/stateful beanovi
stateless -> stateful    zabranjeno
stateless -> stateless   OK
stateful  -> stateful    OK
stateful  -> stateless   OK

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
[dist/pr11]$ java -jar Client.jar
