package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.bytechannel.ByteChannelParser;
import estamosremoto.utils.classmodel.util.properties.HasBytes;
import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

import java.nio.channels.ByteChannel;
import java.nio.charset.StandardCharsets;

public record CONSTANT_Utf8(ConstantPoolTag tag, int length, byte[] bytes) implements ConstantPoolItem, HasBytes {
    public CONSTANT_Utf8(ByteChannel channel) {
        int length = ByteChannelParser.parseU2(channel);
        byte[] bytes = ByteChannelParser.parseBytes(channel, length);
        this(ConstantPoolTag.CONSTANT_Utf8, length, bytes);
    }

    @Override
    public String toString() {
        return "CONSTANT_Utf8[tag=" + tag.toString() + ", " + "bytes=" + new String(bytes, StandardCharsets.UTF_8)+ "]";
    }
}
