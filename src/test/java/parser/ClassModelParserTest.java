package parser;

import bbambozzi.parser.ClassModelParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClassModelParserTest {

    ClassModelParser parser;

    @BeforeEach
    public void setUp() {
        Path path = Path.of("src/main/resources/examples/single-file/HelloWorld.class");
        parser = new ClassModelParser(path);
    }

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
        assertNotNull(parser);
    }

    @Test
    void methodsCountIsCorrect() {
        assertEquals(2, parser.getMethodsCount());
    }

    @Test
    void fieldsCountIsCorrect() {
        assertEquals(0, parser.getFieldsCount());
    }

    @Test
    void attributesCountIsCorrect() {
        assertEquals(1, parser.getAttributesCount());
    }

    @Test
    void constantPoolCountIsCorrect() {
        assertEquals(12, parser.getConstantPoolCount());
    }

    void versionInformationExists() {
        assertNotNull(parser.get);
    }

}