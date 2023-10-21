package statePlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Results implements ResultsI{

	private BufferedWriter writer;

	public Results(String outputFileIn){
		try{
			writer = new BufferedWriter(new FileWriter(outputFileIn, false));
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
	}

	@Override
	public void writeOutput(String output){
		try{
			writer.write(output);
		}catch(IOException e){
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void closeOutputFile(){
		try{
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return;
	}

	@Override
	public String toString(){
		return "Results";
	}

	@Override
	public int hashCode(){
		return 1;
	}
}

