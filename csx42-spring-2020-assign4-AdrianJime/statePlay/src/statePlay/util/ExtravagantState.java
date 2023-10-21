package statePlay.util;

import statePlay.util.Person;
import statePlay.util.PersonI;
import java.util.Map;

/**
 * @author Adrian Jimenez
 *
 * ExtravagantState is a utility class that determines
 * the spending state of a person
 */

public class ExtravagantState implements StateI{

	/**
	 * The person whose spending state is being monitored
	 */
	private PersonI person;

	/**
	 * Constructs an ExtravagantState object
	 */
	public ExtravagantState(PersonI personIn){
		person = personIn;
	}

	/**
	 * Determines the person's state based on their income
	 */
	@Override
	public StateI determineState(){
		return person.getExtravagantState();
	}

	/**
	 * Determines if the person can make the purchase based on
	 * their spending state
	 */
	@Override
	public String makePurchase(String item){
		return "YES";
	}

	/**
	 * Returns a string representation of this object
	 */
	@Override
	public String toString(){
		return "Extravagant State";
	}

	/**
	 * Returns the hashcode of this object
	 */
	@Override
	public int hashCode(){
		return 6;
	}

}
