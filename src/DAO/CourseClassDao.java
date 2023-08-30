package view.DAO;

import view.CourseSelection.CourseClass;
import view.SchoolRolls.Grade;

import java.sql.*;
import java.util.List;

public class CourseClassDao {


    public CourseClass[] findClassByTeacherId(String TeacherId){
        CourseClass[] classes= new CourseClass[1];
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass where classTeacherId = '" + TeacherId + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if(count == 0){
                return null;
            }
            classes = new CourseClass[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                classes[index] = new CourseClass(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8),
                        ClassNameListDao.findStudentIdByClassId(res.getString(1))
                        );
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

}
