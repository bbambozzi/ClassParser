package estamosremoto.utils.bytechannel;

import estamosremoto.utils.logger.ColorLogger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public class ByteChannelParser {

    private static final ColorLogger logger = new ColorLogger();

    private byte parseU1(ByteChannel byteChannel) {
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

    private short parseU2(ByteChannel byteChannel) {
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


    private byte[] parseU4(ByteChannel byteChannel) {
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
