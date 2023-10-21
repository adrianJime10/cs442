package numberPlay.subject;

import numberPlay.util.Event;
import numberPlay.observer.ObserverI;
import numberPlay.subject.Filter.FilterI;

public interface SubjectI{

	public void notifyAlll();

	public void registerObserver(ObserverI observer, FilterI f);

	public void processNum(String inString);

}
