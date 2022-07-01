package uo.mp.minesweeper.ranking;

import java.util.ArrayList;
import java.util.List;

import uo.mp.minesweeper.util.ArgumentChecks;

public class GameRanking {

	private List<Score> scores;
	
	public GameRanking() {
		scores = new ArrayList<Score>(); // no se si linked o array
	}
	
	/**
	 * 
	 * @param score
	 */
	public void append(Score score) {
		ArgumentChecks.isTrue(score != null, "Score cannot be null");
		scores.add(score);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Score> getGeneral(){
		List<Score> res = scores;
		return res;
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public List<Score> getScoresFor(String username){
		ArgumentChecks.isTrue(username != null, "Username cannot be null");
		ArgumentChecks.isTrue(!username.trim().isEmpty(), "Username cannot be "
				+ "null");
		List<Score> res = new ArrayList<Score>();
		
		for(Score score: scores) {
			if(score.getUsername().equals(username)) {
				res.add(score);
			}
		}
		return res;
	}
	
}
