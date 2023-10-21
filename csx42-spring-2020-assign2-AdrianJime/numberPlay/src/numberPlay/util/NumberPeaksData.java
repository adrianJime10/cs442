package numberPlay.util;

import java.util.List;
import java.text.DecimalFormat;
import java.math.RoundingMode;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {

	private Number peak;
	private Writer writer;

	public NumberPeaksData(String inOutputFile){
		peak = null;
		try{
			writer = new FileWriter(inOutputFile);
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
	}

	public Number getPeak(){
		return peak;
	}

	public Writer getWriter(){
		return writer;
	}

	@Override
	public void store(Number inPeak) {
		peak = inPeak;
		return;
	}

	@Override
	public void writeToFile() {

		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		try{
			writer.write(df.format(peak) + "\n");
		}catch(IOException e){
			e.printStackTrace();
		}finally{}
		return;
	}

	@Override
	public void close() {
		try{
			writer.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{}	
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(NumberPeaksData other){
		if(other.getPeak() == peak && other.getWriter() == writer){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 4;
	}
}
