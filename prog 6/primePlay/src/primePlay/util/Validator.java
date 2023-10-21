package primePlay.util;

import java.io.File;
import java.lang.NumberFormatException;

public class Validator implements ValidatorI{

	@Override
	public boolean fileEmpty(String filename) {
		File file = new File(filename);
		//System.out.println("Length = " + file.length());
		boolean ret = file.length() == 1;
		return ret;
	}

	@Override
	public boolean validateCapacity(String capacityStr) {
		validateInt(capacityStr);
		int capacity = Integer.parseInt(capacityStr);
		return capacity > 0;
	}

	@Override
	public boolean validatePort(String portStr) {
		validateInt(portStr);
		int port = Integer.parseInt(portStr);
		return port > 32768 && port < 50000;
	}

	@Override
	public boolean validateNumThreads(String numThreadsStr) {
		validateInt(numThreadsStr);
		int numThreads = Integer.parseInt(numThreadsStr);
		return numThreads > 0 && numThreads <= 5;
	}

	@Override
	public void validateInt(String str) {
		try {

			int num = Integer.parseInt(str);
		} catch(NumberFormatException e) {
			System.err.println(e);
			e.printStackTrace();
			System.exit(1);
		}
	}
}

