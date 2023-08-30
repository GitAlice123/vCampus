package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.message.LoginMessage;

import java.io.*;
import java.net.Socket;

public class LoginClientAPIImpl implements LoginClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();
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

            rwTool.ClientSendOutStream(outputStream,jsonData);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

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