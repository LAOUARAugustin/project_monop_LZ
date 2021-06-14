package Interface;



import java.io.IOException;
import java.util.ArrayList;

import Exception.alertException;
import Exception.boiteAlerte;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import projet_monopoly.Plateau;
import projet_monopoly.Carte.CartesAnniversaire;
import projet_monopoly.Case.Cases;
import projet_monopoly.Case.Compagnie;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.Joueur;
import projet_monopoly.joueur.JoueurHumain;

public class controleurPlateau {
	@FXML Button _test;
	@FXML Button _Jouer;
	@FXML Button _Proprietes;
	@FXML Button _Quitter;
	@FXML Label _NomJoueur;
	@FXML Label _Solde;
	@FXML GridPane _Grid;
	@FXML Label _SJ1;
	@FXML Label _SJ2;
	@FXML Label _SJ3;
	@FXML Label _SJ4;
	@FXML ListView _Historique;
	private static String lastMsg;
	private static String Msg;
	

	@FXML private void initialize() {
		for(Joueur Iterator : Plateau.getInstance().getListeJoueur()) {
			if(Iterator instanceof JoueurHumain) {
				JoueurHumain Joueur = (JoueurHumain)Iterator;
				ImageView image = new ImageView(Joueur.getPion().getImage());
				image.setFitHeight(40);
				image.setFitWidth(40);			  
				_Grid.add(image, Joueur.getPion().getAxeX(), Joueur.getPion().getAxeY());
			}
		}
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
	
	public void Test(ActionEvent event) throws IOException {
		try {
			Plateau.getInstance().getJoueurActuel().TirerCarteChance();
			System.out.println(Plateau.getInstance().getListeCartesChance().size());
		} catch (alertException e) {
			boiteAlerte A = new boiteAlerte("",e.getMsg());
			A.show();
		}
		System.out.println(Plateau.getInstance().getJoueurActuel().getPosition().getNumeroCase());
		Plateau.getInstance().getJoueurActuel().getPion().deplacerPion(Plateau.getInstance().getJoueurActuel().getPosition());
		Actualise();
	}
	public void Jouer(ActionEvent event) throws IOException {
		JoueurHumain J = (JoueurHumain)Plateau.getInstance().getJoueurActuel();
		
		if(!J.isEnPrison()) 
	        JouerTour(J);
		else 
	       JouerTourPrison(J);

		Plateau.getInstance().changerTour();
		ajouterMessageHistorique("C'est au tour de " + Plateau.getInstance().getJoueurActuel().getNom());
		_NomJoueur.setText(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getNom());
		_Solde.setText(Integer.toString(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getSolde()));
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
		_Solde.setText(Integer.toString(Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur()).getSolde()));
		
		
			
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
        boiteAlerte A = new boiteAlerte("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
        A.show();
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
					boiteAlerte X = new boiteAlerte("Action impossible", e.getMsg());
					X.show();
					
		    }
		}
        if(Dé1 == Dé2) { // double
      	  	  ajouterMessageHistorique("DOUBLE !!");
        	  Dé1 = J.LancerDé();
              Dé2 = J.LancerDé();
              boiteAlerte B = new boiteAlerte("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
  	          B.show();
              somme =Dé1+Dé2;
              ajouterMessageHistorique(Plateau.getInstance().getJoueurActuel().getNom() + " a fait " + Dé1 + " et " + Dé2);
              if(Dé1 == Dé2) { // 2 doubles
            	  	ajouterMessageHistorique("DOUBLE !!");
	                boiteAlerte C = new boiteAlerte("Pas de chance !","Vous avez fait 3 doubles, vous allez donc en prison");
	      	        C.show();
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
							boiteAlerte Y = new boiteAlerte("Action impossible", e.getMsg());
							Y.show();
						}		
		  		    } 
	             }
             
        }
        
		
	}

	public void JouerTourPrison(JoueurHumain J) {
		 	int Dé1 = J.LancerDé();
	        int Dé2 = J.LancerDé();
	        boiteAlerte A = new boiteAlerte("Vous avez lancé les dés","Les dés ont fait " +Dé1 +" et " + Dé2 + ".");
	        A.show();
	        if(Dé1 == Dé2) {
	        	boiteAlerte B = new boiteAlerte("Coup de chance !","Les dés ont fait un double, vous sortez donc de prison.");
		        B.show();
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
	}
	
	
}
