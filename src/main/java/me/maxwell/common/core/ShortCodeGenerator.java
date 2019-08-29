package me.maxwell.common.core;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Maxwell.Lee
 * @version 3.8.1
 * @company Scho Techonlogy Co. Ltd
 * @date 2019/8/29 16:55
 */
public class ShortCodeGenerator {
    private static final String AlphabetAndNumberSet = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     * 短编码长度
     */
    private int     size;

    /**
     * 可选字符集
     */
    private char[]  availableChars;

    private Random rand;

    /**
     *
     * @param size              生成的短编码的长度
     * @param availableChars    短编码的字符集合
     */
    public ShortCodeGenerator(int size, String availableChars) {
        if (availableChars.length() == 0) {
            throw new IllegalArgumentException("参数availableChars不能时空字符串");
        }
        this.size = size;
        this.availableChars = availableChars.toCharArray();
        this.rand = new Random(System.currentTimeMillis());
    }

    public ShortCodeGenerator(int size) {
        this(size, AlphabetAndNumberSet);
    }

    public String   next() {
        char[] buf = new char[size];

        for(int i = 0; i < size; i++) {
            int idx = rand.nextInt(availableChars.length);

            buf[i] = availableChars[idx];
        }

        return new String(buf);
    }

    /**
     * 重置整个生成器的内部状态；
     */
    public synchronized void reset() {
        rand.setSeed(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ShortCodeGenerator{");
        sb.append("size=").append(size);
        sb.append(", availableChars=").append(Arrays.toString(availableChars));
        sb.append(", rand=").append(rand);
        sb.append('}');
        return sb.toString();
    }

}
