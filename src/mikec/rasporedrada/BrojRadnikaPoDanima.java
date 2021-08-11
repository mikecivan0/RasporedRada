package mikec.rasporedrada;

import java.util.Date;

public class BrojRadnikaPoDanima {

	private Date vrijediOd;
	private Date vrijediDo;
	private Integer ponedjeljak;
	private Integer utorak;
	private Integer srijeda;
	private Integer cetvrtak;
	private Integer petak;
	private Integer subota;
	private Integer nedjelja;
	
	public BrojRadnikaPoDanima() {
		
	}
	
	public BrojRadnikaPoDanima(Date vrijediOd, Date vrijediDo, Integer ponedjeljak, Integer utorak, Integer srijeda,
			Integer cetvrtak, Integer petak, Integer subota, Integer nedjelja) {
		this.vrijediOd = vrijediOd;
		this.vrijediDo = vrijediDo;
		this.ponedjeljak = ponedjeljak;
		this.utorak = utorak;
		this.srijeda = srijeda;
		this.cetvrtak = cetvrtak;
		this.petak = petak;
		this.subota = subota;
		this.nedjelja = nedjelja;
	}
	
	public Date getVrijediOd() {
		return vrijediOd;
	}
	public void setVrijediOd(Date vrijediOd) {
		this.vrijediOd = vrijediOd;
	}
	public Date getVrijediDo() {
		return vrijediDo;
	}
	public void setVrijediDo(Date vrijediDo) {
		this.vrijediDo = vrijediDo;
	}
	public Integer getPonedjeljak() {
		return ponedjeljak;
	}
	public void setPonedjeljak(Integer ponedjeljak) {
		this.ponedjeljak = ponedjeljak;
	}
	public Integer getUtorak() {
		return utorak;
	}
	public void setUtorak(Integer utorak) {
		this.utorak = utorak;
	}
	public Integer getSrijeda() {
		return srijeda;
	}
	public void setSrijeda(Integer srijeda) {
		this.srijeda = srijeda;
	}
	public Integer getCetvrtak() {
		return cetvrtak;
	}
	public void setCetvrtak(Integer cetvrtak) {
		this.cetvrtak = cetvrtak;
	}
	public Integer getPetak() {
		return petak;
	}
	public void setPetak(Integer petak) {
		this.petak = petak;
	}
	public Integer getSubota() {
		return subota;
	}
	public void setSubota(Integer subota) {
		this.subota = subota;
	}
	public Integer getNedjelja() {
		return nedjelja;
	}
	public void setNedjelja(Integer nedjelja) {
		this.nedjelja = nedjelja;
	} 	
	
	@Override
	public String toString() {
		return Alati.hrDatum(this.vrijediOd) + " - " + Alati.hrDatum(this.vrijediDo);
	}
	
	public void ispisiDetalje() {
		System.out.println("Vrijeme primjene: " + Alati.hrDatum(this.vrijediOd) + " - " + Alati.hrDatum(this.vrijediDo));
		System.out.println("Broj radnika ponedjeljkom: " + this.ponedjeljak);	
		System.out.println("Broj radnika utorkom: " + this.utorak);	
		System.out.println("Broj radnika srijedom: " + this.srijeda);	
		System.out.println("Broj radnika četvrtkom: " + this.cetvrtak);	
		System.out.println("Broj radnika petkom: " + this.petak);	
		System.out.println("Broj radnika subotom: " + this.subota);	
		System.out.println("Broj radnika nedjeljom: " + this.nedjelja);	
	}	
	
	public boolean isValid() {
	    return this.ponedjeljak != null 
	    		&& this.utorak != null 
	    		&& this.srijeda != null
	    		&& this.cetvrtak != null
	    		&& this.petak != null
	    		&& this.subota != null
	    		&& this.nedjelja != null;
	  }
}
