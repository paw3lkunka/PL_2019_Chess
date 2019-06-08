package Exceptions;

/**
 * The Class NoCollisionException.
 * @author Piotr Ruciñski
 */
public class NoCollisionException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new no collision exception.
	 *
	 * @param msg the message
	 */
	public NoCollisionException(String msg) {
		super(msg);
	}
		
}
