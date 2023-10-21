package primePlay.util;

public interface ValidatorI {
	public boolean fileEmpty(String filename);
	public boolean validateCapacity(String capacityStr);
	public boolean validatePort(String portStr);
	public boolean validateNumThreads(String numThreadsStr);
	public void validateInt(String str);
}
