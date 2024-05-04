package estamosremoto.utils.bytecode.util.item;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;



public record Constant_Float(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public Constant_Float(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Float, ByteChannelParser.parseU4(channel));
    }
}
