package Exceptions;

/**
 * The Class ColorMissingException.
 * @author Piotr Ruciñski
 */
public class ColorMissingException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new color missing exception.
	 *
	 * @param msg the message
	 */
	public ColorMissingException(String msg) 
	{
		super(msg);
	}
	
}
