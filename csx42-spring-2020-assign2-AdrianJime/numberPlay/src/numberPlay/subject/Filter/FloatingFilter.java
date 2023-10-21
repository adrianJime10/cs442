package numberPlay.subject.Filter;

import java.util.List;
import numberPlay.util.Event;

public class FloatingFilter implements FilterI{

	public FloatingFilter(){
	}

	@Override
	public boolean check(Event e){
		if(e == Event.FLOATING_POINT_EVENT){
			return true;
		}
		return false;
	}
/*
	public filterObservers(List<ObserverI> listeners, Event incomingEvent){
		if(mostRecentEvent.getKey() == Event.INTEGER_EVENT){
			listeners[1].listen(mostRecentEvent.getKey());
			listeners[0].listen(mostRecentEvent.getKey());
			listeners[2].listen(mostRecentEvent.getKey());
		}else if(mostRecentEvent.getKey() == Event.FLOATING_POINT_EVENT){
			listeners[0].listen(mostRecentEvent.getKey());
			listeners[2].listen(mostRecentEvent.getKey());
		}else{
			listeners[0].listen(mostRecentEvent.getKey());
			listeners[1].listen(mostRecentEvent.getKey());
			listeners[2].listen(mostRecentEvent.getKey());
		}
		return;
	}
*/
}
