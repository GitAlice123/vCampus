package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import view.Login.logInUI;
import view.connect.*;
import view.DAO.UserDao;
import view.Login.User;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import view.connect.BoolRespMessage;




public class ServerApplication {

    public static void main(String[] args) {
        int port = 8888; // 服务器监听的端口号


        try {
            // 创建 ServerSocket，监听指定端口
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            // 等待客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // 获取输入流
            InputStream inputStream = clientSocket.getInputStream();
            System.out.println("Get inputstream");

            byte[] lengthBytes = new byte[4];
            inputStream.read(lengthBytes);  // 读取消息长度
            int messageLength = ByteBuffer.wrap(lengthBytes).getInt();
            byte[] jsonDataBytes = new byte[messageLength];
            inputStream.read(jsonDataBytes);  // 读取消息内容
            String receivedJsonData = new String(jsonDataBytes, StandardCharsets.UTF_8);

            String jsonData = receivedJsonData.toString();
            System.out.println("Success");
            System.out.println(jsonData);

//          创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = jsonData.replaceAll("^\\[|\\]$", "");

//          将 JSON 数据还原为对象
            LoginMessage loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
            System.out.println("Into object");



//          inputStream.close();
            switch (loginMessage.getMessageType()) {
                case "login":
                    // 以下内容先写在这，后续封装成类调用，要不然代码太长
                    // 根据消息的类型决定服务器要采取的动作


                    UserDao userdao = new UserDao();
                    User user = userdao.findUserByuId(loginMessage.getUserId());
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

                            byte[] responseServer = outputData.getBytes(StandardCharsets.UTF_8);
                            int responseLength = responseServer.length;
                            System.out.println(responseLength);
                            byte[] lengthBytesResponse = ByteBuffer.allocate(4).putInt(messageLength).array();
                            outputStream.write(responseLength);  // 写入消息长度
                            outputStream.write(responseServer);  // 写入消息内容
                            outputStream.flush();



                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    }

                    // 关闭连接
                    clientSocket.close();
                    serverSocket.close();
            }
        } catch (JsonMappingException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
