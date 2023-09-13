package view.connect;

import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.SchoolRolls.Grade;
import view.SchoolRolls.StudentInfo;

import java.io.IOException;

public interface InfoClientAPI {
    //300
    StudentInfo SearchStuInfoByID(String ID) throws IOException;

    //301
    boolean AddStuInfo(StudentInfo Info) throws IOException;

    //302
    boolean DeleteStuInfoByID(String ID) throws IOException;

    //303
    Grade[] SearchGradeByID(String ID) throws IOException;

    //304
    Course SearchCourseByinfo(String CouID, String StuID) throws IOException;

    //306
    CourseClass[] SearchCourseClassByTeacherID(String TeacherID) throws IOException;

    //307
    Course[] SearchCourseByID(String ID) throws IOException;//按学号查课程

    //308
    StudentInfo[] SearchStudentByClassID(String classid) throws IOException;

    //309
    boolean ModifyGrade(Grade grade) throws IOException;

    //310
    double FindGradeByInfo(String StudentID, String courseID) throws IOException;

    StudentInfo[] GetAllInfo() throws IOException;

    boolean ModifyInfo(StudentInfo info) throws IOException;

}
