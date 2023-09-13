package view.connect;

import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.Login.User;

import java.io.IOException;

public interface CourseSelectClientAPI {
    /**返回所有Course
     * @return 数据库中所有Course
     */
    Course[] GetAllCourse()throws IOException;
    CourseClass[] SearchClassByCourseID(String couID)throws IOException;
    CourseClass[] SearchSelectClassByID(String stuID)throws IOException;
    boolean AddClassByinfo(String stuID,String classID)throws IOException;
    boolean QuitClassByinfo(String stuID,String classID)throws IOException;
    int GetClassNumByCourseID(String courseID)throws IOException;
    CourseClass[] GetAllClass()throws IOException;
    boolean DeleteClassByClassid(String ID)throws IOException;
    boolean AddClass(CourseClass courseClass)throws  IOException;
    boolean ModifyClassByinfo(CourseClass courseClass)throws  IOException;

    User[] GetAllTeacher()throws IOException;
}
