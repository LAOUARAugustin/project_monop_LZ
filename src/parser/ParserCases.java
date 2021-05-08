
package parser;

import Exception.ParserManquantException;

public abstract class ParserCases {
	
	// Un parser est en fait un maillon dans une liste chainée...
	// Cette liste chainée représente une instruction "switch"
	// Chaque maillon représente un "case" du switch
	private ParserCases suivant = null;
	public static final String separateur = ";";
	
	public ParserCases(ParserCases suivant) {
		this.suivant = suivant;
	}

	/**
	 * La fonction traiter() parcours la liste à la recherche d'un maillon qui sait comment parser
	 * la ligne. Dans ce cas la ligne est parsée et la recherche s'arrête
	 * @param ligne la ligne à parser
	 * @exception lance une exception si quelque chose a mal tourné
	 */
	public void traiter(String ligne) throws Exception {
		if (saitParser(ligne)) {
			parser(ligne);
		}
		else if (aUnSuivant()) 
			// S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se débrouille !
			getSuivant().traiter(ligne);
		else {
			
			throw new ParserManquantException();
		}
		
	}

	private ParserCases getSuivant() {
		return suivant;
	}

	private boolean aUnSuivant() {
		return suivant != null;
	}

	/**
	 * Parse une ligne. Renvoie une Exception si quelque chose a mal tourné...
	 * @param ligne
	 * @throws Exception
	 */
	public abstract void parser(String ligne) throws Exception;
	
	/**
	 * Renvoie true si le parser en question reconnait le type de ligne, c'est-à-dire
	 * qu'il sait la "décortiquer", et créer le ou les objets qu'il faut. Il n'y a pas 
	 * d'exception. En cas de problème, on renvoie false !
	 * @param ligne
	 * @return true si la ligne est reconnue
	 */
	public abstract boolean saitParser(String ligne);

}