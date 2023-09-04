package view.connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class RWTool {
    // 读写流的工具函数

    /**
     * 无参构造函数。
     */
    public RWTool(){
    }

    /**
     * 从输入流中读取服务器消息并返回消息内容和消息编号的配对。
     *
     * @param inputStream 输入流
     * @return 消息内容和消息编号的配对
     * @throws IOException 如果读取过程中发生 I/O 错误
     */
    public Pair<String, Integer> ServerReadStream(InputStream inputStream) throws IOException {
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
        inputStream.read(indexBytes);
        messageNumber = ByteBuffer.wrap(indexBytes, 0, 4).getInt();

        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容

        jsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);

        System.out.println("Success");
        System.out.println("message length: " + messageLength);
        System.out.println("message number: " + messageNumber);
        System.out.println(jsonData);

        return new Pair<>(jsonData, messageNumber);
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


    /**
     * 从输入流中读取客户端消息并返回消息内容。
     *
     * @param inputStream 输入流
     * @return 客户端消息内容
     * @throws IOException 如果读取过程中发生 I/O 错误
     */
    public String ClientReadStream(InputStream inputStream) throws IOException {
        byte[] lengthBytes = new byte[4];
        System.out.println("Client inputStream status: "+inputStream.available());
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
    public void ClientSendOutStream(OutputStream outputStream, String jsonData,int index) throws IOException {
            byte[] jsonDataBytes = jsonData.getBytes(StandardCharsets.UTF_8);
            int messageLength = jsonDataBytes.length;
            byte[] lengthBytes = ByteBuffer.allocate(4).putInt(messageLength).array();
            byte[] indexInfo = ByteBuffer.allocate(4).putInt(index).array();
            outputStream.write(lengthBytes,0,4);  // 写入消息长度
            outputStream.write(indexInfo,0,4);// 写入消息编码
            outputStream.write(jsonDataBytes);  // 写入消息内容
            outputStream.flush();
    }

    /**
     * 从输入流中读取服务器简单请求并返回消息编号。
     *
     * @param inputStream 输入流
     * @return 消息编号
     * @throws IOException 如果读取过程中发生 I/O 错误
     */
    public int ServerReadSimpleReq(InputStream inputStream) throws IOException {
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
        try {
            inputStream.read(indexBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        messageNumber = ByteBuffer.wrap(indexBytes, 0, 4).getInt();

        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容

        jsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);

        System.out.println("Server success read request");
        System.out.println("message number: " + messageNumber);
        System.out.println(jsonData);

        return messageNumber;
    }
}
