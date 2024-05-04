package estamosremoto.utils.bytecode;

import estamosremoto.utils.bytecode.util.item.CONSTANT_Class;
import estamosremoto.utils.bytecode.util.item.ConstantPoolItem;
import estamosremoto.utils.bytecode.util.tag.ConstantPoolTag;
import estamosremoto.utils.logger.ColorLogger;

import java.nio.channels.ByteChannel;
import java.util.ArrayList;
import java.util.List;

public record ConstantPoolItemsParser() {
    private static final ColorLogger logger = new ColorLogger();

    public static List<ConstantPoolItem> parseItems(ByteChannel channel, int constantPoolCount) {
        List<ConstantPoolItem> constantPoolItems = new ArrayList<>();
        for (int i = 0; i < constantPoolCount - 1; i++) {
            ConstantPoolTag tag = ConstantPoolTag.parse(channel);
            switch (tag) {
                default -> {
                    throw new IllegalArgumentException(tag + " is not yet implemented");
                }
            }
        }
        return constantPoolItems;
    }
}
