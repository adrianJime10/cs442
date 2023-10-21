package statePlay.util;

import statePlay.util.Person;
import statePlay.util.PersonI;
import java.util.Map;

public class LuxuriousState implements StateI{

	private PersonI person;

	public LuxuriousState(PersonI personIn){
		person = personIn;
	}

	@Override
	public StateI determineState(){
		if(10000 <= person.getRunningAverage() && person.getRunningAverage() <50000){
			return person.getLuxuriousState();
		}else{
			return person.getExtravagantState();
		}
	}

	@Override
	public String makePurchase(String item){
		if(person.getItemCategories().get(item).equals("basic") || person.getItemCategories().get(item).equals("moderatelyExpensive")){
			return "YES";
		}else{
			return "NO";
		}
	}

	@Override
	public String toString(){
		return "Luxurious State";
	}

	@Override
	public int hashCode(){
		return 5;
	}

}
