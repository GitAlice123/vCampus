package view.connect;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.Library.*;
import view.message.*;

import view.client.*;

public class ChatClientAPIImpl implements ChatClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();

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
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 Message 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(chatQuesMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 2000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        GPTAnsRepMessage gptAnsRepMessage = objectMapper.readValue(mess, GPTAnsRepMessage.class);

//      处理结果
        String gptAnswer = gptAnsRepMessage.getGPTanswer();

        return gptAnswer;
    }

    @Override
    public void sendUserMessage(ChatWithUserMessage chatWithUserMessage) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 Message 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(chatWithUserMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 2001);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllOnlineName(UniqueMessage uniqueMessage) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 Message 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 2002);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        OnlineListRespMessage gptAnsRepMessage = objectMapper.readValue(mess, OnlineListRespMessage.class);

        return gptAnsRepMessage.getOnlineList();
    }
}
