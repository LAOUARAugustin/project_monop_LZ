package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesCartes;
import projet_monopoly.Case.CasesRepos;

public class ParserCasesRepos extends Parser {
	
	public ParserCasesRepos(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		String mot[] = ligne.split(separateur);
		int numCase = Integer.parseInt(mot[0]);
		String nom = mot[1];
		
		CasesRepos C= new CasesRepos(numCase,nom);
		
		Plateau.getInstance().ajouterCase(C);
		
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";PARKING GRATUIT")|| ligne.contains(";SIMPLE VISITE"))
			return true;
		return false;
	}

}
