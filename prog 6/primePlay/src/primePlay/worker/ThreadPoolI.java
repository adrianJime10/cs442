package primePlay.worker;

import java.lang.Thread;

public interface ThreadPoolI{

	public Thread borrow();
	
	public int getMaxThreads();

	public int getNumThreads();

}
