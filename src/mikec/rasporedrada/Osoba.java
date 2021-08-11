package mikec.rasporedrada;

public class Osoba {
	
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private String adresa;
	
	public Osoba() {
		
	}
	
	public Osoba(String ime, String prezime, String telefon, String email, String adresa) {
		this.ime = ime;
		this.prezime = prezime;
		this.telefon = telefon;
		this.email = email;
		this.adresa = adresa;
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime.trim();
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	

	@Override
	public String toString() {
		return this.ime + " " + this.prezime + ", " + this.adresa;
	}

	public void ispisiDetalje() {
		System.out.println("Ime: " + this.ime + "\n"
			+ "Prezime: " + this.prezime + "\n"
			+ "Adresa: " + this.adresa  + "\n"
			+ "Telefon: " + this.telefon + "\n"
			+ "Email: " + this.email
			);
		
	}
}
