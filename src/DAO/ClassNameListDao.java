package view.DAO;

import view.CourseSelection.CourseClass;

import java.sql.*;

/**
 * 选课模块 班级名单DAO
 */
public class ClassNameListDao {
    /**
     * 查找一个班级里的所有学生
     * @param ClassId 班级ID
     * @return 所有学生的ID　String[]
     */
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

    /**
     * 查找一个学生选择的所有班级ID
     * @param sId 学生一卡通号
     * @return 所有的班级ID　String[]
     */
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

    /**
     * 查找一个学生选择的所有班级
     * @param sId 学生一卡通号
     * @return 所有的班级　CourseClass[]
     */
    public CourseClass[] findClassByStudentId(String sId) {
        CourseClass[] classlist = new CourseClass[1];
        CourseClassDao dao = new CourseClassDao();
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

            classlist = new CourseClass[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                classlist[index] = dao.findClassByClassId(res.getString(1));
                index++;
            }

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classlist;
    }

    /**
     * 增加选课链接
     * @param classId 班级编号
     * @param uId　学生编号
     * @return 是否创建成功
     */
    public boolean createClassStudentLink(String classId, String uId) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblClassNameList where classId = '" + classId + "' and uId = '" + uId + "'");
            if (test.next()) {
                con.close();
                return false;
            }
            sta.executeUpdate("insert into tblClassNameList(classId,uId) values('" + classId + "','" + uId + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 删除选课链接
     * @param classId 班级编号
     * @param uId 学生编号
     * @return 是否创建成功
     */
    public boolean deleteClassStudentLink(String classId, String uId) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            int res = sta.executeUpdate("delete from tblClassNameList where classId = '" + classId + "' and uId = '" + uId + "'");

            con.close();//关闭数据库连接

            if (res == 0) return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
