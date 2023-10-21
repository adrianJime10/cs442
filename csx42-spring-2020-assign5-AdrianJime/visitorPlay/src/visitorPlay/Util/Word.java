package visitorPlay.Util;

public class Word{

	private String word;
	private int frequency;

	public Word(String wordIn){
		word = wordIn;
		frequency = 0;
	}

	public int getFrequency(){
		return frequency;
	}

	public String getWord(){
		return word;
	}

	public void increaseFrequency(){
		frequency++;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof Word)) return false;
		Word oWord = (Word) o;
		return (oWord.frequency == frequency);
	}

	@Override
	public int hashCode(){
		return 8;
	}

	@Override
	public String toString(){
		return "Word";
	}
}
