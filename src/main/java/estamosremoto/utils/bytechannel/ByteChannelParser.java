package estamosremoto.utils.bytechannel;

import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ByteChannelParser {

    public static final ColorLogger logger = new ColorLogger();

    public static int parseU1(ByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        try {
            byteChannel.read(buffer);
            return buffer.get(0) & 0xff;
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
        }
        System.exit(1);
        return buffer.get(0);
    }

    public static int parseU2(ByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        try {
            byteChannel.read(buffer);
            return buffer.getShort(0) & 0xffff;
        } catch (IOException e) {
            logger.red("Failed to parse U2 at position " + buffer.position());
            System.exit(1);
            return buffer.getShort(0);
        }
    }


    public static byte[] parseU4(ByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        try {
            byteChannel.read(buffer);
            return buffer.array();
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
            System.exit(1);
            return buffer.array();
        }
    }

    public static long parseU4L(ByteChannel byteChannel) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        try {
            byteChannel.read(buffer);
            int unsignedInt = buffer.getInt();
            long ans =  unsignedInt & 0xffffffffL;
            return ans;
        } catch (IOException e) {
            logger.red("Failed to parse U1 at position " + buffer.position());
            System.exit(1);
            return -1;
        }
    }

    public static byte[] parseBytes(ByteChannel byteChannel, int amount) {
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
