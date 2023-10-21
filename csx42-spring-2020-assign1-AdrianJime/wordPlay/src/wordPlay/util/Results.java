package wordPlay.util;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.HashMap;
import java.lang.Character;

/**
 *The results class will compute the desired metrics for this program
 */
public class Results implements FileDisplayInterface, ResultsInterface{
	private ArrayList<String> wordsInInputFile;
	private String longestWord;
	private String outputFileName;
	private String metricsFileName;
	private String highestFrequencyWord;
	private int numSentences;
	private int numCharacters;
	private int numNewLineChars;
	private double averageCharsPerSentence;
	private double averageWordsPerSentence;
	private double numWords;
	private Map<String,Integer> wordFrequency;

	/**
	 *Overrides the toString() method for this class
	 *
	 *@param
	 *@return string Returns a string that reads "This is the results class"
	 */
	@Override
	public String toString(){
		return "This is the Results class";
	}

	/**
	 *Increments the numSentences variable
	 *
	 *@param
	 *@return void This method has no return value
	 */
	private void incrementNumSentences(){
		numSentences++;
		return;
	}

	/**
	 *Increments the numCharacters variable
	 *
	 *@return void This method has no return value
	 */
	private void incrementNumCharacters(){
		numCharacters++;
		return;
	}

	/**
	 *Increments the numNewLineChars variable
	 *
	 *@return void This method has no return value
	 */
	private void incrementNumNewLineChars(){
		numNewLineChars++;
		return;
	}
	
	/**
	 *Constructor
	 *
	 *@param wordsInInputFile (required) Stores each individual word
	 *@param outputFileName (required) Stores the output file name
	 *@param metricsFileName (required) Stores the metrics file name
	 */
	public Results(ArrayList<String> wordsInInputFile, String outputFileName, String metricsFileName){
		this.wordsInInputFile = wordsInInputFile;
		this.outputFileName = outputFileName;
		this.metricsFileName = metricsFileName;
		numSentences = 0;
		numCharacters = 0;
		numWords = 0;
		numNewLineChars = 0;
		wordFrequency = new HashMap<String,Integer>();
	}

	/**
	 *Begins processing the sentences from the input file
	 *
	 *@return void This method has no return value
	 */
	public void processSentences(){
		for (int i = 0; i < wordsInInputFile.size(); i++){
			if(wordsInInputFile.get(i).endsWith(".")){
				wordsInInputFile.set(i,wordsInInputFile.get(i).substring(0,(wordsInInputFile.get(i).length()-1)));
				wordsInInputFile.add(i+1, ".");
				i++;
				incrementNumSentences();
			}
		}
		updateWordFrequency();
	}

	/**
	 *Counts the frequency of each word in the input file
	 *
	 *@return void This method has no return value
	 */
	private void updateWordFrequency(){
		for( String word : wordsInInputFile){
			if(word.equals("\n")){
				incrementNumNewLineChars();
				continue;
			}
			if(wordFrequency.containsKey(word.toLowerCase())){
				wordFrequency.put(word.toLowerCase(), wordFrequency.get(word.toLowerCase())+1);
			}else{
				wordFrequency.put(word.toLowerCase(),1);
			}
		}
		getMostFrequentWord();
		return;
	}

	/**
	 *Retrieves the word with the highest frequency
	 *
	 *@return void This method has no return value
	 */
	private void getMostFrequentWord(){
		int highestValue = 0;
		String resultWord = "";
		for (String key : wordFrequency.keySet()){
			if(wordFrequency.get(key) > highestValue){
				highestValue = wordFrequency.get(key);
				resultWord = key;
			}
		}
		highestFrequencyWord = resultWord;
		reverseWords();
		return;
	}

