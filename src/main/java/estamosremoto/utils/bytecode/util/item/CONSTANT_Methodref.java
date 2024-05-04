package estamosremoto.utils.bytecode.util.item;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Methodref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem {
    public CONSTANT_Methodref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Methodref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
