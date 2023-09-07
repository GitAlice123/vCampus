package view.message;

public class ChatQuesMessage {
    private String queString;
    /* messageType = RegisterReq */

    public ChatQuesMessage() {
        // 无参数构造函数
    }

    public ChatQuesMessage(String queString) {
        this.queString = queString;
    }

    public String getqueString() {
        return queString;
    }
}
