package bbambozzi.utils.classmodel.util.constantpool;


import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.properties.HasClassIndex;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Fieldref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem, HasClassIndex {

    public CONSTANT_Fieldref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Fieldref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }

}
