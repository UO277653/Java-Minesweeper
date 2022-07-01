package uo.mp.minesweeper.util.file;

import java.io.BufferedReader;  
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public abstract class AbstractFileUtil {
	
	public List<String> readLines(String inFileName) 
			throws FileNotFoundException {
		
		List<String> res = new LinkedList<>();
		String line = null;
		BufferedReader br = getReaderChain(inFileName);
		
		try {
			try {
			line = br.readLine();
			while(line != null) {
				res.add(line);
				line = br.readLine();
			}
		
		} finally {
			br.close();
		}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
		
		return res;
	}
	
	public void writeLines(String outFileName, List<String> lines){
		
		BufferedWriter bw = getWriterChain(outFileName);
		
		try {
			try {
			for(String line: lines)
				if(line != null) {
					bw.write(line + "\n");
				}
		
		} finally {
			bw.close();
		}
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
	
	public abstract BufferedReader getReaderChain(String inFileName) 
			throws FileNotFoundException;
	
	public abstract BufferedWriter getWriterChain(String outFileName);
}
