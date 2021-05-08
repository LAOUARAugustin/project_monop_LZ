package projet_monopoly.Carte;

import java.util.Scanner;

import projet_monopoly.joueur.JoueurHumain;



public class CartesChance implements Cartes {
	private int montant;
	
	
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public CartesChance(int montant) {
		super();
		this.setMontant(montant);
	}
	@Override
	public void effet(JoueurHumain Joueur) {
		try ( Scanner scanner = new Scanner( System.in ) ) {
            
            System.out.print( "choisir 1 pour payer 2 pour chance : " );
            int choix = scanner.nextInt();
		if(choix == 1) {
			Joueur.payer(10);
		}
		else {
			Joueur.TirerCarteChance();
		}
	}
	}
}
