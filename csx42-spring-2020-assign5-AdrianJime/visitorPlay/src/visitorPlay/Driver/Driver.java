package visitorPlay.Driver;

import visitorPlay.Util.MyArrayList;
import visitorPlay.Util.MyElement;
import visitorPlay.Util.Element;
import visitorPlay.Util.Visitor;
import visitorPlay.Util.FileProcessor;
import visitorPlay.Util.Results;
import visitorPlay.Util.SpellCheckResults;
import visitorPlay.Util.SpellCheckAnalyzer;
import visitorPlay.Util.TopKMostFreqAnalyzer;
import visitorPlay.Util.TopKFreqWordsResults;
import visitorPlay.Util.Validator;
import visitorPlay.Util.ValidatorI;
import java.lang.Integer;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;

public class Driver {
	private static void runAnalysis(FileProcessor fileProcessor, Visitor... visitors) {
		Element myArrayList = new MyArrayList.Builder()
			.withFileProcessor(fileProcessor)
			.build();
		
		for (Visitor visitor : visitors) {
			myArrayList.accept(visitor);
		}
	}

	private static void persistResults(Results... analysisResults) {
		for (Results results : analysisResults) {
			results.writeToFile();
		}
	}

	public static void main(String[] args) {
		// TODO command-line args validation.
		// TODO command-line parsing and initialization of following variables.
		// 	1. inputFilename.
		//	2. acceptableWordsFilename.
		// 	3. k.
		// 	4. topKOutputFilename.
		// 	5. spellCheckOutputFilename.

		String inputFilename = "${inputFile}";
		String acceptableWordsFilename = "${acceptableWordsFile}";
		int k = 0;
		String topKOutputFilename = "${topKOutputFile}";
		String spellCheckOutputFilename = "${spellCheckOutputFilename}";


		if((5!=args.length) || (args[0].equals("${inputFile}")) || (args[1].equals("${acceptableWordsFile}")) || (args[2].equals("${k}")) || (args[3].equals("${topKOutputFile}")) || args[4].equals("${spellCheckOutputFile}")){
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(1);
		}else{
			inputFilename = args[0];
			acceptableWordsFilename = args[1];
			k = Integer.parseInt(args[2]);
			topKOutputFilename = args[3];
			spellCheckOutputFilename = args[4];
		}

		ValidatorI validator = new Validator();
		validator.validateK(k);
		validator.validateInputFile(inputFilename);
		validator.validateAcceptableWordsFile(acceptableWordsFilename);

		FileProcessor fileProcessor = null;

		try{
			fileProcessor = new FileProcessor(inputFilename);
		}catch(InvalidPathException | SecurityException | IOException e){
			e.printStackTrace();
		}

		Results topKFreqWordsResults = new TopKFreqWordsResults(topKOutputFilename);
		Visitor topKMostFreqAnalyzer = new TopKMostFreqAnalyzer(k, topKFreqWordsResults);

		Results spellCheckResults = new SpellCheckResults(spellCheckOutputFilename);
		Visitor spellCheckAnalyzer = new SpellCheckAnalyzer(acceptableWordsFilename, spellCheckResults);

		runAnalysis(fileProcessor, topKMostFreqAnalyzer, spellCheckAnalyzer);

		persistResults(topKFreqWordsResults, spellCheckResults);
	}
}
