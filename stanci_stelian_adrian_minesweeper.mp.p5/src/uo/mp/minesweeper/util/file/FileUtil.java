package uo.mp.minesweeper.util.file;

import java.io.BufferedReader;     
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A utility class to read/write text lines from/to a text file
 */
public class FileUtil extends AbstractFileUtil {
	
	@Override
	public BufferedReader getReaderChain(String inFileName) 
			throws FileNotFoundException {
		BufferedReader res = null;
		
		res = new BufferedReader(new FileReader(inFileName));
		
		return res;
	}

	@Override
	public BufferedWriter getWriterChain(String outFileName) {
		BufferedWriter res = null;
		try {
			res = new BufferedWriter(new FileWriter(outFileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

}
