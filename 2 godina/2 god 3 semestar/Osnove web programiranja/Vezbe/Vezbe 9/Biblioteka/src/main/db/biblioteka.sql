DROP SCHEMA IF EXISTS biblioteka;
CREATE SCHEMA biblioteka DEFAULT CHARACTER SET utf8;
USE biblioteka;

CREATE TABLE korisnici (
	id BIGINT AUTO_INCREMENT,
	ime VARCHAR(20),
	prezime VARCHAR(20),
	email VARCHAR(20) NOT NULL,
	lozinka VARCHAR(20) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE knjige (
	id BIGINT AUTO_INCREMENT,
	naziv VARCHAR(75) NOT NULL,
	registarskiBrojPrimerka VARCHAR(75) NOT NULL,
	jezik VARCHAR(75) NOT NULL,
	brojStranica INT NOT NULL,
	izdata BOOLEAN NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE clanskeKarte (
	id BIGINT AUTO_INCREMENT,
	registarskiBroj VARCHAR(25) NOT NULL,
	korisnikId BIGINT NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(korisnikId) REFERENCES korisnici(id)
		ON DELETE CASCADE
);

CREATE TABLE knjigeClanskeKarte (
    knjigaId BIGINT,
    clanskaKartaId BIGINT,
    PRIMARY KEY(knjigaId, clanskaKartaId),
    FOREIGN KEY(knjigaId) REFERENCES knjige(id)
		ON DELETE CASCADE,
    FOREIGN KEY(clanskaKartaId) REFERENCES clanskeKarte(id)
		ON DELETE CASCADE
);

INSERT INTO korisnici (ime, prezime, email, lozinka) VALUES ('Pera', 'Peric', 'pera@mail.com', 'pera');
INSERT INTO korisnici (ime, prezime, email, lozinka) VALUES ('Mika', 'Mikic', 'mika@mail.com', 'mika');
INSERT INTO korisnici (ime, prezime, email, lozinka) VALUES ('Jova', 'Jovic', 'jova@mail.com', 'jova');

INSERT INTO knjige (naziv, registarskiBrojPrimerka, jezik, brojStranica, izdata) VALUES ('Inferno', '456/785', 'srpski', 496, false);
INSERT INTO knjige (naziv, registarskiBrojPrimerka, jezik, brojStranica, izdata) VALUES ('To kill a mockingbird', '478/956', 'engleski', 320, false);
INSERT INTO knjige (naziv, registarskiBrojPrimerka, jezik, brojStranica, izdata) VALUES ('Ostrvo', '985/784', 'srpski', 473, false);

INSERT INTO clanskeKarte (registarskiBroj, korisnikId) VALUES ('455/456', 1);
INSERT INTO clanskeKarte (registarskiBroj, korisnikId) VALUES ('456/456', 2);


