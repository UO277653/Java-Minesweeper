package uo.mp.minesweeper.game.actions;

import java.util.List;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.ArgumentChecks;

public class ClearAction implements Action {

	List<Square> squares;
	
	/**
	 * Constructor of objects of class BlowUpAction
	 * @param list of the squares adyacent to a certain one
	 */
	public ClearAction(List<Square> neighbouringSquares) {
		ArgumentChecks.isTrue(neighbouringSquares != null, 
											"Invalid action parameter");
		squares = neighbouringSquares;
	}
	
	/**
	 * Performs the action associated with this class
	 */
	public  void activate() {
		for(Square square: squares) {
			if(!square.hasMine()) {
				square.stepOn();
			}
		}
	}
}
