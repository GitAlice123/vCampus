package view.client;

import java.io.IOException;

/**
 * 服务器的main函数
 */
public class clientMain {
    public clientMain() {
    }

    public static void main(String[] args) throws IOException {
        ClientApplication clientApplication = new ClientApplication();
        clientApplication.startClient();
    }
}