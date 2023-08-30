package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;

public class LoginClientAPIImpl implements LoginClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

   //构造函数
    public LoginClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginByUserId(LoginMessage loginMessage) throws IOException {


        //以下发送用户id和密码给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(loginMessage);
            System.out.println(jsonData);


//            outputStream.write(jsonData.getBytes(StandardCharsets.UTF_8));
//            socket.shutdownOutput();
            byte[] jsonDataBytes = jsonData.getBytes(StandardCharsets.UTF_8);
            int messageLength = jsonDataBytes.length;
            byte[] lengthBytes = ByteBuffer.allocate(4).putInt(messageLength).array();
            outputStream.write(lengthBytes);  // 写入消息长度
            outputStream.write(jsonDataBytes);  // 写入消息内容
            outputStream.flush();



        } catch (Exception e) {
            e.printStackTrace();
        }


        byte[] lengthBytes = new byte[4];
        inputStream.read(lengthBytes);  // 读取消息长度
        int messageLength = ByteBuffer.wrap(lengthBytes).getInt();
        byte[] jsonDataBytes = new byte[messageLength];
        inputStream.read(jsonDataBytes);  // 读取消息内容
        String receivedJsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);

//        System.out.println(receivedJsonData);
        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


}