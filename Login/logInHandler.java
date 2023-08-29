package view;

import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.connect.LoginClientAPI;
import view.connect.LoginClientAPIImpl;
import view.connect.LoginMessage;

import javax.swing.*;

public class logInHandler extends KeyAdapter implements ActionListener{
    private logInUI loginView;
    private boolean radioButtonSelected = false;
    public logInHandler(logInUI loginView){

        this.loginView=loginView;
    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() instanceof JRadioButton) {
            radioButtonSelected = true;
        } else if (e.getSource() instanceof JButton) {
            JButton jButton = (JButton) e.getSource();
            String text2 = jButton.getText();
            // 执行按钮操作
            if ("注册".equals(text2)) {
                loginView.dispose();
                new RegisterUI();
            }
            else if (!radioButtonSelected) {
                // 当单选按钮未被选择时，不执行后续的操作
                JOptionPane.showMessageDialog(loginView, "请先选择单选按钮");
                return;
            }
            if ("登录".equals(text2)) {
                try {
                    extracted();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }

    private void extracted() throws IOException {
        String userId=loginView.getUserNameTxt().getText();
        String password=loginView.getPwdField().getText();
        if(userId.length()!=9){
            JOptionPane.showMessageDialog(loginView,"请输入9位用户名！");
            loginView.getUserNameTxt().setText("");
            loginView.getPwdField().setText("");
            return;
        }

        else if(password.length()==0)
        {
            JOptionPane.showMessageDialog(loginView,"请输入密码！");
        }


        /*以下更改为使用后端接口访问服务器*/
        //查询数据库，给flag值
        boolean flag=false;


        String role = "";
        if(loginView.studentRadioButton.isSelected())
            role = "ST";
        else if(loginView.teacherRadioButton.isSelected())
            role = "TC";
        else if(loginView.adminRadioButton.isSelected())
            role = "AD";


        // 创建 LoginClientAPI 的实例，可以是接口的任何实现类
        LoginClientAPI loginClientAPI = new LoginClientAPIImpl("localhost", 8888);
        LoginMessage login_message = new LoginMessage("login",userId,password,role);

        // 调用接口方法
        boolean resultByte = loginClientAPI.loginByUserId(login_message);

        // 处理结果
        flag = (resultByte == true); // 如果接收到的字节为 1，结果为 true；否则结果为 false

/*与后端连接部分修改结束*/
        if(flag){
            JOptionPane.showMessageDialog(loginView,"登录成功！");
        }
        else {
            JOptionPane.showMessageDialog(loginView,"用户名或密码或用户类型错误！");
        }

        System.out.println(userId+":"+password+":"+role);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.getKeyCode();
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            try {
                extracted();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
