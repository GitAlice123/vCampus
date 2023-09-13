package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.Global.GlobalData;
import view.client.ClientRWTool;
import view.message.BankAccountMessage;
import view.message.BoolRespMessage;
import view.message.UniqueMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class NoticeClientAPIImpl implements NoticeClientAPI{
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private view.client.ClientRWTool ClientRWTool = new ClientRWTool();

    //构造函数
    public NoticeClientAPIImpl(String serverAddress, int serverPort) {
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
     * 读取数据库中的公告内容 800
     * @return 字符串类型的公告内容
     */
    @Override
    public String getNotice(){
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage=new UniqueMessage("");

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 800);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(mess, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        String result= uniqueMessage.getUniMessage();
        return result;
    }

    /**
     * 将公告内容修改为传入的字符串 801
     * @param text 公告内容
     * @return 是否成功修改
     */
    @Override
    public boolean editNotice(String text){
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage=new UniqueMessage(text);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 801);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = ClientRWTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage boolRespMessage = null;
        try {
            boolRespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        boolean result = boolRespMessage.getFlag();
        return result;
    }
}
