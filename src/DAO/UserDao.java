package view.DAO;

import view.Login.User;

import java.sql.*;

/**
 * 用户管理DAO
 *
 * @author shuangmu555
 */
public class UserDao {

    /**
     * 通过用户ID查找用户类
     *
     * @param uId 用户ID
     * @return 对应的User类，否则返回null
     */
    public User findUserByuId(String uId) {

        String sqlString = "select * from tblUser where uId = '" + uId + "'";
        User user = new User();

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
            }
            res.beforeFirst();

            res.next();

            user.setuId(res.getString(1));
            user.setuPwd(res.getString(2));
            user.setuRole(res.getString(3));

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 传入用户信息，创建用户
     * @param user 创建用户的信息
     * @return 是否创建成功
     */
    public boolean CreateUser(User user) {

        String sqlString = "insert into tblUser(uId,uPwd,uRole) values('" + user.getuId() + "','" + user.getuPwd() + "','" + user.getuRole() + "')";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int num = sta.executeUpdate(sqlString);

            con.close();//关闭数据库连接
            if (num == 0) return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 查找全部老师，用于下拉框
     * @return 全部老师的User[]
     */
    public User[] findAllTeachers(){
        String sqlString = "select * from tblUser where uRole = 'TC'";
        User[] allTeachers = new User[10];

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

            int count = 0;
            while (res.next()) {
                count++;
            }//统计一共查找到多少条数据
            res.beforeFirst();

            if (count == 0) {
                return null;
            }//若未找教师账户，则返回null

            allTeachers = new User[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                allTeachers[index] = new User(res.getString(1), res.getString(2), res.getString(3));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTeachers;
    }

    public User[] findAllStudents(){
        String sqlString = "select * from tblUser where uRole = 'ST'";
        User[] allStudents = new User[10];

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

            int count = 0;
            while (res.next()) {
                count++;
            }//统计一共查找到多少条数据
            res.beforeFirst();

            if (count == 0) {
                return null;
            }//若未找教师账户，则返回null

            allStudents = new User[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                allStudents[index] = new User(res.getString(1), res.getString(2), res.getString(3));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStudents;
    }

}