package visitorPlay.Util;

import java.io.File;
import visitorPlay.Util.ValidatorI;

public class Validator implements ValidatorI{

	public Validator(){}

	@Override
	public void validateK(int k){
		if(k <=0){
			System.err.println("ERROR: K must be greater than 0");
			System.exit(1);
		}
		return;
	}

	@Override
	public void validateInputFile(String inputFile){
		File file = new File(inputFile);
		if(0 == file.length()){
			System.err.println("ERROR: Input file empty");
			System.exit(1);
		}
		return;
	}

	@Override
	public void validateAcceptableWordsFile(String acceptableWordsFile){
		File file = new File(acceptableWordsFile);
		if(0 == file.length()){
			System.err.println("ERROR: AcceptableWordsFile empty");
			System.exit(1);
		}
		return;
	}

	@Override
	public int hashCode(){
		return 1;
	}

	@Override
	public String toString(){
		return "Validator";
	}
}
