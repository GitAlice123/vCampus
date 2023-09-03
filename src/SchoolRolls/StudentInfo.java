package view.SchoolRolls;

import java.util.Date;
public class StudentInfo {
    private String CardID;

    public StudentInfo(String cardID, String ID, String sex, String name, Date birth, int grade, String college) {
        CardID = cardID;
        this.ID = ID;
        Sex = sex;
        Name = name;
        Birth = birth;
        Grade = grade;
        College = college;
    }
    public StudentInfo() {
    }

    private String ID;
    private String Sex;
    private String Name;
    private Date Birth;
    private int Grade;
    private String College;

    // 构造函数
    public void Student(String CardID, String ID, String Sex, String Name, Date Birth, int grade, String College) {
        this.CardID = CardID;
        this.ID = ID;
        this.Sex = Sex;
        this.Name = Name;
        this.Birth = Birth;
        this.Grade = grade;
        this.College = College;
    }
// 获取校园一卡通号
    public String getCardID() {
        return CardID;
    }

    // 获取学号
    public String getID() {
        return ID;
    }

    // 获取性别
    public String getSex() {
        return Sex;
    }

    // 获取姓名
    public String getName() {
        return Name;
    }

    // 获取出生日期
    public Date getBirth() {
        return Birth;
    }

    // 获取入学年份
    public int getGrade() {
        return Grade;
    }

    // 获取学院
    public String getCollege() {
        return College;
    }

    // 设置校园一卡通号
    public void setCardID(String cardID) {
        this.CardID = cardID;
    }

    // 设置学号
    public void setID(String ID) {
        this.ID = ID;
    }

    // 设置性别
    public void setSex(String sex) {
        this.Sex = sex;
    }

    // 设置姓名
    public void setName(String name) {
        this.Name = name;
    }

    // 设置出生日期
    public void setBirth(Date birth) {
        this.Birth = birth;
    }

    // 设置入学年份
    public void setGrade(int grade) {
        this.Grade = grade;
    }

    // 设置学院
    public void setCollege(String college) {
        this.College = college;
    }

    // toString方法，用于将学生信息转换为字符串
    @Override
    public String toString() {
        return "Student{" +
                "campusCardNumber='" + CardID + '\'' +
                ", studentID='" + ID + '\'' +
                ", gender='" + Sex + '\'' +
                ", name='" + Name + '\'' +
                ", dateOfBirth=" + Birth +
                ", enrollmentYear=" + Grade +
                ", college='" + College + '\'' +
                '}';
    }
}

