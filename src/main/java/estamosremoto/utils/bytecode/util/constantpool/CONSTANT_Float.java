package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Float(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public CONSTANT_Float(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Float, ByteChannelParser.parseU4(channel));
    }
}