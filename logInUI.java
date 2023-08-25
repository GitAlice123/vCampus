package src.logIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.PlainDocument;
import src.main.handler.logInHandler;
public class logInUI extends JFrame{
    JLabel titleLabel=new JLabel("用户登录",JLabel.CENTER);//位于边界布局的北边
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);//其余组件在边界布局中间的JPanel中
    JLabel userNameLabel=new JLabel("用户名");//用户名标签
    JTextField userNameTxt=new JTextField();//用户名输入框


    JLabel pwdLabel=new JLabel("密码");//密码标签
    JPasswordField pwdField=new JPasswordField();//密码输入框
    //JLabel studentLabel=new JLabel("学生");
    JRadioButton studentRadioButton=new JRadioButton("学生");//学生登录单选按钮
    //JLabel adminLabel=new JLabel("管理员");
    JRadioButton adminRadioButton=new JRadioButton("管理员");//管理员登录单选按钮
    JButton logInBtn=new JButton("登录");//登录按钮
    JButton registerBtn=new JButton("注册");//注册按钮
    ButtonGroup group=new ButtonGroup();//创建按钮组
    //    SystemTray systemTray;//定义系统托盘
//    TrayIcon trayIcon;//定义托盘

   logInHandler loginHandler;
    public logInUI(){
        super("虚拟校园系统");
        JFrame jFrame=new JFrame();
        loginHandler=new logInHandler(this);

        Container contentPane=getContentPane();//获取控制面板

        titleLabel.setFont(new Font("华文行楷",Font.PLAIN,40));//设置标题大小、字体
        titleLabel.setPreferredSize(new Dimension(0,80));//设置标题宽（0为自动填充）高（80像素）
        Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
        userNameLabel.setFont(centerFont);
        userNameTxt.setPreferredSize(new Dimension(200,30));//设置输入框大小
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));//设置输入框大小
        studentRadioButton.setFont(centerFont);
        adminRadioButton.setFont(centerFont);
        logInBtn.setFont(centerFont);
        registerBtn.setFont(centerFont);

        //把组件加入控制面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userNameTxt);

        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        studentRadioButton.addActionListener(loginHandler);
        centerPanel.add(studentRadioButton);
        adminRadioButton.addActionListener(loginHandler);
        centerPanel.add(adminRadioButton);
        logInBtn.addActionListener(loginHandler);
        group.add(adminRadioButton);
        group.add(studentRadioButton);
        //增加按键事件
        logInBtn.addKeyListener(loginHandler);
        centerPanel.add(logInBtn);
        registerBtn.addActionListener(loginHandler);
        centerPanel.add(registerBtn);

        //弹簧布局
        //布局userNameLabel，水平居中，北边与边界布局中间一块顶部距离20
        Spring childWidth=Spring.sum(Spring.sum(Spring.width(userNameLabel),Spring.width(userNameTxt)),Spring.constant(20));
        //  userNameLabel长度+userNameTxt长度+20
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        //userNameTxt西边与userNameLabel东边距离20，userNameTxt北边与userNameLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST,userNameTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameTxt,0,SpringLayout.NORTH,userNameLabel);
        //pwdLabel东边与userNameLabel平齐，pwdLabel北边与userNameLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        //pwdTxt西边与pwdLabel东边距离20，pwdTxt北边与pwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //布局studentRadioButton和adminRadioButton
        Spring childWidth2=Spring.sum(Spring.sum(Spring.width(studentRadioButton),Spring.width(adminRadioButton)),
                Spring.constant(30));
        int offsetX2=childWidth2.getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,studentRadioButton,-offsetX2-20,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,studentRadioButton,40,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,adminRadioButton,offsetX2-20,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,adminRadioButton,40,SpringLayout.SOUTH,pwdLabel);
        //布局logInBtn和registerBtn
        Spring childWidth3=Spring.sum(Spring.sum(Spring.width(logInBtn),Spring.width(registerBtn)),
                Spring.constant(30));
        int offsetX3=childWidth3.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,logInBtn,70,SpringLayout.NORTH,studentRadioButton);
        springLayout.putConstraint(SpringLayout.WEST,logInBtn,-offsetX3-20,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,registerBtn,70,SpringLayout.NORTH,adminRadioButton);
        springLayout.putConstraint(SpringLayout.WEST,registerBtn,offsetX3-20,SpringLayout.HORIZONTAL_CENTER,centerPanel);


        contentPane.add(titleLabel,BorderLayout.NORTH);//把标题放在边界布局北边一块
        contentPane.add(centerPanel,BorderLayout.CENTER);//把centerPanel放在边界布局中间一块

//        if(SystemTray.isSupported()){//判断是否支持系统托盘
//            systemTray=SystemTray.getSystemTray();//初始化系统托盘
//
//        }

//设置登录按钮为默认按钮
        getRootPane().setDefaultButton(logInBtn);

//        String resource=getClass().getResource("/Images/登录.png").toString();
//        Image image=new Image(resource);
//        URL resource=logInUI.class.getClassLoader().getResource("/Images/登录.png");
//        Image image=new ImageIcon(resource).getImage();
//        jFrame.setIconImage(image);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

    public JTextField getUserNameTxt() {
        return userNameTxt;
    }


    public JPasswordField getPwdField() {
        return pwdField;
    }

    public static void main(String[] args){

        new logInUI();
    }
}
