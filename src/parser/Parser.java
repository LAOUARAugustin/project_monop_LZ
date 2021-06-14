
package parser;

import Exception.ParserManquantException;

public abstract class Parser {
	
	private Parser suivant = null;
	public static final String separateur = ";";
	
	public Parser(Parser suivant) {
		this.suivant = suivant;
	}


	public void traiter(String ligne) throws Exception {
		if (saitParser(ligne)) {
			parser(ligne);
		}
		else if (aUnSuivant()) 
			getSuivant().traiter(ligne);
		else {
			
			throw new ParserManquantException();
		}
		
	}

	private Parser getSuivant() {
		return suivant;
	}
	
	public void setSuivant(Parser P) {
		this.suivant = P;
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