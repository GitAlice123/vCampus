package view.Bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.message.*;
import view.server.ServerRWTool;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class bankServerActionTool {
    private bankFunction funcs = new bankFunction();
    private ServerRWTool ServerRWTool = new ServerRWTool();

    public bankServerActionTool() {

    }

    /**
     * 对应changeLoss
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1000(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankIDMessage bankIDMessage = null;
        try {
            bankIDMessage = objectMapper.readValue(jsonData, BankIDMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1000");

        int flag = -1;
        //执行对应的操作，这里是解挂/挂失，调用bankFunction里面的函数即可
        String id = bankIDMessage.getId();
        String pwd = bankIDMessage.getPwd();
        flag = funcs.changeLoss(id, pwd);

        //下面将response信息返回客户端
        IntMessage respMessage = new IntMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应充值recharge
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1001(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankMoneyMessage bankMoneyMessage = null;
        try {
            bankMoneyMessage = objectMapper.readValue(jsonData, BankMoneyMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1001");


        //执行对应操作，这里是充值，直接调用bankFunction里的方法即可
        double flag = -3.00;
        String userId = bankMoneyMessage.getId();
        double userMoney = bankMoneyMessage.getMoney();
        String userPwd = bankMoneyMessage.getPwd();
        flag = funcs.recharge(userId, userMoney, userPwd);

        //下面将response信息返回客户端
        DoubleMesage respMessage = new DoubleMesage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应非强制消费consume
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1002(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankBillMessage bankBillMessage = null;
        try {
            bankBillMessage = objectMapper.readValue(jsonData, BankBillMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1002");


        //执行对应操作，这里是消费，直接调用bankFunction里的方法即可
        double flag = -4.00;
        String userId = bankBillMessage.getId();
        bankBill userBill = bankBillMessage.getBill();
        String userPwd = bankBillMessage.getPwd();
        boolean isC= bankBillMessage.isCoercive();
        try {
            flag = funcs.bankConsume(userId, userBill, userPwd,isC);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //下面将response信息返回客户端
        DoubleMesage respMessage = new DoubleMesage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应修改密码changePwd
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1003(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankChangePwdMessage bankChangePwdMessage = null;
        try {
            bankChangePwdMessage = objectMapper.readValue(jsonData, BankChangePwdMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1003");


        //执行对应操作，这里是消费，直接调用bankFunction里的方法即可
        int flag = -1;
        String userId = bankChangePwdMessage.getId();
        String userOldPwd = bankChangePwdMessage.getOldPwd();
        String userNewPwd = bankChangePwdMessage.getNewPwd();
        String usernewNewPwd = bankChangePwdMessage.getnewNewPwd();
        flag = funcs.changePwd(userId, userOldPwd, userNewPwd, usernewNewPwd);

        //下面将response信息返回客户端
        IntMessage respMessage = new IntMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理查询账单功能（1004操作码）。
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1004(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankSearchBillsMessage bankSearchBillsMessage = null;
        try {
            bankSearchBillsMessage = objectMapper.readValue(jsonData, BankSearchBillsMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1004");


        //执行对应操作，这里是消费，直接调用bankFunction里的方法即可
        String[][] result;
        String userId = bankSearchBillsMessage.getId();
        Date start = bankSearchBillsMessage.getStartTime();
        Date end = bankSearchBillsMessage.getEndTime();
        String query = bankSearchBillsMessage.getQuery();
        result = funcs.billForSometime(userId, start, end, query);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过id查找account
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1005(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankIDMessage bankIDMessage = null;
        try {
            bankIDMessage = objectMapper.readValue(jsonData, BankIDMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1005");


        //执行对应操作，这里是消费，直接调用bankFunction里的方法即可
        bankAccount result;
        String userId = bankIDMessage.getId();
        result = funcs.findBankAccountById(userId);

        //下面将response信息返回客户端
        bankAccount respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员获取账户信息
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1006(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankIDMessage bankIDMessage = null;
        try {
            bankIDMessage = objectMapper.readValue(jsonData, BankIDMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1006");


        //执行对应操作，这里是查找所有账单
        String[][] result;
        String userID = bankIDMessage.getId();
        result = funcs.findBankAccounts(userID);

        //下面将response信息返回客户端
        String[][] respMessage = result;
        try {
            // 将 respMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 对应新建bankAccount
     *
     * @param jsonData     包含查询信息的JSON数据
     * @param clientSocket 与客户端通信的套接字
     */
    public void Action1007(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BankAccountMessage bankAccountMessage = null;
        try {
            bankAccountMessage = objectMapper.readValue(jsonData, BankAccountMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 1007");


        //执行对应操作，这里是消费，直接调用bankFunction里的方法即可
        Boolean flag = false;
        bankAccount bankA = bankAccountMessage.getBankA();
        flag = funcs.addBankAccount(bankA);


        //下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 bankMoneyMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            ServerRWTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
