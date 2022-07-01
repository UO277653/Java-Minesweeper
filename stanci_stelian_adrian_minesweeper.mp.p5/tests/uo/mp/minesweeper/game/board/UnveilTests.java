package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class UnveilTests {

	/**
	 * GIVEN a valid board with all of its squares hidden
	 * WHEN opening all the squares
	 * THEN all the squares are opened
	 */
	@Test
	public void unveilFullClosedBoard() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		
		// RUN THE METHOD
		board.unveil();
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
		assertTrue(board.getSquaresForTest()[0][1].isOpen());
		assertTrue(board.getSquaresForTest()[0][2].isOpen());
		assertTrue(board.getSquaresForTest()[1][0].isOpen());
		assertTrue(board.getSquaresForTest()[1][1].isOpen());
		assertTrue(board.getSquaresForTest()[1][2].isOpen());
		assertTrue(board.getSquaresForTest()[2][0].isOpen());
		assertTrue(board.getSquaresForTest()[2][1].isOpen());
		assertTrue(board.getSquaresForTest()[2][2].isOpen());
	}

	/**
	 * GIVEN a valid board with some squares flagged
	 * WHEN opening all the squares
	 * THEN all the squares are opened
	 */
	@Test
	public void unveilSquaresFlaggedBoard() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].flag();
		board.getSquaresForTest()[1][0].flag();
		board.getSquaresForTest()[2][0].flag();
		
		// RUN THE METHOD
		board.unveil();
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
		assertTrue(board.getSquaresForTest()[0][1].isOpen());
		assertTrue(board.getSquaresForTest()[0][2].isOpen());
		assertTrue(board.getSquaresForTest()[1][0].isOpen());
		assertTrue(board.getSquaresForTest()[1][1].isOpen());
		assertTrue(board.getSquaresForTest()[1][2].isOpen());
		assertTrue(board.getSquaresForTest()[2][0].isOpen());
		assertTrue(board.getSquaresForTest()[2][1].isOpen());
		assertTrue(board.getSquaresForTest()[2][2].isOpen());
	}
	
	/**
	 * GIVEN a valid board with some squares already opened
	 * WHEN opening all the squares
	 * THEN all the squares are opened
	 */
	@Test
	public void unveilBoardUnveiledSquares() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].open();
		board.getSquaresForTest()[1][0].open();
		board.getSquaresForTest()[2][0].open();
		
		// RUN THE METHOD
		board.unveil();
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
		assertTrue(board.getSquaresForTest()[0][1].isOpen());
		assertTrue(board.getSquaresForTest()[0][2].isOpen());
		assertTrue(board.getSquaresForTest()[1][0].isOpen());
		assertTrue(board.getSquaresForTest()[1][1].isOpen());
		assertTrue(board.getSquaresForTest()[1][2].isOpen());
		assertTrue(board.getSquaresForTest()[2][0].isOpen());
		assertTrue(board.getSquaresForTest()[2][1].isOpen());
		assertTrue(board.getSquaresForTest()[2][2].isOpen());
	}
}
