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
		osobe.add(new Osoba("Ivan","Mikec","091","email","adresa"));
		osobe.add(new Osoba("Netko","Drugi","091","email","adresa"));
		osobe.add(new Osoba("Netko1","treći","091","email","adresa"));
		
		Korisnik k = new Korisnik(osobe.get(0),"ja","ja","2-654",1,true);
		korisnici.add(k);
		// kraj probnih podataka
		
		korisnici.add(korisnici.get(0));
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
				povratakUGlavniIzbornik();
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

	private void povratakUGlavniIzbornik() {
		if (Alati.daNe("Želite li se vratiti u glavni izbornik? (da/ne): ", "Unesite da ili ne")) {
			glavniIzbornik();
		} else {
			System.out.println("Doviđenja.");
		}

	}

	private void login() {
		Korisnik korisnik = new Korisnik();
		Alati.ispisZaglavlja("Podaci za prijavu u aplikaciju", true);
		korisnik.setKorisnickoIme(Alati.ucitajString("korisničko ime: ", porukaGreskePraznogUnosa, 1, 15));
		korisnik.setLozinka(Alati.ucitajString("lozinka: ", porukaGreskePraznogUnosa, 1, 30));
		
		korisnikProvjeraVjerodajnica(korisnik.getKorisnickoIme(), korisnik.getLozinka());
	}
	
	private void logout() {
		valjanost = false;
		glavniIzbornik();
		
	}

	private void korisnikProvjeraVjerodajnica(String korisnickoIme, String lozinka) {		
		valjanost = true;
		for(Korisnik korisnik : korisnici) {
			if(!korisnik.getKorisnickoIme().contains(korisnickoIme)) {
				valjanost = false;
			}
			if(!korisnik.getLozinka().contains(lozinka)) {
				valjanost = false;
			}	

			if(valjanost) {
				break;
			}
					
		}		
		
		if(valjanost) {
			korisnikGlavniIzbornik();
		}else {
			System.out.println("Nevaljana kombinacija korisničkog imena i lozinke");
			povratakUGlavniIzbornik();
		}		
	}

	private void korisnikGlavniIzbornik() {
		Alati.ispisZaglavlja("IZBORNIK ZA KORISNIKE", true);
		System.out.println("1 za rad sa osobama");
		System.out.println("2 za rad sa korisnicima");
		System.out.println("3 za rad sa redovnim radnim vremenom");
		System.out.println("4 za rad sa iznimnim radnim vremenom");
		System.out.println("5 za rad sa brojem radnika po danima");
		System.out.println("6 za rad sa oznakama unosa u raspored");
		System.out.println("7 za rad sa rasporedom rada");
		System.out.println("8 za odjavu i povratak u glavni izbornik");
		korisnikIzborGlavneAkcije();
		
	}

	private void korisnikIzborGlavneAkcije() {
		switch(Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 8)) {
			case 1 -> osobeIzbornik();
			case 8 -> logout();
		}
		
	}

	private void osobeIzbornik() {
		Alati.ispisZaglavlja("Rad sa osobama", true);
		System.out.println("1 za unos nove osobe");
		System.out.println("2 za izmjenu postojeće osobe");
		System.out.println("3 za brisanje postojeće osobe");
		System.out.println("4 za pregled svih osoba");
		System.out.println("5 za povratak u glavni korsinički izbornik");
		osobeOdabirAkcije();
	}

	private void osobeOdabirAkcije() {
		switch(Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 5)) {
		case 1 -> osobeUnosNove();
		case 2 -> osobeIzmjena();
		case 4 -> {
			osobeIzlistanje("Osobe koje se nalaze u bazi", osobe);
			osobeIzbornik();
		}
		case 5 -> korisnikGlavniIzbornik();
	}
		
	}

	private void osobeIzmjena() {
		Alati.ispisZaglavlja("Izmjena podataka osobe", true);
		System.out.println("1 za izlistanje svih osoba od kojih će te izabrati željenu osobu");
		System.out.println("2 za pretragu osoba po imenu i/ili prezimenu");
		ucitajOdabirPretrageOsoba();
	}

	private void ucitajOdabirPretrageOsoba() {
		switch(Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 2)) {
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
		int i = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1, osobe.size())-1;
		Osoba osoba = osobe.get(i);		
		osobeIzmjena(osoba, i);
	}

	private void osobeIzmjenaPoImenu() {
		String uvjet = Alati.ucitajString("Upišite ime i/ili prezime osobe koju tražite: ", porukaGreskePraznogUnosa, 0, 30);
		List<Osoba> nadjeneOsobe = osobePronadjiPoUvjetu(uvjet);
		if(nadjeneOsobe.isEmpty()) {
			System.out.println("Nema rezultata koji dogovaraju zadanom kriteriju. ");
			if(Alati.daNe("Želite li pokušati opet? (da/ne)", "Unesite da ili ne")) {
				osobeIzmjenaPoImenu();
			}else {
				osobeIzbornik();
			}
		}else {
			osobeIzlistanje("Pronađene osobe", nadjeneOsobe);
			Integer offset = Alati.ucitajBroj("Unesite broj osobe koju želite izmijeniti: ", porukaGreskeIzboraAkcije, 1, nadjeneOsobe.size());
			Integer offsetCounter = 1;
			for(int i=0; i<osobe.size();i++) {
				Osoba osoba = osobe.get(i);	
				if(osobeJeLiUvjetZadovoljen(osoba, uvjet)) {
					if(offsetCounter.equals(offset)) {
						osobeIzmjena(osoba, i);
					}else {
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
		for(Osoba osoba : osobe) {
			System.out.println(counter + " " + osoba.toString());
			counter++;
		}
	}
	
	private List<Osoba> osobePronadjiPoUvjetu(String uvjet) {
		List<Osoba> listaOsobaZaIzmjenu = new ArrayList<Osoba>();
		for(Osoba osoba : osobe) {
			if(osobeJeLiUvjetZadovoljen(osoba, uvjet)) {
				listaOsobaZaIzmjenu.add(osoba);
			}
		}
		return listaOsobaZaIzmjenu;
	}
	
	public boolean osobeJeLiUvjetZadovoljen(Osoba osoba, String uvjet) {
		if((osoba.getIme().toLowerCase() + " " + osoba.getPrezime().toLowerCase()).contains(uvjet.toLowerCase()) 
				|| (osoba.getPrezime().toLowerCase() + " " + osoba.getIme().toLowerCase()).contains(uvjet.toLowerCase())) {
			return true;
		}else {
			return false;
		}
	}

	private void osobeUnosNove() {
		Alati.ispisZaglavlja("Podaci nove osobe", true);		
		Osoba osoba = new Osoba();
		osoba = osobaUnosPodataka(osoba);		
		osobe.add(osoba);
		System.out.println();
		System.out.println("Osoba je spremljena. Što želite dalje?");
		osobeIzbornik();
		
	}

	private Osoba osobaUnosPodataka(Osoba osoba) {
		osoba.setIme(Alati.ucitajString("ime osobe: ", porukaGreskePraznogUnosa, 1, 15));
		osoba.setPrezime(Alati.ucitajString("prezime osobe: ", porukaGreskePraznogUnosa, 1, 25));
		osoba.setAdresa(Alati.ucitajString("adresa osobe ", porukaGreskePraznogUnosa, 1, 100));
		if(Alati.daNe("Želite li unijeti telefon osobe? (da/ne): ", "Unesite da ili ne")) {
			osoba.setTelefon(Alati.ucitajString("telefon osobe: ",porukaGreskePraznogUnosa, 1, 20));
		}else {
			osoba.setTelefon("");
		}
		if(Alati.daNe("Želite li unijeti email adresu osobe? (da/ne): ", "Unesite da ili ne")) {
			osoba.setEmail(Alati.ucitajString("email osobe: ", porukaGreskePraznogUnosa, 1, 50));
		}else {
			osoba.setEmail("");
		}
		
		return osoba;
		
	}

	public static void main(String[] args) {
		new Start();
	}

}
