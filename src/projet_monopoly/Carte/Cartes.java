package projet_monopoly.Carte;

import Exception.alertException;
import projet_monopoly.joueur.JoueurHumain;
/**
 * Classe abstraite dont les classes enfants représentent tout les types de cartes du jeu.
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 *
 */
public abstract class  Cartes {
	protected String message;
	/**
	 * Effet appliqué sur l joueur passé en parametre lorsqu'il tire la carte.
	 * @param Joueur
	 * @throws alertException
	 */
	public abstract void effet(JoueurHumain Joueur) throws alertException;
	
	public String getMessage() {
		return this.message;
	}
	
	public Cartes(String msg){
		this.message = msg;
	}
}
