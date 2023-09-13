package view.DAO;

import view.CourseSelection.Course;

import java.sql.*;

/**
 * 选课／学籍 课程DAO
 */
public class CourseDao {
    /**
     * 通过课程编号查找课程
     * @param courseNum 课程编号
     * @return 查找到的课程Course
     */
    public Course findCourseByNum(String courseNum) {

        Course course = new Course();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblCourse where courseId = '" + courseNum + "'");

            if (!res.next()) {
                return null;
            }
            res.beforeFirst();
            res.next();


            course.setCourseID(res.getString(1));
            course.setCourseName(res.getString(2));
            course.setCourseType(res.getString(3));
            course.setCourseTime(res.getDouble(4));
            course.setCredit(res.getDouble(5));


            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    /**
     * 显示所有的课程
     * @return 所有的课程Course[]
     */
    public Course[] showAllCourse() {
        Course[] course = new Course[1];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblCourse");

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            course = new Course[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                course[index] = new Course(res.getString(1), res.getString(2), res.getString(3), res.getDouble(4), res.getDouble(5));
                index++;
            }

            con.close();//关闭数据库连接


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    /**
     * 添加课程
     * @param course 用户输入的信息
     * @return 是否添加成功
     */
    public boolean createCoures(Course course) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblCourse where courseId = '" + course.getCourseID() + "'");
            if (test.next()) {
                con.close();
                return false;
            }
            sta.executeUpdate("insert into tblCourse(courseId,courseName,couresType,courseHors,credit) values('"
                    + course.getCourseID() + "','"
                    + course.getCourseName() + "','"
                    + course.getCourseType("") + "','"
                    + course.getCourseTime() + "','"
                    + course.getCredit() + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 删除课程
     * @param courseId 要删除的课程的编号
     * @return 是否删除成功
     */
    public boolean deleteCouresById(String courseId) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int res = sta.executeUpdate("delete from tblCourse where courseId = '" + courseId + "'");

            con.close();//关闭数据库连接
            if (res == 0) return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

}
