package estamosremoto.parser;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.VersionMetadata;
import estamosremoto.utils.bytecode.ConstantPoolItemsParser;
import estamosremoto.utils.bytecode.util.constantpool.ConstantPoolItem;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BytecodeParser {
    static final ColorLogger logger = new ColorLogger();
    private final SeekableByteChannel byteChannel;
    private final VersionMetadata versionMetadata;
    private final List<ConstantPoolItem> constantPoolItems;

    public BytecodeParser(Path pathToBytecode) {
        this.byteChannel = getByteChannel(pathToBytecode);
        this.versionMetadata = getVersionMetadata();
        this.constantPoolItems = getConstantPoolItems();
        logger.green("bytecode model = " + versionMetadata.toString());
        logger.green("constant pool items = " + constantPoolItems);
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
