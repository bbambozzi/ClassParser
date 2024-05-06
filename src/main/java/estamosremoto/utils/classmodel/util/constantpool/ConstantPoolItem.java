package estamosremoto.utils.classmodel.util.constantpool;

import estamosremoto.utils.classmodel.util.tag.ConstantPoolTag;

sealed public interface ConstantPoolItem permits CONSTANT_Class, CONSTANT_Double, CONSTANT_Dynamic, CONSTANT_Fieldref, CONSTANT_Float, CONSTANT_Integer, CONSTANT_InterfaceMethodref, CONSTANT_InvokeDynamic, CONSTANT_Long, CONSTANT_MethodHandle, CONSTANT_MethodType, CONSTANT_Methodref, CONSTANT_Module, CONSTANT_NameAndType, CONSTANT_Package, CONSTANT_String, CONSTANT_Utf8 {
    ConstantPoolTag tag();
}
