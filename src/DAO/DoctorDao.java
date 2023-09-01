package view.DAO;

import view.Hospital.Doctor;

import java.sql.*;

public class DoctorDao {
    public Doctor findDocById(String id){
        Doctor doctor = new Doctor();

        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);

            ResultSet res = sta.executeQuery("select * from tblDoctor where Doctor_id = '" + id + "'");

            if(!res.next()){return null;}
            res.beforeFirst();
            res.next();

            doctor.setDepart_ID(res.getString(1));
            doctor.setDoctor_name(res.getString(2));
            doctor.setDoctor_level(res.getBoolean(3));
            doctor.setDoctor_phone(res.getString(4));
            doctor.setDoctor_addr(res.getString(5));
            doctor.setDoctor_sex(res.getBoolean(6));
            doctor.setDoctor_id(res.getString(7));

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public boolean createDoc(Doctor doctor){
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");//导入Access驱动文件，本质是.class文件
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:Access:///.\\src\\Database\\vCampus.mdb", "", "");
            //与数据库建立连接，getConnection()方法第一个参数为jdbc:Access:///+文件总路径,第二个参数是用户名 ，第三个参数是密码（Access是没有用户名和密码此处为空字符串）
            Statement sta = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet test = sta.executeQuery("select * from tblDoctor where Doctor_id = '" + doctor.getDoctor_id() + "'");
            if(test.next()){return false;}
            sta.executeUpdate("insert into tblDoctor(Depart_ID,Doctor_name,Doctor_level,Doctor_phone,Doctor_addr,Doctor_sex,Doctor_id) values('"
                    + doctor.getDepart_ID() + "','"
                    + doctor.getDoctor_name() + "','"
                    + doctor.isDoctor_level() + "','"
                    + doctor.getDoctor_phone() + "','"
                    + doctor.getDoctor_addr() + "','"
                    + doctor.isDoctor_sex() + "','"
                    + doctor.getDoctor_id() + "')");

            con.close();//关闭数据库连接

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
