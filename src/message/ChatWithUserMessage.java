package view.message;

public class ChatWithUserMessage {
    String messageToSent;
    String toUserID;

    public ChatWithUserMessage(String messageToSent, String toUserID) {
        this.messageToSent = messageToSent;
        this.toUserID = toUserID;
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
