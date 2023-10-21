package visitorPlay.Util;

import visitorPlay.Util.Visitor;
import visitorPlay.Util.FileProcessor;
import visitorPlay.Util.Element;
import visitorPlay.Util.Results;
import visitorPlay.Util.MyElement;
import visitorPlay.Util.Word;
import visitorPlay.Util.TopKFreqWordsResults;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrian Jimenez
 *
 * TopKMostFreqAnalyzer is a utility class which determines
 * the top k most frequent words in a sentence
 */

public class TopKMostFreqAnalyzer implements Visitor{

	/**
	 * The results object that will be storing and persisting
	 * information generated by this object
	 */
	private Results topKFreqWordsResults;

	/**
	 * The number of most frequent words to be displayed
	 */
	private int k;
	

	/**
	 * Constructs a TopKMostFreqAnalyzer object
	 * @param kIn The number of most frequent words to be
	 * displayed
	 * @param topKFreqWordsResultsIn The results object that
	 * will be stored in this object
	 */
	public TopKMostFreqAnalyzer(int kIn, Results topKFreqWordsResultsIn){
		topKFreqWordsResults = topKFreqWordsResultsIn;
		k = kIn;
	}

	/**
	 * Visits an element and determines the top k most
	 * frequent words within that sentence
	 */
	@Override
	public void visit(Element element){
		TopKFreqWordsResults results = (TopKFreqWordsResults) topKFreqWordsResults;
		results.clearWords();
		MyElement myElement = (MyElement) element;
		String[] words = myElement.getSentence().split(" ");
		for(String word : words){
			word = word.toLowerCase();
			if(word.endsWith(".")){
				word = word.substring(0,word.length()-1);
			}
			Word newWord = new Word(word);
			for(String wordToCheck : words){
				if(word.equals(wordToCheck)){
					newWord.increaseFrequency();
				}
			}
			if(!results.getMostFreqWords().contains(newWord)){
				results.storeWord(newWord);
			}
		}
		String wordToPrint;
		Word wordToPull;
		List<String> sentenceWords = new ArrayList();
		for(int i=0; i<k;i++){
			if(results.getMostFreqWords().size() == 0){
				break;
			}
			wordToPull = results.pollWord();
			wordToPrint = wordToPull.getWord();
			sentenceWords.add(wordToPrint);
		}
		results.storeSentenceWordFreq(sentenceWords);

	}

	/**
	 * Returns the hashcode of this object
	 */
	@Override
	public int hashCode(){
		return 7;
	}

	/**
	 * Returns a string representation of this object
	 */
	@Override
	public String toString(){
		return "TopKMostFreqAnalyzer";
	}
}

