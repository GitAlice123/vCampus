package view.message;

/**
 * 该类可用于bank的changeLoss功能；1000
 */
public class BankIDMessage {
    private String id; // 一卡通号
    private String pwd;//密码

    /**
     * 无参构造函数。
     */
    public BankIDMessage() {
    }

    /**
     * 有参构造函数。
     *
     * @param id 一卡通号
     */
    public BankIDMessage(String id, String p) {
        this.id = id;
        this.pwd = p;
    }

    /**
     * 有参构造函数。
     *
     * @param id 一卡通号
     */
    public BankIDMessage(String id) {
        this.id = id;
        this.pwd = "";
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
     * 获取密码
     *
     * @return 密码
     */
    public String getPwd() {
        return pwd;
    }
}
