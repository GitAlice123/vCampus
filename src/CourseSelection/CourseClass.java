package view.CourseSelection;

public class CourseClass {
        private String classID;        // 课程班编号
        private String courseNum;      // 课程编号
        private String classTeacher;   // 任课教师ID
        private String classPlace;     // 上课地点
        private int classMax;          // 最大人数
        private int classTemp;         // 当前人数
        private String classTime;      // 上课时间
        private String[] classStudent; // 选本班级的学生ID

        public CourseClass(String classID, String courseNum, String classTeacher, String classPlace, int classMax, int classTemp, String classTime, String[] classStudent) {
            this.classID = classID;
            this.courseNum = courseNum;
            this.classTeacher = classTeacher;
            this.classPlace = classPlace;
            this.classMax = classMax;
            this.classTemp = classTemp;
            this.classTime = classTime;
            this.classStudent = classStudent;
        }

    public CourseClass() {
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }

    public String getClassPlace() {
        return classPlace;
    }

    public void setClassPlace(String classPlace) {
        this.classPlace = classPlace;
    }

    public int getClassMax() {
        return classMax;
    }

    public void setClassMax(int classMax) {
        this.classMax = classMax;
    }

    public int getClassTemp() {
        return classTemp;
    }

    public void setClassTemp(int classTemp) {
        this.classTemp = classTemp;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String[] getClassStudent() {
        return classStudent;
    }

    public void setClassStudent(String[] classStudent) {
        this.classStudent = classStudent;
    }
}
