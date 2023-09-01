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

}
