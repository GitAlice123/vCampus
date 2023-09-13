package view.connect;

import view.message.LoginMessage;

import java.io.IOException;

public interface LoginClientAPI {
    /**
     * 通过用户提供的登录信息进行用户登录。
     *
     * @param loginMessage 登录消息对象，包含用户ID和密码
     * @return 如果登录成功返回true，否则返回false
     * @throws IOException 如果发生通信错误
     */
    boolean loginByUserId(LoginMessage loginMessage) throws IOException;

//    boolean getServerResponse() throws IOException;
}

