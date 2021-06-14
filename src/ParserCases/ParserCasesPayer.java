package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesCartes;
import projet_monopoly.Case.CasesPayer;

public class ParserCasesPayer extends Parser {

	public ParserCasesPayer(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		String mot[] = ligne.split(separateur);
		int numCase = Integer.parseInt(mot[0]);
		String nom = mot[1];
		int montant = Integer.parseInt(mot[2]);
		CasesPayer C= new CasesPayer(numCase,nom,montant);
		
		Plateau.getInstance().ajouterCase(C);

	}

	@Override
	public boolean saitParser(String ligne) {
		if( ligne.contains(";TAXE DE LUXE") || ligne.contains(";IMPOT SUR LE REVENU"))
			return true;
		return false;
	}

}
