package projet_monopoly.Carte;

import Exception.alertException;
import projet_monopoly.joueur.JoueurHumain;

public abstract class  Cartes {
	protected String message;
	
	public abstract void effet(JoueurHumain Joueur) throws alertException;
	
	public String getMessage() {
		return this.message;
	}
	
	public Cartes(String msg){
		this.message = msg;
	}
}
