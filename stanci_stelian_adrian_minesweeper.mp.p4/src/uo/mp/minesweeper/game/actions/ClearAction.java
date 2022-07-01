package uo.mp.minesweeper.game.actions;

import java.util.List;

import uo.mp.minesweeper.game.Action;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.ArgumentChecks;

public class ClearAction implements Action {

	List<Square> squares;
	
	public ClearAction(List<Square> neighbouringSquares) {
		ArgumentChecks.isTrue(neighbouringSquares != null, 
											"Invalid action parameter");
		squares = neighbouringSquares;
	}
	
	public  void activate() {
		for(Square square: squares) {
			if(!square.hasMine()) {
				square.stepOn();
			}
		}
	}
}
