package wordPlay.util;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

public class FileProcessor implements FileProcessorInterface{
	private int c;
	private String inputFileName;
	private String outputFileName;
	private String metricsFileName;
	private ArrayList<String> wordsInInputFile;

	public String toString(){
		return "This is the FileProcessor Class";
	}
	
	public FileProcessor(String inputFileName, String outputFileName, String metricsFileName){
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		this.metricsFileName = metricsFileName;
		wordsInInputFile = new ArrayList<String>();
	}
	
	private void testEmptyFile(File file){
		if(file.length() == 0){
			System.out.println("File cannot be empty. Exiting...");
			System.exit(0);
		}
		return;
	}

	public void fileIO(){
		
		try{
			File file = new File(inputFileName);
			testEmptyFile(file);
			Scanner scanner = new Scanner(file);
			while(scanner.hasNext()){
				String line = scanner.nextLine();
				String[] wordsArr = line.split(" ");
				for (String word : wordsArr){
					wordsInInputFile.add(word);
				}
				wordsInInputFile.add("\n");
			}

		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("Program exiting...");
			System.exit(0);
		}

		Results metrics = new Results(wordsInInputFile, outputFileName, metricsFileName);
		metrics.processSentences();
	}
}
