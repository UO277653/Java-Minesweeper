package uo.mp.minesweeper.util.log;

import java.io.BufferedWriter; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uo.mp.minesweeper.util.InputChecks;

public class FileLogger implements SimpleLogger{

	private static String fileName = "minesweeper.log";
	private static BufferedWriter out;
	
	/**
	 * Constructor of objects of class FileLogger
	 * @param fileName
	 */
	public FileLogger(String fileName) {
		setFilename(fileName);
	}
	
	private void setFilename(String fileName) {
		InputChecks.checkString(fileName);
		FileLogger.fileName = fileName;
	}
	
	/**
	 * Writes a message of an error
	 * @param message to be written
	 */
	public static void log(String message) {
		
		String date = new SimpleDateFormat("dd/MM/yy").format
				( new Date() );
		String time = new SimpleDateFormat("HH:mm:ss").format
				( new Date() );
		try {
			out = new BufferedWriter(new FileWriter(fileName, true));
			out.write( "[" + date +  " - " + time + "]: ");
			out.write( message + "\n"); 
			
		} catch (FileNotFoundException e1) {
			throw new RuntimeException("File was not found");
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				throw new RuntimeException("IOException");
			}
		}
	}
	
	/**
	 * Writes the message contained in the exception in a file
	 * @param exception that contains the message
	 */
	@Override
	public void log(Exception ex) {
		
		String date = new SimpleDateFormat("dd/MM/yy").format
				( new Date() );
		String time = new SimpleDateFormat("HH:mm:ss").format
				( new Date() );
		try {
			out = new BufferedWriter(new FileWriter(fileName, true));
			out.write( "[" + date +  " - " + time + "]: ");
			out.write( ex.getMessage() + "\n"); 
			
		} catch (FileNotFoundException e1) {
			throw new RuntimeException("File was not found");
		} catch (IOException e) {
			throw new RuntimeException("IOException");
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				throw new RuntimeException("IOException");
			}
		}
	}

}
