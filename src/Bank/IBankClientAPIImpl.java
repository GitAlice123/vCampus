package view.Bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.connect.RWTool;
import view.message.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class IBankClientAPIImpl implements IBankClientAPI{
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();
    //构造函数
    public IBankClientAPIImpl(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 充值
     * @param id 用户一卡通ID
     * @param money 充值金额
     * @param pwd 用户输入的密码
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    @Override
    public boolean recharge(String id, double money, String pwd){
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankMoneyMessage bankMoneyMessage=new BankMoneyMessage(id,money,pwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankMoneyMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,1001);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
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


    /**
     * 修改密码
     * @param id 用户一卡通ID
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @param newNewPwd 确认新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    @Override
    public boolean changePwd(String id, String oldPwd, String newPwd,String newNewPwd)
            throws NullPointerException{
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankChangePwdMessage bankChangePwdMessage=new BankChangePwdMessage(id,oldPwd,newPwd,newNewPwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankChangePwdMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,1003);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
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

    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id       需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query    查询关键字，即账单描述
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    @Override
    public String[][] billForSometime(String id, Date startTime, Date endTime, String query) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankSearchBillsMessage bankSearchBillsMessage = new BankSearchBillsMessage(id, startTime, endTime, query);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankSearchBillsMessage);
            System.out.println(jsonData);

            //发给服务端
            rwTool.ClientSendOutStream(outputStream, jsonData, 1004);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
//        StringArrayRespMessage stringArrayRespMessage = null;
//        try {
//            stringArrayRespMessage = objectMapper.readValue(mess, StringArrayRespMessage.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        String[][] result = null;
        try {
            result = objectMapper.readValue(mess, String[][].class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        //String[][] result = stringArrayRespMessage.getData();
        return result;
    }



    /**
     * 挂失/解挂
     * @param id 一卡通号
     * @param pwd 用户输入的密码
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    @Override
    public boolean changeLoss(String id,String pwd) throws NullPointerException{
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankIDMessage bankIDMessage=new BankIDMessage(id,pwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankIDMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
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


    /**
     * 消费
     * @param id 用户一卡通ID
     * @param bill 账单信息
     * @param pwd 用户输入的密码
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public boolean bankConsume(String id, bankBill bill,String pwd) throws IOException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankBillMessage bankBillMessage=new BankBillMessage(id,bill,pwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankBillMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,1002);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
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

    /**
     * 通过一卡通号查找account
     * @param id 一卡通号
     * @return bankAccount 账户信息
     */
    @Override
    public bankAccount findBankAccountById(String id) throws NullPointerException{
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankIDMessage bankIDMessage=new BankIDMessage(id);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankIDMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,1005);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = null;
        try {
            receivedJsonData = rwTool.ClientReadStream(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        bankAccount bankA = null;
        try {
            bankA = objectMapper.readValue(mess, bankAccount.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        bankAccount result = bankA;
        return result;
    }
}


