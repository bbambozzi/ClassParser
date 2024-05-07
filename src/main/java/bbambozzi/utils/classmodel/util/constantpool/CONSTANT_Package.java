package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasNameIndex;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Package(ConstantPoolTag tag, int name_index) implements ConstantPoolItem, HasNameIndex {

    public CONSTANT_Package(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Package, ByteChannelParser.parseU2(channel));
    }
}
