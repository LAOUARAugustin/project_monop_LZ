package parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Exception.ParserManquantException;


public class Fichier {
	

	/**
	 * Cette m�thode ouvre le fichier (en faisant tous les contr�les n�cessaires). Puis, elle boucle sur
	 * chaque ligne et confie au parser le traitement de la ligne. S'il n'y a pas de parser,
	 * la ligne est tout simplement affich�e dans la console
	 * @param nomFichier nom du fichier � lire et � parser
	 * @param parser c'est le premier parser de la liste
	 */
	public static void lire(String nomFichier, Parser parser) {
		if (nomFichier == null)
			throw new IllegalArgumentException("yoh ! man ! tu crois m'avoir avec un null ? M�me pas en r�ve alors d�gage !!!");
		
		File fichier = new File(nomFichier);
		
		if (! fichier.isFile())
			throw new IllegalArgumentException("Eh ! c'koi'�'fichier ??? il'exist'pas !!!");
		
		BufferedReader reader = null;
		String ligne;

		try {
			reader = new BufferedReader(new FileReader(fichier));
				
			while ((ligne = reader.readLine()) != null) {
				// On a bien lu une ligne ue fichier, maintenant qu'est-ce qu'on en fait ??
				if (parser==null)
					// Si y a pas de parser, alors on ne sais vraiment pas quoi faire avec et on l'affiche...
					System.out.println("Ligne : "+ligne);
				else
					// Puisqu'on a un parser, on l'utilise. C'est lui qui traitera la ligne
					// pour cr�er des objets, afficher des trucs, ou je ne sais quoi !
					try {
						parser.traiter(ligne);
					}
					catch (ParserManquantException e) {
						System.err.println("Aucun parser n'existe pour la ligne "+ligne);
					}
					catch (Exception e) {
						// Je ne me foule pas dans le traitement de l'exception
						e.printStackTrace();
					}
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
