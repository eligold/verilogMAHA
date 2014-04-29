
public class MAHA {
    static int PC; //Program Counter
	public MAHA(){
		PC = 0;
	}
	
	//Input: {Operation, Destination, Operand 1, Operand 2} or {"IF", operand 1, operand 2, condition, if outcome, else outcome}
	public String makeMAHA(String[] input){
		StringBuilder build = new StringBuilder();
		
		//Add
		if(input[0] == "ADD"){
			build.append("ADD 000 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + "/n"); //Op 2
			PC++;
		
		//Add immediate
		}else if(input[0] == "ADDI"){
			build.append("ADDI 000 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + "/n"); //Constant
			PC++;
			
		//Subtract
		}else if(input[0] == "SUB"){
			build.append("ADD 100 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + "/n"); //Op 2
			PC++;
			
		//Subtract immediate
		}else if(input[0] == "SUBI"){
			build.append("ADDI 100 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + "/n"); //Constant
			PC++;
			
		//Multiply
		}else if(input[0] == "MUL"){
			int start = PC;
			build.append("ADDI 000 R0, R0, 1/n"); //Temp = 1 to compare with B
			PC++;
			
			build.append("ADD 100 R1, R1, R1/n"); //Temp total = 0
			PC++;
			
			int branch = start + 2;
			build.append("ADDI 100 " + input[1] + ", " + input[3] + ", R0/n"); //Branch must be preceeded by A-B to evaluate A condition B. Stored to destination as it will be overwritten last.
			PC++;
			
			int end = start + 7;
			build.append("BR 000, 100, " + end.toString() + "/n"); //Branch to end line if B >= 1, else continue
			PC++;
			
			build.append("ADD 100 R1, R1, "); //Total = Total + A
			build.append(input[2]); 
			PC++;
			
			build.append("ADDI 100 " + input[3] + ",  " + input[3] ", 1/n"); //Decrement B
			PC++;
			
			build.append("J " + branch.toString() + "/n"); //Jump back to line where branch is evaluated
			PC++
			
			build.append("ADDI 000 "); //Dest = temp total
			build.append(input[1] + ", "); 
			build.append("R1, 0/n");
			PC++;
		
		//Divide
		}else if(input[0] == "DIV"){
			int start = PC;
			build.append("ADD 100 R0, R0, R0/n"); //Temp counter = 0
			PC++;
			
			int branch = start + 1;
			build.append("ADDI 100 " + input[1] + ", " + input[2] + ", " + input[3] + "/n"); //Branch must be preceeded by A-B to evaluate A condition B. Stored to destination as it will be overwritten last.
			PC++;
			
			int end = start + 6;
			build.append("BR 000, 011, " + end.toString() + "/n"); //Branch to end line if A < B, else continue.
			PC++;
			
			build.append("ADD 100 "); //A = A - B
			build.append(input[2] + ", "); 
			build.append(input[2] + ", ");
			build.append(input[3] + "/n");
			PC++;
			
			build.append("ADDI 000 R0, R0, 1/n"); //Increment temp counter
			PC++;
			
			build.append("J " + branch.toString() + "/n"); //Jump back to line where branch is evaluated
			PC++
			
			build.append("ADDI 000 "); //Dest = temp counter + 0
			build.append(input[1] + ", "); 
			build.append("R0, 0/n");
			PC++;
		
		//Negate
		}else if(input[0] == "NEG"){
			//Not sure how to do this, but it doesn't seem like MAHA works with negative numbers.
		
		//And
		}else if(input[0] == "AND"){
			build.append("FUSE8 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + ", "); //Op 2
			build.append("0x10/n"); //RM Expansion for AND
			PC++;
			
		//Or
		}else if(input[0] == "OR"){
			build.append("FUSE8 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + ", "); //Op 2
			build.append("0x16/n"); //RM Expansion for OR
			PC++;
			
		//Xor
		}else if(input[0] == "XOR"){
			build.append("FUSE8 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + ", "); //Op 2
			build.append("0x06/n"); //RM Expansion for XOR
			PC++;
			
		//Not
		}else if(input[0] == "NOT"){
			build.append("FUSE8 ");
			build.append(input[1] + ", "); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append("0x03/n"); //RM Expansion for NOT
			PC++;
			
		//Shift Left
		}else if(input[0] == "SHL"){
			build.append("SHF 000 " + input[1]); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + ", "); //Op 2 (# of places to shift)
			PC++;
		
		//Shift Left
		}else if(input[0] == "SHR"){
			build.append("SHF 100 " + input[1]); //Destination
			build.append(input[2] + ", "); //Op 1
			build.append(input[3] + ", "); //Op 2 (Places to shift)
			PC++;
		
		//If
		}else if(input[0] == "IF"){
		
		}
		return build.toString();
	}
	
	
}
