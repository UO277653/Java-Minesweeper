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
	
	/**
	 * Evaluates whether a coordinate is valid or not, given the limits
	 * of the board
	 * @param x to be tested
	 * @param y to be tested
	 * @param board whose limits are used
	 * @return true if valid, false otherwise
	 */
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
}
