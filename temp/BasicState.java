package statePlay.util;

import statePlay.util.Person;
import statePlay.util.PersonI;
import java.util.Map;

public class BasicState implements StateI{

	PersonI person;

	public BasicState(PersonI personIn){
		person = personIn;
	}

	public StateI determineState(){
		if(person.getRunningAverage() < 10000){
			return person.getBasicState();
		}else if(10000 <= person.getRunningAverage() && person.getRunningAverage() <50000){
			return person.getLuxuriousState();
		}else{
			return person.getExtravagantState();
		}
	}

	public String makePurchase(String item){
		if(person.getItemCategories().get(item) == "basic"){
			return "YES";
		}else{
			return "NO";
		}
	}

}
