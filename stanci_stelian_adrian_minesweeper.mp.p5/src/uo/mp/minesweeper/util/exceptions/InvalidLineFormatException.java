package uo.mp.minesweeper.util.exceptions;

public class InvalidLineFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numLine;
	private String defaultMessage = "Parsing error in line <%d>";
	
	public InvalidLineFormatException(int arg) {
		super();
		this.numLine = arg;
	}
	
	public String getMessage() {
		return String.format(defaultMessage, this.numLine);
	}
	
	
}
