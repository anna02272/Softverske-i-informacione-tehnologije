
Create Table TipSobe
	(TsId		TinyInt Primary Key,
	 BrKreveta	TinyInt,
	 NazivTipaSobe	VarChar(20)
	
) On Sobe
Create Table Soba
	(BrSobe		int Primary Key,
	 MiniBar	TinyInt,  /* 1 - Ima Mini bar; 0 - Nema mini bar */
	 Tv		TinyInt,  /* 1 - Ima Tv; 0 - Nema Tv */
	 TsId		tinyInt Foreign Key
		References TipSobe(TsId)
) On Sobe
Create Table TipIznajmljivanja 
	(TiId		TinyInt Primary Key,
	 NazivTipaIznajmljivanja varChar(20)
) On Cene
Create Table Cenovnik
	(TsId		TinyInt Foreign Key
		References TipSobe(TsId),
	 TiId		TinyInt Foreign Key
		References TipIznajmljivanja(TiId),
	 Cena	Money,
	 Primary Key (TsId, TiId)
) On Cene
Create Table Gost
	(Gid		TinyInt Primary Key,
	 Prezime	VarChar(18),
	 Ime 		VarChar(18),
	 BrojLk		VarChar(12)
)
Create Table Iznajmljivanje
	(Gid		TinyInt Foreign Key
		References Gost(Gid),
	 IzId		TinyInt,
	 BrSobe		int Foreign Key
		References Soba(BrSobe),
	 TiId		TinyInt Foreign Key
		References TipIznajmljivanja(TiId),	
	 DatVremeP	SmallDateTime,
	 DatVremeZ	SmallDateTime,
	 BrNocenja	TinyInt,
	 Primary Key (Gid, IzId)
) On Posete	 