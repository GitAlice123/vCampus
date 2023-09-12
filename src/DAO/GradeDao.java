package view.DAO;

import view.SchoolRolls.Grade;

import java.sql.*;

/**
 * 学生成绩DAO
 */
public class GradeDao {
    /**
     * 通过一卡通号查找学生的全部成绩
     * @param uId 一卡通号
     * @return 学生的全部成绩Grade[]
     */
    public Grade[] findGradesById(String uId) {
        String sqlString = "select * from tblGrade where uId = '" + uId + "'";
        Grade[] allGrades = new Grade[10];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            allGrades = new Grade[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                allGrades[index] = new Grade(res.getString(1), res.getString(2), res.getDouble(3));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allGrades;
    }

    /**
     * 增加成绩记录
     * @param gradeInfo 学生、课程、成绩的对应记录
     * @return 是否添加成功
     */
    public boolean AddGradeInfo(Grade gradeInfo) {
        /*
        增加一条成绩信息
         */
        String sqlString = "insert into tblGrade values('" + gradeInfo.getCardID() + "','" + gradeInfo.getCourseID() + "','" + gradeInfo.getGrade() + "')";

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
     * 修改成绩
     * @param grade 要修改的内容
     * @return 是否修改成功
     */
    public boolean ModifyGrade(Grade grade) {
        /*
            通过传入参数garde修改一条成绩信息
         */

        String sqlString = "update tblGrade set courseGrade = " + grade.getGrade()
                + " where uId = '" + grade.getCardID() + "' and courseId = '" + grade.getCourseID() + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString);
            if (count == 0) return false;

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 通过一卡通号和课程编号查找成绩
     * @param uId 一卡通号
     * @param courseId 课程编号
     * @return 双精度成绩
     */
    public double findGradeByInfo(String uId, String courseId) {
        double ans = -1;
        String sqlString = "select * from tblGrade where uId = '" + uId + "' and courseId = '" + courseId + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if (!res.next()) {
                return ans;
            }
            res.beforeFirst();
            res.next();

            ans = res.getDouble(3);

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
