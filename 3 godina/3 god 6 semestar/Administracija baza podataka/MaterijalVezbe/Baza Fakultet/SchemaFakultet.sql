Drop Table IF EXISTS Polozio
Drop Table IF EXISTS Ucestvuje
Drop Table IF EXISTS Predmet
Drop Table IF EXISTS Nastavnik
Drop Table IF EXISTS Katedra
Drop Table IF EXISTS Projekat
Drop Table IF EXISTS Student

Create Table Katedra (
    S_Kat       Int		Primary Key,
    Naziv_Kat   Char(45)    Not Null
)

Create Table Nastavnik (
    S_Nas       Int		Primary Key,
    Prezime_Ime VarChar(20) Not Null,   
    Zvanje      Char(6)     Not Null,
    Plata       Money		Not Null,
    S_Dir       Int,
    S_Kat       Int		Foreign Key
	    References Katedra(S_Kat)
)
    
Create Table Projekat (
    S_Pro       Int		Primary Key,
    Naziv_Pro   Char(45)    Not Null,
    Budzet 		Money		Not Null
)
    
Create Table Ucestvuje (
    S_Pro       Int	Not Null Foreign Key
	    References Projekat(S_Pro),
    S_Nas		Int	Not Null Foreign Key
	    References Nastavnik(S_Nas),
    SatiAngazovanja Int		Not Null,
    IznosHonorara Money		Not Null,
	Primary Key (S_Pro, S_Nas)
)

Create Table Predmet (
    S_Pred      Int		Primary Key,
    Naziv_Pred  VarChar(60) Not Null,
    S_Nas       Int Not Null Foreign Key
	    References Nastavnik(S_Nas),
    Casova		Int			Not Null
)
    
Create Table Student (
    Br_Ind      Char(6)	Primary Key,
    Prezime     VarChar(20) Not Null,
    Ime         VarChar(20) Not Null,
    GodStud     Char(3)     Not Null
)
    
Create Table Polozio (
    Br_Ind      Char(6) Not Null Foreign Key
	    References Student(Br_Ind),
    S_Pred      Int	    Not Null Foreign Key
	    References Predmet(S_Pred),
    Datum       DateTime    Not Null,
    Ocena       Int	        Not Null,
	Primary Key (Br_Ind, S_Pred)
)

