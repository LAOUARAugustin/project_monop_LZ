package Exception;

public class alertException extends Exception {
	private String msg;
	
	public alertException(String msg) {
		this.msg = msg;
	}
	public String getMsg() {
		return this.msg;
	}
}
