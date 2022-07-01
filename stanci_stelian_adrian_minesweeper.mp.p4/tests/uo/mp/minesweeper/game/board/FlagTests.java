package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class FlagTests {

	/**
	 * GIVEN a valid board with one of its squares flagged
	 * WHEN flagging it
	 * THEN the square has a flag
	 */
	@Test
	public void flagFlaggedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].flag();
		
		// RUN THE METHOD
		board.flag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].hasFlag());
	}

	/**
	 * GIVEN a valid board with one of its squares unflagged
	 * and armed
	 * WHEN flagging it
	 * THEN the square has a flag
	 */
	@Test
	public void flagUnflaggedArmedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].putMine();
		
		// RUN THE METHOD
		board.flag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].hasFlag());
	}
	
	/**
	 * GIVEN a valid board with one of its squares unflagged
	 * and unarmed
	 * WHEN flagging it
	 * THEN the square has a flag
	 */
	@Test
	public void flagUnflaggedUnarmedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		
		// RUN THE METHOD
		board.flag(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].hasFlag());
	}
}
