package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.File;

public class RunningAverageData implements RunningAverageResultsI {

	private double runningAverage;
	private String outputFileName;
	private FileWriter writer;

	public RunningAverageData(String inOutputFileName){
		outputFileName = inOutputFileName;
		try{
			writer = new FileWriter(outputFileName);
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
	}

	public double getRunningAverage(){
		return runningAverage;
	}

	public String getOutputFileName(){
		return outputFileName;
	}

	public FileWriter getWriter(){
		return writer;
	}

	@Override
	public void store(double d) {
		runningAverage = d;
		return;
	}

	@Override
	public void writeToFile() {
		DecimalFormat df = new DecimalFormat("#.##");
		
		df.setRoundingMode(RoundingMode.HALF_UP);

		String outString = df.format(runningAverage).toString();

		try{
			writer.write(outString + "\n");
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void close() {
		try{
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return;
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(RunningAverageData other){
		if(other.getRunningAverage() == runningAverage && other.getOutputFileName() == outputFileName && other.getWriter() == writer){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 9;
	}
}
