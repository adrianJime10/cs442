package numberPlay.observer;

import numberPlay.util.Event;
import java.util.List;
import java.util.ArrayList;
import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;

public class NumberPeaksObserver implements ObserverI{

	private List<Number> numList;
	private List<Number> peaksList;
	private NumberPeaksResultsI numPeaksData;

	public NumberPeaksObserver(String inOutputFile){
		peaksList = new ArrayList<>();
		numList = new ArrayList<>();
		numPeaksData = new NumberPeaksData(inOutputFile);
	}

	public List<Number> getNumList(){
		return numList;
	}

	public List<Number> getPeaksList(){
		return peaksList;
	}

	public NumberPeaksResultsI getNumPeaksData(){
		return numPeaksData;
	}
	
	@Override
	public void update(Event incomingEvent,Number num){
		if(incomingEvent == Event.PROCESSING_COMPLETE){
			numPeaksData.close();
			return;
		}else{
			numList.add(num);
			if(numList.size() > 1 && (numList.get(numList.size()-1).doubleValue() < numList.get(numList.size()-2).doubleValue())){
				peaksList.add(numList.get(numList.size()-2));
				numPeaksData.store(numList.get(numList.size()-2));
				numPeaksData.writeToFile();
			}
		}
		return;
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(NumberPeaksObserver other){
		if(other.getNumList() == numList && other.getPeaksList() == peaksList && other.getNumPeaksData() == numPeaksData){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 5;
	}

}
