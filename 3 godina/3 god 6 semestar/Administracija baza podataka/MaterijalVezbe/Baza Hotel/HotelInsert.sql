

Insert Into TipSobe 
	Values (1, 1,'Jednokrevetna Soba')
Insert Into TipSobe 
	Values (2, 2,'Dvokrevetna Soba')
Insert Into TipSobe 
	Values (3, 3,'Trokrevetna Soba')

Insert Into Soba
	Values (101, 1, 1, 1)
Insert Into Soba
	Values (102, 1, 1, 1)
Insert Into Soba
	Values (103, 1, 1, 1)
Insert Into Soba
	Values (201, 1, 1, 2)
Insert Into Soba
	Values (202, 1, 1, 2)
Insert Into Soba
	Values (301, 1, 1, 3)
Insert Into Soba
	Values (302, 1, 1, 3)

Insert Into TipIznajmljivanja
	Values (1, 'Dnevni boravak')
Insert Into TipIznajmljivanja
	Values (2, 'Nocenje')
Insert Into TipIznajmljivanja
	Values (3, 'Nocenje s doruckom')
Insert Into TipIznajmljivanja
	Values (4, 'Polu pansion')
Insert Into TipIznajmljivanja
	Values (5, 'Pansion')

Insert Into Cenovnik
	Values ( 1, 1, 1000)
Insert Into Cenovnik
	Values ( 1, 2, 1500)
Insert Into Cenovnik
	Values ( 1, 3, 1800)
Insert Into Cenovnik
	Values ( 1, 4, 2200)
Insert Into Cenovnik
	Values ( 1, 5, 2600)
Insert Into Cenovnik
	Values ( 2, 1, 1300)
Insert Into Cenovnik
	Values ( 2, 2, 1800)
Insert Into Cenovnik
	Values ( 2, 3, 2100)
Insert Into Cenovnik
	Values ( 2, 4, 2500)
Insert Into Cenovnik
	Values ( 2, 5, 3000)
Insert Into Cenovnik
	Values ( 3, 1, 1800)
Insert Into Cenovnik
	Values ( 3, 2, 2100)
Insert Into Cenovnik
	Values ( 3, 3, 2600)
Insert Into Cenovnik
	Values ( 3, 4, 3200)
Insert Into Cenovnik
	Values ( 3, 5, 3800)

Insert Into Gost
	Values ( 1, 'Nikolic', 'Dragan', 'CX 12060')
Insert Into Gost
	Values ( 2, 'Stankovic', 'Slobodan', 'CX 12061')
Insert Into Gost
	Values ( 3, 'Markovic', 'Goran', 'CX 12063')
Insert Into Gost
	Values ( 4, 'Bil', 'Klinton', 'CX 12064')
Insert Into Gost
	Values ( 5, 'Merkel', 'Angela', 'DE 10066')
Insert Into Gost
	Values ( 6, 'Messi', 'Lionel', 'CX 12060')
Insert Into Gost
	Values ( 7, 'Divac', 'Vlade', 'SR 12067')
Insert Into Gost
	Values ( 8, 'Danilovic', 'Predrag', 'CG 12070')
Insert Into Gost
	Values ( 9, 'Kukoc', 'Toni', 'CR 12080')
Insert Into Gost
	Values ( 10, 'Maradona', 'Diego', 'CX 12060')

Insert Into Iznajmljivanje
	Values (1, 1, 101,2, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (2, 2, 102,2, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (3, 3, 103,3, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (4, 4, 201,3, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (5, 5, 202,3, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (6, 6, 301,4, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (7, 7, 302,4, '2010-01-10', '2010-01-11',1)
Insert Into Iznajmljivanje
	Values (8, 8, 101,4, '2010-01-15', '2010-01-17',2)
Insert Into Iznajmljivanje
	Values (10, 9, 102,3, '2010-01-15', '2010-01-17',2)

Insert Into Iznajmljivanje
	Values (1, 10, 101,3, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (2, 11, 102,3, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (3, 12, 103,3, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (4, 13, 201,3, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (5, 14, 202,4, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (6, 15, 301,4, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (7, 16, 302,4, '2010-02-10', '2010-02-11',1)
Insert Into Iznajmljivanje
	Values (8, 17, 101,4, '2010-02-15', '2010-02-17',2)
Insert Into Iznajmljivanje
	Values (10, 18, 102,4,'2010-02-15', '2010-02-17',2)

Insert Into Iznajmljivanje
	Values (5, 19, 202,2, '2010-02-20', '2010-02-22',2)
Insert Into Iznajmljivanje
	Values (6, 20, 301,2, '2010-02-20', '2010-02-22',2)
Insert Into Iznajmljivanje
	Values (7, 21, 302,4, '2010-02-20', '2010-02-22',2)
Insert Into Iznajmljivanje
	Values (8, 22, 101,4, '2010-02-20', '2010-02-22',2)
Insert Into Iznajmljivanje
	Values (10, 23, 102,4,'2010-02-20', '2010-02-22',2)

Insert Into Iznajmljivanje
	Values (7, 24, 302,3, '2010-03-20', '2010-03-22',2)
Insert Into Iznajmljivanje
	Values (8, 25, 101,2, '2010-03-20', '2010-03-22',2)
Insert Into Iznajmljivanje
	Values (10, 26, 102,4,'2010-03-20', '2010-03-22',2)

Insert Into Iznajmljivanje
	Values (8, 27, 101,2, '2010-03-10', '2010-03-12',2)
Insert Into Iznajmljivanje
	Values (10, 27, 102,3,'2010-03-10', '2010-03-12',2)

Insert Into Iznajmljivanje
	Values (8, 28, 101,2, '2010-03-15', '2010-03-17',2)

Insert Into Iznajmljivanje
	Values (10, 29, 101,4, '2010-04-15', '2010-04-17',2)

Insert Into Iznajmljivanje
	Values (10, 30, 101,4,'2010-05-15', '2010-05-17',2)

Insert Into Iznajmljivanje
	Values (7, 31, 302,2, '2010-06-20', '2010-06-22',2)
Insert Into Iznajmljivanje
	Values (8, 32, 101,2, '2010-06-20', '2010-06-22',2)
