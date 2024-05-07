package estamosremoto;

import estamosremoto.parser.ClassModelParser;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        ColorLogger logger = new ColorLogger();
        if (args.length < 1) {
            System.out.println("File path is required");
            System.exit(1);
        }
        System.out.println("Got path: " + args[0]);
        Path path = Path.of(args[0]);
        ClassModelParser classModelParser = new ClassModelParser(path);
        ReadableByteChannel buffer = classModelParser.findMainMethodBytecode();
        System.exit(0);
    }
}