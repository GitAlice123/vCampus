package view.Hospital;

public class Payment {
    boolean Ifpaid_pay;//1为已缴费，0为未缴费
    String Register_ID;//十位挂号记录编号
    double Payment_amount;//应缴费金额

    public Payment() {    }

    public Payment(boolean ifpaid_pay, String register_ID, double payment_amount) {
        Ifpaid_pay = ifpaid_pay;
        Register_ID = register_ID;
        Payment_amount = payment_amount;
    }

    public boolean isIfpaid_pay() {
        return Ifpaid_pay;
    }

    public void setIfpaid_pay(boolean ifpaid_pay) {
        Ifpaid_pay = ifpaid_pay;
    }

    public String getRegister_ID() {
        return Register_ID;
    }

    public void setRegister_ID(String register_ID) {
        Register_ID = register_ID;
    }

    public double getPayment_amount() {
        return Payment_amount;
    }

    public void setPayment_amount(double payment_amount) {
        Payment_amount = payment_amount;
    }

}
