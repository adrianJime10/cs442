package numberPlay.util;

import java.util.List;
import java.util.Queue;

/**
* TopKNumbersResultsI defines an interface to be implemented by
* classes that intend to store the top K numbers when processing
* a stream of numbers.
*/
public interface TopKNumbersResultsI extends PersisterI{
	void store(Queue<Double> topK);
	void close();
	void writeToFile();
}
