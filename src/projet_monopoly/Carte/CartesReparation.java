package projet_monopoly.Carte;

import Interface.controleurPlateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class CartesReparation extends Cartes{
	private int prixParMaison;
	private int prixParHotel;
	
	public CartesReparation(int prixParMaison, int prixParHotel, String msg) {
		super(msg);
		this.setPrixParMaison(prixParMaison);
		this.setPrixParHotel(prixParHotel);
	}

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
		Joueur.payerJoueur(somme,Banque.getInstance());
	}

	public int getPrixParMaison() {
		return prixParMaison;
	}

	public void setPrixParMaison(int prixParMaison) {
		if(prixParMaison<0)
			throw new IllegalArgumentException("prix maison");
		this.prixParMaison = prixParMaison;
	}

	public int getPrixParHotel() {
		return prixParHotel;
	}

	public void setPrixParHotel(int prixParHotel) {
		if(prixParHotel<0)
			throw new IllegalArgumentException("prix hotel");
		this.prixParHotel = prixParHotel;
	}

	@Override
	public String toString() {
		return "CartesReparation [prixParMaison=" + prixParMaison + ", prixParHotel=" + prixParHotel + "]";
	}
	
}
