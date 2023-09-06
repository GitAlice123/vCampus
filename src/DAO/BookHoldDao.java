package view.DAO;

import view.Library.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import view.DAO.*;
public class BookHoldDao {

    /**
     * 查找该用户目前借了那些书
     *
     * @param uId 查询用户一卡通号
     * @return 该用户的所有现存借书记录，已BookHold[]的形式返回
     */
    public BookHold[] findBookHoldsById(String uId){
        String sqlString = "select * from tblBookHold where uId = '" + uId + "'";
        BookHold[] bookHolds = new BookHold[10];

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

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if(count == 0){
                return null;
            }

            bookHolds = new BookHold[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                bookHolds[index] = new BookHold(res.getString(1),res.getString(2),res.getDate(3),
                        res.getDate(4));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookHolds;


    }



    /**
     * 借书，在BookHold表中新增该用户对该书本的借书记录
     *
     * @param borrowRecord 借书操作记录（包涵操作号、一卡通号、书号、操作时间、操作类型、备注）
     * @return 借书操作是否成功，如该书已被该用户借阅，则返回false，并不进行借书操作
     */
    public boolean Borrow(view.Library.BookOperationRecord borrowRecord){
        String sqlString1 = "select * from tblBookHold where uId = '" + borrowRecord.getuId() + "' and bookISBN ='" +
               borrowRecord.getISBN() + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(res.next()){
                return false;
            }//如果在数据库找BookHold表中已存在该用户对该书的借阅记录，则返回false，不再进行借书操作
            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }


        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowRecord.getOprTime());
        cal.add(Calendar.MONTH, 2);
        Date outdateTime = cal.getTime();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd");
        String sqlString2 = "insert into tblBookHold values('" + borrowRecord.getuId() + "','" + borrowRecord.getISBN() + "','" +
                ft.format(borrowRecord.getOprTime()) + "','" + ft.format(outdateTime)  + "')";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            sta.executeUpdate(sqlString2);


            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return true;
    }


    /*
     * 还书，在BookHold表中删除该用户对该书本的借书记录
     *
     * @param borrowRecord 还书操作记录（包涵操作号、一卡通号、书号、操作时间、操作类型、备注）
     * @return 还书操作是否成功，如该书还未被该用户借阅，或者操作日期晚于借阅过期时间，则返回false，并不进行还书操作
     */
    public boolean Return(view.Library.BookOperationRecord borrowRecord){
        String sqlString1 = "select * from tblBookHold where uId = '" + borrowRecord.getuId() + "' and bookISBN ='" +
                borrowRecord.getISBN() + "'";
        Date outdateTime = new Date();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            }//如果在数据库BookHold表中找不到该用户对该书的借阅记录，说明该用户还未借阅该书，则返回false，不再进行还书操作

            res.beforeFirst();
            res.next();
            outdateTime = res.getDate(4);

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(borrowRecord.getOprTime().after(outdateTime))return false;
        //如果操作时间晚于借阅过期时间，则返回false，并不进行还书操作

        String sqlString2 = "delete from tblBookHold where uId = '" + borrowRecord.getuId() + "' and bookISBN ='" +
                borrowRecord.getISBN() + "'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString2);
            if(count == 0) return false; //若未进行删除操作，返回false

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    /*
     * 续借，在BookHold表中修改该用户对该书本的借书情况，将过期时间改为当前操作时间加两个月；
     *
     * @param borrowRecord 续借操作记录（包涵操作号、一卡通号、书号、操作时间、操作类型、备注）
     * @return 续借操作是否成功，如该书还未被该用户借阅，则返回false，或者操作日期晚于借阅过期时间，并不进行续借操作
     */
    public boolean Renew(view.Library.BookOperationRecord renewRecord){
        String sqlString1 = "select * from tblBookHold where uId = '" + renewRecord.getuId() + "' and bookISBN ='" +
                renewRecord.getISBN() + "'";
        Date outdateTime = new Date();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString1);

            if(!res.next()){
                return false;
            }//如果在数据库BookHold表中找不到该用户对该书的借阅记录，说明该用户还未借阅该书，则返回false，不再进行续借操作

            res.beforeFirst();
            res.next();
            outdateTime = res.getDate(4);

            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(renewRecord.getOprTime().after(outdateTime))return false;
        //如果操作时间晚于借阅过期时间，则返回false，并不进行还书操作

        Calendar cal = Calendar.getInstance();
        cal.setTime(renewRecord.getOprTime());
        cal.add(Calendar.MONTH, 2);
        Date new_outdateTime=cal.getTime();
        //将借阅时间在当前操作时间的基础上增加两个月

        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd");
        String sqlString = "update tblBookHold set outdateTime = '"+ ft.format(new_outdateTime)
                + "' where uId = '" + renewRecord.getuId() + "' and bookISBN = '" + renewRecord.getISBN() +"'";

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int count = sta.executeUpdate(sqlString);
            if(count == 0) return false;

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
