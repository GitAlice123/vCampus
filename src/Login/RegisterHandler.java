package view.Login;

import view.DAO.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterHandler extends KeyAdapter implements ActionListener {
    private RegisterUI registerView;
    private boolean radioButtonSelected = false;

    public RegisterHandler(RegisterUI registerView) {
        this.registerView = registerView;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JRadioButton) {
            radioButtonSelected = true;
        } else if (e.getSource() instanceof JButton) {
            JButton jButton = (JButton) e.getSource();
            String text2 = jButton.getText();
            // 执行按钮操作
            if("返回".equals(text2)){
                registerView.dispose();
                new logInUI();
            }
            else if (!radioButtonSelected) {
                // 当单选按钮未被选择时，不执行后续的操作
                JOptionPane.showMessageDialog(registerView, "请先选择单选按钮");
                return;
            }else if ("注册".equals(text2)) {
                extracted();
            }

        }
    }

    private void extracted(){
        String userId=registerView.getUserNameTxt().getText();
        String password=registerView.getPwdField().getText();
        String ensurepsd=registerView.getEnsurepwdField().getText();
        if(userId.length()!=9){
            JOptionPane.showMessageDialog(registerView,"请输入9位用户名！");
            registerView.getUserNameTxt().setText("");
            registerView.getPwdField().setText("");
            registerView.getEnsurepwdField().setText("");
        }

        else if(password.length()==0)
        {
            JOptionPane.showMessageDialog(registerView,"请输入密码！");
        }
        //判断两次密码输入是否一致
        else if(!(password.equals(ensurepsd))){
            JOptionPane.showMessageDialog(registerView,"两次密码输入不一致！");
        }
        /* TODO:改成用后端访问数据库 */
        else {
            UserDao userdao = new UserDao();
            if(userdao.findUserByuId(userId)!=null){
                JOptionPane.showMessageDialog(registerView, "该用户已存在！");
                return;
            }else{
                User user = new User();
                user.setuId(userId);
                user.setuPwd(password);
                if(registerView.studentRadioButton.isSelected())
                    user.setuRole("ST");
                else if(registerView.teacherRadioButton.isSelected())
                    user.setuRole("TC");
                else if(registerView.adminRadioButton.isSelected())
                    user.setuRole("AD");

                userdao.CreateUser(user);//创建新用户
                JOptionPane.showMessageDialog(registerView, "新用户注册成功！");
                //连接数据库 创建新的用户
            }

        }

        char[] chars=registerView.getPwdField().getPassword();
        String pwd=new String(chars);
        System.out.println(userId+":"+pwd+":"+ensurepsd);
    }

    public void keyPressed(KeyEvent e) {
        e.getKeyCode();
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            extracted();
        }
    }
}
