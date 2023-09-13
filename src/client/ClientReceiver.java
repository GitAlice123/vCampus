package view.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.message.ChatMessage;
import view.message.GPTAnsRepMessage;
import view.message.IDSendMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 客户端消息接收器，负责接收服务器发送的消息并处理。
 */
public class ClientReceiver {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private MessageCallback messageCallback;

    /**
     * 创建一个新的客户端消息接收器实例。
     *
     * @param callback 用于处理接收到的消息的回调接口。
     * @throws IOException 如果发生输入/输出错误。
     */
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

    /**
     * 启动消息接收线程，持续接收服务器发送的消息。
     */
    public void startReceiving() {
        Thread receivingThread = new Thread(() -> {
            try {
                while (true) {
                    int messageLength = readMessageLength();
                    ChatMessage receivedMessage = readMessageContent(messageLength);
                    // 处理接收到的消息
                    handleMessage(receivedMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        receivingThread.start();
    }

    /**
     * 从输入流中读取消息长度。
     *
     * @return 消息的长度。
     * @throws IOException 如果发生输入/输出错误。
     */
    private int readMessageLength() throws IOException {
        byte[] lengthBytes = new byte[4];
        inputStream.read(lengthBytes);  // 读取消息长度
        return ByteBuffer.wrap(lengthBytes).getInt();
    }

    /**
     * 从输入流中读取消息内容并解析为 ChatMessage 对象。
     *
     * @param messageLength 消息的长度。
     * @return 解析后的 ChatMessage 对象。
     * @throws IOException 如果发生输入/输出错误。
     */
    private ChatMessage readMessageContent(int messageLength) throws IOException {
        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容
        ObjectMapper objectMapper = new ObjectMapper();
        ChatMessage gptAnsRepMessage = objectMapper.readValue(jsonDataBytes, ChatMessage.class);

        return gptAnsRepMessage;
    }

    /**
     * 处理接收到的消息，通常将消息传递给 UI 进行显示。
     *
     * @param message 接收到的消息。
     * @throws JsonProcessingException 如果无法处理消息内容。
     */
    private void handleMessage(ChatMessage message) throws JsonProcessingException {
        // 在这里处理接收到的消息
        System.out.println("Thread Received message: " + message);
        // 可以根据具体需求进行相应的处理逻辑
        // 调用回调函数将消息传递给 UI 文件
        if (messageCallback != null) {
            messageCallback.onMessageReceived(message);
        }
    }

    /**
     * 定义一个回调接口，用于处理接收到的消息。
     */
    public interface MessageCallback {
        /**
         * 当接收到消息时调用此方法。
         *
         * @param message 接收到的消息。
         */
        void onMessageReceived(ChatMessage message);
    }
}