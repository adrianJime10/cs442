package statePlay.util;

import statePlay.util.BasicState;
import statePlay.util.StateI;
import java.util.Map;
import java.util.HashMap;
import statePlay.util.PersonI;

public class Person implements PersonI{

	Map<String, String> itemCategories;
	StateI basicState, luxuriousState, extravagantState;
	StateI currentState;

	int windowSize;
	int runningAverage = 0;

	public Person(int windowSizeIn){
		itemCategories = new HashMap<String,String>();
		basicState = new BasicState(this);
		currentState = basicState;
		windowSize = windowSizeIn;
	}

	public int getWindowSize(){
		return windowSize;
	}

	public int getRunningAverage(){
		return runningAverage;
	}

	public Map<String,String> getItemCategories(){
		return itemCategories;
	}

	public StateI getBasicState(){
		return basicState;
	}

	public StateI getLuxuriousState(){
		return luxuriousState;
	}

	public StateI getExtravagantState(){
		return extravagantState;
	}



}
