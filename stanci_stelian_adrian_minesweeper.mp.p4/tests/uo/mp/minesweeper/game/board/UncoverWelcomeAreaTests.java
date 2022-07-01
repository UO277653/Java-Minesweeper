package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*; 

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class UncoverWelcomeAreaTests {

	/**
	 * GIVEN a board with no mines
	 * WHEN unveiling an empty tile at the beginning
	 * THEN all the board is opened
	 */
	@Test
	public void testUncoverWelcome_EmptyBoard() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(1, generateEmptyBoard_threebythree());
		
		// RUN THE METHOD
		board.uncoverWelcomeArea();
		char[][] result = board.getStatus();
		
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {' ',' ',' '}, result[0]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	/**
	 * GIVEN a 2x2 board with a mine at the corner
	 * WHEN unveiling an empty tile at the beginning
	 * THEN no tile is opened because all of them are corners
	 */
	@Test
	public void testUncoverWelcome_2x2_allCorners() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard_twobytwo();
		aux[0][0].putMine();
		Board board = new Board(1, aux);
		board.uncoverWelcomeArea();
		
		// RUN THE METHOD
		char[][] result = board.getStatus();
		
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {'#','#'}, result[0]);
		assertArrayEquals(new char[] {'#','#'}, result[1]);
	}
	
	private Square[][] generateEmptyBoard_threebythree(){
		return new Square[][] {{new Square(), new Square(),
			new Square()}, {new Square(), new Square(), new Square()},
			 {new Square(), new Square(), new Square()}};
	}
	
	private Square[][] generateEmptyBoard_twobytwo(){
		return new Square[][] {{new Square(), new Square()}, {new Square(), 
			new Square()},};
	}

}
