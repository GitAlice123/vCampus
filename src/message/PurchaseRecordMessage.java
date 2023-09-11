package view.message;

import view.Shop.PurchaseRecord;

/**
 * PurchaseRecordMessage 表示包含购买记录的消息。
 */
public class PurchaseRecordMessage {
    private PurchaseRecord purchaseRecord;

    /**
     * 构造一个空的 PurchaseRecordMessage。
     */
    public PurchaseRecordMessage() {
        // 空构造函数
    }

    /**
     * 构造一个具有指定购买记录的 PurchaseRecordMessage。
     *
     * @param purchaseRecord 要设置的购买记录。
     */
    public PurchaseRecordMessage(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }

    /**
     * 获取购买记录。
     *
     * @return 购买记录。
     */
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord;
    }

    /**
     * 设置购买记录。
     *
     * @param purchaseRecord 要设置的购买记录。
     */
    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }
}
