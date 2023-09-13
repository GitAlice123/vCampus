package view.message;

public class ChatWithUserMessage {
    public String getMyID() {
        return myID;
    }

    public void setMyID(String myID) {
        this.myID = myID;
    }

    String myID;
    String messageToSent;
    String toUserID;

    public ChatWithUserMessage(String myID, String messageToSent, String toUserID) {
        this.messageToSent = messageToSent;
        this.toUserID = toUserID;
        this.myID = myID;
    }

    public ChatWithUserMessage() {
    }

    public String getMessageToSent() {
        return messageToSent;
    }

    public void setMessageToSent(String messageToSent) {
        this.messageToSent = messageToSent;
    }

    public String getToUserID() {
        return toUserID;
    }

    public void setToUserID(String toUserID) {
        this.toUserID = toUserID;
    }
}
