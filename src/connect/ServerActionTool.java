package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import view.DAO.*;

import view.message.*;

import java.io.OutputStream;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerActionTool {
    public ServerActionTool() {
    }
    private RWTool rwTool = new RWTool();
    public void Action100(String jsonData, Socket clientSocket) {
        //        创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
//          将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object");
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(loginMessage.getUserId());
        boolean flag = false;
        if (user != null && user.getuPwd().equals(loginMessage.getPassword())
                && user.getuRole().equals(loginMessage.getRole())) {
            flag = true;
            //下面将response信息返回客户端
            BoolRespMessage respMessage = new BoolRespMessage(flag);
            try {
                // 将 LoginMessage 对象转换为 JSON 字符串
                String outputData = objectMapper.writeValueAsString(respMessage);
                OutputStream outputStream = clientSocket.getOutputStream();
                rwTool.ServerSendOutStream(outputStream, outputData);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void Action101(String jsonData, Socket clientSocket){
        //        创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
//          将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object");
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
        boolean flag = false;
        // flag = true表示user已经在表单里了
        if (user != null) {
            flag = true;
        }
        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}