package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Dynamic(ConstantPoolTag tag, int bootstrap_method_attr_index, int name_and_type_index) implements ConstantPoolItem {
    public CONSTANT_Dynamic(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Dynamic, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
