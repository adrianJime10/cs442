package primePlay.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import primePlay.util.MyLogger;

import java.util.List;

public final class FileProcessor {
	private BufferedReader reader;
	private String line;
	private MyLogger logger = new MyLogger();

	public FileProcessor(String inputFilePath) 
		throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		logger.writeMessage("FileProcessor constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		if (!Files.exists(Paths.get(inputFilePath))) {
			throw new FileNotFoundException("invalid input file or input file in incorrect location");
		}

		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
		line = reader.readLine();
	}

	public String poll() throws IOException {
		if (null == line) return null;
	
		String newValue = line.trim();
		line = reader.readLine();

		if(null != line) {
			try {
				int val = Integer.parseInt(line);
			}catch(NumberFormatException e) {
				System.err.println(e);
				e.printStackTrace();
				System.exit(1);
			}
		}

		return newValue;
	}

	public void close() throws IOException {
		try {
			reader.close();
			line = null;
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}

	public String getLine() {
		return line;
	}	

	@Override
	public String toString() {
		return "This is the file processor.";
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

	@Override 
	public int hashCode() {
		return line.hashCode();
	}
}
