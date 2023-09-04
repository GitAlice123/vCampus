package view.Library;

import java.util.Date;

public class BookOperationRecord {

    String oprId;
    String uId;
    String ISBN;
    Date oprTime;
    String oprtype;
    String oprMark;

    public BookOperationRecord() {
    }

    public BookOperationRecord(String oprId, String uId, String ISBN, Date oprTime, String oprtype, String oprMark) {
        this.oprId = oprId;
        this.uId = uId;
        this.ISBN = ISBN;
        this.oprTime = oprTime;
        this.oprtype = oprtype;
        this.oprMark = oprMark;
    }

    public String getOprId() {
        return oprId;
    }

    public void setOprId(String oprId) {
        this.oprId = oprId;
    }

    public String getuId() {
        return uId;
    }

    public void setsId(String sId) {
        this.uId = uId;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getOprTime() {
        return oprTime;
    }

    public void setOprTime(Date oprTime) {
        this.oprTime = oprTime;
    }

    public String getOprtype() {
        return oprtype;
    }

    public void setOprtype(String oprtype) {
        this.oprtype = oprtype;
    }


    public String getOprMark() {
        return oprMark;
    }

    public void setOprMark(String oprMark) {
        this.oprMark = oprMark;
    }

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