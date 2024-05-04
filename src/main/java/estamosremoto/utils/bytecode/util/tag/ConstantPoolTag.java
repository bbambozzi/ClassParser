package estamosremoto.utils.bytecode.util.tag;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ConstantPoolTag {
    CONSTANT_Class((byte) 7),
    CONSTANT_Fieldref((byte) 9),
    CONSTANT_Methodref((byte) 10),
    CONSTANT_InterfaceMethodref((byte) 11),
    CONSTANT_String((byte) 8),
    CONSTANT_Integer((byte) 3),
    CONSTANT_Float((byte) 4),
    CONSTANT_Long((byte) 5),
    CONSTANT_Double((byte) 6),
    CONSTANT_NameAndType((byte) 12),
    CONSTANT_Utf8((byte) 1),
    CONSTANT_MethodHandle((byte) 15),
    CONSTANT_MethodType((byte) 16),
    CONSTANT_Dynamic((byte) 17),
    CONSTANT_InvokeDynamic((byte) 18),
    CONSTANT_Module((byte) 19),
    CONSTANT_Package((byte) 20);

    private final static Map<Byte, ConstantPoolTag> tags;

    static {
        HashMap<Byte, ConstantPoolTag> ts = new HashMap<>();
        for (ConstantPoolTag cpool : ConstantPoolTag.values()) {
            ts.put(cpool.val, cpool);
        }
        tags = Collections.unmodifiableMap(ts);
    }

    private final byte val;

    ConstantPoolTag(byte value) {
        this.val = value;
    }

    public static ConstantPoolTag parse(ByteBuffer buffer) {
        ConstantPoolTag ans = tags.get(buffer.get(0));
        if (ans == null) {
            throw new IllegalArgumentException("ByteBuffer does not evaluate to a valid CpInfo tag");
        }
        return ans;
    }

    public static ConstantPoolTag parse(ByteChannel channel) {
        ByteBuffer buffer = ByteBuffer.allocate(1);
        try {
            channel.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse the constant pool tag from the byte channel!");
        }
        return parse(buffer);
    }
}
