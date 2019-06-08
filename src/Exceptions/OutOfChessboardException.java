package Exceptions;


/**
 * The Class OutOfChessboardException.
 * @author Piotr Ruciñski
 */
public class OutOfChessboardException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new out of chessboard exception.
	 *
	 * @param msg the message
	 */
	public OutOfChessboardException(String msg) 
	{
		super(msg);
	}
	
}
