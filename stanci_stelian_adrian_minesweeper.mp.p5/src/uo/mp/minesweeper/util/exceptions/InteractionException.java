package uo.mp.minesweeper.util.exceptions;

public class InteractionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "Parsing error in line <%d>";
	
	public InteractionException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
