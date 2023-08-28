package view.VObj;

import java.util.Date;

public class BookOperationRecord {

    String oprId;
    String sId;
    String ISBN;
    Date oprTime;
    OprType oprtype;
    String oprMark;

    public String getOprId() {
        return oprId;
    }

    public void setOprId(String oprId) {
        this.oprId = oprId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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

    public OprType getOprtype() {
        return oprtype;
    }

    public void setOprtype(OprType oprtype) {
        this.oprtype = oprtype;
    }

    public void setOprtype(String oprtype) {
        switch (oprtype) {
            case "BOR":
                this.oprtype = OprType.BOR;
            case "RET":
                this.oprtype = OprType.RET;
            case "REN":
                this.oprtype = OprType.REN;
        }

    }

    public String getOprMark() {
        return oprMark;
    }

    public void setOprMark(String oprMark) {
        this.oprMark = oprMark;
    }
}
