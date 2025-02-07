package view.Bank;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.client.ClientRWTool;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

public class IBankClientAPIImpl implements IBankClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();

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
     *
     * @param id    用户一卡通ID
     * @param money 充值金额
     * @param pwd   用户输入的密码
     * @return 充值结果，true表示充值成功，false表示充值失败
     */
    @Override
    public double recharge(String id, double money, String pwd) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankMoneyMessage bankMoneyMessage = new BankMoneyMessage(id, money, pwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankMoneyMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1001);

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
        DoubleMesage DoubleMesage = null;
        try {
            DoubleMesage = objectMapper.readValue(mess, DoubleMesage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        double result = DoubleMesage.getaDouble();
        return result;
    }


    /**
     * 修改密码
     *
     * @param id        用户一卡通ID
     * @param oldPwd    原密码
     * @param newPwd    新密码
     * @param newNewPwd 确认新密码
     * @return 修改密码结果，true表示修改成功，false表示修改失败
     */
    @Override
    public int changePwd(String id, String oldPwd, String newPwd, String newNewPwd)
            throws NullPointerException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankChangePwdMessage bankChangePwdMessage = new BankChangePwdMessage(id, oldPwd, newPwd, newNewPwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankChangePwdMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1003);

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
        IntMessage intMessage = null;
        try {
            intMessage = objectMapper.readValue(mess, IntMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        int result = intMessage.getNum();
        return result;
    }

    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id        需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query     查询关键字，即账单描述
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
            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1004);

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
     *
     * @param id  一卡通号
     * @param pwd 用户输入的密码
     * @return 挂失/解挂结果，true表示挂失/解挂成功，false表示挂失/解挂失败
     */
    @Override
    public int changeLoss(String id, String pwd) throws NullPointerException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankIDMessage bankIDMessage = new BankIDMessage(id, pwd);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankIDMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1000);

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
        IntMessage intMessage = null;
        try {
            intMessage = objectMapper.readValue(mess, IntMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        int result = intMessage.getNum();
        return result;
    }


    /**
     * 消费
     *
     * @param id   用户一卡通ID
     * @param bill 账单信息
     * @param pwd  用户输入的密码
     * @param isCoercive 是否为强制扣费（如水电费、学费），true代表是强制扣费
     * @return 消费结果，true表示消费成功，false表示消费失败
     */
    public double bankConsume(String id, bankBill bill, String pwd,boolean isCoercive) throws IOException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankBillMessage bankBillMessage = new BankBillMessage(id, bill, pwd,isCoercive);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankBillMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1002);

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
        DoubleMesage doubleMesage = null;
        try {
            doubleMesage = objectMapper.readValue(mess, DoubleMesage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//      处理结果
        double result = doubleMesage.getaDouble();
        return result;
    }

    /**
     * 通过一卡通号查找account
     *
     * @param id 一卡通号
     * @return bankAccount 账户信息
     */
    @Override
    public bankAccount findBankAccountById(String id) throws NullPointerException {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankIDMessage bankIDMessage = new BankIDMessage(id);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankIDMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1005);

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

    /**
     * 用一卡通号查询并返回所有的银行账户信息。管理员用
     *
     * @return 包含所有银行账户的 bankAccount[] 数组，如果没有找到任何账户则返回 null。
     */
    public String[][] findBankAccounts(String id) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankIDMessage bankIDMessage = new BankIDMessage(id);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankIDMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1006);

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
     * 添加银行账户（1007操作码）。
     *
     * @param bankaccount 要添加的银行账户
     * @return 如果成功添加银行账户，则返回true；否则返回false
     */
    public boolean addBankAccount(bankAccount bankaccount) {
        //以下发送用户id给服务器
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            BankAccountMessage bankAccountMessage = new BankAccountMessage(bankaccount);

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(bankAccountMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 1007);

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


