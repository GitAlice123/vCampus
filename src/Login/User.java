package view.Login;

/**
 * 用户类，用于表示系统中的用户信息。
 */
public class User {
    private String uId;
    private String uPwd;
    private String uRole;

    /**
     * 获取用户ID。
     *
     * @return 用户ID。
     */
    public String getuId() {
        return uId;
    }

    /**
     * 设置用户ID。
     *
     * @param uId 用户ID。
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * 获取用户密码。
     *
     * @return 用户密码。
     */
    public String getuPwd() {
        return uPwd;
    }


    /**
     * 设置用户密码。
     *
     * @param uPwd 用户密码。
     */
    public void setuPwd(String uPwd) {
        this.uPwd = uPwd;
    }

    /**
     * 获取用户角色。
     *
     * @return 用户角色。
     */
    public String getuRole() {
        return uRole;
    }

    /**
     * 设置用户角色。
     *
     * @param uRole 用户角色。
     */
    public void setuRole(String uRole) {
        this.uRole = uRole;
    }

    /**
     * 获取用户的字符串表示形式，用于调试和日志记录。
     *
     * @return 用户的字符串表示形式。
     */
    @Override
    public String toString() {
        return "User [uId=" + uId + ", uPwd=" + uPwd + ", uRole=" + uRole + "]";
    }

    /**
     * 创建一个新的用户对象。
     *
     * @param uId   用户ID。
     * @param uPwd  用户密码。
     * @param uRole 用户角色。
     */
    public User(String uId, String uPwd, String uRole) {
        this.uId = uId;
        this.uPwd = uPwd;
        this.uRole = uRole;
    }

    /**
     * 创建一个空的用户对象。
     */
    public User() {
    }
}