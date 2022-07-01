package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.ArgumentChecks;

public class Game {
	
	private Board board;
	private long time;
	private GameInteractor interactor;
	
	/**
	 * Constructor for objects of type Game
	 * @param board
	 */
	public Game(Board board) {
		this.board = board;
		time = System.currentTimeMillis();
	}
	
	/**
	 * It sets interactor as the interface to be used to communicate with the 
	 * player.
	 * @param interactor to be used
	 */
	public void setInteractor(GameInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, "Error: interactor must be "
				+ "different " + "from null");
		this.interactor = interactor;
	}
	
	/**
	 * It is responsible for initializations (user messages, timer, etc.), 
	 * for deciding whether game is finished or not as well as for managing the 
	 * main loop of the game.
	 * 
	 */
	public void play() {
		board.uncoverWelcomeArea();
		interactor.showReadyToStart();
		interactor.showGame(getTime(), board);
		
		while(!board.isExploded() || board.getMinesLeft()==0) {
			GameMove action = interactor.askMove(board.rows(), board.cols());
			
			decideAction(action.getOperation(), action.getRow(), 
							action.getColumn());
			
			if(board.isExploded()) {
				interactor.showBooommm();
				board.unveil();
			}
			
			if(board.getMinesLeft()==0) {
				interactor.showCongratulations(getTime());
				board.unveil();
				interactor.showGame(getTime(), board);
				break;
			}
			
			interactor.showGame(getTime(), board);
			continue;
		}
		interactor.showGameFinished();
	}
	
	
	public long getTime() {
		return Math.round((System.currentTimeMillis() - time)/1000);
	}
	
	public boolean isWon() {
		return !this.board.isExploded();
	}
	
	private void decideAction(char action, int coord1, int coord2) {
		
		if (action == 's') {
			board.stepOn(coord1, coord2);
		}
		else if (action == 'f') {
			board.flag(coord1,  coord2);
		}
		else if (action == 'u') {
			board.unflag(coord1,  coord2);
		}
	}
}
