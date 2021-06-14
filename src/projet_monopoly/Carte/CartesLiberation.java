package projet_monopoly.Carte;

import java.util.Scanner;

import Interface.controleurPlateau;
import projet_monopoly.joueur.JoueurHumain;

public class CartesLiberation extends Cartes{

	@Override
	public void effet(JoueurHumain Joueur) {
		Joueur.setNbCarteLiberation(Joueur.getNbCarteLiberation()+1);
	}
	public CartesLiberation(String msg) {
		super(msg);
	}
	@Override
	public String toString() {
		return "CartesLiberation []";
	}
	
}
