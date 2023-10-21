package numberPlay.util;

import java.lang.NumberFormatException;
import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.lang.Double;

public class InputFileValidator implements Validator{

	private FileProcessor fp;

	public InputFileValidator(String inInputFile){
		try{
			fp = new FileProcessor(inInputFile);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() throws NumberFormatException, IllegalArgumentException{
		String line = null;
		try{
			line = fp.poll();
		}catch(IOException e){
			e.printStackTrace();
		}

		if(line!=null){
			try{
				Double.parseDouble(line);
			}catch(NumberFormatException e){
				e.printStackTrace();
			}
		}
		return;
	}

	public FileProcessor getFileProcessor(){
		return fp;
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(InputFileValidator other){
		if(other.getFileProcessor() == fp){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 1;
	}
}
