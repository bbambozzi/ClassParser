package estamosremoto.utils.bytecode.util.method;

import estamosremoto.utils.bytecode.util.accessflag.AccessFlag;
import estamosremoto.utils.bytecode.util.attributes.HasDescriptorIndex;
import estamosremoto.utils.bytecode.util.attributes.HasNameIndex;

import java.util.List;

public record Method(List<AccessFlag> accessFlags, int name_index, int descriptor_index) implements HasNameIndex, HasDescriptorIndex {
}
