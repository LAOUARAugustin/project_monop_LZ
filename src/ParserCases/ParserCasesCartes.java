package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesCartes;
import projet_monopoly.Case.Compagnie;

public class ParserCasesCartes extends Parser {

	public ParserCasesCartes(Parser suivant) {
		super(suivant);
		
	}

	@Override
	public void parser(String ligne) throws Exception {
		
			String mot[] = ligne.split(separateur);
			int numCase = Integer.parseInt(mot[0]);
			String nom = mot[1];
			
			CasesCartes C= new CasesCartes(numCase,nom);
			
			Plateau.getInstance().ajouterCase(C);
		
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";CAISSE COMMUNAUTE")|| ligne.contains(";CHANCE"))
			return true;
		return false;
	}

}
