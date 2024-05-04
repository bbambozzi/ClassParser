package estamosremoto.parser;

import estamosremoto.utils.bytecode.BytecodeModel;
import estamosremoto.utils.bytecode.ConstantPoolItemsParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class BytecodeParser {
    static final ColorLogger logger = new ColorLogger();
    private final byte[] bytecodeBytes;
    private final SeekableByteChannel byteChannel;
    private final BytecodeModel bytecodeModel;

    public BytecodeParser(Path pathToBytecode) {
        this.bytecodeBytes = parseBytes(pathToBytecode);
        this.byteChannel = getByteChannel(pathToBytecode);
        this.bytecodeModel = getBytecodeModel();
        logger.green("bytecode model = " + bytecodeModel.toString());
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


    /**
     * @param path Path to the .class that contains Java Bytecode
     */
    private static byte[] parseBytes(Path path) {
        try {
            byte[] bytes = Files.readAllBytes(path);
            String magic = toHex(bytes[0]) + toHex(bytes[1]) + toHex(bytes[2]) + toHex(bytes[3]);
            if (!magic.equals("CAFEBABE")) {
                throw new IllegalArgumentException("Input magic not correct");
            }
            return bytes;
        } catch (IllegalArgumentException | IOException e) {
            if (e instanceof IllegalArgumentException) {
                logger.red("Input file is not valid java bytecode.");
            }
            if (e instanceof IOException) {
                logger.red("Could not parse input file = " + e);
            }
            System.exit(1);
            return null;
        }
    }

    private BytecodeModel getBytecodeModel() {
        try {
            byteChannel.position(0);
            String magicString = toHex(parseU4());

            int minorVersion = parseU2();
            int majorVersion = parseU2();
            int constantPoolCount = parseU2();
            var ans = new BytecodeModel(magicString, minorVersion, majorVersion, constantPoolCount);
            logger.green("bytecode model metadata = " + ans);
            logger.green("About to parse the first constant pool tag");
            ConstantPoolItemsParser.parseItems(byteChannel, constantPoolCount);


            return ans;
        } catch (Exception err) {
            logger.red("Failed to parse bytecode model!");
            logger.red(err.getMessage());
            System.exit(1);
            return null;
        }
    }


    private byte parseU1() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        try {
            byteChannel.read(buffer);
            return buffer.get(0);
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
        }
        System.exit(1);
        return buffer.get(0);
    }

    private int parseU2() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        try {
            byteChannel.read(buffer);
            return buffer.getShort(0);
        } catch (IOException e) {
            logger.red("Failed to parse U2 at position " + buffer.position());
        }
        System.exit(1);
        return buffer.getShort(0);
    }


    private byte[] parseU4() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        try {
            byteChannel.read(buffer);
            return buffer.array();
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
        }
        System.exit(1);
        return buffer.array();
    }

}
