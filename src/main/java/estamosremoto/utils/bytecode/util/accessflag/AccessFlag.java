package estamosremoto.utils.bytecode.util.accessflag;

import java.util.*;

public enum AccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MODULE(0x8000);
    public static final Map<Integer, AccessFlag> publicMap;
    private static final Map<Integer, AccessFlag> map;

    static {
        Map<Integer, AccessFlag> elements = new HashMap<>();
        for (AccessFlag flag : AccessFlag.values()) {
            elements.put(flag.value, flag);
        }
        map = Collections.unmodifiableMap(elements);
        publicMap = Collections.unmodifiableMap(elements);
    }

    private final int value;

    AccessFlag(int value) {
        this.value = value;
    }

    public static List<AccessFlag> getMatching(int mask) {
        System.out.println("got mask " + mask);
        List<AccessFlag> result = new ArrayList<>();
        for (AccessFlag flag : AccessFlag.values()) {
            if ((mask & flag.value) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public AccessFlag parse(int value) {
        AccessFlag flag = map.get(value);
        if (flag == null) {
            throw new IllegalArgumentException("Invalid access flag: " + value);
        }
        return flag;
    }
}
