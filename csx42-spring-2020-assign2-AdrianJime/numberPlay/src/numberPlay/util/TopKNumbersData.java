package numberPlay.util;

import java.util.List;
import java.util.Queue;
import java.io.IOException;
import java.io.Writer;
import java.io.FileWriter;
import java.util.PriorityQueue;
import java.text.DecimalFormat;
import java.lang.StringBuilder;
import java.math.RoundingMode;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {

	private Queue<Double> topKNums;
	private String outputFile;
	private Writer writer;

	public TopKNumbersData(String inOutputFileName){
		outputFile = inOutputFileName;
		topKNums = new PriorityQueue<Double>();
		try{
			writer = new FileWriter(outputFile);
		}catch(IOException e){
			writer = null;
			e.printStackTrace();
		}
	}

	public Queue<Double> getTopKNums(){
		return topKNums;
	}

	public String getOutputFile(){
		return outputFile;
	}

	public Writer getWriter(){
		return writer;
	}

	@Override
	public void store(Queue<Double> topK) {
		topKNums = topK;
		return;
	}

	@Override
	public void writeToFile() {

		Object[] queueToArray = topKNums.toArray();
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.HALF_UP);
		StringBuilder outString= new StringBuilder("[");
		for(int i = topKNums.size()-1; i>-1; i--){
			if(i == 0){
				outString.append(df.format(queueToArray[i]).toString());
			}else{
				outString.append(df.format(queueToArray[i]).toString() + ", ");
			}
		}
		try{
			writer.write(outString + "]" + "\n");
		}catch(IOException e){
			writer=null;
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
	}

	@Override
	public String toString(){
		return " (づ ﾟ෴ ﾟ)づ ";
	}

	public boolean equals(TopKNumbersData other){
		if(other.getTopKNums() == topKNums && other.getOutputFile() == outputFile && other.getWriter() == writer){
			return true;
		}
		return false;
	}

	@Override
	public int hashCode(){
		return 6;
	}
}
