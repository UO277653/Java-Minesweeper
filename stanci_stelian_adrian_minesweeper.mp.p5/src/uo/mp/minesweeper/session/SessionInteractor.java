package uo.mp.minesweeper.session;

import java.util.List;

import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.util.exceptions.InteractionException;

public interface SessionInteractor {

	/**
	 * Ask the user to type path to the file. Must return a non-empty String.
	 * @return String which is the name of the file
	 */
	String askFileName();
	
	/**
	 * It asks the user for a level of difficulty and returns the answer with a 
	 * GameLevel object.
	 * @return GameLevel
	 */
	GameLevel askGameLevel();
	
	/**
	 * Prompts the user for a name and returns a String with the answer,
	 * which cannot be neither null nor empty.
	 * @return username the user has entered
	 */
	String askUsername();
	
	/**
	 * It asks the user to choose an option from the menu. It returns an integer 
	 * representing the selection. A value greater than zero will represent some 
	 * of the available actions. A zero value will always represent the exit 
	 * option.
	 * @return number of the option
	 */
	int askNextOption();
	
	/**
	 * At the end of a game, it asks the user if he wants to save his score. 
	 * Returns true if the answer is affirmative and false otherwise.
	 * @return boolean true if the user wants to register the score, false
	 * otherwise
	 */
	boolean doYouWantToRegisterYourScore();
	
	/**
	 * It receives a list of Score objects representing all the scores recorded
	 * in the system and displays all the information about them all (tabular
	 * format, one line for each score).
	 * @param scores of the ranking
	 */
	void showRanking(List<Score> ranking);
	
	/**
	 * It receives a list of Score objects representing all the scores recorded
	 * in the system and displays all the information about them all (tabular 
	 * format, one line for each score) except username (it is understood that 
	 * it is the user stored in the session).
	 * @param scores in the ranking
	 */
	void showPersonalRanking(List<Score> ranking);
	
	/**
	 * It displays a farewell message when user chooses to end the session.
	 */
	void showGoodBye();
	
	/**
	 * It displays an error message, received as a parameter. 
	 * This kind of errors don’t stop execution (recoverable errors).
	 * @param message of the error
	 */
	void showErrorMessage(String message);
	
	/**
	 * It displays serious error messages to the user. This method has to be 
	 * used to report that the error is unrecoverable and the program will end 
	 * its execution immediately.
	 * @param message of the error
	 */
	void showFatalErrorMessage(String message);

	/**
	 * Exports the ranking provided as a parameter to a file whose name
	 * is introduced by the user
	 * @param List of scores which is the ranking to be exported
	 */
	void exportRanking(List<Score> ranking);

	/**
	 * Imports the ranking from the file name specified by the user.
	 * Deletes the previous one
	 * @param Game ranking that is going to be modified
	 */
	void importRanking(GameRanking gameRanking) throws InteractionException;
}
