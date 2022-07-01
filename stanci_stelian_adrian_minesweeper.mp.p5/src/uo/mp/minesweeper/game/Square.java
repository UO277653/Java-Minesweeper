package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Square {
	private static final int MIN_VALUE = -1;
	private static final int MAX_VALUE = 8;

	public static enum State {FLAGGED, OPEN, CLOSED};
	
	private static final String CLOSED_SYMBOL = "#";
	private static final String FLAG_SYMBOL = Character.toString((char) 182);
	private static final String EMPTY_SYMBOL = " ";
	private static final String MINE_SYMBOL = "@";
	private State state;
	private int content;
	private Action action;
	
	/**
	 * Constructor of objects of class Square
	 */
	public Square() {
		setState(State.CLOSED);
		setValue(0);
		setAction(new NullAction());
	}
	
	/**
	 * If actual state is CLOSED, it becomes OPEN. Otherwise, does nothing.
	 */
	public void stepOn() {
		if(getState()==State.CLOSED) {
			setState(State.OPEN);
			this.action.activate();
		}
	}
	
	public void setAction(Action action) {
		ArgumentChecks.isTrue(action != null, "Invalid action parameter");
		this.action = action;
	}
	
	/**
	 * Returns a character representing the square according to its state 
	 * and content.
	 */
	public String toString() {
		if(!isOpen() && !hasFlag()) {
			return CLOSED_SYMBOL;
		}
		else if(!isOpen() && hasFlag()) {
			return FLAG_SYMBOL;
		}
		else if(isOpen() && hasMine()) {
			return MINE_SYMBOL;
		}
		else if(isOpen() && (getValue() == 0)) {
			return EMPTY_SYMBOL;
		}
		else {
			return Integer.toString(content);
		}
	}
	
	/**
	 * If actual state is CLOSE, it becomes FLAGGED. Otherwise, does nothing.
	 */
	public void flag() {
		if(getState()==State.CLOSED) {
			setState(State.FLAGGED);
		}
	}
	
	/**
	 * If actual state is FLAGGED, it becomes CLOSED. Otherwise, does nothing.
	 */
	public void unflag() {
		if(getState() == State.FLAGGED) {
			setState(State.CLOSED);
		}
	}
	
	/**
	 * State becomes OPEN.
	 */
	public void open() {
		setState(State.OPEN);
	}
	
	/**
	 * Returns its numerical value.
	 */
	public int getValue() {
		return content;
	}
	
	/**
	 * Set the content to the argument.
	 * @param value
	 */
	public void setValue(int value) {
		ArgumentChecks.isTrue(value>=MIN_VALUE, 
				"Error: Value cannot be lower than -1");
		ArgumentChecks.isTrue(value<=MAX_VALUE, 
				"Error: Value cannot be higher than 8");
		content = value;
	}
	
	/**
	 * Returns true whenever the square hides a mine; false, otherwise.
	 */
	public boolean hasMine() {
		return getValue()==MIN_VALUE;
	}
	/**
	 * Set the content to the value corresponding to a mine.
	 */
	public void putMine() {
		setValue(MIN_VALUE);
	}
	
	/**
	 * Returns true whenever state is FLAGGED; false otherwise.
	 * @return true whenever state is FLAGGED; false otherwise.
	 */
	public boolean hasFlag() {
		return getState()==State.FLAGGED;
	}
	
	/**
	 * Returns true whenever state is OPEN and false otherwise.
	 * @return true whenever state is OPEN and false otherwise.
	 */
	public boolean isOpen() {
		return getState()==State.OPEN;
	}
	
	private State getState() {
		return this.state;
	}
	
	private void setState(State State) {
		this.state = State;
	}
	
	
}