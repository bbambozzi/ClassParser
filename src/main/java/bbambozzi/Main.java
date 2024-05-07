package bbambozzi;

import bbambozzi.parser.ClassModelParser;
import bbambozzi.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        ColorLogger logger = new ColorLogger();
        if (args.length < 1) {
            System.out.println("File path is required");
            System.exit(1);
        }
        Path path = Path.of(args[0]);
        ClassModelParser classModelParser = new ClassModelParser(path);
        byte[] mainMethodBytes = classModelParser.findMainMethodBytecode();
        System.out.println(mainMethodBytes);
    }
}