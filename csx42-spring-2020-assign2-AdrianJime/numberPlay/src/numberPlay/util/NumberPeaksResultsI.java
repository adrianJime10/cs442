package numberPlay.util;

/**
* NumberPeaksResultsI defines an interface to be implemented by 
* classes that intend to store peaks in the input number stream.
*/
public interface NumberPeaksResultsI extends PersisterI{
	void store(Number num);
	void close();
	void writeToFile();
}
