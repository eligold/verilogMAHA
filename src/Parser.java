
public class Parser {

    private static parseVerilog parser = new parseVerilog();

    public static void parse(String l, String m)
    {
        parseVerilog p = new parseVerilog();
        p.parse(l);
        p.parse(m);
    }

    public static void parse(String verilogStatement) {
        parser.parse(verilogStatement);
    }

}
