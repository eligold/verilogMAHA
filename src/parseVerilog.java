
public class parseVerilog {
	//private instruction head;
	//private instruction tail;
	//private instruction ifHead;
	//private instruction ifTail;
	private boolean vars = true;
	private boolean _if = false;
	private boolean inOut = false;
	private boolean oneLine = true;
	private int regIndex = 0;
	private String[] r;
	
	public parseVerilog()
	{
		
	}
	
	public void outputTest(String filename)
	{
		System.out.println("converting file: " + filename);
		System.out.println("ADD 100 R0, R0, R0\t//a = in / 2");
		System.out.println("ADDI 100 R3, R2, 2");
		System.out.println("BR 000, 011, 6");
		System.out.println("ADD 100 R2, R2, 2");
		System.out.println("ADDI 000 R0, R0, 1");
		System.out.println("J 1");
		System.out.println("ADDI 000 R3, R0, 0");

		System.out.println("ADDI 000 R4, R3, 7\t//b = a + 7");

		System.out.println("ADDI 000 R0, R0, 1\t// c = 3 * a");

		System.out.println("ADD 100 R1, R1, R1");
		System.out.println("ADDI 100 R5, R3, R0");
		System.out.println("BR 000, 100, 15");
		System.out.println("ADD 000 R1, R1, 3");
		System.out.println("ADDI 100 R3, R3, 1");
		System.out.println("J 10 ");
		System.out.println("ADDI 000 R5, R1, 0");

		System.out.println("ADDI 100 R6, R4, 3\t//d = b - 3");

		System.out.println("ADD 000 R5, R5, R6\t//c = c + d");

		System.out.println("FUSE8 R6, R3, R4, 0x10\t//d = a && b");

		System.out.println("ADDI 100 R0, R5, R6\t//if(c < d): a = 0 else: b = 0");
		System.out.println("BR 000, 011, 24");
		System.out.println("ADDI 000 R3, R3, 0");
		System.out.println("J 25");
		System.out.println("ADDI 000 R4, R4, 0");

		System.out.println("FUSE8 R5, R3, R6, 0x10\t//c = a && d");

		System.out.println("FUSE8 R5, R5, R4, 0x06\t//c = c ^ b");

		System.out.println("ADDI 000 R8, R5, 0\t//out = c");
	}
		
	public void parse(String l){
		
		l = l.trim();
		
		if(vars)
		{
			if(!l.contains("module"))	//if it doesn't have "module" throws message
				System.out.println("This does not appear to be a correct verilog file");
			if(l.contains("(") && !l.contains(")")) //checks whether there is a paren opener but no closer
				oneLine = false;	//this boolean is passed to reg and stores whether inputs and outputs are on one line
			if(l.contains(")") && !l.contains("("))	//if it has reached the end line of in/out
				oneLine = true;		//end the reg method conditional
			reg(l);
		}
		
		if(inOut)
		{
			reg(l);
		}
		
		if(_if)
		{
			//Ians class
		}
		
		String[] s = l.split("[ \t\b\f\r]");
		System.out.println("\n" + "=====\n" + "- - -\n" + "=====");
		for (int i = 0; i < s.length; i++)
		{
			System.out.println(s[i]);
		}
	}
	
	public void reg(String l)
	{
		if(vars)
		{
			int start = l.indexOf('(');
			int end = l.length();
			if(oneLine)
			{
				end = l.indexOf(')');
				vars = false;
				inOut = true;
			}
			l = l.substring(start + 1, end);
			String[] s = l.split("[,]");
			r = new String[s.length - 1];
			for (int k = 0; k < s.length; k++)
			{
				s[k] = s[k].trim();
				if(!s[k].equals("clock") && !s[k].equals("clk"))
				{
					r[regIndex] = s[k];
					regIndex++;
				}
				if(regIndex >= 29)
					System.out.println("Out of registers!");
			}
		}
		
		if(inOut)
		{
			if(l.contains("output"))
			{
				regSize(l);
				//TODO if not in reg list out error
			}
			else if(l.contains("reg") && (!l.contains("output") || !l.contains("input")))
			{
				boolean b = regSize(l);
				if(l.contains(","))
				{
					String[] v;
					if(b)
						v = l.split("\\]");
					else
						v = l.split("reg");
					v[1] = v[1].trim();
					String[] m = v[1].split("[,]");
					for(int i = 0; i < m.length; i++)
					{
						System.out.println(m[i]);
						checkReg(m[i]);
					}
				}
			}
		}
		
		return;
	}
	
	private boolean regSize(String l)
	{
		boolean b = false;
		if(l.contains("[") && l.contains("]"))
		{
			b = true;
			String[] v = l.split("[\\[\\]]");
			for(int k = 0; k < v.length; k++)
			{
				v[k] = v[k].replace(";", "").trim();
			}
			String[] n = v[1].split(":");
			int high = Integer.parseInt(n[0]);
			int low = Integer.parseInt(n[1]);
			if((high - low) > 7)
				System.out.println("variable " + v[2] + " contains too many bits");
		}
		return b;
	}
	
	private void checkReg(String l)
	{
		
	}
}



































