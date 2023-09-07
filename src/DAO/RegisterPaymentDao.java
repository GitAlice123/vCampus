package view.DAO;

import view.Hospital.Register;

import java.sql.*;
import java.text.SimpleDateFormat;

public class RegisterPaymentDao {
    public boolean createRegisterInfo(Register reg, double payment_amount) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblRegisterPayment where Register_ID = '" + reg.getRegister_ID() + "'");
            if (test.next()) {
                return false;
            }
            sta.executeUpdate("insert into tblRegisterPayment(Patient_ID,Register_date,Register_depart,Register_ID,Ifpaid_pay,Payment_amount) values('" +
                    reg.getPatient_ID() + "','" +
                    ft.format(reg.getRegister_date()) + "','" +
                    reg.getRegister_depart() + "','" +
                    reg.getRegister_ID() + "','" +
                    false + "','" +
                    String.valueOf(payment_amount) + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public Register[] findRegisterByUserID(String ID) {
        Register[] regs = new Register[1];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblRegisterPayment where Patient_ID = '" + ID + "'");
            res.last();
            int count = res.getRow();
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            regs = new Register[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                regs[index] = new Register(
                        res.getString(1),
                        res.getDate(2),
                        res.getString(3),
                        res.getString(4),
                        res.getBoolean(5),
                        res.getDouble(6)
                );
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return regs;
    }

    public Register[] findPaymentByUserID(String ID) {
        Register[] pays = new Register[1];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblRegisterPayment where Patient_ID = '" + ID + "' and Ifpaid_pay = ‘" + false + "'");

            int count = 0;
            while (res.next()) {
                count++;
            }
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            pays = new Register[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                pays[index] = new Register(
                        res.getString(1),
                        res.getDate(2),
                        res.getString(3),
                        res.getString(4),
                        res.getBoolean(5),
                        res.getDouble(6)
                );
                index++;
            }

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pays;
    }

    public boolean payRegisterById(String Register_ID) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int ret = sta.executeUpdate("update tblRegisterPayment set Ifpaid_pay = '" + true + "' where Register_ID = '" + Register_ID + "'");
            con.close();//关闭数据库连接
            if (ret == 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
