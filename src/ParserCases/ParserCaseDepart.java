package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CaseDepart;

public class ParserCaseDepart extends Parser {

	public ParserCaseDepart(Parser suivant) {
		super(suivant);
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";CASE DEPART")) {
			return true;
		}
		return false;
	}

	@Override
	public void parser(String ligne) {
		String mot[] = ligne.split(separateur);
		int numCase = Integer.parseInt(mot[0]);
		String nom = mot[1];
		int somme = Integer.parseInt(mot[2]);
		CaseDepart D = new CaseDepart(numCase, nom, somme);
		Plateau.getInstance().ajouterCase(D);
		
	}

}
