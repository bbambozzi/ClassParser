package parser;

import bbambozzi.parser.ClassModelParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class ClassModelParserTest {

    @Test()
    void testsLoad() {
        assertEquals(0, 0);
    }
    @Test
    void resourcesExist() {
        Path path = Path.of("src/main/resources/examples/single-file/HelloWorld.class");
        assertNotNull(path);
    }

    @Test
    void classModelParserLoads() {
        Path path = Path.of("src/main/resources/examples/single-file/HelloWorld.class");
        ClassModelParser parser = new ClassModelParser(path);
        assertNotNull(parser);
    }

}