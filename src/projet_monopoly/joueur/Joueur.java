package projet_monopoly.joueur;

import java.util.ArrayList;

import Exception.alertException;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;

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
	public void ajouterDette(Dette d) {
		this.listeDettes.add(d);
	}
	public void ajouterProprietes(CasesProprietes prop)
	{
		this.listeProprietes.add(prop);
	}
	
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
	
	public boolean possede(CasesProprietes prop) {
		if(this.listeProprietes.contains(prop)) {
			return true;
		}
		return false;
	}
	
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
	private void recevoir(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()+somme);
	}
	private void payer(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()-somme);
		
	}
	
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
	
	public boolean estEndette() {
		for(Dette Iterator : this.listeDettes) {
			if(Iterator.aUneDette())
				return true;
		}
		return false;
	}
	
	public int montantDetteTotal() {
		int montant=0;
		for(Dette Iterator : this.listeDettes) {
			montant += Iterator.getMontantDette();
		}
		return montant;
	}
	
	public void rembourser(int montant, Joueur J) throws alertException {
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
