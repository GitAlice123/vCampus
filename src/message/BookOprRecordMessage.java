package view.message;

import java.util.Date;

public class BookOprRecordMessage {
    //String oprId, String uId, String ISBN, Date oprTime, String oprtype, String oprMark


    private String operationId;
    private String userId;
    private String bookISBN;
    private Date operationTime;
    private String operationType;
    private String operationMark;

    public BookOprRecordMessage(String operationId, String userId, String bookISBN, Date operationTime, String operationType, String operationMark) {
        this.operationId = operationId;
        this.userId = userId;
        this.bookISBN = bookISBN;
        this.operationTime = operationTime;
        this.operationType = operationType;
        this.operationMark = operationMark;
    }

    public BookOprRecordMessage() {
    }

    public String getOperationId() {
        return operationId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getOperationMark() {
        return operationMark;
    }
}
