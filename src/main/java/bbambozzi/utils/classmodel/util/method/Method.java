package bbambozzi.utils.classmodel.util.method;

import bbambozzi.utils.bytechannel.ByteChannelParser;
import bbambozzi.utils.classmodel.util.accessflag.MethodAccessFlag;
import bbambozzi.utils.classmodel.util.attribute.AttributeInfo;
import bbambozzi.utils.classmodel.util.properties.HasDescriptorIndex;
import bbambozzi.utils.classmodel.util.properties.HasNameIndex;

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
