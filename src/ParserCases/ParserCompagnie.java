package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Compagnie;
import projet_monopoly.Case.Gare;

public class ParserCompagnie extends Parser {

	public ParserCompagnie(Parser suivant) {
		super(suivant);
		
	}

	@Override
	public void parser(String ligne) throws Exception {
		
			String mot[] = ligne.split(separateur);
			int numCase = Integer.parseInt(mot[0]);
			String nom = mot[2];
			int loyerBase= Integer.parseInt(mot[3]);
			Compagnie C= new Compagnie(numCase,nom,loyerBase);
			
			Plateau.getInstance().ajouterCase(C);
	
		
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";COMPAGNIE"))
			return true;
		return false;
	}
	

}
