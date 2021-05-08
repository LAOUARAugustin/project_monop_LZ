package projet_monopoly;

import java.util.ArrayList;

import projet_monopoly.Carte.Cartes;
import projet_monopoly.Case.Cases;
import projet_monopoly.joueur.Joueur;

public class Plateau {
	private ArrayList<Cases> listeCases = new ArrayList<Cases>();
	private ArrayList<Cartes> listeCartesCommunaute= new ArrayList<Cartes>();
	private ArrayList<Cartes> listeCartesChance= new ArrayList<Cartes>();
	private ArrayList<Joueur> listeJoueur= new ArrayList<Joueur>();
	public static final int nbCases = 10;
	
	public ArrayList<Cases> getListeCases() {
		ArrayList<Cases> listeClone = new ArrayList<Cases>(this.listeCases);
		return listeClone;
	}

	public ArrayList<Cartes> getListeCartesCommunaute() {
		ArrayList<Cartes> listeClone = new ArrayList<Cartes>(this.listeCartesCommunaute);
		return listeClone;
	}

	public ArrayList<Cartes> getListeCartesChance() {
		ArrayList<Cartes> listeClone = new ArrayList<Cartes>(this.listeCartesChance);
		return listeClone;
	}
	public ArrayList<Joueur> getListeJoueur() {
		ArrayList<Joueur> listeClone = new ArrayList<Joueur>(this.listeJoueur);
		return listeClone;
	}
	public void ajouterCase(Cases c)
	{
		this.listeCases.add(c);
	}
	public void ajouterCarteChance(Cartes c)
	{
		this.listeCartesChance.add(c);
	}
	public void ajouterCarteCommunaute(Cartes c)
	{
		this.listeCartesCommunaute.add(c);
	}
	public void ajouterJoueur(Joueur j)
	{
		this.listeJoueur.add(j);
	}

	private Plateau() {
	}
	
	private static Plateau instance = null;
	
	
	public static Plateau getInstance() {
		if (instance==null)
			instance = new Plateau();
		
		return instance; 
	}
	//Methods 
	

	
}
