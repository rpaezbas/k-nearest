package exceptions;

public class VectorInitializationException extends Exception { 
   
	private static final long serialVersionUID = 1L;

	public VectorInitializationException(String errorMessage) {
        super(errorMessage);
    }
}
