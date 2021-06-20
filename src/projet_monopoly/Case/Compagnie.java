package projet_monopoly.Case;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.controleurPlateau;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class Compagnie extends CasesProprietes {

	public Compagnie(int numeroCase, String nomCase, int prixBase) {
		super(numeroCase, nomCase, prixBase);
		
	}
	
	public void arretSurLaCase(JoueurHumain J, int d�) {
		if(this.isHypotheque()) {
			return;
		}
		if(this.getProprietaire() == Banque.getInstance()) {
			if(this.getProprietaire() == Banque.getInstance()) {
				Alert alert = new Alert(AlertType.CONFIRMATION, "La propri�t� " + this.getNomCase() + " (" + this.getPrixBase() + " euros) " + " n'appartient � aucun joueur, voulez vous l'acheter ?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					try {
						J.acheter(this, this.getProprietaire(), this.getPrixBase());
						controleurPlateau.passerMessage(this.getProprietaire().getNom() + " a achet� " + this.getNomCase()+ " (" + this.getPrixBase() + " euros)");
					} catch (alertException e) {
						boiteAlerte.afficherBoite(e);
					}
				}
		}
		else if(this.getProprietaire() != J) {
			int nbCompagnie = 0;
			for(Cases iterator : this.getProprietaire().getProprietes()) {
				if(iterator instanceof Compagnie) {
					nbCompagnie ++;
					}
				}
				if(nbCompagnie == 1) {
					J.payerJoueur(d�*4,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a pay� un loyer de "+ d�*4 + " � "+ this.getProprietaire().getNom());
					}
				if(nbCompagnie == 2) {
					J.payerJoueur(d�*10,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a pay� un loyer de "+ d�*10 + " � "+ this.getProprietaire().getNom());
					}
			}
		}
	}

	@Override
	public void ajouterMaison() throws alertException {
		throw new alertException("Vous ne pouvez pas construire de maisons sur une compagnie");	
	}

	@Override
	public void retirerMaison() throws alertException {
		throw new alertException("Vous ne pouvez pas construire de maisons sur une compagnie");
		
	}

	@Override
	public void ajouterHotel() throws alertException {
		throw new alertException("Vous ne pouvez pas construire d'hotel sur une compagnie");
		
	}

	@Override
	public void retirerHotel() throws alertException {
		throw new alertException("Il ne peut pas y avoir d'hotel sur une compagnie");
		
	}

	@Override
	public void echangerHotel() throws alertException {
		throw new alertException("Il ne peut pas y avoir d'hotel sur une compagnie");
		
	}


}
