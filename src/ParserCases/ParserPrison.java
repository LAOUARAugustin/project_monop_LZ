package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesPrison;
import projet_monopoly.Case.Gare;

public class ParserPrison extends Parser{

	public ParserPrison(Parser suivant) {
		super(suivant);
	}

	@Override
	public void parser(String ligne) throws Exception {
		
		
			String mot[] = ligne.split(separateur);
			int numCase = Integer.parseInt(mot[0]);
			CasesPrison p = new CasesPrison(numCase,"PRISON");
			Plateau.getInstance().ajouterCase(p);
			
		
		
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";ALLEZ EN PRISON"))
			return true;
		return false;
	}

}
