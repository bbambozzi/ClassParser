package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.attributes.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_MethodType(ConstantPoolTag tag, int descriptor_index) implements ConstantPoolItem {
    public CONSTANT_MethodType(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_MethodType, ByteChannelParser.parseU2(channel));
    }
}
