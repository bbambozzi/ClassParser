package estamosremoto.parser;

import estamosremoto.utils.bytecode.BytecodeModel;
import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.lang.reflect.Array;
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
            ByteBuffer magic = ByteBuffer.allocate(4);
            ByteBuffer minor = ByteBuffer.allocate(2);
            ByteBuffer major = ByteBuffer.allocate(2);
            ByteBuffer constantPoolCnt = ByteBuffer.allocate(2);
            byteChannel.read(magic);
            byteChannel.read(minor);
            byteChannel.read(major);
            byteChannel.read(constantPoolCnt);
            String magicString = toHex(magic.array());
            short minorVersion = minor.getShort(0);
            short majorVersion = major.getShort(0);
            short constantPoolCount = constantPoolCnt.getShort(0);
            return new BytecodeModel(magicString, minorVersion, majorVersion, constantPoolCount);
        } catch (Exception err) {
            logger.red("Failed to parse bytecode model!");
            logger.red(err.getMessage());
            System.exit(1);
            return null;
        }
    }

}
