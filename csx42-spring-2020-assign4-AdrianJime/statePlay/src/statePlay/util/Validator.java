package statePlay.util;

import java.io.File;

/**
 * @author Adrian Jimenez
 *
 * Validator is a utility class in charge of
 * validating commandline arguments and input
 * files
 */

public class Validator implements ValidatorI{

	/**
	 * Constructs a Validator object
	 */
	public Validator(){}

	/**
	 * Validates that the windowsize is greater
	 * than 0
	 * @param windowSize The size of the window
	 */
	@Override
	public void validateWindowSize(int windowSize){
		if(windowSize <= 0){
			System.err.println("ERROR: WindowSize cannot be 0 or below");
			System.exit(1);
		}
		return;
	}

	/**
	 * Validates that the input file is not empty
	 * @param inputFile The input file
	 */
	@Override
	public void validateInputFile(String inputFile){
		File file = new File(inputFile);
		if(0 == file.length()){
			System.err.println("ERROR: Input file empty");
			System.exit(1);
		}
		return;
	}

	/**
	 * Validates that the items file is not empty
	 * @param itemsFile The items file
	 */
	@Override
	public void validateItemsFile(String itemsFile){
		File file = new File(itemsFile);
		if(0 == file.length()){
			System.err.println("ERROR: Items file empty");
			System.exit(1);
		}
		return;
	}

	/**
	 * Returns a string representation of this object
	 */
	@Override
	public String toString(){
		return "Validator";
	}

	/**
	 * Returns the hashcode of this object
	 */
	@Override
	public int hashCode(){
		return 3;
	}
}
