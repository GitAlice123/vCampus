package view.DAO;

import view.SchoolRolls.StudentInfo;

import java.sql.*;
import java.text.SimpleDateFormat;
public class StudentInfoDao {

    public StudentInfo findStudentInfoById(String uId){
        /*
            通过一卡通号查询学籍信息
            传入参数为一卡通号uId
            返回值为该uId对应的一个 StudentInfo类的学籍信息数据
         */

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
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            if(!res.next()){
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

    public boolean AddStudentInfo(StudentInfo stuInfo) {
        /*
            增加学生学籍信息
            传入参数为想要添加的学生学籍信息
         */
        String sqlString1 = "select * from tblStudentInfo where uId = '" + stuInfo.getCardID() + "'";
        //查找数据库中原本是否存在该学籍信息

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if (res.next()) {
                return false;
            }//如果在数据库原本就存在该学籍信息，则返回false，不进行新增插入操作
            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");

        String sqlString = "insert into tblStudentInfo values('" + stuInfo.getCardID() + "','" + stuInfo.getID() + "','" + stuInfo.getName()
                + "','" + stuInfo.getSex() + "','" + ft.format(stuInfo.getBirth()) + "'," + stuInfo.getGrade() + ",'" + stuInfo.getCollege() + "')";

        System.out.println(ft.format(stuInfo.getBirth()));

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


    public Boolean DeleteStudentInfoById(String uId) {
        /*
            根据一卡通号删除学生学籍信息，并级联删除ClassNameList表、Grade表、PurchaseRecord表里相关信息
            传入参数为一卡通号uId
         */

        String sqlString = "select * from tblStudentInfo where uId = '" + uId + "'";
        //查看StudentInfo表中原先存不存在该学生信息
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
                return false;
            }//如果在数据库找不到该学生信息，则返回false
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sqlString1 = "delete from tblClassNameList where uId ='" + uId + "'";
        //删除ClassNameList表里的相关信息
        String sqlString2 = "delete from tblGrade where uId ='" + uId + "'";
        //删除Grade表里的相关信息
        String sqlString3 = "delete from tblPurchaseRecord where uId ='" + uId + "'";
        //删除PurchaseRecord表里的相关信息
        String sqlString4 = "delete from tblBookHold where uId ='" + uId + "'";
        //删除BookHold表里的相关信息

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            sta.executeUpdate(sqlString1);
            sta.executeUpdate(sqlString2);
            sta.executeUpdate(sqlString3);
            sta.executeUpdate(sqlString4);
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        String sqlString5 = "delete from tblStudentInfo where uId ='" + uId + "'";
        //删除该学籍信息
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString5); //返回删除数据条数
            if (count == 0) return false;

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public StudentInfo[] showAllStudentInfo(){
       /*
            查询所以学生学籍信息
         */
        String sqlString = "select * from tblStudentInfo order by uId";
        StudentInfo[] allInfo = new StudentInfo[10];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database.\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;    //如果无书籍操作记录，则返回null
            }

            allInfo = new StudentInfo[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                allInfo[index] = new StudentInfo(res.getString(1), res.getString(2), res.getString(4), res.getString(3), res.getDate(5),res.getInt(6),res.getString(7));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allInfo;
    }
    public boolean ModifyStudentInfo(StudentInfo stuInfo){

        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        String sqlString = "update tblStudentInfo set sId= '" + stuInfo.getID() + "', uName = '" + stuInfo.getName() +
                "', uSex = '" + stuInfo.getSex() + "', uBirth = '" + ft.format(stuInfo.getBirth()) + "', uGrade = '" +
                + stuInfo.getGrade() + "', uCollege = '" + stuInfo.getCollege()
                + "' where uId = '" + stuInfo.getCardID() + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database.\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString);
            if (count == 0) return false; //若无修改记录，则返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
