package uo.mp.minesweeper.console;

import java.util.Scanner;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.ArgumentChecks;

public class ConsoleGameInteractor implements GameInteractor {

	private Scanner reader;
	
	public ConsoleGameInteractor() {
		reader = new Scanner( System.in );
	}
	
	/**
	 * It asks the user to type an operation as well as a row and a column.
	 * @param number of rows of the board
	 * @param number of columns of the board
	 * @return GameMove object containing the information introduced.
	 */
	@Override
	public GameMove askMove(int rows, int cols) {
		
		System.out.println("Movement (s | f | u)?:");
		char c = reader.next(".").toLowerCase().charAt(0);
		ArgumentChecks.isTrue((c == 's') || (c == 'f') || (c == 'u'), "Error: "
				+ "invalid " + "action");
		System.out.println("Row?: ");
		int coord1 = reader.nextInt();
		ArgumentChecks.isTrue(coord1 < rows, "Error: number of row exceeds the"
				+ " limit");
		System.out.println("Column?: ");
		int coord2 = reader.nextInt();
		ArgumentChecks.isTrue(coord2 < cols, "Error: number of column exceeds "
				+ "the limit");
		GameMove res = new GameMove(c, coord1, coord2);
		return res;
	}

	/**
	 * It shows the user the state of the game including board, elapsed time 
	 * and flags still unplaced.
	 * @param time that has passed since first turn
	 * @param board in which is being played
	 */
	@Override
	public void showGame(long elapsedTime, Board board) {
		char[][] aux = board.getStatus();
		System.out.println("Time: " + elapsedTime + " seconds");
		System.out.println("Flags left: " + board.getFlagsLeft());
		printBoard(aux);
		
	}

	/**
	 * It informs the player the game is over.
	 */
	@Override
	public void showGameFinished() {
		System.out.println("The game has finished");
	}

	/**
	 * It tells the user that he wins and shows him the time spent playing.
	 * @param time that has passed since first turn
	 */
	@Override
	public void showCongratulations(long elapsedTime) {
		System.out.println("Congratulations! You won!" + 
				" Time spent: " + elapsedTime);
		
	}

	/**
	 * It tells the user he/she has hit a mine.
	 */
	@Override
	public void showBooommm() {
		System.out.println("BOOM!");
	}

	/**
	 * It tells the user the game is ready to start.
	 */
	@Override
	public void showReadyToStart() {
		System.out.println("Ready to start");
		
	}
	
	private void printBoard(char[][] board) {
		System.out.print("    ");
		printHeader(board);
		printRowSep(board);
		
		for(int i = 0; i<board.length; i++) {
			System.out.print(i + " | ");
			for(int j = 0; j<board.length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			printRowSep(board);
		}
	}
	
	private void printRowSep(char[][] board) {
		System.out.print("  +");
		for(int i = 0; i<board.length; i++) {
			System.out.print("---+");
		}
		System.out.println();
	}
	private void printHeader(char[][] board) {
		for(int i = 0; i<board.length; i++) {
			System.out.print(i + "   ");
		}
		System.out.println();
	}

}
