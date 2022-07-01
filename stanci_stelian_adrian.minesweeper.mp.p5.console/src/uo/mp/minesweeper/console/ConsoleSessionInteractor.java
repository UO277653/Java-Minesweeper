package uo.mp.minesweeper.console;

import java.io.BufferedWriter;  
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import uo.mp.minesweeper.ranking.GameRanking;
import uo.mp.minesweeper.ranking.Score;
import uo.mp.minesweeper.session.GameLevel;
import uo.mp.minesweeper.session.SessionInteractor;
import uo.mp.minesweeper.util.InputChecks;
import uo.mp.minesweeper.util.exceptions.InteractionException;
import uo.mp.minesweeper.util.file.FileUtil;
import uo.mp.minesweeper.util.file.parser.RankingParser;
import uo.mp.minesweeper.util.file.serializer.ScoreSerializer;
import uo.mp.minesweeper.util.log.FileLogger;

public class ConsoleSessionInteractor implements SessionInteractor {

	private Scanner reader;
	
	/**
	 * Constructor of objects of the class ConsoleSessionInteractor
	 */
	public ConsoleSessionInteractor() {
		reader = new Scanner( System.in );
	}
	
	/**
	 * It asks the user for a level of difficulty and returns the answer with a 
	 * GameLevel object.
	 * @return GameLevel
	 */
	@Override
	public GameLevel askGameLevel() {
		System.out.println("Level of difficulty? (e)asy, (m)edium, (h)igh");
		
		char c = reader.next().toLowerCase().charAt(0);
		
		GameLevel res = decideGameLevel(c);
		
		return res;
	}

	private GameLevel decideGameLevel(char c) {
		switch(c) {
		case 'e':
			return GameLevel.EASY;
		case 'm':
			return GameLevel.MEDIUM;
		case 'h':
			return GameLevel.HARD;
		default:
			return askGameLevel();
		}
	}

	/**
	 * Prompts the user for a name and returns a String with the answer,
	 * which cannot be neither null nor empty.
	 * @return username the user has entered
	 */
	@Override
	public String askUsername() {
		System.out.println("Please, enter your username: ");
		
		String name = reader.next();
		
		InputChecks.checkString(name);
		
		return name;
	}

	/**
	 * It asks the user to choose an option from the menu. It returns an integer 
	 * representing the selection. A value greater than zero will represent some 
	 * of the available actions. A zero value will always represent the exit 
	 * option.
	 * @return number of the option
	 */
	@Override
	public int askNextOption() {
		showOptions();
		
		int option = 0;
		
		try {
			option = reader.nextInt();
		} catch (InputMismatchException e){
			reader = new Scanner(System.in);
			return -1;
		}
		
		return option;
	}

	private void showOptions() {
		System.out.println("\nAvailable options:");
		System.out.println("  1 - Play a new game");
		System.out.println("  2 - Show my results");
		System.out.println("  3 - Show all results");
		System.out.println("  4 - Export results");
		System.out.println("  5 - Import results");
		System.out.println("  0 - Exit");
		System.out.println("Option?");
	}

	/**
	 * At the end of a game, it asks the user if he wants to save his score. 
	 * Returns true if the answer is affirmative and false otherwise.
	 * @return boolean true if the user wants to register the score, false
	 * otherwise
	 */
	@Override
	public boolean doYouWantToRegisterYourScore() {
		System.out.println("Do you want to register your score? (y)es, (n)o");
		
		String option = reader.next(".");
		
		InputChecks.checkString(option);
		
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
	 * @param scores of the ranking
	 */
	@Override
	public void showRanking(List<Score> ranking) {
		System.out.println("User     .Date     .Hour      .Level .Res .Time");
		for(Score score: ranking) {
			System.out.print("" + score.getUsername() + "     " + 
					new SimpleDateFormat("dd/MM/yy  HH:mm:ss").format
					( score.getDate() ) +
					"    " + score.getLevel() + "  " + 
					getResult(score) + "   " + score.getTime());
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
	 * @param scores in the ranking
	 */
	@Override
	public void showPersonalRanking(List<Score> ranking) {
		System.out.println(".Date     .Hour      .Level .Res .Time");
		for(Score score: ranking) {
			System.out.print(new SimpleDateFormat("dd/MM/yy  HH:mm:ss").format
					( score.getDate() ) + "    " + score.getLevel() + "  " + 
					getResult(score) + "   " + score.getTime());
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
	 * @param message of the error
	 */
	@Override
	public void showErrorMessage(String message) {
		System.out.println("ERROR: " + message);
	}

	/**
	 * It displays serious error messages to the user. This method has to be 
	 * used to report that the error is unrecoverable and the program will end 
	 * its execution immediately.
	 * @param message of the error
	 */
	@Override
	public void showFatalErrorMessage(String message) {
		System.out.println("FATAL ERROR: " + message);
	}

	/**
	 * Ask the user to type path to the file. Must return a non-empty String.
	 * @return String which is the name of the file
	 */
	@Override
	public String askFileName() {
		System.out.print("Please, specify the name of the file: ");
		
		String name = reader.next();
		
		InputChecks.checkString(name);
		
		return name;
	}

	/**
	 * Exports the ranking provided as a parameter to a file whose name
	 * is introduced by the user
	 * @param List of scores which is the ranking to be exported
	 */
	@Override
	public void exportRanking(List<Score> ranking) {
		String fileName = askFileName();
		BufferedWriter writer = null;
		ScoreSerializer serializer = new ScoreSerializer();
		
		List<String> res = serializer.serialize(ranking);
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			for(String line: res) {
				writer.write(line);
			}
			writer.close();
		} catch (IOException e) {
			FileLogger.log(e.getMessage());
			throw new RuntimeException("IOException while writing ranking"
					+ " to a file, please contact the programmer. \n");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				FileLogger.log(e.getMessage());
				throw new RuntimeException("Error while closing the file \n");
			}
		  }
		}

	/**
	 * Imports the ranking from the file name specified by the user.
	 * Deletes the previous one
	 * @param Game ranking that is going to be modified
	 */
	@Override
	public void importRanking(GameRanking gameRanking) 
			throws InteractionException {
		String file = askFileName();
		
		gameRanking.clear();
		List<String> scores;
		List<Score> parsedScores = null;
		try {
			FileUtil reader = new FileUtil();
			scores = reader.readLines(file);
			parsedScores = new RankingParser().parse(scores);
		} catch (FileNotFoundException e) {
			throw new InteractionException("The file specified was not found. "
					+ "Please try again with a valid file name  \n");
		}
		for(Score score: parsedScores) {
			gameRanking.append(score);
		}
	  }	
	}


