package numberPlay.observer;

import numberPlay.util.Event;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.Queue;
import numberPlay.util.RunningAverageResultsI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.PersisterI;


public class RunningAverageObserver implements ObserverI{

	private Queue<Integer> window;
	private int runningSum;
	private RunningAverageResultsI runningAverageData;

	public RunningAverageObserver(int inWindowSize, String inOutputFileName){
		window = new ArrayBlockingQueue(inWindowSize);
		runningSum = 0;
		runningAverageData = new RunningAverageData(inOutputFileName);
	}

	public Queue<Integer> getWindow(){
		return window;
	}

	public int getRunningSum(){
		return runningSum;
	}

	public RunningAverageResultsI getRunningAverageData(){
		return runningAverageData;
	}

	@Override
	public void update(Event incomingEvent, Number num){
		if(incomingEvent == Event.PROCESSING_COMPLETE){
			runningAverageData.close();
			return;
		}else if(window.offer(num.intValue())){
			runningSum+=num.intValue();
		}
		else{
			runningSum -=window.poll();
			window.offer(num.intValue());
			runningSum +=num.intValue();
		}
		calculateRunningAverage();
		return;
	}

	private void calculateRunningAverage(){
		double d = Double.valueOf(runningSum) / Double.valueOf(window.size());
		runningAverageData.store(d);
		runningAverageData.writeToFile();
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(RunningAverageObserver other){
		if(other.getWindow() == window && other.getRunningSum() == runningSum && other.getRunningAverageData() == runningAverageData){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 8;
	}

}
