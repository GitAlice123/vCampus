package view.DAO;

import view.Library.BookOperationRecord;
import view.Shop.PurchaseRecord;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookOperationRecordDao {

    /**
     * 新增书籍操作记录信息
     *
     * @param bookOperationRecord  需要新增的购书籍操作记录的信息
     * @return 新增是否成功，如果数据库中原本就存在该书籍操作记录，则不进行新增插入操作，返回false
     */
    public boolean AddBookOperationRecord(BookOperationRecord bookOperationRecord){
        String sqlString1 = "select * from tblBookOperationRecord where oprId = '" + bookOperationRecord.getOprId() + "'";
        //查找数据库中原本是否存在该书籍操作记录

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
            }//如果在数据库原本就存在该书记操作记录，则返回false，不进行新增插入操作
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        String sqlString2 = "insert into tblBookOperationRecord values('"+ bookOperationRecord.getOprId() +"','" + bookOperationRecord.getuId()
                +"','" + bookOperationRecord.getISBN() + "','" + ft.format(bookOperationRecord.getOprTime())
                +"','" + bookOperationRecord.getOprtype() + "','" + bookOperationRecord.getOprMark() + "')";

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


    /**
     * 查找并返回数据库所有的书籍操作记录信息
     *
     * @return BookOperationRecord类数组allRecords，代表数据库中所有的书籍操作记录，根据操作时间升序排序
     */
    public BookOperationRecord[] findAllBookOperationRecord(){
        String sqlString = "select * from tblBookOperationRecord order by oprTime";
        BookOperationRecord[] allRecords = new BookOperationRecord[10];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb?serverTimezone=UTC+8", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlString);

            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if(count == 0){
                return null;    //如果无书籍操作记录，则返回null
            }

            allRecords = new BookOperationRecord[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                res.getDate(4,cal1);
                res.getTime(4,cal2);
                cal1.set(Calendar.HOUR_OF_DAY,cal2.get(Calendar.HOUR_OF_DAY));
                cal1.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
                cal1.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
                Date oprTime = cal1.getTime();
                allRecords[index] = new BookOperationRecord(res.getString(1),res.getString(2),res.getString(3),oprTime,res.getString(5),res.getString(6));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allRecords;
    }


}
