package view.connect;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import view.Bank.bankServerActionTool;

public class ServerApplication {

    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        int port = 8888; // 服务器监听的端口号
        RWTool rwTool = new RWTool();
        ServerActionTool serverActionTool = new ServerActionTool();
        bankServerActionTool bankServerActionTool = new bankServerActionTool();

        // 创建 ServerSocket，监听指定端口
        serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket clientSocket = null;
            try {
                // 等待客户端连接
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // 获取输入流
                InputStream inputStream = clientSocket.getInputStream();
                System.out.println("Get inputstream");

                Pair<String, Integer> result = rwTool.ServerReadStream(inputStream);
                String jsonData = result.getFirst();
                int messageNumber = result.getSecond();

                switch (messageNumber) {
                    case 100: {
                        // 100为LoginMessage
                        // 根据消息的类型决定服务器要采取的动作
                        serverActionTool.Action100(jsonData, clientSocket);
                        break;
                    }
                    case 101: {
                        // 101为RegisterReqMessage
                        serverActionTool.Action101(jsonData, clientSocket);
                        break;
                    }
                    case 102: {
                        serverActionTool.Action102(jsonData,clientSocket);
                        break;
                    }
                    case 1000: {
                        // 挂失/解挂:1000
                        // 根据消息的类型决定服务器要采取的动作
                        bankServerActionTool.Action1000(jsonData, clientSocket);
                        break;
                    }
                    case 1001:{
                        //充值：1001
                        bankServerActionTool.Action1001(jsonData, clientSocket);
                        break;
                    }
                    case 1002:{
                        //消费
                        bankServerActionTool.Action1002(jsonData, clientSocket);
                        break;
                    }
                    case 1003:{
                        //修改密码
                        bankServerActionTool.Action1003(jsonData, clientSocket);
                        break;
                    }
                    case 1004:{
                        //查询筛选账单
                        bankServerActionTool.Action1004(jsonData, clientSocket);
                        break;
                    }
                    case 1005:{
                        //用id查找account
                        bankServerActionTool.Action1005(jsonData, clientSocket);
                        break;
                    }
                    case 200: {
                        serverActionTool.Action200(jsonData,clientSocket);
                        break;
                    }
                    case 201: {
                        serverActionTool.Action201(jsonData,clientSocket);
                        break;
                    }
                    case 202: {
                        serverActionTool.Action202(jsonData,clientSocket);
                        break;
                    }
                    case 203: {
                        serverActionTool.Action203(jsonData,clientSocket);
                        break;
                    }
                    case 204: {
                        serverActionTool.Action204(jsonData,clientSocket);
                        break;
                    }
                    case 205: {
                        serverActionTool.Action205(jsonData,clientSocket);
                        break;
                    }
                    case 206: {
                        serverActionTool.Action206(jsonData,clientSocket);
                        break;
                    }
                    case 207: {
                        serverActionTool.Action207(jsonData,clientSocket);
                        break;
                    }
                    case 208: {
                        serverActionTool.Action208(jsonData,clientSocket);
                        break;
                    }
                    default: {
                        // 处理其他消息类型
                        break;
                    }
                }
            } catch (JsonMappingException e) {
                System.out.println(e);
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void closeServer() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                System.out.println("Server closed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
