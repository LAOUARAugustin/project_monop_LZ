package projet_monopoly.joueur;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.Pion;
import Interface.controleurPlateau;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.Cartes;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;

public class JoueurHumain extends Joueur{
	private boolean enPrison;
	private Cases position;
	private int tempsEnPrison;
	private int nbCarteLiberation;
	private Pion Pion;
	

	public boolean isEnPrison() {
		return enPrison;
	}

	public void setEnPrison(boolean enPrison) {
		this.enPrison = enPrison;
	}


	public Cases getPosition() {
		return position;
	}

	public void setPosition(Cases position) {
		this.position = position;
	}
	public int getTempsEnPrison() {
		return this.tempsEnPrison;
	}
	public void setTempsEnPrison(int temps) {
		if(temps<0) {
			throw new IllegalArgumentException("temps en prison negatif");
		}
		this.tempsEnPrison = temps;
	}
	
	public int getNbCarteLiberation() {
		return nbCarteLiberation;
	}

	public void setNbCarteLiberation(int nbCarteLiberation) {
		if(nbCarteLiberation<0) {
			throw new IllegalArgumentException("nbCarteLiberation negatif");
		}
		this.nbCarteLiberation = nbCarteLiberation;
	}
	
	public Pion getPion() {
		return Pion;
	}

	public void setPion(Pion pion) {
		Pion = pion;
	}

	public JoueurHumain(String nom, Cases position, Pion pion) {
		super(nom, 1500);
		this.setEnPrison(false);
		this.setPosition(position);
		this.setTempsEnPrison(0);
		this.setNbCarteLiberation(0);
		this.setPion(pion);
	}

	
	public void seDeplacer(int num)
	{
		if(num<2 || num>12) {
			throw new IllegalArgumentException("num deplacement");
		}
		int numCase=this.getPosition().getNumeroCase()+num;
		if(numCase >=Plateau.nbCases) {
			numCase = numCase - Plateau.nbCases;
			Banque.getInstance().payerJoueur(200, this);// passe par la case depart
		}
		for(int i=0; i < Plateau.getInstance().getListeCases().size() ; i++) { // parcours de la liste
			Cases I = Plateau.getInstance().getListeCases().get(i);
			if(I.getNumeroCase() == numCase) {   //verifie si on a la bonne case
				this.setPosition(I);
			}
		}
	}
	public void seDeplacer(String nom)
	{
		
		for(int i=0; i < Plateau.getInstance().getListeCases().size() ; i++) { // parcours de la liste
			Cases I = Plateau.getInstance().getListeCases().get(i);
			if(I.getNomCase().equals(nom)) {   //verifie si on a la bonne case
				this.setPosition(I);
			}
		}
	}
	public void AllerEnPrison()
	{
		this.setEnPrison(true);
		this.setTempsEnPrison(3);
		this.seDeplacer("PRISON");
	}
	public void SortirDePrison(){
		this.setEnPrison(false);
		this.setTempsEnPrison(0);
	}
	public int LancerDé()
	{
		int min = 1;
        int max = 6;

        int value =  (int) (min+(Math.random()*(max-min +1)));
        return value;
	}
	public void TirerCarteChance() throws alertException
	{
		if(Plateau.getInstance().getListeCartesChance().size() == 0) {
			throw new alertException("Il n'y a plus de carte chance dans le paquet");
		}
		else {
			int min = 0;
	        int max = Plateau.getInstance().getListeCartesChance().size();

	        int value =  (int) (min+(Math.random()*(max-min +1)));
	        boiteAlerte A = new boiteAlerte("Vous avez tirer une carte chance",Plateau.getInstance().getListeCartesChance().get(value).getMessage());
			A.show();
	        Plateau.getInstance().getListeCartesChance().get(value).effet(this);
	        Plateau.getInstance().retirerCarteChance(value);
		}
		
        
	}
	public void TirerCarteCommunaute() throws alertException
	{
		if(Plateau.getInstance().getListeCartesCommunaute().size() == 0) {
			throw new alertException("Il n'y a plus de carte caisse de communauté dans le paquet");
		}
		else {
			int min = 0;
	        int max = Plateau.getInstance().getListeCartesCommunaute().size();


	        int value =  (int) (min+(Math.random()*(max-min +1)));
			boiteAlerte A = new boiteAlerte("Vous avez tirer une carte caisse de communauté",Plateau.getInstance().getListeCartesCommunaute().get(value).getMessage());
			A.show();
	        Plateau.getInstance().getListeCartesCommunaute().get(value).effet(this);
	        Plateau.getInstance().retirerCarteCommunaute(value);
		}
		
        
	}

	public void vendreCarteLiberation(JoueurHumain J) {
		Scanner scanner = new Scanner( System.in );
		System.out.print( "Prix de la carte");
	    int prix = scanner.nextInt();
	    J.payerJoueur(prix,this);
	    J.setNbCarteLiberation(J.getNbCarteLiberation()+1);
	    this.setNbCarteLiberation(J.getNbCarteLiberation()-1);
	}
	
}
