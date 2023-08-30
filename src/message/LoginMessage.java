/*  这里创建了loginmessage类，头部说明了消息的类型
* */

package view.message;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LoginMessage {
    private String userId;
    private String password;
    private String role;

    public LoginMessage()
    {}
    public LoginMessage(String userId, String password,String role) {
        this.userId = userId;
        this.password = password;
        this.role=role;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

}
