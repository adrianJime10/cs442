package statePlay.util;

import java.util.Map;
import statePlay.util.StateI;

public interface PersonI{

	public void calculateRunningAverage();
	public int getCurrentWindowSize();
	public StateI getCurrentState();
	public int getRunningAverage();
	public int getWindowSize();
	public Map<String,String> getItemCategories();
	public StateI getBasicState();
	public StateI getLuxuriousState();
	public StateI getExtravagantState();
	public void readAvailableItems();
	public void readInputFile();

}
