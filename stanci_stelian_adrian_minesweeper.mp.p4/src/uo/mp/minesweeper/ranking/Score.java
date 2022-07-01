package uo.mp.minesweeper.ranking;

import java.util.Date;

import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Score {

	private Date date;
	private long time;
	private GameLevel level;
	private boolean hasWon;
	private String username;
	
	public Score(String name, GameLevel level, long time, boolean hasWon) {
		date = new Date();
		setUserName(name);
		setLevel(level);
		setTime(time);
		setHasWon(hasWon);
	}

	private void setTime(long time) {
		ArgumentChecks.isTrue(time > 0, "Time cannot be negative");
		this.time = time;
	}

	private void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	private void setLevel(GameLevel level) {
		ArgumentChecks.isTrue(level != null, "Level cannot be null");
		this.level = level;
	}

	private void setUserName(String username) {
		ArgumentChecks.isTrue(username != null, "Username cannot be null");
		ArgumentChecks.isTrue(!username.trim().isEmpty(), "Username cannot be "
				+ "null");
		this.username = username;
		
	}

	public Date getDate() {
		return date;
	}

	public long getTime() {
		return time;
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

	@Override
	public String toString() {
		return "Score [date=" + date + ", time=" + time + ", level=" + level + 
				", hasWon=" + hasWon + "]";
	}
	
	
	
}
