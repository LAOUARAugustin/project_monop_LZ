package projet_monopoly.Carte;

import Interface.controleurPlateau;
import projet_monopoly.Plateau;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class CartesAnniversaire extends Cartes {


private int montant;
	
	@Override
	public void effet(JoueurHumain Joueur) {
		
		for(Joueur iterator :Plateau.getInstance().getListeJoueur())
		{
			if(!iterator.equals(Joueur))
			{
				iterator.payerJoueur(montant, Joueur);
				controleurPlateau.passerMessage(Joueur.getNom()+" a reçu "+montant+" pour son anniversaire !");
			}
		}
	}
	
	
	
	public CartesAnniversaire(int montant, String msg) {
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
		return "CartesAnniversaire [montant=" + montant + "]";
	}
	
}
