package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasDescriptorIndex;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_NameAndType(ConstantPoolTag tag, int name_index, int descriptor_index) implements ConstantPoolItem, HasDescriptorIndex {
    public CONSTANT_NameAndType(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_NameAndType, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
