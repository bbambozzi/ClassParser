package estamosremoto.utils.bytecode.util.method;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.accessflag.MethodAccessFlag;
import estamosremoto.utils.bytecode.util.attribute.AttributeInfo;
import estamosremoto.utils.bytecode.util.properties.HasDescriptorIndex;
import estamosremoto.utils.bytecode.util.properties.HasNameIndex;

import java.nio.channels.ByteChannel;
import java.util.ArrayList;
import java.util.List;

public record Method(List<MethodAccessFlag> accessFlags, int name_index, int descriptor_index,
                     int attributes_count, List<AttributeInfo> attribute_info) implements HasNameIndex, HasDescriptorIndex {
    public Method(ByteChannel channel) {
        List<MethodAccessFlag> flags = MethodAccessFlag.getMatching(ByteChannelParser.parseU2(channel));
        int nameIndex = ByteChannelParser.parseU2(channel);
        int descriptorIndex = ByteChannelParser.parseU2(channel);
        int attributesCount = ByteChannelParser.parseU2(channel);
        List<AttributeInfo> attributeInfos = new ArrayList<>();
        for (int i = 0; i < attributesCount; i++) {
            System.out.println("boutta parse the attribute info");
            attributeInfos.add(new AttributeInfo(channel));
        }
        this(flags, nameIndex, descriptorIndex, attributesCount, attributeInfos);
    }
}
