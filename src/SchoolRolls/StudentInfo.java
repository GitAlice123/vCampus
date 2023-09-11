package view.SchoolRolls;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentInfo {
    private String CardID;
    private String ID;
    private String Sex;
    private String Name;
    private Date Birth;
    private int Grade;
    private String College;
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

    // 设置校园一卡通号
    public void setCardID(String cardID) {
        this.CardID = cardID;
    }

    // 获取学号
    public String getID() {
        return ID;
    }

    // 设置学号
    public void setID(String ID) {
        this.ID = ID;
    }

    // 获取性别
    public String getSex() {
        return Sex;
    }

    // 设置性别
    public void setSex(String sex) {
        this.Sex = sex;
    }

    // 获取姓名
    public String getName() {
        return Name;
    }

    // 设置姓名
    public void setName(String name) {
        this.Name = name;
    }

    // 获取出生日期
    public Date getBirth() {
        return Birth;
    }

    // 设置出生日期
    public void setBirth(Date birth) {
        this.Birth = birth;
    }

    // 获取入学年份
    public int getGrade() {
        return Grade;
    }

    // 设置入学年份
    public void setGrade(int grade) {
        this.Grade = grade;
    }

    // 获取学院
    public String getCollege() {
        return College;
    }

    // 设置学院
    public void setCollege(String college) {
        this.College = college;
    }
}

