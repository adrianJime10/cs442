package primePlay.driver;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.lang.Exception;
import java.net.InetAddress;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import primePlay.util.ValidatorI;
import primePlay.util.Validator;

public class PersisterDriver {
	public static void main(String[] args) {

		if ((args.length != 2) || (args[0].equals("${port}")) || (args[1].equals("${outputFile}"))) {	
			System.err.println("Error: Incorrect number of arguments. Program accepts 2  arguments.");
			System.err.println(args.length);
			System.exit(1);
		}

		ValidatorI validator = new Validator();

		if(!validator.validatePort(args[0])) {
			System.err.println("Port must be an integer between 32768 and 50000");
			System.exit(1);
		}

		int port = Integer.parseInt(args[0]);
		String outfileName = args[1];


		Socket socket = null;
		ServerSocket server = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		BufferedWriter writer = null;
		try{
			writer = new BufferedWriter(new FileWriter(outfileName));
		}catch(IOException e){
			e.printStackTrace();
		}

		int msg = 1;
		String fullOutput = "";

		try {

			server = new ServerSocket(port);

			InetAddress addr = InetAddress.getLocalHost();

			System.out.println("Address:" + addr.getHostAddress());
			socket = server.accept();
			System.out.println("Accepted");
			try{
				socket.setSoTimeout(500);
			}catch(SocketException e){
				e.printStackTrace();
			}

			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(socket.getOutputStream());

			//System.out.println("Address: " + addr.getHostAddress());
			//System.out.println("Port: " + port);

			while(0 != msg ) {
				System.out.println("Listening...");
				System.out.println("Receiving from client...");
				msg = in.readInt();
				if(0!=msg){
					fullOutput = fullOutput + msg + "\n";
					System.out.println(fullOutput);
				}
			}
			socket.close();
			in.close();
			try{
				writer.write(fullOutput);
			}catch(IOException e){
				e.printStackTrace();
			}
			writer.close();
			System.out.println(fullOutput);
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("ERROR");
		}

	}
}
