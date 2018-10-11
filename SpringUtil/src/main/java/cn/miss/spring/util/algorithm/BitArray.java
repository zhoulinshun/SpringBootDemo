package cn.miss.spring.util.algorithm;

/**
 * @Author: zhoulinshun
 * @Description:
 * @Date: Created in 2018/10/11.
 */
public class BitArray {
    /**
     *
     */
    private static final int BITS_WORD = 6;

    /**
     * 存储长度
     */
    private static final int BITS_PADDING = 1 << BITS_WORD;

    private static final long MAX_SIZE = (((long) Integer.MAX_VALUE) + 1) << BITS_WORD + 1;

    private final int dataLength;
    private final long size;
    private long[] data;

    /**
     * 获取在数组中的位置
     *
     * @param position
     * @return
     */
    public static int getTruePosition(int position) {
        return position >> BITS_WORD;
    }

    /**
     * 检查传入的长度是否符合限制
     *
     * @param size
     */
    public static void checkSize(long size) {
        if (size > 0) {
            final long l = ((size - 1) >> BITS_WORD) + 1;
            if (l <= Integer.MAX_VALUE) {
                return;
            }
        }
        throw new IllegalArgumentException("out of rang:" + size);
    }

    /**
     * 获取对应的bit值
     *
     * @param position
     * @return
     */
    public static int getBitValue(int position) {
        return 1 << position % BITS_PADDING;
    }

    public static int getTrueSize(long size) {
        return (int) (((size - 1) >> BITS_WORD) + 1);
    }

    public BitArray(long size) {
        checkSize(size);
        this.size = size;
        dataLength = getTrueSize(size);
        data = new long[dataLength];
    }

    public BitArray(long[] data) {
        if (data == null) {
            throw new IllegalArgumentException("data == null");
        }
        this.data = data;
        dataLength = this.data.length;
        size = data.length << BITS_WORD;
    }

    public long getSize() {
        return size;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void set(int position) {
        set(position, true);
    }

    public void set(int position, boolean value) {
        if (position > data.length || position < 0) {
            throw new IndexOutOfBoundsException();
        }
        final int truePosition = getTruePosition(position);
        final int bitValue = getBitValue(position);
        if (value) {
            data[truePosition] = (byte) (data[truePosition] | bitValue);
        } else {
            data[truePosition] = (byte) (data[truePosition] & ~bitValue);
        }
    }

    public void del(int position) {
        set(position, false);
    }

    public boolean get(int position) {
        if (position > data.length) {
            throw new IndexOutOfBoundsException();
        }
        final int truePosition = getTruePosition(position);
        final int bitValue = getBitValue(position);
        return (data[truePosition] & bitValue) != 0;
    }

    public void clear() {
        data = new long[data.length];
    }

}
