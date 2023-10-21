package statePlay.util;

import statePlay.util.Person;
import statePlay.util.PersonI;
import java.util.Map;

/**
 * @author Adrian Jimenez
 *
 * BasicState is a utility class which defines a
 * person's spending ability based on their income
 */

public class BasicState implements StateI{

	/**
	 * The person whose state is being monitored
	 */
	private PersonI person;

	/**
	 * Constructs a BasicState object
	 * @param personIn The person whose state is
	 * being monitored
	 */
	public BasicState(PersonI personIn){
		person = personIn;
	}

	/**
	 * Determines the spending state of the person
	 * based on their income
	 */
	@Override
	public StateI determineState(){
		if(person.getRunningAverage() < 10000){
			return person.getBasicState();
		}else if(10000 <= person.getRunningAverage() && person.getRunningAverage() <50000){
			return person.getLuxuriousState();
		}else{
			return person.getExtravagantState();
		}
	}

	/**
	 * Determines if the person can make the purchse
	 * based on their spending state
	 */
	@Override
	public String makePurchase(String item){
		if(person.getItemCategories().get(item).equals("basic")){
			return "YES";
		}else{
			return "NO";
		}
	}

	/**
	 * Returns a string representation of this object.
	 */
	@Override
	public String toString(){
		return "Basic State";
	}

	/**
	 * Returns the hashcode of this object.
	 */
	@Override
	public int hashCode(){
		return 4;
	}

}
