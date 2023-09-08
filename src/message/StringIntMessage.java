package view.message;

/**
 * 字符串和整数消息类，包含一个字符串和一个整数。
 */
public class StringIntMessage {
    private String str;  // 字符串类型
    private int num;     // 整数类型

    /**
     * 无参构造函数，创建一个空的字符串和整数消息对象。
     */
    public StringIntMessage() {
    }

    /**
     * 带参构造函数，创建一个指定字符串和整数的消息对象。
     *
     * @param str 字符串
     * @param num 整数
     */
    public StringIntMessage(String str, int num) {
        this.str = str;
        this.num = num;
    }

    /**
     * 获取字符串。
     *
     * @return 字符串
     */
    public String getStr() {
        return str;
    }

    /**
     * 设置字符串。
     *
     * @param str 字符串
     */
    public void setStr(String str) {
        this.str = str;
    }

    /**
     * 获取整数。
     *
     * @return 整数
     */
    public int getNum() {
        return num;
    }

    /**
     * 设置整数。
     *
     * @param num 整数
     */
    public void setNum(int num) {
        this.num = num;
    }
}
