package projet_monopoly.Carte;

import projet_monopoly.Case.Cases;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.JoueurHumain;

public class CartesReparation implements Cartes{
	private int prixParMaison;
	private int prixParHotel;
	@Override
	public void effet(JoueurHumain Joueur) {
		int nbMaison = 0;
		int nbHotel = 0;
		for(Cases iterator : Joueur.getProprietes()) {
			if(iterator instanceof Terrain) {
				nbMaison = ((Terrain) iterator).getNbMaison() + nbMaison;
				if(((Terrain) iterator).isHotel()) {
					nbHotel ++;
				}
			}
		}
		int somme = nbMaison*prixParMaison + nbHotel*prixParHotel;
		Joueur.payer(somme);
	}

}
