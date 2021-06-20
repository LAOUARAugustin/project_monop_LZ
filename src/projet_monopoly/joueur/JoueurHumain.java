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
import projet_monopoly.Carte.CartesLiberation;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;
/**
 * Classe repr�sentant un joueur du jeu. 
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 *
 */
public class JoueurHumain extends Joueur{
	private boolean enPrison;
	private Cases position;
	private int tempsEnPrison;
	private int nbCarteLiberation;
	private Pion Pion;
	private ArrayList<CasesProprietes> listeHypotheque=new ArrayList<CasesProprietes>();

	

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
	public ArrayList<CasesProprietes> getHypotheque() {
		ArrayList<CasesProprietes> listCopie = new ArrayList<CasesProprietes>(this.listeHypotheque);	
		return listCopie;
	}
	
	public void ajouterHypotheque(CasesProprietes prop)
	{
		this.listeHypotheque.add(prop);
	}
	
	public void enleverHypotheque(CasesProprietes prop) throws alertException
	{
		if ( this.listeHypotheque.contains(prop))
		{
			this.listeHypotheque.remove(prop);
		}
		else 
		{
			throw new alertException("Vous ne possedez pas cette propriete");

		}
	}
	
	
	public JoueurHumain(String nom, Cases position, Pion pion) {
		super(nom, 0);
		Dette detteBanque = new Dette(Banque.getInstance());
		this.listeDettes.add(detteBanque);
		this.setEnPrison(false);
		this.setPosition(position);
		this.setTempsEnPrison(0);
		this.setNbCarteLiberation(0);
		this.setPion(pion);
	}

	/**
	 * D�place un joueur d'un certain nombre de case depuis sa position initiale.
	 * Lance une exception si la valeur est inf�rieur � 2 ou sup�rieur � 12 ( valeur minimale et maximale possible avec deux d�s )
	 * @param num
	 */
	public void seDeplacer(int num)
	{
		if(num<2 || num>12) {
			throw new IllegalArgumentException("num deplacement");
		}
		int numCase=this.getPosition().getNumeroCase()+num;
		if(numCase >=Plateau.nbCases) {
			numCase = numCase - Plateau.nbCases;
			Banque.getInstance().payerJoueur(200, this);// passe par la case depart
			controleurPlateau.passerMessage(this.nom +" est pass� par la case d�part et a re�u 200 euros");
		}
		for(int i=0; i < Plateau.getInstance().getListeCases().size() ; i++) { // parcours de la liste
			Cases I = Plateau.getInstance().getListeCases().get(i);
			if(I.getNumeroCase() == numCase) {   //verifie si on a la bonne case
				this.setPosition(I);
			}
		}
	}
	
