package visitorPlay.Util;

import visitorPlay.Util.Results;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import visitorPlay.Util.WordComparator;
import visitorPlay.Util.Word;

public class TopKFreqWordsResults implements Results{

	private BufferedWriter writer;
	private Queue<Word> mostFreqWords;
	private List<List<String>> wordFrequencies;

	public TopKFreqWordsResults(String topKOutputFilename){
		try{
			writer = new BufferedWriter(new FileWriter(topKOutputFilename, false));
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
		mostFreqWords = new PriorityQueue<Word>(new WordComparator());
		wordFrequencies = new ArrayList();
	}

	public void storeWord(Word word){
		mostFreqWords.add(word);
	}

	public Word pollWord(){
		return mostFreqWords.poll();
	}

	public Queue<Word> getMostFreqWords(){
		return mostFreqWords;
	}

	public void clearWords(){
		mostFreqWords.clear();
	}

	public void storeSentenceWordFreq(List<String> sentenceWords){
		wordFrequencies.add(sentenceWords);
	}

	@Override
	public void writeToFile(){
		Iterator<String> iterator;
		for(List<String> sentence : wordFrequencies){
			iterator = sentence.iterator();
			try{
				writer.write("[");
			}catch(IOException e){
				e.printStackTrace();
			}
			while(iterator.hasNext()){
				String word = iterator.next();
				if(iterator.hasNext()){
					try{
						writer.write(word+", ");
					}catch(IOException e){
						e.printStackTrace();
					}
				}else{
					try{
						writer.write(word);
					}catch(IOException e){
						e.printStackTrace();
					}
				}
			}
			try{
				writer.write("]\n");
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public int hashCode(){
		return 6;
	}

	@Override
	public String toString(){
		return "TopKFreqWordsResults";
	}

}
