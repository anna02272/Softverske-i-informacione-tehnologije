# Pod pretpostavkom da je pravilno instaliran MySQL server potrebno je u command prompt-u izvrsiti sledecu komandu
#
# 	<MySQL Install Dir>/bin/mysql -u root -p < create.sql
#
# Kada vam klijent zatrazi lozinku upisite onu koju ste definisali tokom postupka instalacije.
# Ukoliko ste menjali korisnika MySQL sistema upotrebite umesto root-a odgovarajuce korisnicko ime i lozinku.

CREATE DATABASE osa DEFAULT CHARACTER SET utf8;
USE osa;

CREATE TABLE nastavnici
(
   nastavnikId	 INTEGER      NOT NULL,
   ime            VARCHAR(25)  NOT NULL,
   prezime        VARCHAR(35)  NOT NULL,
   zvanje         VARCHAR(15)  NOT NULL,
   PRIMARY KEY (nastavnikId)
);

CREATE TABLE predmeti
(
   predmetId   INTEGER      NOT NULL,
   naziv       VARCHAR(150) NOT NULL,
   PRIMARY KEY (predmetId)
);

CREATE TABLE predaje
(
   nastavnikId INTEGER NOT NULL,
   predmetId   INTEGER NOT NULL,
   PRIMARY KEY (nastavnikId, predmetId)
);

ALTER TABLE predaje ADD CONSTRAINT fk_predaje_nastavnik FOREIGN KEY (nastavnikId)
      REFERENCES nastavnici (nastavnikId) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE predaje ADD CONSTRAINT fk_predaje_predmet FOREIGN KEY (predmetId)
      REFERENCES predmeti (predmetId) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO nastavnici VALUES(1,'Petar','Petrovic','docent');
INSERT INTO predmeti VALUES(1,'Matematika');

DELIMITER //
CREATE FUNCTION povezi(
		ime_ VARCHAR(25),
		prezime_ VARCHAR(35),
		naziv_ VARCHAR(150))
RETURNS INTEGER
BEGIN
		DECLARE nasID INTEGER;
		DECLARE predID INTEGER;

		DECLARE EXIT HANDLER FOR NOT FOUND RETURN 0;
		DECLARE EXIT HANDLER FOR SQLSTATE '23000' RETURN 0;

		SELECT nastavnikId INTO nasID FROM nastavnici WHERE ime = ime_ AND prezime = prezime_;
		SELECT predmetId INTO predID FROM predmeti WHERE naziv = naziv_;
		INSERT INTO predaje (nastavnikId, predmetId) VALUES (nasId, predID);

		RETURN 1;
END;
//

DELIMITER ;
