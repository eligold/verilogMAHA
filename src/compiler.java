
public class compiler {

	public static void main(String[] args) 
	{
		Parser.parse("  module example(in, clk, out)\t;  ","output reg [8:0] in, one, two;");
	}

}
