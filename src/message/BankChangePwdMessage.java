package view.message;

/**
 * BankChangePwdMessage 类用于表示修改密码操作的银行消息。1003
 */
public class BankChangePwdMessage {
    private String id;      // 一卡通号
    private String oldPwd;  // 旧密码
    private String newPwd;  // 新密码
    private String newNewPwd;//确认新密码

    /**
     * 构造一个 BankChangePwdMessage 对象。
     */
    public BankChangePwdMessage() {
    }

    /**
     * 构造一个 BankChangePwdMessage 对象。
     *
     * @param id        一卡通号
     * @param oldPwd    旧密码
     * @param newPwd    新密码
     * @param newNewPwd 确认新密码
     */
    public BankChangePwdMessage(String id, String oldPwd, String newPwd, String newNewPwd) {
        this.id = id;
        this.oldPwd = oldPwd;
        this.newPwd = newPwd;
        this.newNewPwd = newNewPwd;
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
     * 获取旧密码。
     *
     * @return 旧密码
     */
    public String getOldPwd() {
        return oldPwd;
    }

    /**
     * 设置旧密码。
     *
     * @param oldPwd 旧密码
     */
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    /**
     * 获取新密码。
     *
     * @return 新密码
     */
    public String getNewPwd() {
        return newPwd;
    }

    /**
     * 设置新密码。
     *
     * @param newPwd 新密码
     */
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    /**
     * 获取确认的新密码。
     *
     * @return 新密码
     */
    public String getnewNewPwd() {
        return newNewPwd;
    }


}
