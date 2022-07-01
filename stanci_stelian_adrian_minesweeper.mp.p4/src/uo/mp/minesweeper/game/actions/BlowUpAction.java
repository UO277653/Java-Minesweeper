package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.ArgumentChecks;

public class BlowUpAction implements Action {

	private Board board;
	
	public BlowUpAction(Board board) {
		ArgumentChecks.isTrue(board != null, "Invalid action parameter");
		this.board = board;
	}
	
	public void activate() {
		board.markAsExploded();
	}
}
