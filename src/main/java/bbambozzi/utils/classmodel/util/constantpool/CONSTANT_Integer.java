package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Integer(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public CONSTANT_Integer(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Integer, ByteChannelParser.parseU4(channel));
    }
}
