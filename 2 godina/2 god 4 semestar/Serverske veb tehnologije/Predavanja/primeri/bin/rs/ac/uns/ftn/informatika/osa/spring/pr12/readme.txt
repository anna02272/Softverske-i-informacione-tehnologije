FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.spring.pr12

2. Opis primera
---------------
Ilustruje upotrebu komponente anotirane sa Aspect kao AOP mehanizma. Klasa Logger sadrzi metodu
koja se poziva pre poziva metoda komponenti, kao i nakon njihovog zavrsetka. Veza je uspostavljena anotacijama u Logger komponenti
klasama.

3. Sadrzaj primera
------------------
CreditCard.java      		- podaci o kreditnoj kartici
Logger.java          		- Aspekt komponenta
Order.java           		- podaci o porudzbini
Payment.java         		- interfejs 
PaymentBean.java     		- bean klasa za procesiranje kreditne kartice
Purchase.java        		- interfejs
PurchaseBean.java    		- bean klasa za procesiranje porudzbine
PurchaseApplication.java 	- Spring Boot konzolna aplikacija

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/spring/pr12]$ java -jar app.jar
