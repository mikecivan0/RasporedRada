package mikec.rasporedrada;

import java.util.Date;

public class IznimnoRadnoVrijeme {
	private Date datum;
	private Date pocetak;
	private Date kraj;
	private String napomena;
	private int pauza;
	
	public IznimnoRadnoVrijeme() {
		
	}
	
	public IznimnoRadnoVrijeme(Date datum, Date pocetak, Date kraj, String napomena, int pauza) {
		this.datum = datum;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.napomena = napomena;
		this.pauza = pauza;
	}
	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Date getPocetak() {
		return pocetak;
	}
	public void setPocetak(Date pocetak) {
		this.pocetak = pocetak;
	}
	public Date getKraj() {
		return kraj;
	}
	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	public String getNapomena() {
		return napomena;
	}
	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}
	public int getPauza() {
		return pauza;
	}
	public void setPauza(int pauza) {
		this.pauza = pauza;
	}
	
	@Override
	public String toString() {
		return Alati.hrDatum(this.datum) + ", " + this.napomena;
	}
	
	public void ispisiDetalje() {
		System.out.println("Datum iznimnog radnog vremena: " + Alati.hrDatum(this.datum));
		System.out.println("Radno vrijeme počinje u : " + Alati.hrVrijeme(this.pocetak));
		System.out.println("Radno vrijeme završava u : " + Alati.hrVrijeme(this.kraj));
		System.out.println("Trajanje pauze u minutama: " + this.getPauza());
		System.out.println("Napomena/oznaka: " + this.napomena);
	}
	
	public String radnoVrijeme() {
		return Alati.hrVrijeme(this.pocetak) + " " + Alati.hrVrijeme(this.kraj);
	}
}
