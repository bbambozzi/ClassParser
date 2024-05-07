package estamosremoto.parser;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.ConstantPoolItemsParser;
import estamosremoto.utils.classmodel.VersionMetadata;
import estamosremoto.utils.classmodel.util.accessflag.ClassAccessFlag;
import estamosremoto.utils.classmodel.util.attribute.AttributeInfo;
import estamosremoto.utils.classmodel.util.constantpool.ConstantPoolItem;
import estamosremoto.utils.classmodel.util.field.Field;
import estamosremoto.utils.classmodel.util.method.Method;
import estamosremoto.utils.classmodel.util.properties.HasBytes;
import estamosremoto.utils.classmodel.util.properties.HasNameIndex;
import estamosremoto.utils.codeattribute.CodeAttribute;
import estamosremoto.utils.logger.ColorLogger;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.channels.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassModelParser {
    static final ColorLogger logger = new ColorLogger();
    private final SeekableByteChannel byteChannel;
    private final VersionMetadata versionMetadata;
    private final List<ConstantPoolItem> constantPoolItems;
    private final List<ClassAccessFlag> accessFlags;
    private final ConstantPoolItem thisClass;
    private final ConstantPoolItem superclass;
    private final int interfaceCount;
    private final byte[] interfaces;
    private final int fieldsCount;
    private final List<Field> fields;
    private final int methodsCount;
    private final List<Method> methods;
    private final int attributesCount;
    private final List<AttributeInfo> attributeInfo;

    public ClassModelParser(Path pathToBytecode) {
        this.byteChannel = getByteChannel(pathToBytecode);
        this.versionMetadata = getVersionMetadata();
        this.constantPoolItems = parseConstantPoolItems();
        this.accessFlags = ClassAccessFlag.getMatching(getAccessFlagsMask());
        this.thisClass = constantPoolItems.get(getThisClassIndex());
        this.superclass = constantPoolItems.get(getThisSuperclassIndex());
        this.interfaceCount = parseInterfaceCount();
        this.interfaces = new byte[]{};
        this.fieldsCount = parseFieldsCount();
        this.fields = new ArrayList<>();
        if (interfaceCount > 0 || fieldsCount > 0) {
            throw new IllegalArgumentException("Parsing interfaces or fields is not yet implemented");
        }
        this.methodsCount = parseMethodsCount();
        this.methods = parseMethodItems();
        this.attributesCount = parseAttributesCount();
        this.attributeInfo = parseAttributesInfo();
        logger.green("classmodel model = " + versionMetadata);
        logger.green("constant pool items = " + constantPoolItems);
        logger.green("Access flags = " + accessFlags);
        logger.green("this class = " + thisClass);
        logger.green("this superclass = " + superclass);
        logger.green("interface count = " + interfaceCount);
        logger.green("fields count = " + fieldsCount);
        logger.green("methods count = " + methodsCount);
        logger.green("method items = " + methods);
        logger.green("attributes count = " + attributesCount);
        logger.green("attributes info = " + attributeInfo);
        logger.green("name index of file = " + nameIndexOfFile());
        logger.green("name of file " + getNameOfFile());
        logger.green("methods = [");
        printAllMethods();
    }

    private static String toHex(byte byt) {
        return String.format("%02X", byt);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    private static SeekableByteChannel getByteChannel(Path path) {
        try {
            return Files.newByteChannel(path);
        } catch (IOException e) {
            logger.red("Failed to get byte channel from path!");
            System.exit(1);
            return null;
        }
    }


    private int nameIndexOfFile() {
        if (thisClass instanceof HasNameIndex nis) {
            return nis.name_index();
        }
        throw new RuntimeException("Class " + thisClass + " does not have a NameIndex");
    }

    private String getNameOfFile() {
        int idx = nameIndexOfFile();
        ConstantPoolItem elem = this.constantPoolItems.get(idx - 1);
        if (elem instanceof HasBytes hasBytesElement) {
            byte[] bytes = hasBytesElement.bytes();
            return new String(bytes);
        }
        throw new RuntimeException("Class " + thisClass + " does not have a NameIndex");
    }


    private int getAccessFlagsMask() {
        return ByteChannelParser.parseU2(byteChannel);
    }

    private int getThisClassIndex() {
        return ByteChannelParser.parseU2(byteChannel) - 1;
    }

    private int getThisSuperclassIndex() {
        return ByteChannelParser.parseU2(byteChannel) - 1;
    }

    private int parseInterfaceCount() {
        return ByteChannelParser.parseU2(byteChannel);
    }

    private int parseFieldsCount() {
        return ByteChannelParser.parseU2(byteChannel);
    }

    private int parseMethodsCount() {
        return ByteChannelParser.parseU2(byteChannel);
    }


    private int parseAttributesCount() {
        return ByteChannelParser.parseU2(byteChannel);
    }


    private VersionMetadata getVersionMetadata() {
        try {
            this.byteChannel.position(0);
            String magicString = toHex(ByteChannelParser.parseU4(this.byteChannel));
            int minorVersion = ByteChannelParser.parseU2(byteChannel);
            int majorVersion = ByteChannelParser.parseU2(byteChannel);
            int constantPoolCount = ByteChannelParser.parseU2(byteChannel);
            return new VersionMetadata(magicString, minorVersion, majorVersion, constantPoolCount);
        } catch (IOException e) {
            logger.red("Failed to parse version metadata");
            System.exit(0);
            return new VersionMetadata("", 0, 0, 0);
        }
    }

    private List<ConstantPoolItem> parseConstantPoolItems() {
        try {
            byteChannel.position(10);
            logger.green("About to parse the first constant pool tag");
            return ConstantPoolItemsParser.parseItems(byteChannel, this.versionMetadata.constantPoolCount());


        } catch (Exception err) {
            logger.red("Failed to parse classmodel model!");
            logger.red(err.getMessage());
            System.exit(1);
            return null;
        }
    }

    private List<Method> parseMethodItems() {
        logger.green("about to parse the method items!");
        List<Method> answer = new ArrayList<>();
        for (int i = 0; i < methodsCount; i++) {
            answer.add(new Method(byteChannel));
        }
        return answer;
    }

    private List<Field> parseFields() {
        logger.green("parsing fields");
        List<Field> answer = new ArrayList<>();
        for (int i = 0; i < fieldsCount; i++) {
            answer.add(new Field(byteChannel));
        }
        return answer;
    }

    private byte[] parseInterfaces() {
        logger.green("parsing interfaces");
        return ByteChannelParser.parseBytes(byteChannel, interfaceCount);
    }

    private List<AttributeInfo> parseAttributesInfo() {
        logger.green("about  to parse the attribute info for the class!");
        List<AttributeInfo> answer = new ArrayList<>();
        for (int i = 0; i < attributesCount; i++) {
            answer.add(new AttributeInfo(byteChannel));
        }
        return answer;
    }

    private void printAllMethods() {
        for (Method method : methods) {
            var name_index = method.name_index();
            ConstantPoolItem item = constantPoolItems.get(name_index - 1);
            if (item instanceof HasNameIndex found) {
                logger.green(constantPoolItems.get(found.name_index()).toString());
            } else {
                logger.green(item.toString());
            }
        }
    }


    public List<Method> findMethodsByName(byte[] name) {
        List<Method> answer = new ArrayList<>();
        for (Method item : methods) {
            ConstantPoolItem possibleAnswer = constantPoolItems.get(item.name_index() - 1);
            if (possibleAnswer instanceof HasBytes found) {
                if (Arrays.equals(name, found.bytes())) {
                    answer.add(item);
                }
            }
        }
        return answer;
    }

    public List<AttributeInfo> findAttributeInfoByName(List<AttributeInfo> atttributes, byte[] name) {
        List<AttributeInfo> answer = new ArrayList<>();
        for (AttributeInfo attribute : atttributes) {
            var item = constantPoolItems.get(attribute.attribute_name_index() - 1);
            if (item instanceof HasBytes found) {
                if (Arrays.equals(name, found.bytes())) {
                    answer.add(attribute);
                }
            }
        }
        return answer;
    }

    public AttributeInfo findCodeByName(List<AttributeInfo> atttributes) {
        return findAttributeInfoByName(atttributes, "Code".getBytes()).getFirst();
    }

    public AttributeInfo findMainMethodAttributeInfo() {
        Method method = findMethodsByName("main".getBytes()).getFirst();
        return findCodeByName(method.attribute_info());
    }

    public byte[] findMainMethodAttributeInfoBytes() {
        return findMainMethodAttributeInfo().info();
    }

    public ReadableByteChannel findMainMethodAttributeInfoByteChannel() {
        byte[] bytes = findMainMethodAttributeInfoBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        return Channels.newChannel(byteArrayInputStream);
    }

    public CodeAttribute findMainMethodCodeAttribute() {
        return new CodeAttribute(findMainMethodAttributeInfoByteChannel());
    }

    public byte[] findMainMethodBytecode() {
        return findMainMethodCodeAttribute().code();
    }

}
