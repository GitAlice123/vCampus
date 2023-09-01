package view.Hospital;

public class Doctor {
    String Depart_ID;//所属科室ID
    String Doctor_name;//医生姓名
    boolean Doctor_level;//0普通or1专家
    String Doctor_phone;//医生电话
    String Doctor_addr;//医生所在房间地址
    boolean Doctor_sex;//医生性别1为女，0为男
    String Doctor_id;

    public String getDepart_ID() {
        return Depart_ID;
    }

    public void setDepart_ID(String depart_ID) {
        Depart_ID = depart_ID;
    }

    public String getDoctor_name() {
        return Doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        Doctor_name = doctor_name;
    }

    public boolean isDoctor_level() {
        return Doctor_level;
    }

    public void setDoctor_level(boolean doctor_level) {
        Doctor_level = doctor_level;
    }

    public String getDoctor_phone() {
        return Doctor_phone;
    }

    public void setDoctor_phone(String doctor_phone) {
        Doctor_phone = doctor_phone;
    }

    public String getDoctor_addr() {
        return Doctor_addr;
    }

    public void setDoctor_addr(String doctor_addr) {
        Doctor_addr = doctor_addr;
    }

    public boolean isDoctor_sex() {
        return Doctor_sex;
    }

    public void setDoctor_sex(boolean doctor_sex) {
        Doctor_sex = doctor_sex;
    }

    public String getDoctor_id() {
        return Doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        Doctor_id = doctor_id;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Depart_ID='" + Depart_ID + '\'' +
                ", Doctor_name='" + Doctor_name + '\'' +
                ", Doctor_level=" + Doctor_level +
                ", Doctor_phone='" + Doctor_phone + '\'' +
                ", Doctor_addr='" + Doctor_addr + '\'' +
                ", Doctor_sex=" + Doctor_sex +
                ", Doctor_id='" + Doctor_id + '\'' +
                '}';
    }

    public Doctor(String depart_ID, String doctor_name, boolean doctor_level, String doctor_phone, String doctor_addr, boolean doctor_sex, String doctor_id) {
        Depart_ID = depart_ID;
        Doctor_name = doctor_name;
        Doctor_level = doctor_level;
        Doctor_phone = doctor_phone;
        Doctor_addr = doctor_addr;
        Doctor_sex = doctor_sex;
        Doctor_id = doctor_id;
    }

    public Doctor() {}
}
