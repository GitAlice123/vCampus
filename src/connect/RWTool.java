package view.connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class RWTool {
    // 读写流的工具函数

    // 无参构造函数
    public RWTool(){
    }

    // 读取输入流，返回json格式的数据
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
}
