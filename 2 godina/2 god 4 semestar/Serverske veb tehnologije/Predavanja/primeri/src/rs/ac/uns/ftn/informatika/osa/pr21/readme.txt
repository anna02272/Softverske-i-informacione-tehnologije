FTN / Katedra za informatiku
Osnove softverskih arhitektura / 2016.
=================================

1. Lokacija primera
-------------------
rs.ac.uns.ftn.informatika.osa.pr21

2. Opis primera
---------------
Primer optimistickog i pesimistickog zakljucavanja pomocu entitija. U obe 
varijante primer se sastoji iz jednog entitija i jedne test-klase koja u dva
threada izvrsava transakciju nad istim podacima.

3. Sadrzaj primera
------------------
optimistic.*   - primer sa optimistickim zakljucavanjem
pessimistic.*  - primer sa pesimistickim zakljucavanjem

4. Pokretanje primera
---------------------
[dist/pr21] java -jar pr21-optimistic.jar
[dist/pr21] java -jar pr21-pessimistic.jar

