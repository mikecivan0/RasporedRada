package raspored;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Start {

	public static final String LINK_POVEZNICE = "https://github.com/mikecivan0/RasporedRada";
	private List<Osoba> osobe;
	private List<Korisnik> korisnici;
	private boolean valjanost = false;
	private String porukaIzboraAkcije = "Unesite neku od gore ponuđenih stavki: ";
	private String porukaGreskeIzboraAkcije = "Nepostojeći izbor";
	private String porukaGreskePraznogUnosa = "Unos ne smije bti prazan";

	public Start() {
		korisnici = new ArrayList<Korisnik>();
		osobe = new ArrayList<Osoba>();

		// početak probnih podataka
		osobe.add(new Osoba("Ivan", "Mikec", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko", "Drugi", "091", "email", "adresa"));
		osobe.add(new Osoba("Netko1", "treći", "091", "email", "adresa"));

		korisnici.add(new Korisnik(osobe.get(0), "ja", "ja", "2-654", 1, true));
		korisnici.add(new Korisnik(osobe.get(1), "on", "on", "2-6545", 1, true));
		// kraj probnih podataka

		
		Alati.scanner = new Scanner(System.in);
		glavniIzbornik();
	}

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

	private void ponovniPokusaj() {
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

		provjeraVjerodajnicaKorisnika(korisnik);
	}

	private void logout() {
		valjanost = false;
		glavniIzbornik();

	}

	private void provjeraVjerodajnicaKorisnika(Korisnik k) {
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
			ponovniPokusaj();
		}
	}

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
			case 8 -> logout();
		}

	}

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
	
	private void korisniciUnosNoveOsobeKaoNovogKorisnika() {
		Korisnik korisnik = new Korisnik();
		osobeUnosNove();
		korisnik.setOsoba(osobe.get(osobe.size()-1));
		korisnik = korisniciPostaviVrijednosti(korisnik);
		korisnici.add(korisnik);
		System.out.println();
		System.out.println("Nova osoba je unešena i postavljena kao novi korisnik");
		korisniciIzbornik();
	}

	private Korisnik korisniciPostaviVrijednosti(Korisnik korisnik) {		
		korisnik.setKorisnickoIme(Alati.ucitajString("Unesite korisničko ime novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50));
		korisnik.setLozinka(Alati.ucitajString("Unesite lozinku novoga korisnika: ", porukaGreskePraznogUnosa, 1, 50));
		korisnik.setOsobniBroj(Alati.ucitajString("Unesite osobni broj novoga korisnika: ", porukaGreskePraznogUnosa, 1, 10));
		korisnik.setAktivan(Alati.daNe("Hoće li korisnik biti aktivan (da/ne): ", porukaGreskeIzboraAkcije));
		korisnik.setRazina(Alati.ucitajBroj("Unesite razinu korisnika: ", porukaGreskeIzboraAkcije, 1, 2));
		
		return korisnik;
	}

	private void korisniciUnosPostojeceOsobeKaoNovogKorisnika() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		Osoba osoba = osobe.get(Alati.ucitajBroj("Unesite broj osobe koju želite dodati kao korisnika: ", "Unos ne smije biti prazan", 1, osobe.size())-1);
		if(!korisniciPostojiLiOsobaKaoKorisnik(osoba)) {
			Korisnik korisnik = new Korisnik();
			korisnik.setOsoba(osoba);
			korisnik = korisniciPostaviVrijednosti(korisnik);
			korisnici.add(korisnik);
			System.out.println();
			System.out.println("Osoba je postavljena kao novi korisnik");
			korisniciIzbornik();
		}else {
			if(Alati.daNe("Osoba je već unešena kao korisnik. Želite li odabrati drugu osobu? (da/ne): ", "Molimo unesite da ili ne")) {
				korisniciUnosPostojeceOsobeKaoNovogKorisnika();
			}else {
				korisniciIzbornik();
			}
		}
	}

	private boolean korisniciPostojiLiOsobaKaoKorisnik(Osoba osoba) {
		boolean mark = false;
		for(Korisnik korisnik: korisnici) {
			if(korisnik.getOsoba().equals(osoba)) {
				mark = true;
				break;
			}
			continue;
		}
		return mark;
		
	}

	private void korisniciIzmjena() {
		
	}


	private void korisniciDetalji() {
		Alati.ispisZaglavlja("Detalji korisnika", true);
		System.out.println("1 za izlistanje svih korisnika od kojih će te izabrati onog kojeg želite");
		System.out.println("2 za pretragu korisnika po imenu i/ili prezimenu");
		korisniciUcitajOdabirPretrageZaIspisDetalja();	
		
	}

	private void korisniciUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> korisniciDetaljiPoIndeksu();
			case 2 -> korisniciDetaljiPoImenu();
		}
		
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

	private List<Korisnik> korisniciPronadjiPoUvjetu(String uvjet) {
		List<Korisnik> listaKorisnika = new ArrayList<Korisnik>();
		for (Korisnik korisnik : korisnici) {
			if (korisniciJeLiToKorisnikPoNazivu(korisnik, uvjet)) {
				listaKorisnika.add(korisnik);
			}
		}
		return listaKorisnika;
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

	private void korisniciDetaljiPoIndeksu() {
		korisniciIzlistanje("Korisici koji se nalaze u bazi", korisnici);
		int i = Alati.ucitajBroj("Unesite broj korisnika čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
				korisnici.size()) - 1;
		Alati.ispisZaglavlja("Detalji korisnika", false);
		korisnici.get(i).ispisiDetalje();
		korisniciIzbornik();
	}

	private void korisniciIzlistanje(String poruka, List<Korisnik> korisnici) {
		int counter = 1;
		Alati.ispisZaglavlja(poruka, false);
		for (Korisnik korisnik : korisnici) {
			System.out.println(counter + " " + korisnik.toString());
			counter++;
		}
		
	}

	private void korisniciBrisanje() {
		Alati.ispisZaglavlja("Brisanje korisnika", true);
		System.out.println("1 za izlistanje svih korisnika od kojih će te izabrati željenog");
		System.out.println("2 za pretragu korisnika po imenu i/ili prezimenu");
		korisniciUcitajOdabirPretrageZaBrisanje();
	}

	private void korisniciUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
		case 1 -> korisniciBrisanjePoIndeksu();
		case 2 -> korisniciBrisanjePoNazivu();
	}
		
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

	private void osobeDetalji() {
		Alati.ispisZaglavlja("Detalji osobe", true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
		osobeUcitajOdabirPretrageZaIspisDetalja();	
	}

	private void osobeUcitajOdabirPretrageZaIspisDetalja() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeDetaljiPoIndeksu();
			case 2 -> osobeDetaljiPoImenu();
		}
		
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

	private void osobeDetaljiPoIndeksu() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		int i = Alati.ucitajBroj("Unesite broj osobe čije detalje želite pogledati: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		Alati.ispisZaglavlja("Detalji osobe", false);
		osobe.get(i).ispisiDetalje();
		osobeIzbornik();
	}

	private void osobeBrisanje() {
		Alati.ispisZaglavlja("Brisanje osobe", true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
		osobeUcitajOdabirPretrageZaBrisanje();
	}

	private void osobeUcitajOdabirPretrageZaBrisanje() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeBrisanjePoIndeksu();
			case 2 -> osobeBrisanjePoImenu();
		}

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

	private void osobeIzmjena() {
		Alati.ispisZaglavlja("Izmjena podataka osobe", true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
		osobeUcitajOdabirPretrage();
	}

	private void osobeUcitajOdabirPretrage() {
		switch (Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
			case 1 -> osobeIzmjenaPoIndeksu();
			case 2 -> osobeIzmjenaPoImenu();
		}

	}

	private void osobeIzmjena(Osoba osoba, int i) {
		osoba = osobaUnosPodataka(osoba);
		osobe.set(i, osoba);
		System.out.println("");
		System.out.println("Podaci osobe su uspješno izmjenjeni. Što želite dalje?");
		osobeIzbornik();

	}

	private void osobeIzmjenaPoIndeksu() {
		osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1,
				osobe.size()) - 1;
		Osoba osoba = osobe.get(i);
		osobeIzmjena(osoba, i);
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
						osobeIzmjena(osoba, i);
					} else {
						offsetCounter++;
						continue;
					}
				}
			}
		}
	}

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

	private void osobeUnosNove() {
		Alati.ispisZaglavlja("Podaci nove osobe", true);
		Osoba osoba = new Osoba();
		osoba = osobaUnosPodataka(osoba);
		osobe.add(osoba);
	}

	private Osoba osobaUnosPodataka(Osoba osoba) {
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

	public static void main(String[] args) {
		new Start();
	}

}
