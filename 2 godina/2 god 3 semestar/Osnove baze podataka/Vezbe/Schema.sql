Drop Table Predaje
Drop Table Predmet
Drop Table Nastavnik

Create Table Nastavnik
	(S_Nas		SmallInt Primary Key,
	 Prezime_Ime		Varchar(25) Not Null,
	 Zvanje		Char(6),
	 S_Dir		SmallInt,
	 DatZap		SmallDateTime Not Null,
	 Plata			Money Not Null,
	 Dodatak		Money)

-- Linijski komentar 

Create Table Predmet
	(S_Pred		SmallInt Primary Key,
	 Naziv		VarChar(35) Not Null,
	 Mesto    		VarChar(25) Not Null,
	 Semestar		SmallInt Not Null)
/*   Blok komentar   */
Create Table Predaje
	(S_Nas		SmallInt 
		Foreign Key References Nastavnik(S_Nas),
	 S_Pred		SmallInt
		Foreign Key references Predmet(S_Pred),
	 Casova		SmallInt Not Null,
	 Primary Key (S_Nas, S_Pred))
