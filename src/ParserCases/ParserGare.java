package ParserCases;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CaseDepart;
import projet_monopoly.Case.Gare;

public class ParserGare extends Parser{

	public ParserGare(Parser suivant) {
		super(suivant);
		
	}

	@Override
	public void parser(String ligne) throws Exception {
		
		
			String mot[] = ligne.split(separateur);
			int numCase = Integer.parseInt(mot[0]);
			String nom = mot[2];
			Gare G = new Gare(numCase,nom,200,25,50,100,200);
			

			Plateau.getInstance().ajouterCase(G);
	
				
		
		
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";GARE"))
			return true;
		
		return false;
	}
	

}
