package estamosremoto.utils.bytecode;

public record VersionMetadata(
        String magic,
        int minorVersion,
        int majorVersion,
        int constantPoolCount
) {
}
