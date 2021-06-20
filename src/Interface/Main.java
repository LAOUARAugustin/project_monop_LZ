package Interface;
import ParserCases.ParserCaseDepart;
import ParserCases.ParserCasesCartes;
import ParserCases.ParserCasesPayer;
import ParserCases.ParserCasesRepos;
import ParserCases.ParserCompagnie;
import ParserCases.ParserGare;
import ParserCases.ParserPrison;
import ParserCases.ParserTerrainConstructible;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import parser.Fichier;
import parserCartes.ParserCartesAnniversaire;
import parserCartes.ParserCartesChances;
import parserCartes.ParserCartesDeplacement;
import parserCartes.ParserCartesEncaisser;
import parserCartes.ParserCartesLiberation;
import parserCartes.ParserCartesPayer;
import parserCartes.ParserCartesReparation;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.Cartes;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesCartes;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class Main extends Application {
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("vuePlateau.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Monopoly");
		    primaryStage.setResizable(false);
			primaryStage.show();
			
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		String fichierTerrains ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\Terrains.csv";	
		String fichierChance ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\CartesChance.csv";
		String fichierCommunaute ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\CartesCommunaute.csv";
		Pion pion1 = new Pion("/interface/pion1.png");
		Pion pion2 = new Pion("/interface/pion2.png");
		Pion pion3 = new Pion("/interface/pion3.png");
		Pion pion4 = new Pion("/interface/pion4.png");
		Plateau.getInstance().initFichier(fichierChance,fichierCommunaute, fichierTerrains);
		JoueurHumain J1 = new JoueurHumain("Augustin", Plateau.getInstance().getListeCases().get(0), pion1);
		JoueurHumain J2 = new JoueurHumain("Aurélien", Plateau.getInstance().getListeCases().get(0), pion2);
		//JoueurHumain J3 = new JoueurHumain("Valérie", Plateau.getInstance().getListeCases().get(0), pion3);
		//JoueurHumain J4 = new JoueurHumain("Madjid", Plateau.getInstance().getListeCases().get(0), pion4);
		Plateau.getInstance().initJoueur(J1, J2);
		launch(args);
	}
	
}
