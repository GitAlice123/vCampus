package view.connect;

import view.message.LoginMessage;
import view.message.RegisterReqMessage;

import java.io.IOException;

/* 注册时客户端访问服务器的API */
public interface RegisterClientAPI {
    /**
     * 检查用户是否已经存在于服务器上。
     *
     * @param registerReqMessage 注册请求消息对象，包含用户ID
     * @return 如果用户已存在返回true，否则返回false
     * @throws IOException 如果发生通信错误
     */
    boolean checkExistByUserId(RegisterReqMessage registerReqMessage) throws IOException;

    /**
     * 创建新用户并将其注册到服务器。
     *
     * @param loginMessage 登录消息对象，包含用户ID和密码
     * @return 如果注册成功返回true，否则返回false
     * @throws IOException 如果发生通信错误
     */
    Boolean createNewUser(LoginMessage loginMessage) throws IOException;
}
