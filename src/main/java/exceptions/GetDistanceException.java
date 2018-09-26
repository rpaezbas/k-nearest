package exceptions;

public class GetDistanceException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public GetDistanceException(String errorMessage) {
        super(errorMessage);
    }

}
