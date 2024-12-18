FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2017.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.spring.pr15

2. Opis primera
---------------
Primer rada sa relacionom bazom pomocu Spring JDBCTemplate-a.

3. Sadrzaj primera
------------------
User.java      				- podaci o korisniku
UserJDBC.java         		- interfejs 
UserJDBCBean.java     		- bean klasa za CRUD operacije nad korisnikom
UserJDBCApplication.java 	- Spring Boot konzolna aplikacija

4. Pokretanje primera
---------------------
[]$ ant deploy
[dist/spring/pr15]$ java -jar app.jar