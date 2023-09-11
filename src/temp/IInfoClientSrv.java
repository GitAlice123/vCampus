package view.temp;

import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;
import view.SchoolRolls.Grade;
import view.SchoolRolls.StudentInfo;
import view.DAO.CourseDao;
import view.DAO.GradeDao;
import view.DAO.StudentInfoDao;

public class IInfoClientSrv {
    private StudentInfoDao StDao=new StudentInfoDao();
    private GradeDao GraDao=new GradeDao();

    private CourseDao CouDao=new CourseDao();
    //在数据库中寻找StudentInfo
    public StudentInfo SearchStuInfoByID(String ID){
        return StDao.findStudentInfoById(ID);
    }

    //在数据库中增加StudentInfo
    public boolean AddStuInfo(StudentInfo Info){
        return StDao.AddStudentInfo(Info);
    }

    //在数据库中删除StudentInfo
    public boolean DeleteStuInfo(String ID){
        return StDao.DeleteStudentInfoById(ID);
    }

    //按学号查找该学生的所有成绩
    public Grade[] SearchGradeByID(String ID){
        return GraDao.findGradesById(ID);
    }

    public Course SearchCourseByCourseID(String ID){return CouDao.findCourseByNum(ID);}

    //将成绩转化为二维数组
    public String[][] CoursetoString (Course[] courses){
        String[][] scourse=new String [courses.length][6];
        for(int i=0;i<courses.length;i++){
            scourse[i][0]=courses[i].getCourseID();
            scourse[i][1]=courses[i].getCourseName();
            scourse[i][2]=courses[i].getCourseType().name();
            scourse[i][3]=Double.toString(courses[i].getCourseTime());
            scourse[i][4]=Double.toString(courses[i].getCredit());
            scourse[i][5]=Double.toString(courses[i].getGrades());
        }
        return scourse;
    }
    //按照教师ID搜索课程班级
    public CourseClass[] SearchCourseClass(String ID) {
        //未完成
        String[] IDs = {"123", "321"};
        CourseClass[] classes = new CourseClass[2];
        classes[0] = new CourseClass("123", "123", "123", "Room101", 40, 32, "10:00AM-12:00PM", IDs);
        classes[1] = classes[0];
        return classes;
    }

    public Course[] SearchCourseByID(String ID){
        Grade[] grades=this.SearchGradeByID(ID);//查成绩
        String[] courseIDs=new String[grades.length];//成绩对应的课程ID
        Course[] courses=new Course[grades.length];
        for(int i=0;i<grades.length;i++){
            courseIDs[i]=grades[i].getCourseID();
            courses[i]=this.SearchCourseByCourseID(grades[i].getCourseID());
        }
        return courses;
    }


}
