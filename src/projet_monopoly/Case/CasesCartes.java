package projet_monopoly.Case;

import Exception.alertException;
import Interface.controleurPlateau;
import projet_monopoly.joueur.JoueurHumain;

public class CasesCartes extends Cases{
	public CasesCartes(int numeroCase, String nomCase) {
		super(numeroCase, nomCase);	}
	
	
	/**
	 * Methode qui fait tir� une carte au joueur si il s'arrete sur une case carte.
	 * @param J 
	 * Le joueur qui s'arrete sur la case
	 */
	@Override
	public void arretSurLaCase(JoueurHumain J) throws alertException  {
		if(this.nomCase.equals("CAISSE COMMUNAUTE")) {
			J.TirerCarteCommunaute();
			controleurPlateau.passerMessage(J.getNom() + " a tir� une carte caisse de communaut�.");
		}
		if(this.nomCase.equals("CHANCE")) {
			J.TirerCarteChance();
			controleurPlateau.passerMessage(J.getNom() + " a tir� une carte chance." );

		}
	}
	
}
