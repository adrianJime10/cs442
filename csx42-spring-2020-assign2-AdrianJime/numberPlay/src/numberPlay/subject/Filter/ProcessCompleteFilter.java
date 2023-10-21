package numberPlay.subject.Filter;

import numberPlay.util.Event;

public class ProcessCompleteFilter implements FilterI{

	public ProcessCompleteFilter(){
	}

	@Override
	public boolean check(Event e){
		if(e == Event.PROCESSING_COMPLETE){
			return true;
		}
		return false;
	}
}
