/*
drop table Univerzitet;
drop table polozio;
drop table student;
drop table predmet;
drop table ucestvuje;
drop table projekat;
drop table katedra;
drop table nastavnik;
*/

create table nastavnik (
    s_nas       int		Primary Key,
    prezime_ime char(20)    	not null,   
    zvanje      char(6)     	not null,
    plata       money		not null,
    s_dir       int references nastavnik(s_nas),
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
	
create table predmet (
    s_pred      int		Primary Key,
    naziv_pred  char(60)    	not null,
    s_nas       int	        not null,
    Casova	int		not null)
    
create table student (
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

