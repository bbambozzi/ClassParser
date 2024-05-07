package bbambozzi.utils.classmodel.util.constantpool;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.properties.HasNameIndex;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Package(ConstantPoolTag tag, int name_index) implements ConstantPoolItem, HasNameIndex {

    public CONSTANT_Package(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Package, ByteChannelParser.parseU2(channel));
    }
}
