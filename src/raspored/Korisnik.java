package raspored;

public class Korisnik extends Osoba{

	private Osoba osoba;
	private String korisnickoIme;
	private String lozinka;
	private String osobniBroj;
	private Integer razina;
	private boolean aktivan;
	
	public Korisnik() {
		
	}
	
	public Korisnik(Osoba osoba,
			String korisnickoIme, String lozinka, String osobniBroj, Integer razina, boolean aktivan) {
		this.osoba = osoba;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.osobniBroj = osobniBroj;
		this.razina = razina;
		this.aktivan = aktivan;
	}
	
	public Osoba getOsoba() {
		return osoba;
	}
	public void setOsoba(Osoba osoba) {
		this.osoba = osoba;
	}
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getOsobniBroj() {
		return osobniBroj;
	}
	public void setOsobniBroj(String osobniBroj) {
		this.osobniBroj = osobniBroj;
	}
	public Integer getRazina() {
		return razina;
	}
	public void setRazina(Integer razina) {
		this.razina = razina;
	}
	public boolean isAktivan() {
		return aktivan;
	}
	public void setAktivan(boolean aktivan) {
		this.aktivan = aktivan;
	}
	
	@Override
	public String toString() {
		return this.korisnickoIme + ", " + this.osobniBroj;
	}
	
	public String getAktivan() {
		return (this.aktivan) ? "da" : "ne";
	}
	
	public void ispisiDetalje() {
		System.out.println("Ime i prezime: " + this.getOsoba().getIme() + " " + this.getOsoba().getPrezime() + "\n"
			+ "Korisničko ime: " + this.korisnickoIme  + "\n"
			+ "Osobni broj: " + this.osobniBroj  + "\n"
			+ "Aktivan: " + getAktivan() + "\n"
			+ "Razina: " + this.razina
			);
		
	}
	
	public String korisnikZaPrikaz() {
		return this.toString() + ", " + this.getOsoba().getIme() + " " + this.getOsoba().getPrezime();
	}
}
