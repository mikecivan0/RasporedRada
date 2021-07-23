package raspored;

import java.util.Date;

public class Raspored {
	private Korisnik korisnik;
	private VrstaUnosaURasporedu element;
	private Date datumRada;
	private boolean pauza;
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public VrstaUnosaURasporedu getElement() {
		return element;
	}
	public void setElement(VrstaUnosaURasporedu element) {
		this.element = element;
	}
	public Date getDatumRada() {
		return datumRada;
	}
	public void setDatumRada(Date datumRada) {
		this.datumRada = datumRada;
	}
	public boolean isPauza() {
		return pauza;
	}
	public void setPauza(boolean pauza) {
		this.pauza = pauza;
	}
	
}
