-- proizvodjac
INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'FRIKOM', 'Zrenjaninski put, Beograd', '011 2074100');

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'AD Imlek', 'Industrijsko naselje bb, Zrenjaninski put, Padinska Skela 11213', '011 3050505');

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Knjaz Milos a.d.', 'Juzna industrijska zona bb, Arandjelovac', '+381 34 700 700');

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Dr. Oetker d.o.o.', 'Vuka Karadzica 13, Simanovci 22310', '+381 (0) 22 800 300');

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (nextval('proizvodjac_seq'), 'Soko Stark d.o.o. Beograd', 'Bulevar Peka Dapcevica 29, Beograd 11000', '+381 11 39 56 000');

INSERT INTO "proizvodjac"("id", "naziv", "adresa", "kontakt")
VALUES (-100, 'Bambi', 'Djure Djakovica bb', '+381 12 539800');

-- proizvod
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Smrznuti grasak Frikom 450g', 1);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Smrznuti pomfrit Frikom 1kg', 1);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Moja Kravica sveze mleko 1,5% mm 1,5l', 2);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Moja Kravica kisela pavlaka 20% mm 180g', 2);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Aqua Viva 1,5l PET', 3);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'TUBE ORANGE 0.5L PET', 3);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Ovsene pahuljice Dr. Oetker 250g', 4);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Puding vanila Dr.Oetker 38g', 4);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Stark coko kolutici 150g', 5);
INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (nextval('proizvod_seq'), 'Cokol.banana Stark Bananica 25g', 5);

INSERT INTO "proizvod"("id", "naziv", "proizvodjac")
VALUES (-100, 'Napolitanke 100g', 5);

-- racun
INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('05.01.2020.','dd.mm.yyyy.'), 'gotovina');

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('12.03.2020.','dd.mm.yyyy.'), 'platna kartica');

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('21.02.2020.','dd.mm.yyyy.'), 'platna kartica');

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('18.04.2020.','dd.mm.yyyy.'), 'gotovina');

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (nextval('racun_seq'), to_date('09.03.2020.','dd.mm.yyyy.'), 'gotovina');

INSERT INTO "racun"("id", "datum", "nacin_placanja")
VALUES (-100, to_date('05.04.2020.', 'dd.mm.yyyy.'), 'gotovina');
           
-- stavka_racuna           
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 1, 'komad', 250, 1, 1);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 3, 'komad', 100, 1, 3);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 1, 'komad', 50, 1, 4);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 5, 'komad', 95, 2, 6);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 20, 'komad', 60, 2, 8);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 4, 'komad', 130, 3, 9);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 10, 'komad', 95, 3, 2);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 40, 'komad', 15, 3, 10);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 15, 'komad', 45, 4, 5);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 5, 'komad', 100, 4, 7);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 1, 5, 'komad', 15, 5, 10);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 2, 3, 'komad', 45, 5, 5);
INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (nextval('stavka_racuna_seq'), 3, 11, 'komad', 60, 5, 8);

INSERT INTO "stavka_racuna"("id", "redni_broj", "kolicina", "jedinica_mere", "cena", "racun", "proizvod")
VALUES (-100, 1, 10, 'komad', 60, 5, 8);
