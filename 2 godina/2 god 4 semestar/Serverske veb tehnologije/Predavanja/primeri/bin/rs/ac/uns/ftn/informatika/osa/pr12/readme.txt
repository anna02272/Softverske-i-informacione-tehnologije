FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr12

2. Opis primera
---------------
Ilustruje upotrebu interceptora kao AOP mehanizma. Klasa Logger sadrzi metodu
koja se poziva pre poziva metoda PurchaseBean.processOrder() i 
PaymentBean.processCreditCard(). Veza je uspostavljena anotacijama u bean
klasama.

3. Sadrzaj primera
------------------
CreditCard.java      - podaci o kreditnoj kartici
Logger.java          - interceptor klasa
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
[dist/pr12]$ java -jar Client.jar
