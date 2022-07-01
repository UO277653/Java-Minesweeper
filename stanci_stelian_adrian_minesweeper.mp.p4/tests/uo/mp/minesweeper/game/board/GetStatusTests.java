package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class GetStatusTests {

	/**
	 * GIVEN a completely hidden board
	 * WHEN trying to get the status of the board
	 * THEN the status is returned
	 */
	@Test
	public void hiddenBoard() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		
		
		// RUN THE METHOD
		char[][] result = board.getStatus();
		
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {'#','#','#'}, result[0]);
		assertArrayEquals(new char[] {'#','#','#'}, result[1]);
		assertArrayEquals(new char[] {'#','#','#'}, result[2]);
	}

	/**
	 * GIVEN a completely revealed board
	 * WHEN trying to get the status of the board
	 * THEN the status is returned
	 */
	@Test
	public void revealedBoard() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(1, generateEmptyBoard());
		
		board.unveil();
		
		// RUN THE METHOD
		char[][] result = board.getStatus();
		
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {' ',' ',' '}, result[0]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	/**
	 * GIVEN a board with every character
	 * WHEN trying to get the status of the board
	 * THEN the status is returned
	 */
	@Test
	public void partialBoard() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		aux[0][0].putMine();
		Board board = new Board(1, aux);
		board.flag(1, 0);
		board.stepOn(0, 1);
		board.stepOn(0, 2);
		
		// RUN THE METHOD
		char[][] result = board.getStatus();
		
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {'#','1',' '}, result[0]);
		assertArrayEquals(new char[] {'¶','1',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	
	private Square[][] generateEmptyBoard(){
		return new Square[][] {{new Square(), new Square(),
			new Square()}, {new Square(), new Square(), new Square()},
			 {new Square(), new Square(), new Square()}};
	}
}
