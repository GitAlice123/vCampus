package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import view.DAO.*;

import view.Login.User;
import view.message.*;

import java.io.OutputStream;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerActionTool {
    public ServerActionTool() {
    }
    private RWTool rwTool = new RWTool();
    public void Action100(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 100");
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
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 101");
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
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action102(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 102");

        Boolean flag = false;

        String userId = loginMessage.getUserId();
        String password = loginMessage.getPassword();
        String role = loginMessage.getRole();
        User user = new User();
        user.setuId(userId);
        user.setuPwd(password);
        user.setuRole(role);
        UserDao userdao = new UserDao();
        userdao.CreateUser(user);//创建新用户
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

