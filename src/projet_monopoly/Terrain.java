package projet_monopoly;

public class Terrain extends CasesProprietes {
	private int loyerDeBase;
	private int loyer1Maison;
	private int loyer2Maison;
	private int loyer3Maison;
	private int loyer4Maison;
	private int loyerHotel;
	private int nbMaison;
	private boolean Hotel;
	private GroupeTerrain groupe;
	
	
	public int getLoyerDeBase() {
		return loyerDeBase;
	}
	public void setLoyerDeBase(int loyerDeBase) {
		if(this.loyerDeBase<0)
		{ //exception 
			
		}
		this.loyerDeBase = loyerDeBase;
	}
	public int getLoyer1Maison() {
		return loyer1Maison;
	}
	public void setLoyer1Maison(int loyer1Maison) {
		if(this.loyer1Maison<0)
		{ //exception 
			
		}
		this.loyer1Maison = loyer1Maison;
	}
	public int getLoyer2Maison() {
		return loyer2Maison;
	}
	public void setLoyer2Maison(int loyer2Maison) {
		if(this.loyer2Maison<0)
		{ //exception 
			
		}
		this.loyer2Maison = loyer2Maison;
	}
	public int getLoyer3Maison() {
		return loyer3Maison;
	}
	public void setLoyer3Maison(int loyer3Maison) {
		if(this.loyer3Maison<0)
		{ //exception 
			
		}
		this.loyer3Maison = loyer3Maison;
	}
	public int getLoyer4Maison() {
		
		return loyer4Maison;
	}
	public void setLoyer4Maison(int loyer4Maison) {
		if(this.loyer4Maison<0)
		{ //exception 
			
		}
		this.loyer4Maison = loyer4Maison;
	}
	public int getLoyerHotel() {
		return loyerHotel;
	}
	public void setLoyerHotel(int loyerHotel) {
		if(this.loyerHotel<0)
		{ //exception  
		}
		this.loyerHotel = loyerHotel;
	}
	public int getNbMaison() {
		return nbMaison;
	}
	public void setNbMaison(int nbMaison) {
		if(this.nbMaison<0)
		{ //exception 
			
		}
		this.nbMaison = nbMaison;
	}
	public boolean isHotel() {
		return Hotel;
	}
	public void setHotel(boolean hotel) {
		this.Hotel = hotel;
	}
	public GroupeTerrain getGroupe() {
		return this.groupe;
	}
	public void setGroupe(GroupeTerrain groupe) {
		this.groupe = groupe;
	}
	
	public Terrain(int numeroCase, String nomCase, int prixBase, int hypotheque, int loyerDeBase, int loyer1Maison,
			int loyer2Maison, int loyer3Maison, int loyer4Maison, int loyerHotel, GroupeTerrain groupe ){
		super(numeroCase, nomCase, prixBase, hypotheque);
		this.setLoyerDeBase(loyerDeBase);
		this.setLoyer1Maison(loyer1Maison);
		this.setLoyer2Maison(loyer2Maison);
		this.setLoyer3Maison(loyer3Maison);
		this.setLoyer4Maison(loyer4Maison);
		this.setLoyerHotel(loyerHotel);
		this.setNbMaison(0);
		this.setHotel(false);
		this.setGroupe(groupe);
	}
	
	
	//methodes 
	
	public void ajouterMaison() {
		if(this.getNbMaison()>3) {
			//exception
		}
		this.setNbMaison(this.getNbMaison()+1);
	}
	
	public void retirerMaison() {
		if(this.getNbMaison()<1) {
			//exception
		}
		this.setNbMaison(this.getNbMaison()-1);
	}
	
	
	
}
