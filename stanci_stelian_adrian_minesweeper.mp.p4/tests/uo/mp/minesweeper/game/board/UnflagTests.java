package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class UnflagTests {

	/**
	 * GIVEN a valid board with one of its squares armed and flagged
	 * WHEN removing the flag
	 * THEN the flag is removed
	 */
	@Test
	public void unflagArmedFlaggedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].putMine();
		board.getSquaresForTest()[0][0].flag();
		
		// RUN THE METHOD
		board.unflag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertFalse(board.getSquaresForTest()[0][0].hasFlag());
	}
	
	/**
	 * GIVEN a valid board with one of its squares armed
	 * WHEN trying to remove a flag
	 * THEN the flag is removed
	 */
	@Test
	public void unflagUnflaggedArmedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].putMine();
		
		// RUN THE METHOD
		board.unflag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertFalse(board.getSquaresForTest()[0][0].hasFlag());
	}
	
	/**
	 * GIVEN a valid board
	 * WHEN trying to remove a flag
	 * THEN the flag is removed
	 */
	@Test
	public void unflagUnflaggedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,0);
		
		// RUN THE METHOD
		board.unflag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertFalse(board.getSquaresForTest()[0][0].hasFlag());
	}
}
