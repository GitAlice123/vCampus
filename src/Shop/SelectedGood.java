package view.Shop;

/**
 * 该类表示加入到购物车中的商品。
 */
public class SelectedGood {
    private String GoodId;      // 商品编号
    private String GoodName;    // 商品名称
    private double GoodPrice;   // 商品价格
    private int GoodNums;       // 选择加入购物车的商品数量

    /**
     * 无参构造函数。
     */
    public SelectedGood() {
    }

    /**
     * 使用指定的商品编号、商品名称、商品价格和商品数量创建SelectedGood对象的有参构造函数。
     *
     * @param goodId    商品编号
     * @param goodName  商品名称
     * @param goodPrice 商品价格
     * @param goodNums  选择加入购物车的商品数量
     */
    public SelectedGood(String goodId, String goodName, double goodPrice, int goodNums) {
        this.GoodId = goodId;
        this.GoodName = goodName;
        this.GoodPrice = goodPrice;
        this.GoodNums = goodNums;
    }

    /**
     * 获取商品编号。
     *
     * @return 商品编号
     */
    public String getGoodId() {
        return GoodId;
    }

    /**
     * 设置商品编号。
     *
     * @param goodId 商品编号
     */
    public void setGoodId(String goodId) {
        GoodId = goodId;
    }

    /**
     * 获取商品名称。
     *
     * @return 商品名称
     */
    public String getGoodName() {
        return GoodName;
    }

    /**
     * 设置商品名称。
     *
     * @param goodName 商品名称
     */
    public void setGoodName(String goodName) {
        GoodName = goodName;
    }

    /**
     * 获取商品价格。
     *
     * @return 商品价格
     */
    public double getGoodPrice() {
        return GoodPrice;
    }

    /**
     * 设置商品价格。
     *
     * @param goodPrice 商品价格
     */
    public void setGoodPrice(double goodPrice) {
        GoodPrice = goodPrice;
    }

    /**
     * 获取选择加入购物车的商品数量。
     *
     * @return 商品数量
     */
    public int getGoodNums() {
        return GoodNums;
    }

    /**
     * 设置选择加入购物车的商品数量。
     *
     * @param goodNums 商品数量
     */
    public void setGoodNums(int goodNums) {
        GoodNums = goodNums;
    }
}
