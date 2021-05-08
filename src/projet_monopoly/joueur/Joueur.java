package projet_monopoly.joueur;

import java.util.ArrayList;

import projet_monopoly.Case.CasesProprietes;

public abstract class Joueur {
	protected String nom;
	protected int solde;
	protected ArrayList<CasesProprietes> listeProprietes=new ArrayList<CasesProprietes>();
	
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
	public Joueur(String nom, int solde) {
		this.setNom(nom);
		this.setSolde(solde);
	}
	//methodes
	
	public void ajouterProprietes(CasesProprietes prop)
	{
		this.listeProprietes.add(prop);
	}
	
	public void enleverProprietes(CasesProprietes prop)
	{
		if ( this.listeProprietes.contains(prop))
		{
			this.listeProprietes.remove(prop);
		}
		else 
		{
			//exception 
		}
	}
	
	public boolean possede(CasesProprietes prop) {
		if(this.listeProprietes.contains(prop)) {
			return true;
		}
		return false;
	}
	
	public void vendre(CasesProprietes propriete, Joueur acheteur) {
		int prix = propriete.getPrixBase();
		if(acheteur.getSolde()<prix) {
			//exception
		}
		if(!(this.possede(propriete))) {
			//exception
		}
		this.setSolde(this.getSolde()+prix);
		acheteur.setSolde(acheteur.getSolde()-prix);
		this.enleverProprietes(propriete);
		acheteur.ajouterProprietes(propriete);
		propriete.setProprietaire(acheteur);
		
	}
	
	
	public void acheter(CasesProprietes propriete, Joueur vendeur) {
		int prix = propriete.getPrixBase();
		if(this.getSolde()<prix) {
			//exception
		}
		if(!(vendeur.possede(propriete))) {
			//exception
		}
		this.setSolde(this.getSolde()-prix);
		vendeur.setSolde(vendeur.getSolde()+prix);
		vendeur.enleverProprietes(propriete);
		this.ajouterProprietes(propriete);
		propriete.setProprietaire(this);
	}
	public void recevoir(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()+somme);
	}
	public void payer(int somme)
	{
		if(somme<0)
		{throw new IllegalArgumentException();}
		this.setSolde(this.getSolde()-somme);
		
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
