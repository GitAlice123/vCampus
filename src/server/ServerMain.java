package view.server;

import java.io.IOException;

/**
 * 服务器的main函数
 */
public class ServerMain {
    public ServerMain() {
    }

    public static void main(String[] args) throws IOException {
        ServerApplication serverApp = new ServerApplication();
        serverApp.startServer();
    }
}
