package statePlay.util;

import java.util.Map;
import statePlay.util.StateI;

public interface PersonI{

	public int getRunningAverage();
	public int getWindowSize();
	public Map<String,String> getItemCategories();
	public StateI getBasicState();
	public StateI getLuxuriousState();
	public StateI getExtravagantState();

}
