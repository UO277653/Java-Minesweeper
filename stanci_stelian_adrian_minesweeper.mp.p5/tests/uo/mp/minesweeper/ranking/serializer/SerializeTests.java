package uo.mp.minesweeper.ranking.serializer;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.file.serializer.ScoreSerializer;

public class SerializeTests {

	/**
	 * GIVEN an empty list
	 * WHEN parsing
	 * THEN an empty list is returned
	 */
	@Test
	public void testEmptyList() {
		// PREPARING THE ENVIRONMENT
		ScoreSerializer ser = new ScoreSerializer();
		List<Score> scores = new LinkedList<Score>();
		
		// RUNNING THE METHOD
		List<String> res = ser.serialize(scores);
		
		// CHECK EXPECTED RESULTS
		assertTrue(res.isEmpty());
	}
	
	/**
	 * GIVEN a list with many correct score objects
	 * WHEN serializing
	 * THEN all the objects are serialized
	 */
	@Test
	public void testListCombinationObjects() {
		// PREPARING THE ENVIRONMENT
		ScoreSerializer ser = new ScoreSerializer();
		List<Score> scores = new LinkedList<Score>();
		scores.add(new Score("adri", GameLevel.HARD, 30, false));
		scores.add(new Score("adri", GameLevel.MEDIUM, 20, true));
		
		// RUNNING THE METHOD
		List<String> res = ser.serialize(scores);
		
		// CHECK EXPECTED RESULTS
		assertTrue(res.size() == 2);
	}
}
