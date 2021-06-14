package parserCartes;
import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesLiberation;

public class ParserCartesLiberation extends Parser {


	
		
		public ParserCartesLiberation(Parser suivant) {
			super(suivant);
		}


		@Override
		public void parser(String ligne) throws Exception {
			
				String mot[] = ligne.split(separateur);
				String msg = mot[2];
				CartesLiberation C = new CartesLiberation(msg);
				
				if(mot[0].equals("CHANCE"))
					Plateau.getInstance().ajouterCarteChance(C);
				else if(mot[0].equals("COMMUNAUTE"))
					Plateau.getInstance().ajouterCarteCommunaute(C);
				else
					throw new IllegalArgumentException("Parser Cartes");
				
		}

		@Override
		public boolean saitParser(String ligne) {
			if(ligne.contains(";LIBERATION"))
				return true;
			return false;
		}

	

}
