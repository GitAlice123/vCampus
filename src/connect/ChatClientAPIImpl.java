package view.connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.Library.*;
import view.message.*;

public class ChatClientAPIImpl implements ChatClientAPI{
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();
    public ChatClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getGPTAnswer(ChatQuesMessage chatQuesMessage) throws IOException {
        //以下发送无数据的请求消息给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 Message 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(chatQuesMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        GPTAnsRepMessage gptAnsRepMessage = objectMapper.readValue(mess, GPTAnsRepMessage.class);

//      处理结果
        String gptAnswer = gptAnsRepMessage.getGPTanswer();

        return gptAnswer;
    }
}
