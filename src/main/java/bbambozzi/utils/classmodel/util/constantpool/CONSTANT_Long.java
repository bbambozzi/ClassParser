package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Long(ConstantPoolTag tag, byte[] high_bytes, byte[] low_bytes) implements ConstantPoolItem {
    public CONSTANT_Long(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Long, ByteChannelParser.parseU4(channel), ByteChannelParser.parseU4(channel));
    }
}
