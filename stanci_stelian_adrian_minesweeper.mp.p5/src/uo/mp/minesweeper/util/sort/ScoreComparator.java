package uo.mp.minesweeper.util.sort;

import java.util.Comparator;

import uo.mp.minesweeper.ranking.Score;

public class ScoreComparator implements Comparator<Score> {

	/**
	 * Compares two score objects, first by level (harder levels come before), 
	 * then by time (quicker games come before) and finally by date (older 
	 * games come before)
	 */
	@Override
	public int compare(Score o1, Score o2) {
		int result;
		
		result = o2.getLevel().compareTo(o1.getLevel());
		
		if(result == 0) {
			result = (int) (o1.getTime() - o2.getTime());
		}
		
		if(result == 0) {
			result = o2.getDate().compareTo(o1.getDate());
		}
		
		return result;
	}

}
