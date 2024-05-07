package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_String(ConstantPoolTag tag, int string_index) implements ConstantPoolItem {

    public CONSTANT_String(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_String, ByteChannelParser.parseU2(channel));
    }
}
