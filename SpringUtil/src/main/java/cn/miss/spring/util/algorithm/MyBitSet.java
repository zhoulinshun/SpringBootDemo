package cn.miss.spring.util.algorithm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/11.
 */
public class MyBitSet {
    private static final int BITS_WORD = 6;
    /**
     * data的位数
     */
    private static final int LONG_BITS = 1 << BITS_WORD;

    private static final int INT_BITS = 31;

    private static final long SINGLE_MAX_BITS = ((long) (Integer.MAX_VALUE)) * LONG_BITS;

    private long size;

    private int oneSize;

    private int twoSize;

    private long[][] bigData;

    public static int getOnePosition(long position) {
        return getOneSize(position) - 1;
    }

    public static int getTwoPosition(long position) {
        final long onePosition = getOnePosition(position);
        if (onePosition == 0) {
            return (int) (position / LONG_BITS);
        }
        final long t = (position - (onePosition * SINGLE_MAX_BITS)) >> BITS_WORD;
        return (int) t;
    }

    /**
     * 获取对应的bit值
     *
     * @param position
     * @return
     */
    public static int getBitValue(long position) {
        return 1 << position % LONG_BITS;
    }

    public static int getOneSize(long size) {
        if (size < SINGLE_MAX_BITS) {
            return 1;
        }
        return (int) ((size - 1) / SINGLE_MAX_BITS + 1);
    }

    public static int getTwoSize(long size) {
        if (size < SINGLE_MAX_BITS) {
            return (int) ((size - 1) / LONG_BITS + 1);
        }
        return Integer.MAX_VALUE;
    }

    public MyBitSet(long size) {
        if (size < 0) {
            throw new IllegalArgumentException("size < 0 :" + size);
        }
        this.size = size;
        this.oneSize = getOneSize(size);
        this.twoSize = getTwoSize(size);
        bigData = new long[oneSize][twoSize];
    }

    public void set(long position) {
        set(position, true);
    }


    public void set(long position, boolean value) {
        final int onePosition = getOnePosition(position);
        final int twoPosition = getTwoPosition(position);
        final int bitValue = getBitValue(position);
        if (value) {
            bigData[onePosition][twoPosition] = bigData[onePosition][twoPosition] | bitValue;
        } else {
            bigData[onePosition][twoPosition] = bigData[onePosition][twoPosition] & ~bitValue;
        }
    }

    public boolean get(long position) {
        final int onePosition = getOnePosition(position);
        final int twoPosition = getTwoPosition(position);
        final int bitValue = getBitValue(position);
        return (bigData[onePosition][twoPosition] & bitValue) != 0;
    }

    public void del(long position) {
        set(position, false);
    }

    public static void main(String[] args) {
        long l = Integer.MAX_VALUE;

        final MyBitSet myBitSet = new MyBitSet(l + 1);
        myBitSet.set(1000);
        myBitSet.set(1001);
        myBitSet.set(999);
        System.out.println(myBitSet.get(1000));
        myBitSet.del(1000);
        System.out.println(myBitSet.get(1000));
        System.out.println(myBitSet.get(999));
    }

    public static void memory() {
        MemoryMXBean memorymbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage usage = memorymbean.getHeapMemoryUsage();
        System.out.println("INIT HEAP: " + usage.getInit());
        System.out.println("MAX HEAP: " + usage.getMax());
        System.out.println("USE HEAP: " + usage.getUsed());
        System.out.println("\nFull Information:");
        System.out.println("Heap Memory Usage: "
                + memorymbean.getHeapMemoryUsage());
        System.out.println("Non-Heap Memory Usage: "
                + memorymbean.getNonHeapMemoryUsage());

        List<String> inputArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
    }


    public static void test(long length1, long length2) {
        final long i = 12 + 4 + length2 * 8;
        long i2 = (i >> 11 >> 11 >> 11) * length1;
        System.out.println(i2);
    }

}
