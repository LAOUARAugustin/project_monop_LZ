package projet_monopoly;

import java.util.ArrayList;

public class JoueurHumain extends Joueur{
	private String nom;
	private boolean enPrison;
	private Cases position;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

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
	
	public JoueurHumain(String nom, int solde, boolean enPrison, ArrayList<CasesProprietes> proprietes, Cases position) {
		super(solde);
		this.setNom(nom);
		this.setEnPrison(enPrison);
		this.setPosition(position);
	}

	
	public void seDeplacer(int num)
	{
		// parcourir l'arraylist et assigner joueur.position a la cases de position i + num
		
	}

}
