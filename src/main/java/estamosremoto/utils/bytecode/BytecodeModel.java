package estamosremoto.utils.bytecode;

public record BytecodeModel(
        String magic,
        short minorVersion,
        short majorVersion,
        short constantPoolCount
) {
}
