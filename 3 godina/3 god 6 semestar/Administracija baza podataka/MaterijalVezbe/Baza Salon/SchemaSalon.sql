
Create Table TipNamestaja
	(TnId		TinyInt Primary Key,
	 NazivTipaNamestaja VarChar(30)
) 
Create Table Komad
	(KnId		TinyInt	Primary Key,
	 Naziv		Varchar(25),
	 Sifra		Varchar(10),
	 Cena		Money,
	 TnId		TinyInt Foreign Key
		References TipNamestaja(TnId)
) 
Create Table Akcija
	(AkId		TinyInt	Primary Key,
	 DatPocetka 	SmallDatetime,
	 DatZavrsetka 	SmallDateTime,
	 NazivAkcije	VarChar(25)
)
Create Table NaAkciji
	(AkId		TinyInt Foreign Key
		References Akcija(AkId),
	 KnId		TinyInt Foreign Key
		References Komad(knId),
	 Popust		Decimal(6,2),
	 Primary Key (AkId, KnId)
)
Create Table Kupac
	(KId		TinyInt Primary Key,
	 Prezime	VarChar(18) Not Null,
	 Ime		VarChar(18) Not Null
) 
Create Table Kupovina
	(KpId		TinyInt Primary Key,
	 BrRacuna	VarChar(10),
	 Datum		SmallDateTime,
	 KId		TinyInt Foreign Key
		References Kupac(KId)
) 
Create Table Stavke
	(KpId		TinyInt Foreign Key
		References Kupovina(KpId),
	 KnId		TinyInt Foreign Key
		References Komad(KnId),
	 Kolicina	TinyInt,
	 Primary Key (KpId, KnId)
) 