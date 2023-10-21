package numberPlay.subject;

import numberPlay.util.Event;
import numberPlay.observer.ObserverI;
import java.lang.Number;
import java.lang.Integer;
import java.lang.Float;
import java.util.List;
import java.util.ArrayList;
import numberPlay.subject.Filter.FilterI;
import java.util.Map;
import java.util.HashMap;

/**
 *Author: Adrian Jimenez
 *
 *The NumberProcessor class will act as the subject in the Observer pattern
 *and will broadcast the most recent event and number read from the input
 *file
 */

public class NumberProcessor implements SubjectI{

	private Event mostRecentEvent;
	private Number mostRecentNumber;
	private Map<FilterI, List<ObserverI>> listeners;
	
	/**
	 *Constructor
	 *
	 *Takes no parameters, allocates spaces for the map listeners
	 */
	public NumberProcessor(){
		listeners = new HashMap<>();
	}
	
	/**
	 *Gets the most recent event
	 *
	 *@return Event returns the most recent event
	 */
	public Event getMostRecentEvent(){
		return mostRecentEvent;
	}

	/**
	 *Gets the most recent number
	 *
	 *@return Number returns the most recent number read from input file
	 */
	public Number getMostRecentNumber(){
		return mostRecentNumber;
	}

	/**
	 *Gets the active listeners and their respective filters
	 *
	 *@return Map<FilterI, List<ObserverI>> returns the mapping of filters
	 *to listeners
	 */
	public Map<FilterI, List<ObserverI>> getListeners(){
		return listeners;
	}

	/**
	 *Registers observers with a respective filter in the listeners hashmap
	 *
	 *@param ObserverI o (required) The observer that is to be registered
	 *@param FilterI f (required) The filter that will be registered with
	 *its corresponding observer
	 *
	 *@return void This method has no return value
	 */
	@Override
	public void registerObserver(ObserverI o, FilterI f){
		if(listeners.containsKey(f)){
			listeners.get(f).add(o);
		}else{
			listeners.put(f, new ArrayList<ObserverI>());
			listeners.get(f).add(o);
		}
		return;
	}

	/**
	 *Notifies all observers with the most recent event and number read
	 *
 	 *@return void This method has no return value
	 */
	@Override
	public void notifyAlll(){
		for(FilterI f : listeners.keySet()){
			for(ObserverI obv : listeners.get(f)){
				if(f.check(mostRecentEvent)){
					obv.update(mostRecentEvent,mostRecentNumber);
				}
			}
		}
		return;
	}

	/**
	 *Determines which event should be sent to the observers based on the
	 *type of number read from input file
	 *
	 *@param String inString (required) The number read in from the input
	 *file
	 *
	 *@return void This method has no return value
	 */
	@Override
	public void processNum(String inString){
		if(inString.contains(".")){
			mostRecentNumber = new Float(inString);
			mostRecentEvent = Event.FLOATING_POINT_EVENT;
		}else if(inString.equals("done")){
			mostRecentNumber = new Integer("0");
			mostRecentEvent = Event.PROCESSING_COMPLETE;
		}else{
			mostRecentNumber = new Integer(inString);
			mostRecentEvent = Event.INTEGER_EVENT;
		}
		return;
	}

	/**
	 *Overrides the toString method for this class
	 *
	 *@return String returns an inspiring little character
	 */
	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	/**
	 *Tests the NumberProcessor this object is being compared with for equality
	 *
	 *@param NumberProcessor other (required) The object that is being compared
	 *against this one for equality
	 *
	 *@return boolean returns true if they are equal, false otherwise
	 */
	public boolean equals(NumberProcessor other){
		if(other.getMostRecentEvent() == mostRecentEvent && other.getMostRecentNumber() == mostRecentNumber && other.getListeners() == listeners){
			return true;
		}
		return false;
	}

	/**
	 *Overrides the hashCode method
	 *
	 *@return int returns 3 for this class' hashCode
	 */
	@Override
	public int hashCode(){
		return 3;
	}
}
