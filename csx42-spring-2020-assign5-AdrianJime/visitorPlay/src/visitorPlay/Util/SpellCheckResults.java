package visitorPlay.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import visitorPlay.Util.Results;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SpellCheckResults implements Results{

	private List<String> acceptableWords;
	private Map<String,String> misspelledWords;
	private BufferedWriter writer;

	public SpellCheckResults(String spellCheckOutputFilename){
		acceptableWords = new ArrayList();
		misspelledWords = new HashMap();
		try{
			writer = new BufferedWriter(new FileWriter(spellCheckOutputFilename,false));
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
	}

	public List<String> getAcceptableWords(){
		return acceptableWords;
	}

	public void storeAcceptableWords(String word){
		acceptableWords.add(word);
	}

	public void storeMisspelledWord(String word, String acceptableWord){
		misspelledWords.put(word,acceptableWord);
	}

	@Override
	public void writeToFile(){
		for(Map.Entry keyElement : misspelledWords.entrySet()){
			String key = (String) keyElement.getKey();
			String value = (String) keyElement.getValue();
			try{
				writer.write(key + "::[" + value + "]\n");
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
		return 5;
	}

	@Override
	public String toString(){
		return "SpellCheckResults";
	}

}
