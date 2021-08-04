package raspored;

import java.util.Date;

public class Raspored {
	private Korisnik korisnik;
	private OznakaUnosaURaspored oznakaUnosaURaspored;
	private Date datum;
	private boolean radSaPauzom;
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public OznakaUnosaURaspored getoznakaUnosaURaspored() {
		return oznakaUnosaURaspored;
	}
	public void setoznakaUnosaURaspored(OznakaUnosaURaspored oznakaUnosaURaspored) {
		this.oznakaUnosaURaspored = oznakaUnosaURaspored;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public boolean isRadSaPauzom() {
		return radSaPauzom;
	}
	public void setRadSaPauzom(boolean radSaPauzom) {
		this.radSaPauzom = radSaPauzom;
	}		

	public void ispisiDetalje() {
		System.out.println("Korisnik: " + this.korisnik.toString() + "\n"
				+ "Oznaka: " + this.oznakaUnosaURaspored.getSkracenica() + "\n"
				+ "Datum: " + Alati.hrDatum(this.datum) + "\n"
				+ "Rad sa pauzom: " + Alati.parseBool(this.radSaPauzom)
				);
	
	}
}