	/**
	 *Reverses the words in the input file
	 *
	 *@return void This method has no return value
	 */
	private void reverseWords(){
		int longestWordLength = 0;
		for(int i = 0; i < wordsInInputFile.size(); i++){
			String reversedWord = "";

			if(wordsInInputFile.get(i).length() > longestWordLength){
				longestWord = wordsInInputFile.get(i);
				longestWordLength = wordsInInputFile.get(i).length();
			}

			for(int j = wordsInInputFile.get(i).length()-1; j >-1; j--){
				if(wordsInInputFile.get(i).equals(".") || wordsInInputFile.get(i).equals(",")){
					reversedWord = wordsInInputFile.get(i);
					incrementNumCharacters();
					break;
				}else if(wordsInInputFile.get(i).equals("\n")){
					reversedWord = wordsInInputFile.get(i);
					incrementNumCharacters();
					break;
				}else if(!Character.isLetterOrDigit(wordsInInputFile.get(i).charAt(j))){
					System.out.println("Words can only contain alphanumeric characters. Exiting...");
					System.exit(0);
				}
				reversedWord = reversedWord + wordsInInputFile.get(i).charAt(j);
				incrementNumCharacters();
			}
			wordsInInputFile.set(i, reversedWord);
		}
		averageMetrics();
	}

	/**
	 *Calculates the average words per sentence metric
	 *
	 *@return void This method has no return value
	 */
	private void calculateAverageWordsPerSentence(){
		averageWordsPerSentence = (double)(numWords-numNewLineChars) / (double)numSentences;
		return;
	}

	/**
	 *Calculates the averages characters per sentence metric
	 *
	 *@return void This method has no return value
	 */
	private void calculateAverageCharsPerSentence(){
		averageCharsPerSentence = (double)(numCharacters - (numNewLineChars*2)) / (double)numSentences;
	}

	/**
	 *Calculates the total number of characters in the input file
	 *
	 *@return void This method has no return value
	 */
	private void calculateNumCharacters(){
		numCharacters += wordsInInputFile.size() - numSentences - 1;
	}

	/**
	 *Calculates the total number of words in the input file
	 *
	 *@return void This method has no return value
	 */
	private void calculateNumWords(){
		numWords = (wordsInInputFile.size() - numSentences);
	}

	/**
	 *Calls the metrics methods and file output methods
	 *
	 *@return void This method has no return value
	 */
	private void averageMetrics(){

		calculateNumCharacters();
		calculateNumWords();

		calculateAverageCharsPerSentence();
		calculateAverageWordsPerSentence();

		writeOutputFile();
		writeMetricsFile();
	}

	/**
	 *Begins writing the reversed words to the output file
	 *
	 *@return void This method has no return value
	 */
	@Override
	public void writeOutputFile(){
		try{
			FileWriter writer = new FileWriter(outputFileName);			

			for(int i = 0; i < wordsInInputFile.size(); i++){
				if(wordsInInputFile.get(i).equals("\n")){
					writer.write(wordsInInputFile.get(i));
				}else if(i+1 < wordsInInputFile.size()){
					if(wordsInInputFile.get(i+1).equals(".")){
						writer.write(wordsInInputFile.get(i));
						continue;
					}
					writer.write(wordsInInputFile.get(i) + " ");
				}else{
					writer.write(wordsInInputFile.get(i));
				}
			}
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Program exiting...");
			System.exit(0);
		}
	}

	/**
	 *Begins writing the metrics to the metrics output file
	 *
	 *@return void This method has no return value
	 */
	@Override
	public void writeMetricsFile(){
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.DOWN);
		try{
			FileWriter writer = new FileWriter(metricsFileName);			

			writer.write("AVG_NUMBER_WORDS_PER_SENTENCE = " + df.format(averageWordsPerSentence)+"\n");
			writer.write("AVG_NUM_CHARS_PER_SENTENCE = " + df.format(averageCharsPerSentence)+"\n");
			writer.write("MAX_FREQ_WORD = "+ highestFrequencyWord + "\n");
			writer.write("LONGEST_WORD = " + longestWord + "\n");
			writer.close();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Program exiting...");
			System.exit(0);
		}
	}
	
}
