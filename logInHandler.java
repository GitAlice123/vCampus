package src.main.handler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.logIn.logInUI;

import javax.swing.*;

public class logInHandler extends KeyAdapter implements ActionListener{
 private logInUI loginView;
 private boolean radioButtonSelected = false;
 public logInHandler(logInUI loginView){

     this.loginView=loginView;
 }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() instanceof JRadioButton) {
            JRadioButton jRadioButton = (JRadioButton) e.getSource();
            String text = jRadioButton.getText();
            if ("学生".equals(text) || "管理员".equals(text)) {
                radioButtonSelected = true;
            }
            else {
                radioButtonSelected = false;
            }
        } else if (e.getSource() instanceof JButton) {
            if (!radioButtonSelected) {
                // 当单选按钮未被选择时，不执行后续的操作
                JOptionPane.showMessageDialog(loginView, "请先选择单选按钮");
                return;
            }

            // 执行按钮操作
            JButton jButton = (JButton) e.getSource();
            String text2 = jButton.getText();
            if ("登录".equals(text2)) {
                extracted();
            } else if ("注册".equals(text2)) {

            }
        }
}

    private void extracted() {
        String user=loginView.getUserNameTxt().getText();
        if(user.length()!=9){
            JOptionPane.showMessageDialog(loginView,"请输入9位用户名！");
            loginView.getUserNameTxt().setText("");
            loginView.getPwdField().setText("");
        }
        char[] chars=loginView.getPwdField().getPassword();
        String pwd=new String(chars);
        System.out.println(user+":"+pwd);
        //查询数据库，给flag值
        boolean flag=false;
        if(flag){

        }
        else {
         //JOptionPane.showMessageDialog(loginView,"用户名或密码错误！");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e.getKeyCode();
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            extracted();
        }
    }
}
