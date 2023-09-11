package view.connect;

import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;

import java.io.IOException;

public interface CourseSelectClientAPI {
    //StudentUI
    Course[] GetAllCourse()throws IOException;
    CourseClass[] SearchClassByCourseID(String couID)throws IOException;
    CourseClass[] SearchSelectClassByID(String stuID)throws IOException;
    boolean AddClassByinfo(String stuID,String classID)throws IOException;
    boolean QuitClassByinfo(String stuID,String classID)throws IOException;
    int GetClassNumByCourseID(String courseID)throws IOException;
    CourseClass[] GetAllClass()throws IOException;
    boolean DeleteClassByClassid(String ID)throws IOException;
    boolean AddClass(CourseClass courseClass)throws  IOException;
}
