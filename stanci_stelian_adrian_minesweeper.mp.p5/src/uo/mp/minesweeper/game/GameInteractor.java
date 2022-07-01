package uo.mp.minesweeper.game;

public interface GameInteractor {

	/**
	 * It asks the user to type an operation as well as a row and a column.
	 * @param number of rows of the board
	 * @param number of columns of the board
	 * @return GameMove object containing the information introduced.
	 */
	GameMove askMove(int rows, int cols);
	
	/**
	 * It shows the user the state of the game including board, elapsed time 
	 * and flags still unplaced.
	 * @param time that has passed since first turn
	 * @param board in which is being played
	 */
	void showGame(long elapsedTime, Board board);
	
	/**
	 * It informs the player the game is over.
	 */
	void showGameFinished();
	
	/**
	 * It tells the user that he wins and shows him the time spent playing.
	 * @param time that has passed since first turn
	 */
	void showCongratulations(long elapsedTime);
	
	/**
	 * It tells the user he/she has hit a mine.
	 */
	void showBooommm();
	
	/**
	 * It tells the user the game is ready to start.
	 */
	void showReadyToStart();
}
