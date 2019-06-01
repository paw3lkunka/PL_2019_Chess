package Exceptions;

public class ColorMissingException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ColorMissingException(String msg) 
	{
		super(msg);
	}
	
}
