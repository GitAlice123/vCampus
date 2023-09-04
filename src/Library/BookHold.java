package view.Library;

import java.util.Date;

public class BookHold {
    String uId;
    String bookISBN;
    Date borrowTime;
    Date outdateTime;

    public BookHold(String uId, String bookISBN, Date borrowTime, Date outdateTime) {
        this.uId = uId;
        this.bookISBN = bookISBN;
        this.borrowTime = borrowTime;
        this.outdateTime = outdateTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getOutdateTime() {
        return outdateTime;
    }

    public void setOutdateTime(Date outdateTime) {
        this.outdateTime = outdateTime;
    }

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
