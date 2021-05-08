package projet_monopoly.joueur;

import java.util.ArrayList;
import java.util.Random;

import projet_monopoly.Plateau;
import projet_monopoly.Carte.Cartes;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;

public class JoueurHumain extends Joueur{
	private boolean enPrison;
	private Cases position;
	

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
		super(nom, solde);
		this.setEnPrison(enPrison);
		this.setPosition(position);
	}

	
	public void seDeplacer(int num)
	{
		int numCase=this.getPosition().getNumeroCase();
		numCase=numCase+num;
		if(numCase > Plateau.nbCases) {
			numCase = numCase - Plateau.nbCases;
		}
		for(int i=0; i < Plateau.getInstance().getListeCases().size() ; i++) { // parcours de la liste
			Cases I = Plateau.getInstance().getListeCases().get(i);
			if(I.getNumeroCase() == num) {   //verifie si on a la bonne case
				this.setPosition(I);
			}
		}
	}
	public void AllerEnPrison()
	{
		this.setEnPrison(true);
	}
	public void SortirDePrison(){
		this.setEnPrison(false);
	}
	public int LancerDé()
	{
		int min = 1;
        int max = 6;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        return value;
	}
	public void TirerCarteChance()
	{
		if(Plateau.getInstance().getListeCartesChance().size() == 0) {
			//execption pas d'element
		}
		int min = 0;
        int max = Plateau.getInstance().getListeCartesChance().size() -1;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        Plateau.getInstance().getListeCartesChance().get(value).effet(this);
        Plateau.getInstance().getListeCartesChance().remove(value);   
        
	}
	public void TirerCarteCommunaute()
	{
		if(Plateau.getInstance().getListeCartesCommunaute().size() == 0) {
			//execption pas d'element
		}
		int min = 0;
        int max = Plateau.getInstance().getListeCartesCommunaute().size() -1;

        Random random = new Random();

        int value = random.nextInt(max + min) + min;
        Plateau.getInstance().getListeCartesCommunaute().get(value).effet(this);
        Plateau.getInstance().getListeCartesCommunaute().remove(value);   
        
	}

	
	
}
