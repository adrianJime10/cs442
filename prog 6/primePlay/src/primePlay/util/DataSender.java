package primePlay.util;

import primePlay.worker.RunMethods;
import primePlay.util.MyLogger;
import primePlay.util.ResultsI;
import primePlay.worker.RunMethodsI;


/**
*
* @author  Adrian Jimenez, Evan McNierney
*
*
* The DataSender class is in charge of creating a Thread object which
* is used to contact a server and send the prime numbers in it's 
* private ResultsI instance.
*/

public class DataSender implements Runnable{

	/**
	 * The instance of the Results interface which holds
	 * the prime numbers that were polled from the input file.
	 */
	private ResultsI results;
	/**
	 * The instance of the RunMethods interface which runs the 
	 * code that the run() methods calls. 
	 */
	private RunMethodsI methods;
	/**
	 * The logger which prints debug statements to the console.
	 */
	private MyLogger logger = new MyLogger();

	/**
	 * Constructs a DataSender object
	 * @param resultsIn sets the results private variable
	 * @param methodsIn sets the methods private variable
	 */
	public DataSender(ResultsI resultsIn, RunMethodsI methodsIn){
		logger.writeMessage("DataSender constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		results = resultsIn;
		methods = methodsIn;
	}

	/**
	 * Starts the thread 
	 */
	@Override
	public void run(){
		logger.writeMessage("DataSender's run method called", MyLogger.DebugLevel.RUN);
		methods.runDataSender();
	}

	/**
	 * Returns a String representation of this object.
	 */
	@Override
	public String toString() {
		return "This is the DataSender";
	}

	/**
	 * Returns the hashcode of this object.
	 */
	@Override
	public int hashCode() {
		return 1;
	}

	/**
	 * Returns true if the hashcode of this object and the object
	 * passed in are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
}
