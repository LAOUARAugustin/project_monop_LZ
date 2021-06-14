package projet_monopoly.Case;

import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class CasesPayer extends Cases {
	private int montant;

	
	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	
	
	public CasesPayer(int numeroCase, String nomCase, int montant) {
		super(numeroCase, nomCase);
		this.setMontant(montant);
	}

	@Override
	public void arretSurLaCase(JoueurHumain J) {
			J.payerJoueur(montant,Banque.getInstance());
		
	}

	
	
	

}
