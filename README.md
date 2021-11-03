# Raspored rada console
Konzolna aplikacija

Konzolna aplikacija za vođenje rasporeda rada


Rad sa aplikacijom je podijeljen na javni i korisnički dio. Korisnički dio ima dvije razine ovlasti rada.

1. Javni dio kojem svatko može pristupiti - nije potrebna prijava u aplikaciju
	U ovom dijelu je moguće:
	- otvaranje prijave u aplikaciju
	- otvaranje linka na GitHub repozitorij aplikacije
	- otvaranje linka na ER dijagram baze podataka aplikacije

2. Korsninički dio kojem pristupaju korisnici sa razinom ovlasti 1 - potebna je prijava u aplikaciju
	U ovom dijelu je moguće:
	- pregled plana rada po određenom mjesecu i godini
	- izmijeniti svoje pristupne podatke (vrši se provjera je li je novo korisničko ime zauzeto)

3. Administratorski dio kojem pristupaju korisnici sa razinom ovlasti 2 - potebna je prijava u aplikaciju

	U ovom dijelu je moguće:	
	- rad sa osobama
		- podaci koje je moguće unositi: 
			- ime
			- prezime
			- telefon
			- email (moguć izbor želi li se unijeti ili ne)
			- adresa (moguć izbor želi li se unijeti ili ne)
		
		Ovdje je moguće:
		- unos nove osobe 
		- izmjena podataka postojeće osobe 
 			- dobija se poruka ako nema niti jedne osobe u bazi
			- pronalazak osobe koju se želi izmijeniti
				- pronalazak po indeksu
					- izlistati će se sve osobe od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- brisanje postojeće osobe 
 			- dobija se poruka ako nema niti jedne osobe u bazi
 			- moguće samo ako osoba nije korisnik
			- pronalazak osobe kojeu će se obrisati 
				- pronalazak po indeksu
					- izlistati će se sve osobe od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu
					- neovisno upisuje li se prvo ime ili prezime
		- pregled svih osoba 
			- dobija se poruka ako nema niti jedne osobe u bazi
			- izlistanje osoba po imenu i prezimenu
		- pregled detalja određene osobe 
			- dobija se poruka ako nema niti jedne osobe u bazi
			- pronalazak osobe čiji detalji se žele pogledati je moguć po indeksu ili po imenu i/ili prezimenu
				- po indeksu znači da će se izlistati sve osobe od kojih će se izvršiti izbor
				- po imenu i/ili prezimenu je moguće neovisno upisuje li se prvo ime ili prezime
		
				
 	- rad sa korisnicima 
 		- podaci koje je moguće unositi: 
 			- korisničko ime
 			- lozinka
 			- osobni broj
 			- razina(1-običan korisnik, 2-admin)
 			- aktivan (da/ne)
 			- podaci osobe navedeni u gornjem odlomku
 		
 		Ovdje je moguće:								 
 		- unos novoga korisnika 
 			- vrši se provjera je li korisničko ime zauzeto
 			- unos potojeće osobe kao novoga korisnika 
 				- dobija se poruka ako nema niti jedne osobe u bazi
 				- izlistavaju se sve osobe od kojih se bira ona kojoj se žele dati korisnička prava
 				- odabirom osobe koja je već korisnik dobija se obavijest da je osoba već korisnik
 			- unos nove osobe kao novoga korisnika
 				- unose se podaci nove osobe 
 				- unose se podaci novoga korisnika
 		- brisanje postojećeg korisnika 
 			- dobija se poruka ako nema niti jednog korisnika u bazi
 			- moguće samo ako korisnik nije unešen u raspored
			- pronalazak korisnika kojeg će se obrisati 
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
		- izmjena postojećeg korisnika 
 			- dobija se poruka ako nema niti jednog korisnika u bazi
			- pronalazak korisnika kojeg će se izmijeniti 
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
		- pregled svih korisnika 
			- dobija se poruka ako nema niti jednog korisnika u bazi
			- izlistanje korisničkog imena i osobnog broja svih korisnika
		- pregled detalja određenog korisnika 
			- dobija se poruka ako nema niti jednog korisnika u bazi
			- pronalazak korisnika čiji detalji se žele pogledati
				- pronalazak po indeksu
					- izlistati će se svi korisnici od kojih će se izvršiti izbor
				- pronalazak po imenu i/ili prezimenu ili korisničkom imenu
					- neovisno upisuje li se prvo ime ili prezime
	
	
	- rad sa redovnim radnim vremenom 
		- ovjde se unosi od kojeg do kojeg datuma će se na koji dan u tjednu kako raditi
		- podaci koje je moguće unositi: 
 			- vrijedi od datuma
 			- vrijedi do datuma
 			- početak radnog vremena u ponedjeljak
 			- kraj radnog vremena u ponedjeljak
 			- početak radnog vremena u utorak
 			- kraj radnog vremena u utorak
 			- početak radnog vremena u srijedu
 			- kraj radnog vremena u srijedu
 			- početak radnog vremena u četvrtak
 			- kraj radnog vremena u četvrtak
 			- početak radnog vremena u petak
 			- kraj radnog vremena u petak
 			- početak radnog vremena u subotu
 			- kraj radnog vremena u subotu
 			- početak radnog vremena u nedjelju
 			- kraj radnog vremena u nedjelju
 			- trajanje pauze u minutama 			
		 
		 Ovdje je moguće:
		 - unos novog redovnog radnog vremena 
		 	- vrši se provjera je li se datum važenja preklapa sa postojećim unosima
		 - izmjena postojećeg redovnog radnog vremena 
		 	- ukoliko nema niti jednog unosa dobija se poruka
		 	- vrši se provjera je li se datum važenja preklapa sa postojećim unosima
		 	- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi izmijeniti
		 - brisanje redovnog radnog vremena
		 	- ukoliko nema niti jednog unosa dobija se poruka
		 	- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi obrisati
		 - pregled postojećih unosa redovnog radnog vremena
		 	- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima
		 - prelged detalja redovnog radnog vremena
		 	- ukoliko nema niti jednog unosa dobija se poruka
		 	- dobija se izlistanje sa postojećim unosima od kojih se bira unos čiji podatci se žele detaljno pogledati
		
		 
	- rad sa iznimnim radnim vremenom
		- ovjde se dodaje iznimka u redovnom radnom vremenu za određeni datum (npr. Božić)
		- podaci koje je moguće unositi: 
 			- datum
 			- početak radnog vremena
 			- kraj radnog vremena
 			- napomena (opis)
 			- trajanje pauze u minutama

		Ovdje je moguće:
		- unos novog iznimnog radnog vremena 
			- vrši se provjera preklapanja datuma sa postojećim unosima
		- izmjena postojećeg iznimnog radnog vremena 
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi izmijeniti
			- vrši se provjera preklapanja datuma sa postojećim unosima
		- brisanje postojećeg iznimnog radnog vremena
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi obrisati
		- pregled postojećih iznimnih radnih vrmena
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima
		 - prikaz detalja određenog iznimnog radnog vremena
		 	- ukoliko nema niti jednog unosa dobija se poruka
		 	- dobija se izlistanje sa postojećim unosima od kojih se bira unos kojeg se želi detaljno pogledati
		 	
	
	- rad sa brojem radnika po danima u tjednu
		- ovjde se unosi od kojeg do kojeg datuma na koji dan u tjednu treba biti koliko radnika koji rade
		- podaci koje je moguće unositi: 
 			- vrijedi od datuma
 			- vrijedi do datuma
 			- broj radnika za ponedjeljak	
 			- broj radnika za utorak	
 			- broj radnika za srijedu	
 			- broj radnika za četvrtak	
 			- broj radnika za petak	
 			- broj radnika za subotu	
 			- broj radnika za nedjelju	

		Ovdje je moguće:
		- unos novog broja radnika po danima u tjednu
			- vrši se provjera preklapanja datuma sa postojećim unosima
		- izmjena broja radnika po danima u tjednu
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi izmijeniti
			- vrši se provjera preklapanja datuma sa postojećim unosima
		- brisanje postojećeg unosa broja radnika po danima u tjednu
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima od kojih se bira kojeg se želi obrisati
		- pregled postojećih unosa broja radnika po danima u tjednu
			- ukoliko nema niti jednog unosa dobija se poruka
			- dobija se izlistanje sa postojećim unosima
		 - prikaz detalja određenog unosa broja radnika po danima u tjednu
		 	- ukoliko nema niti jednog unosa dobija se poruka
		 	- dobija se izlistanje sa postojećim unosima od kojih se bira unos kojeg se želi detaljno pogledati
		 	
		 	
	- rad sa oznakama unosa u raspored
		- ovjde se unose oznake koje se pojavljuju u rasporedu na određeni dan za određenog korisnika
		- predefinirane oznake su: Rad(R), Slobodno(S), Bolovanje(B)
		- podaci koje je moguće unositi: 
 			- naziv oznake (opis)
 			- skraćenica (znak koji se pojavljuje u rasporedu)

		Ovdje je moguće:
		- unos nove oznake
			- vrši se provjera postojanja skraćenice
		- izmjena oznake
			- ukoliko nema niti jedne oznake dobija se poruka
			- dobija se izlistanje sa postojećim oznakama od kojih se bira koju se želi izmijeniti
			- vrši se provjera provjera postojanja skraćenice			
			- oznaku Rad(R) nije moguće mijenjati
		- brisanje oznake
			- ukoliko nema niti jedne oznake dobija se poruka
			- dobija se izlistanje sa postojećim oznakama od kojih se bira koju se želi obrisati			
			- ukoliko je oznaka već unešena u raspored ne može ju se obrisati
			- oznaku Rad(R) nije moguće obrisati
		- pregled postojećih oznaka
			- ukoliko nema niti jedne oznake dobija se poruka
			- dobija se izlistanje sa postojećim oznakama
		 - prikaz detalja određene oznake
		 	- ukoliko nema niti jednoe oznake dobija se poruka
		 	- dobija se izlistanje sa postojećim oznakama od kojih se bira koju se želi detaljno pogledati
		
		
		- rad sa rasporedom rada
		- ovjde se unosi koji korisnik na koji dan radi, ili je slobodan, ili je na bolovanju...
		- podaci koje je moguće unositi: 
 			- datum
 			- korisnik (samo aktivni korisnici)
 			- oznaka
 			- rad sa pauzom (da/ne)

		Ovdje je moguće:
		- unos novog zapisa
			- vrši se provjera postojanja zapisa sa željenim korisnikom na željeni datum
			- nakon unosa zapisa u raspored prikazuje se plan rada za mjesec i godinu vezano za prethodno unešeni zapis, ali bez napomena
			- nudi se mogućnost ponovnog unosa zapisa u raspored za istoga korisnika
		- izmjena zapisa
			- ukoliko nema niti jednog zapisa u rasporedu dobija se poruka
			- vrši se provjera postojanja zapisa sa željenim korisnikom na željeni datum
			- dobija se popis godina u kojima postoje zapisi u rasporedu
			- odabirom godine dobija se popis mjeseci u odabranoj godini u kojima postoje zapisi u rasporedu
			- odabirom mjeseca dobija se popis korisnika za koje postoje zapisi u rasporedu u odabranom mjesecu i godini
			- odabirom korisnika dobija se popis datuma na koje postoje zapisi u rasporedu vezani za odabranog korisnika u odabranom mjesecu i godini
			- odabirom datuma dobija se detaljan ispis zapisa u rasporedu
			- zatim se unose podaci koji će se spremiti kao izmjene
		- brisanje zapisa
			- ukoliko nema niti jednog zapisa u rasporedu dobija se poruka
			- dobija se popis godina u kojima postoje zapisi u rasporedu
			- odabirom godine dobija se popis mjeseci u odabranoj godini u kojima postoje zapisi u rasporedu
			- odabirom mjeseca dobija se popis korisnika za koje postoje zapisi u rasporedu u odabranom mjesecu i godini
			- odabirom korisnika dobija se popis datuma na koje postoje zapisi u rasporedu vezani za odabranog korisnika u odabranom mjesecu i godini
			- odabirom datuma pokreće se postupak brisanja zapisa
		- prikaz rasporeda za određeni mjesec i godinu
			- ukoliko nema niti jednog zapisa u rasporedu dobija se poruka
			- dobija se popis godina u kojima postoje zapisi u rasporedu
			- odabirom godine dobija se popis mjeseci u odabranoj godini u kojima postoje zapisi u rasporedu
			- odabirom mjeseca prikazuje se raspored za taj mjesec i godinu u obliku tablice
			- ukoliko je u neki zapis unešeno da se radi bez pauze onda se uz oznaku pojavljuje znak *
			- vrši se provjera koliko je potrebno radnika na određeni datum
			- ispisuje se napomena ukoliko se potreban broj radnika ne poklapa sa brojem unešenih radnika koji rade taj dan
			- ispisuje se napomena ukoliko na određeni dan nije unešeno koliko treba biti radnika
		 - prikaz detalja rasporeda na određeni datum
		 	- ukoliko nema niti jednog zapisa u rasporedu dobija se poruka
			- dobija se popis godina u kojima postoje zapisi u rasporedu
			- odabirom godine dobija se popis mjeseci u odabranoj godini u kojima postoje zapisi u rasporedu
			- odabirom mjeseca dobija se popis datuma za koje postoje zapisi u rasporedu u odabranom mjesecu i godini
			- odabirom datuma prikazuju se detalji za taj datum
			- vrši se provjera radnog vremena za taj datum u popisu redovnih radnih vremena
			- vrši se provjera radnog vremena za taj datum u popisu izvanrednih radnih vremena
			- ispisuje se od kada do kada se radi, koliko je trajanje pauze i tko sve radi taj dan
			
			
