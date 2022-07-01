package uo.mp.minesweeper.ranking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.InputChecks;
import uo.mp.minesweeper.util.log.FileLogger;

public class GameRanking {

	private List<Score> scores;
	private String rankingFileName;
	
	/**
	 * Constructor of objects of class GameRanking
	 * @param string which is the file in which the ranking is stored
	 */
	public GameRanking( String fileName ) {
		scores = new ArrayList<Score>();
		this.setRankingFileName(fileName);
		loadRanking();
	}
	
	@SuppressWarnings("unchecked")
	private void loadRanking() {
		
		ObjectInputStream reader = null;
		
		try {
			reader = new ObjectInputStream
					(new FileInputStream(rankingFileName));
			scores = (List<Score>) reader.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			FileLogger.log(e.getMessage());
			throw new RuntimeException("IOException while writing ranking"
					+ " to a file, please contact the programmer.");
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				FileLogger.log(e.getMessage());
				throw new RuntimeException("Error while closing the file");
			} catch (Exception e) { // minesweeper.rnk is empty
				FileLogger.log(e.getMessage());
				return;
			}
		}
		
	}

	/**
	 * Adds the score to the current ranking and updates the file in which
	 * it is contained
	 * @param score which is going to be added to the current ranking
	 */
	public void append(Score score) {
		ArgumentChecks.isTrue(score != null, "Score cannot be null");
		scores.add(score);
		updateRanking();
	}

	private void updateRanking() {
		ObjectOutputStream writer = null;
		
		try {
			writer = new ObjectOutputStream
					(new FileOutputStream(rankingFileName));
			writer.writeObject(scores);
		} catch (IOException e) {
			FileLogger.log(e.getMessage());
			throw new RuntimeException("IOException while writing in the"
					+ " ranking, please contact the programmer");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				FileLogger.log(e.getMessage());
				throw new RuntimeException("IOException while writing in the"
						+ " ranking, please contact the programmer.");
			}
		}
	}
	
	/**
	 * Returns all the scores in the ranking
	 * @return a list with all the scores in the ranking
	 */
	public List<Score> getGeneral(){
		List<Score> res = scores;
		return res;
	}
	
	/**
	 * Returns all the scores in the ranking of a certain user
	 * @param username
	 * @return a list with all the scores in the ranking that correspond
	 * to the username passed as a parameter
	 */
	public List<Score> getScoresFor(String username){
		InputChecks.checkString(username);
		List<Score> res = new ArrayList<Score>();
		
		for(Score score: scores) {
			if(score.getUsername().equals(username)) {
				res.add(score);
			}
		}
		return res;
	}

	public void setRankingFileName(String fileName) {
		InputChecks.checkString(fileName);
		this.rankingFileName = fileName;
	}
	
	/**
	 * Resets the ranking
	 */
	public void clear() {
		this.scores = new ArrayList<Score>();
	}
	
	
}
