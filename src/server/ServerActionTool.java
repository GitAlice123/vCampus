package view.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.DAO.*;

import view.Hospital.Department;
import view.Hospital.Register;
import view.Library.*;

import view.Library.BookHold;
import view.Login.User;
import view.SchoolRolls.Grade;
import view.SchoolRolls.StudentInfo;
import view.connect.Pair;
import view.message.*;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerActionTool {
    public ServerActionTool() {
    }

    private ServerRWTool rwTool = new ServerRWTool();

    private static final String KEY = "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";
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private static final String HOST = "localhost";
    private static final int PORT = 8888;

    public List<String> getOnlineList() {
        return onlineList;
    }

    public void setOnlineList(List<String> onlineList) {
        this.onlineList = onlineList;
    }

    private List<String> onlineList;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //按ID查询StudentInfo并返回
    public void Action300(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        String ID=null;
        try {
            IDReqMessage idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
            ID=idReqMessage.getID();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 300");
        StudentInfoDao studentInfoDao=new StudentInfoDao();
        StudentInfo studentInfo=studentInfoDao.findStudentInfoById(ID);
        //StudentInfoRespMessage respMessage=new StudentInfoRespMessage();
        //respMessage.setStudentInfo(studentInfo);


        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(studentInfo);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加学生信息StudentInfo
    public void Action301(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StudentInfo info = null;
        try {
            info = objectMapper.readValue(jsonData, StudentInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 301");

        StudentInfoDao studentInfoDao=new StudentInfoDao();
        boolean result=studentInfoDao.AddStudentInfo(info);
        BoolRespMessage respMessage=new BoolRespMessage(result);

        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //按ID删除StudentInfo
    public void Action302(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idreqmessage = null;
        try {
            idreqmessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 302");

        String ID=idreqmessage.getID();
        StudentInfoDao studentInfoDao=new StudentInfoDao();
        boolean result=studentInfoDao.DeleteStudentInfoById(ID);
        BoolRespMessage respMessage=new BoolRespMessage(result);


        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //按ID查成绩Grade
    public void Action303(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage = null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 303");

        GradeDao GradeDAO=new GradeDao();
        Grade[] result=GradeDAO.findGradesById(idReqMessage.getID());
        GradesRespMessage respMessage=new GradesRespMessage(result);


        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //按CourseID查Course
    public void Action304(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringPariMessage stringPariMessage = null;
        try {
            stringPariMessage = objectMapper.readValue(jsonData, StringPariMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 304");

        CourseDao courseDAO=new CourseDao();
        GradeDao gradeDao=new GradeDao();
        Course respMessage=courseDAO.findCourseByNum(stringPariMessage.getFirst());
        if(respMessage!= null) {
            double grade = gradeDao.findGradeByInfo(stringPariMessage.getSecond(), respMessage.getCourseID());
            respMessage.setGrades(grade);
        }
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Course转为String
    //按教师ID查教学班
    public void Action306(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        String ID = null;
        try {
            IDReqMessage idReqMessage = objectMapper.readValue(jsonData,IDReqMessage.class);
            ID=idReqMessage.getID();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 306");
        CourseClassDao courseClassDao=new CourseClassDao();
        CourseClass[] courses=courseClassDao.findClassByTeacherId(ID);
        CourseClassesRespMessage respMessage=new CourseClassesRespMessage(courses);
        //未完成，缺DAO
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //用ID查找课程Course
    public void Action307(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        String ID = null;
        try {
            IDReqMessage idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
            ID=idReqMessage.getID();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 307");
        GradeDao gradeDao=new GradeDao();
        CourseDao courseDao=new CourseDao();
        Grade[] grades=gradeDao.findGradesById(ID);//查成绩
        CoursesRespMessage respMessage=new CoursesRespMessage();
        if(grades!=null) {
            String[] courseIDs = new String[grades.length];//成绩对应的课程ID
            Course[] courses = new Course[grades.length];
            for (int i = 0; i < grades.length; i++) {
                courseIDs[i] = grades[i].getCourseID();
                courses[i] = courseDao.findCourseByNum(grades[i].getCourseID());
                courses[i].setGrades(grades[i].getGrade());
            }
            respMessage = new CoursesRespMessage(courses);
        }

        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action308(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        String ID = null;
        try {
            IDReqMessage idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
            ID=idReqMessage.getID();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 308");
        ClassNameListDao classNameListDao=new ClassNameListDao();
        StudentInfoDao studentInfoDao=new StudentInfoDao();
        String[] IDs=classNameListDao.findStudentIdByClassId(ID);
        StudentInfo[] studentInfos=null;
        if(IDs!=null) {
            studentInfos = new StudentInfo[IDs.length];
            for (int i = 0; i < IDs.length; i++) {
                studentInfos[i] = studentInfoDao.findStudentInfoById(IDs[i]);
            }
        }
        StudentInfoRespMessage respMessage=new StudentInfoRespMessage(studentInfos);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //boolean grade
    public void Action309(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Grade grade = null;
        try {
            grade = objectMapper.readValue(jsonData, Grade.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 309");
        GradeDao gradeDao=new GradeDao();
        boolean result=gradeDao.ModifyGrade(grade);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //double StudentID CourseID
    public void Action310(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringPariMessage pair = null;
        try {
            pair = objectMapper.readValue(jsonData, StringPariMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 310");
        String studentID=pair.getFirst();
        String courseID= pair.getSecond();;
        GradeDao gradeDao=new GradeDao();
        double result=gradeDao.findGradeByInfo(studentID,courseID);
        DoubleMesage respMessage=new DoubleMesage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action311(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage pair = null;
        try {
            pair = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 311");
        StudentInfoDao studentInfoDao=new StudentInfoDao();
        StudentInfo[] result=studentInfoDao.showAllStudentInfo();
        StudentInfoRespMessage respMessage=new StudentInfoRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action312(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StudentInfo info = null;
        try {
            info = objectMapper.readValue(jsonData, StudentInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 312");
        StudentInfoDao studentInfoDao=new StudentInfoDao();
        boolean result=studentInfoDao.ModifyStudentInfo(info);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action313(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage pair = null;
        try {
            pair = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 313");
        UserDao userDao=new UserDao();
        User[] result=userDao.findAllStudents();
        UserMessage respMessage=new UserMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action400(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage ID = null;
        try {
            ID = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 400");
        CourseDao courseDao=new CourseDao();
        Course[] courses=courseDao.showAllCourse();
        for(int i=0;i<courses.length;i++){
            courses[i].setGrades(100);
        }
        CoursesRespMessage respMessage=new CoursesRespMessage(courses);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //按CourseID 查找Class
    public void Action401(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage = null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 401");
        String ID=idReqMessage.getID();
        CourseClassDao courseClassDao=new CourseClassDao();
        CourseClass[] classes=courseClassDao.findClassByCourseId(ID);
        CourseClassesRespMessage respMessage=new CourseClassesRespMessage(classes);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //按stuID寻找class
    public void Action402(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage = null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 402");
        ClassNameListDao classNameListDao=new ClassNameListDao();
        String stuID=idReqMessage.getID();
        CourseClass[] courseClasses=classNameListDao.findClassByStudentId(stuID);
        CourseClassesRespMessage respMessage=new CourseClassesRespMessage(courseClasses);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //403 学生选课
    public void Action403(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringPariMessage message=new StringPariMessage();
        try {
            message = objectMapper.readValue(jsonData,StringPariMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 403");
        ClassNameListDao classNameListDao=new ClassNameListDao();
        boolean result=classNameListDao.createClassStudentLink(message.getSecond(),message.getFirst());
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //404 学生退课
    public void Action404(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringPariMessage message=new StringPariMessage();
        try {
            message = objectMapper.readValue(jsonData,StringPariMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 404");
        ClassNameListDao classNameListDao=new ClassNameListDao();
        boolean result=classNameListDao.deleteClassStudentLink(message.getSecond(),message.getFirst());
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action405(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage message= new IDReqMessage();
        try {
            message = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 405");
        CourseClassDao courseClassDao=new CourseClassDao();
        String ID=message.getID();
        IntMessage respMessage=new IntMessage(courseClassDao.getClassNumByCourseId(ID));
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action406(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage ID = null;
        try {
            ID = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 406");
        CourseClassDao courseDao=new CourseClassDao();
        CourseClass[] classes=courseDao.showAllClass();
        CourseClassesRespMessage respMessage=new CourseClassesRespMessage(classes);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action407(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage message=null;
        try {
            message = objectMapper.readValue(jsonData,IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 407");
        String id=message.getID();
        CourseClassDao classDao=new CourseClassDao();
        ClassNameListDao classNameListDao=new ClassNameListDao();
        String[] IDs=classNameListDao.findStudentIdByClassId(id);
        if(IDs!=null){
            for(int i=0;i<IDs.length;i++)
                classNameListDao.deleteClassStudentLink(id,IDs[i]);
        }
        boolean result=classDao.deleteClassByClassId(message.getID());
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action408(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        CourseClass message=null;
        try {
            message = objectMapper.readValue(jsonData,CourseClass.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 408");
        CourseClassDao courseClassDao=new CourseClassDao();
        boolean result=courseClassDao.createClass(message);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action409(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        CourseClass message=null;
        try {
            message = objectMapper.readValue(jsonData,CourseClass.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 409");
        CourseClassDao courseClassDao=new CourseClassDao();
        boolean result=courseClassDao.ModifyClass(message);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Action410(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage message=null;
        try {
            message = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 410");
        UserDao userDao=new UserDao();
        User[] users=userDao.findAllTeachers();
        UserMessage respMessage=new UserMessage(users);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String userID;

    /**
     * 处理登录请求，并保留成功登录的用户ID用于唯一标识线程
     * @param jsonData
     * @param clientSocket
     */
    public String Action100(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 100");

        /*
            处理登录请求，看是否允许登录
         */
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(loginMessage.getUserId());
        boolean flag = false;
        if (user != null && user.getuPwd().equals(loginMessage.getPassword())
                && user.getuRole().equals(loginMessage.getRole())) {
            flag = true;
            /*
            保存用户ID
             */
            setUserID(user.getuId());
        }
        // 下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginMessage.getUserId();
    }

    public void Action101(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 101");
        UserDao userdao = new UserDao();
        view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
        boolean flag = false;
        // flag = true表示user已经在表单里了
        if (user != null) {
            flag = true;
        }
        // 下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(respMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action102(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        LoginMessage loginMessage = null;
        try {
            loginMessage = objectMapper.readValue(jsonData, LoginMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 102");

        Boolean flag = false;

        String userId = loginMessage.getUserId();
        String password = loginMessage.getPassword();
        String role = loginMessage.getRole();
        User user = new User();
        user.setuId(userId);
        user.setuPwd(password);
        user.setuRole(role);
        UserDao userdao = new UserDao();
        userdao.CreateUser(user);// 创建新用户
        flag = true;

        // 下面将response信息返回客户端
        BoolRespMessage respMessage = new BoolRespMessage(flag);
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action200(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        System.out.println(jsonData);
        // 将 JSON 数据还原为对象
        UniqueMessage noDataReqMessage = null;
        try {
            noDataReqMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 200");
        /* 以下是和数据库交互部分,最后返回一个BookListRespMessage */
        // TODO:服务器端返回馆藏书籍列表
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

        // UserDao userdao = new UserDao();
        // view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
        // boolean flag = false;
        // // flag = true表示user已经在表单里了
        // if (user != null) {
        // flag = true;
        // }
        // 下面将response信息返回客户端
        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action201(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage registerReqMessage = null;
        try {
            registerReqMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 201");
        /* 以下是和数据库交互部分,最后返回一个BookHoldListRespMessage,表示用户借了哪些书 */
        // TODO:服务器端返回用户借书列表
        BookHoldDao bookDao = new BookHoldDao();
        BookHold[] bookHolds = bookDao.findBookHoldsById(registerReqMessage.getUserId());
        BookHoldListRespMessage bookHoldListRespMessage = new BookHoldListRespMessage(bookHolds);
        // UserDao userdao = new UserDao();
        // view.Login.User user = userdao.findUserByuId(registerReqMessage.getUserId());
        // boolean flag = false;
        // // flag = true表示user已经在表单里了
        // if (user != null) {
        // flag = true;
        // }
        // 下面将response信息返回客户端
        try {
            String outputData = objectMapper.writeValueAsString(bookHoldListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action202(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        SearchBookNameMessage searchBookNameMessage = null;
        try {
            searchBookNameMessage = objectMapper.readValue(jsonData, SearchBookNameMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 202");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findBookByBookName(searchBookNameMessage.getBookName());

        // 下面将response信息返回客户端

        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action203(String jsonData, Socket clientSocket) {
        // 删除图书,成功返回true
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 203");
        Boolean flag = false;
        /* 以下是和数据库交互部分删除图书 */
        BookDao bookDao = new BookDao();
        flag = bookDao.DeleteBook(bookISBNMessage.getBookISBN());

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action204(String jsonData, Socket clientSocket) {
        // 增加图书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Book bookMessage = null;
        try {
            bookMessage = objectMapper.readValue(jsonData, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 204");
        Boolean flag = false;
        /* 以下是和数据库交互部分增加图书 */

        BookDao bookDao = new BookDao();
        flag = bookDao.addBook(bookMessage);

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action205(String jsonData, Socket clientSocket) {
        // 借阅书籍
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 205");
        Boolean flag_1 = false;
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分借阅图书 */

        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        // TODO:借书操作，需要传入用户id
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Borrow(bookOperationRecord);

        Boolean flag = false;
        if (flag_3) {
            BookDao bookDao = new BookDao();
            flag_1 = bookDao.Borrow(bookOperationRecord.getISBN());
            flag = flag_1 && flag_2 && flag_3;
        } else {
            flag = false;
        }

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action206(String jsonData, Socket clientSocket) {
        // 还书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 206");
        Boolean flag_1 = false;
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分借阅图书 */

        BookDao bookDao = new BookDao();
        flag_1 = bookDao.Return(bookOperationRecord.getISBN());

        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        // TODO:借书操作，需要传入用户id
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Return(bookOperationRecord);

        Boolean flag = flag_1 && flag_2 && flag_3;
        // TODO:还书还要调用其他函数

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action207(String jsonData, Socket clientSocket) {
        // 修改图书
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Book bookMessage = null;
        try {
            bookMessage = objectMapper.readValue(jsonData, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 207");
        Boolean flag = false;
        /* 以下是和数据库交互部分更改图书 */
        BookDao bookDao = new BookDao();
        flag = bookDao.ModifyBook(bookMessage);

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用ISBN找书
     * @param jsonData
     * @param clientSocket
     */
    public void Action208(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookISBNMessage bookISBNMessage = null;
        try {
            bookISBNMessage = objectMapper.readValue(jsonData, BookISBNMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 208");
        Book bookMessage;
        /* 以下是和数据库交互部分获取图书 */
        BookDao bookDao = new BookDao();
        bookMessage = bookDao.findBookByISBN(bookISBNMessage.getBookISBN());

        // 下面将response信息返回客户端
        try {
            String outputData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取回所有操作记录
     * @param jsonData
     * @param clientSocket
     */
    public void Action209(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 209");

        /* 以下是和数据库交互部分获取所有操作记录 */
        BookOperationRecord[] bookOperationRecords;
        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        bookOperationRecords = bookOperationRecordDao.findAllBookOperationRecord();

        BookOPRListRespMessage bookMessage = new BookOPRListRespMessage(bookOperationRecords);

        // 下面将response信息返回客户端
        try {
            String outputData = objectMapper.writeValueAsString(bookMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Action210(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 210");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

        // 下面将response信息返回客户端

        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO:忘了是啥了。。
     * @param jsonData
     * @param clientSocket
     */
    public void Action211(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 211");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

        // 下面将response信息返回客户端

        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回所有图书，包括图书名字、编号等
     * @param jsonData
     * @param clientSocket
     */
    public void Action212(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 212");
        BookDao bookDao = new BookDao();
        Book[] bookList = bookDao.findAllBooks();

        // 下面将response信息返回客户端

        BookListRespMessage bookListRespMessage = new BookListRespMessage(bookList);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 续借图书
     * @param jsonData
     * @param clientSocket
     */
    public void Action213(String jsonData, Socket clientSocket) {
        // 续借书籍
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        BookOperationRecord bookOperationRecord = null;
        try {
            bookOperationRecord = objectMapper.readValue(jsonData, BookOperationRecord.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 213");
        Boolean flag_2 = false;
        Boolean flag_3 = false;

        /* 以下是和数据库交互部分续借图书 */

        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        flag_2 = bookOperationRecordDao.AddBookOperationRecord(bookOperationRecord);

        BookHoldDao bookHoldDao = new BookHoldDao();
        flag_3 = bookHoldDao.Renew(bookOperationRecord);

        Boolean flag = flag_2 && flag_3;

        // 下面将response信息返回客户端
        BoolRespMessage boolRespMessage = new BoolRespMessage(flag);
        try {
            String outputData = objectMapper.writeValueAsString(boolRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 展示馆藏图书数据信息
     * @param jsonData
     * @param clientSocket
     */
    public void Action214(String jsonData, Socket clientSocket) {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 214");
        BookDao bookDao = new BookDao();
        int[] report = bookDao.getReport();

        // 下面将response信息返回客户端

        LibraryReportRespMessage bookListRespMessage = new LibraryReportRespMessage(report);
        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到所有图书操作记录
     * @param jsonData
     * @param clientSocket
     */
    public void Action215(String jsonData, Socket clientSocket) {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        RegisterReqMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, RegisterReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 215");
        BookOperationRecordDao bookOperationRecordDao = new BookOperationRecordDao();
        BookOperationRecord[] report = bookOperationRecordDao.findBookOperationRecordById(uniqueMessage.getUserId());

        // 下面将response信息返回客户端

        BookAdminSearchRespMessage bookListRespMessage = new BookAdminSearchRespMessage(report);

        try {
            String outputData = objectMapper.writeValueAsString(bookListRespMessage);
            System.out.println(outputData);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得所有department
     * @param jsonData
     * @param clientSocket
     */
    public void Action500(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage=null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData,UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 500");

        DepartmentDao departmentDao=new DepartmentDao();
        Department[] result=departmentDao.showAllDep();
        DepartmentsMessage respMessage=new DepartmentsMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //501 根据ID找到所有挂号记录(包括已缴费)
    public void Action501(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage message=null;
        try {
            message = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 501");
        String ID=message.getID();
        RegisterPaymentDao registerPaymentDao=new RegisterPaymentDao();
        Register[] payments=registerPaymentDao.findRegisterByUserID(ID);
        RegisterMessage respMessage=new RegisterMessage(payments);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //502 根据ID找到所有未缴费挂号
    public void Action502(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage message=null;
        try {
            message = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 502");
        String ID=message.getID();
        RegisterPaymentDao registerPaymentDao=new RegisterPaymentDao();
        Register[] payments=registerPaymentDao.findPaymentByUserID(ID);
        if(payments==null){
            System.out.println("空空空为什么为什么");
        }else{
            System.out.println("长度为："+payments.length);
        }

        RegisterMessage respMessage=new RegisterMessage(payments);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //503 根据ID返回未支付挂号
    public void Action503(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Register register=null;
        try {
            register = objectMapper.readValue(jsonData, Register.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 503");
        RegisterPaymentDao registerPaymentDao=new RegisterPaymentDao();
        boolean result=registerPaymentDao.createRegisterInfo(register);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 支付所选择挂号
     * @param jsonData
     * @param clientSocket
     */
    public void Action504(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage=null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData,UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 504");

        String rID=uniqueMessage.getUniMessage();
        RegisterPaymentDao registerPaymentDao=new RegisterPaymentDao();
        boolean result=registerPaymentDao.payRegisterById(rID);

        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 支付所选择挂号
     * @param jsonData
     * @param clientSocket
     */
    public void Action505(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        StringPariMessage registerMessage=null;
        try {
            registerMessage = objectMapper.readValue(jsonData, StringPariMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 505");

        String type=registerMessage.getFirst();
        String Slevel=registerMessage.getSecond();

//        boolean level=Boolean.parseBoolean(Slevel);
        DepartmentDao departmentDao=new DepartmentDao();
//        String lev=level?"专家":"普通";

        Department[] result = departmentDao.findDepartmentByInfo(type, Slevel);
        if (result == null) {

            System.out.println("dep的result为空++++++");
        }else {
            System.out.println(result.length);
        }

        DepartmentsMessage respMessage=new DepartmentsMessage(result);

        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 根据科室ID查找科室
     * @param jsonData
     * @param clientSocket
     */
    public void Action506(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage=null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 506");

        String ID = idReqMessage.getID();
        DepartmentDao departmentDao = new DepartmentDao();
        Department result = departmentDao.findDepartmentById(ID);
        DepartmentsMessage respMessage = new DepartmentsMessage(result);

        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回所有挂号信息
     * @param jsonData
     * @param clientSocket
     */
    public void Action507(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage=null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 507");
        RegisterPaymentDao registerPaymentDao=new RegisterPaymentDao();
        Register[] registers=registerPaymentDao.showAllRegister();
        RegisterMessage respMessage=new RegisterMessage(registers);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据信息新建科室
     * @param jsonData
     * @param clientSocket
     */
    public void Action508(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        Department department=null;
        try {
            department = objectMapper.readValue(jsonData, Department.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 508");
        DepartmentDao departmentDao=new DepartmentDao();
        boolean result=departmentDao.createDepartment(department);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据科室ID删除科室
     * @param jsonData
     * @param clientSocket
     */
    public void Action509(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        IDReqMessage idReqMessage=null;
        try {
            idReqMessage = objectMapper.readValue(jsonData, IDReqMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 509");
        String ID=idReqMessage.getID();
        DepartmentDao departmentDao=new DepartmentDao();
        boolean result=departmentDao.deleteDepartment(ID);
        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户需要在前端读取公告
     *
     * @param jsonData
     * @param clientSocket
     */
    public void Action800(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 800");

        noticeDao noticeDao=new noticeDao();
        String result=noticeDao.getNotice();

        UniqueMessage respMessage=new UniqueMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 管理员发布公告
     *
     * @param jsonData
     * @param clientSocket
     */
    public void Action801(String jsonData, Socket clientSocket) {
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 801");

        noticeDao noticeDao=new noticeDao();
        String text=uniqueMessage.getUniMessage();
        boolean result=noticeDao.editNotice(text);


        BoolRespMessage respMessage=new BoolRespMessage(result);
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 调用语言模型API
     * @param jsonData
     * @param clientSocket
     * @throws IOException
     */
    public void Action2000(String jsonData, Socket clientSocket) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        ChatQuesMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, ChatQuesMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 400");

        // 将服务器作为中转服务器和GPT连接
        String content = uniqueMessage.getqueString();
        String accessToken = getAccessToken();

        String url = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions" +
                "?access_token=" + accessToken;

        ChatRequest request = new ChatRequest();
        ChatMessage message = new ChatMessage();
        message.setRole("user");
        message.setContent(content);
        request.getMessages().add(message);

        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(request);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestJson.getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        String response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Reading the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            response = reader.readLine();
            reader.close();

            System.out.println(response);
        } else {
            // Error handling
            System.err.println("Failed to make chat completions. Response code: " + responseCode);
        }

        ChatInnerResp chatInnerResp = new ChatInnerResp();
        chatInnerResp = objectMapper.readValue(response, ChatInnerResp.class);
        // 下面将response信息返回客户端
        String answer = chatInnerResp.getResult();
        GPTAnsRepMessage gptAnsRepMessage = new GPTAnsRepMessage(answer);

        try {
            String outputData = objectMapper.writeValueAsString(gptAnsRepMessage);
            System.out.println(outputData);
            OutputStream outputStream2 = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream2, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 和其他客户端发消息的chat
     * @param jsonData
     * @param clientSocket
     * @throws IOException
     */
    public ChatWithUserMessage Action2001(String jsonData, Socket clientSocket) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        ChatWithUserMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, ChatWithUserMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 2001");

        return uniqueMessage;
    }


    /**
     * 功能函数 返回语言模型的API AccessToken
     * @return 返回语言模型的API AccessToken
     * @throws IOException
     */
    private static String getAccessToken() throws IOException {
        String apiKey = "lNiXykCWosvV8mWOt1VGAFFQ";
        String secretKey = "28VenBQWggxqSGhHlYSn4dCOCx8NpILm";
        String url = "https://aip.baidubce.com/oauth/2.0/token?" +
                "grant_type=client_credentials&" +
                "client_id=" + apiKey + "&" +
                "client_secret=" + secretKey;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write("".getBytes());
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Reading the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();

            ObjectMapper mapper = new ObjectMapper();
            TokenResponse tokenResponse = mapper.readValue(response, TokenResponse.class);
            return tokenResponse.getAccess_token();
        } else {
            // Error handling
            throw new IOException("Failed to obtain access token. Response code: " + responseCode);
        }
    }

    /**
     * 服务器在得到客户端A的消息后，将它转发给B
     * @param answer
     * @param clientSocket
     */
    public void Action0(String fromID,String answer,Socket clientSocket){
        ChatMessage gptAnsRepMessage = new ChatMessage(fromID,answer);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String outputData = objectMapper.writeValueAsString(gptAnsRepMessage);
            System.out.println(outputData);
            OutputStream outputStream2 = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream2, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到所有在线人员列表
     * @param jsonData
     * @param clientSocket
     */
    public void Action2002(String jsonData, Socket clientSocket){
        // 创建 ObjectMapper 对象
        ObjectMapper objectMapper = new ObjectMapper();
        jsonData = jsonData.replaceAll("^\\[|]$", "");
        // 将 JSON 数据还原为对象
        UniqueMessage uniqueMessage = null;
        try {
            uniqueMessage = objectMapper.readValue(jsonData, UniqueMessage.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Into object 2002");

        /*
        以下获得所有在线人员列表
         */

        OnlineListRespMessage respMessage=new OnlineListRespMessage(getOnlineList());
        //下面将response信息返回客户端
        try {
            // 将 LoginMessage 对象转换为 JSON 字符串
            String outputData = objectMapper.writeValueAsString(respMessage);
            OutputStream outputStream = clientSocket.getOutputStream();
            rwTool.ServerSendOutStream(outputStream, outputData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
