package estamosremoto;

import estamosremoto.parser.BytecodeParser;
import estamosremoto.utils.bytecode.util.attribute.AttributeInfo;
import estamosremoto.utils.bytecode.util.method.Method;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ColorLogger logger = new ColorLogger();
        if (args.length < 1) {
            System.out.println("File path is required");
            System.exit(1);
        }
        System.out.println("Got path: " + args[0]);
        Path path = Path.of(args[0]);
        BytecodeParser bytecodeParser = new BytecodeParser(path);
        byte[] bytecode = bytecodeParser.findMainMethodBytecode();
        System.out.println(new String(bytecode, StandardCharsets.UTF_8).length());
        System.exit(0);
    }
}