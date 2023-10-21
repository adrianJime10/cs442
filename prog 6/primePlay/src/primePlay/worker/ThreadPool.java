package primePlay.worker;
import java.lang.Thread;
import java.util.List;
import java.util.ArrayList;
import primePlay.worker.WorkerThread;
import primePlay.util.FileProcessor;
import primePlay.util.IsPrimeI;
import primePlay.util.ResultsI;
import primePlay.util.MyLogger;
import primePlay.worker.RunMethodsI;

/**
 *
 * @author Adrian Jimenez, Evan Mcnierney
 *
 * The Threadpool class creates a pool of threads that are readily
 * available. This allows for threads to be serviced on demand without
 * needing to devote resources to create a thread object later on.
 *
 */

public class ThreadPool implements ThreadPoolI{

	/**
	 * The data structure in which the threads are stored
	 */
	private List<Thread> threads = new ArrayList();

	/**
	 * The maximum amount of threads allowed in the pool
	 */
	private int maxThreads;

	/**
	 * The logger which prints debug statements to the console.
	 */
	private MyLogger logger = new MyLogger();

	/**
	 * Creates a ThreadPool object
	 * @param maxThreadsIn sets the maxThreads variable
	 * @param processorIn assigns the file processor in each thread
	 * @param primeFinderIn assigns the IsPrime in each thread
	 * @param resultsIn assigns the Results in each thread
	 * @param methodsIn assigns the RunMethods in each thread
	 */
	public ThreadPool(int maxThreadsIn, FileProcessor processorIn, IsPrimeI primeFinderIn, ResultsI resultsIn, RunMethodsI methodsIn) {
		logger.writeMessage("ArrayList constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		logger.writeMessage("ThreadPool constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		maxThreads = maxThreadsIn;

		for(int i = 0; i < maxThreadsIn; i++) {
			Thread thread = new Thread(new WorkerThread(processorIn, primeFinderIn, resultsIn, methodsIn));

			threads.add(thread);
		}
	}

	/**
	 * Services a thread
	 */
	public Thread borrow() {
		if(threads.size() > 0) {
			return threads.remove(0);
		}
		else {
			System.err.println("ERROR: no more threads in pool");
			return null;
		}
	}

	/**
	 * Returns the maximum amount of threads allowed in the thread pool
	 */
	public int getMaxThreads() {
		return maxThreads;
	}

	/**
	 * Returns the number of threads currently available
	 */
	public int getNumThreads() {
		return threads.size();
	}

	/**
	 * Returns a String representation of this object.
	 */
	@Override
	public String toString() {
		return "This is the ThreadPool";
	}

	/**
	 * Returns the hashcode of this object.
	 */
	@Override
	public int hashCode() {
		return maxThreads;
	}

	/**
	 * Returns true if the hashcode of this object and the object
	 * passed in are equal
	 * @param obj The object to be comapared against
	 */
	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}	
}
