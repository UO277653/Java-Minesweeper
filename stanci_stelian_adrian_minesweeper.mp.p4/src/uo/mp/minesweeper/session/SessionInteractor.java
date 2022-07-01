package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.ranking.Score;

public interface SessionInteractor {

	GameLevel askGameLevel();
	
	String askUsername();
	
	int askNextOption();
	
	boolean doYouWantToRegisterYourScore();
	
	void showRanking(List<Score> ranking);
	
	void showPersonalRanking(List<Score> ranking);
	
	void showGoodBye();
	
	void showErrorMessage(String message);
	
	void showFatalErrorMessage(String message);
	
}
