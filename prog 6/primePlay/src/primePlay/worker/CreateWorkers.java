package primePlay.worker;
import primePlay.worker.WorkerThread;
import primePlay.worker.ThreadPool;
import primePlay.util.Results;
import primePlay.util.FileProcessor;
import primePlay.util.IsPrime;
import java.util.List;
import java.util.ArrayList;
import java.lang.InterruptedException;
import primePlay.util.MyLogger;
import primePlay.util.IsPrimeI;
import primePlay.util.ResultsI;
import primePlay.worker.RunMethodsI;
import primePlay.worker.ThreadPoolI;

public class CreateWorkers implements CreateWorkersI{
	private ThreadPoolI pool = null;
	private FileProcessor processor;
	private IsPrimeI primeFinder;
	private ResultsI results;
	private RunMethodsI methods;
	private MyLogger logger = new MyLogger();

	public CreateWorkers(FileProcessor processorIn, IsPrimeI primeFinderIn, ResultsI resultsIn, RunMethodsI methodsIn) {
		processor = processorIn;
		primeFinder = primeFinderIn;
		results = resultsIn;
		methods = methodsIn;
		logger.writeMessage("CreateWorkers constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		pool = new ThreadPool(5, processor, primeFinder, results, methods);
	}

	public void startWorkers(int numThreadsIn) {

		List<Thread> runningThreads = new ArrayList();
		logger.writeMessage("ArrayList constructor called", MyLogger.DebugLevel.CONSTRUCTOR);

		for(int i = 0; i < numThreadsIn; i++) {
			Thread thread = pool.borrow();
			runningThreads.add(thread);
			thread.start();
		}

		for(int i=0; i < runningThreads.size(); i++){
			try{
				runningThreads.get(i).join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		results.signalEndOfFile();

		//System.out.println("Num left in pool = " + pool.getNumThreads());
	}

	@Override
	public String toString() {
		return "This is the CreateWorkers object";
	}

	@Override
	public int hashCode() {
		return 5;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}
}
