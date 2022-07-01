package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.ArgumentChecks;

public class BlowUpAction implements Action {

	private Board board;
	
	/**
	 * Constructor of objects of class BlowUpAction
	 * @param board which is the parameter of this class
	 */
	public BlowUpAction(Board board) {
		ArgumentChecks.isTrue(board != null, "Invalid action parameter");
		this.board = board;
	}
	
	/**
	 * Performs the action associated with this class
	 */
	public void activate() {
		board.markAsExploded();
	}
}
