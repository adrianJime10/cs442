package primePlay.util;
import primePlay.util.MyLogger;

/**
 * @author Adrian Jimenez, Evan Mcnierney
 *
 * IsPrime is a utility class in charge of determining whether a
 * number from the input file is prime
 */

public class IsPrime implements IsPrimeI{

	/**
	 * The looger which prints debug statements to the console.
	 */
	private MyLogger logger = new MyLogger();

	/**
	 * Constructs an IsPrime object
	 */
	public IsPrime(){
		logger.writeMessage("IsPrime constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
	}
	
	/**
	 * Determines if the number is prime or not
	 * @param numIn The number read from the input file
	 */
	@Override
	public boolean isPrime(int numIn) {
		if(numIn < 2) {
			return false;
		}
		
		for(int i = 2; i < numIn; i++) {
			if(numIn % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns a string representation of this object.
	 */
	@Override
	public String toString() {
		return "This is the IsPrime object";
	}

	/**
	 * Returns the hashcode of this object.
	 */
	@Override
	public int hashCode() {
		return 2;
	}

	/**
	 * Returns true if the hashcode of this object and the object
	 * passed in are equal.
	 * @param obj the object to be compared against
	 */
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

}
