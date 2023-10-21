package primePlay.worker;

import java.io.IOException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import primePlay.util.FileProcessor;
import primePlay.util.Results;
import primePlay.util.IsPrime;
import primePlay.util.MyLogger;
import primePlay.util.ResultsI;
import primePlay.util.IsPrimeI;

public class RunMethods implements RunMethodsI {
	private FileProcessor processor;
	private IsPrimeI primeFinder;
	private ResultsI results;
	private MyLogger logger = new MyLogger();
	private String address;
	private int port;

	public RunMethods(FileProcessor processorIn, IsPrimeI primeFinderIn, ResultsI resultsIn, String addressIn, int portIn) {
		logger.writeMessage("RunMethods constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		processor = processorIn;
		primeFinder = primeFinderIn;
		results = resultsIn;
		address = addressIn;
		port = portIn;
	}

	public void runWorkerThread() {
		String line = "default";
		
		while(null != line){
			//System.out.println("This is thread" + Thread.currentThread().getId());
			//synchronized(this){
				try{
					synchronized(this){
					line = processor.poll();
					//System.out.println("Polled: " + line);
					}
				}catch(IOException e){
					e.printStackTrace();
				}
				if(null != line){
					Integer numberParsed = new Integer(line);
					if(primeFinder.isPrime(numberParsed.intValue())){
						synchronized(this){											try{
								while(results.getPrimeNumbersSize() == results.getCapacity()) {	
									//System.out.println("Thread " + Thread.currentThread().getId() + " waiting...");
									wait();
								}
								//else {
								//	notifyAll();
								//}
							}catch(InterruptedException e){
								e.printStackTrace();
							}
							results.store(numberParsed);
							logger.writeMessage(results.toString(),MyLogger.DebugLevel.RESULTS);
						}
						//System.out.println(numberParsed.intValue());
					}
				}
				//notifyAll();
			//}
		}
		//results.signalEndOfFile();
		//System.out.println("End signaled!");
	}

	public void runDataSender() {
		//System.out.println("Data sender running");
		int position = 0;
		
		Socket socket = null;
		DataOutputStream out = null;

		try{
			socket = new Socket(address, port);
			//System.out.println("Connected");

			out = new DataOutputStream(socket.getOutputStream());
		}catch(IOException e){
			e.printStackTrace();
		}//catch(IOException e){
		//	e.printStackTrace();
		//}


			while(/*!results.getParsingFinished()*/true){
				if(position >= results.getPrimeNumbersSize() && results.getParsingFinished()){
					//System.out.println("Data sender signing off!");
					break;
				}

				while(position >= results.getPrimeNumbersSize() && !results.getParsingFinished()){

				continue;
				}
				if(0 == results.getPrimeNumbersSize() && !results.getParsingFinished()){
					synchronized(this){
					//System.out.println("Data sender waiting...");
					//System.out.println("Results size: " + results.getPrimeNumbersSize());
					try{
						wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
					}
				}

				

				if(position < results.getPrimeNumbersSize()){
				synchronized(this){

				try{
					out.writeInt(results.getPrimeNumber(position));
				}catch(IOException e){
					e.printStackTrace();
				}

				notifyAll();
				}
				}
			}
			try{
				out.writeInt(0);
			}catch(IOException e){
				e.printStackTrace();
			}

		try{
			out.close();
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "This is the RunMethods object";
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
