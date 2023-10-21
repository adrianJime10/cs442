package numberPlay.driver;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;
import java.lang.Integer;
import numberPlay.util.Event;
import numberPlay.observer.ObserverI;
import numberPlay.observer.TopKNumbersObserver;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.NumberPeaksObserver;
import numberPlay.util.FileProcessor;
import numberPlay.subject.Filter.FloatingFilter;
import numberPlay.subject.Filter.IntegerFilter;
import numberPlay.subject.Filter.ProcessCompleteFilter;
import numberPlay.subject.Filter.FilterI;
import numberPlay.util.Validator;
import numberPlay.util.ValidatorUtil;
import numberPlay.util.InputFileValidator;
import numberPlay.util.CommandLineValidator;
import java.lang.IllegalArgumentException;
import java.lang.NumberFormatException;

/**
 * @author Adrian Jimenez
 */
public class Driver {

	public static String line;

	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 * FIXME Refactor commandline validation using the validation design taught in class.
		 */
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || 
				(args[0].equals("${inputNumStream}")) || 
				(args[1].equals("${runAvgWindowSize}")) || 
				(args[2].equals("${runAvgOutFile}")) ||
				(args[3].equals("${k}")) ||
				(args[4].equals("${topKNumOutFile}")) ||
				(args[5].equals("${numPeaksOutFile}"))) {

			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}

		Validator commandLineValidator = new CommandLineValidator(args[0],args[1],args[2],args[3],args[4],args[5]);
		ValidatorUtil validator = new ValidatorUtil();
		Validator inputFileValidator = new InputFileValidator(args[0]);

		try{
			validator.validate("Error", commandLineValidator, inputFileValidator);
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}finally{}

		//System.out.println(Integer.parseInt(args[1])); THROWS NumberFormatException if incorrect
		
		// FIXME Create an instance of each of the classes implementing PersisterI and the 
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually persisted 
		// to the corresponding output file.

		// FIXME Instantiate the subject.

		SubjectI subject = new NumberProcessor();

		// FIXME Instantiate the observers, providing the necessary filter and the results object.
		ObserverI numPeaksObserver = new NumberPeaksObserver(args[5]);
		ObserverI runningAvgObserver = new RunningAverageObserver(Integer.parseInt(args[1]), args[2]);
		ObserverI topKObserver = new TopKNumbersObserver(Integer.parseInt(args[3]), args[4]);

		FilterI floatingFilter = new FloatingFilter();
		FilterI processCompleteFilter = new ProcessCompleteFilter();
		FilterI intFilter = new IntegerFilter();

		// FIXME Register each observer with the subject for the necessary set of events.

		subject.registerObserver(numPeaksObserver, intFilter);
		subject.registerObserver(runningAvgObserver, intFilter);
		subject.registerObserver(topKObserver, intFilter);

		subject.registerObserver(numPeaksObserver, floatingFilter);
		subject.registerObserver(topKObserver, floatingFilter);

		subject.registerObserver(numPeaksObserver, processCompleteFilter);
		subject.registerObserver(runningAvgObserver, processCompleteFilter);
		subject.registerObserver(topKObserver, processCompleteFilter);

		// FIXME Delegate control to a separate utility class/method that provides numbers one at a time, from the FileProcessor,
		// to the subject.

		FileProcessor fp = null;

		try{
			fp = new FileProcessor(args[0]);
		}catch(InvalidPathException | SecurityException | IOException e){
			e.printStackTrace();
		}

		try{
			line = fp.poll();
		}catch(IOException e){
			e.printStackTrace();
		}
		while(line != null ){
			
			subject.processNum(line);
			subject.notifyAlll();
			try{
				line = fp.poll();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		String nullString = "done";
		subject.processNum(nullString);
		subject.notifyAlll();

		try{
			fp.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
