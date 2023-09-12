package view.client;

import view.Login.logInUI;

import javax.swing.*;
import java.io.IOException;

public class ClientApplication {
    public ClientApplication() {
    }

    public void startClient() throws IOException {
        ClientRWTool rwTool = new ClientRWTool();

        Thread dataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new logInUI();
                } catch (UnsupportedLookAndFeelException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        dataThread.start();

//        Thread chatThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    ClientReceiver clientReceiver = new ClientReceiver();
//                    clientReceiver.startReceiving();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        chatThread.start();
    }
}
