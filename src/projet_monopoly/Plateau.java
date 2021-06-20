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
	
	
	public static Plateau getInstance() {
		if (instance==null)
			instance = new Plateau();
		
		return instance; 
	}
	//Methods 
	
	
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
	public void initJoueur(JoueurHumain J1, JoueurHumain J2) {
		initBanque();
		Plateau.getInstance().ajouterJoueur(J1);
		Plateau.getInstance().ajouterJoueur(J2);
		initDettes(J1);
		initDettes(J2);
	}
	public void initJoueur(JoueurHumain J1, JoueurHumain J2, JoueurHumain J3) {
		initBanque();
		Plateau.getInstance().ajouterJoueur(J1);
		Plateau.getInstance().ajouterJoueur(J2);
		Plateau.getInstance().ajouterJoueur(J3);
		initDettes(J1);
		initDettes(J2);
		initDettes(J3);
	}
	public void initJoueur(JoueurHumain J1, JoueurHumain J2, JoueurHumain J3, JoueurHumain J4 ) {
		initBanque();
		Plateau.getInstance().ajouterJoueur(J1);
		Plateau.getInstance().ajouterJoueur(J2);
		Plateau.getInstance().ajouterJoueur(J3);
		Plateau.getInstance().ajouterJoueur(J4);
		initDettes(J1);
		initDettes(J2);
		initDettes(J3);
		initDettes(J4);
	}
	private void initDettes(JoueurHumain J) {
		for(JoueurHumain Iterator : this.listeJoueur) {
			if(!Iterator.equals(J)) {
				Dette D = new Dette(Iterator);
				J.ajouterDette(D);
			}
			
		}
	}
	private void initBanque() {
		for(Cases Iterator : Plateau.getInstance().getListeCases()) {
			if(Iterator instanceof CasesProprietes) {
				Banque.getInstance().ajouterProprietes((CasesProprietes)Iterator);
				((CasesProprietes) Iterator).setProprietaire(Banque.getInstance());
			}
		}
	}
	/*int Tour = 1;
	boolean isGagnant = false;
	while(!isGagnant) {
		for(Joueur iterator : this.getListeJoueur()) {
			if(iterator instanceof JoueurHumain) {
				//JouerTour((JoueurHumain) iterator);
				}
			if(this.getListeJoueur().size()<2) {
				isGagnant = true;
				String gagnant = this.getListeJoueur().get(0).getNom();
				System.out.println("Partie terminée, le gagnant est " + gagnant);
			}
		}
		Tour ++;
	}*/
}
