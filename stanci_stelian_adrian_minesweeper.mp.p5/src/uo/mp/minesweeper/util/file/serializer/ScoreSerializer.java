package uo.mp.minesweeper.util.file.serializer;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import uo.mp.minesweeper.ranking.Score;

public class ScoreSerializer {

	/**
	 * Generates a list of strings with a given format given a list of scores
	 * @param scores to be serialized
	 * @return strings with the scores
	 */
	public List<String> serialize(List<Score> scores){
		if(scores == null) {
			return null;
		}
		
		List<String> res = new LinkedList<String>();
		
		for(Score score: scores) {
			String scoreSerial = generateSerial(score);
			res.add(scoreSerial);
		}
		
		return res;
	}

	private String generateSerial(Score score) {
		String res;
		String date = new SimpleDateFormat("dd/MM/yy").format
				( score.getDate() );
		String time = new SimpleDateFormat("HH:mm:ss").format
				( score.getDate() );
		res = score.getUsername() + ";";
		res += date + ";";
		res += time + ";";
		res += score.getLevel() + ";";
		res += getResult(score.getHasWon()) + ";";
		res += score.getTime() + "\n";
				
		return res;
	}
	
	private String getResult(boolean hasWon) {
		return hasWon == true ? "won" : "lost";
	}
}
