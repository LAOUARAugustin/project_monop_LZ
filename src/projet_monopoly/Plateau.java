package projet_monopoly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.alertException;
import Interface.Pion;
import ParserCases.ParserCaseDepart;
import ParserCases.ParserCasesCartes;
import ParserCases.ParserCasesPayer;
import ParserCases.ParserCasesRepos;
import ParserCases.ParserCompagnie;
import ParserCases.ParserGare;
import ParserCases.ParserPrison;
import ParserCases.ParserTerrainConstructible;
import parser.Fichier;
import parserCartes.ParserCartesAnniversaire;
import parserCartes.ParserCartesChances;
import parserCartes.ParserCartesDeplacement;
import parserCartes.ParserCartesEncaisser;
import parserCartes.ParserCartesLiberation;
import parserCartes.ParserCartesPayer;
import parserCartes.ParserCartesReparation;
import projet_monopoly.Carte.Cartes;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Gare;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.Dette;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;
/**
 * Classe représentant le plateau ou se déroule la partie. Contient des fonctions pour la gestion de la partie.
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 *
 */
public class Plateau {
	private ArrayList<Cases> listeCases = new ArrayList<Cases>();
	private ArrayList<Cartes> listeCartesCommunaute= new ArrayList<Cartes>();
	private ArrayList<Cartes> listeCartesChance= new ArrayList<Cartes>();
	private ArrayList<JoueurHumain> listeJoueur= new ArrayList<JoueurHumain>();
	public static final int nbCases = 40;
	private int TourDuJoueur;
	
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
	public ArrayList<JoueurHumain> getListeJoueur() {
		ArrayList<JoueurHumain> listeClone = new ArrayList<JoueurHumain>(this.listeJoueur);
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
	public void retirerCarteChance(int pos)
	{
		this.listeCartesChance.remove(pos);
	}
	public void retirerCarteCommunaute(int pos)
	{
		this.listeCartesCommunaute.remove(pos);
	}
	
	
	public void ajouterJoueur(JoueurHumain j)
	{
		this.listeJoueur.add(j);
	}
	
	/**
	 * Retire le joueur passé en paramètre de la liste de joueur ( et donc de la partie )
	 * @param J
	 * @throws alertException
	 */
	public void retirerJoueur(JoueurHumain J) throws alertException
	{
		if(!this.listeJoueur.remove(J)) {
			throw new alertException("Ce joueur n'est plus dans la partie");
		}

	}
	
	public void setTourDuJoueur(int t) {
		if(t<0 || t>3) {
			throw new IllegalArgumentException("tour joueur");
		}
		this.TourDuJoueur = t;
	}
	
	public int getTourDuJoueur() {
		return this.TourDuJoueur;
	}
	
	/**
	 * Fonction qui gère le changement de tour dans une partie.
	 * Le joueur suivant est l'élément suivant de la liste de joueur.
	 * Si on est en queue de liste, on revient à la tête.
	 */
	public void changerTour() {
		int nbJoueur = this.getListeJoueur().size();
		switch(nbJoueur) {
			case 2:{
				if(TourDuJoueur == 0)
					setTourDuJoueur(1);
				else
					setTourDuJoueur(0);
			}
			break;
			case 3 :{
				if(TourDuJoueur == 2) 
					setTourDuJoueur(0);
				else
					setTourDuJoueur(TourDuJoueur + 1);
			}
			break;
			case 4:{
				if(TourDuJoueur == 3) 
					setTourDuJoueur(0);
				else
					setTourDuJoueur(TourDuJoueur + 1);
			}
		}
	}
	
	public JoueurHumain getJoueurActuel() {
		JoueurHumain J = (JoueurHumain)this.getListeJoueur().get(this.getTourDuJoueur());
		return J;
	}
	
	private Plateau() {
		this.TourDuJoueur = 0;
	}
	
	private static Plateau instance = null;
	
	/**
	 * Nous utilisons le sigleton car il n'y aura qu'un seul plateau.
	 * @return
	 */
	public static Plateau getInstance() {
		if (instance==null)
			instance = new Plateau();
		
		return instance; 
	} 
	
	/**
	 * Initialise les données du jeu avec des parsers.
	 * @param fichierChance
	 * Le fichier des cartes chances.
	 * @param fichierCommunaute
	 * Le fichier des cartes caisses de communautés.
	 * @param fichierTerrain
	 * Le fichier des terrains.
	 */
	public void initFichier(String fichierChance, String fichierCommunaute, String fichierTerrain) {
		ParserCaseDepart PCD = new ParserCaseDepart(null);
		ParserCasesPayer PCP = new ParserCasesPayer(null);
		ParserCompagnie PC = new ParserCompagnie(null);
		ParserGare PG = new ParserGare(null);
		ParserPrison PP = new ParserPrison(null);
		ParserCasesRepos PCR = new ParserCasesRepos(null);
		ParserCasesCartes PCC = new ParserCasesCartes(null);
		ParserTerrainConstructible PTC = new ParserTerrainConstructible(null);
		PCD.setSuivant(PCP);
		PCP.setSuivant(PC);
		PC.setSuivant(PG);
		PG.setSuivant(PP);
		PP.setSuivant(PCR);
		PCR.setSuivant(PCC);
		PCC.setSuivant(PTC);
		Fichier.lire(fichierTerrain,PCD);

		ParserCartesAnniversaire PCaA = new ParserCartesAnniversaire(null);
		ParserCartesChances PCaC = new ParserCartesChances(null);
		ParserCartesDeplacement PCaD = new ParserCartesDeplacement(null);
		ParserCartesEncaisser PCaE = new ParserCartesEncaisser(null);
		ParserCartesLiberation PCaL = new ParserCartesLiberation(null);
		ParserCartesPayer PCaP = new ParserCartesPayer(null);
		ParserCartesReparation PCaR = new ParserCartesReparation(null);
		PCaA.setSuivant(PCaC);
		PCaC.setSuivant(PCaD);
		PCaD.setSuivant(PCaE);
		PCaE.setSuivant(PCaL);
		PCaL.setSuivant(PCaP);
		PCaP.setSuivant(PCaR);
		Fichier.lire(fichierChance,PCaA);
		Fichier.lire(fichierCommunaute,PCaA);
	}
	
	
	/**
	 * Initialise les informations et les pions des joueurs.
	 * Leurs dettes sont toutes initialisés à 0.
	 * @param soldeJoueur
	 * Le solde initiale du joueur
	 * @param soldeBanque
	 * Le solde initiale de la banque.
	 */
	public void initJoueur( int soldeJoueur, int soldeBanque) {
		Pion pion1 = new Pion("/interface/pion1.png");
		Pion pion2 = new Pion("/interface/pion2.png");
		Pion pion3 = new Pion("/interface/pion3.png");
		Pion pion4 = new Pion("/interface/pion4.png");
		initBanque();
		Banque.getInstance().setSolde(soldeBanque);
		for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur()) {
			initDettes(Iterator);
			Iterator.setSolde(soldeJoueur);
			Iterator.setPosition(Plateau.getInstance().getListeCases().get(0));
		}
		Plateau.getInstance().getListeJoueur().get(0).setPion(pion1);
		Plateau.getInstance().getListeJoueur().get(1).setPion(pion2);
		if(Plateau.getInstance().getListeJoueur().size()>2)
			Plateau.getInstance().getListeJoueur().get(2).setPion(pion3);
		if(Plateau.getInstance().getListeJoueur().size()>3)
			Plateau.getInstance().getListeJoueur().get(3).setPion(pion4);
	}
	
	/**
	 * Initialise toutes les dettes du joueur passé en paramètres.
	 * @param J
	 */
	private void initDettes(JoueurHumain J) {
		for(JoueurHumain Iterator : this.listeJoueur) {
			if(!Iterator.equals(J)) {
				Dette D = new Dette(Iterator);
				J.ajouterDette(D);
			}
			
		}
	}
	
	/** 
	 * Initialise la banque et la rend propriétaire de toutes les propriétés en début de partie.
	 */
	private void initBanque() {
		for(Cases Iterator : Plateau.getInstance().getListeCases()) {
			if(Iterator instanceof CasesProprietes) {
				Banque.getInstance().ajouterProprietes((CasesProprietes)Iterator);
				((CasesProprietes) Iterator).setProprietaire(Banque.getInstance());
			}
		}
	}
}
