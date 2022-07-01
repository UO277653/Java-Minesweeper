package uo.mp.minesweeper.util;

import uo.mp.minesweeper.game.Square;

public class ArgumentChecks {

	/**
	 * Evaluates condition. If false, throw IllegalArgumentException with 
	 * message
	 * @param condition
	 * @param message
	 */
	public static void isTrue(boolean condition, String message) {
		if(!condition) {
			throw new IllegalArgumentException(message);
		}
	}
	/**
	 * Evaluates condition. If false, throw IllegalArgumentException
	 * @param condition
	 */
	public static void isTrue(boolean condition) {
		if(!condition) {
			throw new IllegalArgumentException();
		}
	}
	
	public static boolean isValidCoord(int x, int y, Square[][] board) {
		if(x < 0 || y < 0) {
			return false;
		}
		
		if(x >= board.length) {
			return false;
		}
		
		if(y >= board[0].length) {
			return false;
		}
		
		return true;
	}
	public static void checkOperation(char operation) {
		if( (operation != 's') && (operation != 'f') && (operation != 'u') ) {
			throw new IllegalArgumentException("The operation introduced is not"
							+ " correct");
		}
		
	}
}
