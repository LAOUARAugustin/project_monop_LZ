package parserCartes;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesAnniversaire;
import projet_monopoly.Carte.CartesReparation;

public class ParserCartesReparation extends Parser{
	
	public ParserCartesReparation(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
		
			String mot[] = ligne.split(separateur);
			String msg = mot[2];
			int montantM = Integer.parseInt(mot[3]);
			int montantH = Integer.parseInt(mot[4]);
			
			CartesReparation C = new CartesReparation(montantM,montantH,msg);
			
			if(mot[0].equals("CHANCE"))
				Plateau.getInstance().ajouterCarteChance(C);
			else if(mot[0].equals("COMMUNAUTE"))
				Plateau.getInstance().ajouterCarteCommunaute(C);
			else
				throw new IllegalArgumentException("Parser Cartes");
			
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";REPARATIONS") || ligne.contains(";IMPOTS"))
			return true;
		return false;
	}

}
