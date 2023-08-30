package view.connect;

import view.message.*;
import java.io.IOException;

/* 注册时客户端访问服务器的API */
public interface RegisterClientAPI {
    //loginByUserId:接收登录时输入的用户id，返回true为允许注册，返回false为不允许注册
    boolean checkExistByUserId(RegisterReqMessage registerReqMessage) throws IOException;

    Boolean createNewUser(LoginMessage loginMessage)throws IOException;
}
