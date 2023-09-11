package view.Hospital;

import java.util.Date;

public class Register {
    String Patient_ID;//和总的id对应
    Date Register_date;//挂号日期
    String Register_depart;//挂号科室
    String Register_ID;//挂号记录的唯一编号
    boolean Register_Ifpaid;//1为已缴费，0为未缴费
    double Register_amount;//应缴费金额

    public Register() {
    }

    public Register(String patient_ID, Date register_date, String register_depart, String register_ID, boolean register_Ifpaid, double register_amount) {
        Patient_ID = patient_ID;
        Register_date = register_date;
        Register_depart = register_depart;
        Register_ID = register_ID;
        Register_Ifpaid = register_Ifpaid;
        Register_amount = register_amount;
    }

    public String getPatient_ID() {
        return Patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        Patient_ID = patient_ID;
    }

    public Date getRegister_date() {
        return Register_date;
    }

    public void setRegister_date(Date register_date) {
        Register_date = register_date;
    }

    public String getRegister_depart() {
        return Register_depart;
    }

    public void setRegister_depart(String register_depart) {
        Register_depart = register_depart;
    }

    public String getRegister_ID() {
        return Register_ID;
    }

    public void setRegister_ID(String register_ID) {
        Register_ID = register_ID;
    }

    public boolean isRegister_Ifpaid() {
        return Register_Ifpaid;
    }

    public void setRegister_Ifpaid(boolean register_Ifpaid) {
        Register_Ifpaid = register_Ifpaid;
    }

    public double getRegister_amount() {
        return Register_amount;
    }

    public void setRegister_amount(double register_amount) {
        Register_amount = register_amount;
    }
}