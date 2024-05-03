package estamosremoto.parser;

import java.io.IOException;
import java.lang.classfile.ClassFile;
import java.lang.classfile.ClassModel;
import java.nio.file.Path;

public class ClassModelParser {
    ClassModel parse(byte[] input) {
        return ClassFile.of().parse(input);
    }

    ClassModel parse(Path input) throws IOException {
        return ClassFile.of().parse(input);
    }
}
