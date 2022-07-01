package uo.mp.minesweeper.util.file;



import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;


/**
 * A utility class to read/write text lines 
 * from/to a compressed text file (.txt.gz) 
 */
public class ZipFileUtil extends AbstractFileUtil{

	@Override
	public BufferedReader getReaderChain(String inFileName)
			throws FileNotFoundException {
		
		BufferedReader res = null;
		try {
			res = new BufferedReader(new InputStreamReader 
					(new GZIPInputStream(new FileInputStream(inFileName))));
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return res;
	}

	@Override
	public BufferedWriter getWriterChain(String outFileName) {
		BufferedWriter res = null;
		
		try {
			res = new BufferedWriter(new OutputStreamWriter
					(new GZIPOutputStream(new FileOutputStream(outFileName))));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return res;
	}

}
