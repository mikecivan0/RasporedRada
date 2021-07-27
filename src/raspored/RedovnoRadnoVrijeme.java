package raspored;

import java.util.Date;

public class RedovnoRadnoVrijeme {

	private Date vrijediOd;
	private Date vrijediDo;
	private Date ponedjeljakOd;
	private Date ponedjeljakDo;
	private Date utorakOd;
	private Date utorakDo;
	private Date srijedaOd;
	private Date srijedaDo;
	private Date cetvrtakOd;
	private Date cetvrtakDo;
	private Date petakOd;
	private Date petakDo;
	private Date subotaOd;
	private Date subotaDo;
	private Date nedjeljaOd;
	private Date nedjeljaDo;
	private Integer trajanjePauzeUMinutama;
	
	public RedovnoRadnoVrijeme() {
		
	}
	
	public RedovnoRadnoVrijeme(Date vrijediOd, Date vrijediDo,Date ponedjeljakOd, Date ponedjeljakDo, Date utorakOd, Date utorakDo,
			Date srijedaOd, Date srijedaDo, Date cetvrtakOd, Date cetvrtakDo, Date petakOd, Date petakDo, Date subotaOd,
			Date subotaDo, Date nedjeljaOd, Date nedjeljaDo, Integer trajanjePauzeUMinutama) {
		this.vrijediOd = vrijediOd;
		this.vrijediDo = vrijediDo;
		this.ponedjeljakOd = ponedjeljakOd;
		this.ponedjeljakDo = ponedjeljakDo;
		this.utorakOd = utorakOd;
		this.utorakDo = utorakDo;
		this.srijedaOd = srijedaOd;
		this.srijedaDo = srijedaDo;
		this.cetvrtakOd = cetvrtakOd;
		this.cetvrtakDo = cetvrtakDo;
		this.petakOd = petakOd;
		this.petakDo = petakDo;
		this.subotaOd = subotaOd;
		this.subotaDo = subotaDo;
		this.nedjeljaOd = nedjeljaOd;
		this.nedjeljaDo = nedjeljaDo;
		this.trajanjePauzeUMinutama = trajanjePauzeUMinutama;
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
	public Date getPonedjeljakOd() {
		return ponedjeljakOd;
	}
	public void setPonedjeljakOd(Date ponedjeljakOd) {
		this.ponedjeljakOd = ponedjeljakOd;
	}
	public Date getPonedjeljakDo() {
		return ponedjeljakDo;
	}
	public void setPonedjeljakDo(Date ponedjeljakDo) {
		this.ponedjeljakDo = ponedjeljakDo;
	}
	public Date getUtorakOd() {
		return utorakOd;
	}
	public void setUtorakOd(Date utorakOd) {
		this.utorakOd = utorakOd;
	}
	public Date getUtorakDo() {
		return utorakDo;
	}
	public void setUtorakDo(Date utorakDo) {
		this.utorakDo = utorakDo;
	}
	public Date getSrijedaOd() {
		return srijedaOd;
	}
	public void setSrijedaOd(Date srijedaOd) {
		this.srijedaOd = srijedaOd;
	}
	public Date getSrijedaDo() {
		return srijedaDo;
	}
	public void setSrijedaDo(Date srijedaDo) {
		this.srijedaDo = srijedaDo;
	}
	public Date getCetvrtakOd() {
		return cetvrtakOd;
	}
	public void setCetvrtakOd(Date cetvrtakOd) {
		this.cetvrtakOd = cetvrtakOd;
	}
	public Date getCetvrtakDo() {
		return cetvrtakDo;
	}
	public void setCetvrtakDo(Date cetvrtakDo) {
		this.cetvrtakDo = cetvrtakDo;
	}
	public Date getPetakOd() {
		return petakOd;
	}
	public void setPetakOd(Date petakOd) {
		this.petakOd = petakOd;
	}
	public Date getPetakDo() {
		return petakDo;
	}
	public void setPetakDo(Date petakDo) {
		this.petakDo = petakDo;
	}
	public Date getSubotaOd() {
		return subotaOd;
	}
	public void setSubotaOd(Date subotaOd) {
		this.subotaOd = subotaOd;
	}
	public Date getSubotaDo() {
		return subotaDo;
	}
	public void setSubotaDo(Date subotaDo) {
		this.subotaDo = subotaDo;
	}
	public Date getNedjeljaOd() {
		return nedjeljaOd;
	}
	public void setNedjeljaOd(Date nedjeljaOd) {
		this.nedjeljaOd = nedjeljaOd;
	}
	public Date getNedjeljaDo() {
		return nedjeljaDo;
	}
	public void setNedjeljaDo(Date nedjeljaDo) {
		this.nedjeljaDo = nedjeljaDo;
	}
	public Integer getTrajanjePauzeUMinutama() {
		return trajanjePauzeUMinutama;
	}
	public void setTrajanjePauzeUMinutama(Integer trajanjePauzeUMinutama) {
		this.trajanjePauzeUMinutama = trajanjePauzeUMinutama;
	}

	public void ispisiDetalje() {
		System.out.println("Datum primjene: " + Alati.hrDatum(this.vrijediOd) + " - " + Alati.hrDatum(this.vrijediDo));
		System.out.println("Radno vrijeme ponedjeljkom: " + Alati.hrVrijeme(this.ponedjeljakOd) + " - " + Alati.hrVrijeme(this.ponedjeljakDo));
		System.out.println("Radno vrijeme utorkom: " + Alati.hrVrijeme(this.utorakOd) + " - " + Alati.hrVrijeme(this.utorakDo));
		System.out.println("Radno vrijeme srijedom: " + Alati.hrVrijeme(this.srijedaOd) + " - " + Alati.hrVrijeme(this.srijedaDo));
		System.out.println("Radno vrijeme četvrtkom: " + Alati.hrVrijeme(this.cetvrtakOd) + " - " + Alati.hrVrijeme(this.cetvrtakDo));
		System.out.println("Radno vrijeme petkom: " + Alati.hrVrijeme(this.petakOd) + " - " + Alati.hrVrijeme(this.petakDo));
		System.out.println("Radno vrijeme subotom: " + Alati.hrVrijeme(this.subotaOd) + " - " + Alati.hrVrijeme(this.subotaDo));
		System.out.println("Radno vrijeme nedjeljom: " + Alati.hrVrijeme(this.nedjeljaOd) + " - " + Alati.hrVrijeme(this.nedjeljaDo));		
	}
	
}
