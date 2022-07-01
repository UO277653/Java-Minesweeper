package uo.mp.minesweeper.session;

import java.util.Collections;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Game;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.exceptions.InteractionException;
import uo.mp.minesweeper.util.log.SimpleLogger;
import uo.mp.minesweeper.util.sort.ScoreComparator;

public class GameSession {

	private static final int EXIT_VALUE = 0;
	private GameInteractor gameInt;
	private SessionInteractor sessionInt;
	private SimpleLogger logger;
	private GameRanking ranking;

	/**
	 * Constructor of objects of type GameSession
	 */
	public GameSession() {

	}

	/**
	 * Initializes the program
	 */
	public void run() {
		play();
	}

	private void handleUserError(Exception e) {
		sessionInt.showErrorMessage(e.getMessage());
	}

	private void handleSystemError(RuntimeException e) {
		sessionInt.showFatalErrorMessage(e.getMessage());
		logger.log(e);
	}

	private void play() {
		String name = sessionInt.askUsername();
		int res = sessionInt.askNextOption();
		while(res != EXIT_VALUE) {
			try {
				res = processOption(res, name);
			}
			catch (RuntimeException e) {
				handleSystemError(e);
				return;
			} catch (Exception e) {
				handleUserError(e);
			}
		}
		
		sessionInt.showGoodBye();
	}

	private int processOption(int opt, String name) 
			throws InteractionException {
		int res;
		switch (opt) {
		case 0:
			return 0;
		case 1:
			executeGame(name);
			break;
		case 2:
			sortRanking();
			sessionInt.showPersonalRanking(ranking.getScoresFor(name));
			break;
		case 3:
			sortRanking();
			sessionInt.showRanking(ranking.getGeneral());
			break;
		case 4:
			sessionInt.exportRanking(ranking.getGeneral());
			break;
		case 5:
			sessionInt.importRanking(ranking);
			break;
		default:
			sessionInt.showErrorMessage("Please enter a valid number");
			break;
		}
		res = sessionInt.askNextOption();
		return res;

	}

	private void sortRanking() {
		Collections.sort(ranking.getGeneral(), new ScoreComparator());
	}

	private void executeGame(String name) {
		GameLevel level = sessionInt.askGameLevel();
		Board board = selectBoard(level);
		Game game = new Game(board);
		game.setInteractor(gameInt);
		game.play();
		if (game.isWon()) {
			if (sessionInt.doYouWantToRegisterYourScore()) {
				Score score = new Score(name, level, game.getTime(), true);
				ranking.append(score);
			}
		}
	}

	private Board selectBoard(GameLevel level) {
		Board board;
		switch (level) {
		case EASY:
			board = new Board(4, 4, 13);
			break;
		case MEDIUM:
			board = new Board(6, 6, 12);
			break;
		case HARD:
			board = new Board(9, 9, 12);
			break;
		default:
			board = new Board(6, 6, 12);
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
