package projet_monopoly.Case;

import projet_monopoly.joueur.JoueurHumain;

import java.io.IOException;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.controleurPlateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import projet_monopoly.joueur.Banque;

public class Terrain extends CasesProprietes {
	private int loyerDeBase;
	private int loyer1Maison;
	private int loyer2Maison;
	private int loyer3Maison;
	private int loyer4Maison;
	private int PrixAchatMaison;
	private int loyerHotel;
	private int nbMaison;
	private boolean Hotel;
	private String groupe;
	
	
	public int getLoyerDeBase() {
		return loyerDeBase;
	}
	public void setLoyerDeBase(int loyerDeBase) {
		if(this.loyerDeBase<0)
		{ 
			throw new IllegalArgumentException("loyer de base");
		}
		this.loyerDeBase = loyerDeBase;
	}
	public int getLoyer1Maison() {
		return loyer1Maison;
	}
	public void setLoyer1Maison(int loyer1Maison) {
		if(this.loyer1Maison<0)
		{ 
			throw new IllegalArgumentException("loyer 1 maison");
		}
		this.loyer1Maison = loyer1Maison;
	}
	public int getLoyer2Maison() {
		return loyer2Maison;
	}
	public void setLoyer2Maison(int loyer2Maison) {
		if(this.loyer2Maison<0)
		{ 
			throw new IllegalArgumentException("loyer 2 maison");
		}
		this.loyer2Maison = loyer2Maison;
	}
	public int getLoyer3Maison() {
		return loyer3Maison;
	}
	public void setLoyer3Maison(int loyer3Maison) {
		if(this.loyer3Maison<0)
		{ 
			throw new IllegalArgumentException("loyer 3 maisons");
		}
		this.loyer3Maison = loyer3Maison;
	}
	public int getLoyer4Maison() {
		
		return loyer4Maison;
	}
	public void setLoyer4Maison(int loyer4Maison) {
		if(this.loyer4Maison<0)
		{ 
			throw new IllegalArgumentException("loyer 4 maisons");
		}
		this.loyer4Maison = loyer4Maison;
	}
	public int getLoyerHotel() {
		return loyerHotel;
	}
	public void setLoyerHotel(int loyerHotel) {
		if(this.loyerHotel<0)
		{ 
			throw new IllegalArgumentException("loyer hotel");
		}
		this.loyerHotel = loyerHotel;
	}
	public int getNbMaison() {
		return nbMaison;
	}
	public void setNbMaison(int nbMaison) {
		if(this.nbMaison<0)
		{ 
			throw new IllegalArgumentException("nb maisonsS");
		}
		this.nbMaison = nbMaison;
	}
	public boolean isHotel() {
		return Hotel;
	}
	private void setHotel(boolean hotel) {
		this.Hotel = hotel;
	}
	public int getPrixAchatMaison() {
		return PrixAchatMaison;
	}
	public void setPrixAchatMaison(int prixAchatMaison) {
		if(prixAchatMaison<0)
		{
			throw new IllegalArgumentException("prix d'achat invalide");
		}
		PrixAchatMaison = prixAchatMaison;
	}
	public String getGroupe() {
		
		return groupe;
	}
	public void setGroupe(String groupe) {
		if(groupe.trim().isEmpty())
		{
			throw new IllegalArgumentException("nom du groupe invalide");
		}
		this.groupe = groupe;
	}
	
	public Terrain(int numeroCase, String nomCase, int prixBase, int loyerDeBase, int loyer1Maison,
			int loyer2Maison, int loyer3Maison, int loyer4Maison, int loyerHotel,String groupe , int prixAchatMaison){
		super(numeroCase, nomCase, prixBase);
		this.setLoyerDeBase(loyerDeBase);
		this.setLoyer1Maison(loyer1Maison);
		this.setLoyer2Maison(loyer2Maison);
		this.setLoyer3Maison(loyer3Maison);
		this.setLoyer4Maison(loyer4Maison);
		this.setLoyerHotel(loyerHotel);
		this.setNbMaison(0);
		this.setHotel(false);
		this.setGroupe(groupe);
		this.setPrixAchatMaison(prixAchatMaison);
		
	}
	
	
	//methodes 
	
	public void ajouterMaison() throws alertException {
		if(this.getNbMaison()>3) {
			throw new alertException("Impossible d'ajouter plus de 4 maisons");
		}
		else {
			if(this.toutLesTerrains()) {
				if(this.nbMaison == 0) {
					this.setNbMaison(this.getNbMaison()+1);
					this.getProprietaire().payerJoueur(PrixAchatMaison,Banque.getInstance());
	
				}
				else {
					boolean droitDeConstruire = true;
					for(Cases iterator : this.getProprietaire().getProprietes()) {
						if(iterator instanceof Terrain) {
							Terrain T = ((Terrain) iterator);
							if(T.getGroupe().equals(this.groupe) && this.nbMaison > T.getNbMaison()) {
								droitDeConstruire = false;
							}
						}
					}
					if(droitDeConstruire) {
						this.setNbMaison(this.getNbMaison()+1);
						this.getProprietaire().payerJoueur(PrixAchatMaison,Banque.getInstance());
					}
					else {
						throw new alertException("Impossible d'ajouter la maison, vous n'avez pas assez de maisons sur les autres terrains du groupe");
					}
				}
			}
			else {
				throw new alertException("Impossible d'ajouter une maison, vous n'avez pas tout les terrains du groupe");
			}
		}
	}
	
