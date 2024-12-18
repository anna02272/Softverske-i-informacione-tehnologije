FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.spring.pr11

2. Opis primera
---------------
Primer za spring komponente i inverziju kontrole (dependency injection): PurchaseBean
dobija referencu na PaymentBean preko IoC mehanizma, odnosno @Autowired anotacije.

3. Sadrzaj primera
------------------
CreditCard.java      - podaci o kreditnoj kartici
Order.java           - podaci o porudzbini
Payment.java         - interfejs 
PaymentBean.java     - bean klasa za procesiranje kreditne kartice
Purchase.java        - interfejs
PurchaseBean.java    - bean klasa za procesiranje porudzbine
PurchaseApplication.java  - Spring Boot konzolna aplikacija (starter)

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/spring/pr11]$ java -jar app.jar
