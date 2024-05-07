package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.properties.HasDescriptorIndex;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_NameAndType(ConstantPoolTag tag, int name_index, int descriptor_index) implements ConstantPoolItem, HasDescriptorIndex {
    public CONSTANT_NameAndType(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_NameAndType, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
