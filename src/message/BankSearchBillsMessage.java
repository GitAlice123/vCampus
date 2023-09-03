package view.message;

import java.util.Date;

/**
 * BankSearchBillsMessage 类用于表示查询账单操作的银行消息。1004
 */
public class BankSearchBillsMessage {
    private String id;         // 一卡通号
    private Date startTime;    // 起始时间
    private Date endTime;      // 结束时间
    private String query;      // 用于查询的关键字

    /**
     * 构造一个 BankSearchBillsMessage 对象。
     */
    public BankSearchBillsMessage() {
    }

    /**
     * 构造一个 BankSearchBillsMessage 对象。
     *
     * @param id         一卡通号
     * @param startTime  起始时间
     * @param endTime    结束时间
     * @param query      用于查询的关键字
     */
    public BankSearchBillsMessage(String id, Date startTime, Date endTime, String query) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.query = query;
    }

    /**
     * 获取一卡通号。
     *
     * @return 一卡通号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置一卡通号。
     *
     * @param id 一卡通号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取起始时间。
     *
     * @return 起始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置起始时间。
     *
     * @param startTime 起始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间。
     *
     * @return 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间。
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取用于查询的关键字。
     *
     * @return 查询关键字
     */
    public String getQuery() {
        return query;
    }

    /**
     * 设置用于查询的关键字。
     *
     * @param query 查询关键字
     */
    public void setQuery(String query) {
        this.query = query;
    }
}
