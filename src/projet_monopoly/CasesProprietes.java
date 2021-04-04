package projet_monopoly;

public abstract class CasesProprietes extends Cases {
	protected int prixBase;
	protected int hypotheque;
	public int getPrixBase() {
		return prixBase;
	}
	public void setPrixBase(int prixBase) {
		this.prixBase = prixBase;
	}
	public int getHypotheque() {
		return hypotheque;
	}
	public void setHypotheque(int hypotheque) {
		this.hypotheque = hypotheque;
	}
	public CasesProprietes(int numeroCase, String nomCase, int prixBase, int hypotheque) {
		super(numeroCase, nomCase);
		this.setPrixBase(prixBase);
		this.setHypotheque(hypotheque);  
	}
	
	
}