	/**
	 * D�place un joueur sur une case dont le nom est pass� en param�tre
	 * @param nom
	 */
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
		this.seDeplacer("SIMPLE VISITE");
	}
	public void SortirDePrison(){
		this.setEnPrison(false);
		this.setTempsEnPrison(0);
	}
	public int LancerD�()
	{
		int min = 1;
        int max = 6;

        int value =  (int) (min+(Math.random()*(max-min +1)));
        return value;
	}
	
	/** 
	 * Tire une carte al�atoire dans la liste de cartes chances
	 * @throws alertException
	 * Exception d�stin� � lancer une boite d'alerte
	 */
	public void TirerCarteChance() throws alertException
	{
		if(Plateau.getInstance().getListeCartesChance().size() == 0) {
			throw new alertException("Il n'y a plus de carte chance dans le paquet");
		}
		else {
			int min = 0;
	        int max = Plateau.getInstance().getListeCartesChance().size();

	        int value =  (int) (min+(Math.random()*(max-min)));
	        boiteAlerte.afficherBoite("Vous avez tirer une carte chance",Plateau.getInstance().getListeCartesChance().get(value).getMessage());
	        Plateau.getInstance().getListeCartesChance().get(value).effet(this);
	        Plateau.getInstance().retirerCarteChance(value);
	       
		}
		
        
	}
	
	/** 
	 * Tire une carte al�atoire dans la liste de cartes caisse de communaut�
	 * @throws alertException
	 * Exception d�stin� � lancer une boite d'alerte
	 */
	public void TirerCarteCommunaute() throws alertException
	{
		if(Plateau.getInstance().getListeCartesCommunaute().size() == 0) {
			throw new alertException("Il n'y a plus de carte caisse de communaut� dans le paquet");
		}
		else {
			int min = 0;
	        int max = Plateau.getInstance().getListeCartesCommunaute().size();


	        int value =  (int) (min+(Math.random()*(max-min)));
	        boiteAlerte.afficherBoite("Vous avez tirer une carte caisse de communaut�",Plateau.getInstance().getListeCartesCommunaute().get(value).getMessage());
	        Plateau.getInstance().getListeCartesCommunaute().get(value).effet(this);
	        Plateau.getInstance().retirerCarteCommunaute(value);
		}
		
        
	}

	/**
	 * Vend une carte de lib�ration � un joueur pass� en param�tre, � un prix convenue entre les deux joueurs.
	 * @param J
	 * @param prix
	 * @throws alertException
	 */
	public void vendreCarteLiberation(JoueurHumain J,int prix) throws alertException {
		if(this.nbCarteLiberation<1) {
			throw new alertException("Vous n'avez aucune carte de lib�ration");
		}
	    J.payerJoueur(prix,this);
	    J.setNbCarteLiberation(J.getNbCarteLiberation()+1);
	    this.setNbCarteLiberation(J.getNbCarteLiberation()-1);
	}
	
	/**
	 * Met une propri�t� pass� en param�tre, que le joueur doit poss�d� en hypoth�que.
	 * Elle est supprim� de sa liste de propri�t�s et est pass� dans sa liste d'hypoth�ques
	 * @param C
	 * @throws alertException
	 */
	public void mettreHypotheque(CasesProprietes C) throws alertException {
		if(C instanceof Terrain) {
			Terrain T = (Terrain)C;
			if(T.getNbMaison()> 0 || T.isHotel()) {
				throw new alertException("Vous devez d'abord vendre vos batiments avant de pouvoir hypoth�qu� cette propri�t�s");
			}
		}
		C.setHypotheque(true);
		this.ajouterHypotheque(C);
		this.enleverProprietes(C);
		Banque.getInstance().payerJoueur((int)(C.getPrixBase()/2), this); 
	}
	
	
	/**
	 * L�ve l'hypoth�que de la propri�t� pass� en parametre. Elle revient donc dans sa liste de propri�t�s.
	 * @param C
	 * @throws alertException
	 */
	public void leverHypotheque(CasesProprietes C) throws alertException {
		if(this.getSolde()<(C.getPrixBase()/2)*1.1) {
			throw new alertException("Votre solde est insuffisant");
		}
		C.setHypotheque(false);
		this.ajouterProprietes(C);
		this.enleverHypotheque(C);
		this.payerJoueur(((int)((C.getPrixBase()/2)*1.1)), Banque.getInstance());
	}
	/**
	 * Vend une propri�t� hypoth�qu�. Elle sera pass� directement dans la liste des propri�t�s hypoth�qu�s du joueur qui l'a achet�.
	 * @param C
	 * @param montant
	 * @param acheteur
	 * @throws alertException
	 */
	public void vendreHypotheque(CasesProprietes C, int montant, JoueurHumain acheteur) throws alertException {
		if(acheteur.getSolde()<montant) {
			throw new alertException("Les fonds de l'acheteur sont insuffisants");
		}
		acheteur.payerJoueur(montant, this);
		this.enleverHypotheque(C);
		acheteur.ajouterHypotheque(C);
		C.setProprietaire(acheteur);
	}
	/**
	 * Calcul la valeur totale des biens que poss�de le joueur. La valeur des terrains est la valeur de leurs hypoth�que, et la valeur des batiments est celle de leurs prix de revente � la banque.
	 * @return
	 */
	public int fortune() {
		int valeurHotel = 0 ,valeurMaisons = 0,Solde,ValHypotheque=0;
		Solde=this.getSolde();
		for(CasesProprietes iterator: this.getProprietes())
		{
			ValHypotheque += iterator.getPrixBase()/2;
			if(iterator instanceof Terrain)
			{
				Terrain T = (Terrain)iterator;
				valeurMaisons +=T.getNbMaison()*(T.getPrixAchatMaison()/2);
				if(T.isHotel())
					valeurHotel += (T.getPrixAchatMaison()*5)/2;
			}
		}
		return valeurHotel + valeurMaisons + Solde + ValHypotheque;
	}
	
	/**
	 * Renvoi vrai si le joueur est en faillite, et applique toutes les modalit�s si le joueurs fait faillite.
	 * ( Donner toutes ses propri�t�s au joueur qui l'a mis en faillite, donner son solde etc ... ). 
	 * @return
	 */
	public boolean Faillite() {
		for(Dette Iterator : this.getDettes()) {
			if(Iterator.getMontantDette()>this.fortune()) {
					boiteAlerte.afficherBoite("Faillite", this.getNom() + " a fait faillite ! " + Iterator.getBeneficiere().getNom() + " r�cup�re sa fortune.");	
					this.payerJoueur(this.getSolde(), Iterator.getBeneficiere());
					for(CasesProprietes Iterator2 : this.getProprietes()) {
						if(Iterator2 instanceof Terrain) {
							Terrain T = (Terrain)Iterator2;
							Banque.getInstance().payerJoueur((T.getNbMaison()*T.getPrixAchatMaison())/2, Iterator.getBeneficiere());
							Banque.getInstance().setNbMaisons(Banque.getInstance().getNbMaisons() + T.getNbMaison());
							T.setNbMaison(0);
							if(T.isHotel()) {
								Banque.getInstance().payerJoueur((T.getPrixAchatMaison()*5)/2, Iterator.getBeneficiere());
								Banque.getInstance().setNbHotel(Banque.getInstance().getNbHotel()+1);
								try {
									T.retirerHotel();
								} catch (alertException e) {
									boiteAlerte.afficherBoite(e);
								}
							}
						}
						Iterator2.setProprietaire(Iterator.getBeneficiere());
						Iterator.getBeneficiere().ajouterProprietes(Iterator2);
						try {
							this.enleverProprietes(Iterator2);
						} catch (alertException e) {
							boiteAlerte.afficherBoite(e);
						}
						}
					for(CasesProprietes Iterator2 : this.getHypotheque()) {
						int prix = (int)((Iterator2.getPrixBase()/2)*1.1);
						Iterator.getBeneficiere().payerJoueur(prix, Banque.getInstance());
						Iterator2.setHypotheque(false);
						Iterator2.setProprietaire(Iterator.getBeneficiere());
						Iterator.getBeneficiere().ajouterProprietes(Iterator2);
						try {
							this.enleverHypotheque(Iterator2);
						} catch (alertException e) {
							boiteAlerte.afficherBoite(e);
						}
						
						}
				if(Iterator.getBeneficiere().equals(Banque.getInstance())) {
					for(int i=0;i<this.getNbCarteLiberation();i++) {
						CartesLiberation C = new CartesLiberation("Vous �tes lib�r� de prison. Cette carte peut �tre conserv�e jusqu'� ce qu'elle soit utilis�e ou vendue.");
						Plateau.getInstance().getListeCartesChance().add(C);
					}
				}
				else {			
					JoueurHumain J = (JoueurHumain)Iterator.getBeneficiere();
					J.setNbCarteLiberation(J.getNbCarteLiberation() + this.getNbCarteLiberation());
				}
				try {
					Plateau.getInstance().retirerJoueur(this);
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
				return true;
			}
		}
		return false;
	}
}
