package view.Library;

import java.util.Date;

/**
 * 代表一本预约的书籍。
 */
public class BookHold {
    String uId;
    String bookISBN;
    Date borrowTime;
    Date outdateTime;

    /**
     * 构造函数，用于创建BookHold对象。
     *
     * @param uId         用户ID
     * @param bookISBN    书籍ISBN
     * @param borrowTime  借阅时间
     * @param outdateTime 到期时间
     */
    public BookHold(String uId, String bookISBN, Date borrowTime, Date outdateTime) {
        this.uId = uId;
        this.bookISBN = bookISBN;
        this.borrowTime = borrowTime;
        this.outdateTime = outdateTime;
    }

    /**
     * 默认构造函数，创建一个空的BookHold对象。
     */
    public BookHold() {
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
    public String getBookISBN() {
        return bookISBN;
    }

    /**
     * 设置书籍ISBN。
     *
     * @param bookISBN 书籍ISBN
     */
    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    /**
     * 获取借阅时间。
     *
     * @return 借阅时间
     */
    public Date getBorrowTime() {
        return borrowTime;
    }

    /**
     * 设置借阅时间。
     *
     * @param borrowTime 借阅时间
     */
    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    /**
     * 获取到期时间。
     *
     * @return 到期时间
     */
    public Date getOutdateTime() {
        return outdateTime;
    }

    /**
     * 设置到期时间。
     *
     * @param outdateTime 到期时间
     */
    public void setOutdateTime(Date outdateTime) {
        this.outdateTime = outdateTime;
    }

    /**
     * 返回BookHold对象的字符串表示。
     *
     * @return BookHold对象的字符串表示
     */
    @Override
    public String toString() {
        return "BookHold{" +
                "uId='" + uId + '\'' +
                ", bookISBN='" + bookISBN + '\'' +
                ", borrowTime=" + borrowTime +
                ", outdateTime=" + outdateTime +
                '}';
    }
}