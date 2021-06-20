package projet_monopoly.Carte;

import java.io.IOException;

import Exception.alertException;
import Exception.boiteAlerte;
import Interface.controleurPlateau;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.Compagnie;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class CartesDeplacement extends Cartes {
	private String nom; // case ou se deplacer
	private boolean passerCaseDepart;
	private int nbCases; // ou bien nb de cases à faire

	@Override
	public void effet(JoueurHumain Joueur) {
		if(nbCases == 0) { // cas ou on se deplace sur une case 
			int anciennePos = Joueur.getPosition().getNumeroCase();
			Joueur.seDeplacer(nom);
			if(Joueur.getPosition().getNumeroCase()<=anciennePos && passerCaseDepart)
				Banque.getInstance().payerJoueur(200, Joueur);
		}
		else { // on se deplace d'un nombre de case
			int anciennePos = Joueur.getPosition().getNumeroCase();
			int nouvellePos = anciennePos + nbCases;
			if(anciennePos<0) {
				nouvellePos = nouvellePos+40;
			}
			if(nouvellePos>=40) {
				nouvellePos = nouvellePos - 40;
			}
			for(int i=0; i < Plateau.getInstance().getListeCases().size() ; i++) { // parcours de la liste
				Cases I = Plateau.getInstance().getListeCases().get(i);
				if(I.getNumeroCase() == nouvellePos) {   //verifie si on a la bonne case
					Joueur.setPosition(I);
				}
			}
			if(Joueur.getPosition().getNumeroCase()<=anciennePos && passerCaseDepart)
				Banque.getInstance().payerJoueur(200, Joueur);
			
		}
		if(Joueur.getPosition() instanceof Compagnie) {
			Compagnie Case = (Compagnie)Joueur.getPosition();
			int Somme = Joueur.LancerDé() + Joueur.LancerDé();
			boiteAlerte.afficherBoite("Vous avez lancé les dés","Les dés ont fait un total de " + Somme);
			Case.arretSurLaCase(Joueur, Somme);
			return;
		}
		try {
			Joueur.getPosition().arretSurLaCase(Joueur);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (alertException e) {
			boiteAlerte.afficherBoite(e);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isPasserCaseDepart() {
		return passerCaseDepart;
	}

	public void setPasserCaseDepart(boolean passerCaseDepart) {
		this.passerCaseDepart = passerCaseDepart;
	}

	public int getNbCases() {
		return nbCases;
	}

	public void setNbCases(int nbCases) {
		this.nbCases = nbCases;
	}

	public CartesDeplacement(String nom, boolean passerCaseDepart, int nbCases, String msg) {
		super(msg);
		this.setNbCases(nbCases);
		this.setNom(nom);
		this.setPasserCaseDepart(passerCaseDepart);
	}

	@Override
	public String toString() {
		return "CartesDeplacement [nom=" + nom + ", passerCaseDepart=" + passerCaseDepart + ", nbCases=" + nbCases
				+ "]";
	}
	
	
}
