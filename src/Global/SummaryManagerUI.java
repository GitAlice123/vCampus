package view.Global;

import view.connect.NoticeClientAPI;
import view.connect.NoticeClientAPIImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class SummaryManagerUI extends JFrame
{
    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/centerSEU.jpg");
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

    JPanel southPanel=new JPanel();

    //左侧
    JButton studentstatusmanagementBtn=new JButton("学生学籍管理");
    JButton courseselectionBtn=new JButton("选课系统");
    JButton labraryBtn=new JButton("图书馆");
    JButton shopBtn=new JButton("商店");
    JButton hospitalBtn=new JButton("医院");
    JButton bankBtn=new JButton("银行");
    JButton chatBtn=new JButton("chat");
    JButton logoutBtn=new JButton("登出");

    //顶部
    JLabel summaryLabel=new JLabel("综合服务大厅");

    //右侧
    JScrollPane centerpane;
    JTextArea textarea;//公告栏文本
    JLabel textLabel=new JLabel("公告栏");
    JButton publishBtn=new JButton("发布");

    ImageIcon Carouselimg = new ImageIcon("Images/Carousel.jpg");
    //轮播图长图
    int index =0;
    //轮播图播放图片偏移像素量
    int speed = 1;
    //轮播图移动速度

    JPanel mp;

    SummaryManagerHandler summaryHandler;
    public SummaryManagerUI()
    {
        super("综合服务大厅");
        summaryHandler=new SummaryManagerHandler(this);

        Container contentPane=getContentPane();//获取控制面板


        studentstatusmanagementBtn.setFont(buttonFont);
        courseselectionBtn.setFont(buttonFont);
        labraryBtn.setFont(buttonFont);
        shopBtn.setFont(buttonFont);
        hospitalBtn.setFont(buttonFont);
        bankBtn.setFont(buttonFont);
        chatBtn.setFont(buttonFont);
        logoutBtn.setFont(buttonFont);

        studentstatusmanagementBtn.setPreferredSize(new Dimension(200, 40));
        courseselectionBtn.setPreferredSize(new Dimension(200, 40));
        labraryBtn.setPreferredSize(new Dimension(200, 40));
        shopBtn.setPreferredSize(new Dimension(200, 40));
        hospitalBtn.setPreferredSize(new Dimension(200, 40));
        bankBtn.setPreferredSize(new Dimension(200, 40));
        chatBtn.setPreferredSize(new Dimension(200, 40));
        logoutBtn.setPreferredSize(new Dimension(200, 40));

        //将按钮添加到面板上
        centerPanel.add(studentstatusmanagementBtn);
        centerPanel.add(courseselectionBtn);
        centerPanel.add(labraryBtn);
        centerPanel.add(shopBtn);
        centerPanel.add(hospitalBtn);
        centerPanel.add(bankBtn);
        centerPanel.add(chatBtn);
        southPanel.add(logoutBtn);



        studentstatusmanagementBtn.addActionListener(summaryHandler);
        courseselectionBtn.addActionListener(summaryHandler);
        labraryBtn.addActionListener(summaryHandler);
        shopBtn.addActionListener(summaryHandler);
        hospitalBtn.addActionListener(summaryHandler);
        bankBtn.addActionListener(summaryHandler);
        chatBtn.addActionListener(summaryHandler);
        logoutBtn.addActionListener(summaryHandler);
        publishBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoticeClientAPI noticeClientAPI=new NoticeClientAPIImpl("localhost", 8888);
                String input=textarea.getText();
                System.out.println("input:::::是"+input);
                boolean flag=noticeClientAPI.editNotice(input);
                if(flag){
                    JOptionPane.showMessageDialog(centerPanel, "成功发布！");
                }else {
                    JOptionPane.showMessageDialog(centerPanel, "发布失败");
                }

            }
        });

        //右侧
        textarea=new JTextArea("公告栏");
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setFont(centerFont);
        centerpane=new JScrollPane(textarea);
        centerpane.setPreferredSize(new Dimension(250,500));
        textLabel.setFont(centerFont);
        publishBtn.setFont(buttonFont);

        centerPanel.add(textLabel);
        centerPanel.add(centerpane);
        centerPanel.add(summaryLabel);
        centerPanel.add(publishBtn);
        summaryLabel.setFont(titleFont);

        springLayout.putConstraint(SpringLayout.WEST,summaryLabel,450,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.WEST,textLabel,900,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,textLabel,20,SpringLayout.SOUTH,summaryLabel);
        springLayout.putConstraint(SpringLayout.WEST,centerpane,900,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,centerpane,20,SpringLayout.SOUTH,textLabel);
        springLayout.putConstraint(SpringLayout.WEST,publishBtn,80,SpringLayout.WEST,centerpane);
        springLayout.putConstraint(SpringLayout.NORTH,publishBtn,20,SpringLayout.SOUTH,centerpane);

        springLayout.putConstraint(SpringLayout.WEST,studentstatusmanagementBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,studentstatusmanagementBtn,0,SpringLayout.NORTH,centerpane);
        springLayout.putConstraint(SpringLayout.WEST,courseselectionBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,courseselectionBtn,40,SpringLayout.SOUTH,studentstatusmanagementBtn);
        springLayout.putConstraint(SpringLayout.WEST,labraryBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,labraryBtn,40,SpringLayout.SOUTH,courseselectionBtn);
        springLayout.putConstraint(SpringLayout.WEST,shopBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,shopBtn,40,SpringLayout.SOUTH,labraryBtn);
        springLayout.putConstraint(SpringLayout.WEST,hospitalBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,hospitalBtn,40,SpringLayout.SOUTH,shopBtn);
        springLayout.putConstraint(SpringLayout.WEST,bankBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,bankBtn,40,SpringLayout.SOUTH,hospitalBtn);
        springLayout.putConstraint(SpringLayout.WEST,chatBtn,40,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,chatBtn,40,SpringLayout.SOUTH,bankBtn);

        //显示公告
        NoticeClientAPI noticeClientAPI=new NoticeClientAPIImpl("localhost", 8888);
        String text=noticeClientAPI.getNotice();
        textarea.setText(text);

        mp = new MyJPanel();
        mp.setPreferredSize(new Dimension(600,399));
        Timer timer = new Timer(10,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mp.repaint();
            }
        });
        timer.start();

        centerPanel.add(mp);

        springLayout.putConstraint(SpringLayout.WEST,mp,40,SpringLayout.EAST,studentstatusmanagementBtn);
        springLayout.putConstraint(SpringLayout.NORTH,mp,50,SpringLayout.NORTH,studentstatusmanagementBtn);

        contentPane.add(centerPanel,BorderLayout.CENTER);
        contentPane.add(southPanel,BorderLayout.SOUTH);


        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    class MyJPanel extends JPanel{
        @Override
        public void paint(Graphics g) {

            super.paint(g);
            g.drawImage(Carouselimg.getImage(), index*(-1), 0,this);
            if(index%600<=80 || index%600>=520){
                speed=1;
            }else{
                speed=4;
            }

            index = index + speed;

            if(index==1800){
                index=0;
            }
        }

    }
    public static void main(String[] args)
    {
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
        new view.Global.SummaryManagerUI();
    }
}
