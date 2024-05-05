package estamosremoto.utils.bytecode.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Package(ConstantPoolTag tag, int name_index) implements ConstantPoolItem {

    public CONSTANT_Package(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Package, ByteChannelParser.parseU2(channel));
    }
}
