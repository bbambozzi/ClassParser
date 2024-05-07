package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Double(ConstantPoolTag tag, byte[] bytes) implements ConstantPoolItem {
    public CONSTANT_Double(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Double, ByteChannelParser.parseU4(channel));
    }
}