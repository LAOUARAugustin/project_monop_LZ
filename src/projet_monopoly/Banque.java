package projet_monopoly;

import java.util.ArrayList;

public class Banque {
	private int solde;
	private ArrayList<CasesProprietes> listeProprietes=new ArrayList<CasesProprietes>();
	private int nbMaisons;
	private int nbHotel;
	
	public int getSolde() {
		return solde;
	}
	public void setSolde(int solde) {
		this.solde = solde;
	}
	public int getNbMaisons() {
		return nbMaisons;
	}
	public void setNbMaisons(int nbMaisons) {
		if(nbMaisons<0) {
			//exception
		}
		this.nbMaisons = nbMaisons;
	}
	public int getNbHotel() {
		return nbHotel;
	}
	public void setNbHotel(int nbHotel) {
		if(nbHotel<0) {
			//exception
		}
		this.nbHotel = nbHotel;
	}
	public ArrayList<CasesProprietes> getListeProprietes() {
		ArrayList<CasesProprietes> listeCopie = new ArrayList<CasesProprietes>(this.listeProprietes);
		return listeCopie;
	}
	
	public void setListeProprietes(ArrayList<CasesProprietes> listeProprietes) {
		this.listeProprietes = listeProprietes;
	}
	
	public Banque(int solde, int nbMaisons, int nbHotel, ArrayList<CasesProprietes> prop) {
		
		this.setSolde(solde);
		this.setNbMaisons(nbMaisons);
		this.setNbHotel(nbHotel);
		this.listeProprietes= prop;
	}
	
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
	
	
	

}
