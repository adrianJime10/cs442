package primePlay.util;

import primePlay.worker.RunMethods;
import primePlay.worker.RunMethodsI;

public interface ResultsI{

	public void store(Integer primeNum);

	public void sendData(RunMethodsI methods);

	public int getPrimeNumber(int position);

	public int getPrimeNumbersSize();

	public void signalEndOfFile();

	public boolean getParsingFinished();

	public int getSum();

	public int getCapacity();

}
