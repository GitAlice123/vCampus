package view.Hospital;

public class Department {
    String Department_name;//科室名称
    String Department_ID;//科室ID（唯一编号）
    String Department_dir;//挂号医生
    boolean Department_level;//0普通or1专家
    String Department_phone;//医生电话
    String Department_addr;//医生所在房间地址

    public boolean isDepartment_level() {
        return Department_level;
    }

    public void setDepartment_level(boolean department_level) {
        Department_level = department_level;
    }

    public String getDepartment_phone() {
        return Department_phone;
    }

    public void setDepartment_phone(String department_phone) {
        Department_phone = department_phone;
    }

    public String getDepartment_addr() {
        return Department_addr;
    }

    public void setDepartment_addr(String department_addr) {
        Department_addr = department_addr;
    }

    public String getDepartment_name() {
        return Department_name;
    }

    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }

    public String getDepartment_ID() {
        return Department_ID;
    }

    public void setDepartment_ID(String department_ID) {
        Department_ID = department_ID;
    }

    public String getDepartment_dir() {
        return Department_dir;
    }

    public void setDepartment_dir(String department_dir) {
        Department_dir = department_dir;
    }

    @Override
    public String toString() {
        return "Department{" +
                "Department_name='" + Department_name + '\'' +
                ", Department_ID='" + Department_ID + '\'' +
                ", Department_dir='" + Department_dir + '\'' +
                ", Department_level=" + Department_level +
                ", Department_phone='" + Department_phone + '\'' +
                ", Department_addr='" + Department_addr + '\'' +
                '}';
    }

    public Department() {
    }

    public Department(String department_name, String department_ID, String department_dir, boolean department_level, String department_phone, String department_addr) {
        Department_name = department_name;
        Department_ID = department_ID;
        Department_dir = department_dir;
        Department_level = department_level;
        Department_phone = department_phone;
        Department_addr = department_addr;
    }
}
