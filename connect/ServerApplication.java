package view.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import view.logInUI;
import view.connect.*;
import view.DAO.UserDao;
import view.Login.User;

import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;
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

            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
            while ((len = inputStream.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, "UTF-8"));
            }

            String jsonData = sb.toString();
            System.out.println("Success");
            System.out.println(jsonData);

// 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            jsonData = jsonData.replaceAll("^\\[|\\]$", "");

// 将 JSON 数据还原为对象
            LoginMessage loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
            System.out.println("Into object");



//            inputStream.close();
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
                            System.out.println(outputData);

                            System.out.println("server socket is closed?:" + clientSocket.isClosed());
                            // 获取输出流
                            OutputStream outputStream = clientSocket.getOutputStream();

                            outputStream.write(outputData.getBytes(StandardCharsets.UTF_8));
                            clientSocket.shutdownOutput();


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
