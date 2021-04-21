package projet_monopoly;

class Banque extends Joueur {
	
	private int nbMaisons;
	private int nbHotel;
	
	public int getNbMaisons() {
		return nbMaisons;
	}
	public void setNbMaisons(int nbMaisons) {
		if(nbMaisons<0) {
			//exception
		}
		this.nbMaisons = nbMaisons;
	}
	public int getNbHotel() {
		return nbHotel;
	}
	public void setNbHotel(int nbHotel) {
		if(nbHotel<0) {
			//exception
		}
		this.nbHotel = nbHotel;
	}
	
	public Banque(int solde, int nbMaisons, int nbHotel) {
		super(solde);
		this.setNbMaisons(nbMaisons);
		this.setNbHotel(nbHotel);
	}
	
	
	//methodes 
	

}
