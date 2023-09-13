package view.message;

/**
 * @brief 整数消息类
 *
 * 该类表示一个整数消息。
 */
public class IntMessage {
    private int num;

    /**
     * @brief 无参构造函数
     *
     * 创建一个空的整数消息对象。
     */
    public IntMessage() {
    }

    /**
     * @brief 有参构造函数
     *
     * 创建一个带有指定整数值的整数消息对象。
     *
     * @param num 整数值
     */
    public IntMessage(int num) {
        this.num = num;
    }

    /**
     * @brief 设置整数值
     *
     * 设置整数消息的值。
     *
     * @param num 整数值
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @brief 获取整数值
     *
     * 获取整数消息的值。
     *
     * @return 整数值
     */
    public int getNum() {
        return num;
    }
}
