package view.DAO;

import view.Hospital.Register;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 医院挂号缴费记录DAO
 * @author SunYanlin
 */
public class RegisterPaymentDao {
    /**
     * 通过传入的挂号信息增加挂号记录
     * @param reg 用户输入的挂号信息
     * @return 是否成功添加
     */
    public boolean createRegisterInfo(Register reg) {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
                    reg.getRegister_amount() + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 通过用户ID查找其对应的挂号记录
     * @param ID 一卡通号
     * @return 所有对应ID的挂号记录Register[]
     */
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
                        res.getTimestamp(2),
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

    /**
     * 通过用户ID查找所有未缴费记录
     * @param ID 一卡通号
     * @return 所有对应ID的未缴费记录Register[]
     */
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
            ResultSet res = sta.executeQuery("select * from tblRegisterPayment where Patient_ID = '" + ID + "' and Ifpaid_pay = '" + false + "'");

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
                        res.getTimestamp(2),
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

    /**
     * 将对应ID的记录改为已交付
     * @param Register_ID
     * @return 是否修改成功
     */
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

    /**
     * 管理员功能，展示所有人的挂号记录
     * @return 所有挂号记录Register[]
     */
    public Register[] showAllRegister() {
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
            ResultSet res = sta.executeQuery("select * from tblRegisterPayment");

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
                        res.getTimestamp(2),
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
}
