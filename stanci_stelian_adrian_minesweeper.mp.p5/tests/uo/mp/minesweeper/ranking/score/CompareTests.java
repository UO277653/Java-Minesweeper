package uo.mp.minesweeper.ranking.score;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.sort.ScoreComparator;

public class CompareTests {

	/**
	 * GIVEN two score objects with different levels
	 * WHEN comparing them
	 * THEN the one with higher level has higher priority
	 */
	@Test
	public void testDifficultyLessThan() {
		// PREPARING THE ENVIRONMENT
		Score s1 = new Score("adri", GameLevel.MEDIUM, 30, false);
		Score s2 = new Score("adri", GameLevel.HARD, 30, false);
		
		// RUNNING THE METHOD
		int result = new ScoreComparator().compare(s1, s2);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result > 0);
	}
	
	/**
	 * GIVEN two score objects with equal information
	 * WHEN comparing them
	 * THEN they have the same priority
	 */
	@Test
	public void testDifficultyEqual() {
		// PREPARING THE ENVIRONMENT
		Score s1 = new Score("adri", GameLevel.HARD, 30, false);
		Score s2 = new Score("adri", GameLevel.HARD, 30, false);
		
		// RUNNING THE METHOD
		int result = new ScoreComparator().compare(s1, s2);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result == 0);
	}
	
	/**
	 * GIVEN two score objects with different levels
	 * WHEN comparing them
	 * THEN the one with higher level has higher priority
	 */
	@Test
	public void testDifficultyGreaterThan() {
		Score s1 = new Score("adri", GameLevel.HARD, 30, false);
		Score s2 = new Score("adri", GameLevel.MEDIUM, 30, false);
		
		// RUNNING THE METHOD
		int result = new ScoreComparator().compare(s1, s2);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result < 0);
	}
	
	/**
	 * GIVEN two score objects with different time
	 * WHEN comparing them
	 * THEN the one with less time has higher priority
	 */
	@Test
	public void testDifferentTime() {
		Score s1 = new Score("adri", GameLevel.HARD, 29, false);
		Score s2 = new Score("adri", GameLevel.HARD, 30, false);
		
		// RUNNING THE METHOD
		int result = new ScoreComparator().compare(s1, s2);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result < 0);
	}
	
	/**
	 * GIVEN two score objects with different date
	 * WHEN comparing them
	 * THEN the one with less time has higher priority
	 */
	@Test
	public void testDifferentDate() {
		Score s1 = new Score("adri", GameLevel.HARD, 30, false, new Date());
		
		try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
		}
		
		Score s2 = new Score("adri", GameLevel.HARD, 30, false, new Date());
		
		// RUNNING THE METHOD
		int result = new ScoreComparator().compare(s1, s2);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result > 0);
	}
}
