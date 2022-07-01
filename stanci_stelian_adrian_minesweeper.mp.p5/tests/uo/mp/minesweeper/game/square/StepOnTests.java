package uo.mp.minesweeper.game.square;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Square;

public class StepOnTests {

	
	/**
	 * GIVEN an open square
	 * WHEN stepping on it
	 * THEN the square is revealed
	 */
	@Test
	public void revealOpenSquare() {
		// Preparing the environment
		Square aux = new Square();
		aux.open();
		
		// Running the method
		aux.stepOn();
		
		// Check expected results
		assertTrue(aux.isOpen());
		
	}
	
	/**
	 * GIVEN a closed square
	 * WHEN stepping on it
	 * THEN the square is revealed
	 */
	@Test
	public void revealClosedSquare() {
		// Preparing the environment
		Square aux = new Square();
		
		// Running the method
		aux.stepOn();
		
		// Check expected results
		assertTrue(aux.isOpen());
		
	}
	
	/**
	 * GIVEN a flagged square
	 * WHEN stepping on it
	 * THEN the square is not revealed
	 */
	@Test
	public void revealFlaggedSquare() {
		// Preparing the environment
		Square aux = new Square();
		aux.flag();
		
		// Running the method
		aux.stepOn();
		
		// Check expected results
		assertFalse(aux.isOpen());
		
	}

}
