package view.message;

import view.Bank.*;

/**
 * BankBillMessage 类表示银行账单消息，包含一卡通号、账单信息和用户输入的密码。1002
 */
public class BankBillMessage {
    private String id;        // 一卡通号
    private bankBill bill;    // 账单信息
    private String pwd;       // 用户输入的密码

    /**
     * 无参构造函数，创建一个 BankBillMessage 的实例，使用默认值初始化属性。
     */
    public BankBillMessage() {
        this.id = "";
        this.bill = null;
        this.pwd = "";
    }

    /**
     * 有参构造函数，创建一个 BankBillMessage 的实例，使用指定的一卡通号、账单信息和密码初始化属性。
     *
     * @param id   一卡通号
     * @param bill 账单信息
     * @param pwd  用户输入的密码
     */
    public BankBillMessage(String id, bankBill bill, String pwd) {
        this.id = id;
        this.bill = bill;
        this.pwd = pwd;
    }

    /**
     * 获取一卡通号。
     *
     * @return 一卡通号
     */
    public String getId() {
        return id;
    }

    /**
     * 设置一卡通号。
     *
     * @param id 一卡通号
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取账单信息。
     *
     * @return 账单信息
     */
    public bankBill getBill() {
        return bill;
    }

    /**
     * 设置账单信息。
     *
     * @param bill 账单信息
     */
    public void setBill(bankBill bill) {
        this.bill = bill;
    }

    /**
     * 获取用户输入的密码。
     *
     * @return 用户输入的密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置用户输入的密码。
     *
     * @param pwd 用户输入的密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
