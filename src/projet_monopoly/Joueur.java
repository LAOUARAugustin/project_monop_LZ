package projet_monopoly;

import java.util.ArrayList;

public abstract class Joueur {
	protected int solde;
	protected ArrayList<CasesProprietes> listeProprietes=new ArrayList<CasesProprietes>();
	
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

	public void setProprietes(ArrayList<CasesProprietes> proprietes) {
		this.listeProprietes = proprietes;
	}	
	
	
	
	public Joueur(int solde) {
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
	
	
}
