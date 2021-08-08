package raspored;

import java.awt.Desktop;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

	public static final String LINK_POVEZNICE = "https://github.com/mikecivan0/RasporedRada";
	private List<Osoba> osobe;
	private List<Korisnik> korisnici;
	private List<Korisnik> aktivniKorisnici;
	private List<RedovnoRadnoVrijeme> redovnaRadnaVremena;
	private List<IznimnoRadnoVrijeme> iznimnaRadnaVremena;
	private List<BrojRadnikaPoDanima> brojeviRadnikaPoDanima;
	private List<OznakaUnosaURaspored> oznakeUnosaURaspored;
	private List<Raspored> rasporedi;
	private boolean valjanost = false;
	private String porukaIzboraAkcije = "Unesite neku od gore ponuđenih stavki: ";
	private String porukaGreskeIzboraAkcije = "Nepostojeći izbor";
	private String porukaGreskePraznogUnosa = "Unos ne smije bti prazan";
	private String porukaGreskeUnosaCijelogBroja = "Molimo unesite cijeli broj";
	private String porukaGreskeDaNe = "Molimo unesite da ili ne";
	private String porukaGreskeNemaOsoba = "\nU bazi nema niti jedne osobe";
	private String porukaGreskeNemaKorisnika = "\nU bazi nema niti jednog korisnika";
	private String porukaGreskeNemaRedovnihRadnihVremena ="\nU bazi nema niti jednog redovnog radnog vremena";
	private String porukaGreskeNemaIznimnihRadnihVremena ="\nU bazi nema niti jednog iznimnog radnog vremena";
	private String porukaGreskeNemaBrojRadnikaPoDanima ="\nU bazi nema niti jednog unosa broja radnika po danima u tjednu";
	private String porukaGreskeNemaOznakaUnosaURaspored ="\nU bazi nema niti jedne oznake unosa u raspored";
	private String porukaGreskeNemaZapisaURasporedu ="\nU bazi nema niti jedan zapis u rasporedu";
	private String porukaGreskeKorisnikJeURasporedu ="\nNije moguće obrisati korisnika jer se nalazi u rasporedu";
	SimpleDateFormat formatDatuma;
	SimpleDateFormat formatVremena;

	public Start() {
		korisnici = new ArrayList<Korisnik>();
		aktivniKorisnici = new ArrayList<Korisnik>();
		osobe = new ArrayList<Osoba>();
		redovnaRadnaVremena = new ArrayList<RedovnoRadnoVrijeme>();
		iznimnaRadnaVremena = new ArrayList<IznimnoRadnoVrijeme>();
		brojeviRadnikaPoDanima = new ArrayList<BrojRadnikaPoDanima>();
		oznakeUnosaURaspored = new ArrayList<OznakaUnosaURaspored>();
		rasporedi = new ArrayList<Raspored>();
		
		

		/**
		 *  početak probnih podataka
		 */
		
		// probni podaci nove osobe
		osobe.add(new Osoba("Ivan", "Mikec", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko", "Drugi", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko1", "treći", "091", "email", "adresa"));

		// probni podaci novog korisnika
		korisnici.add(new Korisnik(osobe.get(0), "ja", "ja", "2-654", 1, true));
		korisnici.add(new Korisnik(osobe.get(1), "on", "on", "2-6545", 1, true));
		
		formatDatuma = new SimpleDateFormat("dd.MM.yyyy.");
		formatVremena = new SimpleDateFormat("HH:mm");
		
		// probni podaci redovnog radnog vremena
		try {
			redovnaRadnaVremena.add(new RedovnoRadnoVrijeme(
					formatDatuma.parse("1.1.2020."),
					formatDatuma.parse("1.3.2020."),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					30));
			redovnaRadnaVremena.add(new RedovnoRadnoVrijeme(
					formatDatuma.parse("1.4.2020."),
					formatDatuma.parse("1.6.2020."),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					formatVremena.parse("00:00"),
					formatVremena.parse("08:45"),
					formatVremena.parse("00:00"),
					formatVremena.parse("08:45"),
					formatVremena.parse("22:00"),
					formatVremena.parse("06:15"),
					30));
		} catch (ParseException e) {
			System.out.println("Nesto je pošlo po zlu sa unosom redovnog radnog vremena");
		}
		
		// probni podaci iznimnog radnog vremena
		try {
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("1.1.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Nova Godina 2020.",
					30));
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("1.3.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Neki blagdan 2020.",
					30));
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("1.5.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Praznik rada 2020.",
					30));
		} catch (ParseException e) {
			System.out.println("Nesto je pošlo po zlu unosom iznimnog radnog vremena");
		}
		
		// probni podaci broja radnika po danima u tjednu
		try {
			brojeviRadnikaPoDanima.add(new BrojRadnikaPoDanima(
					formatDatuma.parse("1.1.2020."),
					formatDatuma.parse("1.3.2020."),
					6,
					6,
					7,
					7,
					6,
					6,
					6));
			brojeviRadnikaPoDanima.add(new BrojRadnikaPoDanima(
					formatDatuma.parse("1.4.2020."),
					formatDatuma.parse("1.6.2020."),
					8,
					6,
					7,
					7,
					6,
					9,
					6));
		} catch (ParseException e) {
			System.out.println("Nesto je pošlo po zlu unosom iznimnog radnog vremena");
		}
		
		// probni podaci vrsta unosa u raspored
		oznakeUnosaURaspored.add(new OznakaUnosaURaspored("Rad","R"));
		oznakeUnosaURaspored.add(new OznakaUnosaURaspored("Slobodno","S"));
		oznakeUnosaURaspored.add(new OznakaUnosaURaspored("Bolovanje","B"));
		
		// probni podaci raspored
		try {
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(0),
					formatDatuma.parse("1.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(0),
					formatDatuma.parse("2.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(0),
					formatDatuma.parse("3.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(1),
					oznakeUnosaURaspored.get(1),
					formatDatuma.parse("4.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(1),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("5.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("07.04.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("9.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(1),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("10.4.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(0),
					formatDatuma.parse("7.5.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("8.5.2020."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(2),
					formatDatuma.parse("10.1.2021."),
					true));
			rasporedi.add(new Raspored(
					korisnici.get(0),
					oznakeUnosaURaspored.get(1),
					formatDatuma.parse("15.2.2021."),
					true));
			
		} catch (ParseException e) {
			System.out.println("Nesto je pošlo po zlu unosom u raspored");
		}
		
		/**
		 *  kraj probnih podataka
		 */

		korisniciAktualiziranjeListeAktivnihKorisnika();
		Alati.scanner = new Scanner(System.in);
		glavniIzbornik();
	}

	/**
	 * GLAVNI IZBORNIK 
	 */
	
	private void glavniIzbornik() {
		System.out.println();
		System.out.println("---------APLIKACIJA ZA VOĐENJE RASPOREDA RADA---------");
		Alati.ispisZaglavlja("GLAVNI IZBORNIK", true);
		System.out.println("1 ukoliko se želite prijaviti u aplikaciju");
		System.out.println("2 ukoliko želite pogledati izvorni kod aplikacije na GitHub-u");
		glavniIzbornikOdabirAkcije();
	}

	private void glavniIzbornikOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> login();
			case 2 -> {
				otvoriGithub();
				glavniIzbornik();
			}
		}
	}

	private void otvoriGithub() {
		try {
			Desktop desktop = java.awt.Desktop.getDesktop();
			desktop.browse(new URI(LINK_POVEZNICE));
		} catch (Exception e) {
			System.out.println("Dogodila se greška. Pokušajte ponovno kasnije.");
		}
	}

	private void logiranjePonovniPokusaj() {
		if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
			login();
		} else {
			glavniIzbornik();
		}
	}

	private void login() {
		Korisnik korisnik = new Korisnik();
		Alati.ispisZaglavlja("Podaci za prijavu u aplikaciju", true);
		korisnik.setKorisnickoIme(Alati.ucitajString("korisničko ime: ", porukaGreskePraznogUnosa, 1, 15));
		korisnik.setLozinka(Alati.ucitajString("lozinka: ", porukaGreskePraznogUnosa, 1, 30));
		korisniciProvjeraVjerodajnica(korisnik);
	}

	private void logout() {
		valjanost = false;
		glavniIzbornik();
	}

	/**
	 * 
	 * GLAVNI IZBORNIK KRAJ
	 * 
	 * AUTENTIFICIRANI KORISNIK
	 * 
	 */
	
	private void autentificiraniKorisnikGlavniIzbornik() {
		Alati.ispisZaglavlja("IZBORNIK ZA KORISNIKE", true);
		System.out.println("1 za rad sa osobama");
		System.out.println("2 za rad sa korisnicima");
		System.out.println("3 za rad sa redovnim radnim vremenom");
		System.out.println("4 za rad sa iznimnim radnim vremenom");
		System.out.println("5 za rad sa brojem radnika po danima u tjednu");
		System.out.println("6 za rad sa oznakama unosa u raspored");
		System.out.println("7 za rad sa rasporedom rada");
		System.out.println("8 za odjavu i povratak u glavni izbornik");
		autentificiraniKorisnikIzborGlavneAkcije();
	}
	
	private void autentificiraniKorisnikIzborGlavneAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 8)) {
			case 1 -> osobeIzbornik();
			case 2 -> korisniciIzbornik();
			case 3 -> redovnaRadnaVremenaIzbornik();
			case 4 -> iznimnaRadnaVremenaIzbornik();
			case 5 -> brojRadnikaPoDanimaIzbornik();
			case 6 -> oznakeUnosaURasporedIzbornik();
			case 7 -> rasporedIzbornik();
			case 8 -> logout();
		}
	}
	
	private void korisniciProvjeraVjerodajnica(Korisnik k) {
		valjanost = false;
		for (Korisnik korisnik : korisnici) {
			if (korisnik.getKorisnickoIme().equals(k.getKorisnickoIme()) && korisnik.getLozinka().equals(k.getLozinka())) {
				valjanost = true;
				break;
			}			
		}

		if (valjanost) {
			autentificiraniKorisnikGlavniIzbornik();
		} else {
			System.out.println("Nevaljana kombinacija korisničkog imena i lozinke");
			logiranjePonovniPokusaj();
		}
	}

	

	/**
	 * 
	 * AUTENTIFICIRANI KORISNIK KRAJ
	 * 
	 * OSOBE
	 * 
	 */
	
	// GLAVNI IZBORNIK OSOBA
	private void osobeIzbornik() {
		Alati.ispisZaglavlja("Rad sa osobama", true);
		System.out.println("1 za unos nove osobe");
		System.out.println("2 za izmjenu postojeće osobe");
		System.out.println("3 za brisanje postojeće osobe");
		System.out.println("4 za pregled svih osoba");
		System.out.println("5 za pregled detalja postojeće osobe");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		osobeOdabirAkcije();
	}

	private void osobeOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> {
				osobeUnosNove();
				System.out.println("\nOsoba je spremljena. Što želite dalje?");
				osobeIzbornik();
			}
			case 2 -> osobeIzmjena();
			case 3 -> osobeBrisanje();
			case 4 -> {
				osobeIzlistanje();
				osobeIzbornik();
			}
			case 5 -> {
				osobeDetalji();
				osobeIzbornik();
			}
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}

	}
	
	// UNOS NOVE OSOBE
	private void osobeUnosNove() {
		Alati.ispisZaglavlja("Podaci nove osobe", true);
		Osoba osoba = new Osoba();
		osoba = osobeUnosPodataka(osoba);
		osobe.add(osoba);
	}

	// IZMJENA OSOBE
	private void osobeIzmjena() {
		if(!osobe.isEmpty()) {
			osobeIspisIzboraPretrage("Izmjena podataka osobe");
			osobeUcitajOdabirPretrageZaIzmjenu();
		}else {
			System.out.println(porukaGreskeNemaOsoba);
			osobeIzbornik();
		}		
	}

	private void osobeUcitajOdabirPretrageZaIzmjenu() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeIzmjenaPoIndeksu();
			case 2 -> osobeIzmjenaPoImenu();
		}
	}
	
	private void osobeIzmjenaPoIndeksu() {
		osobeIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		Osoba osoba = osobe.get(i);
		osobeIzmjenaPodataka(osoba, i);
	}

	private void osobeIzmjenaPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer izbor = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size())-1;
			int indeksOdabraneOsobe = osobeIndeksOsobeIzIzvorneListe(nadjeneOsobe.get(izbor));
			osobeIzmjenaPodataka(osobe.get(indeksOdabraneOsobe), indeksOdabraneOsobe);					
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				osobeIzmjenaPoImenu();
			} else {
				osobeIzbornik();
			}
		}
	}
	
	private void osobeIzmjenaPodataka(Osoba osoba, int i) {
		osoba = osobeUnosPodataka(osoba);
		osobe.set(i, osoba);
		System.out.println("\nPodaci osobe su uspješno izmjenjeni. Što želite dalje?");
		osobeIzbornik();
	}
	
	// BRISANJE OSOBE	
	private void osobeBrisanje() {
		if(!osobe.isEmpty()) {
			osobeIspisIzboraPretrage("Brisanje osobe");
			osobeUcitajOdabirPretrageZaBrisanje();
		}else {
			System.out.println(porukaGreskeNemaOsoba);
			osobeIzbornik();
		}		
	}

	private void osobeUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeBrisanjePoIndeksu();
			case 2 -> osobeBrisanjePoImenu();
		}
	}
	
	private void osobeBrisanjePoIndeksu() {
		osobeIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		if(Alati.daNe("Želite li zaista obrisati osobu " + osobe.get(i).toString() + " (da/ne): ", porukaGreskeDaNe)) {
			if(!korisniciJeLiOsobaKorisnik(osobe.get(i))) {
				osobe.remove(i);
				System.out.println("\nOsoba je obrisana.");
			}else {
				System.out.println("\nOdabrana osoba je korisnik i stoga ju ne možete obrisati.");
			}
			
		}		
		osobeIzbornik();		
	}

	private void osobeBrisanjePoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer izbor = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size())-1;
			int indeksOdabraneOsobe = osobeIndeksOsobeIzIzvorneListe(nadjeneOsobe.get(izbor));
			if(Alati.daNe("Želite li zaista obrisati osobu " 
						+ osobe.get(indeksOdabraneOsobe).toString() + " (da/ne): ", 
						porukaGreskeDaNe)) {
				if(!korisniciJeLiOsobaKorisnik(osobe.get(indeksOdabraneOsobe))) {
					osobe.remove(indeksOdabraneOsobe);
					System.out.println("\nOsoba je obrisana.");
				}else {
					System.out.println("\nOdabrana osoba je korisnik i stoga ju ne možete obrisati.");
				}				
			}					
		} else {
			System.out.println("\nNema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				osobeBrisanjePoImenu();
			}
		}
		osobeIzbornik();
	}

	// DETALJI OSOBE
	private void osobeDetalji() {
		if(!osobe.isEmpty()) {
			osobeIspisIzboraPretrage("Detalji osobe");
			osobeUcitajOdabirPretrageZaIspisDetalja();	
		}else {
			System.out.println(porukaGreskeNemaOsoba);
			osobeIzbornik();
		}		
	}

	private void osobeUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeDetaljiPoIndeksu();
			case 2 -> osobeDetaljiPoImenu();
		}		
	}
	
	private void osobeDetaljiPoIndeksu() {
		osobeIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		Alati.ispisZaglavlja("Detalji osobe", false);
		osobe.get(i).ispisiDetalje();
		osobeIzbornik();
	}

	private void osobeDetaljiPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (!nadjeneOsobe.isEmpty()) {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer izbor = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size())-1;
			Alati.ispisZaglavlja("Detalji osobe", false);
			nadjeneOsobe.get(izbor).ispisiDetalje();		
			osobeIzbornik();						
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				osobeDetaljiPoImenu();
			} else {
				osobeIzbornik();
			}			
		}
	}	

	// POMOĆNE METODE OSOBA
	
	// izlistanje svih osoba
	private void osobeIzlistanje() {
		if(!osobe.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("Osobe koje se nalaze u bazi", false);
			for (Osoba osoba : osobe) {
				System.out.println(counter + " " + osoba.toString());
				counter++;
			}
		}else {
			System.out.println(porukaGreskeNemaOsoba);
			osobeIzbornik();
		}			
	}
	
	//izlistanje nađenih osoba
	private void osobeIzlistanje(String poruka, List<Osoba> osobe) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Osoba osoba : osobe) {
			System.out.println(counter + " " + osoba.toString());
			counter++;
		}
	}	

	private List<Osoba> osobePronadjiPoUvjetu(String uvjet) {
		List<Osoba> listaOsoba = new ArrayList<Osoba>();
		for (Osoba osoba : osobe) {
			if (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet)) {
				listaOsoba.add(osoba);
			}
		}
		return listaOsoba;
	}

	private boolean osobeJeLiToOsobaPoImenuIPrezimenu(Osoba osoba, String uvjet) {
		uvjet = uvjet.trim().toLowerCase();
		String imePrezime = osoba.getIme().toLowerCase() + " " + osoba.getPrezime().toLowerCase();
		String prezimeIme = osoba.getPrezime().toLowerCase() + " " + osoba.getIme().toLowerCase();
		if (imePrezime.contains(uvjet) || prezimeIme.contains(uvjet)) {
			return true;
		} else {
			return false;
		}
	}	
	
	private int osobeIndeksOsobeIzIzvorneListe(Osoba osoba) {
		return osobe.indexOf(osoba);
	}
	
	private void osobeIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
	}
	
	private Osoba osobeUnosPodataka(Osoba osoba) {
		osoba.setIme(Alati.ucitajString("ime osobe: ", porukaGreskePraznogUnosa, 1, 25));
		osoba.setPrezime(Alati.ucitajString("prezime osobe: ", porukaGreskePraznogUnosa, 1, 25));
		osoba.setAdresa(Alati.ucitajString("adresa osobe: ", porukaGreskePraznogUnosa, 1, 100));
		if (Alati.daNe("Želite li unijeti telefon osobe? (da/ne): ", porukaGreskeDaNe)) {
			osoba.setTelefon(Alati.ucitajString("telefon osobe: ", porukaGreskePraznogUnosa, 1, 20));
		} else {
			osoba.setTelefon("");
		}
		if (Alati.daNe("Želite li unijeti email adresu osobe? (da/ne): ", porukaGreskeDaNe)) {
			osoba.setEmail(Alati.ucitajString("email osobe: ", porukaGreskePraznogUnosa, 1, 50));
		} else {
			osoba.setEmail("");
		}
		return osoba;
	}	
	
	/**
	 * 
	 * OSOBE KRAJ
	 * 
	 * KORISNICI
	 * 
	 */

	private void korisniciIzbornik() {
		Alati.ispisZaglavlja("Rad sa korisnicima", true);
		System.out.println("1 za unos novog korisnika");
		System.out.println("2 za izmjenu postojećeg korisnika");
		System.out.println("3 za brisanje postojećeg korisnika");
		System.out.println("4 za pregled svih korisnika");
		System.out.println("5 za pregled detalja postojećeg korisnika");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		korisniciOdabirAkcije();
	}

	private void korisniciOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> korisniciUnosNovog();
			case 2 -> korisniciIzmjena();
			case 3 -> korisniciBrisanje();
			case 4 -> {
				korisniciIzlistanje();
				korisniciIzbornik();
			}
			case 5 -> {
				korisniciDetalji();
				korisniciIzbornik();
			}
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}		
	}
	
	private void korisniciUnosNovog() {
		Alati.ispisZaglavlja("Odabir načina unosa novog korisnika", true);
		System.out.println("1 za unos postojeće osobe kao novog korisnika");
		System.out.println("2 za unos nove osobe kao novog korisnika");
		System.out.println("3 za povratak u glavni izbornika za rad sa korisnicima");
		korisniciUnosNovogOdabirAkcije();
	}
	
	private void korisniciUnosNovogOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 3)) {
			case 1 -> korisniciUnosPostojeceOsobeKaoNovogKorisnika();
			case 2 -> korisniciUnosNoveOsobeKaoNovogKorisnika();
			case 3 -> korisniciIzbornik();
		}				
	}

	private void korisniciUnosPostojeceOsobeKaoNovogKorisnika() {
		if(!osobe.isEmpty()) {
			osobeIzlistanje();
			Osoba osoba = osobe.get(Alati.ucitajBroj("Unesite broj osobe koju želite dodati kao korisnika: ", 
							"Unos ne smije biti prazan", 1, osobe.size())-1);
			if(!korisniciJeLiOsobaKorisnik(osoba)) {
				Korisnik korisnik = new Korisnik();
				korisnik.setOsoba(osoba);
				while(true) {
					String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
					if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
						korisnik.setKorisnickoIme(korisnickoIme);
						korisnik = korisniciUnosOstalihPodataka(korisnik);
						korisnici.add(korisnik);
						System.out.println("\nNova osoba je unešena i postavljena kao novi korisnik");
					}else {
						if(Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
							continue;
						}
					}				
					break;
				}			
			}else {
				if(Alati.daNe("Osoba je već unešena kao korisnik. Želite li odabrati drugu osobu? (da/ne): ", porukaGreskeDaNe)) {
					korisniciUnosPostojeceOsobeKaoNovogKorisnika();
				}
			}
		}else {
			System.out.println("\nU bazi ne postoji niti jedna osoba. \n"
					+ "Da bi Ste mogli dodati osobu kao novog korisnika prvo unesite najmanje jednu osobu.");
		}	
		korisniciAktualiziranjeListeAktivnihKorisnika();
		korisniciIzbornik();
	}
	
	// UNOS NOVOG KORISNIKA
	private void korisniciUnosNoveOsobeKaoNovogKorisnika() {
		Korisnik korisnik = new Korisnik();
		osobeUnosNove();
		korisnik.setOsoba(osobe.get(osobe.size()-1));		
		while(true) {
			String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
			if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme)) {
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik = korisniciUnosOstalihPodataka(korisnik);
				korisnici.add(korisnik);
				System.out.println("\nNova osoba je unešena i postavljena kao novi korisnik");	
			}else {
				if(Alati.daNe("\nKorisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}else {
					osobe.remove(osobe.size()-1);
				}
			}
			korisniciAktualiziranjeListeAktivnihKorisnika();
			korisniciIzbornik();
			break;
		}		
	}

	// IZMJENA KORISNIKA
	private void korisniciIzmjena() {
		if(!korisnici.isEmpty()) {
			korisniciIspisIzboraPretrage("Izmjena podataka korisnika");
			korisniciIzmjenaUcitajOdabirPretrage();
		}else {
			System.out.println(porukaGreskeNemaKorisnika);
			korisniciIzbornik();
		}		
	}
	
	private void korisniciIzmjenaUcitajOdabirPretrage() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciIzmjenaPoIndeksu();
			case 2 -> korisniciIzmjenaPoImenu();
		}
		
	}

	private void korisniciIzmjenaPoIndeksu() {
		korisniciIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size()) - 1;
		Korisnik korisnik = korisnici.get(i);
		korisniciIzmjenaPodataka(korisnik, i);
	}

	private void korisniciIzmjenaPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Korisnik> nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (!nadjeniKorisnici.isEmpty()) {
			korisniciIzlistanje("Pronađeni korsnici", nadjeniKorisnici);
			Integer izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size())-1;
			int indeksOdabranogKorisnika = korisniciIndeksKorisnikaIzIzvorneListe(nadjeniKorisnici.get(izbor));
			korisniciIzmjenaPodataka(korisnici.get(indeksOdabranogKorisnika), indeksOdabranogKorisnika);				
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				korisniciIzmjenaPoImenu();
			}			
		}
		korisniciIzbornik();
	}	

	private void korisniciIzmjenaPodataka(Korisnik korisnik, int i) {
		while(true) {
			String korisnickoIme = Alati.ucitajString("Unesite korisničko ime korisnika: ", porukaGreskePraznogUnosa, 1, 50);
			if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnickoIme, korisnik)) {
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik = korisniciUnosOstalihPodataka(korisnik);
				korisnici.set(i, korisnik);
				System.out.println("");
				System.out.println("Novi podaci korisnika su spremljeni");
			}else {
				if(Alati.daNe("Korisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			korisniciAktualiziranjeListeAktivnihKorisnika();
			korisniciIzbornik();
			break;
		}		
	}
	
	// BRISANJE KORISNIKA
	private void korisniciBrisanje() {
		if(!korisnici.isEmpty()) {
			korisniciIspisIzboraPretrage("Brisanje korisnika");
			korisniciUcitajOdabirPretrageZaBrisanje();
		}else {
			System.out.println(porukaGreskeNemaKorisnika);
			korisniciIzbornik();
		}
	}

	private void korisniciUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciBrisanjePoIndeksu();
			case 2 -> korisniciBrisanjePoNazivu();
		}		
	}
	
	private void korisniciBrisanjePoIndeksu() {
		Korisnik korisnik = new Korisnik();
		korisniciIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size())-1;
		korisnik = korisnici.get(i);
		if(!korisniciJeLiKorisnikURasporedu(korisnik)) {
			if(Alati.daNe("Želite li zaista obrisati korisnika (" 
					+ korisnik.korisnikZaPrikaz() + "): ", 
					porukaGreskeDaNe)) {
			korisnici.remove(i);
			System.out.println("\nKorisnik je obrisan.");
			korisniciAktualiziranjeListeAktivnihKorisnika();
			}				
		}else {
			System.out.println(porukaGreskeKorisnikJeURasporedu);
		}	
		korisniciIzbornik();
	}

	private void korisniciBrisanjePoNazivu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ", 
				porukaGreskePraznogUnosa, 0, 30);
		List<Korisnik> nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (!nadjeniKorisnici.isEmpty()) {
			korisniciIzlistanje("Pronađeni korisnici", nadjeniKorisnici);
			Integer izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size())-1;
			int indeksKorisnikaIzIzvorneListe = korisniciIndeksKorisnikaIzIzvorneListe(nadjeniKorisnici.get(izbor));
			if(Alati.daNe("Želite li zaista obrisati odabranog korisnika (" 
							+ korisnici.get(indeksKorisnikaIzIzvorneListe).korisnikZaPrikaz() + "): ", 
							porukaGreskeDaNe)) {
				korisnici.remove(indeksKorisnikaIzIzvorneListe);
				System.out.println("\nKorisnik je obrisan.");	
			}
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				korisniciBrisanjePoNazivu();
			}			
		}
		korisniciAktualiziranjeListeAktivnihKorisnika();
		korisniciIzbornik();
	}	
	
	// DETALJI KORISNIKA
	private void korisniciDetalji() {
		if(!korisnici.isEmpty()) {
			korisniciIspisIzboraPretrage("Detalji korisnika");
			korisniciUcitajOdabirPretrageZaIspisDetalja();	
		}else {
			System.out.println(porukaGreskeNemaKorisnika);
			korisniciIzbornik();
		}
	}

	private void korisniciUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciDetaljiPoIndeksu();
			case 2 -> korisniciDetaljiPoImenu();
		}		
	}
	
	private void korisniciDetaljiPoIndeksu() {
		korisniciIzlistanje();
		int i = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size()) - 1;
		Alati.ispisZaglavlja("Detalji korisnika", false);
		korisnici.get(i).ispisiDetalje();
		korisniciIzbornik();
	}
	
	private void korisniciDetaljiPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime osobe/korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Korisnik> nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (!nadjeniKorisnici.isEmpty()) {
			korisniciIzlistanje("Pronađeni korisnici", nadjeniKorisnici);
			Integer izbor = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size())-1;
			Alati.ispisZaglavlja("Detalji korisnika", false);
			nadjeniKorisnici.get(izbor).ispisiDetalje();	
		} else {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", porukaGreskeDaNe)) {
				korisniciDetaljiPoImenu();
			}			
		}
		korisniciIzbornik();
	}	
	
	// POMOĆNE METODE KORISNIKA
	
	//  provjera kod unosa novog
	private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme) {
		return korisnici.stream().filter(o -> o.getKorisnickoIme().equals(korisnickoIme)).findFirst().isPresent();
	}
	
	// provjera kod izmjene
	private boolean korisniciProvjeriPostojanjeKorisnickogImena(final String korisnickoIme, final Korisnik korisnik) {
		boolean postojanje = false;
		for(Korisnik k : korisnici) {
			if(!k.equals(korisnik)) {
				if(k.getKorisnickoIme().equals(korisnickoIme)) {
					postojanje = true;
					break;
				}
			}
		}
		return postojanje;		
	}
		
	private void korisniciIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih korisnika od kojih će te izabrati željenog");
		System.out.println("2 za pretragu korisnika po imenu i/ili prezimenu ili korisničkom imenu");
	}
	
	private boolean korisniciJeLiOsobaKorisnik(Osoba osoba) {
		boolean valjanost = false;
		for(Korisnik korisnik: korisnici) {
			if(korisnik.getOsoba().equals(osoba)) {
				valjanost = true;
				break;
			}
			continue;
		}
		return valjanost;		
	}
	
	private Korisnik korisniciUnosOstalihPodataka(Korisnik korisnik) {		
		korisnik.setLozinka(Alati.ucitajString("Unesite lozinku novoga korisnika: ", porukaGreskePraznogUnosa, 1, 100));
		korisnik.setOsobniBroj(Alati.ucitajString("Unesite osobni broj novoga korisnika: ", porukaGreskePraznogUnosa, 1, 10));		
		korisnik.setRazina(Alati.ucitajBroj("Unesite razinu korisnika (1 ili 2): ", porukaGreskeIzboraAkcije, 1, 2));
		korisnik.setAktivan(Alati.daNe("Hoće li korisnik biti aktivan (da/ne): ", porukaGreskeIzboraAkcije));		
		return korisnik;
	}
	
	private boolean korisniciJeLiToKorisnikPoNazivu(Korisnik korisnik, String uvjet) {
		uvjet = uvjet.trim().toLowerCase();
		String imePrezime = korisnik.getOsoba().getIme().toLowerCase() + " " + korisnik.getOsoba().getPrezime().toLowerCase();
		String prezimeIme = korisnik.getOsoba().getPrezime().toLowerCase() + " " + korisnik.getOsoba().getIme().toLowerCase();
		String korisnickoIme = korisnik.getKorisnickoIme().toLowerCase();
		if (imePrezime.contains(uvjet) || prezimeIme.contains(uvjet) || korisnickoIme.equals(uvjet)) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean korisniciJeLiKorisnikURasporedu(Korisnik korisnik) {
		return rasporedi.stream().filter(o -> o.getKorisnik().equals(korisnik)).findFirst().isPresent();
	}
	
	private int korisniciIndeksKorisnikaIzIzvorneListe(Korisnik korisnik) {
		return korisnici.indexOf(korisnik);
	}
	
	private List<Korisnik> korisniciPronadjiPoUvjetu(String uvjet) {
		List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		for (Korisnik korisnik : korisnici) {
			if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
				listaKorisnika.add(korisnik);
			}
		}
		return listaKorisnika;
	}
	
	// izlistanje svih korisnika
	private void korisniciIzlistanje() {
		if(!korisnici.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("Korisici koji se nalaze u bazi", false);
			for (Korisnik korisnik : korisnici) {
				System.out.println(counter + " " + korisnik.toString());
				counter++;
			}		
		}else {
			System.out.println(porukaGreskeNemaKorisnika);
		}	
	}
	
	// izlistanje nađenih korisnika
	private void korisniciIzlistanje(String poruka, List<Korisnik> korisnici) {
		if(!korisnici.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja(poruka, false);
			for (Korisnik korisnik : korisnici) {
				System.out.println(counter + " " + korisnik.toString());
				counter++;
			}	
		}else {
			System.out.println(porukaGreskeNemaKorisnika);
		}			
	}
	
	// aktualiziranje aktivnih korisnika
	private void korisniciAktualiziranjeListeAktivnihKorisnika() {
		aktivniKorisnici.clear();
		for (Korisnik korisnik : korisnici) {
			if(korisnik.isAktivan()) {
				aktivniKorisnici.add(korisnik);
			}
		}
	}
	
	// izlistanje aktivnih korisnika
	private void korisniciIzlistanjeAktivnihKorisnika(String poruka, List<Korisnik> korisnici) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Korisnik korisnik : korisnici) {
			System.out.println(counter + " " + korisnik.korisnikZaPrikaz());
			counter++;
		}		
	}	
	
	/**
	 * 
	 * KORISNICI KRAJ
	 * 
	 * REDOVNA RADNA VREMENA
	 * 
	 */

	private void redovnaRadnaVremenaIzbornik() {
		Alati.ispisZaglavlja("Rad sa redovnim radnim vremenom", true);
		System.out.println("1 za unos redovnog radnog vremena");
		System.out.println("2 za izmjenu redovnog radnog vremena");
		System.out.println("3 za brisanje redovnog radnog vremena");
		System.out.println("4 za pregled redovnih radnih vremena");
		System.out.println("5 za prikaz detalja određenog redovnog radnog vremena");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		redovnaRadnaVremenaOdabirAkcije();
	}

	private void redovnaRadnaVremenaOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> redovnaRadnaVremenaUnosNovog();
			case 2 -> redovnaRadnaVremenaIzmjena();
			case 3 -> redovnaRadnaVremenaBrisanje();
			case 4 -> {
				redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do");
				redovnaRadnaVremenaIzbornik();
			}
			case 5 -> redovnaRadnaVremenaDetalji();
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}
		
	}
	
	// UNOS NOVOG RADNOG VREMENA
	private void redovnaRadnaVremenaUnosNovog() {
		Alati.ispisZaglavlja("Unos novog redovnog radnog vremena", false);
		RedovnoRadnoVrijeme redovnoRadnoVrijeme = new RedovnoRadnoVrijeme();		
		Date datumPocetkaPrimjene = Alati.ucitajDatum("Od kada će početi vrijediti nova radna vremena: ");
		Date datumKrajaPrimjene = Alati.ucitajDatum("Do kada će vrijediti nova radna vremena: ");
		if(redovnaRadnaVremenaProvjeriPreklapanje(datumPocetkaPrimjene) 
				&& redovnaRadnaVremenaProvjeriPreklapanje(datumKrajaPrimjene)) {
			redovnoRadnoVrijeme.setVrijediOd(datumPocetkaPrimjene);
			redovnoRadnoVrijeme.setVrijediDo(datumKrajaPrimjene);
			redovnoRadnoVrijeme = redovnaRadnaVremenaUnesiOstaleVrijednosti(redovnoRadnoVrijeme);
			redovnaRadnaVremena.add(redovnoRadnoVrijeme);
			System.out.println("\nNovo redovno radno vrijeme je unešeno");	
		}else {
			if(Alati.daNe("Redovno radno vrijeme u tom intervalu primjene je već unešeno."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
				redovnaRadnaVremenaUnosNovog();
			}
		}
		redovnaRadnaVremenaIzbornik();
	}
	
	//IZMJENA REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaIzmjena() {
		if(!redovnaRadnaVremena.isEmpty()) {
			redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do");
			int i = Alati.ucitajBroj("Unesite broj ispred redovnog radnog vremena koje želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
					redovnaRadnaVremena.size()) - 1;
			RedovnoRadnoVrijeme redovnoRadnoVrijeme = redovnaRadnaVremena.get(i);
			redovnaRadnaVremenaIzmjenaPodataka(redovnoRadnoVrijeme, i);		
		}else {
			System.out.println(porukaGreskeNemaRedovnihRadnihVremena);
			redovnaRadnaVremenaIzbornik();
		}		
	}
	
	private void redovnaRadnaVremenaIzmjenaPodataka(RedovnoRadnoVrijeme redovnoRadnoVrijeme, int i) {
		while(true) {
			Date datumPocetkaPrimjene = Alati.ucitajDatum("Od kada će početi vrijediti nova radna vremena: ");
			Date datumKrajaPrimjene = Alati.ucitajDatum("Do kada će vrijediti nova radna vremena: ");
			if(redovnaRadnaVremenaProvjeriPreklapanje(datumPocetkaPrimjene, redovnoRadnoVrijeme) 
					&& redovnaRadnaVremenaProvjeriPreklapanje(datumKrajaPrimjene,redovnoRadnoVrijeme )) {
				redovnoRadnoVrijeme.setVrijediOd(datumPocetkaPrimjene);
				redovnoRadnoVrijeme.setVrijediDo(datumKrajaPrimjene);
				redovnoRadnoVrijeme = redovnaRadnaVremenaUnesiOstaleVrijednosti(redovnoRadnoVrijeme);
				redovnaRadnaVremena.set(i, redovnoRadnoVrijeme);
				System.out.println("\nRedovno radno vrijeme je izmijenjeno");	
			}else {
				if(Alati.daNe("Radno vrijeme u tom intervalu primjene je već unešeno."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			redovnaRadnaVremenaIzbornik();
			break;
		}		
	}
	
	// BRISANJE REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaBrisanje() {
		if(!redovnaRadnaVremena.isEmpty()) {
			redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do");
			int i = Alati.ucitajBroj("Unesite broj unosa koji želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					redovnaRadnaVremena.size()) - 1;
			if(Alati.daNe("Želite li zaista obrisati radno vrijeme sa datumom primjene " 
					+ redovnaRadnaVremena.get(i).toString() + ": ", 
					porukaGreskeDaNe)) {
				redovnaRadnaVremena.remove(i);
				System.out.println("\nUnos je obrisan.");
			}				
		}else {
			System.out.println(porukaGreskeNemaRedovnihRadnihVremena);
		}
		redovnaRadnaVremenaIzbornik();
	}
	
	// DETALJI REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaDetalji() {	
		if(!redovnaRadnaVremena.isEmpty()) {
			redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do");
			int i = Alati.ucitajBroj("Unesite redni broj redovnog radnog vremena koje želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
					redovnaRadnaVremena.size()) - 1;
			Alati.ispisZaglavlja("Detalji redovnog radnog vremena", false);
			redovnaRadnaVremena.get(i).ispisiDetalje();
		}else {
			System.out.println(porukaGreskeNemaRedovnihRadnihVremena);
		}
		redovnaRadnaVremenaIzbornik();	
	}	
	
	// POMOĆNE FUNKCIJE REDOVNOG RADNOG VREMENA
	
	// provjera kod unosa novog
	private boolean redovnaRadnaVremenaProvjeriPreklapanje(final Date datum) {		
		boolean valjanost = true;
		for(RedovnoRadnoVrijeme rrv : redovnaRadnaVremena) {
			if((rrv.getVrijediOd().before(datum) || rrv.getVrijediOd().equals(datum)) 
					& (rrv.getVrijediDo().after(datum) || rrv.getVrijediDo().equals(datum))) {
				valjanost = false;
				break;
			}
			continue;
		}	
		return valjanost;
	}
	
	// provjera kod izmjene podataka
	private boolean redovnaRadnaVremenaProvjeriPreklapanje(final Date datum, final RedovnoRadnoVrijeme redovnoRadnoVrijeme) {		
		boolean valjanost = true;
		for(RedovnoRadnoVrijeme rrv : redovnaRadnaVremena) {
			if(!rrv.equals(redovnoRadnoVrijeme)) {
				if((rrv.getVrijediOd().before(datum) || rrv.getVrijediOd().equals(datum)) 
						& (rrv.getVrijediDo().after(datum) || rrv.getVrijediDo().equals(datum))) {
					valjanost = false;
					break;
				}
			}			
		}	
		return valjanost;
	}
	
	private RedovnoRadnoVrijeme redovnaRadnaVremenaUnesiOstaleVrijednosti(RedovnoRadnoVrijeme redovnoRadnoVrijeme) {
		redovnoRadnoVrijeme.setPonedjeljakOd(Alati.ucitajVrijeme("Početak redovnog radog vremena ponedjeljkom: "));
		redovnoRadnoVrijeme.setPonedjeljakDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena ponedjeljkom: "));
		redovnoRadnoVrijeme.setUtorakOd(Alati.ucitajVrijeme("Početak redovnog radog vremena utorkom: "));
		redovnoRadnoVrijeme.setUtorakDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena utorkom: "));
		redovnoRadnoVrijeme.setSrijedaOd(Alati.ucitajVrijeme("Početak redovnog radog vremena srijedom: "));
		redovnoRadnoVrijeme.setSrijedaDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena srijedom: "));
		redovnoRadnoVrijeme.setCetvrtakOd(Alati.ucitajVrijeme("Početak redovnog radog vremena četvrtkom: "));
		redovnoRadnoVrijeme.setCetvrtakDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena četvrtkom: "));
		redovnoRadnoVrijeme.setPetakOd(Alati.ucitajVrijeme("Početak redovnog radog vremena petkom: "));
		redovnoRadnoVrijeme.setPetakDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena petkom: "));
		redovnoRadnoVrijeme.setSubotaOd(Alati.ucitajVrijeme("Početak redovnog radog vremena subotom: "));
		redovnoRadnoVrijeme.setSubotaDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena subotom: "));
		redovnoRadnoVrijeme.setNedjeljaOd(Alati.ucitajVrijeme("Početak redovnog radog vremena nedjeljom: "));
		redovnoRadnoVrijeme.setNedjeljaDo(Alati.ucitajVrijeme("Kraj redovnog radnog vremena nedjeljom: "));
		redovnoRadnoVrijeme.setTrajanjePauzeUMinutama(Alati.ucitajBroj("Trajanje pauze u minutama: ", porukaGreskeUnosaCijelogBroja, 1, 120));
		
		return redovnoRadnoVrijeme;
	}
	
	private void redovnaRadnaVremenaIzlistanje(String poruka) {
		if(!redovnaRadnaVremena.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja(poruka, false);
			for (RedovnoRadnoVrijeme rrv : redovnaRadnaVremena) {
				System.out.println(counter + " " + rrv.toString());
				counter++;
			}
		}else {
			System.out.println(porukaGreskeNemaRedovnihRadnihVremena);
		}		
	}

	/**
	 * 
	 * REDOVNA RADNA VREMENA KRAJ
	 * 
	 * IZNIMNA RADNA VREMENA
	 * 
	 */
	
	private void iznimnaRadnaVremenaIzbornik() {
		Alati.ispisZaglavlja("Rad sa iznimnim radnim vremenom", true);
		System.out.println("1 za unos iznimnog radnog vremena");
		System.out.println("2 za izmjenu iznimnog radnog vremena");
		System.out.println("3 za brisanje iznimnog radnog vremena");
		System.out.println("4 za pregled iznimnih radnih vremena");
		System.out.println("5 za prikaz detalja određenog iznimnog radnog vremena");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		iznimnaRadnaVremenaOdabirAkcije();
	}

	private void iznimnaRadnaVremenaOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> iznimnaRadnaVremenaUnosNovog();
			case 2 -> iznimnaRadnaVremenaIzmjena();
			case 3 -> iznimnaRadnaVremenaBrisanje();
			case 4 -> {
				iznimnaRadnaVremenaIzlistanje();
				iznimnaRadnaVremenaIzbornik();
			}
			case 5 -> iznimnaRadnaVremenaDetalji();
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}
		
	}
	
	// UNOS IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaUnosNovog() {
		Alati.ispisZaglavlja("Unos novog iznimnog radnog vremena", false);
		IznimnoRadnoVrijeme iznimnoRadnoVrijeme = new IznimnoRadnoVrijeme();
		Date datum = Alati.ucitajDatum("Unesite datum iznimnog radnog vremena: ");
		if(!iznimnaRadnaVremenaProvjeriPreklapanje(datum)) {
			iznimnoRadnoVrijeme.setDatum(datum);
			iznimnoRadnoVrijeme = iznimnaRadnaVremenaUnesiOstaleVrijednosti(iznimnoRadnoVrijeme);
			iznimnaRadnaVremena.add(iznimnoRadnoVrijeme);
			System.out.println("\nNovo iznimno radno vrijeme je unešeno");	
		}else {
			if(Alati.daNe("Iznimno radno vrijeme sa tim datumom je već unešeno."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
				iznimnaRadnaVremenaUnosNovog();
			}
		}
		iznimnaRadnaVremenaIzbornik();
	}
	
	//IZMJENA IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaIzmjena() {
		if(!iznimnaRadnaVremena.isEmpty()) {
			iznimnaRadnaVremenaIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj iznimnog radnog vremena koje želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
					iznimnaRadnaVremena.size()) - 1;
			IznimnoRadnoVrijeme iznimnoRadnoVrijeme = iznimnaRadnaVremena.get(i);
			iznimnaRadnaVremenaIzmjenaPodataka(iznimnoRadnoVrijeme, i);		
		}else {
			System.out.println(porukaGreskeNemaIznimnihRadnihVremena);
			iznimnaRadnaVremenaIzbornik();
		}		
	}
	
	private void iznimnaRadnaVremenaIzmjenaPodataka(IznimnoRadnoVrijeme iznimnoRadnoVrijeme, int i) {
		while(true) {
			Date datum = Alati.ucitajDatum("Unesite datum iznimnog radnog vremena: ");
			if(!iznimnaRadnaVremenaProvjeriPreklapanje(datum, iznimnoRadnoVrijeme)) {
				iznimnoRadnoVrijeme.setDatum(datum);
				iznimnoRadnoVrijeme = iznimnaRadnaVremenaUnesiOstaleVrijednosti(iznimnoRadnoVrijeme);
				iznimnaRadnaVremena.set(i, iznimnoRadnoVrijeme);
				System.out.println("\nIznimno radno vrijeme je izmijenjeno");	
			}else {
				if(Alati.daNe("Iznimno radno vrijeme sa tim datumom je već unešeno."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			iznimnaRadnaVremenaIzbornik();
			break;
		}		
	}
	
	// BRISANJE IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaBrisanje() {
		if(!iznimnaRadnaVremena.isEmpty()) {
			iznimnaRadnaVremenaIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj iznimnog radnog vremena koje želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					iznimnaRadnaVremena.size()) - 1;
			if(Alati.daNe("Želite li zaista obrisati iznimno radno vrijeme sa datumom " 
							+ Alati.hrDatum(iznimnaRadnaVremena.get(i).getDatum()) + ": ", 
							porukaGreskeDaNe)) {
				iznimnaRadnaVremena.remove(i);
				System.out.println("\nIznimno radno vrijeme je obrisano.");
			}		
		}else {
			System.out.println(porukaGreskeNemaIznimnihRadnihVremena);
		}		
		iznimnaRadnaVremenaIzbornik();
	}
	
	// DETALJI IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaDetalji() {	
		if(!iznimnaRadnaVremena.isEmpty()) {			
			iznimnaRadnaVremenaIzlistanje();
			int i = Alati.ucitajBroj("Unesite redni broj ispred iznimnog radnog vremena koje želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
					iznimnaRadnaVremena.size()) - 1;
			Alati.ispisZaglavlja("Detalji iznimnog radnog vremena", false);
			iznimnaRadnaVremena.get(i).ispisiDetalje();
		}else {
			System.out.println(porukaGreskeNemaIznimnihRadnihVremena);
		}			
		iznimnaRadnaVremenaIzbornik();		
	}	
	
	// POMOĆNE FUNKCIJE IZNIMNOG RADNOG VREMENA
	// provjera kod novog unosa
	private boolean iznimnaRadnaVremenaProvjeriPreklapanje(final Date datum) {		
		return iznimnaRadnaVremena.stream().filter(o -> o.getDatum().equals(datum)).findFirst().isPresent();
	}
	
	// provjera kod izmjene unosa
	private boolean iznimnaRadnaVremenaProvjeriPreklapanje(final Date datum, final IznimnoRadnoVrijeme iznimnoRadnoVrijeme) {	
		boolean postojanje = false;
		for(IznimnoRadnoVrijeme irv : iznimnaRadnaVremena) {
			if(!irv.equals(iznimnoRadnoVrijeme)) {
				if(irv.getDatum().equals(datum)) {
					postojanje = true;
					break;
				}
			}
		}
		return postojanje;	
	}
	
	private IznimnoRadnoVrijeme iznimnaRadnaVremenaUnesiOstaleVrijednosti(IznimnoRadnoVrijeme iznimnoRadnoVrijeme) {
		iznimnoRadnoVrijeme.setPocetak(Alati.ucitajVrijeme("Početak radnog vremena na dan " + Alati.hrDatum(iznimnoRadnoVrijeme.getDatum()) + ": "));
		iznimnoRadnoVrijeme.setKraj(Alati.ucitajVrijeme("Kraj radnog vremena na dan " + Alati.hrDatum(iznimnoRadnoVrijeme.getDatum()) + ": "));
		iznimnoRadnoVrijeme.setPauza(Alati.ucitajBroj("Trajanje pauze u minutama na dan " + 
									 Alati.hrDatum(iznimnoRadnoVrijeme.getDatum())  + ": ", porukaGreskeUnosaCijelogBroja, 1, 120));
		iznimnoRadnoVrijeme.setNapomena(Alati.ucitajString("Unesite napomenu/oznaku iznimnog radnog vremena (npr. Božić 2015.): ", porukaGreskePraznogUnosa, 1, 50));		
		return iznimnoRadnoVrijeme;
	}
	
	private void iznimnaRadnaVremenaIzlistanje() {
		if(!iznimnaRadnaVremena.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("U bazi postoje sljedeća iznimna radna vremena", false);
			for (IznimnoRadnoVrijeme irv : iznimnaRadnaVremena) {
				System.out.println(counter + " " + irv.toString());
				counter++;
			}
		}else {
			System.out.println(porukaGreskeNemaIznimnihRadnihVremena);
		}		
	}

	/**
	 *  
	 * IZNIMNA RADNA VREMENA KRAJ
	 * 
	 * BROJ RADNIKA PO DANIMA
	 * 
	 */
	
	private void brojRadnikaPoDanimaIzbornik() {
		Alati.ispisZaglavlja("Rad sa brojem radnika po danima u tjednu", true);
		System.out.println("1 za novi unos broja radnika po danima u tjednu");
		System.out.println("2 za izmjenu postojećeg unosa broja radnika po danima u tjednu");
		System.out.println("3 za brisanje postojećeg unosa broja radnika po danima u tjednu");
		System.out.println("4 za pregled postojećih unosa broja radnika po danima u tjednu");
		System.out.println("5 za prikaz detalja određenog unosa broja radnika po danima u tjedna");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		brojRadnikaPoDanimaOdabirAkcije();
	}

	private void brojRadnikaPoDanimaOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> brojRadnikaPoDanimaUnosNovog();
			case 2 -> brojRadnikaPoDanimaIzmjena();
			case 3 -> brojRadnikaPoDanimaBrisanje();
			case 4 -> {
				brojRadnikaPoDanimaIzlistanje();
				brojRadnikaPoDanimaIzbornik();
			}
			case 5 -> brojRadnikaPoDanimaDetalji();
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}
		
	}
	
	// UNOS NOVOG BROJA RADNIKA PO DANIMA U TJEDNU
	private void brojRadnikaPoDanimaUnosNovog() {
		Alati.ispisZaglavlja("Unos novog redovnog radnog vremena", false);
		BrojRadnikaPoDanima brojRadnikaPoDanima = new BrojRadnikaPoDanima();		
		Date datumPocetkaPrimjene = Alati.ucitajDatum("Od kada će početi vrijediti novi unosi: ");
		Date datumKrajaPrimjene = Alati.ucitajDatum("Do kada će vrijediti novi unosi: ");
		if(brojRadnikaPoDanimaProvjeriPreklapanje(datumPocetkaPrimjene) 
				&& brojRadnikaPoDanimaProvjeriPreklapanje(datumKrajaPrimjene)) {
			brojRadnikaPoDanima.setVrijediOd(datumPocetkaPrimjene);
			brojRadnikaPoDanima.setVrijediDo(datumKrajaPrimjene);
			brojRadnikaPoDanima = brojRadnikaPoDanimaUnesiOstaleVrijednosti(brojRadnikaPoDanima);
			brojeviRadnikaPoDanima.add(brojRadnikaPoDanima);
			System.out.println("\nUnešen je novi broj radnika po danima u tjednu.");	
		}else {
			if(Alati.daNe("Broj radnika u tom intervalu primjene je već unešen."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
				brojRadnikaPoDanimaUnosNovog();
			}
		}
		brojRadnikaPoDanimaIzbornik();
	}
	
	//IZMJENA BROJA RADNIKA PO DANIMA U TJEDNU
	private void brojRadnikaPoDanimaIzmjena() {
		if(!brojeviRadnikaPoDanima.isEmpty()) {
			brojRadnikaPoDanimaIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj ispred unosa koji želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
					brojeviRadnikaPoDanima.size()) - 1;
			BrojRadnikaPoDanima brojRadnikaPoDanima = brojeviRadnikaPoDanima.get(i);
			brojRadnikaPoDanimaIzmjenaPodataka(brojRadnikaPoDanima, i);		
		}else {
			System.out.println(porukaGreskeNemaBrojRadnikaPoDanima);
			brojRadnikaPoDanimaIzbornik();
		}		
	}
	
	private void brojRadnikaPoDanimaIzmjenaPodataka(BrojRadnikaPoDanima brojRadnikaPoDanima, int i) {
		while(true) {
			Date datumPocetkaPrimjene = Alati.ucitajDatum("Od kada će početi vrijediti novi unos: ");
			Date datumKrajaPrimjene = Alati.ucitajDatum("Do kada će vrijediti novi unos: ");
			if(!brojRadnikaPoDanimaProvjeriPreklapanje(datumPocetkaPrimjene, brojRadnikaPoDanima) 
					&& !brojRadnikaPoDanimaProvjeriPreklapanje(datumKrajaPrimjene, brojRadnikaPoDanima)) {
				brojRadnikaPoDanima.setVrijediOd(datumPocetkaPrimjene);
				brojRadnikaPoDanima.setVrijediDo(datumKrajaPrimjene);
				brojRadnikaPoDanima = brojRadnikaPoDanimaUnesiOstaleVrijednosti(brojRadnikaPoDanima);
				brojeviRadnikaPoDanima.set(i, brojRadnikaPoDanima);
				System.out.println("\nUnos je izmjenjen.");	
			}else {
				if(Alati.daNe("Broj radnika u tom intervalu primjene je već unešen."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			brojRadnikaPoDanimaIzbornik();
			break;
		}		
	}
	
	// BRISANJE UNOSA BROJA RANIKA PO DANIMA U TJEDNU
	private void brojRadnikaPoDanimaBrisanje() {
		if(!brojeviRadnikaPoDanima.isEmpty()) {
			brojRadnikaPoDanimaIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj unosa koji želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					brojeviRadnikaPoDanima.size()) - 1;
			if(Alati.daNe("Želite li zaista obrisati unos sa datum primjene " 
							+ brojeviRadnikaPoDanima.get(i).toString() + ": ", 
							porukaGreskeDaNe)) {
				brojeviRadnikaPoDanima.remove(i);
				System.out.println("\nUnos je obrisan.");
			}		
		}else {
			System.out.println(porukaGreskeNemaBrojRadnikaPoDanima);
		}			
		brojRadnikaPoDanimaIzbornik();
	}
	
	// DETALJI UNOSA BROJA RADNIKA PO DANIMA U TJEDNU
	private void brojRadnikaPoDanimaDetalji() {	
		if(!brojeviRadnikaPoDanima.isEmpty()) {
			brojRadnikaPoDanimaIzbornik();
			brojRadnikaPoDanimaIzlistanje();
			int i = Alati.ucitajBroj("Unesite redni broj unosa koji želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
					brojeviRadnikaPoDanima.size()) - 1;
			Alati.ispisZaglavlja("Detalji unosa", false);
			brojeviRadnikaPoDanima.get(i).ispisiDetalje();
		}else {
			System.out.println(porukaGreskeNemaBrojRadnikaPoDanima);
		}					
		brojRadnikaPoDanimaIzbornik();		
	}	
	
	// POMOĆNE FUNKCIJE BROJA RADNIKA PO DANIMA U TJEDNU
	
	// provjera kod unosa novog
	private boolean brojRadnikaPoDanimaProvjeriPreklapanje(final Date datum) {		
		boolean postojanje = false;
		for(BrojRadnikaPoDanima brojRadnika : brojeviRadnikaPoDanima) {
			if((brojRadnika.getVrijediOd().before(datum) || brojRadnika.getVrijediOd().equals(datum)) 
					& (brojRadnika.getVrijediDo().after(datum) || brojRadnika.getVrijediDo().equals(datum))) {
				postojanje = true;
				break;
			}
			continue;
		}	
		return postojanje;
	}
	
	// provjera kod izmjene podataka
	private boolean brojRadnikaPoDanimaProvjeriPreklapanje(final Date datum, final BrojRadnikaPoDanima brojRadnikaPoDanima) {	
		boolean valjanost = true;
		for(BrojRadnikaPoDanima brojRadnika : brojeviRadnikaPoDanima) {
			if(!brojRadnika.equals(brojRadnikaPoDanima)) {
				if((brojRadnika.getVrijediOd().before(datum) || brojRadnika.getVrijediOd().equals(datum)) 
						& (brojRadnika.getVrijediDo().after(datum) || brojRadnika.getVrijediDo().equals(datum))) {
					valjanost = false;
					break;
				}
			}
		}
		return valjanost;	
	}
	
	private BrojRadnikaPoDanima brojRadnikaPoDanimaUnesiOstaleVrijednosti(BrojRadnikaPoDanima brojRadnikaPoDanima) {
		brojRadnikaPoDanima.setPonedjeljak(Alati.ucitajBroj("Unesite broj radnika ponedjeljkom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setUtorak(Alati.ucitajBroj("Unesite broj radnika utorkom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setSrijeda(Alati.ucitajBroj("Unesite broj radnika srijedom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setCetvrtak(Alati.ucitajBroj("Unesite broj radnika četvrtkom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setPetak(Alati.ucitajBroj("Unesite broj radnika petkom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setSubota(Alati.ucitajBroj("Unesite broj radnika subotom: ", porukaGreskeUnosaCijelogBroja, 1, 30));
		brojRadnikaPoDanima.setNedjelja(Alati.ucitajBroj("Unesite broj radnika nedjeljom: ", porukaGreskeUnosaCijelogBroja, 1, 30));		
		return brojRadnikaPoDanima;
	}
	
	private void brojRadnikaPoDanimaIzlistanje() {
		if(!brojeviRadnikaPoDanima.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("U bazi postoje unosi broja radnika po danima sa datumom primjene od-do", false);
			for (BrojRadnikaPoDanima brojRadnika : brojeviRadnikaPoDanima) {
				System.out.println(counter + " " + brojRadnika.toString());
				counter++;
			}
		}else {
			System.out.println(porukaGreskeNemaBrojRadnikaPoDanima);
		}
		
	}
	
	/**
	 *  
	 * BROJ RADNIKA PO DANIMA KRAJ
	 * 
	 * OZNAKE UNOSA U RASPORED
	 * 
	 */
	
	private void oznakeUnosaURasporedIzbornik() {
		Alati.ispisZaglavlja("Rad sa oznakama koje se unose u raspored", true);
		System.out.println("1 za novi unos oznake");
		System.out.println("2 za izmjenu postojeće oznake");
		System.out.println("3 za brisanje postojeće oznake");
		System.out.println("4 za pregled postojećih oznaka");
		System.out.println("5 za prikaz detalja određene oznake");
		System.out.println("6 za povratak u glavni korisnički izbornik");
		oznakeUnosaURasporedOdabirAkcije();
	}

	private void  oznakeUnosaURasporedOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> oznakeUnosaURasporedUnosNove();
			case 2 -> oznakeUnosaURasporedIzmjena();
			case 3 -> oznakeUnosaURasporedBrisanje();
			case 4 -> {
				oznakeUnosaURasporedIzlistanje();
				oznakeUnosaURasporedIzbornik();
			}
			case 5 ->  oznakeUnosaURasporedDetalji();
			case 6 -> autentificiraniKorisnikGlavniIzbornik();
		}		
	}
	
	// UNOS NOVE OZNAKE UNOSA U RASPORED
	private void oznakeUnosaURasporedUnosNove() {
		Alati.ispisZaglavlja("Unos nove oznake koja će se unositi u raspored", false);
		OznakaUnosaURaspored oznakaUnosaURaspored = new OznakaUnosaURaspored();		
		String skracenica = Alati.ucitajString("Koja će biti skraćenica nove oznaka: ", porukaGreskePraznogUnosa, 1, 3);
		if(!oznakeUnosaProvjeriPostojanjeSkracenice(skracenica)) {
			oznakaUnosaURaspored.setSkracenica(skracenica);
			oznakaUnosaURaspored.setNaziv(Alati.ucitajString("Koji će biti naziv nove oznake: ", porukaGreskePraznogUnosa, 1, 30));
			oznakeUnosaURaspored.add(oznakaUnosaURaspored);
			System.out.println("\nNova oznaka je unešena.");	
		}else {
			if(Alati.daNe("Oznaka unosa u raspored sa tom skraćenicom već postoji."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
				oznakeUnosaURasporedUnosNove();
			}
		}
		oznakeUnosaURasporedIzbornik();
	}
	
	//IZMJENA OZNAKE UNOSA U RASPORED
	private void oznakeUnosaURasporedIzmjena() {
		if(!oznakeUnosaURaspored.isEmpty()) {
			oznakeUnosaURasporedIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj ispred oznake koju želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
					oznakeUnosaURaspored.size()) - 1;
			OznakaUnosaURaspored oznakaUnosaURaspored = oznakeUnosaURaspored.get(i);
			oznakeUnosaURasporedIzmjenaPodataka(oznakaUnosaURaspored, i);	
		}else {
			System.out.println(porukaGreskeNemaOznakaUnosaURaspored);
			oznakeUnosaURasporedIzbornik();
		}			
	}
	
	private void oznakeUnosaURasporedIzmjenaPodataka(OznakaUnosaURaspored oznakaUnosaURaspored, int i) {
		while(true) {
			String skracenica = Alati.ucitajString("Koja će biti skraćenica nove oznaka: ", porukaGreskePraznogUnosa, 1, 3);
			if(!oznakeUnosaProvjeriPostojanjeSkracenice(skracenica, oznakaUnosaURaspored)) {
				oznakaUnosaURaspored.setSkracenica(skracenica);
				oznakaUnosaURaspored.setNaziv(Alati.ucitajString("Koji će biti naziv nove oznake: ", porukaGreskePraznogUnosa, 1, 30));
				oznakeUnosaURaspored.set(i, oznakaUnosaURaspored);
				System.out.println("\nOznaka unosa u raspored je izmjenjena.");	
			}else {
				if(Alati.daNe("Oznaka unosa u raspored sa tom skraćenicom već postoji."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", porukaGreskeDaNe)) {
					continue;
				}
			}
			oznakeUnosaURasporedIzbornik();
			break;
		}		
	}
	
	// BRISANJE OZNAKE UNOSA U RASPORED
	private void oznakeUnosaURasporedBrisanje() {
		if(!oznakeUnosaURaspored.isEmpty()) {
			oznakeUnosaURasporedIzlistanje();
			int i = Alati.ucitajBroj("Unesite broj oznake koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
					oznakeUnosaURaspored.size()) - 1;
			if(Alati.daNe("Želite li zaista obrisati oznaku " 
							+ oznakeUnosaURaspored.get(i).toString() + " (da/ne): ", porukaGreskeDaNe)) {
				oznakeUnosaURaspored.remove(i);
				System.out.println("\nOznaka je obrisana.");
			}		
		}else {
			System.out.println(porukaGreskeNemaOznakaUnosaURaspored);
		}	
		oznakeUnosaURasporedIzbornik();
	}
	
	// DETALJI OZNAKE UNOSA U RASPORED
	private void oznakeUnosaURasporedDetalji() {			
		if(!oznakeUnosaURaspored.isEmpty()) {
			oznakeUnosaURasporedIzlistanje();
			int i = Alati.ucitajBroj("Unesite redni broj oznake koju želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
					oznakeUnosaURaspored.size()) - 1;
			Alati.ispisZaglavlja("Detalji oznake", false);
			oznakeUnosaURaspored.get(i).ispisiDetalje();
		}else {
			System.out.println(porukaGreskeNemaOznakaUnosaURaspored);
		}	
		oznakeUnosaURasporedIzbornik();
	}	
	
	// POMOĆNE FUNKCIJE OZNAKE UNOSA U RASPORED
	
	// provjera kod unosa novog
	private boolean oznakeUnosaProvjeriPostojanjeSkracenice(final String skracenica) {
		return oznakeUnosaURaspored.stream().filter(o -> o.getSkracenica().equals(skracenica)).findFirst().isPresent();
	}
	
	// provjera kod izmjene
	private boolean oznakeUnosaProvjeriPostojanjeSkracenice(final String skracenica, OznakaUnosaURaspored oznakaUnosaURaspored) {
		boolean postojanje = false;
		for(OznakaUnosaURaspored our : oznakeUnosaURaspored) {
			if(!our.equals(oznakaUnosaURaspored)) {
				if(our.getSkracenica().equals(skracenica)) {
					postojanje = true;
					break;
				}
			}
		}
		return postojanje;		
	}

	private void oznakeUnosaURasporedIzlistanje() {
		if(!oznakeUnosaURaspored.isEmpty()) {
			int counter = 1;
			Alati.ispisZaglavlja("U bazi postoje slijedeće oznake unosa u raspored", false);
			for (OznakaUnosaURaspored oznakaUnosaURaspored : oznakeUnosaURaspored) {
				System.out.println(counter + " " + oznakaUnosaURaspored.toString());
				counter++;
			}		
		}else {
			System.out.println(porukaGreskeNemaOznakaUnosaURaspored);
		}			
	}
	
	/**
	 *  
	 * OZNAKE UNOSA U RASPORED KRAJ
	 * 
	 * RASPORED
	 * 
	 */
	
	private void rasporedIzbornik() {
		Alati.ispisZaglavlja("Rad sa rasporedom", true);
		System.out.println("1 za novi unos u raspored");
		System.out.println("2 za izmjenu postojećeg unosa u rasporedu");
		System.out.println("3 za brisanje postojećeg unosa u rasporedu");
		System.out.println("4 za prikaz rasporeda za određeni mjesec u određenoj godini");
		System.out.println("5 za povratak u glavni korisnički izbornik");
		rasporedOdabirAkcije();
	}

	private void  rasporedOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 6)) {
			case 1 -> raposredNoviUnos();
			case 2 -> raposredIzmjena();
			case 3 -> raposredBrisanje();
			case 4 -> rasporedPregledPoMjesecu();
			case 5 -> autentificiraniKorisnikGlavniIzbornik();
		}		
	}

	private void raposredNoviUnos() {	
		if(rasporedPostojanjeAktivnihKorisnika() && raposredPostojanjeOznakaUnosa()) {
			Korisnik odabraniKorisnik = new Korisnik();
			korisniciIzlistanjeAktivnihKorisnika("Aktivni korisnici koji se nalaze u bazi", aktivniKorisnici);
			int izbor = Alati.ucitajBroj("Unesite broj korisnika za kojeg želite stvoriti novi unos u rasporedu: ", 
									"Unos ne smije biti prazan", 1, aktivniKorisnici.size())-1;
			int indeksKorisnika = korisniciIndeksKorisnikaIzIzvorneListe(korisnici.get(izbor));
			odabraniKorisnik = korisnici.get(indeksKorisnika);
			Date datum = Alati.ucitajDatum("Unesite datum za koji želite stvoriti novi unos u rasporedu: ");
			if(rasporedProvjeriPostojanjeUnosa(odabraniKorisnik, datum)) {	
				Raspored unos = new Raspored();
				unos.setKorisnik(odabraniKorisnik);
				unos.setDatum(datum);
				unos = rasporedUnosPodataka(unos);
				rasporedi.add(unos);
			}else {
				if(Alati.daNe("Korisnik je već unešen u raspored sa tim datumom. Želite li pokušati opet? (da/ne): ", 
						porukaGreskeDaNe)) {
					raposredNoviUnos();				
				}
			}
		}		
		rasporedIzbornik();
	}	
	
	private Raspored rasporedUnosPodataka(Raspored unos) {
			oznakeUnosaURasporedIzlistanje();
			OznakaUnosaURaspored oznaka = oznakeUnosaURaspored.get(
					Alati.ucitajBroj("Odaberite oznaku zapisa koju želite spremiti u zapis: ", porukaGreskeIzboraAkcije, 1, oznakeUnosaURaspored.size())-1
					);
			unos.setOznakaUnosaURaspored(oznaka);
			unos.setRadSaPauzom(Alati.daNe("Radi li se taj dan sa pauzom? (da/ne): ", porukaGreskeDaNe));
			return unos;	
	}

	private void raposredIzmjena() {
		if(!rasporedi.isEmpty()) {
			int indeks  = rasporedPronalazakZapisa();					
			Raspored unos = new Raspored();
			unos = rasporedi.get(indeks);
			Korisnik odabraniKorisnik = new Korisnik();
			korisniciIzlistanjeAktivnihKorisnika("Aktivni korisnici koji se nalaze u bazi", aktivniKorisnici);
			int izbor = Alati.ucitajBroj("Unesite broj korisnika kojeg želite spremiti u promjene unosa: ", 
									"Unos ne smije biti prazan", 1, aktivniKorisnici.size())-1;
			int indeksKorisnika = korisniciIndeksKorisnikaIzIzvorneListe(korisnici.get(izbor));
			odabraniKorisnik = korisnici.get(indeksKorisnika);
			Date datum = Alati.ucitajDatum("Unesite datum koji želite spremiti u promjenu unosa: ");
			if(rasporedProvjeriPostojanjeUnosa(odabraniKorisnik, datum, unos)) {	
				unos.setKorisnik(odabraniKorisnik);
				unos.setDatum(datum);
				unos = rasporedUnosPodataka(unos);
				rasporedi.set(indeks,unos);
				System.out.println("\nUnos je izmijenjen");
			}else {
				if(Alati.daNe("Korisnik je već unešen u raspored sa tim datumom. Želite li pokušati opet? (da/ne): ", 
						porukaGreskeDaNe)) {
					raposredNoviUnos();				
				}
			}
		}		
		rasporedIzbornik();	
	}
	
	private void raposredBrisanje() {
		if(!rasporedi.isEmpty()) {
			int indeks  = rasporedPronalazakZapisa();
			if(Alati.daNe("Jeste li sigurni da želite obrisati taj unos (da/ne): ", porukaGreskeDaNe)) {				
				rasporedi.remove(indeks);
				System.out.println("\nUnos je obrisan");
			}			
		}else {
			System.out.println(porukaGreskeNemaOznakaUnosaURaspored);
		}
		rasporedIzbornik();
	}

	private void rasporedPregledPoMjesecu() {
		if(!rasporedi.isEmpty()) {
			List<Integer> aktivneGodine = new ArrayList<Integer>();
			List<Integer> aktivniMjeseci = new ArrayList<Integer>();
			aktivneGodine = rasporedAktivneGodine();		
			int izborGodine = Alati.ucitajBroj("Unesite broj ispreg godine za koju želite pogledati raposred: ", 
					"Unos ne smije biti prazan", 1, aktivneGodine.size())-1;
			Integer izabranaGodina = aktivneGodine.get(izborGodine);
			aktivniMjeseci = rasporedAktivniMjeseciUGodini(izabranaGodina);
			int izborMjeseca = Alati.ucitajBroj("Unesite broj ispreg mjeseca za koji želite pogledati raposred: ", 
					"Unos ne smije biti prazan", 1, aktivniMjeseci.size())-1;
			Integer izabraniMjesec = aktivniMjeseci.get(izborMjeseca);
			rasporedIspisZaGodinuIMjesec(izabranaGodina,izabraniMjesec);
			rasporedIzbornik();
		}else {
			System.out.println(porukaGreskeNemaZapisaURasporedu);
		}	
	}

	private void rasporedIspisZaGodinuIMjesec(Integer izabranaGodina, Integer izabraniMjesec) {
		
		List<Raspored> rasporedZaGodinuIMjesec = new ArrayList<Raspored>();
		rasporedZaGodinuIMjesec = rasporedZaGodinuIMjesec(izabranaGodina,izabraniMjesec);
		
		YearMonth obj = YearMonth.of(izabranaGodina, izabraniMjesec);
		int brojDanaUmjesecu = obj.lengthOfMonth();
		
		List<Korisnik> korisniciZaGodinuIMjesec = new ArrayList<Korisnik>();
		korisniciZaGodinuIMjesec = rasporedKorisniciZaGodinuIMjesec(rasporedZaGodinuIMjesec);
		
		Integer brojPocetnihRazmaka = rasporedBrojRazmaka(korisniciZaGodinuIMjesec);
		String pocetniRazmak = rasporedIspisiRazmake(brojPocetnihRazmaka);
		
		Alati.ispisZaglavlja("Raspored za " + izabraniMjesec + ". mjesec " + izabranaGodina + ". godine", false);		
		System.out.print(pocetniRazmak);
		for(int i=1;i<=brojDanaUmjesecu;i++) {
			if(i<10) {
				System.out.print(i + "   ");
			}else {
				System.out.print(i + "  ");
			}
			
		}		
		System.out.println();
		
		for(Korisnik k : korisniciZaGodinuIMjesec) {
			Integer naknadniBrojRazmaka = rasporedBrojRazmaka(k.imeIPrezime(),brojPocetnihRazmaka);
			String naknadniRazmak = rasporedIspisiRazmake(naknadniBrojRazmaka);
			System.out.print(k.imeIPrezime() + naknadniRazmak);
			for(int d=1;d<=brojDanaUmjesecu;d++) {
				String zapis = "-   ";
				for(Raspored r : rasporedZaGodinuIMjesec) {
					Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
					Integer mjesec = Integer.parseInt(Alati.hrMjesec(r.getDatum()));
					Integer dan = Integer.parseInt(Alati.hrDan(r.getDatum()));
					if(r.getKorisnik().equals(k) && godina.equals(izabranaGodina) 
							&& mjesec.equals(izabraniMjesec) && dan.equals(d)) {
						zapis = r.getOznakaUnosaURaspored().getSkracenica() + "   ";
					}
				}
				System.out.print(zapis);
			}
			System.out.println();
		}		
	}
	
	// POMOĆNE FUNKCIJE RASPOREDA

	// provjera potojanja zapisa u rasporedu kod unosa novog
	private boolean rasporedProvjeriPostojanjeUnosa(final Korisnik korisnik, final Date datum) {	
		boolean valjanost = true;
		for(Raspored raspored : rasporedi) {
			if(raspored.getKorisnik().equals(korisnik) && raspored.getDatum().equals(datum)) {				
				valjanost = false;
				break;		
			}
		}
		return valjanost;	
	}
	
	private boolean rasporedProvjeriPostojanjeUnosa(final Korisnik korisnik, final Date datum, Raspored unos) {	
		boolean valjanost = true;
		for(Raspored raspored : rasporedi) {
			if(raspored.getKorisnik().equals(korisnik) && raspored.getDatum().equals(datum) && !raspored.equals(unos)) {				
				valjanost = false;
				break;		
			}
		}
		return valjanost;	
	}
	
	private boolean rasporedPostojanjeAktivnihKorisnika() {
		boolean valjanost = true;
		if(aktivniKorisnici.isEmpty()) {
			System.out.println("\nU bazi mora postojati barem jedan aktivan korisnik za nastavak ove radnje.");
			valjanost = false;			
		}
		return valjanost;
	}
	
	private boolean raposredPostojanjeOznakaUnosa() {
		boolean valjanost = true;
		if(oznakeUnosaURaspored.isEmpty()) {
			System.out.println("\nU bazi mora postojati barem jedna oznaka unosa u raspored za nastavak ove radnje.");
			valjanost = false;			
		}
		return valjanost;
	}
	
	private List<Integer> rasporedAktivneGodine() {
		int counter = 1;
		List<Integer> akrivneGodine = new ArrayList<Integer>();
		Alati.ispisZaglavlja("U bazi postoje zapisi u rasporedu sa idućim godinama", false);
		for (Raspored r : rasporedi) {
			Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
			if(!akrivneGodine.contains(godina)) {
				akrivneGodine.add(godina);
			}
		}
		Collections.sort(akrivneGodine);
		for(Integer g : akrivneGodine) {
			System.out.println(counter + " " + g);
			counter++;
		}
		return akrivneGodine;
	}
	
	private List<Raspored> rasporedZaGodinuIMjesec(Integer izabranaGodina, Integer izabraniMjesec) {
		List<Raspored> rasporedZaGodinuImjesec = new ArrayList<Raspored>();
		for (Raspored r : rasporedi) {
			Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
			Integer mjesec = Integer.parseInt(Alati.hrMjesec(r.getDatum()));
			if(godina.equals(izabranaGodina) && mjesec.equals(izabraniMjesec)) {
				rasporedZaGodinuImjesec.add(r);
			}
		}
		Collections.sort(rasporedZaGodinuImjesec);
		return rasporedZaGodinuImjesec;
	}
	
	private List<Korisnik> rasporedKorisniciZaGodinuIMjesec(List<Raspored> rasporedZaGodinuIMjesec) {
		List<Korisnik> korisniciZaGodiniIMjesec = new ArrayList<Korisnik>();
		for(Raspored r : rasporedZaGodinuIMjesec) {
			if(!korisniciZaGodiniIMjesec.contains(r.getKorisnik())) {
				korisniciZaGodiniIMjesec.add(r.getKorisnik());
			}
		}
		return korisniciZaGodiniIMjesec;
	}
	
	private List<Integer> rasporedAktivniMjeseciUGodini(Integer aktivnaGodina) {
		int counter = 1;
		List<Integer> aktivniMjeseci = new ArrayList<Integer>();
		Alati.ispisZaglavlja("Za " + aktivnaGodina + ". godinu u bazi postoje zapisi u rasporedu sa idućim mjesecima", false);
		for (Raspored r : rasporedi) {
			Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
			Integer mjesec = Integer.parseInt(Alati.hrMjesec(r.getDatum()));
			if(aktivnaGodina.equals(godina) && !aktivniMjeseci.contains(mjesec)) {
				aktivniMjeseci.add(mjesec);
			}			
		}
		Collections.sort(aktivniMjeseci);
		for(Integer m : aktivniMjeseci) {
			System.out.println(counter + " " + m + ". mjesec " + aktivnaGodina + ". godine");
			counter++;
		}
		return aktivniMjeseci;
	}
	
	private int rasporedPronalazakZapisa() {
		List<Integer> aktivneGodine = new ArrayList<Integer>();
		List<Integer> aktivniMjeseci = new ArrayList<Integer>();
		List<Korisnik> korisniciUMjesecuIGodini = new ArrayList<Korisnik>();
		List<Raspored> rasporedPoKorisnikuMjesecuIGodini = new ArrayList<Raspored>();
		Korisnik izabraniKorisnik = new Korisnik();
		Raspored izabraniUnos = new Raspored();
		int izborGodine, izborMjeseca, izborKorisnika, izborUnosa;
		aktivneGodine = rasporedAktivneGodine();		
		izborGodine = Alati.ucitajBroj("Unesite broj ispred godine u kojoj tražite unos: ", 
				"Unos ne smije biti prazan", 1, aktivneGodine.size())-1;
		Integer izabranaGodina = aktivneGodine.get(izborGodine);
		aktivniMjeseci = rasporedAktivniMjeseciUGodini(izabranaGodina);
		izborMjeseca = Alati.ucitajBroj("Unesite broj ispreg mjeseca u kojem tražite unos: ", 
				"Unos ne smije biti prazan", 1, aktivniMjeseci.size())-1;
		Integer izabraniMjesec = aktivniMjeseci.get(izborMjeseca);
		korisniciUMjesecuIGodini = rasporedKorisniciUMjesecuIGodini(izabranaGodina,izabraniMjesec);
		izborKorisnika = Alati.ucitajBroj("Unesite broj ispreg korisnika za kojeg tražite unos: ", 
				"Unos ne smije biti prazan", 1, korisniciUMjesecuIGodini.size())-1;
		izabraniKorisnik = korisniciUMjesecuIGodini.get(izborKorisnika);
		rasporedPoKorisnikuMjesecuIGodini = rasporedPoKorisnikuMjesecuIGodini(
				izabranaGodina,izabraniMjesec,izabraniKorisnik);
		izborUnosa = Alati.ucitajBroj("Unesite broj ispreg datuma za odabir unosa: ", 
				"Unos ne smije biti prazan", 1, rasporedPoKorisnikuMjesecuIGodini.size())-1;
		izabraniUnos = rasporedPoKorisnikuMjesecuIGodini.get(izborUnosa);
		return rasporedIndeksUnosaIzIzvorneListe(izabraniUnos);
	}
	
	private List<Korisnik> rasporedKorisniciUMjesecuIGodini(Integer izabranaGodina, Integer izabraniMjesec) {
		int counter = 1;
		List<Korisnik> korisnici = new ArrayList<Korisnik>();
		Alati.ispisZaglavlja("U " + izabraniMjesec + "." + izabranaGodina + ". postoje unosi za sljedeće korisnike", false);
		for (Raspored r : rasporedi) {
			Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
			Integer mjesec = Integer.parseInt(Alati.hrMjesec(r.getDatum()));
			if(izabranaGodina.equals(godina) && !izabraniMjesec.equals(mjesec) 
					&& !korisnici.contains(r.getKorisnik())) {
				korisnici.add(r.getKorisnik());
			}			
		}
		for(Korisnik k : korisnici) {
			System.out.println(counter + " " + k.imeIPrezime());
			counter++;
		}
		return korisnici;
	}
	
	private List<Raspored> rasporedPoKorisnikuMjesecuIGodini(Integer izabranaGodina, Integer izabraniMjesec,
			Korisnik izabraniKorisnik) {
		int counter = 1;
		List<Raspored> rasporedPoGodiniMjesecuIKorisniku = new ArrayList<Raspored>();
		Alati.ispisZaglavlja("U " + izabraniMjesec + ". mjesecu " + izabranaGodina + ". godine "
							+ "zakorisnika " + izabraniKorisnik.imeIPrezime() 
							+ " postoje unosi sa idućim datumima", false);
		for(Raspored r : rasporedi) {
			Integer godina = Integer.parseInt(Alati.hrGodina(r.getDatum()));
			Integer mjesec = Integer.parseInt(Alati.hrMjesec(r.getDatum()));
			if(godina.equals(izabranaGodina) && mjesec.equals(izabraniMjesec) 
					&& r.getKorisnik().equals(izabraniKorisnik) 
					&& !rasporedPoGodiniMjesecuIKorisniku.contains(r)) {
				rasporedPoGodiniMjesecuIKorisniku.add(r);
			}
		}		
		Collections.sort(rasporedPoGodiniMjesecuIKorisniku);
		for(Raspored r : rasporedPoGodiniMjesecuIKorisniku) {
			System.out.println(counter + " " + Alati.hrDatum(r.getDatum()));
			counter++;
		}
		return rasporedPoGodiniMjesecuIKorisniku;		
	}

	private int rasporedIndeksUnosaIzIzvorneListe(Raspored raspored) {
		return rasporedi.indexOf(raspored);
	}
	
	private Integer rasporedBrojRazmaka(List<Korisnik> aktivniKorisniciZaGodiniIMjesec) {
		Integer brojRazmaka = 1;
		int maxLength = 0;
		for(Korisnik k : aktivniKorisniciZaGodiniIMjesec) {
			int korisnikImeLength = k.imeIPrezime().length();
			if(korisnikImeLength>maxLength) {
				maxLength = korisnikImeLength;
			}
		}		
		if(maxLength>7 && maxLength<=15) {
			brojRazmaka = 2;
		}		
		if(maxLength>15 && maxLength<=23) {
			brojRazmaka = 3;
		}		
		if(maxLength>23 && maxLength<=31) {
			brojRazmaka = 4;
		}		
		if(maxLength>31 && maxLength<=39) {
			brojRazmaka = 5;
		}		
		if(maxLength>39 && maxLength<=47) {
			brojRazmaka = 6;
		}		
		if(maxLength>47 && maxLength<=51) {
			brojRazmaka = 7;
		}		
		return brojRazmaka;
	}
	
	private String rasporedIspisiRazmake(Integer brojPocetnihRazmaka) {
		String razmak = "";
		if(brojPocetnihRazmaka!=0) {
			for(int i=0; i<brojPocetnihRazmaka;i++) {
				razmak += "\t";
			}
		}		
		return razmak;
	}
	
	private Integer rasporedBrojRazmaka(String imeIPrezime, Integer brojPocetnihRazmaka) {
		Integer brojRazmaka = 1;
		int length = imeIPrezime.length();		
		if(length>=8 && length<16) {
			brojRazmaka = 2;
		}		
		if(length>=16 && length<24) {
			brojRazmaka = 3;
		}		
		if(length>=24 && length<32) {
			brojRazmaka = 4;
		}		
		if(length>=32 && length<40) {
			brojRazmaka = 5;
		}		
		if(length>=40 && length<=48) {
			brojRazmaka = 6;
		}		
		if(length>=48 && length<=51) {
			brojRazmaka = 7;
		}		
		return brojPocetnihRazmaka-brojRazmaka+1;
	}
	
	/**
	 *
	 * RASPORED KRAJ
	 * 
	 */
	
	public static void main(String[] args) {
		new Start();
	}

}
