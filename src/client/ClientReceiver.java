package view.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.message.GPTAnsRepMessage;
import view.message.IDSendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClientReceiver {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private MessageCallback messageCallback;

    public ClientReceiver(MessageCallback callback) throws IOException {
        socket = new Socket(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();

        // 要表达两个意思：已上线、自己的学号
        // 已上线
        byte[] signalBytes = ByteBuffer.allocate(4).putInt(0).array();
        outputStream.write(signalBytes, 0, 4);  // 写入signal
        // 传递自己的学号

        ObjectMapper objectMapper = new ObjectMapper();
        IDSendMessage idSendMessage = new IDSendMessage(GlobalData.getUID());
        String jsonData = objectMapper.writeValueAsString(idSendMessage);
        byte[] jsonDataBytes = jsonData.getBytes(StandardCharsets.UTF_8);
// 信号、消息长度、学号信息
        int messageLength = jsonDataBytes.length;
        byte[] lengthBytes = ByteBuffer.allocate(4).putInt(messageLength).array();
        outputStream.write(lengthBytes, 0, 4);  // 写入消息长度
        outputStream.write(jsonDataBytes);  // 写入学号
        outputStream.flush();

        this.messageCallback = callback;
    }

    public void startReceiving() {
        Thread receivingThread = new Thread(() -> {
            try {
                while (true) {
                    int messageLength = readMessageLength();
                    String receivedMessage = readMessageContent(messageLength);
                    // 处理接收到的消息
                    handleMessage(receivedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receivingThread.start();
    }

    private int readMessageLength() throws IOException {
        byte[] lengthBytes = new byte[4];
        inputStream.read(lengthBytes);  // 读取消息长度
        return ByteBuffer.wrap(lengthBytes).getInt();
    }

    private String readMessageContent(int messageLength) throws IOException {
        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容
        ObjectMapper objectMapper = new ObjectMapper();
        GPTAnsRepMessage gptAnsRepMessage = objectMapper.readValue(jsonDataBytes, GPTAnsRepMessage.class);
        String gptAnswer = gptAnsRepMessage.getGPTanswer();
        return gptAnswer;
    }

    private void handleMessage(String message) throws JsonProcessingException {
        // 在这里处理接收到的消息
        System.out.println("Thread Received message: " + message);
        // 可以根据具体需求进行相应的处理逻辑
        // 调用回调函数将消息传递给 UI 文件
        if (messageCallback != null) {
            messageCallback.onMessageReceived(message);
        }
    }

    public interface MessageCallback {
        void onMessageReceived(String message);
    }
}