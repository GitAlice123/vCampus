package view.message;

import view.SchoolRolls.StudentInfo;

import java.util.Date;

public class StudentInfoRespMessage {
    private String CardID;
    private String ID;
    private String Sex;
    private String Name;
    private Date Birth;
    private int Grade;
    private String College;
    private StudentInfo[] studentInfos;
    private StudentInfo studentInfo;

    public StudentInfoRespMessage(StudentInfo[] studentInfos) {
        this.studentInfos = studentInfos;
    }

    public StudentInfoRespMessage(String cardID, String ID, String sex, String name, Date birth, int grade, String college) {
        CardID = cardID;
        this.ID = ID;
        Sex = sex;
        Name = name;
        Birth = birth;
        Grade = grade;
        College = college;
    }

    public StudentInfoRespMessage() {
    }

    public StudentInfo[] getStudentInfos() {
        return studentInfos;
    }

    public String getCardID() {
        return CardID;
    }

    public void setCardID(String cardID) {
        CardID = cardID;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Date getBirth() {
        return Birth;
    }

    public void setBirth(Date birth) {
        Birth = birth;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }
}
