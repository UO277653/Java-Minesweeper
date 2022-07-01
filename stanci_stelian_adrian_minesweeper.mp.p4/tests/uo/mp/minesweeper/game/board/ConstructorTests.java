package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class ConstructorTests {

	/**
	 * GIVEN a board that is being constructed
	 * WHEN trying to construct a board
	 * THEN the board is constructed with the correct
	 * dimensions and number of mines
	 */
	@Test
	public void testConstructor() {
		Board board1 = new Board(5, 4, 11);
		
		assertEquals(2, board1.getMinesLeft());
		assertEquals(4, board1.getSquaresForTest().length);
		assertEquals(5, board1.getSquaresForTest()[0].length);
	}

}
