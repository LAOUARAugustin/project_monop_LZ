package projet_monopoly;

import java.util.ArrayList;

public class Plateau {
	private ArrayList<Cases> listeCases = new ArrayList<Cases>();

	public ArrayList<Cases> getListeCases() {
		ArrayList<Cases> listeClone = new ArrayList<Cases>(this.listeCases);
		return listeClone;
	}

	public void setListeCases(ArrayList<Cases> listeCases) {
		this.listeCases = listeCases;
	}
	public void ajouterCase(Cases c)
	{
		this.listeCases.add(c);
	}

	public Plateau(ArrayList<Cases> listeCases) {
		this.listeCases = listeCases;
	}
	
}
