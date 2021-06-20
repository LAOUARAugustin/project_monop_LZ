package Exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Classe servant à afficher une boite d'alerte rapidement ( avec une seule ligne de code ).
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 *
 */

public class boiteAlerte {
	private String Header;
	private String content;
	
	private boiteAlerte(String h, String c) {
		this.Header = h;
		this.content = c;
	}
	
	public static void afficherBoite(alertException e) {
		boiteAlerte A = new boiteAlerte("Action impossible", e.getMsg());
		A.show();
	}
	
	public static void afficherBoite(String header, String content) {
		boiteAlerte A = new boiteAlerte(header, content);
		A.show();
	}
	private void show() {

		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(this.Header);
        alert.setContentText(this.content);
 
        alert.showAndWait();
	}
}
