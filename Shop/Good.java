package view.Shop;

/**
 * 商品实体类
 */
public class Good {
    private String goodId;
    private String goodName;
    private double goodPrice;
    private String category;
    private String provider;
    private int goodStock;

    /**
     * 创建商品对象
     *
     * @param goodId    商品号
     * @param goodName  商品名称
     * @param goodPrice 商品价格
     * @param category  商品类别
     * @param provider  供应商
     * @param goodStock 商品库存数
     */
    public Good(String goodId, String goodName, double goodPrice, String category, String provider, int goodStock) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodPrice = goodPrice;
        this.category = category;
        this.provider = provider;
        this.goodStock = goodStock;
    }

    /**
     * 获取商品号
     *
     * @return 商品号
     */
    public String getGoodId() {
        return goodId;
    }

    /**
     * 设置商品号
     *
     * @param goodId 商品号
     */
    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    /**
     * 获取商品名称
     *
     * @return 商品名称
     */
    public String getGoodName() {
        return goodName;
    }

    /**
     * 设置商品名称
     *
     * @param goodName 商品名称
     */
    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    /**
     * 获取商品价格
     *
     * @return 商品价格
     */
    public double getGoodPrice() {
        return goodPrice;
    }

    /**
     * 设置商品价格
     *
     * @param goodPrice 商品价格
     */
    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    /**
     * 获取商品类别
     *
     * @return 商品类别
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置商品类别
     *
     * @param category 商品类别
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取供应商
     *
     * @return 供应商
     */
    public String getProvider() {
        return provider;
    }

    /**
     * 设置供应商
     *
     * @param provider 供应商
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * 获取商品库存数
     *
     * @return 商品库存数
     */
    public int getGoodStock() {
        return goodStock;
    }

    /**
     * 设置商品库存数
     *
     * @param goodStock 商品库存数
     */
    public void setGoodStock(int goodStock) {
        this.goodStock = goodStock;
    }
}

