package view.DAO;

import view.SchoolRolls.StudentInfo;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 学生学籍DAO
 * @author shuangmu555
 */
public class StudentInfoDao {

    /**
     * 通过一卡通号查询学籍信息
     * @param uId 一卡通号
     * @return 该uId对应的一个 StudentInfo类的学籍信息数据
     */
    public StudentInfo findStudentInfoById(String uId) {
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

    /**
     * 增加学生学籍信息
     * @param stuInfo 想要添加的学生学籍信息
     * @return 是否成功
     */
    public boolean AddStudentInfo(StudentInfo stuInfo) {
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


    /**
     * 根据一卡通号删除学生学籍信息
     * @param uId 一卡通号
     * @return 是否成功删除
     */
    public Boolean DeleteStudentInfoById(String uId) {
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

    /**
     * 查询所以学生学籍信息
     * @return 所有学生的StudentInfo[]
     */
    public StudentInfo[] showAllStudentInfo() {

        String sqlString = "select * from tblStudentInfo order by uId";
        StudentInfo[] allInfo = new StudentInfo[10];

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

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;    //如果无学籍信息，则返回null
            }

            allInfo = new StudentInfo[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                allInfo[index] = new StudentInfo(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getDate(5),res.getInt(6),res.getString(7));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allInfo;

    }

}
