package view.connect;

import view.message.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

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
            String jsonData = registerReqMessage.getUserId();
            rwTool.ClientSendOutStream(outputStream,jsonData,101);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //以下收取服务器响应
        boolean result = false;
        try {
            String receivedJsonData = rwTool.ClientReadStream(inputStream);
            String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
            BoolRespMessage boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);

//      处理结果
            result = boolRespMessage.getFlag();

        } catch (Exception e) {
            e.printStackTrace();
        };
        return result;
    }

}
