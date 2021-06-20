package projet_monopoly.Case;

import java.io.IOException;

import Exception.alertException;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public abstract class CasesProprietes extends Cases {
	protected int prixBase;
	private Joueur proprietaire;
	private boolean hypotheque;

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
		this.setHypotheque(false);
		this.setPrixBase(prixBase);
		this.setProprietaire(Banque.getInstance());
	}
	
	public boolean isHypotheque() {
		return hypotheque;
	}
	public void setHypotheque(boolean hypotheque) {
		this.hypotheque = hypotheque;
	}
	public void arretSurLaCase(JoueurHumain J) throws IOException {
		//defini dans les classes enfants
	}
	
	public abstract void ajouterMaison() throws alertException;
	public abstract void retirerMaison() throws alertException;
	public abstract void ajouterHotel() throws alertException;
	public abstract void retirerHotel() throws alertException;
	public abstract void echangerHotel() throws alertException;
	
	
}
