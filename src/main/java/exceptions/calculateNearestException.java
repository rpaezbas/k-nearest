package exceptions;

public class calculateNearestException extends Exception { 
   
	private static final long serialVersionUID = 1L;

	public calculateNearestException(String errorMessage) {
        super(errorMessage);
    }
}

