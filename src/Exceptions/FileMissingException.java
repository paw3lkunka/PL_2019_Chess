package Exceptions;

/**
 * The Class FileMissingException.
 * @author Piotr Ruciñski
 */
public class FileMissingException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new file missing exception.
	 *
	 * @param msg the message
	 */
	public FileMissingException(String msg) 
	{
		super(msg);
	}
	
}
