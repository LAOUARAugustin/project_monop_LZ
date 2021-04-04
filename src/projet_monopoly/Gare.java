package projet_monopoly;

public class Gare extends CasesProprietes{
	private int loyer;
	
	public int getLoyer() {
		return this.loyer;
	}
	public void setLoyer(int loyer){
		if(loyer<0) {
			//exception
		}
		this.loyer = loyer;
	}
	
	public Gare(int numCase, String nom, int prixBase, int hypotheque, int loyer) {
		super(numCase,nom,prixBase,hypotheque);
		this.setLoyer(loyer);
	}
	
	
}