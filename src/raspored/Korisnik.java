package raspored;

import java.util.Date;

public class Korisnik extends Osoba{

	private Osoba osoba;
	private String korisnickoIme;
	private String lozinka;
	private String osobniBroj;
	private Integer razina;
	private boolean aktivan;
	private Date pocetakUgovora;
	private Date krajUgovora;
	
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
	public Date getPocetakUgovora() {
		return pocetakUgovora;
	}
	public void setPocetakUgovora(Date pocetakUgovora) {
		this.pocetakUgovora = pocetakUgovora;
	}
	public Date getKrajUgovora() {
		return krajUgovora;
	}
	public void setKrajUgovora(Date krajUgovora) {
		this.krajUgovora = krajUgovora;
	}
	
	@Override
	public String toString() {
		return this.korisnickoIme + ", " + this.osobniBroj;
	}
}
