package estamosremoto.utils.bytecode.util.attribute;

import estamosremoto.utils.bytechannel.ByteChannelParser;

import java.nio.channels.ByteChannel;

public record AttributeInfo(int attribute_name_index, long attribute_length) { // todo info
    public AttributeInfo(ByteChannel channel) {
        int attributeIndex = ByteChannelParser.parseU2(channel);
        long attributeLength = ByteChannelParser.parseU4L(channel);
        this(attributeIndex, attributeLength);
    }
}
