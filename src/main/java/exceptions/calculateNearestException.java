package exceptions;

public class CalculateNearestException extends Exception { 
   
	private static final long serialVersionUID = 1L;

	public CalculateNearestException(String errorMessage) {
        super(errorMessage);
    }
}

