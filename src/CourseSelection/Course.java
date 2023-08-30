package view.CourseSelection;

public class Course {
    // 定义一个内部枚举类型表示课程类型
    public enum CourseType {
        必修, 限选, 选修
    }

    private String CourseNum;     // 课程代码
    private String CourseName;    // 课程名称
    private CourseType CourseType; // 课程类型
    private double CourseTime;    // 课程学时
    private double Credit;        // 学分
    private double Grades;        // 成绩

    // 构造器
    public Course(String courseNum, String courseName, CourseType courseType,
                  double courseTime, double credit, double grades) {
        this.CourseNum = courseNum;
        this.CourseName = courseName;
        this.CourseType = courseType;
        this.CourseTime = courseTime;
        this.Credit = credit;
        setGrades(grades); // 使用setter方法来确保值的范围
    }

    public Course(){}//空构造

    // getters 和 setters
    public String getCourseNum() {
        return CourseNum;
    }

    public void setCourseNum(String courseNum) {
        this.CourseNum = courseNum;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        if (courseName != null && courseName.length() <= 40) {
            this.CourseName = courseName;
        } else {
            throw new IllegalArgumentException("Course name too long!");
        }
    }

    public CourseType getCourseType() {
        return CourseType;
    }

    public void setCourseType(CourseType courseType) {
        this.CourseType = courseType;
    }

    public double getCourseTime() {
        return CourseTime;
    }

    public void setCourseTime(double courseTime) {
        if (courseTime >= 0) {
            this.CourseTime = courseTime;
        } else {
            throw new IllegalArgumentException("Course time must be >= 0");
        }
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        if (credit >= 0) {
            this.Credit = credit;
        } else {
            throw new IllegalArgumentException("Credit must be >= 0");
        }
    }

    public double getGrades() {
        return Grades;
    }

    public void setGrades(double grades) {
        if (grades >= 0 && grades <= 100) {
            this.Grades = grades;
        } else {
            throw new IllegalArgumentException("Grades must be in [0, 100]");
        }
    }
}
