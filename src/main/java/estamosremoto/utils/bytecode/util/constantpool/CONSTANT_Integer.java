package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Integer(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public CONSTANT_Integer(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Integer, ByteChannelParser.parseU4(channel));
    }
}
