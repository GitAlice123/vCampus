package view.connect;

import java.io.IOException;

import view.connect.LoginMessage;

public interface LoginClientAPI {
    //loginByUserId:接收登录时输入的用户id和密码，返回true为允许登录，返回false为不允许
    boolean loginByUserId(LoginMessage loginMessage) throws IOException;

//    boolean getServerResponse() throws IOException;
}

