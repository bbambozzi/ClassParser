package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.attributes.HasClassIndex;
import estamosremoto.utils.bytecode.util.attributes.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Methodref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem, HasClassIndex {
    public CONSTANT_Methodref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Methodref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
