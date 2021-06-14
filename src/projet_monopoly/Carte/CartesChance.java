package projet_monopoly.Carte;

import java.util.Scanner;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.controleurPlateau;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;



public class CartesChance extends Cartes {
	private int montant;
	
	
	public int getMontant() {
		return montant;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public CartesChance(int montant, String msg) {
		super(msg);
		this.setMontant(montant);
	}
	@Override
	public void effet(JoueurHumain Joueur) throws alertException {
		Alert alert = new Alert(AlertType.CONFIRMATION,"Voulez-vous tirer une carte chance ? \n Sinon payez une amende de 10 euros. ", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			
			Joueur.TirerCarteChance();
			}
		else {
			Joueur.payerJoueur(montant,Banque.getInstance());	
			controleurPlateau.passerMessage(Joueur.getNom()+" a payé "+montant);
		}
		
	}
	@Override
	public String toString() {
		return "CartesChance [montant=" + montant + "]";
	}
	
}
