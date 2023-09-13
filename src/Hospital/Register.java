package view.Hospital;

import java.util.Date;

/**
 * 医院挂号记录类，用于表示患者的挂号信息。
 */
public class Register {
    String Patient_ID;          // 患者ID，与总的ID对应
    Date Register_date;        // 挂号日期
    String Register_depart;    // 挂号科室
    String Register_ID;        // 挂号记录的唯一编号
    boolean Register_Ifpaid;   // 是否已缴费，true表示已缴费，false表示未缴费
    double Register_amount;    // 应缴费金额

    /**
     * 默认构造函数，创建一个空的挂号记录对象。
     */
    public Register() {
    }

    /**
     * 构造函数，用于创建一个具有指定属性的挂号记录对象。
     *
     * @param patient_ID     患者ID
     * @param register_date  挂号日期
     * @param register_depart 挂号科室
     * @param register_ID    挂号记录的唯一编号
     * @param register_Ifpaid 是否已缴费，true表示已缴费，false表示未缴费
     * @param register_amount 应缴费金额
     */
    public Register(String patient_ID, Date register_date, String register_depart, String register_ID, boolean register_Ifpaid, double register_amount) {
        Patient_ID = patient_ID;
        Register_date = register_date;
        Register_depart = register_depart;
        Register_ID = register_ID;
        Register_Ifpaid = register_Ifpaid;
        Register_amount = register_amount;
    }

    /**
     * 获取患者ID。
     *
     * @return 患者ID
     */
    public String getPatient_ID() {
        return Patient_ID;
    }

    /**
     * 设置患者ID。
     *
     * @param patient_ID 患者ID
     */
    public void setPatient_ID(String patient_ID) {
        Patient_ID = patient_ID;
    }

    /**
     * 获取挂号日期。
     *
     * @return 挂号日期
     */
    public Date getRegister_date() {
        return Register_date;
    }

    /**
     * 设置挂号日期。
     *
     * @param register_date 挂号日期
     */
    public void setRegister_date(Date register_date) {
        Register_date = register_date;
    }

    /**
     * 获取挂号科室。
     *
     * @return 挂号科室
     */
    public String getRegister_depart() {
        return Register_depart;
    }

    /**
     * 设置挂号科室。
     *
     * @param register_depart 挂号科室
     */
    public void setRegister_depart(String register_depart) {
        Register_depart = register_depart;
    }

    /**
     * 获取挂号记录的唯一编号。
     *
     * @return 挂号记录的唯一编号
     */
    public String getRegister_ID() {
        return Register_ID;
    }

    /**
     * 设置挂号记录的唯一编号。
     *
     * @param register_ID 挂号记录的唯一编号
     */
    public void setRegister_ID(String register_ID) {
        Register_ID = register_ID;
    }

    /**
     * 检查是否已缴费。
     *
     * @return true表示已缴费，false表示未缴费
     */
    public boolean isRegister_Ifpaid() {
        return Register_Ifpaid;
    }

    /**
     * 设置是否已缴费。
     *
     * @param register_Ifpaid 是否已缴费，true表示已缴费，false表示未缴费
     */
    public void setRegister_Ifpaid(boolean register_Ifpaid) {
        Register_Ifpaid = register_Ifpaid;
    }

    /**
     * 获取应缴费金额。
     *
     * @return 应缴费金额
     */
    public double getRegister_amount() {
        return Register_amount;
    }

    /**
     * 设置应缴费金额。
     *
     * @param register_amount 应缴费金额
     */
    public void setRegister_amount(double register_amount) {
        Register_amount = register_amount;
    }
}
