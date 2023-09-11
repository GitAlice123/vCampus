package view.Login;

import javax.swing.*;
import java.awt.*;

public class RegisterUI extends JFrame {
    JLabel titleLabel = new JLabel("用户注册", JLabel.CENTER);//位于边界布局的北边
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);//其余组件在边界布局中间的JPanel中
    JLabel userNameLabel = new JLabel("用户名");//用户名标签
    JTextField userNameTxt = new JTextField();//用户名输入框
    JLabel pwdLabel = new JLabel("密码");//密码标签
    JPasswordField pwdField = new JPasswordField();//密码输入框
    JLabel ensurepwdLabel = new JLabel("再次确认密码");//再次确认密码标签
    JPasswordField ensurepwdField = new JPasswordField();//再次确认密码输入框
    JRadioButton studentRadioButton = new JRadioButton("学生");//学生登录单选按钮
    JRadioButton adminRadioButton = new JRadioButton("管理员");//管理员登录单选按钮
    JRadioButton teacherRadioButton = new JRadioButton("老师");//老师登录单选按钮
    JButton registerBtn = new JButton("注册");//注册按钮
    JButton backBtn = new JButton("返回");//返回按钮
    ButtonGroup group = new ButtonGroup();//创建按钮组

    RegisterHandler registerHandler;

    public RegisterUI() {
        super("虚拟校园系统");
        //JFrame jFrame=new JFrame();
        registerHandler = new RegisterHandler(this);

        Container contentPane = getContentPane();//获取控制面板


        titleLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));//设置标题大小、字体
        titleLabel.setPreferredSize(new Dimension(0, 80));//设置标题宽（0为自动填充）高（80像素）
        Font centerFont = new Font("楷体", Font.PLAIN, 20);//设置中间组件的文字大小、字体
        userNameLabel.setFont(centerFont);
        userNameTxt.setPreferredSize(new Dimension(200, 30));//设置输入框大小
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200, 30));//设置输入框大小
        ensurepwdLabel.setFont(centerFont);
        ensurepwdField.setPreferredSize(new Dimension(200, 30));
        studentRadioButton.setFont(centerFont);
        adminRadioButton.setFont(centerFont);
        registerBtn.setFont(centerFont);
        backBtn.setFont(centerFont);
        teacherRadioButton.setFont(centerFont);


        //把组件加入控制面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userNameTxt);

        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(ensurepwdLabel);
        centerPanel.add(ensurepwdField);
        studentRadioButton.addActionListener(registerHandler);
        centerPanel.add(studentRadioButton);
        adminRadioButton.addActionListener(registerHandler);
        centerPanel.add(adminRadioButton);
        registerBtn.addActionListener(registerHandler);
        backBtn.addActionListener(registerHandler);
        teacherRadioButton.addActionListener(registerHandler);
        centerPanel.add(teacherRadioButton);
        group.add(adminRadioButton);
        group.add(studentRadioButton);
        group.add(teacherRadioButton);
        //增加按键事件
        registerBtn.addKeyListener(registerHandler);
        centerPanel.add(registerBtn);
        centerPanel.add(backBtn);
        //弹簧布局
        //布局userNameLabel，水平居中，北边与边界布局中间一块顶部距离20
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userNameTxt)), Spring.constant(20));
        //  userNameLabel长度+userNameTxt长度+20
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, 20, SpringLayout.NORTH, centerPanel);
        //userNameTxt西边与userNameLabel东边距离20，userNameTxt北边与userNameLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST, userNameTxt, 20, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameTxt, 0, SpringLayout.NORTH, userNameLabel);
        //pwdLabel东边与userNameLabel平齐，pwdLabel北边与userNameLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, userNameLabel);
        //pwdTxt西边与pwdLabel东边距离20，pwdTxt北边与pwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
        //ensurepwdLabel东边与pwdLabel平齐，ensurepwdLabel北边与pwdLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST, ensurepwdLabel, 0, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, ensurepwdLabel, 20, SpringLayout.SOUTH, pwdLabel);
        //ensurepwdTxt西边雨ensurepwdLabel东边距离20，ensurepwdTxt与ensurepwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST, ensurepwdField, 20, SpringLayout.EAST, ensurepwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, ensurepwdField, 0, SpringLayout.NORTH, ensurepwdLabel);
        //布局studentRadioButton和adminRadioButton
        Spring childWidth2 = Spring.sum(Spring.sum(Spring.width(studentRadioButton), Spring.width(adminRadioButton)),
                Spring.constant(30));
        int offsetX2 = childWidth2.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, studentRadioButton, -offsetX2 - 20, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, studentRadioButton, 70, SpringLayout.SOUTH, pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, adminRadioButton, offsetX2 - 20, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, adminRadioButton, 70, SpringLayout.SOUTH, pwdLabel);
        //布局registerBtn
        Spring childWidth3 = Spring.sum(Spring.sum(Spring.width(backBtn), Spring.width(registerBtn)),
                Spring.constant(30));
        int offsetX3 = childWidth3.getValue() / 2;
        springLayout.putConstraint(SpringLayout.NORTH, registerBtn, 40, SpringLayout.NORTH, studentRadioButton);
        springLayout.putConstraint(SpringLayout.WEST, registerBtn, -offsetX3 - 20, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, backBtn, 40, SpringLayout.NORTH, adminRadioButton);
        springLayout.putConstraint(SpringLayout.WEST, backBtn, offsetX3 - 20, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.WEST, teacherRadioButton, -15, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, teacherRadioButton, 70, SpringLayout.SOUTH, pwdLabel);


        contentPane.add(titleLabel, BorderLayout.NORTH);//把标题放在边界布局北边一块
        contentPane.add(centerPanel, BorderLayout.CENTER);//把centerPanel放在边界布局中间一块

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

    public static void main(String[] args) {
        new RegisterUI();
    }

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    public JPasswordField getPwdField() {
        return pwdField;
    }

    public JPasswordField getEnsurepwdField() {
        return ensurepwdField;
    }
}
