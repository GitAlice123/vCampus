package view.CourseSelection;

public class Course {//学生视角的课程

    // 定义一个内部枚举类型表示课程类型
    public enum CourseType {
        Compulsory, Optional, Limitative
    }

    private String CourseID;     // 课程代码
    private String CourseName;    // 课程号
    private CourseType CourseType; // 课程类型
    private double CourseTime;    // 课程学时
    private double Credit;        // 学分
    private double Grades;        // 成绩

    // 构造器
    public Course(String courseID, String courseName, CourseType courseType, double courseTime, double credit, double grades) {
        this.CourseID = courseID;
        this.CourseName = courseName;
        this.CourseType = courseType;
        this.CourseTime = courseTime;
        this.Credit = credit;
        setGrades(grades); // 使用setter方法来确保值的范围
    }

    public Course(String courseID, String courseName, String courseType, double courseTime, double credit) {
        CourseID = courseID;
        CourseName = courseName;
        setCourseType(courseType);
        CourseTime = courseTime;
        Credit = credit;
        Grades = -1;
    }

    public Course() {
    }//空构造

    // getters 和 setters
    public String getCourseID() {
        return CourseID;
    }

    public void setCourseID(String courseID) {
        this.CourseID = courseID;
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

    public void setCourseType(String courseType) {
        switch (courseType) {
            case "Compulsory" -> this.CourseType = CourseType.Compulsory;
            case "Optional" -> this.CourseType = CourseType.Optional;
            case "Limitative" -> this.CourseType = CourseType.Limitative;
        }
    }

    public String getCourseType(String s) {
        switch (this.CourseType) {
            case Compulsory:
                return "Compulsory";
            case Optional:
                return "Optional";
            case Limitative:
                return "Limitative";
        }
        return null;
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