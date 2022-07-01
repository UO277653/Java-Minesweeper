package uo.mp.minesweeper.game.square;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class FlagTests {

	/**
	 * GIVEN an open square
	 * WHEN flagging it
	 * THEN the square is not flagged
	 */
	@Test
	public void flagOpenSquare() {
		// Preparing the environment
		Square aux = new Square();
		aux.open();
		
		// Running the method
		aux.flag();
		
		// Check expected results
		assertFalse(aux.hasFlag());
		
	}
	
	/**
	 * GIVEN a closed square
	 * WHEN flagging it
	 * THEN the square is flagged
	 */
	@Test
	public void flagClosedSquare() {
		// Preparing the environment
		Square aux = new Square();
		
		// Running the method
		aux.flag();
		
		// Check expected results
		assertTrue(aux.hasFlag());
		
	}
	
	/**
	 * GIVEN a flagged square
	 * WHEN flagging it
	 * THEN the square is flagged
	 */
	@Test
	public void flagFlaggedSquare() {
		// Preparing the environment
		Square aux = new Square();
		
		// Running the method
		aux.flag();
		
		// Check expected results
		assertTrue(aux.hasFlag());
		
	}

}
