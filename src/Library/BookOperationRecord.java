package view.Library;

import java.util.Date;

/**
 * 代表书籍的操作记录。
 */
public class BookOperationRecord {

    String oprId;
    String uId;
    String ISBN;
    Date oprTime;
    String oprtype;
    String oprMark;

    /**
     * 默认构造函数，创建一个空的BookOperationRecord对象。
     */
    public BookOperationRecord() {
    }

    /**
     * 构造函数，用于创建BookOperationRecord对象。
     *
     * @param oprId    操作记录ID
     * @param uId      用户ID
     * @param ISBN     书籍ISBN
     * @param oprTime  操作时间
     * @param oprtype  操作类型
     * @param oprMark  操作备注
     */
    public BookOperationRecord(String oprId, String uId, String ISBN, Date oprTime, String oprtype, String oprMark) {
        this.oprId = oprId;
        this.uId = uId;
        this.ISBN = ISBN;
        this.oprTime = oprTime;
        this.oprtype = oprtype;
        this.oprMark = oprMark;
    }

    /**
     * 获取操作记录ID。
     *
     * @return 操作记录ID
     */
    public String getOprId() {
        return oprId;
    }

    /**
     * 设置操作记录ID。
     *
     * @param oprId 操作记录ID
     */
    public void setOprId(String oprId) {
        this.oprId = oprId;
    }

    /**
     * 获取用户ID。
     *
     * @return 用户ID
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置用户ID。
     *
     * @param uId 用户ID
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * 获取书籍ISBN。
     *
     * @return 书籍ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * 设置书籍ISBN。
     *
     * @param ISBN 书籍ISBN
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * 获取操作时间。
     *
     * @return 操作时间
     */
    public Date getOprTime() {
        return oprTime;
    }

    /**
     * 设置操作时间。
     *
     * @param oprTime 操作时间
     */
    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    /**
     * 获取操作类型。
     *
     * @return 操作类型
     */
    public String getOprtype() {
        return oprtype;
    }

    /**
     * 设置操作类型。
     *
     * @param oprtype 操作类型
     */
    public void setOprtype(String oprtype) {
        this.oprtype = oprtype;
    }

    /**
     * 获取操作备注。
     *
     * @return 操作备注
     */
    public String getOprMark() {
        return oprMark;
    }

    /**
     * 设置操作备注。
     *
     * @param oprMark 操作备注
     */
    public void setOprMark(String oprMark) {
        this.oprMark = oprMark;
    }

    /**
     * 返回BookOperationRecord对象的字符串表示。
     *
     * @return BookOperationRecord对象的字符串表示
     */
    @Override
    public String toString() {
        return "BookOperationRecord{" +
                "oprId='" + oprId + '\'' +
                ", uId='" + uId + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", oprTime=" + oprTime +
                ", oprtype='" + oprtype + '\'' +
                ", oprMark='" + oprMark + '\'' +
                '}';
    }
}