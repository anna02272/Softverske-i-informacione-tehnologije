FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.spring.pr18

2. Opis primera
---------------
Pristup service beanu iz controller-a. 

3. Sadrzaj primera
------------------
CreditCard.java      - podaci o kreditnoj kartici
Logger.java          - interceptor klasa
Order.java           - podaci o porudzbini
Payment.java         - interfejs 
PaymentBean.java     - bean klasa za procesiranje kreditne kartice
Purchase.java        - interfejs
PurchaseBean.java    - bean klasa za procesiranje porudzbine
PurchaseController.java - controller koji pristupa purchase beanu
index.html           - stranica sa HTML formom koja upucuje na controller

4. Pokretanje primera
---------------------
[] ant deploy
http://localhost:8080/pr18/
