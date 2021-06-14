package parserCartes;
import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesArgent;

public class ParserCartesPayer extends Parser {


	
		
		public ParserCartesPayer(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}


		@Override
		public void parser(String ligne) throws Exception {
			
				String mot[] = ligne.split(separateur);
				int montant = Integer.parseInt(mot[3]);
				String msg = mot[2];
				
				CartesArgent C = new CartesArgent(-montant,msg);
				
				if(mot[0].equals("CHANCE"))
					Plateau.getInstance().ajouterCarteChance(C);
				else if(mot[0].equals("COMMUNAUTE"))
					Plateau.getInstance().ajouterCarteCommunaute(C);
				else
					throw new IllegalArgumentException("Parser Cartes");
				
		}

		@Override
		public boolean saitParser(String ligne) {
			if(ligne.contains(";PAYER"))
				return true;
			return false;
		}

	

}
