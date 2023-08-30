package view.Global;

/**
 * 全局数据类，用于存储用户登录信息。
 */
public class GlobalData {
    private static String uID;//用户一卡通号
    private static int uType;//用户类型，1表示学生，2表示教师，3表示管理员

    /**
     * 设置用户登录信息。
     *
     * @param userID   用户一卡通号
     * @param userType 用户类型，1表示学生，2表示教师，3表示管理员
     */
    public static void setUser(String userID, int userType) {
        uID = userID;
        uType = userType;
    }

    /**
     * 获取当前登录用户的一卡通号。
     *
     * @return 用户一卡通号
     */
    public static String getUID() {
        return uID;
    }

    /**
     * 获取当前登录用户的用户类型。
     *
     * @return 用户类型，1表示学生，2表示教师，3表示管理员
     */
    public static int getUType() {
        return uType;
    }

    /**
     * 注销当前登录用户，清空用户登录信息。
     */
    public static void logout() {
        uID = null;
        uType = 0;
    }
}