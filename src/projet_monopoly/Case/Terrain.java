package projet_monopoly.Case;

public class Terrain extends CasesProprietes {
	private int loyerDeBase;
	private int loyer1Maison;
	private int loyer2Maison;
	private int loyer3Maison;
	private int loyer4Maison;
	private int PrixAchatMaison;
	private int loyerHotel;
	private int nbMaison;
	private boolean Hotel;
	private String groupe;
	
	
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
	private void setHotel(boolean hotel) {
		this.Hotel = hotel;
	}
	public int getPrixAchatMaison() {
		return PrixAchatMaison;
	}
	public void setPrixAchatMaison(int prixAchatMaison) {
		if(prixAchatMaison<0)
		{
			throw new IllegalArgumentException("prix d'achat invalide");
		}
		PrixAchatMaison = prixAchatMaison;
	}
	public String getGroupe() {
		
		return groupe;
	}
	public void setGroupe(String groupe) {
		if(groupe.trim().isEmpty())
		{
			throw new IllegalArgumentException("nom du groupe invalide");
		}
		this.groupe = groupe;
	}
	
	public Terrain(int numeroCase, String nomCase, int prixBase, int loyerDeBase, int loyer1Maison,
			int loyer2Maison, int loyer3Maison, int loyer4Maison, int loyerHotel,String groupe , int prixAchatMaison){
		super(numeroCase, nomCase, prixBase);
		this.setLoyerDeBase(loyerDeBase);
		this.setLoyer1Maison(loyer1Maison);
		this.setLoyer2Maison(loyer2Maison);
		this.setLoyer3Maison(loyer3Maison);
		this.setLoyer4Maison(loyer4Maison);
		this.setLoyerHotel(loyerHotel);
		this.setNbMaison(0);
		this.setHotel(false);
		this.setGroupe(groupe);
		this.setPrixAchatMaison(prixAchatMaison);
		
	}
	
	
	//methodes 
	
	public void ajouterMaison() {
		if(this.getNbMaison()>3) {
			//exception qui lance une boite d'alerte 
		}
		this.setNbMaison(this.getNbMaison()+1);
	}
	
	public void retirerMaison() {
		if(this.getNbMaison()<1) {
			//exception qui lance une boite d'alerte		
			}
		this.setNbMaison(this.getNbMaison()-1);
	}
	
	public void ajouterHotel() {
		if(this.getNbMaison()== 4) 
			this.setHotel(true);
			
		else {
			//exception qui lance une boite d'alerte
		}
	}
	
	public void retirerHotel() {
		if(this.isHotel() == true)
			this.setHotel(false);
		else {
			//exception qui lance une boite d'alerte
		}
	}
	@Override
	public void arreteSurLaCase() {
		// TODO Auto-generated method stub
		
	}
}
