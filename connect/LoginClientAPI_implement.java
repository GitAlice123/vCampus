package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import view.connect.LoginMessage;

public class LoginClientAPI_implement implements LoginClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

   //构造函数
    public LoginClientAPI_implement(String serverAddress, int serverPort) {
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


            outputStream.write(jsonData.getBytes(StandardCharsets.UTF_8));
            socket.shutdownOutput();



        } catch (Exception e) {
            e.printStackTrace();
        }



        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder jsonDataBuilder = new StringBuilder();
        String line;


        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len,"UTF-8"));
        }
        String mess = sb.toString();

// 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

// 将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

// 处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }


}