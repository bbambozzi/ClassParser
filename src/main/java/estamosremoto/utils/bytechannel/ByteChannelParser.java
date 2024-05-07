package estamosremoto.utils.bytechannel;

import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.ReadableByteChannel;

public class ByteChannelParser {

    public static final ColorLogger logger = new ColorLogger();

    public static int parseU1(ReadableByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        try {
            byteChannel.read(buffer);
            buffer.flip();
            return buffer.get() & 0xff;
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
        }
        System.exit(1);
        return buffer.get(0);
    }

    public static int parseU2(ReadableByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        try {
            byteChannel.read(buffer);
            buffer.flip();
            return buffer.getShort(0) & 0xffff;
        } catch (IOException e) {
            logger.red("Failed to parse U2 at position " + buffer.position());
            System.exit(1);
            return buffer.getShort(0);
        }
    }


    public static byte[] parseU4(ReadableByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        try {
            byteChannel.read(buffer);
            buffer.flip();
            return buffer.array();
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
            System.exit(1);
            return buffer.array();
        }
    }

    public static long parseU4L(ReadableByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        try {
            int bytesRead = byteChannel.read(buffer);
            if (bytesRead != 4) {
                logger.red("could not read 4 bytes");
            }
            buffer.flip();
            int unsignedInt = buffer.getInt();
            long ans = unsignedInt & 0xFFFFFFFFL;
            buffer.clear();
            return ans;
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
            System.exit(1);
            return -1;
        }
    }


    public static byte[] parseBytes(ReadableByteChannel byteChannel, int amount) {
        ByteBuffer buffer = ByteBuffer.allocate(amount);
        try {
            byteChannel.read(buffer);
            return buffer.array();
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
            System.exit(1);
            return buffer.array();
        }
    }

}
