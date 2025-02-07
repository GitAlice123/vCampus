package view.Login;

import view.Global.GlobalData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * 登录界面
 */
public class logInUI extends JFrame {
    JLabel titleLabel = new JLabel("用户登录");//位于边界布局的北边
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);//其余组件在边界布局中间的JPanel中
    JLabel userNameLabel = new JLabel("用户名");//用户名标签
    JTextField userNameTxt = new JTextField();//用户名输入框


    JLabel pwdLabel = new JLabel("密码");//密码标签
    JPasswordField pwdField = new JPasswordField();//密码输入框
    //JLabel studentLabel=new JLabel("学生");
    JRadioButton studentRadioButton = new JRadioButton("学生");//学生登录单选按钮
    //JLabel adminLabel=new JLabel("管理员");
    JRadioButton adminRadioButton = new JRadioButton("管理员");//管理员登录单选按钮
    JRadioButton teacherRadioButton = new JRadioButton("老师");//老师登录单选按钮
    JButton logInBtn = new JButton("登录");//登录按钮
    JButton registerBtn = new JButton("注册");//注册按钮
    ButtonGroup group = new ButtonGroup();//创建按钮组
    //    SystemTray systemTray;//定义系统托盘
//    TrayIcon trayIcon;//定义托盘

    logInHandler loginHandler;
    JPanel backgroundPanel = new JPanel(springLayout) {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/640.jfif");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };

    /**
     * 登录界面，用于用户登录虚拟校园系统。
     *
     * @throws UnsupportedLookAndFeelException 如果外观不支持。
     * @throws ClassNotFoundException        如果找不到指定类。
     * @throws InstantiationException        如果无法实例化类。
     * @throws IllegalAccessException        如果无法访问类。
     */
    public logInUI() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        super("虚拟校园系统");
        // 设置外观为Windows外观
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        UIManager.put("nimbusBase", new Color(46, 139, 87)); // 边框
        UIManager.put("nimbusBlueGrey", new Color(46, 139, 87)); // 按钮
        UIManager.put("control", new Color(240, 255, 240)); // 背景

        GlobalData.setServerAddr("localhost", "8888");

        //JFrame jFrame=new JFrame();
        loginHandler = new logInHandler(this);

        Container contentPane = getContentPane();//获取控制面板

        titleLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));//设置标题大小、字体
        //titleLabel.setPreferredSize(new Dimension(0,80));//设置标题宽（0为自动填充）高（80像素）
        Font centerFont = new Font("楷体", Font.PLAIN, 20);//设置中间组件的文字大小、字体
        userNameLabel.setFont(centerFont);
        userNameTxt.setPreferredSize(new Dimension(200, 30));//设置输入框大小
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200, 30));//设置输入框大小
        studentRadioButton.setFont(centerFont);
        adminRadioButton.setFont(centerFont);
        logInBtn.setFont(centerFont);
        registerBtn.setFont(centerFont);
        teacherRadioButton.setFont(centerFont);

        //把组件加入控制面板
        backgroundPanel.add(userNameLabel);
        backgroundPanel.add(userNameTxt);
        backgroundPanel.add(titleLabel);

        backgroundPanel.add(pwdLabel);
        backgroundPanel.add(pwdField);
        studentRadioButton.addActionListener(loginHandler);
        backgroundPanel.add(studentRadioButton);
        adminRadioButton.addActionListener(loginHandler);
        backgroundPanel.add(adminRadioButton);
        teacherRadioButton.addActionListener(loginHandler);
        backgroundPanel.add(teacherRadioButton);
        logInBtn.addActionListener(loginHandler);
        group.add(adminRadioButton);
        group.add(studentRadioButton);
        group.add(teacherRadioButton);
        //增加按键事件
        logInBtn.addKeyListener(loginHandler);
        backgroundPanel.add(logInBtn);
        registerBtn.addActionListener(loginHandler);
        backgroundPanel.add(registerBtn);


        //弹簧布局
        //布局userNameLabel，水平居中，北边与边界布局中间一块顶部距离20
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userNameTxt)), Spring.constant(20));
        //  userNameLabel长度+userNameTxt长度+20

        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, titleLabel, -50, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 250, SpringLayout.NORTH, backgroundPanel);
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, 50, SpringLayout.NORTH, titleLabel);
        //userNameTxt西边与userNameLabel东边距离20，userNameTxt北边与userNameLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST, userNameTxt, 20, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameTxt, 0, SpringLayout.NORTH, userNameLabel);
        //pwdLabel东边与userNameLabel平齐，pwdLabel北边与userNameLabel南边相距20
        springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 20, SpringLayout.SOUTH, userNameLabel);
        //pwdTxt西边与pwdLabel东边距离20，pwdTxt北边与pwdLabel北边平齐
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
        //布局studentRadioButton和adminRadioButton
        Spring childWidth2 = Spring.sum(Spring.sum(Spring.width(studentRadioButton), Spring.width(adminRadioButton)),
                Spring.constant(30));
        int offsetX2 = childWidth2.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, studentRadioButton, -offsetX2 - 20, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, studentRadioButton, 40, SpringLayout.SOUTH, pwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, adminRadioButton, offsetX2 - 20, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, adminRadioButton, 40, SpringLayout.SOUTH, pwdLabel);
        //布局logInBtn和registerBtn
        Spring childWidth3 = Spring.sum(Spring.sum(Spring.width(logInBtn), Spring.width(registerBtn)),
                Spring.constant(30));
        int offsetX3 = childWidth3.getValue() / 2;
        springLayout.putConstraint(SpringLayout.NORTH, logInBtn, 70, SpringLayout.NORTH, studentRadioButton);
        springLayout.putConstraint(SpringLayout.WEST, logInBtn, -offsetX3 - 20, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, registerBtn, 70, SpringLayout.NORTH, adminRadioButton);
        springLayout.putConstraint(SpringLayout.WEST, registerBtn, offsetX3 - 20, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.WEST, teacherRadioButton, -15, SpringLayout.HORIZONTAL_CENTER, backgroundPanel);
        springLayout.putConstraint(SpringLayout.NORTH, teacherRadioButton, 40, SpringLayout.SOUTH, pwdLabel);


        //contentPane.add(titleLabel,BorderLayout.NORTH);//把标题放在边界布局北边一块
        //contentPane.add(backgroundPanel,BorderLayout.CENTER);//把backgroundPanel放在边界布局中间一块

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

        //registerBtn.addActionListener(loginHandler);

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 创建一个面板用于承载背景图片


        // 将背景面板设置为透明，以便显示其他组件
        backgroundPanel.setOpaque(false);
        setContentPane(backgroundPanel);
        setResizable(false);
        setVisible((true));
    }

    /**
     * 获取用户名输入框。
     *
     * @return 用户名输入框。
     */
    public JTextField getUserNameTxt() {
        return userNameTxt;
    }

    /**
     * 获取密码输入框。
     *
     * @return 密码输入框。
     */
    public JPasswordField getPwdField() {
        return pwdField;
    }
}