package view.DAO;

import view.Hospital.Department;

import java.sql.*;

/**
 * 医院医生/科室DAO
 */
public class DepartmentDao {
    /**
     * 通过部门和医生类型查找
     *
     * @param Department_Type 部门（可以传空值）
     * @param Doctor_Type     医生类型（可以传空值）
     * @return 查找到的医生Department[]
     */
    public Department[] findDepartmentByInfo(String Department_Type, String Doctor_Type) {
        Department[] dep = new Department[1];
        String sqlstring = null;

        switch (Doctor_Type) {
            case "专家" -> {
                if (Department_Type.equals(""))
                    sqlstring = "select * from tblDepartment where Doctor_Type = '" + true + "'";
                else
                    sqlstring = "select * from tblDepartment where Department_name = '" + Department_Type + "' and Doctor_Type = '" + true + "'";
            }
            case "普通" -> {
                if (Department_Type.equals(""))
                    sqlstring = "select * from tblDepartment where Doctor_Type = '" + false + "'";
                else
                    sqlstring = "select * from tblDepartment where Department_name = '" + Department_Type + "' and Doctor_Type = '" + false + "'";
            }
            default -> {
                if (Department_Type.equals(""))
                    sqlstring = "select * from tblDepartment";
                else
                    sqlstring = "select * from tblDepartment where Department_name = '" + Department_Type + "'";
            }
        }


        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery(sqlstring);

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

    /**
     * 创建医生/科室条目
     *
     * @param dep 输入的信息
     * @return 是否创建成功
     */
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

    /**
     * 通过医生ID删除对应条目，并级联删除RegisterPayment表中和被删除科室有关的挂号记录
     *
     * @param Department_ID 要删除的条目ID
     * @return 是否删除成功
     */
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
            sta.executeUpdate("delete from tblRegisterPayment where Register_depart = '" + Department_ID + "'");
            //先级联删除RegisterPayment表中和被删除科室有关的挂号记录
            int res = sta.executeUpdate("delete from tblDepartment where Department_ID = '" + Department_ID + "'");
            con.close();//关闭数据库连接
            if (res == 0) return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 管理员通过医生ID查找对应条目
     *
     * @param Department_ID 要查找的条目ID
     * @return 找到的条目Department
     */
    public Department findDepartmentById(String Department_ID) {
        Department dep = new Department();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = sta.executeQuery("select * from tblDepartment where Department_ID = '" + Department_ID + "'");

            if (!res.next()) {
                return null;
            }
            res.beforeFirst();
            res.next();

            dep = new Department(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getBoolean(4),
                    res.getString(5),
                    res.getString(6)
            );

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dep;
    }

    /**
     * 显示所有的医生
     *
     * @return 所有的医生Department[]
     */
    public Department[] showAllDep() {
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
            ResultSet res = sta.executeQuery("select * from tblDepartment");

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
}
