package view.connect;

import com.fasterxml.jackson.databind.ObjectMapper;
import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.SchoolRolls.Grade;
import view.SchoolRolls.StudentInfo;
import view.message.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class InfoClientAPIImp implements InfoClientAPI {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private RWTool rwTool = new RWTool();

    public InfoClientAPIImp(String serverAddress, int serverPort) {
        try {
            // 创建 Socket 连接
            socket = new Socket(serverAddress, serverPort);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //300
    @Override
    public StudentInfo SearchStuInfoByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 300);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        StudentInfo RespMessage = objectMapper.readValue(mess, StudentInfo.class);

//      处理结果
        return RespMessage;
    }

    //301
    @Override
    public boolean AddStuInfo(StudentInfo Info) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(Info);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 301);

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

    //302
    @Override
    public boolean DeleteStuInfoByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 302);

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

    //303
    @Override
    public Grade[] SearchGradeByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 303);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        GradesRespMessage RespMessage = objectMapper.readValue(mess, GradesRespMessage.class);
        Grade[] result = RespMessage.getGrades();
//      处理结果
        return result;
    }

    //304
    @Override
    public Course SearchCourseByinfo(String CourseID, String stuID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            StringPariMessage stringPariMessage = new StringPariMessage(CourseID, stuID);
            String jsonData = objectMapper.writeValueAsString(stringPariMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 304);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        Course RespMessage = objectMapper.readValue(mess, Course.class);

//      处理结果
        return RespMessage;
    }

    //306
    //按照教师ID搜索课程班级
    @Override
    public CourseClass[] SearchCourseClassByTeacherID(String TeacherID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            IDReqMessage idReqMessage = new IDReqMessage(TeacherID);
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 306);

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
        CourseClass[] courses = RespMessage.getCourseClasses();
//      处理结果
        return courses;
    }

    //307
    @Override
    public Course[] SearchCourseByID(String ID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();

            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(ID);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 307);

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
        Course[] courses = RespMessage.getCourses();
//      处理结果
        return courses;
    }

    //308
    @Override
    public StudentInfo[] SearchStudentByClassID(String classid) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage idReqMessage = new IDReqMessage(classid);
            String jsonData = objectMapper.writeValueAsString(idReqMessage);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 308);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        StudentInfoRespMessage RespMessage = objectMapper.readValue(mess, StudentInfoRespMessage.class);
        StudentInfo[] studentInfos = RespMessage.getStudentInfos();
//      处理结果
        return studentInfos;
    }

    @Override
    public boolean ModifyGrade(Grade grade) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(grade);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 309);

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

    @Override
    public double FindGradeByInfo(String StudentID, String courseID) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            Pair<String, String> pair = new Pair<>(StudentID, courseID);
            String jsonData = objectMapper.writeValueAsString(pair);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 310);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        DoubleMesage RespMessage = objectMapper.readValue(mess, DoubleMesage.class);
        double result = RespMessage.getaDouble();
//      处理结果
        return result;
    }

    @Override
    public StudentInfo[] GetAllInfo() throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            IDReqMessage message = null;
            String jsonData = objectMapper.writeValueAsString(message);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 311);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //接收服务器响应
        String receivedJsonData = rwTool.ClientReadStream(inputStream);

        String mess = receivedJsonData.toString();

//      创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();

//      将 JSON 数据转换为对象
        StudentInfoRespMessage RespMessage = objectMapper.readValue(mess, StudentInfoRespMessage.class);
        StudentInfo[] result = RespMessage.getStudentInfos();
//      处理结果
        return result;
    }

    @Override
    public boolean ModifyInfo(StudentInfo info) throws IOException {
        try {
            // 创建 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 LoginMessage 对象转换为 JSON 字符串
            String jsonData = objectMapper.writeValueAsString(info);
            System.out.println(jsonData);

            rwTool.ClientSendOutStream(outputStream, jsonData, 312);

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
