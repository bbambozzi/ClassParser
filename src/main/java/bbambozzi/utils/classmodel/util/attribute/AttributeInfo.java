package bbambozzi.utils.classmodel.util.attribute;

import bbambozzi.utils.bytechannel.ByteChannelParser;

import java.nio.channels.ByteChannel;

public record AttributeInfo(int attribute_name_index, long attribute_length, byte[] info) {
    public AttributeInfo(ByteChannel channel) {
        int attributeIndex = ByteChannelParser.parseU2(channel);
        System.out.println("attr index = " + attributeIndex);
        long attributeLength = ByteChannelParser.parseU4L(channel);
        int intl = (int) attributeLength;
        System.out.println("attr length: " + attributeLength);
        byte[] info = ByteChannelParser.parseBytes(channel, intl);
        System.out.println("getting attr bytes");
        this(attributeIndex, attributeLength, info);
    }
}
