package view.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClientRWTool {
    public ClientRWTool() {
    }

    /**
     * 从输入流中读取客户端消息并返回消息内容。
     *
     * @param inputStream 输入流
     * @return 客户端消息内容
     * @throws IOException 如果读取过程中发生 I/O 错误
     */
    public String ClientReadStream(InputStream inputStream) throws IOException {
        byte[] lengthBytes = new byte[4];
        System.out.println("Client inputStream status: " + inputStream.available());
        try {
            inputStream.read(lengthBytes);  // 读取消息长度
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int messageLength = ByteBuffer.wrap(lengthBytes).getInt();
        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容
        String receivedJsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);
        return receivedJsonData;
    }


    /**
     * 将消息数据发送到输出流中。
     *
     * @param outputStream 输出流
     * @param jsonData     消息数据
     * @param index        消息编号
     * @throws IOException 如果写入过程中发生 I/O 错误
     */
    public void ClientSendOutStream(OutputStream outputStream, String jsonData, int index) throws IOException {
        byte[] jsonDataBytes = jsonData.getBytes(StandardCharsets.UTF_8);
        int messageLength = jsonDataBytes.length;
        byte[] lengthBytes = ByteBuffer.allocate(4).putInt(messageLength).array();
        byte[] indexInfo = ByteBuffer.allocate(4).putInt(index).array();
        outputStream.write(lengthBytes, 0, 4);  // 写入消息长度
        outputStream.write(indexInfo, 0, 4);// 写入消息编码
        outputStream.write(jsonDataBytes);  // 写入消息内容
        outputStream.flush();
    }
}
