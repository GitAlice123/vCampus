package view.message;

/**
 * BankMoneyMessage 类用于表示充值和消费操作的银行金额消息。1001
 */
public class BankMoneyMessage {
    private String id;     // 一卡通号
    private double money;  // 充值或消费的金额
    private String pwd;    // 密码

    public BankMoneyMessage(){

    }

    /**
     * 构造一个 BankMoneyMessage 对象。
     *
     * @param id    一卡通号
     * @param money 充值或消费的金额
     * @param pwd   密码
     */
    public BankMoneyMessage(String id, double money, String pwd) {
        this.id = id;
        this.money = money;
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
     * 获取充值或消费的金额。
     *
     * @return 充值或消费的金额
     */
    public double getMoney() {
        return money;
    }

    /**
     * 设置充值或消费的金额。
     *
     * @param money 充值或消费的金额
     */
    public void setMoney(double money) {
        this.money = money;
    }

    /**
     * 获取密码。
     *
     * @return 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * 设置密码。
     *
     * @param pwd 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
