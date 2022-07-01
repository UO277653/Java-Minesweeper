package uo.mp.minesweeper.ranking.parser;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.file.parser.RankingParser;

public class ParseTests {

	/**
	 * GIVEN an empty list
	 * WHEN parsing
	 * THEN an empty list of scores is returned
	 */
	@Test
	public void testEmptyList() {
		// PREPARING THE ENVIRONMENT
		RankingParser parser = new RankingParser();
		List<String> lines = new LinkedList<String>();
		
		// RUNNING THE METHOD
		List<Score> result = parser.parse(lines);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result.isEmpty());
	}
	
	/**
	 * GIVEN a list with an empty line
	 * WHEN parsing
	 * THEN the empty line is ignored
	 */
	@Test
	public void testListWithEmptyLine() {
		// PREPARING THE ENVIRONMENT
		RankingParser parser = new RankingParser();
		List<String> lines = new LinkedList<String>();
		lines.add("adri;09/05/20;18:04:10;EASY;won;3");
		lines.add(" ");
		lines.add("adri;09/05/20;19:06:16;EASY;won;3");
		
		// RUNNING THE METHOD
		List<Score> result = parser.parse(lines);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result.size() == 2);
	}
	
	/**
	 * GIVEN a list with a line with an incorrect number of fields
	 * WHEN parsing
	 * THEN the line is ignored
	 */
	@Test
	public void testIncorrectNumberOfFieldsLine() {
		// PREPARING THE ENVIRONMENT
		RankingParser parser = new RankingParser();
		List<String> lines = new LinkedList<String>();
		lines.add("adri;09/05/20;18:04:10;EASY;won;3");
		lines.add("adri;09/05/20;19:06:16;won;3");
		lines.add("adri;09/05/20;19:06:16;EASY;won;3");
		
		// RUNNING THE METHOD
		List<Score> result = parser.parse(lines);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result.size() == 2);
	}
	
	/**
	 * GIVEN a list with a line with an incorrect result
	 * WHEN parsing
	 * THEN the line is ignored
	 */
	@Test
	public void testIncorrectResultsLine() {
		// PREPARING THE ENVIRONMENT
		RankingParser parser = new RankingParser();
		List<String> lines = new LinkedList<String>();
		lines.add("adri;09/05/20;18:04:10;EASY;won;3");
		lines.add("adri;09/05/20;19:06:16;ganado;3");
		lines.add("adri;09/05/20;19:06:16;EASY;won;3");
		
		// RUNNING THE METHOD
		List<Score> result = parser.parse(lines);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result.size() == 2);
	}
	
	/**
	 * GIVEN a list with only correct lines
	 * WHEN parsing
	 * THEN all the lines are parsed
	 */
	@Test
	public void testListWithCorrectLines() {
		// PREPARING THE ENVIRONMENT
		RankingParser parser = new RankingParser();
		List<String> lines = new LinkedList<String>();
		lines.add("adri;09/05/20;18:04:10;EASY;won;3");
		lines.add("adri;09/05/20;19:00:32;EASY;won;3");
		lines.add("adri;09/05/20;19:06:16;EASY;won;3");
		
		// RUNNING THE METHOD
		List<Score> result = parser.parse(lines);
		
		// CHECK EXPECTED RESULTS
		assertTrue(result.size() == 3);
	}
}
