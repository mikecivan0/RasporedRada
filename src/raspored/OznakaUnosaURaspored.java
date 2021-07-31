package raspored;

public class OznakaUnosaURaspored {

	private String naziv;
	private String skracenica;
	
	public OznakaUnosaURaspored() {
		
	}
	
	public OznakaUnosaURaspored(String naziv, String skracenica) {
		super();
		this.naziv = naziv;
		this.skracenica = skracenica;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getSkracenica() {
		return skracenica;
	}
	public void setSkracenica(String skracenica) {
		this.skracenica = skracenica;
	}
	
	@Override
	public String toString() {
		return this.getNaziv() + "(" + this.getSkracenica() + ")";
	}
	
	public void ispisiDetalje() {
		 System.out.println("Naziv oznake: " + this.getNaziv() 
		 					+ "\nOznaka: " + this.getSkracenica());
	}
	
}
