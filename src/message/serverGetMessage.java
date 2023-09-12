package view.message;

public class serverGetMessage {
    private int signalOrLength;
    private int dataLengthOrCode;
    private String IDInfoOrMessage;
    // 信号、消息长度、学号信息
    // 或
    // 长度、消息编码、信息

    public serverGetMessage(int signalOrLength, int data, String messageContent) {
        this.signalOrLength = signalOrLength;
        this.dataLengthOrCode = data;
        this.IDInfoOrMessage = messageContent;
    }

    public serverGetMessage() {
    }

    public int getSignalOrLength() {
        return signalOrLength;
    }

    public void setSignalOrLength(int signalOrLength) {
        this.signalOrLength = signalOrLength;
    }

    public int getDataLengthOrCode() {
        return dataLengthOrCode;
    }

    public void setDataLengthOrCode(int dataLengthOrCode) {
        this.dataLengthOrCode = dataLengthOrCode;
    }

    public String getIDInfoOrMessage() {
        return IDInfoOrMessage;
    }

    public void setIDInfoOrMessage(String IDInfoOrMessage) {
        this.IDInfoOrMessage = IDInfoOrMessage;
    }
}
