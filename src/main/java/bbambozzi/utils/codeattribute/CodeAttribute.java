package bbambozzi.utils.codeattribute;

import bbambozzi.utils.bytechannel.ByteChannelParser;

import java.nio.channels.ReadableByteChannel;

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
public record CodeAttribute(int max_stack, int max_locals, long code_length, byte[] code) {

    public CodeAttribute(ReadableByteChannel channel) {
//        int attributeNameIndex overlaps
//        long attributeLength overlaps, the channel starts at the 0th byte of the maxstack
        int maxStack = ByteChannelParser.parseU2(channel);
        int maxLocals = ByteChannelParser.parseU2(channel);
        long codeLength = ByteChannelParser.parseU4L(channel);
        byte[] codeBytes = ByteChannelParser.parseBytes(channel, codeLength);
        this(maxStack, maxLocals, codeLength, codeBytes);
    }

}
