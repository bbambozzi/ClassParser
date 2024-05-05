package estamosremoto.utils.bytecode.util.method;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.bytecode.util.accessflag.MethodAccessFlag;
import estamosremoto.utils.bytecode.util.properties.HasDescriptorIndex;
import estamosremoto.utils.bytecode.util.properties.HasNameIndex;

import java.nio.channels.ByteChannel;
import java.util.List;

public record Method(List<MethodAccessFlag> accessFlags, int name_index, int descriptor_index,
                     int attributes_count, byte[] attribute_info) implements HasNameIndex, HasDescriptorIndex {
    public Method(ByteChannel channel) {
        List<MethodAccessFlag> flags = MethodAccessFlag.getMatching(ByteChannelParser.parseU2(channel));
        int nameIndex = ByteChannelParser.parseU2(channel);
        int descriptorIndex = ByteChannelParser.parseU2(channel);
        int attributesCount = ByteChannelParser.parseU2(channel);
        this(flags, nameIndex, descriptorIndex, attributesCount, ByteChannelParser.parseBytes(channel, attributesCount));
    }
}
