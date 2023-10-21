package wordPlay.driver;

import wordPlay.util.FileProcessor;
import java.io.IOException;

/**
 * @author Adrian Jimenez
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 3) || (args[0].equals("${arg0}")) || (args[1].equals("${arg1}")) || (args[2].equals("${arg2}"))) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}

		FileProcessor filee = new FileProcessor(args[0], args[1], args[2]);
		try{
			filee.fileIO();
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Program exiting...");
			System.exit(0);			
		}

	}
}
