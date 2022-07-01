package uo.mp.minesweeper.util.file.parser;

import java.text.ParseException; 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.InputChecks;
import uo.mp.minesweeper.util.exceptions.InvalidLineFormatException;
import uo.mp.minesweeper.util.log.FileLogger;

public class RankingParser {

	private static final int CORRECT_FIELDS_NUMBER = 6;
	private int numLine = 0;
	
	/**
	 * Generates a list of scores given a list of strings with the right
	 * format
	 * @param list of strings to be read
	 * @return list of scores
	 */
	public List<Score> parse(List<String> scores) {
		List<Score> result = new LinkedList<>();
		if(scores == null) {
			return result;
		}
		for (String score: scores) {
			try {
				numLine++;
				result.add(parseLine(score));
			} catch (InvalidLineFormatException e) {
				FileLogger.log(e.getMessage());
			}
		}
		
		return result;
		
	}
	
	private Score parseLine(String score) throws InvalidLineFormatException {
		Score result = null;
		
		if(score == null | score.trim().isEmpty()) {
			throw new InvalidLineFormatException(numLine);
		}
		
		String[] splittedScore = score.split(";");
		
		if(splittedScore.length != CORRECT_FIELDS_NUMBER) { 
			throw new InvalidLineFormatException(numLine);
		}
		
		String name = splittedScore[0];
		
		try {
			String dateTime = splittedScore[1] + " " + splittedScore[2];
			Date date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse
					(dateTime);
			GameLevel level = parseLevel(splittedScore[3]);
			boolean hasWon = parseHasWon(splittedScore[4]);
			long gameTime = Integer.valueOf(splittedScore[5]);
			
			result = new Score(name, level, gameTime, hasWon, date);
			
		} catch (ParseException e) {
			throw new InvalidLineFormatException(numLine);
		}
		
		return result;
	}

	private boolean parseHasWon(String string) 
			throws InvalidLineFormatException {
		InputChecks.checkString(string);
		switch(string.toUpperCase()) {
			case "WON":
				return true;
			case "LOST":
				return false;
			default:
				throw new InvalidLineFormatException(numLine);
		}
	}

	private GameLevel parseLevel(String string) throws 
													InvalidLineFormatException {
		InputChecks.checkString(string);
		switch(string.toUpperCase()) {
		case "EASY":
			return GameLevel.EASY;
		case "MEDIUM":
			return GameLevel.MEDIUM;
		case "HARD":
			return GameLevel.HARD;
		default:
			throw new InvalidLineFormatException(numLine);
		}
	}
	
}
