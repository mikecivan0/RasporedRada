package mikec.rasporedrada;

import java.util.Date;

public class Raspored implements Comparable<Raspored>{
	private Korisnik korisnik;
	private OznakaUnosaURaspored oznakaUnosaURaspored;
	private Date datum;
	private boolean radSaPauzom;
	
	public Raspored() {
		
	}	
	
	public Raspored(Korisnik korisnik, OznakaUnosaURaspored oznakaUnosaURaspored, Date datum, boolean radSaPauzom) {
		this.korisnik = korisnik;
		this.oznakaUnosaURaspored = oznakaUnosaURaspored;
		this.datum = datum;
		this.radSaPauzom = radSaPauzom;
	}


	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public OznakaUnosaURaspored getOznakaUnosaURaspored() {
		return oznakaUnosaURaspored;
	}
	public void setOznakaUnosaURaspored(OznakaUnosaURaspored oznakaUnosaURaspored) {
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
	
	@Override
	  public int compareTo(Raspored r) {
	    return getDatum().compareTo(r.getDatum());
	  }
}
