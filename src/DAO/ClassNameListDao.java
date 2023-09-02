package view.DAO;

import java.sql.*;

public class ClassNameListDao {
    public String[] findStudentIdByClassId(String ClassId) {
        String[] namelist = new String[1];
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClassNameList where classId = '" + ClassId + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            namelist = new String[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                namelist[index] = new String(res.getString(2));
                index++;
            }

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return namelist;
    }

    public String[] findClassIdByStudentId(String sId) {
        String[] classlist = new String[1];
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClassNameList where uId = '" + sId + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            classlist = new String[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                classlist[index] = new String(res.getString(1));
                index++;
            }

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classlist;
    }

}
