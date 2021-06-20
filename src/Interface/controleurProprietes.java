package Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Exception.alertException;
import Exception.boiteAlerte;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import projet_monopoly.Plateau;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class controleurProprietes{
	@FXML private Label _entete;
	@FXML private Label _NbCartes;
	@FXML private Label _fortune;
	@FXML private VBox _Vbox;
	@FXML private Button _Vendre;
	@FXML private Button _AddMaison;
	@FXML private Button _AddHotel;
	@FXML private Button _Echanger;
	@FXML private Button _vendreLibe;
	@FXML private Button _hypotheque;
	public ListView<HBox> list = new ListView<HBox>();
	
	
	
	
	
	public void initialize() {
		_fortune.setText("Votre capital total :" + Plateau.getInstance().getJoueurActuel().fortune() + " euros");
		_entete.setText("Proprietés de " + Plateau.getInstance().getJoueurActuel().getNom());
		_NbCartes.setText(Integer.toString(Plateau.getInstance().getJoueurActuel().getNbCarteLiberation()));
		_Vbox.getChildren().add(list);		
		for(CasesProprietes Iterator : Plateau.getInstance().getJoueurActuel().getProprietes()) {
			HBox H = new HBox();
			Text nom = new Text(Iterator.getNomCase());
			H.getChildren().add(nom);
			
			if(Iterator instanceof Terrain) {
				Terrain T = (Terrain)Iterator;
				Text couleur = new Text(T.getGroupe());
				Text nbMaison = new Text();
				if(T.getNbMaison()<2) {
					nbMaison.setText(Integer.toString(T.getNbMaison()) + " maison");
				}
				else {
					nbMaison.setText(Integer.toString(T.getNbMaison()) + " maisons");
				}
				Text Hotel = new Text();
				if(T.isHotel()) {
					Hotel.setText("1 hotel"); 
				}
				else {
					Hotel.setText("Pas d'hotel");
				}
				H.getChildren().addAll(couleur,nbMaison,Hotel);
			}
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
				for(Joueur Iterator : Plateau.getInstance().getListeJoueur())
				{
						
						if(Iterator.getNom().equals(joueurSelect))
						{
							for(CasesProprietes caseprop: Plateau.getInstance().getJoueurActuel().getProprietes())
							{
								if(caseprop.getNomCase().equals(nom))
								{
									if(Prix.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !Prix.getText().trim().isEmpty()) {
										int p = Integer.parseInt(Prix.getText().trim());
										if(p>0) {
											try {
												Plateau.getInstance().getJoueurActuel().vendre(caseprop, Iterator,p);
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
	
	
	public void AddMaison(ActionEvent event) {
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
			Text T = (Text)H.getChildren().get(0);
			String nom = T.getText();
			for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
			{
				if(iterator.getNomCase().equals(nom))
				{
					try {
						iterator.ajouterMaison();
					} catch (alertException e) {
						boiteAlerte.afficherBoite(e);
					}
					
				}
			}
			refresh();
	}
	public void AddHotel(ActionEvent event)  {
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
		{
			if(iterator.getNomCase().equals(nom))
			{
				try {
					iterator.ajouterHotel();
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
		}
		refresh();
	}
	
	public void RemoveHotel(ActionEvent event)
	{
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
		{
			if(iterator.getNomCase().equals(nom))
			{
				try {
					iterator.retirerHotel();
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
		}
		refresh();
	}
	public void RemoveMaison(ActionEvent event)
	{
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
		{
			if(iterator.getNomCase().equals(nom))
			{
				try {
					iterator.retirerMaison();
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
		}
		refresh();
	}	
	public void Echanger(ActionEvent event) {
		if(list.getItems().size() == 0) {
			return;
		}
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
		{
			if(iterator.getNomCase().equals(nom))
			{
				try {
					iterator.echangerHotel();
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
			}
		}
		refresh();
	}
	public void hypotheque()
	{
		HBox H = list.getSelectionModel().getSelectedItem();
		Text T = (Text)H.getChildren().get(0);
		String nom = T.getText();
		for(CasesProprietes iterator: Plateau.getInstance().getJoueurActuel().getProprietes())
		{
			if(iterator.getNomCase().equals(nom))
			{
				try {
				Plateau.getInstance().getJoueurActuel().mettreHypotheque(iterator);
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
				}
				
			}
		}
		refresh();
	}
	
	public void vendreLibe() {
	
		Dialog<String> dialog = new Dialog<>();
		
		AnchorPane Anchor = new AnchorPane();
		HBox hbox = new HBox();
		VBox V = new VBox();
		ListView<HBox> list=new ListView<HBox>();
		V.getChildren().add(list);
		hbox.getChildren().add(V);
		for(Joueur Iterator : Plateau.getInstance().getListeJoueur()) 
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
				for(Joueur Iterator : Plateau.getInstance().getListeJoueur())
				{
					if(Iterator instanceof JoueurHumain)
					{
						JoueurHumain joueur= (JoueurHumain)Iterator;
						
						if(joueur.getNom().equals(joueurSelect))
						{
							if(Prix.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !Prix.getText().trim().isEmpty()) {
								int p = Integer.parseInt(Prix.getText().trim());
								if(p>0) {
									try {
										Plateau.getInstance().getJoueurActuel().vendreCarteLiberation(joueur, p);
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
		});
		hbox.getChildren().addAll(Vendre,Prix);
		Anchor.getChildren().add(hbox);
		ButtonType buttonTypeOkAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);
		dialog.getDialogPane().setContent(Anchor);
		dialog.setTitle("Vente");
		dialog.show();
	}
	
	
	
	public void refresh() {
		_fortune.setText("Votre capital total :" + Plateau.getInstance().getJoueurActuel().fortune() + " euros");
		_NbCartes.setText(Integer.toString(Plateau.getInstance().getJoueurActuel().getNbCarteLiberation()));
		list.getItems().clear();
		for(CasesProprietes Iterator : Plateau.getInstance().getJoueurActuel().getProprietes()) {
			HBox H = new HBox();
			Text nom = new Text(Iterator.getNomCase());
			H.getChildren().add(nom);
			
			if(Iterator instanceof Terrain) {
				Terrain T = (Terrain)Iterator;
				Text couleur = new Text(T.getGroupe());
				Text nbMaison = new Text();
				if(T.getNbMaison()<2) {
					nbMaison.setText(Integer.toString(T.getNbMaison()) + " maison");
				}
				else {
					nbMaison.setText(Integer.toString(T.getNbMaison()) + " maisons");
				}
				Text Hotel = new Text();
				if(T.isHotel()) {
					Hotel.setText("1 hotel"); 
				}
				else {
					Hotel.setText("Pas d'hotel");
				}
				H.getChildren().addAll(couleur,nbMaison,Hotel);
			}
			H.setSpacing(20);
			list.getItems().add(H);
			
		}
		list.getSelectionModel().select(0);
	}
	
	
	
	
	

}
