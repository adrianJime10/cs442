package primePlay.driver;
import primePlay.util.FileProcessor;
import primePlay.worker.CreateWorkers;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.io.IOException;
import primePlay.util.Results;
import primePlay.util.IsPrime;
import primePlay.worker.RunMethods;
import primePlay.util.MyLogger;
import primePlay.util.ResultsI;
import primePlay.util.IsPrimeI;
import primePlay.worker.RunMethodsI;
import primePlay.worker.CreateWorkersI;
import primePlay.util.Validator;
import primePlay.util.ValidatorI;

/**
 * @author Evan McNierney, Adrian Jimenez
 *
 */
public class PrimeDetector {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 6) || (args[0].equals("${inputFile}")) || (args[1].equals("${numThreads}")) || (args[2].equals("${capacity}")) || (args[3].equals("${persisterServiceIp}")) || (args[4].equals("${persisterServicePort}")) || (args[5].equals("${debugValue}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 6 arguments.");
			System.err.println(args.length);
			System.exit(1);
		}

		ValidatorI validator = new Validator();

		if(!validator.validateCapacity(args[2])) {
			System.err.println("ERROR: invalid argument for capacity");
			System.exit(1);
		}
		if(!validator.validatePort(args[4])) {
			System.err.println("ERROR: invalid argument for port");
			System.exit(1);

		}
		if(!validator.validateNumThreads(args[1])) {
			System.err.println("ERROR: invalid argument for number of threads");
			System.exit(1);

		}
		
		FileProcessor processor = null;
		IsPrimeI primeFinder = null;
		ResultsI results = null;
		RunMethodsI methods = null;
		MyLogger logger = null;

		try {
			logger = new MyLogger();
			logger.setDebugValue(Integer.parseInt(args[5]));
			logger.writeMessage("MyLogger constructor called", MyLogger.DebugLevel.CONSTRUCTOR);
			processor = new FileProcessor(args[0]);

			if(validator.fileEmpty(args[0])) {
				System.err.println("ERROR: File is empty");
				System.exit(1);
			}


			primeFinder = new IsPrime();

			results = new Results(Integer.parseInt(args[2]));

			methods = new RunMethods(processor, primeFinder, results, args[3], Integer.parseInt(args[4]));


			CreateWorkersI x = new CreateWorkers(processor, primeFinder, results, methods);
			results.sendData(methods);

			x.startWorkers(Integer.parseInt(args[1]));

			
			logger.writeMessage(results.toString(), MyLogger.DebugLevel.RESULTS);
			logger.writeMessage(new String("The sum of all prime numbers is: " + results.getSum()), MyLogger.DebugLevel.SUM);
		} catch(InvalidPathException e) {
			System.out.println(e);
		} catch(SecurityException e) {
			System.out.println(e);
		} catch(FileNotFoundException e) {
			System.out.println(e);
		} catch(IOException e) {
			System.out.println(e);
		} finally {
			try {
				processor.close();
			} catch (IOException e) {
				System.out.println("Error closing file processor. Shutting down...");
				System.exit(1);
			}
		}
	}
}
