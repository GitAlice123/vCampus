package view.Bank;

import java.util.Date;

/**
 * 银行账单类
 */
public class bankBill {
    private String description; // 商品说明 医院/商店/银行充值/水电费
    private String nums; // 订单号
    private String cardId; // 对应卡账号，6位数字
    private String userId;//一卡通号
    private Date time; // 交易时间
    private boolean type; // 交易类型 true:消费，false:充值
    private double amount; // 交易金额

    /**
     * 无参构造
     */
    public bankBill() {

    }

    /**
     * 构造方法
     *
     * @param description 商品说明
     * @param nums        订单号
     * @param cardId      对应卡账号
     * @param time        交易时间
     * @param type        交易类型
     * @param amount      交易金额
     */
    public bankBill(String description, String nums, String cardId, String userId, Date time, boolean type, double amount) {
        this.description = description;
        this.nums = nums;
        this.cardId = cardId;
        this.userId = userId;
        this.time = time;
        this.type = type;
        this.amount = amount;
    }

    /**
     * 获取商品说明
     *
     * @return 商品说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商品说明
     *
     * @param description 商品说明
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取订单号
     *
     * @return 订单号
     */
    public String getNums() {
        return nums;
    }

    /**
     * 设置订单号
     *
     * @param nums 订单号
     */
    public void setNums(String nums) {
        this.nums = nums;
    }

    /**
     * 获取对应卡账号
     *
     * @return 对应卡账号
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置对应卡账号
     *
     * @param cardId 对应卡账号
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取一卡通号
     *
     * @return 对应一卡通号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置对应一卡通号
     *
     * @param _userId 对应一卡通号
     */
    public void setUserIdId(String _userId) {
        this.userId = _userId;
    }

    /**
     * 获取交易时间
     *
     * @return 交易时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置交易时间
     *
     * @param time 交易时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取交易类型
     *
     * @return 交易类型
     */
    public boolean isType() {
        return type;
    }

    /**
     * 设置交易类型
     *
     * @param type 交易类型
     */
    public void setType(boolean type) {
        this.type = type;
    }

    /**
     * 获取交易金额
     *
     * @return 交易金额
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 设置交易金额
     *
     * @param amount 交易金额
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "bankBill{" +
                "description='" + description + '\'' +
                ", nums='" + nums + '\'' +
                ", cardId='" + cardId + '\'' +
                ", userId='" + userId + '\'' +
                ", time='" + time + '\'' +
                ", type=" + type +
                ", amount=" + amount +
                '}';
    }

}
