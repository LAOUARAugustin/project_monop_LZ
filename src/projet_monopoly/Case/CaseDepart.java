package projet_monopoly.Case;

import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class CaseDepart extends Cases{
	private int SommeDepart;
	public CaseDepart(int numeroCase, String nomCase, int SommeDepart) {
		super(numeroCase, nomCase);
		this.SommeDepart = SommeDepart;
	}
	public int getSommeDepart() {
		return SommeDepart;
	}
	public void setSommeDepart(int sommeDepart) {
		if(sommeDepart < 0) {
			throw new IllegalArgumentException("somme invalide");
		}
		SommeDepart = sommeDepart;
	}
	@Override
	public void arretSurLaCase(JoueurHumain J) {	
		
	}

}
