package view.Hospital;

public class Department {
    String Department_name;//科室名称
    String Department_ID;//科室ID（唯一编号）
    String Department_dir;//科室主任

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
                '}';
    }
}
