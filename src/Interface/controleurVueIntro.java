package Interface;

import java.io.IOException;

import javax.swing.JSpinner.ListEditor;

import Exception.alertException;
import Exception.boiteAlerte;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class controleurVueIntro {
	@FXML private Button _test;
	@FXML private Button _ajouterJoueur;
	@FXML private Button _SupprimerJoueur;
	@FXML private Button _jouer;
	@FXML private TextField _soldeJ;
	@FXML private TextField _soldeB;
	@FXML private VBox _Vbox;
	public ListView<Text> list = new ListView<Text>();
	
	@FXML private void initialize() {
		_Vbox.getChildren().add(list);	
		_soldeJ.setText("1500");
		_soldeB.setText("100000");
	}
	
	public void Test(ActionEvent e) throws IOException {
		
		
	}
	
	public void ajouterJoueur(ActionEvent e) {
		Dialog<String> dialog = new Dialog<>();
		
		AnchorPane Anchor = new AnchorPane();
		VBox V = new VBox();
		HBox H = new HBox();
		Text T = new Text("Nom :");
		TextField nom = new TextField();
		H.getChildren().addAll(T,nom);
		H.setSpacing(10);
		Button add = new Button("Ajouter");
		add.setOnAction(new EventHandler<ActionEvent>() {
			
		    @Override
		    public void handle(ActionEvent event) {
		    	if(Plateau.getInstance().getListeJoueur().size()>3) {
		    		boiteAlerte.afficherBoite("L'ajout à échouer","Impossible d'ajouter plus de 4 joueurs");
		    		dialog.close();
		    	}
		    	else {
		    		for(JoueurHumain J : Plateau.getInstance().getListeJoueur()) {
		    			if(J.getNom().equals(nom.getText())) {
		    				boiteAlerte.afficherBoite("L'ajout à échouer","Un joueur a déjà ce nom.");
		    				return;
		    			}
		    		}
		    		if(nom.getText().trim().isEmpty()) {
			    		boiteAlerte.afficherBoite("L'ajout à échouer","Entrez un nom valide");
			    	}
			    	else {
			    		JoueurHumain J = new JoueurHumain(nom.getText(), null, null);
				    	Plateau.getInstance().ajouterJoueur(J);
				    	Text T = new Text(J.getNom());
				    	list.getItems().add(T);
				    	dialog.close();
			    	}

		    	}
		     }
		});
		V.getChildren().addAll(H,add);
		V.setSpacing(20);
		Anchor.getChildren().add(V);
		ButtonType buttonTypeOkAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);
		dialog.getDialogPane().setContent(Anchor);
		dialog.setTitle("Ajout d'un joueur");
		dialog.showAndWait();
	}
	
	public void SupprimerJoueur(ActionEvent e ) {
		if(list.getItems().size() == 0) {
			return;
		}
		Text T = list.getSelectionModel().getSelectedItem();
		String nom = T.getText();
		for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur()) {
			if(Iterator.getNom().equals(nom)) {
				try {
					Plateau.getInstance().retirerJoueur(Iterator);
				} catch (alertException e1) {
					boiteAlerte.afficherBoite(e1);
				}
				list.getItems().remove(T);
			}
		}
	}
	public void Jouer(ActionEvent e) throws IOException {
		if(Plateau.getInstance().getListeJoueur().size()<2) {
			boiteAlerte.afficherBoite("Lancement impossible","Vous devez avoir au moins 2 joueurs pour pouvoir jouer");
			return;
		}
		int soldeJ,soldeB;
		if(_soldeJ.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !_soldeJ.getText().trim().isEmpty()) {
			soldeJ = Integer.parseInt(_soldeJ.getText().trim());
			if(soldeJ<200) {
				boiteAlerte.afficherBoite("Lancement impossible","Le solde minimum pour les joueurs est 200 euros");
				return;
			}
		}
		else {
			boiteAlerte.afficherBoite("Lancement impossible","Entrez une solde de base valide pour les joueurs");
			return;
		}
		if(_soldeB.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !_soldeB.getText().trim().isEmpty()) {
			soldeB = Integer.parseInt(_soldeB.getText().trim());
			if(soldeB<1000) {
				boiteAlerte.afficherBoite("Lancement impossible","Le solde minimum pour la banque est 1000 euros");
				return;
			}
		}
		else {
			boiteAlerte.afficherBoite("Lancement impossible","Entrez une solde de base valide pour la banque");
			return;
		}
		String fichierTerrains ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\Terrains.csv";	
		String fichierChance ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\CartesChance.csv";
		String fichierCommunaute ="C:\\Users\\Augustin\\Desktop\\COURS_S4\\BPOO\\project_monop_LZ\\src\\CartesCommunaute.csv";
		Plateau.getInstance().initFichier(fichierChance,fichierCommunaute, fichierTerrains);
		Plateau.getInstance().initJoueur(soldeJ, soldeB);
		Parent AnchorPaneParent = FXMLLoader.load(getClass().getResource("vuePlateau.fxml"));
		Scene SceneJeu = new Scene(AnchorPaneParent);
		Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
		window.setScene(SceneJeu);
		window.setTitle("Monopoly");
		window.setResizable(false);
		window.show();
	}
}
