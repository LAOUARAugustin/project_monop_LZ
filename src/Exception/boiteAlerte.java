package Exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class boiteAlerte {
	private String Header;
	private String content;
	
	public boiteAlerte(String h, String c) {
		this.Header = h;
		this.content = c;
	}
	
	public void show() {

		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(this.Header);
        alert.setContentText(this.content);
 
        alert.showAndWait();
	}
}
