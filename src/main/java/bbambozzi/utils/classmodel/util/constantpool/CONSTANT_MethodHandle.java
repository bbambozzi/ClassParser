package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_MethodHandle(ConstantPoolTag tag, int reference_kind, int reference_index) implements ConstantPoolItem {

    public CONSTANT_MethodHandle(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_MethodHandle, ByteChannelParser.parseU1(channel), ByteChannelParser.parseU2(channel));
    }
}
