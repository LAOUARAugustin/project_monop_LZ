package projet_monopoly;

public abstract class CasesProprietes extends Cases {
	protected int prixBase;
	protected int hypotheque;
	private Joueur proprietaire;

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
	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public CasesProprietes(int numeroCase, String nomCase, int prixBase, int hypotheque) {
		super(numeroCase, nomCase);
		this.setPrixBase(prixBase);
		this.setHypotheque(hypotheque);  
	}
	
	
}
