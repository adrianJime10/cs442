package primePlay.util;
import java.util.List;
import java.util.ArrayList;
import primePlay.worker.RunMethods;
import primePlay.util.MyLogger;
import primePlay.worker.RunMethodsI;

public class Results implements ResultsI{

	private List<Integer> primeNumbers = null;
	private int capacity;
	private boolean parsingFinished;
	private MyLogger logger = new MyLogger();
	private int sum  = 0;

	public Results(int capacityIn){
		logger.writeMessage("Results constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
		parsingFinished = false;
		capacity = capacityIn;
		primeNumbers = new ArrayList();
	}

	public void store(Integer primeNum) {
		primeNumbers.add(primeNum);
		logger.writeMessage(new String("Value " + primeNum.intValue() + " stored in results"), MyLogger.DebugLevel.ENTRY);
		//System.out.println("Data stored: " + primeNum.intValue());
		sum = sum + primeNum;
	}

	public void sendData(RunMethodsI methods){
		Thread sender = new Thread(new DataSender(this, methods));
		sender.start();
	}

	public int getPrimeNumber(int position){
		return primeNumbers.remove(position).intValue();
	}

	public int getPrimeNumbersSize(){
		return primeNumbers.size();
	}

	public void signalEndOfFile(){
		parsingFinished = true;
		return;
	}

	public boolean getParsingFinished(){
		return parsingFinished;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		String ret = "Current contents of the Results data structure: \n";
		for(int i = 0; i < primeNumbers.size(); i++) {
			ret = ret + primeNumbers.get(i).toString() + "\n";	
		}
		return ret;
	}

	public int getSum() {
		return sum;
	}

	@Override
	public int hashCode() {
		return capacity;
	}

	@Override
	public boolean equals(Object obj) {
		return this.hashCode() == obj.hashCode();
	}

}
