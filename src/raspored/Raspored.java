package raspored;

import java.util.Date;

public class Raspored {
	private Korisnik korisnik;
	private OznakaUnosaURaspored vrstaUnosaURaspored;
	private Date datum;
	private boolean radSaPauzom;
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public OznakaUnosaURaspored getElement() {
		return vrstaUnosaURaspored;
	}
	public void setElement(OznakaUnosaURaspored vrstaUnosaURaspored) {
		this.vrstaUnosaURaspored = vrstaUnosaURaspored;
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
}
