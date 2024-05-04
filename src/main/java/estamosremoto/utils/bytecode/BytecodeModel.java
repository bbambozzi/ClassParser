package estamosremoto.utils.bytecode;

public record BytecodeModel(
        String magic,
        int minorVersion,
        int majorVersion,
        int constantPoolCount
) {
}
