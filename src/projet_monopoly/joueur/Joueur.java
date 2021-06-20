package projet_monopoly.joueur;

import java.util.ArrayList;

import Exception.alertException;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;
/**
 * Classe représentant une entité active du jeu. Cela peut être un joueur physique ( joueur humain ) ou bien la banque.
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 *
 */
public abstract class Joueur {
	protected String nom;
	protected int solde;
	protected ArrayList<CasesProprietes> listeProprietes=new ArrayList<CasesProprietes>();
	protected ArrayList<Dette> listeDettes=new ArrayList<Dette>();

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		if(nom.trim().isEmpty()) {
			throw new IllegalArgumentException("Nom vide");
		}
		this.nom = nom;
	}
	public int getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}
	public ArrayList<CasesProprietes> getProprietes() {
		ArrayList<CasesProprietes> listCopie = new ArrayList<CasesProprietes>(this.listeProprietes);	
		return listCopie;
	}
	public ArrayList<Dette> getDettes() {
		ArrayList<Dette> listCopie = new ArrayList<Dette>(this.listeDettes);	
		return listCopie;
	}
	public Joueur(String nom, int solde) {
		this.setNom(nom);
		this.setSolde(solde);
	}
	//methodes
	
	/**
	 * Ajouter une dette à la liste de dette
	 * @param d
	 * La dette a ajouté
	 */
	public void ajouterDette(Dette d) {
		this.listeDettes.add(d);
	}
	
	
	/**
	 * Ajouter une propriete à la liste de propriété
	 * @param prop
	 * La propriete a ajouté
	 */
	public void ajouterProprietes(CasesProprietes prop)
	{
		this.listeProprietes.add(prop);
	}
	
	/**
	 * Retire une propriété de la liste de propriété
	 * @param prop
	 * La propriété a retiré
	 * @throws alertException
	 * Une exception destiné a lancer une boite d'alerte.
	 */
	public void enleverProprietes(CasesProprietes prop) throws alertException
	{
		if ( this.listeProprietes.contains(prop))
		{
			this.listeProprietes.remove(prop);
		}
		else 
		{
			throw new alertException("Vous ne possedez pas cette propriete");

		}
	}
	
	
	/**
	 * Retourne vrai si le joueur possede la propriete passé en paramètre
	 * @param prop
	 * @return
	 */
	public boolean possede(CasesProprietes prop) {
		if(this.listeProprietes.contains(prop)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Le joueur vend une propriété à un autre joueur ( qui peut etre la banque ) à un prix fixé.
	 * @param propriete
	 * La propriété à vendre
	 * @param acheteur
	 * Le joueur qui va obtenir la propriété
	 * @param prix
	 * @throws alertException
	 * Exception qui sera destiné à ouvrir une boite d'alerte dans le cas ou toutes les conditions ne sont pas réunis pour la vente.
	 */
	public void vendre(CasesProprietes propriete, Joueur acheteur, int prix) throws alertException {
		if(propriete instanceof Terrain) {
			Terrain T1 = (Terrain)propriete;
			for(Cases iterator : this.getProprietes()) {
				if(iterator instanceof Terrain) {
					Terrain T2 = ((Terrain) iterator);
					if(T2.getGroupe().equals(T1.getGroupe()) && (T2.getNbMaison()>0 || T2.isHotel()) ) {
						throw new alertException("Vous ne pouvez pas vendre une propriété s'il y a des constructions sur le groupe de propriété");
					}
				}
			}
		}
		if(acheteur.getSolde()<prix) {
			throw new alertException("Les fonds de l'acheteur sont insuffisants");
		}
		if(!(this.possede(propriete))) {
			throw new alertException("Vous ne possedez pas cette propriete");
		}
		acheteur.payerJoueur(prix, this);
		this.enleverProprietes(propriete);
		acheteur.ajouterProprietes(propriete);
		propriete.setProprietaire(acheteur);
			}
	
	/**
	 * Le joueur achete une propriété à un autre joueur
	 * @param propriete
	 * @param vendeur
	 * @param prix
	 * @throws alertException
	 * Exception qui sera destiné à ouvrir une boite d'alerte dans le cas ou toutes les conditions ne sont pas réunis pour la vente.

	 */
	public void acheter(CasesProprietes propriete, Joueur vendeur, int prix) throws alertException {
		if(this.getSolde()<prix) {
			throw new alertException("Les fonds de l'acheteur sont insuffisants");
		}
		if(!(vendeur.possede(propriete))) {
			throw new alertException("Le vendeur ne possede pas cette propriete");
		}
		this.payerJoueur(prix, vendeur);
		vendeur.enleverProprietes(propriete);
		this.ajouterProprietes(propriete);
		propriete.setProprietaire(this);
	}
	
	/**
	 * Le joueur recoit la somme passé en parametre ( elle va s'ajouté à son solde )
	 * @param somme
	 */
	private void recevoir(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()+somme);
	}
	/**
	 * Le joueur paye la somme passé en paramètre ( elle va se retirer de son solde ). 
	 * @param somme
	 */
	private void payer(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()-somme);
		
	}
	
	
	/**
	 * Le joueur va payer le joueur passé en paramètre d'un montant passé en paramètre.
	 * @param somme
	 * @param J
	 * Le joueur qui va recevoir la somme.
	 */
	
	public void payerJoueur(int somme, Joueur J) {
		if(this.solde<somme) {
			for(Dette Iterator : this.listeDettes) {
				if(Iterator.getBeneficiere().equals(J)) {
					Iterator.ajouterDette(somme - this.solde);
					this.payer(somme);
					J.recevoir(somme);
					return;
				}
			}
		}
		this.payer(somme);
		J.recevoir(somme);
	}
	
	/**
	 * Renvoi vrai si le joueur a au moins une dette supérieur à 0.
	 * @return
	 */
	public boolean estEndette() {
		for(Dette Iterator : this.listeDettes) {
			if(Iterator.aUneDette())
				return true;
		}
		return false;
	}
	
	/**
	 * Renvoi le montant total de ses dettes. C'est à dire la somme de toutes ses dettes.
	 * @return
	 */
	public int montantDetteTotal() {
		int montant=0;
		for(Dette Iterator : this.listeDettes) {
			montant += Iterator.getMontantDette();
		}
		return montant;
	}
	
	/**
	 * Le joueur rembourse une dette d'un montant passé en paramètre. Il peut remboursé seulement une partie de la dette.
	 * @param montant
	 * @param J
	 * @throws alertException
	 * Exception destiné a ouvrir une boite d'alerte 
	 */
	public void rembourser(int montant, Joueur J) throws alertException {
		if(this.getSolde()<montant) {
			throw new alertException("Vous n'avez pas le solde nécessaire pour effectuer le remboursement");
		}
		for(Dette Iterator : this.getDettes()) {
			if(Iterator.getBeneficiere().equals(J)) {
				Iterator.payerDette(montant);
				this.payerJoueur(montant, J);
			}
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	

	
	
}
