package view.Hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.connect.RWTool;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HospitalClientAPIImp implements HospitalClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();

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

    //500 返回所有department
    @Override
    public Department[] GetAllDepartments() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            UniqueMessage uniqueMessage = new UniqueMessage();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 500);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department[] departments = RespMessage.getDepartments();
//      处理结果
        return departments;
    }

    //501  根据用户ID返回所有挂号记录
    @Override
    public Register[] GetRegisterByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 501);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        RegisterMessage RespMessage = objectMapper.readValue(mess, RegisterMessage.class);
        Register[] registers = RespMessage.getRegisters();
//      处理结果
        return registers;
    }

    //502 根据ID返回所有未支付挂号
    @Override
    public Register[] GetPaymentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 502);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

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

    //503 根据register创建挂号
    @Override
    public boolean CreaatPaymentByInfo(Register register) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(register);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 503);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }

    //504 支付所选订单
    @Override
    public boolean PayAllPayment(String rID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            UniqueMessage uniqueMessage = new UniqueMessage(rID);
            String jsonData = objectMapper.writeValueAsString(uniqueMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 504);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }

    //505 根据科室名称和医生类型寻找挂号
    @Override
    public Department[] GetDepartmentByinfo(String type, String level) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            StringPariMessage registerMessage = new StringPariMessage(type, level);
            String jsonData = objectMapper.writeValueAsString(registerMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 505);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department[] departments = RespMessage.getDepartments();
//      处理结果
        return departments;
    }

    //506 根据科室ID查找科室
    @Override
    public Department GetDepartmentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 506);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DepartmentsMessage RespMessage = objectMapper.readValue(mess, DepartmentsMessage.class);
        Department department = RespMessage.getDepartment();
//      处理结果
        return department;
    }

    //507 返回所有挂号信息
    @Override
    public Register[] GetAllRegisters() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage();
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 507);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        RegisterMessage RespMessage = objectMapper.readValue(mess, RegisterMessage.class);
        Register[] registers = RespMessage.getRegisters();
//      处理结果
        return registers;
    }

    //508 根据信息新建科室
    @Override
    public boolean AddDepartmentByInfo(Department department) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(department);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 508);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        BoolRespMessage RespMessage = objectMapper.readValue(mess, BoolRespMessage.class);
        boolean result = RespMessage.getFlag();
//      处理结果
        return result;
    }


    //509 根据科室编号删除科室
    @Override
    public boolean DeleteDepartmentByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 509);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

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
