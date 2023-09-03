package view.message;

public class UniqueMessage {
    private String uniMessage;
    /* messageType = RegisterReq */

    public UniqueMessage() {
        // 无参数构造函数
    }
    public UniqueMessage(String userId) {
        this.uniMessage = userId;
    }
    public String getUniMessage(){
        return uniMessage;
    }
}
