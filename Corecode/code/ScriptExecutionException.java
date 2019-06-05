package code;
//public class ScriptExecutionException extends Exception {
	@SuppressWarnings("serial")
	public class ScriptExecutionException extends RuntimeException {
	    public ScriptExecutionException(String message) {
	        super(message);
	    }

	    public ScriptExecutionException(String message, Exception cause) {
	        super(message, cause);
	    }
	}
