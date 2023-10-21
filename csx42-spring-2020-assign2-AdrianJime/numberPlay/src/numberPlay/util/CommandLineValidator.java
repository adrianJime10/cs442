package numberPlay.util;

import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.lang.Double;

public class CommandLineValidator implements Validator{

	private String inputFile;
	private String windowSize;
	private String avgOutFile;
	private String kNums;
	private String topKOutFile;
	private String numPeaksOutFile;

	public CommandLineValidator(String inInputFile, String inWindowSize, String inAvgOutFile, String inKNums, String inTopKOutFile, String inNumPeaksOutFile){
		inputFile = inInputFile;
		windowSize = inWindowSize;
		avgOutFile = inAvgOutFile;
		kNums = inKNums;
		topKOutFile = inTopKOutFile;
		numPeaksOutFile = inNumPeaksOutFile;
	}

	public String getInputFile(){
		return inputFile;
	}

	public String getWindowSize(){
		return windowSize;
	}

	public String getAvgOutFile(){
		return avgOutFile;
	}

	public String getKNums(){
		return kNums;
	}

	public String getTopKOutFile(){
		return topKOutFile;
	}

	public String getNumPeaksOutFile(){
		return numPeaksOutFile;
	}

	@Override
	public void run() throws NumberFormatException, IllegalArgumentException{
		try{
			Integer.parseInt(kNums);
			Integer.parseInt(windowSize);
		}catch(NumberFormatException e){
			e.printStackTrace();
		}finally{}

		if(Integer.parseInt(windowSize) < 1){
			try{
				throw new IllegalArgumentException("Invalid window size");
			}catch(IllegalArgumentException e){
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(Integer.parseInt(kNums) < 1){
			try{
				throw new IllegalArgumentException("Invalid k");
			}catch(IllegalArgumentException e){
				e.printStackTrace();
				System.exit(0);
			}
		}

		if(inputFile.equals("") || inputFile == null){
			try{
				throw new IllegalArgumentException("Illegal input file path");
			}catch(IllegalArgumentException e){
				e.printStackTrace();
				System.exit(0);
			}
		}
		return;

	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(CommandLineValidator other){
		if(other.getInputFile() == inputFile && other.getWindowSize() == windowSize && other.getAvgOutFile() == avgOutFile && other.getKNums() == kNums && other.getTopKOutFile() == topKOutFile && other.getNumPeaksOutFile() == numPeaksOutFile){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 2;
	}

}
