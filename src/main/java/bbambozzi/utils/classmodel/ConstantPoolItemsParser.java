package bbambozzi.utils.classmodel;

import bbambozzi.utils.classmodel.util.constantpool.ConstantPoolItem;
import bbambozzi.utils.classmodel.util.constantpool.*;
import bbambozzi.utils.classmodel.util.tag.ConstantPoolTag;
import bbambozzi.utils.logger.ColorLogger;

import java.nio.channels.ByteChannel;
import java.util.ArrayList;
import java.util.List;

public record ConstantPoolItemsParser() {
    private static final ColorLogger logger = new ColorLogger();

    public static List<ConstantPoolItem> parseItems(ByteChannel channel, int constantPoolCount) {
        List<ConstantPoolItem> constantPoolItems = new ArrayList<>();
        for (int i = 0; i < constantPoolCount - 1; i++) {
            ConstantPoolTag tag = ConstantPoolTag.parse(channel);
            ConstantPoolItem constantPoolItemToAdd = switch (tag) {
                case CONSTANT_Class -> new CONSTANT_Class(channel);
                case CONSTANT_Fieldref -> new CONSTANT_Fieldref(channel);
                case CONSTANT_Methodref -> new CONSTANT_Methodref(channel);
                case CONSTANT_InterfaceMethodref -> new CONSTANT_InterfaceMethodref(channel);
                case CONSTANT_String -> new CONSTANT_String(channel);
                case CONSTANT_Integer -> new CONSTANT_Integer(channel);
                case CONSTANT_Float -> new CONSTANT_Float(channel);
                case CONSTANT_Long -> new CONSTANT_Long(channel);
                case CONSTANT_Double -> new CONSTANT_Double(channel);
                case CONSTANT_NameAndType -> new CONSTANT_NameAndType(channel);
                case CONSTANT_Utf8 -> new CONSTANT_Utf8(channel);
                case CONSTANT_MethodHandle -> new CONSTANT_MethodHandle(channel);
                case CONSTANT_MethodType -> new CONSTANT_MethodType(channel);
                case CONSTANT_Dynamic -> new CONSTANT_Dynamic(channel);
                case CONSTANT_InvokeDynamic -> new CONSTANT_InvokeDynamic(channel);
                case CONSTANT_Package -> new CONSTANT_Package(channel);
                case CONSTANT_Module -> new CONSTANT_Module(channel);
            };
            logger.green("Added Constant pool constantpool = " + constantPoolItemToAdd);
            constantPoolItems.add(constantPoolItemToAdd);
        }
        return constantPoolItems;
    }
}
