package view.DAO;

import view.CourseSelection.Course;
import view.CourseSelection.CourseClass;

import java.sql.*;

public class CourseClassDao {


    public CourseClass[] findClassByTeacherId(String TeacherId) {
        CourseClass[] classes = new CourseClass[1];
        ClassNameListDao dao = new ClassNameListDao();
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass where classTeacherId = '" + TeacherId + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }
            classes = new CourseClass[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                classes[index] = new CourseClass(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8),
                        dao.findStudentIdByClassId(res.getString(1))
                );
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public boolean createClass(CourseClass courseClass) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblClass where classId = '" + courseClass.getClassID() + "'");
            if (test.next()) {
                con.close();
                return false;
            }
            sta.executeUpdate("insert into tblClass(classId,courseId,classTeacher,classTeacherId,classPlace,classMax,classTemp,classTime) values('"
                    + courseClass.getClassID() + "','"
                    + courseClass.getCourseNum() + "','"
                    + "Null" + "','"
                    + courseClass.getClassTeacher() + "','"
                    + courseClass.getClassPlace() + "','"
                    + courseClass.getClassMax() + "','"
                    + courseClass.getClassTemp() + "','"
                    + courseClass.getClassTime() + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public int getClassNumByCourseId(String courseNum) {
        int count = 0;
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass where courseId = '" + courseNum + "'");
            res.last();
            count = res.getRow();
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public CourseClass[] findClassByCourseId(String courseNum) {
        CourseClass[] classes = new CourseClass[1];
        ClassNameListDao dao = new ClassNameListDao();
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass where courseId = '" + courseNum + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }
            classes = new CourseClass[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                classes[index] = new CourseClass(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8),
                        dao.findStudentIdByClassId(res.getString(1))
                );
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public CourseClass findClassByClassId(String classId) {
        CourseClass classes = null;
        ClassNameListDao dao = new ClassNameListDao();
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass where classId = '" + classId + "'");

            if (!res.next()) {
                return null;
            }
            res.beforeFirst();
            res.next();

            classes = new CourseClass(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(5),
                    res.getInt(6),
                    res.getInt(7),
                    res.getString(8),
                    dao.findStudentIdByClassId(res.getString(1)));

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public boolean deleteClassByClassId(String classId){
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int res = sta.executeUpdate("delete from tblClass where classId = '" + classId + "'");

            con.close();//关闭数据库连接
            if (res == 0) return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public CourseClass[] showAllClass() {
        CourseClass[] classes = new CourseClass[1];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblClass");

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            classes = new CourseClass[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                classes[index] = new CourseClass(
                        res.getString(1),
                        res.getString(2),
                        res.getString(4),
                        res.getString(5),
                        res.getInt(6),
                        res.getInt(7),
                        res.getString(8));
                index++;
            }

            con.close();//关闭数据库连接


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public boolean ModifyClass(CourseClass courseClass) {
        String sqlString = "update tblClass " +
                "set courseId = " + courseClass.getCourseNum() +
                "set classTeacher = " + "Null"+
                "set classTeacherId = " + courseClass.getClassTeacher() +
                "set classPlace = " + courseClass.getClassPlace() +
                "set classMax = " + courseClass.getClassMax() +
                "set classTemp = " + courseClass.getClassTemp()+
                "set classTime = " + courseClass.getClassTime() +
                " where classId = '" + courseClass.getClassID() + "'";

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
}
