package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_InvokeDynamic(ConstantPoolTag tag, int bootstrap_method_attr_index, int name_and_type_index) implements ConstantPoolItem {
    public CONSTANT_InvokeDynamic(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_InvokeDynamic, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }
}
