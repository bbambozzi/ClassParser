package estamosremoto.utils.bytecode.util.constantpool;


import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.attributes.ClassIndex;
import estamosremoto.utils.bytecode.util.attributes.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Fieldref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem, ClassIndex {

    public CONSTANT_Fieldref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Fieldref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }

}
