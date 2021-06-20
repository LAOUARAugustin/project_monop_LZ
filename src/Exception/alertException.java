package Exception;
/**
 * Exception qui sera destiné à afficher une boite d'alerte.
 * @author LAOUAR Augustin, ZEDDAM Thinhinane
 * @see boiteAlerte.java
 *
 */
public class alertException extends Exception {
	private String msg;
	
	public alertException(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return this.msg;
	}
}
