package projet_monopoly.Case;

public class Gare extends CasesProprietes{
	private int loyer;
	
	public int getLoyer() {
		return this.loyer;
	}
	public void setLoyer(int loyer){
		if(loyer<0) {
			throw new IllegalArgumentException("GARE : loyer");		
			}
		this.loyer = loyer;
	}
	
	public Gare(int numCase, String nom, int prixBase, int loyer) {
		super(numCase,nom,prixBase);
		this.setLoyer(loyer);
	}
	@Override
	public void arreteSurLaCase() {
		// TODO Auto-generated method stub
		
	}
	
	
}