package Exceptions;

public class OutOfChessboardException extends Exception {

	private static final long serialVersionUID = 1L;

	public OutOfChessboardException(String msg) 
	{
		super(msg);
	}
	
}
