package estamosremoto.utils.bytecode;

import estamosremoto.utils.bytecode.util.ConstantPoolTag;

import java.nio.ByteBuffer;

public record CpInfo(ConstantPoolTag tag) {
    public CpInfo(ByteBuffer buffer) {
        this(ConstantPoolTag.parse(buffer));
    }
}
