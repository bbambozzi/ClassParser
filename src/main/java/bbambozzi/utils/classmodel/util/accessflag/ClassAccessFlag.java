package bbambozzi.utils.classmodel.util.accessflag;

import java.util.*;

public enum ClassAccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MODULE(0x8000);
    public static final Map<Integer, ClassAccessFlag> publicMap;
    private static final Map<Integer, ClassAccessFlag> map;

    static {
        Map<Integer, ClassAccessFlag> elements = new HashMap<>();
        for (ClassAccessFlag flag : ClassAccessFlag.values()) {
            elements.put(flag.value, flag);
        }
        map = Collections.unmodifiableMap(elements);
        publicMap = Collections.unmodifiableMap(elements);
    }

    private final int value;

    ClassAccessFlag(int value) {
        this.value = value;
    }

    public static List<ClassAccessFlag> getMatching(int mask) {
        List<ClassAccessFlag> result = new ArrayList<>();
        for (ClassAccessFlag flag : ClassAccessFlag.values()) {
            if ((mask & flag.value) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public ClassAccessFlag parse(int value) {
        ClassAccessFlag flag = map.get(value);
        if (flag == null) {
            throw new IllegalArgumentException("Invalid access flag: " + value);
        }
        return flag;
    }
}
