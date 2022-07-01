package uo.mp.minesweeper.game.board;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.mp.minesweeper.game.Board;

public class StepOnTests {

	/**
	 * GIVEN a revealed square
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealRevealedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].open();
		
		// RUN THE METHOD
		board.stepOn(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
	}

	/**
	 * GIVEN a flagged armed square
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealMarkedArmedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].flag();
		board.getSquaresForTest()[0][0].putMine();
		
		// RUN THE METHOD
		board.stepOn(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertFalse(board.getSquaresForTest()[0][0].isOpen());
	}
	
	/**
	 * GIVEN a flagged square
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealMarkedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].flag();
		
		// RUN THE METHOD
		board.stepOn(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertFalse(board.getSquaresForTest()[0][0].isOpen());
	}
	
	/**
	 * GIVEN an armed square
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealArmedSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].putMine();
		
		// RUN THE METHOD
		board.stepOn(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
	}
	
	/**
	 * GIVEN a square with a clue
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealClueSquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		board.getSquaresForTest()[0][0].putMine();
		
		// RUN THE METHOD
		board.stepOn(0, 1);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][1].isOpen());
	}
	
	/**
	 * GIVEN an armed square
	 * WHEN trying to reveal the square
	 * THEN the square is revealed
	 */
	@Test
	public void revealEmptySquare() {
		// PREPARING THE ENVIRONMENT
		Board board = new Board(3,3,12);
		
		// RUN THE METHOD
		board.stepOn(0, 0);
		
		// CHECK EXPECTED RESULTS
		assertTrue(board.getSquaresForTest()[0][0].isOpen());
	}
}
