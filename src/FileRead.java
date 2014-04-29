import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.*;

public class FileRead {

	public void reader(String input, String output) {

		// Location of file to read
		File file = new File(input);
		String line = new String();
		try {

			// Write values to file
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				Parser.parse(line);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Check if Verilog code essentials are there
		if (!line.contains("module")) {
			System.out.println("There does not seem to be a beginning module");
		}
		if (!line.contains("endmodule")) {
			System.out.println("There does not seem to be an end module");
		}
		if (!line.contains("always @")) {
			System.out.println("There does not seem to be an always block");
		}
		String[] indiline = line.split(";");
		String maha = new String();
		for (int i = 0; i < indiline.length; i++) {
			//Check to see what other inputs are other than clock
			if (indiline[i].contains("input")) {
				String[] inputs = indiline[i].split(" ");
				for (int l = 0; l < inputs.length; l++) {
					if(!inputs[l].contains("clk") & !inputs[l].contains("input")){
						System.out.println("There is more than the clock input for this code, there is also : " + inputs[l]);
					}
				}
				
			}
			//Start at the third input in the line array because changes are the first three inputs are the module, inputs, and outputs lol
			//split lines up into command type and put command type in constuctor as well as command and put result in array
			if(indiline[i].contains("+")){
				//It is addition, find out if you need to further split it up or just give it to him
				// Call constructor and add what it returns to maha.concat();
			}
			else if(indiline[i].contains("-")){
				//It is subtraction
			}
			else if(indiline[i].contains("if")){
				//It is a conditional statement like this one lol
			}
			else if(indiline[i].contains("[")){
				//It is a register
			}
		}
		//Print out shit that is sent to console lol

		File file2 = new File(output);
		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(file2));
		for (int c = 0; c < maha.length(); c++)  //print out the frequency and word for each array entry
			out.println(maha);
		out.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}


   
