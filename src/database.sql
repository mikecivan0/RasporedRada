drop database if exists raspored;
create database raspored character set=utf8 collate=utf8_general_ci;
use raspored;

CREATE TABLE IF NOT EXISTS osobe (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  ime VARCHAR(25) NOT NULL,
  prezime VARCHAR (25) NOT NULL,
  adresa VARCHAR(100),
  telefon VARCHAR(20),
  email VARCHAR(50) 
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS korisnici (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  osobaId int NOT NULL,
  korisnickoIme VARCHAR(50) NOT NULL,
  loznika VARCHAR(100) NOT NULL,
  osobniBroj VARCHAR(10) NOT NULL,
  razina TINYINT NOT NULL,
  aktivan BOOLEAN NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS redovnaRadnaVremena (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  vrijediOd DATE NOT NULL,
  vrijdiDo DATE NOT NULL,
  ponedjeljakOd TIME NOT NULL,
  ponedjeljakDo TIME NOT NULL,
  utorakOd TIME NOT NULL,
  utorakDo TIME NOT NULL,
  srijedaOd TIME NOT NULL,
  srijedaDo TIME NOT NULL,
  cetvrtakOd TIME NOT NULL,
  cetvrtakDo TIME NOT NULL,
  petakOd TIME NOT NULL,
  petakDo TIME NOT NULL,
  subotaOd TIME NOT NULL,
  subotaDo TIME NOT NULL,
  nedjeljaOd TIME NOT NULL,
  nedjeljaDo TIME NOT NULL,
  trajanjePauzeUMinutama int(3) NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS iznimnaRadnaVremena (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  datum DATE NOT NULL,
  pocetak TIME NOT NULL,
  kraj TIME NOT NULL,
  pauza int(3) NOT NULL,
  napomena VARCHAR(50) NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS brojRadnikaPoDanima (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  vrijdiOd DATE NOT NULL,
  vrijdiDo DATE NOT NULL,
  ponedjeljak TINYINT NOT NULL,
  utorak TINYINT NOT NULL,  
  srijeda TINYINT NOT NULL,
  cetvrtak TINYINT NOT NULL,
  petak TINYINT NOT NULL,
  subota TINYINT NOT NULL,
  nedjelja TINYINT NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS oznakeUnosaURaspored (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  naziv VARCHAR(30) NOT NULL,
  skracenica VARCHAR(3) NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE IF NOT EXISTS raspored (
  id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  korisnikId int NOT NULL,
  oznakaUnosaURasporedId int NOT NULL,
  datum DATE NOT NULL,
  radSaPauzom TINYINT(1) NOT NULL default 0
) ENGINE=InnoDB CHARSET=utf8;

alter table korisnici add foreign key(osobaId) references osobe(id);

alter table raspored add foreign key(korisnikId) references korisnici(id);
alter table raspored add foreign key(oznakaUnosaURasporedId) references oznakeUnosaURaspored(id);
