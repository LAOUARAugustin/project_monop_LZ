package projet_monopoly.Case;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.controleurPlateau;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class Gare extends CasesProprietes{
	private int loyer1Gare;
	private int loyer2Gare;
	private int loyer3Gare;
	private int loyer4Gare;
	
	public int getLoyer1Gare() {
		return this.loyer1Gare;
	}
	public void setLoyer1Gare(int loyer){
		if(loyer<0) {
			throw new IllegalArgumentException("GARE : loyer");		
			}
		this.loyer1Gare = loyer;
	}
	public int getLoyer2Gare() {
		return this.loyer2Gare;
	}
	public void setLoyer2Gare(int loyer){
		if(loyer<0) {
			throw new IllegalArgumentException("GARE : loyer");		
			}
		this.loyer2Gare = loyer;
	}
	public int getLoyer3Gare() {
		return this.loyer3Gare;
	}
	public void setLoyer3Gare(int loyer){
		if(loyer<0) {
			throw new IllegalArgumentException("GARE : loyer");		
			}
		this.loyer3Gare = loyer;
	}
	public int getLoyer4Gare() {
		return this.loyer4Gare;
	}
	public void setLoyer4Gare(int loyer){
		if(loyer<0) {
			throw new IllegalArgumentException("GARE : loyer");		
			}
		this.loyer4Gare = loyer;
	}
	
	public Gare(int numCase, String nom, int prixBase, int loyer1, int loyer2, int loyer3, int loyer4) {
		super(numCase,nom,prixBase);
		this.setLoyer1Gare(loyer1);
		this.setLoyer2Gare(loyer2);
		this.setLoyer3Gare(loyer3);
		this.setLoyer4Gare(loyer4);
	}
	
	
	@Override
	public void arretSurLaCase(JoueurHumain J) {
		if(this.isHypotheque()) {
			return;
		}
		if(this.getProprietaire() == Banque.getInstance()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "La propriété " + this.getNomCase() +  " (" + this.getPrixBase() + " euros) " + " n'appartient à aucun joueur, voulez vous l'acheter ?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				try {
					J.acheter(this, this.getProprietaire(), this.getPrixBase());
					controleurPlateau.passerMessage(this.getProprietaire().getNom() + " a acheté " + this.getNomCase() + " (" + this.getPrixBase() + " euros)");
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
		}
		else if(this.getProprietaire() != J){
			int nbGare = 0;
			for(Cases iterator : this.getProprietaire().getProprietes()) {
				if(iterator instanceof Gare) {
					nbGare ++;
					}
				}
			switch(nbGare) {
				case 1:{
					J.payerJoueur(loyer1Gare,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer1Gare + " à "+ this.getProprietaire().getNom());
				}
				
				break;
				case 2:{
					J.payerJoueur(loyer2Gare,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer2Gare + " à "+ this.getProprietaire().getNom());

				}
				break;
				case 3:{
					J.payerJoueur(loyer3Gare,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer3Gare + " à "+ this.getProprietaire().getNom());

				}
				break;
				case 4:{
					J.payerJoueur(loyer4Gare,this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer4Gare + " à "+ this.getProprietaire().getNom());

				}
			}
		}
	}
	@Override
	public void ajouterMaison() throws alertException {
		throw new alertException("Vous ne pouvez pas construire de maisons sur une gare");
		
	}
	@Override
	public void retirerMaison() throws alertException {
		throw new alertException("Il ne peut pas y avoir de maisons sur une gare");
		
	}
	@Override
	public void ajouterHotel() throws alertException {
		throw new alertException("Vous ne pouvez pas construire d'hotel sur une gare");
		
	}
	@Override
	public void retirerHotel() throws alertException {
		throw new alertException("Il ne peut pas y avoir d'hotel sur une gare");
		
	}
	
	@Override
	public void echangerHotel() throws alertException {
		throw new alertException("Il ne peut pas y avoir d'hotel sur une compagnie");
		
	}
	
	
}