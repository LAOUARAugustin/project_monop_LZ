package projet_monopoly;

import java.util.ArrayList;

public class Joueur {
	private String nom;
	private int solde;
	private boolean enPrison;
	private ArrayList<CasesProprietes> proprietes=new ArrayList<CasesProprietes>();
	private Cases position;
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getSolde() {
		return solde;
	}

	public void setSolde(int solde) {
		this.solde = solde;
	}

	public boolean isEnPrison() {
		return enPrison;
	}

	public void setEnPrison(boolean enPrison) {
		this.enPrison = enPrison;
	}

	public ArrayList<CasesProprietes> getProprietes() {
		ArrayList<CasesProprietes> listCopie = new ArrayList<CasesProprietes>(this.proprietes);	
		return listCopie;
	}

	public void setProprietes(ArrayList<CasesProprietes> proprietes) {
		this.proprietes = proprietes;
	}

	public Cases getPosition() {
		return position;
	}

	public void setPosition(Cases position) {
		this.position = position;
	}
	
	public Joueur(String nom, int solde, boolean enPrison, ArrayList<CasesProprietes> proprietes, Cases position) {
		 
		this.setNom(nom);
		this.setSolde(solde);
		this.setEnPrison(enPrison);
		this.setPosition(position);
	}

	public void ajouterProprietes(CasesProprietes prop)
	{
		this.proprietes.add(prop);
	}
	public void enleverProprietes(CasesProprietes prop)
	{
		if ( this.proprietes.contains(prop))
		{
			this.proprietes.remove(prop);
		}
		else 
		{
			//exception 
		}
	}
	
	public void seDeplacer(int num)
	{
		// parcourir l'arraylist et assigner joueur.position a la cases de position i + num
		
	}

}
