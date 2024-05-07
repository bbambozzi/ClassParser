package bbambozzi.utils.classmodel.util.accessflag;

import java.util.*;

public enum MethodAccessFlag {
    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100),
    ACC_ABSTRACT(0x0400),
    ACC_STRICT(0x0800),
    ACC_SYNTHETIC(0x1000);


    public static final Map<Integer, MethodAccessFlag> publicMap;
    private static final Map<Integer, MethodAccessFlag> map;

    static {
        Map<Integer, MethodAccessFlag> elements = new HashMap<>();
        for (MethodAccessFlag flag : MethodAccessFlag.values()) {
            elements.put(flag.value, flag);
        }
        map = Collections.unmodifiableMap(elements);
        publicMap = Collections.unmodifiableMap(elements);
    }

    private final int value;


    MethodAccessFlag(int value) {
        this.value = value;
    }

    public static List<MethodAccessFlag> getMatching(int mask) {
        System.out.println("got mask " + mask);
        List<MethodAccessFlag> result = new ArrayList<>();
        for (MethodAccessFlag flag : MethodAccessFlag.values()) {
            if ((mask & flag.value) != 0) {
                result.add(flag);
            }
        }
        return result;
    }

    public MethodAccessFlag parse(int value) {
        MethodAccessFlag flag = map.get(value);
        if (flag == null) {
            throw new IllegalArgumentException("Invalid access flag: " + value);
        }
        return flag;
    }
}
