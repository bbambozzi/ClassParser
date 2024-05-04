package estamosremoto.utils.bytecode.util.item;

import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;

import java.io.DataInput;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;

public record CONSTANT_Class(ConstantPoolTag tag, int nameIndex) implements ConstantPoolItem {
    public CONSTANT_Class(ByteChannel channel) {
        this(ConstantPoolTag.CONSTANT_Class, getNameIndex(channel));
    }

    private static int getNameIndex(ByteChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        var x = buffer.getShort();
        return 0;
    }
}
