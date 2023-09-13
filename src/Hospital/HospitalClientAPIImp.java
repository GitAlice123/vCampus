package view.Hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.client.ClientRWTool;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 医院客户端API的实现类，用于与医院服务器进行通信和交互。
 */
public class HospitalClientAPIImp implements HospitalClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool ClientRWTool = new ClientRWTool();


    /**
     * 构造函数，创建与医院服务器的连接。
     *
     * @param serverAddress 服务器地址
     * @param serverPort    服务器端口号
     */
    public HospitalClientAPIImp(String serverAddress, int serverPort) {
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
     * 获取所有医院科室信息的方法。 500
     *
     * @return 包含所有科室信息的 Department 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Department[] GetAllDepartments() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 500);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department[] departments = RespMessage.getDepartments();
//      处理结果
        return departments;
    }

    /**
     * 根据用户ID返回所有挂号记录的方法。 501
     *
     * @param ID 用户的唯一标识符
     * @return 包含所有挂号记录的 Register 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Register[] GetRegisterByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 501);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        RegisterMessage RespMessage = objectMapper.readValue(mess, RegisterMessage.class);
        Register[] registers = RespMessage.getRegisters();
//      处理结果
        return registers;
    }

    /**
     * 根据用户ID返回所有未支付挂号记录的方法。502
     *
     * @param ID 用户的唯一标识符
     * @return 包含所有未支付挂号记录的 Register 数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Register[] GetPaymentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 502);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        RegisterMessage RespMessage = objectMapper.readValue(mess, RegisterMessage.class);
        Register[] registers = RespMessage.getRegisters();

        if (registers == null) {
            System.out.println("**********空空空为什么为什么1");
        } else {
            System.out.println("1***************长度为：" + registers.length);
        }
//      处理结果
        return registers;
    }

    /**
     * 根据挂号信息创建挂号记录的方法。503
     *
     * @param register 包含挂号信息的 Register 对象
     * @return 创建挂号是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public boolean CreaatPaymentByInfo(Register register) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(register);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 503);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }

    /**
     * 支付指定挂号订单的方法。504
     *
     * @param rID 挂号记录的唯一标识符
     * @return 支付是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public boolean PayAllPayment(String rID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            UniqueMessage uniqueMessage = new UniqueMessage(rID);
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 504);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }

    /**
     * 根据科室名称和医生类型寻找挂号的方法。505
     *
     * @param type  医生类型
     * @param level 科室级别
     * @return 匹配条件的科室数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Department[] GetDepartmentByinfo(String type, String level) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            StringPariMessage registerMessage = new StringPariMessage(type, level);
            String jsonData = objectMapper.writeValueAsString(registerMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 505);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department[] departments = RespMessage.getDepartments();
//      处理结果
        return departments;
    }

    /**
     * 根据科室ID查找科室的方法。506
     *
     * @param ID 科室的唯一标识符
     * @return 匹配的科室对象
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Department GetDepartmentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 506);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department department = RespMessage.getDepartment();
//      处理结果
        return department;
    }

    /**
     * 返回所有挂号信息的方法。507
     *
     * @return 包含所有挂号信息的数组
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public Register[] GetAllRegisters() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage();
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 507);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        RegisterMessage RespMessage = objectMapper.readValue(mess, RegisterMessage.class);
        Register[] registers = RespMessage.getRegisters();
//      处理结果
        return registers;
    }

    /**
     * 根据信息新建科室的方法。508
     *
     * @param department 要添加的科室信息
     * @return 新建科室是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public boolean AddDepartmentByInfo(Department department) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(department);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 508);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }


    /**
     * 根据科室编号删除科室的方法。509
     *
     * @param ID 要删除的科室的编号
     * @return 删除科室是否成功的布尔值
     * @throws IOException 如果与服务器通信过程中发生 I/O 错误
     */
    @Override
    public boolean DeleteDepartmentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            ClientRWTool.ClientSendOutStream(outputStream, jsonData, 509);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = ClientRWTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }
}
