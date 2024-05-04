package estamosremoto.utils.bytecode.util.item;


import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_InterfaceMethodref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem {
    public CONSTANT_InterfaceMethodref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Fieldref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}

