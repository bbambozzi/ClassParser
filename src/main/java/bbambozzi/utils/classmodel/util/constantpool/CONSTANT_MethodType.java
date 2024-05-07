package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasDescriptorIndex;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_MethodType(ConstantPoolTag tag, int descriptor_index) implements ConstantPoolItem, HasDescriptorIndex {
    public CONSTANT_MethodType(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_MethodType, ByteChannelParser.parseU2(channel));
    }
}
