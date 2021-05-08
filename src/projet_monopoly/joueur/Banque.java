package projet_monopoly.joueur;

import projet_monopoly.Plateau;

class Banque extends Joueur {
	private int nbMaisons;
	private int nbHotel;
	
	public int getNbMaisons() {
		return nbMaisons;
	}
	public void setNbMaisons(int nbMaisons) {
		if(nbMaisons<0) {
			throw new IllegalArgumentException("BANQUE : nbmaison");
		}
		this.nbMaisons = nbMaisons;
	}
	public int getNbHotel() {
		return nbHotel;
	}
	public void setNbHotel(int nbHotel) {
		if(nbHotel<0) {
			throw new IllegalArgumentException("BANQUE : nbHotel");
		}
		this.nbHotel = nbHotel;
	}
	
	private Banque() {
		super("Banque", 100000);
		this.setNbMaisons(40);
		this.setNbHotel(10);
	}
	private static Banque instance = null;
	
	
	public static Banque getInstance() {
		if (instance==null)
			instance = new Banque();
		
		return instance; 
	}
	
	//methodes 
	

}
