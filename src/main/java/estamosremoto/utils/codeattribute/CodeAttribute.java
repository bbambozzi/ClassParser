package estamosremoto.utils.codeattribute;

import estamosremoto.utils.bytechannel.ByteChannelParser;

import java.nio.channels.ByteChannel;

/**
 * 4.7.3. The Code Attribute
 *
 * TODO
 *     u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
 */
public record CodeAttribute(int attribute_name_index, long attribute_length, int max_stack, int max_locals, long code_length, byte[] code) {

    public CodeAttribute(ByteChannel channel) {
        int attributeNameIndex = ByteChannelParser.parseU2(channel);
        long attributeLength = ByteChannelParser.parseU4L(channel);
        int maxStack = ByteChannelParser.parseU2(channel);
        int maxLocals = ByteChannelParser.parseU2(channel);
        int codeLength = ByteChannelParser.parseU2(channel);
        byte[] codeBytes = ByteChannelParser.parseBytes(channel, codeLength);
        this(attributeNameIndex, attributeLength, maxStack, maxLocals, codeLength, codeBytes);
    }

}
