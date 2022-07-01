package uo.mp.minesweeper.console;

import java.util.List;
import java.util.Scanner;

import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.ArgumentChecks;

public class ConsoleSessionInteractor implements SessionInteractor {

	private Scanner reader;
	
	/**
	 * 
	 */
	public ConsoleSessionInteractor() {
		reader = new Scanner( System.in );
	}
	
	/**
	 * It asks the user for a level of difficulty and returns the answer with a 
	 * GameLevel object.
	 * @return
	 */
	@Override
	public GameLevel askGameLevel() {
		System.out.println("Level of difficulty? (e)asy, (m)edium, (h)igh");
		char c = reader.next().toLowerCase().charAt(0);
		ArgumentChecks.isTrue(!((c == 's') || (c == 'f') || (c == 'u')), 
				"Error: invalid difficulty");
		
		GameLevel res = decideGameLevel(c);
		
		return res;
	}

	private GameLevel decideGameLevel(char c) {
		if(c == 'e') {
			return GameLevel.EASY;
		}
		if(c == 'm') {
			return GameLevel.MEDIUM;
		}
		if(c == 'h') {
			return GameLevel.HARD;
		}
		return null;
	}

	/**
	 * Prompts the user for a name and returns a String with the answer,
	 * which cannot be neither null nor empty.
	 * @return
	 */
	@Override
	public String askUsername() {
		System.out.println("Name?");
		String name = reader.next();
		ArgumentChecks.isTrue(name != null, "Error: invalid name");
		ArgumentChecks.isTrue(!name.trim().isEmpty(), "Error: empty name");
		
		return name;
	}

	/**
	 * It asks the user to choose an option from the menu. It returns an integer 
	 * representing the selection. A value greater than zero will represent some 
	 * of the available actions. A zero value will always represent the exit 
	 * option.
	 * @return 
	 */
	@Override
	public int askNextOption() {
		showOptions();
		
		int option = reader.nextInt();
		
		return option;
	}

	private void showOptions() {
		System.out.println("Available options:");
		System.out.println("  1 - Play a new game");
		System.out.println("  2 - Show my results");
		System.out.println("  3 - Show all results");
		System.out.println("  0 - Exit");
		System.out.println("Option?");
	}

	/**
	 * At the end of a game, it asks the user if he wants to save his score. 
	 * Returns true if the answer is affirmative and false otherwise.
	 * @return 
	 */
	@Override
	public boolean doYouWantToRegisterYourScore() {
		System.out.println("Do you want to register your score? (y)es, (n)o");
		
		String option = reader.next(".");
		
		ArgumentChecks.isTrue(option != null, "Error: invalid option");
		ArgumentChecks.isTrue(!option.trim().isEmpty(), "Error: empty option");
		
		if(option.equals("y")) {
			return true;
		}
		else if(option.equals("n")) {
			return false;
		}
		else {
			throw new IllegalArgumentException("Error: invalid option");
		}
		
		
	}

	/**
	 * It receives a list of Score objects representing all the scores recorded
	 * in the system and displays all the information about them all (tabular
	 * format, one line for each score).
	 * @param
	 */
	@Override
	public void showRanking(List<Score> ranking) {
		System.out.println("User     .Date     .Hour      .Level .Res .Time");
		for(Score score: ranking) {
			System.out.print("" + score.getUsername() + " " + score.getDate() +
					" " + score.getLevel() + " " + getResult(score) + " " +
					score.getTime());
			System.out.println();
		}
		
	}
	
	private String getResult(Score score) {
		if(score.hasWon()) {
			return "won";
		}
		return "lost";
	}

	/**
	 * It receives a list of Score objects representing all the scores recorded
	 * in the system and displays all the information about them all (tabular 
	 * format, one line for each score) except username (it is understood that 
	 * it is the user stored in the session).
	 * @param
	 */
	@Override
	public void showPersonalRanking(List<Score> ranking) {
		System.out.println(".Date     .Hour      .Level .Res .Time");
		for(Score score: ranking) {
			System.out.print(score.getDate() + " " + score.getLevel() + " " + 
					getResult(score) + " " + score.getTime());
			System.out.println();
		}
		
	}

	/**
	 * It displays a farewell message when user chooses to end the session.
	 */
	@Override
	public void showGoodBye() {
		System.out.println("Thanks for your session. Bye, bye!");
	}

	/**
	 * It displays an error message, received as a parameter. 
	 * This kind of errors don’t stop execution (recoverable errors).
	 * @param
	 */
	@Override
	public void showErrorMessage(String message) {
		System.out.println("ERROR: " + message);
	}

	/**
	 * It displays serious error messages to the user. This method has to be 
	 * used to report that the error is unrecoverable and the program will end 
	 * its execution immediately.
	 * @param
	 */
	@Override
	public void showFatalErrorMessage(String message) {
		System.out.println("FATAL ERROR: " + message);
	}

}
