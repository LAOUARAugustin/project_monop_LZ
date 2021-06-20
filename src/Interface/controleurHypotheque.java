package Interface;

import java.io.IOException;

import Exception.alertException;
import Exception.boiteAlerte;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class controleurHypotheque {
	
	@FXML private VBox _Vbox;
	@FXML private Button _Vendre;
	@FXML private Button _LeverHypotheque;
	@FXML private Label _entete;
	public ListView<HBox> list = new ListView<HBox>();
	
	public void initialize() {
		_entete.setText("Hypothèques de " + Plateau.getInstance().getJoueurActuel());
		_Vbox.getChildren().add(list);		
		for(CasesProprietes Iterator : Plateau.getInstance().getJoueurActuel().getHypotheque()) {
			HBox H = new HBox();
			Text nom = new Text(Iterator.getNomCase());
			H.getChildren().add(nom);
			
			if(Iterator instanceof Terrain) {
				Terrain T = (Terrain)Iterator;
				Text couleur = new Text(T.getGroupe());
				H.getChildren().addAll(couleur);
			}
			Text prix = new Text(Integer.toString(Iterator.getPrixBase()));
			H.getChildren().add(prix);
			H.setSpacing(20);
			list.getItems().add(H);			
		}
	    list.getSelectionModel().select(0);
	}
	
	public void Vendre(ActionEvent event) throws IOException {
			
		if(list.getItems().size() == 0) {
			return;
		}
			
			HBox H = list.getSelectionModel().getSelectedItem();
			Text T = (Text)H.getChildren().get(0);
			String nom = T.getText();
			
			
			Dialog<String> dialog = new Dialog<>();
			
			AnchorPane Anchor = new AnchorPane();
			HBox hbox = new HBox();
			VBox V = new VBox();
			ListView<HBox> list=new ListView<HBox>();
			V.getChildren().add(list);
			hbox.getChildren().add(V);
			for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur()) 
			{
					if(!Iterator.equals(Plateau.getInstance().getJoueurActuel()))
					{
						HBox H1 = new HBox();
						Text nom1 = new Text(Iterator.getNom());
						H1.getChildren().add(nom1);
						H1.setSpacing(20);
						list.getItems().add(H1);
					}
		
			}
			list.getSelectionModel().select(0);
			Button Vendre = new Button();
			Vendre.setText("Vendre");
			TextField Prix = new TextField();
			Prix.setText("Entrez votre prix");
			Vendre.setOnAction(new EventHandler<ActionEvent>() {
			
			    @Override
			    public void handle(ActionEvent event) {
			    	HBox H = list.getSelectionModel().getSelectedItem();
					Text T = (Text)H.getChildren().get(0);
					String joueurSelect = T.getText();
					for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur())
					{
							
							if(Iterator.getNom().equals(joueurSelect))
							{
								for(CasesProprietes caseprop: Plateau.getInstance().getJoueurActuel().getHypotheque())
								{
									if(caseprop.getNomCase().equals(nom))
									{
										if(Prix.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !Prix.getText().trim().isEmpty()) {
											int p = Integer.parseInt(Prix.getText().trim());
											if(p>0) {
												try {
													Plateau.getInstance().getJoueurActuel().vendreHypotheque(caseprop, p, Iterator);
													dialog.close();
													refresh();
												} catch (alertException e) {
													boiteAlerte.afficherBoite(e);
												}						
												
											}
											else {
												boiteAlerte.afficherBoite("Vente impossible","Le prix doit être superieur à 0");
												return ;
											}	
										
									}
									else {
										boiteAlerte.afficherBoite("Vente impossible","Entrez un prix valide");
										return ;
									}
								}
								
								
							}
						}
					
					
	
			    }
			  }
			});
			hbox.getChildren().addAll(Vendre,Prix);
			Anchor.getChildren().add(hbox);
			ButtonType buttonTypeOkAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
			dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);
			dialog.getDialogPane().setContent(Anchor);
			dialog.setTitle("Vente");
			dialog.show();		
			
			
			
			
		}
	
	public void leverHypotheque(ActionEvent event) {
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes Iterator : Plateau.getInstance().getJoueurActuel().getHypotheque())
			if (Iterator.getNomCase().equals(nom)) {
				try {
					Plateau.getInstance().getJoueurActuel().leverHypotheque(Iterator);
					refresh();
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
	}
	
	public void refresh() {
		list.getItems().clear();
		for(CasesProprietes Iterator : Plateau.getInstance().getJoueurActuel().getHypotheque()) {
			HBox H = new HBox();
			Text nom = new Text(Iterator.getNomCase());
			H.getChildren().add(nom);
			
			if(Iterator instanceof Terrain) {
				Terrain T = (Terrain)Iterator;
				Text couleur = new Text(T.getGroupe());
				H.getChildren().addAll(couleur);
			}
			Text prix = new Text(Integer.toString(Iterator.getPrixBase()));
			H.getChildren().add(prix);
			H.setSpacing(20);
			list.getItems().add(H);
			
		}
		list.getSelectionModel().select(0);
	}
	}
