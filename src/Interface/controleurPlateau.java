package Interface;



import java.io.IOException;
import java.util.ArrayList;

import Exception.alertException;
import Exception.boiteAlerte;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesAnniversaire;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.CasesProprietes;
import projet_monopoly.Case.Compagnie;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.Dette;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class controleurPlateau {
	@FXML Button _Jouer;
	@FXML Button _Proprietes;
	@FXML Button _Hypotheque;
	@FXML Button _AfficherDettes;
	@FXML Button _Quitter;
	@FXML Label _NomJoueur;
	@FXML Label _Solde;
	@FXML GridPane _Grid;
	@FXML Label _SJ1;
	@FXML Label _SJ2;
	@FXML Label _SJ3;
	@FXML Label _SJ4;
	@FXML Label _soldeBanque;
	@FXML ListView _Historique;
	@FXML ImageView _Pion;
	@FXML ImageView _pion1;
	@FXML ImageView _pion2;
	@FXML ImageView _pion3;
	@FXML ImageView _pion4;
	Image imagePionActuel;
	Image imagePion1;
	Image imagePion2;
	Image imagePion3;
	Image imagePion4;
	private static String lastMsg = "";
	public static String Msg = "";
	

	@FXML private void initialize() {
		_soldeBanque.setText("Solde de la banque : " + Integer.toString(Banque.getInstance().getSolde()));
		for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur()) {
				JoueurHumain Joueur = (JoueurHumain)Iterator;
				ImageView image = new ImageView(Joueur.getPion().getImage());
				image.setFitHeight(40);
				image.setFitWidth(40);			  
				_Grid.add(image, Joueur.getPion().getAxeX(), Joueur.getPion().getAxeY());
		}
		imagePion1 = new Image(Plateau.getInstance().getListeJoueur().get(0).getPion().getImage());
		_pion1.setImage(imagePion1);
		imagePion2 = new Image(Plateau.getInstance().getListeJoueur().get(1).getPion().getImage());
		_pion2.setImage(imagePion2);
		if(Plateau.getInstance().getListeJoueur().size()>2) {
			imagePion3 = new Image(Plateau.getInstance().getListeJoueur().get(2).getPion().getImage());
			_pion3.setImage(imagePion3);
		}
		if(Plateau.getInstance().getListeJoueur().size()>3) {
			imagePion4 = new Image(Plateau.getInstance().getListeJoueur().get(3).getPion().getImage());
			_pion4.setImage(imagePion4);
		}
		imagePionActuel = new Image(Plateau.getInstance().getJoueurActuel().getPion().getImage());
		_Pion.setImage(imagePionActuel);
		_NomJoueur.setText(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getNom());
		_Solde.setText(Integer.toString(Plateau.getInstance().getJoueurActuel().getSolde()));
		_SJ1.setText(Plateau.getInstance().getListeJoueur().get(0).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(0).getSolde()));
		_SJ2.setText(Plateau.getInstance().getListeJoueur().get(1).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(1).getSolde()));
		if(Plateau.getInstance().getListeJoueur().size()>2) {
			_SJ3.setText(Plateau.getInstance().getListeJoueur().get(2).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(2).getSolde()));
		}
		if(Plateau.getInstance().getListeJoueur().size()>3) {
			_SJ4.setText(Plateau.getInstance().getListeJoueur().get(3).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(3).getSolde()));
		}
	}
	
	
	public void Jouer(ActionEvent event) throws IOException {
		JoueurHumain J = (JoueurHumain)Plateau.getInstance().getJoueurActuel();
		
		if(!J.isEnPrison()) 
	        JouerTour(J);
		else 
	       JouerTourPrison(J);
		if(Plateau.getInstance().getJoueurActuel().Faillite()) {
			ajouterMessageHistorique(Plateau.getInstance().getJoueurActuel().getNom() + " a fait faillite !");
			if(Plateau.getInstance().getListeJoueur().size() == 1) {
				boiteAlerte.afficherBoite("Fin de la partie",Plateau.getInstance().getListeJoueur().get(0).getNom() + " a gagné !");
				Platform.exit();
			}
		}
		Plateau.getInstance().changerTour();
		ajouterMessageHistorique("C'est au tour de " + Plateau.getInstance().getJoueurActuel().getNom());
		_NomJoueur.setText(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getNom());
		_Solde.setText(Integer.toString(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getSolde()));
		imagePionActuel = new Image(Plateau.getInstance().getJoueurActuel().getPion().getImage());
		_Pion.setImage(imagePionActuel);
		_soldeBanque.setText("Solde de la banque : " + Integer.toString(Banque.getInstance().getSolde()));

	}
	
	
	public void Proprietes(ActionEvent event) throws IOException {
		Dialog<String> dialog = new Dialog<>();
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("vuePropriete.fxml"));
		
		AnchorPane grille = loader.load();
		ButtonType buttonTypeOkAnnuler = new ButtonType("Fermer", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);

		dialog.getDialogPane().setContent(grille);
		dialog.setTitle("propriete");
		dialog.showAndWait();
		Actualise();
		
			
	}
	
	public void Hypotheque(ActionEvent e) throws IOException {
		Dialog<String> dialog = new Dialog<>();
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("vueHypotheque.fxml"));
		
		AnchorPane grille = loader.load();
		ButtonType buttonTypeOkAnnuler = new ButtonType("Fermer", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);

		dialog.getDialogPane().setContent(grille);
		dialog.setTitle("Hypotheques");
		dialog.showAndWait();
		Actualise();
	}
	
	public void afficherDettes(ActionEvent event) {
		
		Dialog<String> dialog = new Dialog<>();
		
		AnchorPane Anchor = new AnchorPane();
		HBox hbox = new HBox();
		VBox V = new VBox();
		ListView<HBox> list=new ListView<HBox>();
		V.getChildren().add(list);
		hbox.getChildren().add(V);
		for(Dette Iterator : Plateau.getInstance().getJoueurActuel().getDettes()) 
		{
			HBox H1 = new HBox();
			Text nom = new Text(Iterator.getBeneficiere().getNom());
			Text montant = new Text(Integer.toString(Iterator.getMontantDette()));
			H1.getChildren().addAll(nom,montant);
			H1.setSpacing(20);
			list.getItems().add(H1);
				
	
		}
		list.getSelectionModel().select(0);
		Button Rembourser = new Button();
		Rembourser.setText("Rembourser");
		TextField montant = new TextField();
		montant.setText("Entrez un montant");
		Rembourser.setOnAction(new EventHandler<ActionEvent>() {
		
		    @Override
		    public void handle(ActionEvent event) {
		    	HBox H = list.getSelectionModel().getSelectedItem();
				Text T = (Text)H.getChildren().get(0);
				String joueurSelect = T.getText();
				if(joueurSelect.equals(Banque.getInstance().getNom())) {
					if(montant.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !montant.getText().trim().isEmpty()) {
						int p = Integer.parseInt(montant.getText().trim());
						if(p>0) {
							try {
								Plateau.getInstance().getJoueurActuel().rembourser(p, Banque.getInstance());
								boiteAlerte.afficherBoite("Opération réussie", "La somme à bien été versé à la banque");
								Actualise();
								for(Dette D : Plateau.getInstance().getJoueurActuel().getDettes()) {
									if(D.getBeneficiere().equals(Banque.getInstance())){
										((Text)H.getChildren().get(1)).setText(Integer.toString(D.getMontantDette()));

									}
								}
								
							} 
							catch (alertException e) {
								boiteAlerte.afficherBoite(e);
							}						
							
						}
						else {
							boiteAlerte.afficherBoite("Remboursement impossible","Le montant doit être superieur à 0");
							return ;
						}	
					
				}
				else {
					boiteAlerte.afficherBoite("Remboursement impossible","Entrez un montant valide");
					return ;
				}
				}
				for(JoueurHumain Iterator : Plateau.getInstance().getListeJoueur())
				{
						
						if(Iterator.getNom().equals(joueurSelect))
						{
									if(montant.getText().trim().matches("[+-]?\\d*(\\.\\d+)?") && !montant.getText().trim().isEmpty()) {
										int p = Integer.parseInt(montant.getText().trim());
										if(p>0) {
											try {
												Plateau.getInstance().getJoueurActuel().rembourser(p, Iterator);
												boiteAlerte.afficherBoite("Opération réussie", "La somme à bien été versé à " + Iterator.getNom());
												Actualise();
												for(Dette D : Plateau.getInstance().getJoueurActuel().getDettes()) {
													if(D.getBeneficiere().equals(Iterator)){
														((Text)H.getChildren().get(1)).setText(Integer.toString(D.getMontantDette()));

													}		
												}
											}
											catch (alertException e) {
												boiteAlerte.afficherBoite(e);
											}						
											
										}
										else {
											boiteAlerte.afficherBoite("Remboursement impossible","Le montant doit être superieur à 0");
											return ;
										}	
									
								}
								else {
									boiteAlerte.afficherBoite("Remboursement impossible","Entrez un montant valide");
									return ;
								}
							
							
						}
					}
				
				

		    }
		  
		});
		hbox.getChildren().addAll(Rembourser,montant);
		Anchor.getChildren().add(hbox);
		ButtonType buttonTypeOkAnnuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().add(buttonTypeOkAnnuler);
		dialog.getDialogPane().setContent(Anchor);
		dialog.setTitle("Dettes");
		dialog.show();		
		
		
		
	}
	
	public void Quitter(ActionEvent event) {
		Platform.exit();
	}
	
	public void ajouterMessageHistorique(String msg) {
		_Historique.getItems().add(msg);
		lastMsg = msg;
		Msg = msg;
	}
	
	public void ajouterMessageHistorique() {
		if(!lastMsg.equals(Msg)) {
			_Historique.getItems().add(Msg);
			lastMsg = Msg;
		}
	}
	public static void passerMessage(String msg) {
		Msg = msg;
	}
	
	public void Actualise() {
		_soldeBanque.setText("Solde de la banque : " + Integer.toString(Banque.getInstance().getSolde()));
		_NomJoueur.setText(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getNom());
		_Solde.setText(Integer.toString(Plateau.getInstance().getJoueurActuel().getSolde()));
		_SJ1.setText(Plateau.getInstance().getListeJoueur().get(0).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(0).getSolde()));
		_SJ2.setText(Plateau.getInstance().getListeJoueur().get(1).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(1).getSolde()));
		if(Plateau.getInstance().getListeJoueur().size()>2) {
			_SJ3.setText(Plateau.getInstance().getListeJoueur().get(2).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(2).getSolde()));
		}
		if(Plateau.getInstance().getListeJoueur().size()>3) {
			_SJ4.setText(Plateau.getInstance().getListeJoueur().get(3).getNom() + " : " + Integer.toString(Plateau.getInstance().getListeJoueur().get(3).getSolde()));
		}

		_Grid.getChildren().clear();
		for(Joueur Iterator : Plateau.getInstance().getListeJoueur()) {
			if(Iterator instanceof JoueurHumain) {
				JoueurHumain Joueur = (JoueurHumain)Iterator;
				ImageView image = new ImageView(Joueur.getPion().getImage());
				image.setFitHeight(40);
				image.setFitWidth(40);			  
				_Grid.add(image, Joueur.getPion().getAxeX(), Joueur.getPion().getAxeY());
				
			}
		}
	}
	
	//TOUR
	
	
	public void JouerTour(JoueurHumain J) throws IOException {
		int Dé1 = J.LancerDé();
        int Dé2 = J.LancerDé();
        boiteAlerte.afficherBoite("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
        int somme = Dé1 + Dé2;
        ajouterMessageHistorique(Plateau.getInstance().getJoueurActuel().getNom() + " a fait " + Dé1 + " et " + Dé2);
        J.seDeplacer(somme);
        J.getPion().deplacerPion(J.getPosition());
		Actualise();
		if(J.getPosition() instanceof Compagnie) {
			((Compagnie)J.getPosition()).arretSurLaCase(J,somme);
	        ajouterMessageHistorique();
	        J.getPion().deplacerPion(J.getPosition());
	        Actualise();
		}
		else {
				try {
					J.getPosition().arretSurLaCase(J);
					J.getPion().deplacerPion(J.getPosition());
			        ajouterMessageHistorique();
			        Actualise();		
				} catch (alertException e) {
					boiteAlerte.afficherBoite(e);
		    }
		}
        if(Dé1 == Dé2) { // double
      	  	  ajouterMessageHistorique("DOUBLE !!");
        	  Dé1 = J.LancerDé();
              Dé2 = J.LancerDé();
              boiteAlerte.afficherBoite("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
              somme =Dé1+Dé2;
              ajouterMessageHistorique(Plateau.getInstance().getJoueurActuel().getNom() + " a fait " + Dé1 + " et " + Dé2);
              if(Dé1 == Dé2) { // 2 doubles
            	  	ajouterMessageHistorique("DOUBLE !!");
	                boiteAlerte.afficherBoite("Pas de chance !","Vous avez fait 3 doubles, vous allez donc en prison");
	      	        J.AllerEnPrison();
	      	        J.getPion().deplacerPion(J.getPosition());
	      	        Actualise();
	      	        ajouterMessageHistorique(" AHAHAH " + Plateau.getInstance().getJoueurActuel().getNom() + " entre en prison !");
	          }
              else {
            	  J.seDeplacer(somme);
	              J.getPion().deplacerPion(J.getPosition());
	              Actualise();
	              if(J.getPosition() instanceof Compagnie) {
	  				((Compagnie)J.getPosition()).arretSurLaCase(J,somme);
	  		        ajouterMessageHistorique();
	  		        J.getPion().deplacerPion(J.getPosition());
	  		        Actualise();
		  			}
	              else {
		  				try {
							J.getPosition().arretSurLaCase(J);
							J.getPion().deplacerPion(J.getPosition());
					        ajouterMessageHistorique();
					        Actualise();		
						} catch (alertException e) {
							boiteAlerte.afficherBoite(e);
						}		
		  		    } 
	             }
             
        }
        
		
	}

	public void JouerTourPrison(JoueurHumain J) {
			ajouterMessageHistorique(J.getNom() + " est en prison. (" + J.getTempsEnPrison() + " tour(s) restant)");
		 	int Dé1 = J.LancerDé();
	        int Dé2 = J.LancerDé();
	        boiteAlerte.afficherBoite("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
	        if(Dé1 == Dé2) {
	        	boiteAlerte.afficherBoite("Coup de chance !","Les dés ont fait un double, vous sortez donc de prison.");
	        	J.SortirDePrison();
	        	ajouterMessageHistorique(Plateau.getInstance().getJoueurActuel().getNom() + " est libérable.");
	        	J.seDeplacer(Dé1+Dé2);
	        	J.getPion().deplacerPion(J.getPosition());
	    		Actualise();
	    		return ;
	        }
	       else {
	        		
		        		if(J.getNbCarteLiberation()>0) {
		        		Alert alert = new Alert(AlertType.CONFIRMATION,"Voulez-vous utiliser une carte liberation ? ", ButtonType.YES, ButtonType.NO);
		    			alert.showAndWait();
	
			    			if (alert.getResult() == ButtonType.YES) {
			    				J.SortirDePrison();
			    				J.setNbCarteLiberation(J.getNbCarteLiberation()-1);
			    				ajouterMessageHistorique(J.getNom()+"a utilisé une carte liberation pour sortir de prison");
			    				return ;
			    			}
			    			
		        		}
		        		if(J.getSolde()>49) {
		    				Alert alert2 = new Alert(AlertType.CONFIRMATION,"Voulez-vous payer 50 euros pour sortir de prison ? ", ButtonType.YES, ButtonType.NO);
			    			alert2.showAndWait();
	
				    			if (alert2.getResult() == ButtonType.YES) {
				    				J.SortirDePrison();
				    				J.payerJoueur(50, Banque.getInstance());
				    				ajouterMessageHistorique(J.getNom()+"a payé 50 euros pour sortir de prison.");
				    				return ;
				    			}
		        }
			}
	        J.setTempsEnPrison(J.getTempsEnPrison()-1);
	        if(J.getTempsEnPrison() == 0) {
	        	J.SortirDePrison();
	        }
	}
	
	
}
