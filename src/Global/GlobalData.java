package view.Global;

import view.Global.SummaryUI;
import view.Login.logInUI;
import view.Bank.BankTeacherStudentUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import javax.swing.*;
/**
 * 全局数据类，用于存储用户登录信息。
 */
public class GlobalData {
    private static String uID="213213000";//用户一卡通号
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

    /**
     * 捕获double类型的输入信息
     */
    public void actionPerformed(JTextField f) {
        String Text = f.getText(); // 获取文本字段中的内容
        try {
            double amount = Double.parseDouble(Text); // 将文本转换为 double 类型
            System.out.println("用户输入的金额为: " + amount);
            // 在这里可以进行进一步的处理
        } catch (NumberFormatException ex) {
            System.out.println("请输入有效的数值");
            // 处理无效的输入
        }
    }
}