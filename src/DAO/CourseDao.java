package view.DAO;

import view.CourseSelection.Course;

import java.sql.*;

public class CourseDao {
    public Course findUserByuId(String uId) {

        String sqlString = "select * from tblCoures where uId = '" + uId + "'";
        Course course = new Course();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if(!res.next()){return null;}
            res.beforeFirst();
            res.next();



            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
