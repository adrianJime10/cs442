package numberPlay.subject.Filter;

import numberPlay.util.Event;
import java.util.List;

public interface FilterI{

	//public filterObservers(List<ObserverI> listeners, Event incomingEvent);

	public boolean check(Event e);

}
