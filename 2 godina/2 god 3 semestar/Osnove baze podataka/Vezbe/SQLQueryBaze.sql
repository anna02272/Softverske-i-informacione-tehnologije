create database sr462021;
use sr462021;

----------------------------------------------------- Vezbe 2 --------------------------------------------------------------------
--brisanje tabele DROP TABLE
Drop Table Predaje
Drop Table Predmet
Drop Table Nastavnik

--kreiranje tabele CREATE TABLE
Create Table Nastavnik
	(S_Nas		SmallInt Primary Key,
	 Prezime_Ime		Varchar(25) Not Null,
	 Zvanje		Char(6),
	 S_Dir		SmallInt,
	 DatZap		SmallDateTime Not Null,
	 Plata			Money Not Null,
	 Dodatak		Money)

Create Table Predmet
	(S_Pred		SmallInt Primary Key,
	 Naziv		VarChar(35) Not Null,
	 Mesto    		VarChar(25) Not Null,
	 Semestar		SmallInt Not Null)

Create Table Predaje
	(S_Nas		SmallInt 
		Foreign Key References Nastavnik(S_Nas),
	 S_Pred		SmallInt
		Foreign Key references Predmet(S_Pred),
	 Casova		SmallInt Not Null,
	 Primary Key (S_Nas, S_Pred))

 create table Student(
	s_stud smallint primary key,
	ime varchar(30) not null,
	prezime varchar(30) not null,
	br_indeksa varchar(10) not null
 );

 create table Polaze(
	 s_stud smallint foreign key references student(s_stud) ,
	 s_pred smallint foreign key references predmet(s_pred),
	 br_bodova smallint,
	 ocena smallint, 
	 primary key(s_stud, s_pred)

 );

----------------------------------------------------- Vezbe 2 --------------------------------------------------------------------

--izmena tabele ALTER TABLE
--dodavanje ADD
alter table nastavnik add broj_radova int;

--dodavanje ogranicenja ADD CONSTRAINT 
--CHECK
alter table nastavnik add constraint nas_br_rad_ch check(broj_radova > 1);

--brisanje ogranicenja DROP CONSTRAINT
alter table nastavnik drop constraint nas_br_rad_ch;

--brisanje kolone DROP COLUMN
alter table nastavnik drop column broj_radova;

--popunjavanje tabele INSERT INTO
insert into nastavnik values 
(100, 'Lazarevic Laza', 'DOCENT', null, '1982-02-01', 11000, null);

Insert Into Nastavnik Values
	(001, 'Radovic Nikola', 'R Prof', Null, '1961-11-01', 14500,4000)
Insert Into Nastavnik Values
     (002,'Petrovic Petar','Docent',006,'1982-02-01', 11500,Null)
Insert Into Nastavnik Values
	(003, 'Peic Petar', 'Docent', 6, '1983-03-01', 11500, Null)
Insert Into Nastavnik Values
	(004, 'Simic Sima', 'Docent', 6, '1984-04-01', 11500, Null)
Insert Into Nastavnik Values
	(005,'Ilic Jovan', 'Docent', 6, '1985-05-01', 11500,Null)
Insert Into Nastavnik Values
	(006, 'Savic Ilija', 'V Prof', 1, '1975-06-01', 12500, 1000)
Insert Into Nastavnik Values
	(007, 'Tot Ana', 'V Prof',1, '1975-08-01', 12500,1000)
Insert Into Nastavnik Values
	(008, 'Savic Milan', 'R Prof', Null, '1960-10-01', 13500, Null)
Insert Into Nastavnik Values
	(011,'Petric Janko','R Prof', Null,'1960-10-01', 13500,Null)

Insert Into Predmet Values
	(001, 'Informacioni Sistemi', 'Novi Sad', 8)
Insert Into Predmet Values
	(002, 'Strukture i BP', 'Zrenjanin', 3)
Insert Into Predmet Values
	(003, 'Osnove racunarstva', 'Novi Sad', 1)
Insert Into Predmet Values
	(004, 'Tehnike programiranja', 'Novi Sad', 8)
Insert Into Predmet Values
	(005, 'Programiranje RS', 'Novi Sad', 2)

Insert Into Predaje Values
	(1,3,3)
Insert Into Predaje Values
	(1,5,2)
Insert Into Predaje Values
	(2,1,2)
Insert Into Predaje Values
	(3, 5, 2)
