package view.DAO;

import view.SchoolRolls.StudentInfo;

import java.sql.*;
import java.text.SimpleDateFormat;

public class StudentInfoDao {

    public StudentInfo findStudentInfoById(String uId) {
        /*
            通过一卡通号查询学籍信息
            传入参数为一卡通号uId
            返回值为该uId对应的一个 StudentInfo类的学籍信息数据
         */

        String sqlString = "select * from tblStudentInfo where uId = '" + uId + "'";
        StudentInfo studentInfo = new StudentInfo();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if (!res.next()) {
                return null;
            }//如果在数据库找不到该学生信息，则返回null
            res.beforeFirst();

            res.next();

            studentInfo.setCardID(res.getString(1));
            studentInfo.setID(res.getString(2));
            studentInfo.setName(res.getString(3));
            studentInfo.setSex(res.getString(4));
            studentInfo.setBirth(res.getDate(5));
            studentInfo.setGrade(res.getInt(6));
            studentInfo.setCollege(res.getString(7));

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentInfo;
    }

    public boolean AddStudentInfo(StudentInfo stuInfo) {
        /*
            增加学生学籍信息
            传入参数为想要添加的学生学籍信息
         */

        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

        String sqlString = "insert into tblStudentInfo values('" + stuInfo.getCardID() + "','" + stuInfo.getID() + "','" + stuInfo.getName()
                + "','" + stuInfo.getSex() + "','" + ft.format(stuInfo.getBirth()) + "'," + stuInfo.getGrade() + ",'" + stuInfo.getCollege() + "')";


        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sta.executeUpdate(sqlString);

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    public Boolean DeleteStudentInfoById(String uId) {
        /*
            根据一卡通号删除学生学籍信息
            传入参数为一卡通号uId
         */
        String sqlString = "delete from tblStudentInfo where uId ='" + uId + "'";
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString); //返回删除数据条数
            if (count == 0) return false;

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public StudentInfo[] showAllStudentInfo() {
        /*
            查询所以学生学籍信息
         */


        return null;
    }
}
