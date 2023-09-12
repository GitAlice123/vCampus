package view.server;

import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.Bank.bankServerActionTool;
import view.Shop.ShopServerActionTool;
import view.connect.Pair;
import view.message.*;

public class ServerApplication {
    private static ServerSocket serverSocket;
    //    private Map<String, ClientHandler> clientHandlerMap = new HashMap<>();
    private Vector<ClientHandler> clients = new Vector<ClientHandler>();
    private Vector<Socket> threadRefreshOnline = new Vector<Socket>();

    public void startServer() throws IOException {
        int port = 8888; // 服务器监听的端口号
        ServerRWTool rwTool = new ServerRWTool();
        ServerActionTool serverActionTool = new ServerActionTool();
        bankServerActionTool bankServerActionTool = new bankServerActionTool();
        ShopServerActionTool shopServerActionTool = new ShopServerActionTool();

        // 创建 ServerSocket，监听指定端口
        serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            Socket clientSocket = null;
            try {
                // 等待客户端连接
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                // 创建一个新线程处理客户端请求
                ClientHandler clientHandler = new ClientHandler(clientSocket, rwTool, serverActionTool,
                        bankServerActionTool, shopServerActionTool);
//                clients.add(clientHandler);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();


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

    private void forwardMessage(String myID,String userId, String message) {
//        ClientHandler clientHandler = clientHandlerMap.get(userId);
        for (ClientHandler thread : clients) {
            System.out.println("Into for");
            if (thread.getChatOnline()) {
                System.out.println(thread.getUserId() + " is online");
                System.out.println(userId);
                if (Objects.equals(thread.getUserId(), userId)) {
                    System.out.println("equals " + userId);
                    thread.serverActionTool.Action0(myID,message, thread.clientSocket);
                    break;
                }
            }
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;
        private ServerRWTool rwTool;
        private ServerActionTool serverActionTool;
        private bankServerActionTool bankServerActionTool;
        private ShopServerActionTool shopServerActionTool;

        public void setUserID(String userID) {
            this.userID = userID;
        }

        private String userID;

        public Boolean getChatOnline() {
            return isChatOnline;
        }

        public void setChatOnline(Boolean chatOnline) {
            isChatOnline = chatOnline;
        }

        private Boolean isChatOnline = false;

        public ClientHandler(Socket clientSocket, ServerRWTool rwTool, ServerActionTool serverActionTool,
                             bankServerActionTool bankServerActionTool, ShopServerActionTool shopServerActionTool) {
            this.clientSocket = clientSocket;
            this.rwTool = rwTool;
            this.serverActionTool = serverActionTool;
            this.bankServerActionTool = bankServerActionTool;
            this.shopServerActionTool = shopServerActionTool;
        }

        public String getUserId() {
            return userID;
        }

        @Override
        public void run() {
            System.out.println("Begin thread run");
            try {
                // 获取输入流
                InputStream inputStream = clientSocket.getInputStream();
                System.out.println("Get inputstream");

                // 信号、消息长度、学号信息
                // 或
                // 长度、消息编码、信息
                serverGetMessage result = rwTool.ServerReadStream(inputStream);
                int signalOrLength = result.getSignalOrLength();
                int LengthOrCode = result.getDataLengthOrCode();
                String jsonData = result.getIDInfoOrMessage();
                if (signalOrLength == 0) {
                    setChatOnline(true);
                    System.out.println("online!");
                    setUserID(jsonData);
                    /*
                    上线时展示同时在线的客户端
                     */
                    clients.add(this);
                    List<String> stringList = new ArrayList<>();
                    // 使用循环添加元素到列表中
                    for (ClientHandler thread : clients) {
                        stringList.add(thread.getUserId());
                    }
                    serverActionTool.setOnlineList(stringList);


                    /*
                    上线时更改其他所有客户端的在线人员
                     */
//                    for(Socket clientSocket : threadRefreshOnline){
//                        if(clientSocket==this.clientSocket)
//                            continue;
//                        UniqueMessage uniqueMessage = new UniqueMessage("yes");
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        String d = objectMapper.writeValueAsString(uniqueMessage);
//                        serverActionTool.Action2002(d,clientSocket);
//                    }

                }
                else if(signalOrLength == 1){

                    threadRefreshOnline.add(clientSocket);
                }

                switch (LengthOrCode) {
                    case 100: {
                        // 100为LoginMessage
                        // 根据消息的类型决定服务器要采取的动作
                        setUserID(serverActionTool.Action100(jsonData, clientSocket));
                        System.out.println("this " + getUserId());
                        break;
                    }
                    case 101: {
                        // 101为RegisterReqMessage
                        serverActionTool.Action101(jsonData, clientSocket);
                        break;
                    }
                    case 102: {
                        serverActionTool.Action102(jsonData, clientSocket);
                        break;
                    }
                    case 200: {
                        serverActionTool.Action200(jsonData, clientSocket);
                        break;
                    }
                    case 201: {
                        serverActionTool.Action201(jsonData, clientSocket);
                        break;
                    }
                    case 202: {
                        serverActionTool.Action202(jsonData, clientSocket);
                        break;
                    }
                    case 203: {
                        serverActionTool.Action203(jsonData, clientSocket);
                        break;
                    }
                    case 204: {
                        serverActionTool.Action204(jsonData, clientSocket);
                        break;
                    }
                    case 205: {
                        serverActionTool.Action205(jsonData, clientSocket);
                        break;
                    }
                    case 206: {
                        serverActionTool.Action206(jsonData, clientSocket);
                        break;
                    }
                    case 207: {
                        serverActionTool.Action207(jsonData, clientSocket);
                        break;
                    }
                    case 208: {
                        serverActionTool.Action208(jsonData, clientSocket);
                        break;
                    }
                    case 209: {
                        serverActionTool.Action209(jsonData, clientSocket);
                        break;
                    }
                    case 210: {
                        serverActionTool.Action210(jsonData, clientSocket);
                        break;
                    }
                    case 211: {
                        serverActionTool.Action211(jsonData, clientSocket);
                        break;
                    }
                    case 212: {
                        serverActionTool.Action212(jsonData, clientSocket);
                        break;
                    }
                    case 213: {
                        serverActionTool.Action213(jsonData, clientSocket);
                        break;
                    }
                    case 214: {
                        serverActionTool.Action214(jsonData, clientSocket);
                        break;
                    }
                    case 215: {
                        serverActionTool.Action215(jsonData, clientSocket);
                        break;
                    }
                    case 2000: {
                        serverActionTool.Action2000(jsonData, clientSocket);
                        break;
                    }
                    case 500: {
                        serverActionTool.Action500(jsonData, clientSocket);
                        break;
                    }
                    case 501: {
                        serverActionTool.Action501(jsonData, clientSocket);
                        break;
                    }
                    case 502: {
                        serverActionTool.Action502(jsonData, clientSocket);
                        break;
                    }
                    case 503: {
                        serverActionTool.Action503(jsonData, clientSocket);
                        break;
                    }
                    case 504: {
                        serverActionTool.Action504(jsonData, clientSocket);
                        break;
                    }
                    case 505: {
                        serverActionTool.Action505(jsonData, clientSocket);
                        break;
                    }
                    case 506: {
                        serverActionTool.Action506(jsonData, clientSocket);
                        break;
                    }
                    case 507: {
                        serverActionTool.Action507(jsonData, clientSocket);
                        break;
                    }
                    case 508: {
                        serverActionTool.Action508(jsonData, clientSocket);
                        break;
                    }
                    case 509: {
                        serverActionTool.Action509(jsonData, clientSocket);
                        break;
                    }
                    case 900: {
                        // 根据商品ID或商品名称查询商品信息，供学生使用 900
                        // 根据消息的类型决定服务器要采取的动作
                        shopServerActionTool.Action900(jsonData, clientSocket);
                        break;
                    }
                    case 901: {
                        //根据商品ID或商品名称查询商品信息，供管理员使用 901
                        shopServerActionTool.Action901(jsonData, clientSocket);
                        break;
                    }
                    case 902: {
                        //将指定名称的商品按照用户输入的数量加入购物车 902
                        shopServerActionTool.Action902(jsonData, clientSocket);
                        break;
                    }
                    case 903: {
                        //返回购物车中的商品列表，以String[][]形式表示 903
                        shopServerActionTool.Action903(jsonData, clientSocket);
                        break;
                    }
                    case 904: {
                        //返回商店中所有商品的列表，以String[][]形式表示，学生用 904
                        shopServerActionTool.Action904(jsonData, clientSocket);
                        break;
                    }
                    case 905: {
                        //返回商店中所有商品的列表，以String[][]形式表示，管理员用 905
                        shopServerActionTool.Action905(jsonData, clientSocket);
                        break;
                    }
                    case 906: {
                        //管理员进货操作，将指定的商品信息加入到商品数据库中 906
                        shopServerActionTool.Action906(jsonData, clientSocket);
                        break;
                    }
                    case 907: {
                        //管理员退货操作，从商品数据库中减少指定商品的库存数量，907
                        shopServerActionTool.Action907(jsonData, clientSocket);
                        break;
                    }
                    case 908: {
                        //查找并返回数据库所有的购买记录信息 908
                        shopServerActionTool.Action908(jsonData, clientSocket);
                        break;
                    }
                    case 909: {
                        //查找并返回数据库中一卡通号为uId的所有购买记录信息 909
                        //根据商品ID或商品名称查询商品信息，并返回包含这个商品所有属性数据的String[][]  910
                        shopServerActionTool.Action909(jsonData, clientSocket);
                        break;
                    }
                    case 910: {
                        //根据商品ID或商品名称查询商品信息，并返回包含这个商品所有属性数据的String[][]  910
                        shopServerActionTool.Action910(jsonData, clientSocket);
                        break;
                    }
                    case 911: {
                        //后端删除购物车中商品  911  新增购买记录信息 912
                        shopServerActionTool.Action911(jsonData, clientSocket);
                        break;
                    }
                    case 912: {
                        //新增购买记录信息 912
                        shopServerActionTool.Action912(jsonData, clientSocket);
                        break;
                    }
                    case 1000: {
                        // 挂失/解挂:1000
                        // 根据消息的类型决定服务器要采取的动作
                        bankServerActionTool.Action1000(jsonData, clientSocket);
                        break;
                    }
                    case 1001: {
                        //充值：1001
                        bankServerActionTool.Action1001(jsonData, clientSocket);
                        break;
                    }
                    case 1002: {
                        //消费
                        bankServerActionTool.Action1002(jsonData, clientSocket);
                        break;
                    }
                    case 1003: {
                        //修改密码
                        bankServerActionTool.Action1003(jsonData, clientSocket);
                        break;
                    }
                    case 1004: {
                        //查询筛选账单
                        bankServerActionTool.Action1004(jsonData, clientSocket);
                        break;
                    }
                    case 1005: {
                        //用id查找account
                        bankServerActionTool.Action1005(jsonData, clientSocket);
                        break;
                    }
                    case 1006: {
                        //管理员查看所有account
                        bankServerActionTool.Action1006(jsonData, clientSocket);
                        break;
                    }
                    case 1007: {
                        //注册用户时新建bankAccount
                        bankServerActionTool.Action1007(jsonData, clientSocket);
                        break;
                    }
                    case 2001: {
                        //和其他客户端发消息通信
                        ChatWithUserMessage chatWithUserMessage = serverActionTool.Action2001(jsonData, clientSocket);
                        String targetUserID = chatWithUserMessage.getToUserID();
                        String message = chatWithUserMessage.getMessageToSent();
                        String fromID = chatWithUserMessage.getMyID();
                        System.out.println("Server get message, ready to forward");
                        System.out.println("Target userID is " + targetUserID);
                        forwardMessage(fromID,targetUserID, message);
                        break;
                    }
                    case 2002:{
                        serverActionTool.Action2002(jsonData,clientSocket);
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

        public void processMessage(String message) {
            // 在这里处理来自服务器的消息

        }

    }


}







    
    
        
        
    
    
    
        
    
    


