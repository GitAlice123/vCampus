package view.DAO;


import view.Bank.bankBill;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bankBillDao {

    /**
     * 查找并返回数据库中tblbankBill表中的所有账单信息 (管理员界面使用，现在不一定用得上)
     *
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    public bankBill[] findAllBills(){
        String sqlString = "select * from tblBankBill order by bill_time";
        bankBill[] allBills = new bankBill[10];

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
            int count = res.getRow(); //查找到账单信息的条数
            res.beforeFirst();

            if(count == 0){
                return null;
            }//若查找到0条账单数据，则返回null

            allBills = new bankBill[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                res.getDate(5,cal1);
                res.getTime(5,cal2);
                cal1.set(Calendar.HOUR_OF_DAY,cal2.get(Calendar.HOUR_OF_DAY));
                cal1.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
                cal1.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
                Date oprTime = cal1.getTime();
                allBills[index] = new bankBill(res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),oprTime,res.getBoolean(6),res.getDouble(7));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBills;
    }

    /**
     * 通过一卡通号查找并返回数据库中tblbankBill表中的所有该id的账单信息（无用，可用billForSometime函数替代）
     *
     * @return bankBill类数组allbills，代表数据库中所有该id的的账单
     */
    public bankBill[] findAllBillsById(String id){
        String sqlString = "select * from tblBankBill where bill_userID = '" + id + "'";
        bankBill[] allBills = new bankBill[10];

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
            int count = res.getRow(); //查找到账单信息的条数
            res.beforeFirst();

            if(count == 0){
                return null;
            }//若查找到0条账单数据，则返回null

            allBills = new bankBill[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                res.getDate(5,cal1);
                res.getTime(5,cal2);
                cal1.set(Calendar.HOUR_OF_DAY,cal2.get(Calendar.HOUR_OF_DAY));
                cal1.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
                cal1.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
                Date oprTime = cal1.getTime();
                allBills[index] = new bankBill(res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),oprTime,res.getBoolean(6),res.getDouble(7));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBills;
    }


//    /**
//     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query的所有账单信息  (已弃用)！！！
//     *
//     * @param query    查询关键字，即账单描述
//     * @param id       需查询账户的一卡通号
//     * @return bankBill类数组allbills，代表数据库中所有的账单
//     */
//    public bankBill[] findBillsByQuery(String query,String id){
//        String sqlString = "select * from tblBankBill where bill_description = '" + query + "' and bill_userID = '" + id + "'";
//        bankBill[] allBills = new bankBill[10];
//
//        try {
//            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try {
//            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
//            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
//            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            ResultSet res = sta.executeQuery(sqlString);
//
//            int count = 0;
//            while (res.next()){
//                count++;
//            }   //查找到账单信息的条数
//            res.beforeFirst();
//
//            if(count == 0){
//                return null;
//            }//若查找到0条账单数据，则返回null
//
//            allBills = new bankBill[count];
//            int index=0;
//            while (res.next()) {//不断的移动光标到下一个数据
//                Calendar cal1 = Calendar.getInstance();
//                Calendar cal2 = Calendar.getInstance();
//                res.getDate(5,cal1);
//                res.getTime(5,cal2);
//                cal1.set(Calendar.HOUR_OF_DAY,cal2.get(Calendar.HOUR_OF_DAY));
//                cal1.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
//                cal1.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
//                Date oprTime = cal1.getTime();
//                allBills[index] = new bankBill(res.getString(1),res.getString(2),res.getString(3),
//                        res.getString(4),oprTime,res.getBoolean(6),res.getDouble(7));
//                index++;
//            }
//
//            con.close();//关闭数据库连接
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return allBills;
//    }

    /**
     * 查找并返回数据库中一卡通号为id、账单描述（类型）为query、且时间在startTime和endTime之间的所有账单信息
     *
     * @param id       需查询账户的一卡通号
     * @param startTime 查询时间段的开始时间
     * @param endTime   查询时间段的结束时间
     * @param query    查询关键字，即账单描述
     * @return bankBill类数组allbills，代表数据库中所有的账单
     */
    public bankBill[] findBillsForSometime(String id, Date startTime, Date endTime, String query){
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd hh:mm:ss");
        String sqlString = "";
        if(query == ""){
            sqlString = "select * from tblBankBill where bill_userID = '" + id + "' and bill_time >= '"
                    + ft.format(startTime) + "' and bill_time <= '" + ft.format(endTime) +  "'" ;
        }else{
            sqlString = "select * from tblBankBill where bill_userID = '" + id + "' and bill_time >= '"
                    + ft.format(startTime) + "' and bill_time <= '" + ft.format(endTime) + "' and bill_description = '" + query + "'" ;
        }

        bankBill[] allBills = new bankBill[10];

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

            int count = 0;
            while (res.next()){
                count++;
            }   //查找到账单信息的条数
            res.beforeFirst();

            if(count == 0){
                return null;
            }//若查找到0条账单数据，则返回null

            allBills = new bankBill[count];
            int index=0;
            while (res.next()) {//不断的移动光标到下一个数据
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                res.getDate(5,cal1);
                res.getTime(5,cal2);
                cal1.set(Calendar.HOUR_OF_DAY,cal2.get(Calendar.HOUR_OF_DAY));
                cal1.set(Calendar.MINUTE,cal2.get(Calendar.MINUTE));
                cal1.set(Calendar.SECOND,cal2.get(Calendar.SECOND));
                Date oprTime = cal1.getTime();
                allBills[index] = new bankBill(res.getString(1),res.getString(2),res.getString(3),
                        res.getString(4),oprTime,res.getBoolean(6),res.getDouble(7));
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBills;
    }


    /**
     * 向数据库中新增账单信息
     *
     * @param bankbill  增加的账单信息
     * @return 是否新增账单成功
     */
    public boolean AddBankBill(bankBill bankbill){
        String sqlString1 = "select * from tblBankBill where bill_nums = '" + bankbill.getNums() + "'";
        //查找数据库中原本是否存在该购买记录

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
            }//如果在数据库原本就存在该账单记录，则返回false，不进行新增插入操作
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }


        SimpleDateFormat ft = new SimpleDateFormat ("yyyy/MM/dd HH:mm:ss");
        String sqlString2 = "insert into tblBankBill values('"+ bankbill.getDescription() +"','" + bankbill.getNums() +"','" + bankbill.getCardId()
                +"','" + bankbill.getUserId() + "','" + ft.format(bankbill.getTime()) + "'," + bankbill.isType() + ","
                + bankbill.getAmount() + ")";

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

}
