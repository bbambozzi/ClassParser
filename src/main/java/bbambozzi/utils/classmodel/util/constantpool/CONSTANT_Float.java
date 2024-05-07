package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Float(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public CONSTANT_Float(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Float, ByteChannelParser.parseU4(channel));
    }
}
