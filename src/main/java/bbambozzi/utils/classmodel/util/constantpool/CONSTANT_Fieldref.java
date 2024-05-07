package estamosremoto.utils.classmodel.util.constantpool;


import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasClassIndex;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;

public record CONSTANT_Fieldref(ConstantPoolTag tag, int class_index, int name_and_type_index) implements ConstantPoolItem, HasClassIndex {

    public CONSTANT_Fieldref(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Fieldref, ByteChannelParser.parseU2(channel), ByteChannelParser.parseU2(channel));
    }

}
