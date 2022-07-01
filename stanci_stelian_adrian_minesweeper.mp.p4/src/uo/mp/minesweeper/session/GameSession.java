package uo.mp.minesweeper.session;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.log.SimpleLogger;

public class GameSession {

	private GameInteractor gameInt;
	private SessionInteractor sessionInt;
	private SimpleLogger logger;
	private GameRanking ranking;
	
	public GameSession() {
		
	}
	public void run() {
		try {
			play();
		}
		catch(RuntimeException e) { 
			handleSystemError(e);
		}
	}
	private void handleSystemError(RuntimeException e) {
		sessionInt.showFatalErrorMessage( e.getMessage() );
		logger.log(e);
	}
	
	private void play() {
		String name = sessionInt.askUsername();
		int res = sessionInt.askNextOption();
		while(res != 0) {
			res = processOption(res, name);
		}
		sessionInt.showGoodBye();
	}
	
	private int processOption(int opt, String name) {
		int res;
		if(opt == 1) {
			executeGame(name);
		}
		else if(opt == 2) {
			sessionInt.showPersonalRanking
			(ranking.getScoresFor(name));
		}
		else {
			sessionInt.showRanking(ranking.getGeneral());
		}
		res = sessionInt.askNextOption();
		return res;
		
	}
	private void executeGame(String name) {
		GameLevel level = sessionInt.askGameLevel();
		Board board = selectBoard(level);
		Game game = new Game(board);
		game.setInteractor(gameInt);
		game.play();
		if(game.isWon()) {
			if(sessionInt.doYouWantToRegisterYourScore()) {
				Score score = new Score(name, level, game.getTime(), true);
				ranking.append(score);
			}
		}
	}
	private Board selectBoard(GameLevel level) {
		Board board;
		switch(level) {
		case EASY:
			board = new Board (3, 3, 12);
			break;
		case MEDIUM:
			board = new Board (6, 6, 12);
			break;
		case HARD:
			board = new Board (9, 9, 12);
			break;
		default:
			board = new Board (6, 6, 12);
		}
		return board;
	}
	public void setSessionInteractor(SessionInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, "Error: invalid interactor");
		this.sessionInt = interactor;
	}
	
	public void setGameInteractor(GameInteractor interactor) {
		ArgumentChecks.isTrue(interactor != null, "Error: invalid interactor");
		this.gameInt = interactor;
	}
	
	public void setLogger(SimpleLogger logger) {
		ArgumentChecks.isTrue(logger != null, "Error: invalid interactor");
		this.logger = logger;
	}
	
	public void setGameRanking(GameRanking ranking) {
		ArgumentChecks.isTrue(ranking != null, "Error: invalid interactor");
		this.ranking = ranking;
	}
}
