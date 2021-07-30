package raspored;

import java.awt.Desktop;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Start {

	public static final String LINK_POVEZNICE = "https://github.com/mikecivan0/RasporedRada";
	private List<Osoba> osobe;
	private List<Korisnik> korisnici;
	private List<RedovnoRadnoVrijeme> redovnaRadnaVremena;
	private List<IznimnoRadnoVrijeme> iznimnaRadnaVremena;
	private boolean valjanost = false;
	private String porukaIzboraAkcije = "Unesite neku od gore ponuđenih stavki: ";
	private String porukaGreskeIzboraAkcije = "Nepostojeći izbor";
	private String porukaGreskePraznogUnosa = "Unos ne smije bti prazan";
	private String porukaGreskeUnosaCijelogBroja = "Molimo unesite cijeli broj";
	SimpleDateFormat formatDatuma;
	SimpleDateFormat formatVremena;

	public Start() {
		korisnici = new ArrayList<Korisnik>();
		osobe = new ArrayList<Osoba>();
		redovnaRadnaVremena = new ArrayList<RedovnoRadnoVrijeme>();
		iznimnaRadnaVremena = new ArrayList<IznimnoRadnoVrijeme>();

		// početak probnih podataka
		osobe.add(new Osoba("Ivan", "Mikec", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko", "Drugi", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko1", "treći", "091", "email", "adresa"));

		korisnici.add(new Korisnik(osobe.get(0), "ja", "ja", "2-654", 1, true));
		korisnici.add(new Korisnik(osobe.get(1), "on", "on", "2-6545", 1, true));
		
		formatDatuma = new SimpleDateFormat("dd.MM.yyyy.");
		formatVremena = new SimpleDateFormat("HH:mm");
		
		try {
			redovnaRadnaVremena.add(new RedovnoRadnoVrijeme(
					formatDatuma.parse("01.01.2020."),
					formatDatuma.parse("01.03.2020."),
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
					formatDatuma.parse("01.04.2020."),
					formatDatuma.parse("01.06.2020."),
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
		
		try {
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("01.01.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Nova Godina 2020.",
					30));
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("01.03.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Neki blagdan 2020.",
					30));
			iznimnaRadnaVremena.add(new IznimnoRadnoVrijeme(
					formatDatuma.parse("01.05.2020."),
					formatVremena.parse("02:00"),
					formatVremena.parse("04:00"),
					"Praznik rada 2020.",
					30));
		} catch (ParseException e) {
			System.out.println("Nesto je pošlo po zlu unosom iznimnog radnog vremena");
		}
		// kraj probnih podataka

		
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
		if (Alati.daNe("Želite li pokušati ponovno? (da/ne): ", "Unesite da ili ne")) {
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
		System.out.println("5 za rad sa brojem radnika po danima");
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
				System.out.println();
				System.out.println("Osoba je spremljena. Što želite dalje?");
				osobeIzbornik();
			}
			case 2 -> osobeIzmjena();
			case 3 -> osobeBrisanje();
			case 4 -> {
				osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
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
		osobeIspisIzboraPretrage("Izmjena podataka osobe");
		osobeUcitajOdabirPretrageZaIzmjenu();
	}

	private void osobeUcitajOdabirPretrageZaIzmjenu() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeIzmjenaPoIndeksu();
			case 2 -> osobeIzmjenaPoImenu();
		}
	}
	
	private void osobeIzmjenaPoIndeksu() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		Osoba osoba = osobe.get(i);
		osobeIzmjenaPodataka(osoba, i);
	}

	private void osobeIzmjenaPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (nadjeneOsobe.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				osobeIzmjenaPoImenu();
			} else {
				osobeIzbornik();
			}
		} else {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer offset = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < osobe.size(); i++) {
				Osoba osoba = osobe.get(i);
				if (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet)) {
					if (offsetCounter.equals(offset)) {
						osobeIzmjenaPodataka(osoba, i);
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}
	
	private void osobeIzmjenaPodataka(Osoba osoba, int i) {
		osoba = osobeUnosPodataka(osoba);
		osobe.set(i, osoba);
		System.out.println("");
		System.out.println("Podaci osobe su uspješno izmjenjeni. Što želite dalje?");
		osobeIzbornik();
	}
	
	// BRISANJE OSOBE	
	private void osobeBrisanje() {
		osobeIspisIzboraPretrage("Brisanje osobe");
		osobeUcitajOdabirPretrageZaBrisanje();
	}

	private void osobeUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeBrisanjePoIndeksu();
			case 2 -> osobeBrisanjePoImenu();
		}
	}
	
	private void osobeBrisanjePoIndeksu() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		if(Alati.daNe("Želite li zaista obrisati osobu (" + osobe.get(i).toString() + "): ", "Molimo unesite da ili ne")) {
			osobe.remove(i);
			System.out.println();
			System.out.println("Osoba je obrisana.");
		}		
		osobeIzbornik();		
	}

	private void osobeBrisanjePoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if (nadjeneOsobe.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				osobeBrisanjePoImenu();
			} else {
				osobeIzbornik();
			}
		} else {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer offset = Alati.ucitajBroj("Unesite broj osobe koju želite obrisati: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < osobe.size(); i++) {
				Osoba osoba = osobe.get(i);
				if (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet)) {
					if (offsetCounter.equals(offset)) {
						if(Alati.daNe("Želite li zaista obrisati odabranu osobu (" + osoba.toString() + "): ", "Molimo unesite da ili ne")) {
							osobe.remove(i);
							System.out.println();
							System.out.println("Osoba je obrisana.");
							
						}
						osobeIzbornik();						
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}

	// DETALJI OSOBE
	private void osobeDetalji() {
		osobeIspisIzboraPretrage("Detalji osobe");
		osobeUcitajOdabirPretrageZaIspisDetalja();	
	}

	private void osobeUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeDetaljiPoIndeksu();
			case 2 -> osobeDetaljiPoImenu();
		}		
	}
	
	private void osobeDetaljiPoIndeksu() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
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
		if (nadjeneOsobe.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				osobeDetaljiPoImenu();
			} else {
				osobeIzbornik();
			}
		} else {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer offset = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
					1, nadjeneOsobe.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < osobe.size(); i++) {
				Osoba osoba = osobe.get(i);
				if (osobeJeLiToOsobaPoImenuIPrezimenu(osoba, uvjet)) {
					if (offsetCounter.equals(offset)) {
						Alati.ispisZaglavlja("Detalji osobe", false);
						osobe.get(i).ispisiDetalje();		
						osobeIzbornik();						
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}	

	// POMOĆNE METODE OSOBA
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
	
	private void osobeIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
	}
	
	private Osoba osobeUnosPodataka(Osoba osoba) {
		osoba.setIme(Alati.ucitajString("ime osobe: ", porukaGreskePraznogUnosa, 1, 15));
		osoba.setPrezime(Alati.ucitajString("prezime osobe: ", porukaGreskePraznogUnosa, 1, 25));
		osoba.setAdresa(Alati.ucitajString("adresa osobe: ", porukaGreskePraznogUnosa, 1, 100));
		if (Alati.daNe("Želite li unijeti telefon osobe? (da/ne): ", "Unesite da ili ne")) {
			osoba.setTelefon(Alati.ucitajString("telefon osobe: ", porukaGreskePraznogUnosa, 1, 20));
		} else {
			osoba.setTelefon("");
		}
		if (Alati.daNe("Želite li unijeti email adresu osobe? (da/ne): ", "Unesite da ili ne")) {
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
		System.out.println("1 za unos postojeće osobe kao novog korisnika");
		System.out.println("2 za unos nove osobe kao novog korisnika");
		System.out.println("3 za izmjenu postojećeg korisnika");
		System.out.println("4 za brisanje postojećeg korisnika");
		System.out.println("5 za pregled svih korisnika");
		System.out.println("6 za pregled detalja postojećeg korisnika");
		System.out.println("7 za povratak u glavni korisnički izbornik");
		korisniciOdabirAkcije();
	}

	private void korisniciOdabirAkcije() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 7)) {
			case 1 -> korisniciUnosPostojeceOsobeKaoNovogKorisnika();
			case 2 -> korisniciUnosNoveOsobeKaoNovogKorisnika();
			case 3 -> korisniciIzmjena();
			case 4 -> korisniciBrisanje();
			case 5 -> {
				korisniciIzlistanje("Korisnici koji se nalaze u bazi", korisnici);
				korisniciIzbornik();
			}
			case 6 -> {
				korisniciDetalji();
				korisniciIzbornik();
			}
			case 7 -> autentificiraniKorisnikGlavniIzbornik();
		}		
	}
	
	// UNOS NOVOG KORISNIKA
	private void korisniciUnosNoveOsobeKaoNovogKorisnika() {
		Korisnik korisnik = new Korisnik();
		osobeUnosNove();
		korisnik.setOsoba(osobe.get(osobe.size()-1));		
		while(true) {
			String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
			if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnici, korisnickoIme)) {
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik = korisniciUnosOstalihPodataka(korisnik);
				korisnici.add(korisnik);
				System.out.println();
				System.out.println("Nova osoba je unešena i postavljena kao novi korisnik");	
			}else {
				if(Alati.daNe("Korisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
					continue;
				}else {
					osobe.remove(osobe.size()-1);
				}
			}
			korisniciIzbornik();
			break;
		}		
	}

	private void korisniciUnosPostojeceOsobeKaoNovogKorisnika() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		Osoba osoba = osobe.get(Alati.ucitajBroj("Unesite broj osobe koju želite dodati kao korisnika: ", "Unos ne smije biti prazan", 1, osobe.size())-1);
		if(!korisniciJeLiOsobaVecKorisnik(osoba)) {
			Korisnik korisnik = new Korisnik();
			korisnik.setOsoba(osoba);
			while(true) {
				String korisnickoIme = Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50);
				if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnici, korisnickoIme)) {
					korisnik.setKorisnickoIme(korisnickoIme);
					korisnik = korisniciUnosOstalihPodataka(korisnik);
					korisnici.add(korisnik);
					System.out.println();
					System.out.println("Nova osoba je unešena i postavljena kao novi korisnik");
				}else {
					if(Alati.daNe("Korisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
						continue;
					}
				}
				korisniciIzbornik();
				break;
			}			
		}else {
			if(Alati.daNe("Osoba je već unešena kao korisnik. Želite li odabrati drugu osobu? (da/ne): ", "Molimo unesite da ili ne")) {
				korisniciUnosPostojeceOsobeKaoNovogKorisnika();
			}else {
				korisniciIzbornik();
			}
		}
	}

	// IZMJENA KORISNIKA
	private void korisniciIzmjena() {
		korisniciIspisIzboraPretrage("Izmjena podataka korisnika");
		korisniciUcitajOdabirPretrage();
	}
	
	private void korisniciUcitajOdabirPretrage() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciIzmjenaPoIndeksu();
			case 2 -> korisniciIzmjenaPoImenu();
		}
		
	}

	private void korisniciIzmjenaPoIndeksu() {
		korisniciIzlistanje("Korisnici koji se nalaze u bazi", korisnici);
		int i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size()) - 1;
		Korisnik korisnik = korisnici.get(i);
		korisniciIzmjenaPodataka(korisnik, i);
	}

	private void korisniciIzmjenaPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Korisnik> nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (nadjeniKorisnici.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				korisniciIzmjenaPoImenu();
			} else {
				korisniciIzbornik();
			}
		} else {
			korisniciIzlistanje("Pronađeni korsnici", nadjeniKorisnici);
			Integer offset = Alati.ucitajBroj("Unesite broj korisnika kojeg želite izmijeniti: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < korisnici.size(); i++) {
				Korisnik korisnik = korisnici.get(i);
				if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
					if (offsetCounter.equals(offset)) {
						korisniciIzmjenaPodataka(korisnik, i);
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}	

	private void korisniciIzmjenaPodataka(Korisnik korisnik, int i) {
		while(true) {
			String korisnickoIme = Alati.ucitajString("Unesite korisničko ime korisnika: ", porukaGreskePraznogUnosa, 1, 50);
			if(!korisniciProvjeriPostojanjeKorisnickogImena(korisnici, korisnickoIme)) {
				korisnik.setKorisnickoIme(korisnickoIme);
				korisnik = korisniciUnosOstalihPodataka(korisnik);
				korisnici.set(i, korisnik);
				System.out.println("");
				System.out.println("Novi podaci korisnika su spremljeni");
			}else {
				if(Alati.daNe("Korisničko ime je zauzeto. Želite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
					continue;
				}
			}
			korisniciIzbornik();
			break;
		}		
	}
	
	// BRISANJE KORISNIKA
	private void korisniciBrisanje() {
		korisniciIspisIzboraPretrage("Brisanje korisnika");
		korisniciUcitajOdabirPretrageZaBrisanje();
	}

	private void korisniciUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciBrisanjePoIndeksu();
			case 2 -> korisniciBrisanjePoNazivu();
		}		
	}
	
	private void korisniciBrisanjePoIndeksu() {
		korisniciIzlistanje("Korisnici koj1 se nalaze u bazi", korisnici);
		int i = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size()) - 1;
		if(Alati.daNe("Želite li zaista obrisati korisnika (" + korisnici.get(i).korisnikZaPrikaz() + "): ", "Molimo unesite da ili ne")) {
			korisnici.remove(i);
			System.out.println();
			System.out.println("Korisnik je obrisan.");
		}		
		korisniciIzbornik();
	}

	private void korisniciBrisanjePoNazivu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime ili korisničko ime korisnika kojeg tražite: ", porukaGreskePraznogUnosa, 0,
				30);
		List<Korisnik> nadjeniKorisnici = korisniciPronadjiPoUvjetu(uvjet);
		if (nadjeniKorisnici.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				korisniciBrisanjePoNazivu();
			} else {
				korisniciIzbornik();
			}
		} else {
			korisniciIzlistanje("Pronađene osobe", nadjeniKorisnici);
			Integer offset = Alati.ucitajBroj("Unesite broj korisnika kojeg želite obrisati: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < korisnici.size(); i++) {
				Korisnik korisnik = korisnici.get(i);
				if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
					if (offsetCounter.equals(offset)) {
						if(Alati.daNe("Želite li zaista obrisati odabranog korisnika (" + korisnici.get(i).korisnikZaPrikaz() + "): ", "Molimo unesite da ili ne")) {
							korisnici.remove(i);
							System.out.println();
							System.out.println("Korisnik je obrisan.");
							
						}
						korisniciIzbornik();						
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}	
	
	// DETALJI KORISNIKA
	private void korisniciDetalji() {
		korisniciIspisIzboraPretrage("Detalji korisnika");
		korisniciUcitajOdabirPretrageZaIspisDetalja();			
	}

	private void korisniciUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciDetaljiPoIndeksu();
			case 2 -> korisniciDetaljiPoImenu();
		}		
	}
	
	private void korisniciDetaljiPoIndeksu() {
		korisniciIzlistanje("Korisici koji se nalaze u bazi", korisnici);
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
		if (nadjeniKorisnici.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if (Alati.daNe("Želite li pokušati opet? (da/ne): ", "Unesite da ili ne")) {
				korisniciDetaljiPoImenu();
			} else {
				korisniciIzbornik();
			}
		} else {
			korisniciIzlistanje("Pronađeni korisnici", nadjeniKorisnici);
			Integer offset = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije,
					1, nadjeniKorisnici.size());
			Integer offsetCounter = 1;
			for (int i = 0; i < korisnici.size(); i++) {
				Korisnik korisnik = korisnici.get(i);
				if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
					if (offsetCounter.equals(offset)) {
						Alati.ispisZaglavlja("Detalji korisnika", false);
						korisnici.get(i).ispisiDetalje();		
						korisniciIzbornik();						
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}	
	
	// POMOĆNE METODE KORISNIKA
	private boolean korisniciProvjeriPostojanjeKorisnickogImena(final List<Korisnik> korisnici, final String korisnickoIme) {
		return korisnici.stream().filter(o -> o.getKorisnickoIme().equals(korisnickoIme)).findFirst().isPresent();
	}
	
	private void korisniciIspisIzboraPretrage(String naslov) {
		Alati.ispisZaglavlja(naslov, true);
		System.out.println("1 za izlistanje svih korisnika od kojih će te izabrati željenog");
		System.out.println("2 za pretragu korisnika po imenu i/ili prezimenu ili korisničkom imenu");
	}
	
	private boolean korisniciJeLiOsobaVecKorisnik(Osoba osoba) {
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
		korisnik.setLozinka(Alati.ucitajString("Unesite lozinku novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50));
		korisnik.setOsobniBroj(Alati.ucitajString("Unesite osobni broj novoga korisnika: ", porukaGreskePraznogUnosa, 1, 10));
		korisnik.setAktivan(Alati.daNe("Hoće li korisnik biti aktivan (da/ne): ", porukaGreskeIzboraAkcije));
		korisnik.setRazina(Alati.ucitajBroj("Unesite razinu korisnika (1 ili 2): ", porukaGreskeIzboraAkcije, 1, 2));
		
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
	
	private List<Korisnik> korisniciPronadjiPoUvjetu(String uvjet) {
		List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		for (Korisnik korisnik : korisnici) {
			if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
				listaKorisnika.add(korisnik);
			}
		}
		return listaKorisnika;
	}
	
	private void korisniciIzlistanje(String poruka, List<Korisnik> korisnici) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Korisnik korisnik : korisnici) {
			System.out.println(counter + " " + korisnik.toString());
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
				redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do", redovnaRadnaVremena);
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
		if(redovnaRadnaVremenaProvjeriPreklapanje(redovnaRadnaVremena, datumPocetkaPrimjene) 
				&& redovnaRadnaVremenaProvjeriPreklapanje(redovnaRadnaVremena, datumKrajaPrimjene)) {
			redovnoRadnoVrijeme.setVrijediOd(datumPocetkaPrimjene);
			redovnoRadnoVrijeme.setVrijediDo(datumKrajaPrimjene);
			redovnoRadnoVrijeme = redovnaRadnaVremenaUnesiOstaleVrijednosti(redovnoRadnoVrijeme);
			redovnaRadnaVremena.add(redovnoRadnoVrijeme);
			System.out.println();
			System.out.println("Novo redovno radno vrijeme je unešeno");	
		}else {
			if(Alati.daNe("Redovno radno vrijeme u tom intervalu primjene je već unešeno."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
				redovnaRadnaVremenaUnosNovog();
			}
		}
		redovnaRadnaVremenaIzbornik();
	}
	
	//IZMJENA REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaIzmjena() {
		redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do", redovnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite broj ispred redovnog radnog vremena koje želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
				redovnaRadnaVremena.size()) - 1;
		RedovnoRadnoVrijeme redovnoRadnoVrijeme = redovnaRadnaVremena.get(i);
		redovnaRadnaVremenaIzmjenaPodataka(redovnoRadnoVrijeme, i);		
	}
	
	private void redovnaRadnaVremenaIzmjenaPodataka(RedovnoRadnoVrijeme redovnoRadnoVrijeme, int i) {
		while(true) {
			Date datumPocetkaPrimjene = Alati.ucitajDatum("Od kada će početi vrijediti nova radna vremena: ");
			Date datumKrajaPrimjene = Alati.ucitajDatum("Do kada će vrijediti nova radna vremena: ");
			if(redovnaRadnaVremenaProvjeriPreklapanje(redovnaRadnaVremena, datumPocetkaPrimjene) 
					&& redovnaRadnaVremenaProvjeriPreklapanje(redovnaRadnaVremena, datumKrajaPrimjene)) {
				redovnoRadnoVrijeme.setVrijediOd(datumPocetkaPrimjene);
				redovnoRadnoVrijeme.setVrijediDo(datumKrajaPrimjene);
				redovnoRadnoVrijeme = redovnaRadnaVremenaUnesiOstaleVrijednosti(redovnoRadnoVrijeme);
				redovnaRadnaVremena.set(i, redovnoRadnoVrijeme);
				System.out.println();
				System.out.println("Redovno radno vrijeme je izmijenjeno");	
			}else {
				if(Alati.daNe("Radno vrijeme u tom intervalu primjene je već unešeno."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
					continue;
				}
			}
			redovnaRadnaVremenaIzbornik();
			break;
		}		
	}
	
	// BRISANJE REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaBrisanje() {
		redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do", redovnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite broj unosa koji želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				redovnaRadnaVremena.size()) - 1;
		if(Alati.daNe("Želite li zaista obrisati radno vrijeme sa datumom primjene " + redovnaRadnaVremena.get(i).toString() + ": ", "Molimo unesite da ili ne")) {
			redovnaRadnaVremena.remove(i);
			System.out.println();
			System.out.println("Unos je obrisan.");
		}		
		redovnaRadnaVremenaIzbornik();
	}
	
	// DETALJI REDOVNOG RADNOG VREMENA
	private void redovnaRadnaVremenaDetalji() {			
		redovnaRadnaVremenaIzlistanje("U bazi postoje redovna radna vremena sa datumom primjene od-do", redovnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite redni broj redovnog radnog vremena koje želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
				redovnaRadnaVremena.size()) - 1;
		Alati.ispisZaglavlja("Detalji redovnog radnog vremena", false);
		redovnaRadnaVremena.get(i).ispisiDetalje();
		redovnaRadnaVremenaIzbornik();		
	}	
	
	// POMOĆNE FUNKCIJE REDOVNOG RADNOG VREMENA
	private boolean redovnaRadnaVremenaProvjeriPreklapanje(final List<RedovnoRadnoVrijeme> redovnaRadnaVremena, final Date datum) {		
		boolean valjanost = true;
		for(RedovnoRadnoVrijeme rrv : redovnaRadnaVremena) {
			if(rrv.getVrijediOd().before(datum) && rrv.getVrijediDo().after(datum)) {
				valjanost = false;
				break;
			}
			continue;
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
	
	private void redovnaRadnaVremenaIzlistanje(String poruka, List<RedovnoRadnoVrijeme> redovnaRadnaVremena) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (RedovnoRadnoVrijeme rrv : redovnaRadnaVremena) {
			System.out.println(counter + " " + rrv.toString());
			counter++;
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
				iznimnaRadnaVremenaIzlistanje("U bazi postoje sljedeća iznimna radna vremena", iznimnaRadnaVremena);
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
		if(!iznimnaRadnaVremenaProvjeriPreklapanje(iznimnaRadnaVremena, datum)) {
			iznimnoRadnoVrijeme.setDatum(datum);
			iznimnoRadnoVrijeme = iznimnaRadnaVremenaUnesiOstaleVrijednosti(iznimnoRadnoVrijeme);
			iznimnaRadnaVremena.add(iznimnoRadnoVrijeme);
			System.out.println();
			System.out.println("Novo iznimno radno vrijeme je unešeno");	
		}else {
			if(Alati.daNe("Iznimno radno vrijeme sa tim datumom je već unešeno."
					+ "\nŽelite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
				iznimnaRadnaVremenaUnosNovog();
			}
		}
		iznimnaRadnaVremenaIzbornik();
	}
	
	//IZMJENA IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaIzmjena() {
		iznimnaRadnaVremenaIzlistanje("U bazi postoje sjedeća iznimna radna", iznimnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite broj iznimnog radnog vremena koje želite izmjeniti: ", porukaGreskeIzboraAkcije, 1,
				iznimnaRadnaVremena.size()) - 1;
		IznimnoRadnoVrijeme iznimnoRadnoVrijeme = iznimnaRadnaVremena.get(i);
		iznimnaRadnaVremenaIzmjenaPodataka(iznimnoRadnoVrijeme, i);		
	}
	
	private void iznimnaRadnaVremenaIzmjenaPodataka(IznimnoRadnoVrijeme iznimnoRadnoVrijeme, int i) {
		while(true) {
			Date datum = Alati.ucitajDatum("Unesite datum iznimnog radnog vremena: ");
			if(!iznimnaRadnaVremenaProvjeriPreklapanje(iznimnaRadnaVremena, datum)) {
				iznimnoRadnoVrijeme.setDatum(datum);
				iznimnoRadnoVrijeme = iznimnaRadnaVremenaUnesiOstaleVrijednosti(iznimnoRadnoVrijeme);
				iznimnaRadnaVremena.set(i, iznimnoRadnoVrijeme);
				System.out.println();
				System.out.println("Iznimno radno vrijeme je izmijenjeno");	
			}else {
				if(Alati.daNe("Iznimno radno vrijeme sa tim datumom je već unešeno."
						+ "\nŽelite li pokušati ponovno? (da/ne): ", "Molimo unesite da ili ne")) {
					continue;
				}
			}
			iznimnaRadnaVremenaIzbornik();
			break;
		}		
	}
	
	// BRISANJE IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaBrisanje() {
		iznimnaRadnaVremenaIzlistanje("U bazi postoje sljedeća iznimna radna vremena", iznimnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite broj iznimnog radnog vremena koje želite obrisati: ", porukaGreskeIzboraAkcije, 1,
				iznimnaRadnaVremena.size()) - 1;
		if(Alati.daNe("Želite li zaista obrisati iznimno radno vrijeme sa datumom " + Alati.hrDatum(iznimnaRadnaVremena.get(i).getDatum()) 
						+ ": ", "Molimo unesite da ili ne")) {
			iznimnaRadnaVremena.remove(i);
			System.out.println();
			System.out.println("Iznimno radno vrijeme je obrisano.");
		}		
		iznimnaRadnaVremenaIzbornik();
	}
	
	// DETALJI IZNIMNOG RADNOG VREMENA
	private void iznimnaRadnaVremenaDetalji() {			
		iznimnaRadnaVremenaIzlistanje("U bazi postoje sljedeća iznimna radna vremena", iznimnaRadnaVremena);
		int i = Alati.ucitajBroj("Unesite redni broj ispred iznimnog radnog vremena koje želite detaljnije pogledati: ", porukaGreskeIzboraAkcije, 1,
				iznimnaRadnaVremena.size()) - 1;
		Alati.ispisZaglavlja("Detalji iznimnog radnog vremena", false);
		iznimnaRadnaVremena.get(i).ispisiDetalje();
		iznimnaRadnaVremenaIzbornik();		
	}	
	
	// POMOĆNE FUNKCIJE IZNIMNOG RADNOG VREMENA
	private boolean iznimnaRadnaVremenaProvjeriPreklapanje(final List<IznimnoRadnoVrijeme> iznimnaRadnaVremena, final Date datum) {		
	return iznimnaRadnaVremena.stream().filter(o -> o.getDatum().equals(datum)).findFirst().isPresent();
	}
	
	private IznimnoRadnoVrijeme iznimnaRadnaVremenaUnesiOstaleVrijednosti(IznimnoRadnoVrijeme iznimnoRadnoVrijeme) {
		iznimnoRadnoVrijeme.setPocetak(Alati.ucitajVrijeme("Početak radnog vremena na dan " + Alati.hrDatum(iznimnoRadnoVrijeme.getDatum()) + ": "));
		iznimnoRadnoVrijeme.setKraj(Alati.ucitajVrijeme("Kraj radnog vremena na dan " + Alati.hrDatum(iznimnoRadnoVrijeme.getDatum()) + ": "));
		iznimnoRadnoVrijeme.setPauza(Alati.ucitajBroj("Trajanje pauze u minutama na dan " + 
									 Alati.hrDatum(iznimnoRadnoVrijeme.getDatum())  + ": ", porukaGreskeUnosaCijelogBroja, 1, 120));
		iznimnoRadnoVrijeme.setNapomena(Alati.ucitajString("Unesite napomenu/oznaku iznimnog radnog vremena (npr. Božić 2015.): ", porukaGreskePraznogUnosa, 1, 50));		
		return iznimnoRadnoVrijeme;
	}
	
	private void iznimnaRadnaVremenaIzlistanje(String poruka, List<IznimnoRadnoVrijeme> iznimnaRadnaVremena) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (IznimnoRadnoVrijeme irv : iznimnaRadnaVremena) {
			System.out.println(counter + " " + irv.toString());
			counter++;
		}
	}

	/**
	 *  
	 * IZNIMNA RADNA VREMENA KRAJ
	 * 
	 */
	
	public static void main(String[] args) {
		new Start();
	}

}
