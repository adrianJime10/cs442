package visitorPlay.Util;

import visitorPlay.Util.Word;
import java.util.Comparator;

public class WordComparator implements Comparator<Word>{

	@Override
	public int compare(Word word1, Word word2){
		int w1Freq = word1.getFrequency();
		int w2Freq = word2.getFrequency();
		if(w1Freq == w2Freq){
			return 0;
		}
		if(w1Freq < w2Freq){
			return 1;
		}
		return -1;
	}

	@Override
	public int hashCode(){
		return 9;
	}

	@Override
	public String toString(){
		return "WordComparator";
	}
}
