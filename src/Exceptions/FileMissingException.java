package Exceptions;

public class FileMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public FileMissingException(String msg) 
	{
		super(msg);
	}
	
}
