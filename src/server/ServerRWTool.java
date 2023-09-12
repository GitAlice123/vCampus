package view.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.message.IDSendMessage;
import view.message.serverGetMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ServerRWTool {
    public ServerRWTool() {
    }

    /**
     * 从输入流中读取服务器消息并返回消息内容和消息编号的配对。
     *
     * @param inputStream 输入流
     * @return 消息内容和消息编号的配对
     * @throws IOException 如果读取过程中发生 I/O 错误
     */
    public serverGetMessage ServerReadStream(InputStream inputStream) throws IOException {
        String jsonData;
        int messageNumber;
        byte[] lengthBytes = new byte[4];
        byte[] indexBytes = new byte[4];
        try {
            inputStream.read(lengthBytes);  // 读取消息长度和编号
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int messageLength = ByteBuffer.wrap(lengthBytes, 0, 4).getInt();
        if (messageLength == 0)// 说明是clientReceiver上线
        {
            // 消息长度、学号信息
            byte[] length = new byte[4];
            inputStream.read(length);
            int mLength = ByteBuffer.wrap(length, 0, 4).getInt();
            byte[] jsonDataBytes = new byte[mLength];
            inputStream.read(jsonDataBytes);  // 读取学号
            jsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();

            IDSendMessage boolRespMessage = objectMapper.readValue(jsonData, IDSendMessage.class);
            serverGetMessage m = new serverGetMessage(0, mLength, boolRespMessage.getUserID());
            return m;
        }

        inputStream.read(indexBytes);
        messageNumber = ByteBuffer.wrap(indexBytes, 0, 4).getInt();

        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容

        jsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);

        System.out.println("Success");
        System.out.println("message length: " + messageLength);
        System.out.println("message number: " + messageNumber);
        System.out.println(jsonData);

        // 长度、消息编码、信息
        return new serverGetMessage(messageLength, messageNumber, jsonData);
    }

    /**
     * 将输出数据发送到输出流中。
     *
     * @param outputStream 输出流
     * @param outputData   输出数据
     * @throws IOException 如果写入过程中发生 I/O 错误
     */
    public void ServerSendOutStream(OutputStream outputStream, String outputData) throws IOException {
        byte[] responseServer = outputData.getBytes(StandardCharsets.UTF_8);
        int responseLength = responseServer.length;
        System.out.println(responseLength);
        byte[] lengthBytesResponse = ByteBuffer.allocate(4).putInt(responseLength).array();
        try {
            outputStream.write(lengthBytesResponse);  // 写入消息长度
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        outputStream.write(responseServer);  // 写入消息内容
        outputStream.flush();
    }
}
