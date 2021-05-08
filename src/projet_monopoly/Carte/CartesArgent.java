package projet_monopoly.Carte;

import projet_monopoly.joueur.JoueurHumain;

public class CartesArgent implements Cartes {
	private int montant;
	
	@Override
	public void effet(JoueurHumain Joueur) {
		if(this.montant>0) {
			Joueur.recevoir(this.montant);
		}
		else {
			Joueur.payer(-(this.montant));
		}
	}
	
	
	public CartesArgent(int montant) {
		super();
		this.setMontant(montant);
	}


	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
}
