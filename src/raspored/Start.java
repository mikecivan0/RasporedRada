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
	private boolean valjanost;
	private String porukaIzboraAkcije = "Unesite neku od gore ponuđenih stavki: ";
	private String porukaGreskeIzboraAkcije = "Nepostojeći izbor";
	private String porukaGreskePraznogUnosa = "Unos ne smije bti prazan";

	public Start() {
		korisnici = new ArrayList<Korisnik>();
		osobe = new ArrayList<Osoba>();
		Korisnik korisnik = new Korisnik(new Osoba("Ivan","Mikec","091","email","adresa"),"ja","ja","2-654",1,true);
		korisnici.add(korisnik);
		Alati.scanner = new Scanner(System.in);
		glavniIzbornik();
	}

	private void glavniIzbornik() {
		System.out.println();
		System.out.println("---------APLIKACIJA ZA VOĐENJE RASPOREDA RADA---------");
		System.out.println("GLAVNI IZBORNIK");
		System.out.println("Unesite: ");
		System.out.println("1 - ukoliko se želite prijaviti u aplikaciju");
		System.out.println("2 - ukoliko želite pogledati izvorni kod aplikacije na GitHub-u");
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
		if (Alati.daNe("Želite li se vratiti u glavni izbornik? (da/ne)", "Unesite da ili ne")) {
			glavniIzbornik();
		} else {
			System.out.println("Doviđenja.");
		}

	}

	private void login() {
		Korisnik korisnik = new Korisnik();
		System.out.println();
		System.out.println("--------------------------------");
		System.out.println("-Podaci za prijavu u aplikaciju-");
		System.out.println("--------------------------------");
		korisnik.setKorisnickoIme(Alati.ucitajString("Unesite svoje korisničko ime", porukaGreskePraznogUnosa, 1, 15));
		korisnik.setLozinka(Alati.ucitajString("Unesite svoju lozinku", porukaGreskePraznogUnosa, 1, 30));
		
		provjeriKorisnika(korisnik.getKorisnickoIme(), korisnik.getLozinka());
	}

	private void provjeriKorisnika(String korisnickoIme, String lozinka) {		
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
		System.out.println();
		System.out.println("-----------------------");
		System.out.println("-IZBORNIK ZA KORISNIKE-");
		System.out.println("-----------------------");
		System.out.println("Odaberite: ");
		System.out.println("1 za rad sa osobama");
		System.out.println("2 za rad sa korisnicima");
		System.out.println("3 za rad sa redovnim radnim vremenom");
		System.out.println("4 za rad sa iznimnim radnim vremenom");
		System.out.println("5 za rad sa brojem radnika po danima");
		System.out.println("6 za rad sa oznakama unosa u raspored");
		System.out.println("7 za rad sa rasporedom rada");
		System.out.println("8 za odjavu i povratak u galvni izbornik");
		korisnikIzborGlavneAkcije();
		
	}

	private void korisnikIzborGlavneAkcije() {
		switch(Alati.ucitajBroj(porukaIzboraAkcije, porukaGreskeIzboraAkcije, 1, 8)) {
			case 1 -> osobeIzbornik();
		}
		
	}

	private void osobeIzbornik() {
		System.out.println();
		System.out.println("----------------");
		System.out.println("-Rad sa osobama-");
		System.out.println("----------------");
		System.out.println("Odaberite: ");
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
		case 5 -> korisnikGlavniIzbornik();
	}
		
	}

	private void osobeUnosNove() {
		System.out.println();
		System.out.println("-----------------");
		System.out.println("Podaci nove osobe");
		System.out.println("-----------------");
		Osoba osoba = new Osoba();
		osoba.setIme(Alati.ucitajString("Unesite ime osobe: ", porukaGreskePraznogUnosa, 1, 15));
		osoba.setPrezime(Alati.ucitajString("Unesite prezime osobe: ", porukaGreskePraznogUnosa, 1, 25));
		osoba.setAdresa(Alati.ucitajString("Adresa osobe ", porukaGreskePraznogUnosa, 1, 100));
		if(Alati.daNe("Želite li unijeti telefon osobe? (da/ne)", "Unesite da ili ne")) {
			osoba.setTelefon(Alati.ucitajString("Unesite telefon osobe: ",porukaGreskePraznogUnosa, 1, 20));
		}else {
			osoba.setTelefon("");
		}
		if(Alati.daNe("Želite li unijeti email adresu osobe? (da/ne)", "Unesite da ili ne")) {
			osoba.setEmail(Alati.ucitajString("Unesite email osobe: ", porukaGreskePraznogUnosa, 1, 50));
		}else {
			osoba.setEmail("");
		}
		
		osobe.add(osoba);
		System.out.println("Osoba je spremljena. Što želite dalje?");
		osobeIzbornik();
		
	}

	public static void main(String[] args) {
		new Start();
	}

}
