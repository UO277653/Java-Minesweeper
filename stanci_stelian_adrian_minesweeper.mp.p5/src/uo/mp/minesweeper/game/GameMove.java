package uo.mp.minesweeper.game;

public class GameMove {

	private char operation;
	private int row;
	private int column;
	
	/**
	 * Constructor of objects of the class GameMove
	 * @param operation to store
	 * @param row to store
	 * @param column to store
	 */
	public GameMove(char operation, int row, int column) {
		this.operation = operation;
		this.row = row;
		this.column = column;
	}

	public char getOperation() {
		return this.operation;
	}

	public int getRow() {
		return this.row;
	}

	public int getColumn() {
		return this.column;
	}

	@Override
	public String toString() {
		return "GameMove [operation=" + operation + ", row=" + row + ", column=" 
						+ column + " ]";
	}
	
}
