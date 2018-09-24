package exceptions;

public class DataSetInitializationException extends Exception { 
	   
	private static final long serialVersionUID = 1L;

	public DataSetInitializationException(String errorMessage) {
        super(errorMessage);
    }
}



