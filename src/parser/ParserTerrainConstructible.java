package parser;

import projet_monopoly.Plateau;
import projet_monopoly.Case.Terrain;

public class ParserTerrainConstructible extends ParserCases {

	public ParserTerrainConstructible(ParserCases suivant) {
		super(suivant);
	}

	@Override
	public boolean saitParser(String ligne) {
		if(ligne.contains(";TERRAIN CONSTRUCTIBLE")) {
			return true;
		}
		return false;
	}

	@Override
	
	public void parser(String ligne) {
		String mot[] = ligne.split(separateur);
		int numCase = Integer.parseInt(mot[0]);
		String nom = mot[3];
		String Couleur = mot[2];
		int prixAchatTerrain=Integer.parseInt(mot[4]);
		int prixAchatMaison=Integer.parseInt(mot[5]);
		int LoyerSansMaison= Integer.parseInt(mot[6]);
		int Loyer1Maison=Integer.parseInt(mot[7]);
		int Loyer2Maison= Integer.parseInt(mot[8]);
		int Loyer3Maison=Integer.parseInt(mot[9]);
		int Loyer4Maison= Integer.parseInt(mot[10]);
		
		int LoyerHotel=Integer.parseInt(mot[11]);
		Terrain T = new Terrain(numCase,nom,prixAchatTerrain,LoyerSansMaison,Loyer1Maison,
				Loyer2Maison,Loyer3Maison,Loyer4Maison,LoyerHotel,Couleur,prixAchatMaison);
		Plateau.getInstance().ajouterCase(T);
		
	}

}
