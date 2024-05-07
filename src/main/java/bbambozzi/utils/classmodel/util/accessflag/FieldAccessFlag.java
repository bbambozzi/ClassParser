package estamosremoto.utils.classmodel.util.accessflag;

import java.util.*;

public enum FieldAccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_VOLATILE(0x0040),
    ACC_TRANSIENT(0x0080),
    ACC_SYNTHETIC(0x1000),
    ACC_ENUM(0x4000);

    public static final Map<Integer, FieldAccessFlag> publicMap;
    private static final Map<Integer, FieldAccessFlag> map;

    static {
        Map<Integer, FieldAccessFlag> elements = new HashMap<>();
        for (FieldAccessFlag flag : FieldAccessFlag.values()) {
            elements.put(flag.value, flag);
        }
        map = Collections.unmodifiableMap(elements);
        publicMap = Collections.unmodifiableMap(elements);
    }

    private final int value;

    FieldAccessFlag(int value) {
        this.value = value;
    }

    public static List<FieldAccessFlag> getMatching(int mask) {
        System.out.println("got mask " + mask);
        List<FieldAccessFlag> result = new ArrayList<>();
        for (FieldAccessFlag flag : FieldAccessFlag.values()) {
            if ((mask & flag.value) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public FieldAccessFlag parse(int value) {
        FieldAccessFlag flag = map.get(value);
        if (flag == null) {
            throw new IllegalArgumentException("Invalid access flag: " + value);
        }
        return flag;
    }
}