	public void retirerMaison() throws alertException {
		if(this.getNbMaison()<1) {
			throw new alertException("Vous n'avez aucune maison");
			}
		boolean droitDeRetrait = true;
		for(Cases iterator : this.getProprietaire().getProprietes()) {
			if(iterator instanceof Terrain) {
				Terrain T = ((Terrain) iterator);
				if(T.getGroupe().equals(this.groupe) && this.nbMaison < T.getNbMaison()) {
					droitDeRetrait = false;
				}
			}
		}
		if(droitDeRetrait) {
			this.setNbMaison(this.getNbMaison()-1);
			Banque.getInstance().payerJoueur(this.PrixAchatMaison/2, this.getProprietaire());
		}
		else {
			throw new alertException("Vous devez retirer les maisons de maniere uniforme");
		}
	}
	
	public void ajouterHotel() throws alertException {
		if(this.getNbMaison()== 4) {
			this.setHotel(true);
			this.setNbMaison(0);
			this.getProprietaire().payerJoueur(PrixAchatMaison, Banque.getInstance());
		}
		else {
			throw new alertException("Vous n'avez pas assez de maisons");
		}
	}
	
	public void retirerHotel() throws alertException {
		boolean isHotel = false;
		for(Cases iterator : this.getProprietaire().getProprietes()) {
			if(iterator instanceof Terrain) {
				if(((Terrain) iterator).getGroupe().equals(this.groupe)) {
					if(this.isHotel() == true) {
						isHotel = true;
						}
					}
				}
			}
		if(!isHotel) {
			throw new alertException("Vous n'avez aucun hotel");	
		}
		Alert alert = new Alert(AlertType.CONFIRMATION, "Tout les hotels du groupe seront retirer, voulez vous continuer ?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			for(Cases iterator : this.getProprietaire().getProprietes()) {
				if(iterator instanceof Terrain) {
					if(((Terrain) iterator).getGroupe().equals(this.groupe)) {	
								((Terrain)iterator).setHotel(false);
								Banque.getInstance().payerJoueur((this.PrixAchatMaison * 5)/2, this.getProprietaire());						
						}
					}
				}
			}
	}
	public boolean toutLesTerrains() {
		boolean toutLesTerrains = false;
		int nbTerrain = 0;
		
		for(Cases iterator : this.getProprietaire().getProprietes()) {
			if(iterator instanceof Terrain) {

				if(((Terrain) iterator).getGroupe().equals(this.groupe)) {
					nbTerrain ++;
				}
			}
		}
		if(this.getGroupe().equals("MARRON") && nbTerrain == 2) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("BLEU CIEL") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("VIOLET") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("ORANGE") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("ROUGE") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("JAUNE") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("VERT") && nbTerrain == 3) {
			toutLesTerrains = true;
		}
		if(this.getGroupe().equals("BLEU FONCE") && nbTerrain == 2) {
			toutLesTerrains = true;
		}
		return toutLesTerrains;
	}
	@Override
	public void arretSurLaCase(JoueurHumain J)  {
		if(this.getProprietaire() == Banque.getInstance()) {
			Alert alert = new Alert(AlertType.CONFIRMATION, "La propriété " + this.getNomCase() + " (" + this.getGroupe() + ") n'appartient à aucun joueur, voulez vous l'acheter ?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				try {
					J.acheter(this, this.getProprietaire(), this.getPrixBase());
					controleurPlateau.passerMessage(this.getProprietaire().getNom() + " a acheté " + this.getNomCase() + " (" + this.getGroupe() + ")");
				} catch (alertException e) {
					boiteAlerte A = new boiteAlerte("Achat impossible",e.getMsg());
					A.show();
				}
			}
		}
		else if(this.getProprietaire() != J){
			
			if(this.toutLesTerrains()) {
				if(this.isHotel()) {
					J.payerJoueur(loyerHotel, this.getProprietaire());
					controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyerHotel + " à "+ this.getProprietaire().getNom());
				}
				else {
				switch(this.nbMaison) {
					case 0 : {
						boolean auMoinsUneMaison = false;
						for(Cases iterator : this.getProprietaire().getProprietes()) {
							if(iterator instanceof Terrain) {
								if(((Terrain) iterator).getGroupe() == this.groupe && ((Terrain) iterator).getNbMaison()>0) {
									auMoinsUneMaison = true;
								}
							}
						}
						if(auMoinsUneMaison) {
							J.payerJoueur(2*loyerDeBase, this.getProprietaire());
							controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ 2*loyerDeBase + " à "+ this.getProprietaire().getNom());
							}
						else {
							J.payerJoueur(loyerDeBase, this.getProprietaire());
							controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyerDeBase + " à "+ this.getProprietaire().getNom());
							}
						}
					break;
					case 1 : {
						J.payerJoueur(loyer1Maison, this.getProprietaire());
						controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer1Maison + " à "+ this.getProprietaire().getNom());
						}
					break;
					case 2 : {
						J.payerJoueur(loyer2Maison, this.getProprietaire());
						controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer2Maison + " à "+ this.getProprietaire().getNom());
						}
					break;
					case 3 : {
						J.payerJoueur(loyer3Maison, this.getProprietaire());
						controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer3Maison + " à "+ this.getProprietaire().getNom());
						
						}
					break;
					case 4 : {
						J.payerJoueur(loyer4Maison, this.getProprietaire());
						controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyer4Maison + " à "+ this.getProprietaire().getNom());
						}
					
					}
				}
			}
		else {
			J.payerJoueur(loyerDeBase, this.getProprietaire());
			controleurPlateau.passerMessage(J.getNom() +" a payé un loyer de "+ loyerDeBase + " à "+ this.getProprietaire().getNom());
			}
		}
	}
	
	
}
