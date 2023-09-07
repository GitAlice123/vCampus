package view.DAO;

import view.Hospital.Department;
import view.Hospital.Register;

import java.sql.*;

public class DepartmentDao {
    public Department[] findDepaetmentByInfo(String Department_Type, boolean Doctor_Type) {
        Department[] dep = new Department[1];

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblDepartment where Department_name = '" + Department_Type + "' and Doctor_Type = '" + Doctor_Type + "'");

            int count = 0;
            while (res.next()) {
                count++;
            }
            res.beforeFirst();

            if (count == 0) {
                return null;
            }

            dep = new Department[count];
            int index = 0;
            while (res.next()) {//不断的移动光标到下一个数据
                dep[index] = new Department(
                        res.getString(1),
                        res.getString(2),
                        res.getString(3),
                        res.getBoolean(4),
                        res.getString(5),
                        res.getString(6)
                );
                index++;
            }
            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dep;
    }

    public boolean createDepartment(Department dep) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblDepartment where Department_ID = '" + dep.getDepartment_ID() + "'");
            if (test.next()) {
                return false;
            }
            sta.executeUpdate("insert into tblDepartment(Department_name,Department_ID,Doctor_name,Doctor_Type,Department_phone,Department_addr) values('" +
                    dep.getDepartment_name() + "','" +
                    dep.getDepartment_ID() + "','" +
                    dep.getDepartment_dir() + "','" +
                    dep.getDepartment_level() + "','" +
                    dep.getDepartment_phone() + "','" +
                    dep.getDepartment_addr() + "')");
            con.close();//关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteDepartment(String Department_ID) {
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int res = sta.executeUpdate("delete from tblDepartment where Department_ID = '" + Department_ID + "'");
            con.close();//关闭数据库连接
            if (res == 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
