package view.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.DAO.UserDao;


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
                extracted();
            }
        }

    }

    private void extracted() {
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



        //查询数据库，给flag值
        boolean flag=false;

        UserDao userdao = new UserDao();
        User user = userdao.findUserByuId(userId);

        String role = "";
        if(loginView.studentRadioButton.isSelected())
            role = "ST";
        else if(loginView.teacherRadioButton.isSelected())
            role = "TC";
        else if(loginView.adminRadioButton.isSelected())
            role = "AD";

        if(user != null && user.getuPwd().equals(password) && user.getuRole().equals(role)){
            flag = true;
        }//判断登录信息是否正确


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
            extracted();
        }
    }
}