Insert Into Predaje Values
	(5, 2, 2)
 
 Insert into Student values(1, 'tina', 'gabric', 'sr39/2021' );
 Insert into Polaze values (1, 4, 91, 10);
 insert into student values (1000, 'Sava', 'Savic', 'SR1/2021');
insert into student values (2000, 'Mirko', 'Miric', 'SR2/2021');
insert into student values (3000, 'Marina', 'Maric', 'SR3/2021');
insert into student values (4000, 'Javor', 'Jelic', 'SR4/2021');
insert into student values (5000, 'Goran', 'Goric', 'SR5/2021');
insert into student values (6000, 'Jovan', 'Ilic', 'SR6/2021');
insert into student values (100, 'Marko', 'Markovic', 'SR7/2019');
insert into polaze values (1000, 2, 87, 9);
insert into polaze values (4000, 2, 40, 5);
insert into polaze values (6000, 2, 57, 6);
insert into polaze values (100, 2, 71, 8);
insert into polaze values (1000, 3, null, null);
insert into polaze values (4000, 3, 17, 5);
insert into polaze values (5000, 3, 77, 8);
insert into polaze values (1000, 1, 80, 8);
insert into polaze values (3000, 1, 97, 10);
 
 ------------- DOMACI
 --jedinstvenost UNIQUE
 alter table student add unique(br_indeksa);

 alter table polaze add constraint ogr_vr check (br_bodova <= 100);

 alter table polaze alter column br_bodova smallint not null;

 ----------------------------------------------------- Vezbe 3 --------------------------------------------------------------------
 -- prikaz sadrzaja SELECT , AS
 --redosled izvrsavanja: FROM, WHERE, GROUP BY, HAVING, SELECT, ORDER BY, LIMIT

 select * from nastavnik;
 select * from predmet;
 select * from predaje;

 select s_nas, prezime_ime, plata * 12 from nastavnik;
 select s_nas, prezime_ime, plata * 12 as "Godisnja plata" from nastavnik;
 select s_nas, prezime_ime, plata * 12 GodPlata from nastavnik;

 --WHERE : and, or, not( in, between x and y, exists, like, is null)

 select * from predmet where semestar = 8;

 --is (not) null
 select prezime_ime, plata, dodatak from nastavnik where dodatak is not null;

 --in
 select * from predmet where s_pred = 2 or s_pred = 3;
 select * from predmet where s_pred in (2, 3);

 --between
 select * from nastavnik where plata between 11500 and 12000;
 select * from nastavnik where plata >= 11500 and plata <= 12000;

 --like %
 select * from nastavnik where Prezime_Ime like 'P%';

 select * from nastavnik where Prezime_Ime like '__I%';

 select * from nastavnik where Prezime_Ime like '% P%';

 --ORDER BY
 --asc desc
 select plata, Prezime_Ime, zvanje from Nastavnik order by plata asc;

 select * from predmet where semestar >=3 order by naziv asc, mesto asc;

 --izbacuje u ispisu vrednosti koje se ponavljaju DISTINCT
 select distinct zvanje from nastavnik;

 --AVG, SUM, MIN, MAX, COUNT
 select count(*) , sum(plata), sum(dodatak), count(distinct zvanje) from nastavnik;

 select count (*), sum(plata), sum(dodatak), count(dodatak) from nastavnik where zvanje = 'Docent';

 ------------- DOMACI
 --CAST AS

 select * from student where br_indeksa not like '%/2021';

 select avg(cast(ocena as float)) prosecnaOcena from polaze where s_stud = '1000' ;

 select count(s_pred) from polaze where s_stud = '4000';

 select s_stud from polaze where ocena = '5';

 ----------------------------------------------------- Vezbe 4 --------------------------------------------------------------------
 
 select distinct zvanje from nastavnik;
 select avg(plata) from nastavnik where zvanje = 'docent';
 select avg(plata) from nastavnik where zvanje = 'r prof';
 select avg(plata) from nastavnik where zvanje = 'v prof';

 -- grupisanje GROUP BY
 select zvanje, avg(plata) from nastavnik group by zvanje;

 select s_nas, count(s_pred), avg(cast(casova as float)) from predaje group by s_nas;

 -- filtriranje grupa HAVING 

 select  mesto, count(s_pred) from predmet group by mesto having count(s_pred) >= 2;
 
 --GROUP BY i ORDER BY

 select zvanje, min(plata) from nastavnik group by zvanje order by min(plata) desc ;

 select s_dir, count(s_nas), max(DatZap) from nastavnik where s_dir is not null group by s_dir order by count(s_nas) asc;
 
 -- ISNULL(izraz,konstanta 0)
 select s_nas, prezime_ime, avg((plata + isnull(dodatak,0)) * 12 ) from nastavnik where zvanje = 'R Prof';
 --bez avg radi

 --nije moguce koristiti u istoj koloni celobrojne i znakovne vrednosti,
 --pa zato konvertujemo sa CAST(obelezje AS CHAR)
 select s_nas, prezime_ime, isnull(cast(s_dir as char), 'Nema direktora') from nastavnik;

 --ABS(broj) apsolutna vred, POWER(broj, x) stepenovanje, ROUND(broj, d) zaokružuje na decimala,
 --SQRT(broj) koren, SIGN(broj) daje +1 ako je broj>0, 0 ako je broj=0, -1 ako je broj<0

 select s_nas, prezime_ime, round((plata+ isnull(dodatak, 0))/176, 2) as zaradaPoSatu from nastavnik where (plata+ isnull(dodatak, 0))/176 > 70 ;

 --LEN(string) duzina, LEFT(string, n) selekcija prvih, RIGHT(string, n) selekcija poslednjih,
 --LTRIM(string) uklanjanje whitespace sa pocetka, RTRIM(string) sa kraja, LOWER(string) mala slova, UPPER(string) velika slova,
 --SUBSTRING(string, n, k) seleckija podniza, duzine, pocevsi od ntog karakt, string1 + string2 konkatenacija

 select upper(zvanje) + ', ' + Prezime_Ime from nastavnik;

 select left(prezime_ime, 3), substring(zvanje, 1, 1) from nastavnik where zvanje like '% Prof';
 select left(prezime_ime, 3), left(zvanje,1) from nastavnik where zvanje like '% Prof';

 select left(prezime_ime, len(prezime_ime)-1) + upper(right(prezime_ime, 1)) from nastavnik;
