package projet_monopoly.Case;

import Exception.alertException;
import Interface.controleurPlateau;
import projet_monopoly.joueur.JoueurHumain;

public class CasesCartes extends Cases{
	public CasesCartes(int numeroCase, String nomCase) {
		super(numeroCase, nomCase);	}

	@Override
	public void arretSurLaCase(JoueurHumain J) throws alertException  {
		if(this.nomCase.equals("CAISSE COMMUNAUTE")) {
			J.TirerCarteCommunaute();
			controleurPlateau.passerMessage(J.getNom() + " a tiré une carte caisse de communauté");
		}
		if(this.nomCase.equals("CHANCE")) {
			J.TirerCarteChance();
			controleurPlateau.passerMessage(J.getNom() + " a tiré une carte chance");

		}
	}
	
}
