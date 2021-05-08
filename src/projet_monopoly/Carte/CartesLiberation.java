package projet_monopoly.Carte;

import projet_monopoly.joueur.JoueurHumain;

public class CartesLiberation implements Cartes{

	@Override
	public void effet(JoueurHumain Joueur) {
		Joueur.SortirDePrison();
		
	}

}
