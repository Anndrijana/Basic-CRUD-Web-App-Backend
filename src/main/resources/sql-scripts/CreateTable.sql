DROP TABLE IF EXISTS proizvodjac CASCADE;
DROP TABLE IF EXISTS proizvod CASCADE;
DROP TABLE IF EXISTS stavka_racuna CASCADE;
DROP TABLE IF EXISTS racun CASCADE;

DROP SEQUENCE IF EXISTS proizvodjac_seq;
DROP SEQUENCE IF EXISTS proizvod_seq;
DROP SEQUENCE IF EXISTS stavka_racuna_seq;
DROP SEQUENCE IF EXISTS racun_seq;

CREATE TABLE proizvodjac (
    id integer not null,
    naziv varchar(50) not null,
    adresa varchar(200) not null,
    kontakt varchar(100) not null
);

CREATE TABLE proizvod (
    id integer not null,
    naziv varchar(50) not null,
    proizvodjac integer not null
);

CREATE TABLE stavka_racuna (
    id integer not null,
    redni_broj integer not null,
    kolicina integer not null,
    jedinica_mere varchar(50) not null,
    cena numeric not null,
    racun integer not null,
    proizvod integer not null
);

CREATE TABLE racun (
    id integer not null,
    datum date not null,
    nacin_placanja varchar(200)
);

ALTER TABLE proizvodjac ADD CONSTRAINT pk_proizvodjac PRIMARY KEY(id);
ALTER TABLE proizvod ADD CONSTRAINT pk_proizvod PRIMARY KEY(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT pk_stavka_racuna PRIMARY KEY(id);
ALTER TABLE racun ADD CONSTRAINT pk_racun PRIMARY KEY(id);

ALTER TABLE proizvod ADD CONSTRAINT fk_proizvod_proizvodjac FOREIGN KEY(proizvodjac) REFERENCES proizvodjac(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT fk_stavka_racuna_racun FOREIGN KEY(racun) REFERENCES racun(id);
ALTER TABLE stavka_racuna ADD CONSTRAINT fk_stavka_racuna_proizvod FOREIGN KEY(proizvod) REFERENCES proizvod(id);

CREATE INDEX idxfk_proizvod_proizvodjac ON proizvod(proizvodjac);
CREATE INDEX idxfk_stavka_racuna_racun ON stavka_racuna(racun);
CREATE INDEX idxfk_stavka_racuna_proizvod ON stavka_racuna(proizvod);

CREATE SEQUENCE proizvodjac_seq INCREMENT 1;
CREATE SEQUENCE proizvod_seq INCREMENT 1;
CREATE SEQUENCE stavka_racuna_seq INCREMENT 1;
CREATE SEQUENCE racun_seq INCREMENT 1;