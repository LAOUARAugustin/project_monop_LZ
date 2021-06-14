package parserCartes;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesAnniversaire;
import projet_monopoly.Carte.CartesChance;
import projet_monopoly.Case.CasesCartes;

public class ParserCartesChances extends Parser{
	
	public ParserCartesChances(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		
			String mot[] = ligne.split(separateur);
			int montant = Integer.parseInt(mot[3]);
			String msg = mot[2];
			
			CartesChance C = new CartesChance(montant,msg);
			
			if(mot[0].equals("CHANCE"))
				Plateau.getInstance().ajouterCarteChance(C);
			else if(mot[0].equals("COMMUNAUTE"))
				Plateau.getInstance().ajouterCarteCommunaute(C);
			else
				throw new IllegalArgumentException("Parser Cartes");
			
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";CHANCE"))
			return true;
		return false;
	}

}

