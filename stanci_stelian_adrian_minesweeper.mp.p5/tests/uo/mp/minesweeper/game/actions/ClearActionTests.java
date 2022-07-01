package uo.mp.minesweeper.game.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class ClearActionTests {

	/**
	 * GIVEN a valid board with all squares empty
	 * WHEN stepping on a square
	 * THEN all the squares are opened
	 */
	@Test
	public void testActivateAllNull() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		Board board = new Board(0, aux);
		// RUNNING THE METHOD
		board.stepOn(0,0);
		char[][] result = board.getStatus();
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {' ',' ',' '}, result[0]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	/**
	 * GIVEN a valid board with empty squares and a bomb
	 * WHEN stepping on a square
	 * THEN all the empty squares are opened
	 */
	@Test
	public void testActivatePartial() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		aux[0][0].putMine();
		Board board = new Board(0, aux);
		// RUNNING THE METHOD
		board.stepOn(0,2);
		char[][] result = board.getStatus();
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {'#','1',' '}, result[0]);
		assertArrayEquals(new char[] {'1','1',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	/**
	 * GIVEN a valid board with empty squares, a bomb and a flag
	 * WHEN stepping on a square
	 * THEN all the empty squares are opened
	 */
	@Test
	public void testActivatePartial_withFlag() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		aux[0][0].putMine();
		aux[0][2].flag();
		Board board = new Board(0, aux);
		// RUNNING THE METHOD
		board.stepOn(1,2);
		char[][] result = board.getStatus();
		// CHECK EXPECTED RESULTS
		assertArrayEquals(new char[] {'#','1','¶'}, result[0]);
		assertArrayEquals(new char[] {'1','1',' '}, result[1]);
		assertArrayEquals(new char[] {' ',' ',' '}, result[2]);
	}
	
	private Square[][] generateEmptyBoard(){
		return new Square[][] {{new Square(), new Square(),
			new Square()}, {new Square(), new Square(), new Square()},
			 {new Square(), new Square(), new Square()}};
	}
}
