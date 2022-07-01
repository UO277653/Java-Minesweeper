package uo.mp.minesweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.ArgumentChecks;

public class Board {
	private Square[][] board;
	private int mines;
	private boolean isBlownUp;
	
	/**
	 * Constructor for objects of type Board. Initializes the board
	 * and fills it with mines.
	 * @param width of the board
	 * @param height of the board
	 * @param percentage of the mines
	 */
	public Board(int width, int height, int percentage) {
		board = new Square[height][width];
		mines = (width * height * percentage)/100;
		fillBoard(mines);
		putClear();
		setBlownUp(false);
	}

	public void uncoverWelcomeArea() {
		Random random = new Random();
		boolean isUncovered = false;
		int x = 0;
		int y = 0;
		while(!isUncovered && !isEveryTileCorner()) {
			x = random.nextInt(board.length);
			y = random.nextInt(board[0].length);
			while(isCorner(x, y)) {
				x = random.nextInt(board.length);
				y = random.nextInt(board[0].length);
			}
			if(board[x][y].getValue() == 0) {
				board[x][y].stepOn();
				isUncovered = true;
			}
		}
		
		
	}

	private boolean isEveryTileCorner() {
		if (rows() == 1 && cols() == 1) {
			return true;
		}
		if (rows() == 1 && cols() == 2) {
			return true;
		}
		if (rows() == 2 && cols() == 1) {
			return true;
		}
		if (rows() == 2 && cols() == 2) {
			return true;
		}
		return false;
	}

	private boolean isCorner(int x, int y) {
		if( x == 0 && y == 0 ) {
			return true;
		}
		if( x == board.length-1 && y == 0 ) {
			return true;
		}
		if( x == 0 && y == board.length-1 ) {
			return true;
		}
		if( x == board[0].length-1 && y == board.length-1 ) {
			return true;
		}
		return false;
	}

