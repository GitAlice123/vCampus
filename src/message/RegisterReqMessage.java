package view.message;

public class RegisterReqMessage {
    private String userId;
    /* messageType = RegisterReq */

    public RegisterReqMessage() {
        // 无参数构造函数
    }
    public RegisterReqMessage(String userId) {
        this.userId = userId;
    }
    public String getUserId(){
        return userId;
    }

}
