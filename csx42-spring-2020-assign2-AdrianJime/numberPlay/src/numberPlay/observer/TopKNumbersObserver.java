package numberPlay.observer;

import java.util.PriorityQueue;
import java.util.Queue;
import numberPlay.util.Event;
import numberPlay.util.TopKNumbersResultsI;
import numberPlay.util.TopKNumbersData;

public class TopKNumbersObserver implements ObserverI{

	private Queue<Double> topKNumbers;
	private TopKNumbersResultsI topKNumbersData;
	private int queueSize;
	
	public TopKNumbersObserver(int inQueueSize, String inOutputFile){
		topKNumbers = new PriorityQueue<Double>();
		topKNumbersData = new TopKNumbersData(inOutputFile);
		queueSize = inQueueSize;
	}

	public Queue<Double> getTopKNumbers(){
		return topKNumbers;
	}

	public TopKNumbersResultsI getTopKNumbersData(){
		return topKNumbersData;
	}

	public int getQueueSize(){
		return queueSize;
	}

	@Override
	public void update(Event incomingEvent, Number num){
		if(incomingEvent == Event.PROCESSING_COMPLETE){
			topKNumbersData.close();
			return;
		}else if(topKNumbers.size() == queueSize){
			topKNumbers.poll();
			topKNumbers.add(num.doubleValue());
		}else{
			topKNumbers.add(num.doubleValue());
		}
		topKNumbersData.store(topKNumbers);
		topKNumbersData.writeToFile();
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(TopKNumbersObserver other){
		if(other.getTopKNumbers() == topKNumbers && other.getTopKNumbersData() == topKNumbersData && other.getQueueSize() == queueSize){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 7;
	}
}
