package estamosremoto.utils.bytecode.util.item;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Module(ConstantPoolTag tag, int name_index) implements ConstantPoolItem {

    public CONSTANT_Module(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Module, ByteChannelParser.parseU2(channel));
    }
}
