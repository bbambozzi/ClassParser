package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasNameIndex;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Class(ConstantPoolTag tag, int name_index) implements ConstantPoolItem, HasNameIndex {
    public CONSTANT_Class(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Class, getNameIndex(channel));
    }

    private static int getNameIndex(ByteChannel channel) {
        return ByteChannelParser.parseU2(channel);
    }
}