module example(in, clk, out);
	input [7:0] in;
	input clk;
	output [7:0] out;
	reg [7:0] a,b,c,d,e;
	
	
	always @(posedge clk)
	begin
		a = in / 2;
		b = a + 7;
		c = 3 * a;
		d = b - 3
		c = c + d;
		d = a && b;
		if (c < d)
		begin
			a = 0;
		end
		else
		begin
			b = 0;
		end
		c = a && d;
		c = c ^ b;
		assign out = c;
		
	end
endmodule