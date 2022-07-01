package uo.mp.minesweeper.game.actions;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;

public class BlowUpActionTests {

	/**
	 * GIVEN a valid board with a bomb
	 * WHEN stepping on a bomb
	 * THEN the state of the game is set to blown up
	 */
	@Test
	public void testActivate_bombTile() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		Board board = new Board(1, aux);
		aux[0][0].setAction(new BlowUpAction(board));
		
		// RUNNING THE METHOD
		board.stepOn(0,0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.isExploded());
	}
	
	/**
	 * GIVEN a valid board
	 * WHEN stepping on an empty tile
	 * THEN the state of the game is not set to blown up
	 */
	@Test
	public void testActivate_emptyTile() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		
		Board board = new Board(1, aux);
		// RUNNING THE METHOD
		board.stepOn(0,0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(!board.isExploded());
	}
	
	/**
	 * GIVEN a valid board
	 * WHEN stepping on a flagged tile without a bomb
	 * THEN the state of the game is not set to blown up
	 */
	@Test
	public void testActivate_flaggedUnArmedTile() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		aux[0][0].flag();
		Board board = new Board(1, aux);
		// RUNNING THE METHOD
		board.stepOn(0,0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(!board.isExploded());
	}
	
	/**
	 * GIVEN a valid board
	 * WHEN stepping on a flagged tile with a bomb
	 * THEN the state of the game is not set to blown up
	 */
	@Test
	public void testActivate_flaggedArmedTile() {
		// PREPARING THE ENVIRONMENT
		Square[][] aux = generateEmptyBoard();
		aux[0][0].putMine();
		aux[0][0].flag();
		Board board = new Board(1, aux);
		// RUNNING THE METHOD
		board.stepOn(0,0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(!board.isExploded());
	}
	
	private Square[][] generateEmptyBoard(){
		return new Square[][] {{new Square(), new Square(),
			new Square()}, {new Square(), new Square(), new Square()},
			 {new Square(), new Square(), new Square()}};
	}
}
