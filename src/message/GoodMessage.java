package view.message;

import view.Shop.Good;

/**
 * 商品消息类，包含一个商品对象。
 */
public class GoodMessage {
    private Good g;  // 商品对象

    /**
     * 无参构造函数，创建一个空的商品消息对象。
     */
    public GoodMessage() {
    }

    /**
     * 带参构造函数，创建一个指定商品的消息对象。
     *
     * @param g 商品对象
     */
    public GoodMessage(Good g) {
        this.g = g;
    }

    /**
     * 获取商品对象。
     *
     * @return 商品对象
     */
    public Good getG() {
        return g;
    }

    /**
     * 设置商品对象。
     *
     * @param g 商品对象
     */
    public void setG(Good g) {
        this.g = g;
    }
}
