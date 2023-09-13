package view.connect;

/**
 * 通用的Pair类，用于存储两个不同类型的对象。
 *
 * @param <T> 第一个对象的类型
 * @param <U> 第二个对象的类型
 */
public class Pair<T, U> {
    private T first;
    private U second;

    /**
     * 构造一个Pair对象，包含两个对象：第一个对象和第二个对象。
     *
     * @param first  第一个对象
     * @param second 第二个对象
     */
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    /**
     * 获取存储在Pair对象中的第一个对象。
     *
     * @return 第一个对象
     */
    public T getFirst() {
        return first;
    }

    /**
     * 获取存储在Pair对象中的第二个对象。
     *
     * @return 第二个对象
     */
    public U getSecond() {
        return second;
    }

    /**
     * 返回Pair对象的字符串表示形式，形如 "(first, second)"。
     *
     * @return Pair对象的字符串表示形式
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
