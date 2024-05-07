package bbambozzi.utils.classmodel;

public record VersionMetadata(
        String magic,
        int minorVersion,
        int majorVersion,
        int constantPoolCount
) {
}
