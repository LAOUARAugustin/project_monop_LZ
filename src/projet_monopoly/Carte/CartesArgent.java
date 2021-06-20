package projet_monopoly.Carte;

import Interface.controleurPlateau;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class CartesArgent extends Cartes {
	private int montant;
	
	@Override
	public void effet(JoueurHumain Joueur) {
		if(this.montant>0) {
			Banque.getInstance().payerJoueur(montant, Joueur);
		}
		else {
			Joueur.payerJoueur(-(montant), Banque.getInstance());
		}
	}
	
	
	public CartesArgent(int montant, String msg) {
		super(msg);
		this.setMontant(montant);
	}


	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}


	@Override
	public String toString() {
		return "CartesArgent [montant=" + montant + "]";
	}
	
}
