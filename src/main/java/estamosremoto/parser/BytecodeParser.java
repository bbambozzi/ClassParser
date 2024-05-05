package estamosremoto.parser;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.ConstantPoolItemsParser;
import estamosremoto.utils.bytecode.VersionMetadata;
import estamosremoto.utils.bytecode.util.accessflag.AccessFlag;
import estamosremoto.utils.bytecode.util.constantpool.Bytes;
import estamosremoto.utils.bytecode.util.constantpool.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.constantpool.NameIndex;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BytecodeParser {
    static final ColorLogger logger = new ColorLogger();
    private final SeekableByteChannel byteChannel;
    private final VersionMetadata versionMetadata;
    private final List<ConstantPoolItem> constantPoolItems;
    private final List<AccessFlag> accessFlags;
    private final ConstantPoolItem thisClass;
    private final ConstantPoolItem superclass;
    private final int interfaceCount;
    private final int fieldsCount;

    public BytecodeParser(Path pathToBytecode) {
        this.byteChannel = getByteChannel(pathToBytecode);
        this.versionMetadata = getVersionMetadata();
        this.constantPoolItems = getConstantPoolItems();
        this.accessFlags = AccessFlag.getMatching(getAccessFlagsMask());
        this.thisClass = constantPoolItems.get(getThisClassIndex());
        this.superclass = constantPoolItems.get(getThisSuperclassIndex());
        this.interfaceCount = getInterfaceCount();
        this.fieldsCount = getFieldsCount();
        if (interfaceCount > 0 || fieldsCount > 0) {
            throw new IllegalArgumentException("Parsing interfaces or fields is not yet implemented");
        }
        // this.interfaces // todo
        logger.green("bytecode model = " + versionMetadata);
        logger.green("constant pool items = " + constantPoolItems);
        logger.green("Access flags = " + accessFlags);
        logger.green("this class = " + thisClass);
        logger.green("this superclass = " + superclass);
        logger.green("interface count = " + interfaceCount);
        logger.green("fields count = " + fieldsCount);
        logger.green("name index of file = " + nameIndexOfFile());
        logger.green("name of file " + getNameOfFile());
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
        if (thisClass instanceof NameIndex nis) {
            return nis.name_index();
        }
        throw new RuntimeException("Class " + thisClass + " does not have a NameIndex");
    }

    private String getNameOfFile() {
        int idx = nameIndexOfFile();
        ConstantPoolItem elem = this.constantPoolItems.get(idx - 1);
        if (elem instanceof Bytes bytesElement) {
            byte[] bytes = bytesElement.bytes();
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

    private int getInterfaceCount() {
        return ByteChannelParser.parseU2(byteChannel);
    }

    private int getFieldsCount() {
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

    private List<ConstantPoolItem> getConstantPoolItems() {
        try {
            byteChannel.position(10);
            logger.green("About to parse the first constant pool tag");
            return ConstantPoolItemsParser.parseItems(byteChannel, this.versionMetadata.constantPoolCount());


        } catch (Exception err) {
            logger.red("Failed to parse bytecode model!");
            logger.red(err.getMessage());
            System.exit(1);
            return null;
        }
    }


}
