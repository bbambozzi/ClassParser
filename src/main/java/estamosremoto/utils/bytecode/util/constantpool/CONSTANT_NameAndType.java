package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.attributes.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_NameAndType(ConstantPoolTag tag, int name_index, int descriptor_index) implements ConstantPoolItem {
    public CONSTANT_NameAndType(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_NameAndType, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
