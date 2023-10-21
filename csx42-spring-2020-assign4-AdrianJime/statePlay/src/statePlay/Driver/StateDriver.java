package statePlay.Driver;

import statePlay.util.Person;
import statePlay.util.PersonI;
import java.lang.Integer;
import statePlay.util.Validator;
import statePlay.util.ValidatorI;

public class StateDriver{

	public static void main(String[] args){

		if((4!=args.length) || (args[0].equals("${inputFile}")) || (args[1].equals("${itemsFile}")) || (args[2].equals("${windowSize}")) || (args[3].equals("${outputFile}"))){
			System.err.println("Error: Incorrect number of arguments. Program accepts 2 arguments.");
			System.err.println(args[0]);
			System.err.println(args[1]);
			System.err.println(args.length);
			System.exit(1);
		}

		ValidatorI validator = new Validator();
		
		validator.validateWindowSize(Integer.parseInt(args[2]));
		validator.validateInputFile(args[0]);
		validator.validateItemsFile(args[1]);


		PersonI person = new Person(Integer.parseInt(args[2]), args[0], args[1], args[3]);
		person.readAvailableItems();
		person.readInputFile();

	}

}
