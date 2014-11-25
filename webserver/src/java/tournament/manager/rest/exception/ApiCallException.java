package tournament.manager.rest.exception;

public class ApiCallException extends RuntimeException{

	private int status = 500;
	
	public ApiCallException(String message) {
		super(message);
	}
	
	public ApiCallException(int status, String message) {
		super(message);
		this.status = status;
	}
	
	public ApiCallException(int status, String message, Throwable ex) {
		super(message, ex);
		this.status = status;
	}
	
	public ApiCallException(String message, Throwable ex) {
		super(message, ex);
	}
	
	public int getStatus() {
		return status;
	}
}
