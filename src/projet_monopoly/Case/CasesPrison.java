package projet_monopoly.Case;

import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class CasesPrison extends Cases{

	public CasesPrison(int numeroCase, String nomCase) {
		super(numeroCase, nomCase);
	}

	@Override
	public void arretSurLaCase(JoueurHumain J) {
		J.AllerEnPrison();
	}

}
