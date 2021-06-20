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
			AnchorPane root = FXMLLoader.load(getClass().getResource("vueIntro.fxml"));
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
		launch(args);
	}
	
}
