package Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import projet_monopoly.Plateau;
import projet_monopoly.Case.Terrain;
import projet_monopoly.joueur.Banque;
import projet_monopoly.joueur.JoueurHumain;

public class controleurJouer {
	@FXML Label _Msg1;
	@FXML Label _Msg2;
	
	@FXML private void initialize() {
		JoueurHumain J = (JoueurHumain) Plateau.getInstance().getListeJoueur().get(Plateau.getInstance().getTourDuJoueur());
		_Msg1.setText("Vous êtes arriver à " + J.getPosition().getNomCase());
		Terrain T = (Terrain)J.getPosition();
		_Msg2.setText(T.getNomCase() + " coute " + T.getPrixBase() + ". Voulez vous l'acheter ?");
	}
}
