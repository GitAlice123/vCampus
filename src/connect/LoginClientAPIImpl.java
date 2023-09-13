package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.client.ClientRWTool;
import view.message.BoolRespMessage;
import view.message.LoginMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * 登录客户端API实现，用于与服务器进行用户登录验证。
 */
public class LoginClientAPIImpl implements LoginClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();

    /**
     * 构造一个LoginClientAPIImpl实例，并连接到服务器。
     *
     * @param serverAddress 服务器地址
     * @param serverPort    服务器端口
     */
    public LoginClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过用户提供的登录信息进行用户登录。
     *
     * @param loginMessage 登录消息对象，包含用户ID和密码
     * @return 如果登录成功返回true，否则返回false
     * @throws IOException 如果发生通信错误
     */
    @Override
    public boolean loginByUserId(LoginMessage loginMessage) throws IOException {
        //以下发送用户id和密码给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(loginMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 100);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

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