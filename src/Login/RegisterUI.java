package view.Login;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.*;
public class RegisterUI extends JFrame {
    JLabel titleLabel=new JLabel("用户注册");//位于边界布局的北边
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout){
        /**
         * @param g the <code>Graphics</code> object to protect
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Register.png");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };//其余组件在边界布局中间的JPanel中
    JLabel userNameLabel=new JLabel("用户名");//用户名标签
    JTextField userNameTxt=new JTextField();//用户名输入框
    JLabel nameLabel=new JLabel("姓名");
    JTextField nameField=new JTextField();
    JLabel pwdLabel=new JLabel("密码");//密码标签
    JPasswordField pwdField=new JPasswordField();//密码输入框
    JLabel ensurepwdLabel=new JLabel("再次确认密码");//再次确认密码标签
    JPasswordField ensurepwdField=new JPasswordField();//再次确认密码输入框
    JRadioButton studentRadioButton=new JRadioButton("学生");//学生登录单选按钮
    JRadioButton adminRadioButton=new JRadioButton("管理员");//管理员登录单选按钮
    JRadioButton teacherRadioButton=new JRadioButton("老师");//老师登录单选按钮
    JButton registerBtn=new JButton("注册");//注册按钮
    JButton backBtn=new JButton("返回");//返回按钮
    ButtonGroup group=new ButtonGroup();//创建按钮组



    RegisterHandler registerHandler;

    /**
     * 构造注册页面的UI
     */
    public RegisterUI(){
        super("虚拟校园系统");
        //JFrame jFrame=new JFrame();
        registerHandler=new RegisterHandler(this);

        Container contentPane=getContentPane();//获取控制面板
        // 设置图标背景
        //TODO
//        URL resource =this.getClass().getClassLoader().getResource("SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //getContentPane().add(lblBackground); // 将组件添加到面板中

        JLabel jl1=new JLabel();
        ImageIcon img1 = new ImageIcon("Images/logo_white.png");//创建图片对象
        jl1.setIcon(img1);

        centerPanel.add(jl1);

        springLayout.putConstraint(SpringLayout.WEST,jl1,950,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,jl1,20,SpringLayout.NORTH,centerPanel);

        JLabel jl2=new JLabel();
        ImageIcon img2 = new ImageIcon("Images/seu_logo.png");//创建图片对象
        jl2.setIcon(img2);

        centerPanel.add(jl2);

        springLayout.putConstraint(SpringLayout.WEST,jl2,80,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,jl2,150,SpringLayout.NORTH,centerPanel);

        titleLabel.setFont(new Font("华文行楷",Font.PLAIN,40));//设置标题大小、字体
        titleLabel.setPreferredSize(new Dimension(0,80));//设置标题宽（0为自动填充）高（80像素）
        Font centerFont=new Font("楷体",Font.PLAIN,30);//设置中间组件的文字大小、字体
        userNameLabel.setFont(centerFont);
        userNameTxt.setPreferredSize(new Dimension(200,30));//设置输入框大小
        userNameTxt.setFont(new Font("楷体",Font.PLAIN,20));
        nameLabel.setFont(centerFont);
        nameField.setPreferredSize(new Dimension(200,30));
        nameField.setFont(new Font("楷体",Font.PLAIN,20));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));//设置输入框大小
        pwdField.setFont(new Font("楷体",Font.PLAIN,20));
        ensurepwdLabel.setFont(centerFont);
        ensurepwdField.setPreferredSize(new Dimension(200,30));
        ensurepwdField.setFont(new Font("楷体",Font.PLAIN,20));
        studentRadioButton.setFont(centerFont);
        adminRadioButton.setFont(centerFont);
        teacherRadioButton.setFont(centerFont);
        registerBtn.setFont(centerFont);
        backBtn.setFont(centerFont);

        //把组件加入控制面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userNameTxt);
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        centerPanel.add(ensurepwdLabel);
        centerPanel.add(ensurepwdField);
        studentRadioButton.addActionListener(registerHandler);
        centerPanel.add(studentRadioButton);
        adminRadioButton.addActionListener(registerHandler);
        centerPanel.add(adminRadioButton);
        teacherRadioButton.addActionListener(registerHandler);
        centerPanel.add(teacherRadioButton);
        registerBtn.addActionListener(registerHandler);
        backBtn.addActionListener(registerHandler);
        group.add(adminRadioButton);
        group.add(studentRadioButton);
        group.add(teacherRadioButton);
        //增加按键事件
        registerBtn.addKeyListener(registerHandler);
        centerPanel.add(registerBtn);
        centerPanel.add(backBtn);
        centerPanel.add(titleLabel);
        //弹簧布局
        //布局userNameLabel，水平居中，北边与边界布局中间一块顶部距离20
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,150,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,350,SpringLayout.NORTH,centerPanel);
        //userNameTxt西边与userNameLabel东边距离20，userNameTxt北边与userNameLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST,userNameTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameTxt,0,SpringLayout.NORTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,nameLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,20,SpringLayout.SOUTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,nameField,0,SpringLayout.EAST,userNameTxt);
        springLayout.putConstraint(SpringLayout.NORTH,nameField,0,SpringLayout.NORTH,nameLabel);
        //pwdLabel东边与userNameLabel平齐，pwdLabel北边与userNameLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,nameLabel);
        //pwdTxt西边与pwdLabel东边距离20，pwdTxt北边与pwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST,pwdField,20,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //ensurepwdLabel东边与pwdLabel平齐，ensurepwdLabel北边与pwdLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST,ensurepwdLabel,0,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ensurepwdLabel,20,SpringLayout.SOUTH,pwdLabel);
        //ensurepwdTxt西边雨ensurepwdLabel东边距离20，ensurepwdTxt与ensurepwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST,ensurepwdField,20,SpringLayout.EAST,ensurepwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ensurepwdField,0,SpringLayout.NORTH,ensurepwdLabel);
        //布局studentRadioButton和adminRadioButton
        springLayout.putConstraint(SpringLayout.WEST,studentRadioButton,100,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,studentRadioButton,100,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,teacherRadioButton,20,SpringLayout.EAST,studentRadioButton);
        springLayout.putConstraint(SpringLayout.NORTH,teacherRadioButton,100,SpringLayout.SOUTH,pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,adminRadioButton,20,SpringLayout.EAST,teacherRadioButton);
        springLayout.putConstraint(SpringLayout.NORTH,adminRadioButton,100,SpringLayout.SOUTH,pwdLabel);
        //布局registerBtn
        springLayout.putConstraint(SpringLayout.NORTH,registerBtn,80,SpringLayout.NORTH,studentRadioButton);
        springLayout.putConstraint(SpringLayout.WEST,registerBtn,140,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,backBtn,0,SpringLayout.NORTH,registerBtn);
        springLayout.putConstraint(SpringLayout.WEST,backBtn,60,SpringLayout.EAST,registerBtn);



        contentPane.add(centerPanel);//把centerPanel放在边界布局中间一块

        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

    /**
     * @return 用户名的文本信息
     */
    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    /**
     * @return 用户名的文本信息
     */
    public JTextField getNameTxt() {
        return nameField;
    }

    /**
     * @return 密码的文本信息
     */
    public JPasswordField getPwdField() {
        return pwdField;
    }

    /**
     * @return 再次确认密码的文本信息
     */
    public JPasswordField getEnsurepwdField(){return ensurepwdField;}

    /**
     * @param args
     */
    public static void main(String[] args){
        try {
            // 设置外观为Windows外观
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            UIManager.put("nimbusBase", new Color(255, 255, 50)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(173, 216, 230)); // 按钮
            UIManager.put("control", new Color(240, 248, 255)); // 背景



        } catch (Exception e) {
            e.printStackTrace();
        }
        new RegisterUI();
    }
}