-- select substring(prezime_ime, 1, len(prezime_ime)-1) + upper(substring(prezime_ime, len(prezime_ime),1) from nastavnik ;

--CHARINDEX(char, string) vra?a indeks zadatog karaktera char u stringu string
select right(prezime_ime, len(prezime_ime) - charindex('', prezime_ime)) from Nastavnik;

 ------------- DOMACI

 select s_stud, cast(avg(ocena) as float) prosecnaOcena from polaze group by s_stud;
 
 select s_pred , count(s_stud) from polaze group by s_pred having count(s_stud) >=2;

 select s_stud, ime, prezime, br_indeksa from student where left(ime, 2)= left(prezime, 2)

 select right(br_indeksa, 4) GodStud, count(s_stud) BrStud from student group by right(br_indeksa, 4);

 ----------------------------------------------------- Vezbe 5 --------------------------------------------------------------------
 --ulaganje upita FROM, SELECT , WHERE =, !=, IN, NOT IN, EXISTS, NOT EXISTS, ANY, ALL

 select * from nastavnik where s_nas in (select s_nas from predaje);
 
 select prezime_ime, plata/(select sum(plata) from nastavnik) from nastavnik;

 select prezime_ime from nastavnik where s_nas in (select s_dir from nastavnik);

 select s_nas, prezime_ime, zvanje from Nastavnik where zvanje in(select zvanje from Nastavnik where prezime_ime = 'Radovic Nikola' );

 select prezime_ime, zvanje, plata from nastavnik where zvanje = 'docent' and s_nas not in (select s_nas from predaje);;
 
 -- kada ocekujemo da upit vraca samo jednu vrednost koristimo = umesto in
 select prezime_ime, plata from nastavnik where plata = (select max(plata) from nastavnik);

 --dinamicka zamena, definisanje alijasa
 select prezime_ime, (select count(s_pred) from predaje p where p.s_nas = n.s_nas) from nastavnik n;

 select s_nas, prezime_ime, plata , zvanje from nastavnik n1 where plata > (select avg(plata) from nastavnik n2 where n2.zvanje = n1.zvanje);
 
 --EXSISTS
 select naziv from predmet pd where exists(select * from Predaje pj where pj.S_pred = pd.S_Pred);

 --NOT EXISTS
 select n1. * from nastavnik n1 where not exists (select * from nastavnik n2 where n2.plata > n1.plata);
 
 --ANY, ALL moze uz njih i  >, <, <=, >=
 select * from nastavnik where plata >= all(select plata from nastavnik);

 select prezime_ime from nastavnik where s_nas in (select s_nas  from predaje where s_pred = 5)
 and s_nas not in(select s_nas from predaje where s_pred = 3);

 select s_nas, prezime_ime, plata from nastavnik where s_nas in (select s_nas from predaje where s_pred in
(select s_pred from predmet where naziv = 'PROGRAMIRANJE RS'));

select s_nas, prezime_ime, plata, zvanje from nastavnik where zvanje = 'R Prof' and plata > 
(select avg(plata) from nastavnik where zvanje = 'docent');

select zvanje, avg(plata) from nastavnik group by zvanje having avg(plata) >=  all(select avg(plata) from nastavnik group by zvanje);

select mesto, count(s_pred) from predmet group by mesto, semestar having count(s_pred) >= 2 and semestar = 8 ;
select mesto, count(s_pred) from predmet where semestar = 8 group by mesto having count (s_pred) >=2;

select s_nas, prezime_ime, plata from nastavnik where s_nas not in(select s_nas from predaje where s_pred in
(select s_pred from predmet where naziv like '%program%'))

select s_pred, naziv from predmet where s_pred in (select s_pred from predaje group by s_pred having sum(casova) >
(select avg(casova) from predaje));

--UNION, UNION ALL, INTERSECT, EXCEPT
--nije dobro
select s_nas, prezime_ime, plata * 1.20 from nastavnik where s_nas in (select s_dir from nastavnik)
union select s_nas, prezime_ime, plata from nastavnik where s_nas not in (select s_dir from nastavnik);

 ------------- DOMACI
 select * from student where s_stud not in (select s_stud from Polaze);

 select naziv from predmet where s_pred in (select s_pred from Polaze where ocena > 5 ) group by naziv;
 
 select naziv from predmet where s_pred in (select s_pred from polaze where s_stud in 
 (select s_stud from student group by s_stud, ime having ime = 'Sava'));

 select * from student where s_stud in (select s_stud from polaze where s_pred in 
 (select s_pred from predmet where naziv = 'Strukture i BP') and ocena>= 
 (select max(ocena) from polaze where s_pred =
 (select s_pred from predmet  where naziv = 'Strukture i BP')));
 
 ----------------------------------------------------- Vezbe 6 --------------------------------------------------------------------
 --ponavljanje
 select zvanje, avg(plata) prosPlata from nastavnik group by zvanje;
 select distinct zvanje, (select avg(plata) from nastavnik n2 where n2.zvanje = n1.zvanje) from nastavnik n1; 
 
 select prezime_ime, zvanje, plata from nastavnik  where zvanje= 'docent' union
 select prezime_ime, zvanje, plata + isnull(dodatak, 0) from nastavnik  where zvanje= 'r prof' ;

 --povezivanje vise relacija
 select prezime_ime, plata, s_pred from nastavnik n, predaje p where n.s_nas = p.s_nas;

 select s_nas from predmet pr, predaje p where pr.S_Pred = p.S_pred and semestar = 8;

 select prezime_ime, casova from nastavnik n, predaje pr, predmet p where  n.S_nas = pr.S_Nas and 
 pr.S_Pred = p.S_Pred and naziv = 'Programiranje RS';

 select n.prezime_ime Nastavnik, d.Prezime_Ime Direktor from nastavnik n, nastavnik d where n.S_Dir = d.S_nas;
 -- INNER JOIN
  select n.prezime_ime Nastavnik, d.Prezime_Ime Direktor from nastavnik n inner join nastavnik d on n.S_Dir = d.S_nas;
  --OUTER JOIN - left, right , full
  select n.prezime_ime Nastavnik, d.Prezime_Ime Direktor from nastavnik n left outer join nastavnik d on n.S_Dir = d.S_nas;

  select pd.s_pred, isnull(prezime_ime, 'nema nastavnika') from(predmet pd left outer join predaje pj
  on pd.S_Pred = pj.S_pred) left outer join nastavnik n on pj.S_Nas = n.S_Nas;

  select n.s_nas, Prezime_Ime, count(p.s_pred) from nastavnik n left outer join  predaje p on n.S_Nas = p.S_Nas
  group by n.s_nas, prezime_ime;

  select n.s_nas, prezime_ime, n.zvanje, count(s_pred)  from nastavnik n, predaje p where n.S_Nas = p.S_Nas
  and plata > (select avg(plata) from nastavnik nn where nn.zvanje = n.zvanje) group by n.S_Nas, Prezime_Ime, n.zvanje
  having count(s_pred) >=1;

select p.s_pred, naziv, mesto, count(s_nas) BrojNastavnika, isnull(sum(casova), 0) BrojCasova from predmet p left outer join predaje pr
on p.s_pred = pr.S_Pred group by p.S_Pred, naziv, mesto;

select d.s_dir, dd.prezime_ime, count(d.s_nas) BrojPodredjenih from nastavnik d, nastavnik dd where
d.S_Dir = dd.S_nas group by d.S_Dir, dd.Prezime_Ime;

 ------------- DOMACI
 select s.*, p.ocena, pr.naziv from student s, polaze p, predmet pr where s.s_stud = p.s_stud and p.s_pred= pr.S_Pred;

 select s.*, pr.Naziv, p.ocena from student s, predmet pr, polaze p where pr.s_pred = p.s_pred and s.s_stud = p.s_stud 
 and naziv = 'Strukture i bp' ;

 select naziv, cast(avg(ocena) as float) ProsecnaOcena from predmet pr left outer join polaze p on
 pr.s_pred = p.s_pred group by naziv;

 select s.s_stud, ime , prezime, max(ocena) from student s left outer join polaze p on s.s_stud = p.s_stud group by s.s_stud, ime, prezime ;
 
 select s.s_stud, ime, prezime, ocena, naziv from student s, polaze p, predmet pd, 
(select s_stud, max(ocena) maxOcena from polaze group by s_stud) x 
where s.s_stud = p.s_stud and p.s_pred = pd.s_pred and x.maxOcena = p.ocena and x.s_stud = p.s_stud;

 ----------------------------------------------------- Vezbe 7 --------------------------------------------------------------------
 --INSERT 
 insert into nastavnik(s_nas, prezime_ime, zvanje,s_dir, datzap, plata, dodatak)
values(110, 'Andjelic Andja', 'R PROF', null, '1984-03-02', 12000, null)

-- UPDATE
update nastavnik set dodatak = 1200 where zvanje = 'R Prof';

update nastavnik set plata = plata * 1.05, dodatak = null where zvanje = 'docent' and Prezime_Ime like '%P';
 
update nastavnik set dodatak = dodatak * 1.10 where s_nas in (select s_dir from nastavnik);

update nastavnik set s_dir = (select s_nas from nastavnik where prezime_ime = 'Radovic Nikola')
where Prezime_Ime = 'Savic Milan';

update nastavnik set dodatak = null where s_nas not in (select s_nas from predaje);

update nastavnik set dodatak = dodatak * 2 where s_nas in (select s_nas from predaje group by s_nas
having count(s_pred)> 1);

--DELETE
delete from nastavnik where s_nas = 110;

delete from nastavnik where plata >= all(select plata from Nastavnik);

--BEGIN TRANSACTION, COMMIT TRANSACTION, ROLLBACK TRANSACTION
begin transaction;
update nastavnik set plata = plata * 1.10 where zvanje = 'Docent';
update predaje set casova = casova + 2 where s_nas in (select s_nas from nastavnik where zvanje = 'Docent');
commit transaction;

 ------------- DOMACI
 update polaze set br_bodova = 51, ocena = 6 where s_stud in( select s_stud from student where ime = 'Javor'and
 prezime = 'Jelic') and s_pred in (select s_pred from predmet where naziv = 'Osnove Racunarstva');


 ----------------------------------------------------- Vezbe 8 --------------------------------------------------------------------
 create table profesor (
    s_nas       int		Primary Key,
    prezime_ime char(20)    	not null,   
    zvanje      char(6)     	not null,
    plata       money		not null,
    s_dir       int references profesor(s_nas),
    s_kat       int		not null)
    
create table katedra (
    s_kat       int		Primary Key,
    naziv_kat   char(45)    	not null)

create table projekat (
    s_pro       int		Primary Key,
    naziv_pro   char(45)    	not null,
    budzet 	money		not Null)
    
create table ucestvuje (
    s_pro       int	        not null,
    s_nas       int	        not null,
    SatiAngazovanja int		not null,
    IznosHonorara money		not null,
	Primary Key (s_pro,s_nas))
	
create table predmeti (
    s_pred      int		Primary Key,
    naziv_pred  char(60)    	not null,
    s_nas       int	        not null,
    Casova	int		not null)
    
create table studenti (
    br_ind      CHAR(6)	Primary Key,
    prezime     char(20)    not null,
    ime         char(20)    not null,
    godstud     char(3)     not null)
    
create table polozio (
    br_ind      char(6)     not null,
    s_pred      int	    not null,
    datum       datetime    not null,
    ocena       int	    not null,
	Primary Key (br_ind,s_pred))
	
Create Table Univerzitet
	(Unid		TinyInt		Primary Key,
	 Naziv		VarChar(30) Not Null,
	 Mesto		VarChar(25)
)

insert into profesor values
    ( 01, 'Radovic Nikola','R prof', 10500, null, 01)
insert into profesor values
    ( 02, 'Petrovic Petar','Docent', 11500, 01, 02)
insert into profesor values
    ( 05, 'Ilic Jovan','Docent', 11500, null, 06)
insert into profesor values
    ( 03, 'Peic Petar','Docent', 11500, 05, 04)
insert into profesor values
    ( 04, 'Simic Sima','Docent', 11500, 05, 05)
insert into profesor values
    ( 06, 'Savic Ilija','V prof', 12500, 01, 07)
insert into profesor values
    ( 07, 'Tot Ana','V prov', 12500, 01, 08)
insert into profesor values
    ( 08, 'Savic Milan','R prof', 13500, 01, 03)
insert into profesor values
    ( 09, 'Petric Janko','R prof', 13500, null, 01)
insert into profesor values
    ( 10, 'Petrovic Slavica','Docent', 11500, 01, 01)
    
insert into predmeti values
    ( 01, 'Informacioni sistemi i projektovanje baza podataka',10,2)
insert into predmeti values
    ( 02, 'Baze podataka', 01,4)
insert into predmeti values
    ( 03, 'Racunarske mreze', 01,4)
insert into predmeti values
    ( 04, 'Osnove baza podataka', 02,2)
insert into predmeti values
    ( 05, 'Arhitektura racunara', 03,4)
insert into predmeti values
    ( 06, 'Vestacka inteligencija', 01,2)
insert into predmeti values
    ( 07, 'Metode optimizacije', 04,2)
insert into predmeti values
    ( 08, 'Upravljanje procesima', 05,2)
insert into predmeti values
    ( 09, 'Merenja', 06,2)
insert into predmeti values
    ( 10, 'Arhitektura i algoritmi DSP', 07,2)
    
    
insert into katedra values
    ( 01, 'Katedra za racunarske nauke i informatiku')
insert into katedra values
    ( 02, 'Katedra za automatiku')
insert into katedra values
    ( 03, 'Katedra za elektroniku')
insert into katedra values
    ( 04, 'Katedra za fundamentalnu elektrotehniku')
insert into katedra values
    ( 05, 'Katedra za telekomunikacije')
insert into katedra values
    ( 06, 'Katedra za osnovne discipline')
insert into katedra values
    ( 07, 'Katedra za mehaniku i masinske konstrukcije')
insert into katedra values
    ( 08, 'Katedra za merenja')

insert into projekat values
    ( 01, 'IBIS',70000)
insert into projekat values
    ( 02, 'ISP',50000)    
insert into projekat values
    ( 03, 'SNTIS',120000)
insert into projekat values
    ( 04, 'AISOS',80000)   
insert into projekat values
    ( 05, 'IISVS',45000) 


insert into polozio values
    ( 'E 7398',1,'2001-01-11',9)
insert into polozio values
    ( 'E 7398',2,'1999-09-12',10)
insert into polozio values
    ( 'E 7398',3,'2001-06-11',10)
insert into polozio values
    ( 'E 7398',5,'2001-09-10',9)
insert into polozio values
    ( 'E 7398',6,'2001-10-10',10)
insert into polozio values
    ( 'E 7398',8,'2001-09-10',7)
insert into polozio values
    ( 'E 7398',10,'2001-09-10',6)
insert into polozio values
    ( 'E 7411',1,'2001-01-11',10)
insert into polozio values
    ( 'E 7411',2,'1999-09-12',8)
insert into polozio values
    ( 'E 7411',3,'2001-06-11',8)
insert into polozio values
    ( 'E 7411',5,'2001-09-10',9)
insert into polozio values
    ( 'E 7411',6,'2001-10-10',7)
insert into polozio values
    ( 'E 7411',8,'2001-09-10',7)
insert into polozio values
    ( 'E 8821',1,'2001-01-11',10)    
insert into polozio values
    ( 'E 8823',2,'1999-09-12',6)
insert into polozio values
    ( 'E 8823',3,'2001-06-11',6)
insert into polozio values
    ( 'E 8823',5,'2001-09-10',6)
insert into polozio values
    ( 'E 8911',2,'1999-09-12',6)
insert into polozio values
    ( 'E 8913',3,'2001-06-11',6)
insert into polozio values
    ( 'E 8917',5,'2001-09-10',6)
insert into polozio values
    ( 'E 8925',2,'1999-01-12',6)
insert into polozio values
    ( 'E 8911',3,'2001-06-11',6)
insert into polozio values
    ( 'E 8823',6,'2001-09-10',6)
insert into polozio values
    ( 'E 8811',2,'1999-09-12',6)
insert into polozio values
    ( 'E 8819',3,'2001-06-11',6)
insert into polozio values
    ( 'E 7399',5,'2001-09-10',6)
insert into polozio values
    ( 'E 8921',2,'1999-09-12',6)
insert into polozio values
    ( 'E 8921',3,'1999-06-11',6)
insert into polozio values
    ( 'E 8917',6,'2001-09-10',6)
insert into polozio values
    ( 'E 9124',1,'2012-09-12',9)
insert into polozio values
    ( 'E 9124',3,'2012-06-11',9)
insert into polozio values
    ( 'E 9124',4,'2012-09-10',10)
    

insert into studenti values
    ( 'E 9044','Jekic','Branislav', 'I')
insert into studenti values
    ( 'E 9041','Secerov','Emil','I')
insert into studenti values
    ( 'E 9046','Mirkov','Branislav','I')
insert into studenti values
    ( 'E 9014','Santrac','Dragan','I')
insert into studenti values
    ( 'E 9124','Sataric','Zvezdan','I')
insert into studenti values
    ( 'E 8911','Kapor','Marica','II')
insert into studenti values
    ( 'E 8913','Seskar','Ivan','II')
insert into studenti values
    ( 'E 8917','Tomic','Mitar','II')
insert into studenti values
    ( 'E 8921','Latinovic','Slobodan','III')
insert into studenti values
    ( 'E 8925','Panjkovic','Vladimir','III')
insert into studenti values
    ( 'E 8912','Rodic','Branislav','III')
insert into studenti values
    ( 'E 8811','Radovanovic','Svetlana','IV')
insert into studenti values
    ( 'E 8819','Vujasinovic','Dragoslav','IV')
insert into studenti values
    ( 'E 8821','Djordjevic','Slobodan','IV')
insert into studenti values
    ( 'E 8823','Berta','Arpad','IV')
insert into studenti values
    ( 'E 7411','Licina','Djuro','V')
insert into studenti values
    ( 'E 7398','Trifunovic','Zoran','V')
insert into studenti values
    ( 'E 7399','Ozvar','Arpad','V')
    

insert into ucestvuje values 
    (1, 1,5,3000)
insert into ucestvuje values 
    (1, 9,4,1200)
insert into ucestvuje values 
    (1, 10,8,5000)
insert into ucestvuje values 
    (2, 1,4,3000)
insert into ucestvuje values 
    (2, 9,8,7000)
insert into ucestvuje values 
    (2, 10,10,8000)
insert into ucestvuje values 
    (3, 3,16,10000)
insert into ucestvuje values 
    (3, 8,6,5000)
insert into ucestvuje values 
    (3, 2,3,2000)
insert into ucestvuje values 
    (4, 5,10,7000)
insert into ucestvuje values 
    (5, 4,2,1500)
insert into ucestvuje values 
    (4, 6,4,3500)
insert into ucestvuje values 
    (4, 7,8,4000)
insert into ucestvuje values 
    (4, 8,12,9000)
insert into ucestvuje values 
    (3, 1,14,8000)
insert into ucestvuje values 
    (3, 9,20,15000)
insert into ucestvuje values 
    (4, 10,10,5500)    
    
Insert Into Univerzitet Values
	(1,'Univerzitet u Novom Sadu', 'Novi Sad')
    
 select * from katedra
 select * from  profesor
 select * from   projekat
 select * from   ucestvuje
 select * from   predmeti
 select * from   studenti
 select * from  polozio 
     
--a
select br_ind, prezime, ime, godstud from studenti where godstud='I' order by prezime, ime asc;

--b
select * from studenti order by godstud, prezime, ime;

--c
select prezime, ime from studenti where prezime like '__t%';

--d
select prezime_ime, zvanje from nastavnik where s_dir is null;

--e
 select s_pro, naziv_pro, budzet from projekat where s_pro in (select s_pro from ucestvuje where 
 s_nas in(select s_nas from profesor where s_nas=2 or s_nas = 4 or s_nas = 6) );
  select s_pro, naziv_pro, budzet from projekat where s_pro in(select s_pro from ucestvuje where s_nas in (2,4,6));
  select p.s_pro, naziv_pro, budzet from projekat p, ucestvuje u where p.s_pro = u.s_pro and s_nas in (2,4,6);

--f
insert into profesor (Prezime_Ime, zvanje, s_nas, plata, s_kat, s_dir) values ('Savic Goran', 'Docent', 11, 1500, 1, 4);

-- UPDATE     update nastavnik set plata = 11500 where s_nas = 1;

--g 
select s_nas, prezime_ime, zvanje from profesor where (zvanje = 'Docent' or zvanje ='V prof') and  s_nas not in (select s_nas from predmeti);
select s_nas, prezime_ime, zvanje from profesor where s_nas not in (select s_nas from predmeti) and zvanje in ('DOCENT', 'V PROF');

--h
select prezime_ime, zvanje, count(p.s_nas)  from profesor pr, predmeti p where (pr.s_nas = p.s_nas) group by prezime_ime, zvanje order by count(p.s_nas) desc;
select prezime_ime, zvanje, count(s_pred) from profesor n, predmeti p where n.s_nas = p.s_nas group by prezime_ime, zvanje order by count(s_pred) desc;

--i
select p.s_pred, p.naziv_pred, pr.prezime_ime , pr.zvanje from predmeti p, profesor pr where (p.s_nas = pr.s_nas) ;

--j
select s.br_ind, s.prezime, s.ime, p.ocena from studenti s, polozio p where (s.br_ind = p.br_ind) and (p.s_pred in 
(select s_pred from predmeti where naziv_pred like 'Osnove baza podataka'));

------------------------------------------------ tezi
--k
select n.s_nas, n.prezime_ime, n.s_kat, count(o.s_nas)-1 from profesor n, 
profesor o where n.s_kat = o.s_kat group by  n.s_nas, n.prezime_ime, n.s_kat;

--l
select k.s_kat, naziv_kat, count(s_nas) BrojNastavnika 
from katedra k, profesor p where k.s_kat = p.s_kat
group by k.s_kat, naziv_kat having count(s_nas)>= 2 ;

--m
select n.s_nas,n.prezime_ime,n.plata,d.s_nas,d.prezime_ime,d.plata,k.naziv_kat 
from nastavnik n,nastavnik d,katedra k
where (n.s_dir=d.s_nas) and (d.s_kat=k.s_kat);

--n
select s_kat, naziv_kat from katedra where 
s_kat in (select s_nas from profesor where s_nas in 
(select s_pred from predmeti where s_pred in 
(select s_pred from polozio where br_ind in (select
br_ind from studenti))) );

--o
select p.s_pred, naziv_pred, count(br_ind)  from predmeti p, 
 polozio po where p.s_pred = po.s_pred 
  group by p.s_pred, naziv_pred having count(br_ind) > 4 ;

 --za svakog = OUTER JOIN 
--p
 select s.br_ind, ime, prezime, count(p.s_pred) BrojPredmeta, isnull(avg(p.ocena), -1) 
  ProsecnaOcena from studenti s
  left outer join polozio p on s.br_ind = p.br_ind group by s.br_ind, ime, prezime having
  count(p.s_pred)=0 or avg(ocena)<8
 
--q
select s.br_ind, ime, prezime , isnull(p.s_pred, -1), isnull(naziv_pred, 'nije polagao')
from (studenti s left outer join polozio o on s.br_ind = o.br_ind ) 
left outer join predmeti p on o.s_pred = p.s_pred;

 --r  
select naziv_pro,budzet,  p.s_pro, count (s_nas) BrNastavnika,
cast(avg(satiAngazovanja) as float) prosekAngazovanje 
from ucestvuje u, projekat p where u.s_pro = p.s_pro and u.s_nas in 
(select p.s_nas from profesor p where p.s_nas in (select s_kat from katedra where 
naziv_kat like 'Katedra za elektroniku')) group by p.s_pro, naziv_pro,budzet ;