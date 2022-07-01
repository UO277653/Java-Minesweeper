package uo.mp.minesweeper.util;

public class InputChecks {

	/**
	 * Checks that a string is neither null nor empty
	 * @param string that is going to be checked
	 */
	public static void checkString(String arg) {
		
		if( arg == null) {
			throw new RuntimeException( arg + " cannot be null");
		}
		
		if( arg.trim().isEmpty()) {
			throw new RuntimeException( arg + " cannot be empty");
		}
	}
	
	/**
	 * Checks that an action is among those accepted
	 * @param action that is going to be checked
	 */
	public static void checkAction(char action) {
		if((action != 's') && (action != 'f') && (action != 'u')) {
			throw new IllegalArgumentException();
		}
	}
}
