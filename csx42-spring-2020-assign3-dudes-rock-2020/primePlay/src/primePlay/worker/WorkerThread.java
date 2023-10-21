package primePlay.worker;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Integer;
import java.io.IOException;
import primePlay.util.FileProcessor;
import primePlay.util.IsPrime;
import primePlay.util.Results;
import primePlay.util.MyLogger;
import primePlay.util.IsPrimeI;
import primePlay.util.ResultsI;
import primePlay.worker.RunMethodsI;


public class WorkerThread implements Runnable {
	private FileProcessor processor;
	private IsPrimeI primeFinder;
	private ResultsI results;
	private RunMethodsI methods;
	private MyLogger logger = new MyLogger();

	public WorkerThread(FileProcessor processorIn, IsPrimeI primeFinderIn, ResultsI resultsIn, RunMethodsI methodsIn) {
		logger.writeMessage("WorkerThread constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		processor = processorIn;
		primeFinder = primeFinderIn;
		results = resultsIn;
		methods = methodsIn;
		//System.out.println("New worker thread");
	}

	@Override
	public void run() {
		logger.writeMessage("WorkerThread's run method called", MyLogger.DebugLevel.RUN);
		methods.runWorkerThread();
	}

	@Override
	public String toString() {
		return "This is the WorkerThread";
	}

	@Override
	public int hashCode() {
		return 4;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

}
