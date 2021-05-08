package projet_monopoly.Case;

import projet_monopoly.joueur.Joueur;

public abstract class CasesProprietes extends Cases {
	protected int prixBase;
	private Joueur proprietaire;

	public int getPrixBase() {
		return prixBase;
	}
	public void setPrixBase(int prixBase) {
		this.prixBase = prixBase;
	}
	public Joueur getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(Joueur proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public CasesProprietes(int numeroCase, String nomCase, int prixBase) {
		super(numeroCase, nomCase);
		this.setPrixBase(prixBase);
	}
	
	
}
