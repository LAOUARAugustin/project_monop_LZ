package projet_monopoly.Case;

import Exception.boiteAlerte;
import Interface.controleurPlateau;
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


	/**
	 * Methode qui fait payé le joueur qui s'arrrete sur une case tel que TAXE DE LUXE.
	 * @param J 
	 * Le joueur qui s'arrete sur la case
	 */
	@Override
	public void arretSurLaCase(JoueurHumain J) {
			J.payerJoueur(montant,Banque.getInstance());
			boiteAlerte.afficherBoite("Arret sur " + this.getNomCase(), "Vous avez payer " + this.getMontant() + " euros.");
			controleurPlateau.passerMessage(J.getNom()+" a payé des taxes à hauteur de "+ montant + " euros");
		
	}

	
	
	

}
