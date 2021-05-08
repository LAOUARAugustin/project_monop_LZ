
package parser;

import Exception.ParserManquantException;

public abstract class ParserCases {
	
	// Un parser est en fait un maillon dans une liste chain�e...
	// Cette liste chain�e repr�sente une instruction "switch"
	// Chaque maillon repr�sente un "case" du switch
	private ParserCases suivant = null;
	public static final String separateur = ";";
	
	public ParserCases(ParserCases suivant) {
		this.suivant = suivant;
	}

	/**
	 * La fonction traiter() parcours la liste � la recherche d'un maillon qui sait comment parser
	 * la ligne. Dans ce cas la ligne est pars�e et la recherche s'arr�te
	 * @param ligne la ligne � parser
	 * @exception lance une exception si quelque chose a mal tourn�
	 */
	public void traiter(String ligne) throws Exception {
		if (saitParser(ligne)) {
			parser(ligne);
		}
		else if (aUnSuivant()) 
			// S'il ne sait pas mais qu'il a un suivant dans la liste chaine, il lui repasse la ligne et qu'il se d�brouille !
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
	 * Parse une ligne. Renvoie une Exception si quelque chose a mal tourn�...
	 * @param ligne
	 * @throws Exception
	 */
	public abstract void parser(String ligne) throws Exception;
	
	/**
	 * Renvoie true si le parser en question reconnait le type de ligne, c'est-�-dire
	 * qu'il sait la "d�cortiquer", et cr�er le ou les objets qu'il faut. Il n'y a pas 
	 * d'exception. En cas de probl�me, on renvoie false !
	 * @param ligne
	 * @return true si la ligne est reconnue
	 */
	public abstract boolean saitParser(String ligne);

}