package view.DAO;

import java.sql.*;

/**
 * 功能选择界面公告栏内容读取修改DAO
 * @author SunYanlin
 */
public class noticeDao {
    /**
     * 读取数据库中的公告内容
     * @return 字符串类型的公告内容
     */
    public String getNotice() {
        String ret = null;
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblNotice where nId = 0");
            res.next();
            ret = res.getString("Notice");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }


    /**
     * 将公告内容修改为传入的字符串
     * @param text 公告内容
     * @return 是否成功修改
     */
    public boolean editNotice(String text) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate("update tblNotice set Notice = '" + text + "' where nId = 0");
            con.close();//关闭数据库连接
            if (count == 0) return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