	/**
	 * Constructor for testing purposes
	 * @param mines
	 * @param squares
	 */
	public Board(int mines, Square[][] squares) {
		this.mines = mines;
		board = squares;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				updateContents(i, j);
			}
		}
		putClear();
		setBlownUp(false);
	}
	
	/*
	 * Initializes the clear actions in the empty squares of the board
	 */
	private void putClear() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].getValue() == 0) {
					board[i][j].setAction(new ClearAction
												(getAdyacentSquares(i, j)));
				}
			}
		}
	}

	/*
	 * Returns a list with all the squares available adyacent to the 
	 * coordinates provided
	 */
	private List<Square> getAdyacentSquares(int x, int y) {
		List<Square> aux = new ArrayList<Square>();
		if(ArgumentChecks.isValidCoord(x-1, y-1, board)) {
			aux.add(board[x-1][y-1]);
		}
		if(ArgumentChecks.isValidCoord(x, y-1, board)) {
			aux.add(board[x][y-1]);
		}
		if(ArgumentChecks.isValidCoord(x+1, y-1, board)) {
			aux.add(board[x+1][y-1]);
		}
		if(ArgumentChecks.isValidCoord(x-1, y, board)) {
			aux.add(board[x-1][y]);
		}
		if(ArgumentChecks.isValidCoord(x+1, y, board)) {
			aux.add(board[x+1][y]);
		}
		if(ArgumentChecks.isValidCoord(x-1, y+1, board)) {
			aux.add(board[x-1][y+1]);
		}
		if(ArgumentChecks.isValidCoord(x, y+1, board)) {
			aux.add(board[x][y+1]);
		}
		if(ArgumentChecks.isValidCoord(x+1, y+1, board)) {
			aux.add(board[x+1][y+1]);
		}
		return aux;
	}

	
	/**
	 * Returns true if a hidden mine was revealed, false otherwise
	 * @return boolean true if a hidden mine was revealed, false otherwise.
	 */
	public boolean isExploded() {
		/*
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].hasMine() && board[i][j].isOpen()) {
					return true;
				}
			}
		}
		return false;
		*/
		return this.isBlownUp;
	}
	
	/**
	 * Reveals the square at the specified coordinates if it is hidden
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void stepOn(int x, int y) {
		if(!ArgumentChecks.isValidCoord(x, y, board)) {
			throw new IllegalArgumentException("Invalid coordinates");
		}
		if(!board[x][y].isOpen() && !board[x][y].hasFlag()) {
			board[x][y].stepOn();
		}
	}
	
	/**
	 * Puts a flag in the coordinates specified
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void flag(int x, int y) {
		if(!ArgumentChecks.isValidCoord(x, y, board)) {
			throw new IllegalArgumentException("Invalid coordinates");
		}
		if(!board[x][y].isOpen() && !board[x][y].hasFlag()) {
			board[x][y].flag();
		}
	}
	
	/**
	 * Remvoves the flag in the coordinates specified, if any
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void unflag(int x, int y) {
		if(!ArgumentChecks.isValidCoord(x, y, board)) {
			throw new IllegalArgumentException("Invalid coordinates");
		}
		if(board[x][y].hasFlag()) {
			board[x][y].unflag();
		}
	}
	
	/**
	 * Reveals every tile in the board
	 */
	public void unveil() {
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j].open();
			}
		}
	}
	
	/**
	 * Returns the number of flags left to use
	 * @return number of flags left to use
	 */
	public int getFlagsLeft() {
		int counter = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].hasFlag()) {
					counter++;
					}
				}
			}
		return this.mines-counter;
	}
	
	/**
	 * Returns the number of mines left unmarked
	 * @return number of mines left
	 */
	public int getMinesLeft() {
		int counter = 0;
		if(isCleared()) {
			return 0;
		}
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(!board[i][j].hasFlag() && board[i][j].hasMine()) {
					counter++;
					}
				}
			}
		return counter;
	}
	
	/*
	 * Checks if there is any tile without mine closed left
	 */
	private boolean isCleared() {
		int counter = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(!board[i][j].isOpen()) {
					counter++;
					}
				}
			}
		int mineCount = 0;
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j].hasMine()) {
					mineCount++;
					}
				}
			}
		return counter==mineCount;
	}
	
	/**
	 * Sets the state of the board as exploded
	 */
	public void markAsExploded() {
		this.setBlownUp(true);
	}
	
	private void setBlownUp(boolean isBlownUp) {
		this.isBlownUp = isBlownUp;
	}
	
	/**
	 * Returns a 2d array of chars representing the state of the board
	 * @return [][] array of chars representing the state of the board
	 */
	public char[][] getStatus(){
		char[][] aux = new char[board.length] [board[0].length];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				aux[i][j] = board[i][j].toString().charAt(0);
			}
		}
		return aux;
	}
	
	/**
	 * Returns the internal array of squares of the board
	 * @return the internal array of squares of the board
	 */
	public Square[][] getSquaresForTest(){
		return board;
	}
	
	/**
	 * 	Returns number of rows in the board
	 * @return number of rows in the board
	 */
	public int rows() {
		return board.length;
	}
	
	/**
	 * Returns number of columns in the board
	 * @return number of columns in the board
	 */
	public int cols() {
		return board[0].length;
	}
	
	/*
	 * Initializes the squares on the board, puts the mines in random locations
	 * and creates the BlowUpActions in those squares
	 */
	private void fillBoard(int mines) {
		Random random = new Random();
		int minesLeft = mines;
		int auxX = 0;
		int auxY = 0;
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j]=new Square();
			}
		}
		
		while(minesLeft>0) {
			auxX = random.nextInt(board.length);
			auxY = random.nextInt(board[0].length);
			
			if(!board[auxX][auxY].hasMine()) {
				board[auxX][auxY].putMine();
				board[auxX][auxY].setAction(new BlowUpAction(this));
				updateContents(auxX, auxY);
				minesLeft--;
			}
		}
	}

	/*
	 * Updates the content of the adyacent squares, in case in the square
	 * of the coordinates provided there is a mine
	 */
	private void updateContents(int x, int y) {
		if(board[x][y].hasMine()) {
			if(ArgumentChecks.isValidCoord(x-1, y-1, board)) {
				if(!board[x-1][y-1].hasMine()) {
					board[x-1][y-1].setValue(board[x-1][y-1].getValue()+1);
					board[x-1][y-1].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x, y-1, board)) {
				if(!board[x][y-1].hasMine()) {
					board[x][y-1].setValue(board[x][y-1].getValue()+1);
					board[x][y-1].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x+1, y-1, board)) {
				if(!board[x+1][y-1].hasMine()) {
					board[x+1][y-1].setValue(board[x+1][y-1].getValue()+1);
					board[x+1][y-1].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x-1, y, board)) {
				if(!board[x-1][y].hasMine()) {
					board[x-1][y].setValue(board[x-1][y].getValue()+1);
					board[x-1][y].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x+1, y, board)) {
				if(!board[x+1][y].hasMine()) {
					board[x+1][y].setValue(board[x+1][y].getValue()+1);
					board[x+1][y].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x-1, y+1, board)) {
				if(!board[x-1][y+1].hasMine()) {
					board[x-1][y+1].setValue(board[x-1][y+1].getValue()+1);
					board[x-1][y+1].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x, y+1, board)) {
				if(!board[x][y+1].hasMine()) {
					board[x][y+1].setValue(board[x][y+1].getValue()+1);
					board[x][y+1].setAction(new NullAction());
				}
			}
			if(ArgumentChecks.isValidCoord(x+1, y+1, board)) {
				if(!board[x+1][y+1].hasMine()) {
					board[x+1][y+1].setValue(board[x+1][y+1].getValue()+1);
					board[x+1][y+1].setAction(new NullAction());
				}
			}
		}
		
	}
}
