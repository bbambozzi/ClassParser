package estamosremoto.utils.classmodel.util.field;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.accessflag.FieldAccessFlag;
import estamosremoto.utils.classmodel.util.attribute.AttributeInfo;
import estamosremoto.utils.classmodel.util.properties.HasDescriptorIndex;
import estamosremoto.utils.classmodel.util.properties.HasNameIndex;

import java.nio.channels.ByteChannel;
import java.util.List;

public record Field(List<FieldAccessFlag> access_flags, int name_index, int descriptor_index, int attributes_count,
                    AttributeInfo attribute_info) implements HasNameIndex, HasDescriptorIndex {

    public Field(ByteChannel channel) {
        List<FieldAccessFlag> access_flags = FieldAccessFlag.getMatching(ByteChannelParser.parseU2(channel));
        int nameIndex = ByteChannelParser.parseU2(channel);
        int descriptor_index = ByteChannelParser.parseU2(channel);
        int attributes_count = ByteChannelParser.parseU2(channel);
        AttributeInfo attribute_info = new AttributeInfo(channel);
        this(access_flags, nameIndex, descriptor_index, attributes_count, attribute_info);
    }
}
