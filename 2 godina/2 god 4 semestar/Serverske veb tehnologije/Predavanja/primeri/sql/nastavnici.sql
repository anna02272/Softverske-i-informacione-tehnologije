create table NASTAVNICI
(
    NASTAVNIK_ID  INTEGER                not null,
    IME           VARCHAR(25)           not null,
    PREZIME       VARCHAR(35)           not null,
    ZVANJE        VARCHAR(15)           not null,
    primary key (NASTAVNIK_ID)
) ENGINE = InnoDB;

create table PREDMETI
(
    PREDMET_ID    INTEGER                not null,
    NAZIV         VARCHAR(150)          not null,
    primary key (PREDMET_ID)
) ENGINE = InnoDB;

create table PREDAJE
(
    PREDMET_ID    INTEGER                not null,
    NASTAVNIK_ID  INTEGER                not null,
    primary key (PREDMET_ID, NASTAVNIK_ID),
    foreign key (PREDMET_ID) references PREDMETI (PREDMET_ID) on delete cascade,
    foreign key (NASTAVNIK_ID) references NASTAVNICI (NASTAVNIK_ID) on delete cascade
) ENGINE = InnoDB;

insert into NASTAVNICI (nastavnik_id, ime, prezime, zvanje) values
  (1, 'Marko', 'Markovic', 'docent');
insert into NASTAVNICI (nastavnik_id, ime, prezime, zvanje) values
  (2, 'Petar', 'Petrovic', 'redovni');
insert into NASTAVNICI (nastavnik_id, ime, prezime, zvanje) values
  (3, 'Nikola', 'Nikolic', 'docent');

insert into PREDMETI (predmet_id, naziv) values
  (1, 'Osnovi racunarstva');
insert into PREDMETI (predmet_id, naziv) values
  (2, 'Programiranje i programski jezici');
insert into PREDMETI (predmet_id, naziv) values
  (3, 'Operativni sistemi');

insert into PREDAJE (nastavnik_id, predmet_id) values
  (1, 1);
insert into PREDAJE (nastavnik_id, predmet_id) values
  (2, 2);
insert into PREDAJE (nastavnik_id, predmet_id) values
  (2, 3);
insert into PREDAJE (nastavnik_id, predmet_id) values
  (3, 1);
insert into PREDAJE (nastavnik_id, predmet_id) values
  (3, 3);
