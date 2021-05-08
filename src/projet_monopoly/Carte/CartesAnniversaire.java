package projet_monopoly.Carte;

import projet_monopoly.Plateau;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class CartesAnniversaire implements Cartes {

private int montant;
	
	@Override
	public void effet(JoueurHumain Joueur) {
		Joueur.recevoir(montant*Plateau.getInstance().getListeJoueur().size());
		
		for(Joueur iterator :Plateau.getInstance().getListeJoueur())
		{
			if(!(iterator.equals(Joueur) || iterator.getNom().equals("Banque")))
			{
				iterator.payer(10);
			}
		}
	}
	
	
	public CartesAnniversaire(int montant) {
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
