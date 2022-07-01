package uo.mp.minesweeper.game.square;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class UnflagTests {

	/**
	 * GIVEN an open square
	 * WHEN unflagging it
	 * THEN the square is revealed
	 */
	@Test
	public void unflagOpenSquare() {
		// Preparing the environment
		Square aux = new Square();
		aux.open();
		
		// Running the method
		aux.unflag();
		
		// Check expected results
		assertFalse(aux.hasFlag());
		
	}
	
	/**
	 * GIVEN a closed square
	 * WHEN unflagging it
	 * THEN the square is revealed
	 */
	@Test
	public void unflagClosedSquare() {
		// Preparing the environment
		Square aux = new Square();
		
		// Running the method
		aux.unflag();
		
		// Check expected results
		assertFalse(aux.hasFlag());
		
	}
	
	/**
	 * GIVEN a closed square
	 * WHEN unflagging it
	 * THEN the square is revealed
	 */
	@Test
	public void unflagFlaggedSquare() {
		// Preparing the environment
		Square aux = new Square();
		aux.flag();
		
		// Running the method
		aux.unflag();
		
		// Check expected results
		assertFalse(aux.hasFlag());
		
	}

}
