package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.Login.User;
import view.message.*;
import view.client.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CourseSelectClientAPIImp implements CourseSelectClientAPI{
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ClientRWTool rwTool = new ClientRWTool();
    public CourseSelectClientAPIImp(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //展示所有课程 400
    @Override
    public Course[] GetAllCourse() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,400);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        CoursesRespMessage RespMessage = objectMapper.readValue(mess, CoursesRespMessage.class);
        Course[] courses=RespMessage.getCourses();
//      处理结果
        return courses;
    }

    //401 按CourseID 查找Class
    @Override
    public CourseClass[] SearchClassByCourseID(String couID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage(couID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,401);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        CourseClassesRespMessage RespMessage = objectMapper.readValue(mess, CourseClassesRespMessage.class);
        CourseClass[] classes=RespMessage.getCourseClasses();
//      处理结果
        return classes;
    }

    //402 未完成 DAO
    @Override
    public CourseClass[] SearchSelectClassByID(String stuID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage(stuID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,402);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        CourseClassesRespMessage RespMessage = objectMapper.readValue(mess, CourseClassesRespMessage.class);
        CourseClass[] classes=RespMessage.getCourseClasses();
//      处理结果
        return classes;
    }

    //学生选课 通过stuID classID建立连接 403
    @Override
    public boolean AddClassByinfo(String stuID, String classID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            StringPariMessage message=new StringPariMessage(stuID,classID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(message);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,403);

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
        boolean result=RespMessage.getFlag();
//      处理结果
        return result;
    }

    //404 退课
    @Override
    public boolean QuitClassByinfo(String stuID, String classID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            StringPariMessage message=new StringPariMessage(stuID,classID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(message);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,404);

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
        boolean result=RespMessage.getFlag();
//      处理结果
        return result;
    }

    //405 根据courseID找到对应的class数量
    @Override
    public int GetClassNumByCourseID(String courseID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage(courseID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,405);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        IntMessage RespMessage = objectMapper.readValue(mess, IntMessage.class);
        int result=RespMessage.getInt();
//      处理结果
        return result;
    }

    @Override
    public CourseClass[] GetAllClass() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,406);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        CourseClassesRespMessage RespMessage = objectMapper.readValue(mess, CourseClassesRespMessage.class);
        CourseClass[] courses=RespMessage.getCourseClasses();
//      处理结果
        return courses;
    }

    @Override
    public boolean DeleteClassByClassid(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage=new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,407);

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
        boolean result=RespMessage.getFlag();
//      处理结果
        return result;
    }

    @Override
    public boolean AddClass(CourseClass courseClass) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(courseClass);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,408);

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
        boolean result=RespMessage.getFlag();
//      处理结果
        return result;
    }

    @Override
    public boolean ModifyClassByinfo(CourseClass courseClass) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(courseClass);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream,jsonData,409);

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
        boolean result=RespMessage.getFlag();
//      处理结果
        return result;
    }

    @Override
    public User[] GetAllTeacher() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage();
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 410);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        UserMessage RespMessage = objectMapper.readValue(mess, UserMessage.class);
        User[] result = RespMessage.getUsers();
//      处理结果
        return result;
    }
}
