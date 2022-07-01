package uo.mp.minesweeper.ranking;

import java.io.Serializable;
import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.InputChecks;

public class Score implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private long gameTime;
	private GameLevel level;
	private boolean hasWon;
	private String username;
	
	/**
	 * Constructor of objects of type Score
	 * @param name
	 * @param level
	 * @param time
	 * @param hasWon
	 */
	public Score(String name, GameLevel level, long time, boolean hasWon) {
		date = new Date();
		setUserName(name);
		setLevel(level);
		setTime(time);
		setHasWon(hasWon);
	}

	/**
	 * Constructor of objects of type Score
	 * @param name
	 * @param level
	 * @param gameTime
	 * @param hasWon
	 * @param date
	 */
	public Score(String name, GameLevel level, long gameTime, boolean hasWon, 
			Date date) {
		this(name, level, gameTime, hasWon);
		this.date = date;
	}

	private void setTime(long time) {
		ArgumentChecks.isTrue(time > 0, "Time cannot be negative");
		this.gameTime = time;
	}

	private void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	private void setLevel(GameLevel level) {
		ArgumentChecks.isTrue(level != null, "Level cannot be null");
		this.level = level;
	}

	private void setUserName(String username) {
		InputChecks.checkString(username);
		this.username = username;
		
	}

	public Date getDate() {
		return date;
	}

	public long getTime() {
		return gameTime;
	}

	public GameLevel getLevel() {
		return level;
	}

	public boolean hasWon() {
		return hasWon;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean getHasWon() {
		return hasWon;
	}
	@Override
	public String toString() {
		return "Score [date=" + date + ", time=" + gameTime + ", level=" + 
				level + ", hasWon=" + hasWon + "]";
	}
	
	
	
}
