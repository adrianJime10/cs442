package numberPlay.subject.Filter;

import numberPlay.util.Event;

public class IntegerFilter implements FilterI{

	public IntegerFilter(){
	}

	@Override
	public boolean check(Event e){
		if(e == Event.INTEGER_EVENT){
			return true;
		}
		return false;
	}

}
