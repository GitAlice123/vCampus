package view.Shop;

import java.util.Date;

/**
 * PurchaseRecord（购买记录）实体类
 * 表示一条购买记录的信息
 */
public class PurchaseRecord {
    private String order_id; // 购买订单号
    private String good_id; // 商品号
    private int nums; // 购买数量
    private String uId; // 购买人的Id
    private Date purchaseTime; // 购买时间

    public PurchaseRecord() {

    }

    /**
     * 构造函数
     *
     * @param orderId      购买订单号
     * @param goodId       商品号
     * @param nums         购买数量（大于等于1）
     * @param userId       购买人的Id
     * @param purchaseTime 购买时间（到秒）
     * @throws IllegalArgumentException 如果购买数量小于1
     */
    public PurchaseRecord(String orderId, String goodId, int nums, String userId, Date purchaseTime) throws IllegalArgumentException {
        this.order_id = orderId;
        this.good_id = goodId;
        if (nums >= 1) {
            this.nums = nums;
        } else {
            throw new IllegalArgumentException("购买数量必须大于等于1");
        }
        this.uId = userId;
        this.purchaseTime = purchaseTime;
    }

    /**
     * 获取购买订单号
     *
     * @return 购买订单号
     */
    public String getOrderId() {
        return order_id;
    }

    /**
     * 设置购买订单号
     *
     * @param orderId 购买订单号
     */
    public void setOrderId(String orderId) {
        this.order_id = orderId;
    }

    /**
     * 获取商品号
     *
     * @return 商品号
     */
    public String getGoodId() {
        return good_id;
    }

    /**
     * 设置商品号
     *
     * @param goodId 商品号
     */
    public void setGoodId(String goodId) {
        this.good_id = goodId;
    }

    /**
     * 获取购买数量
     *
     * @return 购买数量
     */
    public int getNums() {
        return nums;
    }

    /**
     * 设置购买数量
     *
     * @param nums 购买数量（大于等于1）
     */
    public void setNums(int nums) {
        if (nums >= 1) {
            this.nums = nums;
        } else {
            throw new IllegalArgumentException("购买数量必须大于等于1");
        }
    }

    /**
     * 获取购买人的Id
     *
     * @return 购买人的Id
     */
    public String getUserId() {
        return uId;
    }

    /**
     * 设置购买人的Id
     *
     * @param userId 购买人的Id
     */
    public void setUserId(String userId) {
        this.uId = userId;
    }

    /**
     * 获取购买时间
     *
     * @return 购买时间
     */
    public Date getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * 设置购买时间
     *
     * @param purchaseTime 购买时间（到秒）
     */
    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}

