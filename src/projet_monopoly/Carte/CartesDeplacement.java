package projet_monopoly.Carte;

import projet_monopoly.Plateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.joueur.JoueurHumain;

public class CartesDeplacement implements Cartes {
	private String nom; // case ou se deplacer
	private boolean passerCaseDepart;
	private int nbCases; // ou bien nb de cases à faire

	@Override
	public void effet(JoueurHumain Joueur) {
		if(nbCases == 0) { // cas ou on se deplace sur une case 
			for(Cases iterator : Plateau.getInstance().getListeCases()) 
			{
				if(iterator.getNomCase().equals(nom)) {
					if(passerCaseDepart) { // si on doit gagner de l'argent si on passe par case depart
						if(iterator.getNumeroCase()<=Joueur.getPosition().getNumeroCase()){ // on passe par la case depart
							Joueur.seDeplacer(0);
						}
					}
					Joueur.seDeplacer(iterator.getNumeroCase());
				}
			}
		}
		else { // on se deplace d'un nombre de case
			Joueur.seDeplacer(nbCases);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if(nom.trim().isEmpty()) {
			throw new IllegalArgumentException("Nom de la case ou se deplacer");
		}
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

	public CartesDeplacement(String nom, boolean passerCaseDepart, int nbCases) {
		super();
		this.setNbCases(nbCases);
		this.setNom(nom);
		this.setPasserCaseDepart(passerCaseDepart);
	}
	
	
}
