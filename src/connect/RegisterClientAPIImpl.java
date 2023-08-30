package view.connect;

import view.message.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;

public class RegisterClientAPIImpl implements RegisterClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();

    /* 构造函数 */
    public RegisterClientAPIImpl(String serverAddress, int serverPort) {
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
    public boolean checkExistByUserId(RegisterReqMessage registerReqMessage) throws IOException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(registerReqMessage);
            System.out.println(jsonData);
            rwTool.ClientSendOutStream(outputStream,jsonData,101);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //以下收取服务器响应
        boolean result = false;
        try {
            String receivedJsonData = rwTool.ClientReadStream(inputStream);
            String mess = receivedJsonData.toString();

            //  创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            //  将 JSON 数据转换为对象
            BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

            //  处理结果
            result = boolRespMessage.getFlag();

        } catch (Exception e) {
            e.printStackTrace();
        };
        return result;
    }
    @Override
    public Boolean createNewUser(LoginMessage loginMessage) throws IOException {
        //以下发送用户信息给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(loginMessage);
            System.out.println(jsonData+" Successfully send request!");

            rwTool.ClientSendOutStream(outputStream,jsonData,102);


        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

        //创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

        //将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

        return boolRespMessage.getFlag();
    }

}
