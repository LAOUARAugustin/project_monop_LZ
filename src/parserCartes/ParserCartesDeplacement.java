package parserCartes;

import parser.Parser;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesAnniversaire;
import projet_monopoly.Carte.CartesDeplacement;


public class ParserCartesDeplacement extends Parser{
	
	public ParserCartesDeplacement(Parser suivant) {
		super(suivant);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void parser(String ligne) throws Exception {
			
			String mot[] = ligne.split(separateur);
			String msg = mot[2];
			boolean passerCaseDepart;
			int numero = 0;
			String nom =""; 
			if(mot[3].equals("OUI")) {
				passerCaseDepart = true;
			}
			else {
				passerCaseDepart = false;
			}
			if(mot[4].equals("NOM")){
					nom = mot[5];
			}
			else if(mot[4].equals("NUMERO")) {
				numero = Integer.parseInt(mot[5]);
			}
			CartesDeplacement C = new CartesDeplacement(nom, passerCaseDepart, numero,msg);
			
			if(mot[0].equals("CHANCE"))
				Plateau.getInstance().ajouterCarteChance(C);
			else if(mot[0].equals("COMMUNAUTE"))
				Plateau.getInstance().ajouterCarteCommunaute(C);
			else
				throw new IllegalArgumentException("Parser Cartes");
			
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";DEPLACEMENT"))
			return true;
		return false;
	}

}
