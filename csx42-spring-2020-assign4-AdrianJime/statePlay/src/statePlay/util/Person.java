package statePlay.util;

import statePlay.util.BasicState;
import statePlay.util.StateI;
import java.util.Map;
import java.util.HashMap;
import statePlay.util.PersonI;
import statePlay.util.ResultsI;
import statePlay.util.Results;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Person implements PersonI{

	private Map<String, String> itemCategories;
	private Queue<Integer> window;
	private StateI basicState, luxuriousState, extravagantState;
	private StateI currentState;
	private FileProcessor fp;
	private String inputFile;
	private ResultsI results;

	private int windowSize;
	private int currentWindowSize;
	private int runningAverage;

	public Person(int windowSizeIn, String inputFileIn, String itemsFileIn, String outputFileIn){
		itemCategories = new HashMap<String,String>();
		basicState = new BasicState(this);
		luxuriousState = new LuxuriousState(this);
		extravagantState = new ExtravagantState(this);
		currentState = basicState;
		windowSize = windowSizeIn;
		currentWindowSize=0;
		runningAverage=0;
		window = new LinkedList<>();
		results = new Results(outputFileIn);
		inputFile = inputFileIn;
		try{
			fp = new FileProcessor(itemsFileIn);
		}catch(InvalidPathException | SecurityException | IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void readAvailableItems(){
		String line = null;
		try{
			line = fp.poll();
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] tokens;
		while(null != line){
			if(!(line.matches("basic:(.*)") || line.matches("moderatelyExpensive:(.*)") || line.matches("superExpensive:(.*)"))){
				System.err.println("ERROR: Bad input in items file");
				System.exit(1);
			}

			tokens = line.split(":");
			itemCategories.put(tokens[1],tokens[0]);
			try{
				line = fp.poll();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try{
			fp.close();
		}catch(IOException e){
			System.out.println("Error closing file processor. Shutting down");
			System.exit(1);
		}
		return;
	}

	@Override
	public void readInputFile(){
		try{
			fp = new FileProcessor(inputFile);
		}catch(InvalidPathException | SecurityException | IOException e){
			e.printStackTrace();
		}
		String line = null;
		try{
			line = fp.poll();
		}catch(IOException e){
			e.printStackTrace();
		}
		String[] token;
		String decision;
		String itemCategory;
		String output;
		while(null != line){
			if(!(line.matches("money:(.*)") || line.matches("item:(.*)"))){
				System.err.println("ERROR: Bad line in input file");
				System.exit(1);
			}
			token = line.split(":");
			if("money".equals(token[0])){
				if(currentWindowSize == windowSize){
					window.poll();
					window.add(Integer.valueOf(token[1]));
				}else{
					currentWindowSize +=1;
					window.add(Integer.valueOf(token[1]));
				}
				calculateRunningAverage();
				currentState = currentState.determineState();
			}else if("item".equals(token[0])){
				decision = currentState.makePurchase(token[1]);
				itemCategory = itemCategories.get(token[1]).toUpperCase();
				output = itemCategory + "::" + token[1] + "--" + decision+"\n";
				results.writeOutput(output);
			}
			try{
				line = fp.poll();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		results.closeOutputFile();
		return;

	}

	@Override
	public void calculateRunningAverage(){
		int totalIncome = 0;
		for(Integer income : window){
			totalIncome += income.intValue();
		}
		runningAverage = (totalIncome / currentWindowSize);
		return;
	}


	@Override
	public int getWindowSize(){
		return windowSize;
	}

	@Override
	public int getCurrentWindowSize(){
		return currentWindowSize;
	}
	
	@Override
	public int getRunningAverage(){
		return runningAverage;
	}

	@Override
	public Map<String,String> getItemCategories(){
		return itemCategories;
	}

	@Override
	public StateI getCurrentState(){
		return currentState;
	}

	@Override
	public StateI getBasicState(){
		return basicState;
	}

	@Override
	public StateI getLuxuriousState(){
		return luxuriousState;
	}

	@Override
	public StateI getExtravagantState(){
		return extravagantState;
	}

	@Override
	public String toString(){
		return "Person";
	}

	@Override
	public int hashCode(){
		return 2;
	}

}
