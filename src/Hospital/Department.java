package view.Hospital;

/**
 * 医院科室信息类。
 */
public class Department {
    String Department_name;      // 科室名称
    String Department_ID;        // 医生ID
    String Department_dir;       // 挂号医生
    boolean Department_level;    // 0表示普通科室，1表示专家科室
    String Department_phone;     // 医生电话
    String Department_addr;      // 医生所在房间地址

    /**
     * 默认构造函数。
     */
    public Department() {
    }

    /**
     * 构造函数，用于初始化科室信息。
     *
     * @param department_name     科室名称
     * @param department_ID       医生ID
     * @param department_dir      挂号医生
     * @param department_level    科室级别，0表示普通科室，1表示专家科室
     * @param department_phone    医生电话
     * @param department_addr     医生所在房间地址
     */
    public Department(String department_name, String department_ID, String department_dir,
                      boolean department_level, String department_phone, String department_addr) {
        Department_name = department_name;
        Department_ID = department_ID;
        Department_dir = department_dir;
        Department_level = department_level;
        Department_phone = department_phone;
        Department_addr = department_addr;
    }

    /**
     * 获取科室级别。
     *
     * @return 科室级别，0表示普通科室，1表示专家科室
     */
    public boolean getDepartment_level() {
        return Department_level;
    }

    /**
     * 设置科室级别。
     *
     * @param department_level 科室级别，0表示普通科室，1表示专家科室
     */
    public void setDepartment_level(boolean department_level) {
        Department_level = department_level;
    }

    /**
     * 获取医生电话。
     *
     * @return 医生电话
     */
    public String getDepartment_phone() {
        return Department_phone;
    }

    /**
     * 设置医生电话。
     *
     * @param department_phone 医生电话
     */
    public void setDepartment_phone(String department_phone) {
        Department_phone = department_phone;
    }

    /**
     * 获取医生所在房间地址。
     *
     * @return 医生所在房间地址
     */
    public String getDepartment_addr() {
        return Department_addr;
    }

    /**
     * 设置医生所在房间地址。
     *
     * @param department_addr 医生所在房间地址
     */
    public void setDepartment_addr(String department_addr) {
        Department_addr = department_addr;
    }

    /**
     * 获取科室名称。
     *
     * @return 科室名称
     */
    public String getDepartment_name() {
        return Department_name;
    }

    /**
     * 设置科室名称。
     *
     * @param department_name 科室名称
     */
    public void setDepartment_name(String department_name) {
        Department_name = department_name;
    }

    /**
     * 获取医生ID。
     *
     * @return 医生ID
     */
    public String getDepartment_ID() {
        return Department_ID;
    }

    /**
     * 设置医生ID。
     *
     * @param department_ID 医生ID
     */
    public void setDepartment_ID(String department_ID) {
        Department_ID = department_ID;
    }

    /**
     * 获取挂号医生。
     *
     * @return 挂号医生
     */
    public String getDepartment_dir() {
        return Department_dir;
    }

    /**
     * 设置挂号医生。
     *
     * @param department_dir 挂号医生
     */
    public void setDepartment_dir(String department_dir) {
        Department_dir = department_dir;
    }

    /**
     * 重写toString方法，以便在需要时以字符串形式表示科室信息。
     *
     * @return 科室信息的字符串表示
     */
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
}
