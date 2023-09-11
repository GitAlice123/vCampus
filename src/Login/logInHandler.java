package view.Login;

import view.Global.GlobalData;
import view.Global.SummaryUI;
import view.connect.LoginClientAPI;
import view.connect.LoginClientAPIImpl;
import view.message.LoginMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * logInHandler 类是登录界面的事件处理程序，实现了 ActionListener 和 KeyAdapter 接口。
 */
public class logInHandler extends KeyAdapter implements ActionListener {
    private logInUI loginView;               // 登录界面的视图对象
    private boolean radioButtonSelected = false;    // 单选按钮是否被选中的标志

    /**
     * 构造一个 logInHandler 对象。
     *
     * @param loginView 登录界面的视图对象
     */
    public logInHandler(logInUI loginView) {
        this.loginView = loginView;
    }

    /**
     * 处理事件的方法，根据事件源的不同进行相应的操作。
     *
     * @param e 事件对象
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            radioButtonSelected = true;    // 单选按钮被选中
        } else if (e.getSource() instanceof JButton) {
            JButton jButton = (JButton) e.getSource();
            String text2 = jButton.getText();
            // 执行按钮操作
            if ("注册".equals(text2)) {
                loginView.dispose();    // 关闭登录界面
                new RegisterUI();       // 打开注册界面
            } else if (!radioButtonSelected) {
                // 当单选按钮未被选择时，不执行后续的操作
                JOptionPane.showMessageDialog(loginView, "请先选择单选按钮");
                return;
            }
            if ("登录".equals(text2)) {
                try {
                    extracted();    // 执行登录操作
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    /**
     * 提取的方法，用于执行登录操作。
     *
     * @throws IOException 如果在登录过程中发生 I/O 错误
     */
    private void extracted() throws IOException {
        String userId = loginView.getUserNameTxt().getText();    // 获取用户名
        String password = loginView.getPwdField().getText();     // 获取密码
        if (userId.length() != 9) {
            JOptionPane.showMessageDialog(loginView, "请输入9位用户名！");
            loginView.getUserNameTxt().setText("");
            loginView.getPwdField().setText("");
            return;
        } else if (password.length() == 0) {
            JOptionPane.showMessageDialog(loginView, "请输入密码！");
        }

        /*以下更改为使用后端接口访问服务器*/
        // 查询数据库，给flag值
        boolean flag = false;

        String role = "";
        int typeNum = 0;
        if (loginView.studentRadioButton.isSelected()) {
            role = "ST";
            typeNum = 1;
        } else if (loginView.teacherRadioButton.isSelected()) {
            role = "TC";
            typeNum = 2;
        } else if (loginView.adminRadioButton.isSelected()) {
            role = "AD";
            typeNum = 3;
        }
        // 创建 LoginClientAPI 的实例，可以是接口的任何实现类
        LoginClientAPI loginClientAPI = new LoginClientAPIImpl("localhost", 8888);
        LoginMessage login_message = new LoginMessage(userId, password, role);

        // 调用接口方法
        boolean resultByte = loginClientAPI.loginByUserId(login_message);

        // 处理结果
        flag = (resultByte == true);    // 如果接收到的字节为 1，结果为 true；否则结果为 false

        /*与后端连接部分修改结束*/
        if (flag) {
            JOptionPane.showMessageDialog(loginView, "登录成功！");
            GlobalData.setUser(userId, typeNum);
            loginView.dispose();
            new SummaryUI();//跳转页面的判断逻辑待写
            System.out.println("总界面");
        } else {
            JOptionPane.showMessageDialog(loginView, "用户名或密码或用户类型错误！");
        }

        System.out.println(userId + ":" + password + ":" + role);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.getKeyCode();
        if (KeyEvent.VK_ENTER == e.getKeyCode()) {
            try {
                extracted();    // 执行登录操作
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}