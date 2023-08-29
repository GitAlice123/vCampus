/*  这里创建了loginmessage类，头部说明了消息的类型
* */

package connect;

public class LoginMessage {
    private String userId;
    private String password;
    private String role;
    private   String messageType;

    public LoginMessage()
    {}
    public LoginMessage(String messageType,String userId, String password,String role) {
        this.userId = userId;
        this.password = password;
        this.role=role;
        this.messageType = messageType;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    public String getMessageType(){
        return messageType;
    }

    public String getRole(){
        return role;
    }

}
