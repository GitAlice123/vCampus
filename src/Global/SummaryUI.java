//汇总页面（主页面）
package view.Global;
import view.Global.SummaryHandler;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SummaryUI extends JFrame
{
    //JLabel titleLabel=new JLabel("综合服务大厅",JLabel.CENTER);
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);//其余组件在边界布局中间的JPanel中
    JButton studentstatusmanagementBtn=new JButton("学生学籍管理");
    JButton courseselectionBtn=new JButton("选课系统");
    JButton labraryBtn=new JButton("图书馆");
    JButton shopBtn=new JButton("商店");
    JButton hospitalBtn=new JButton("医院");
    JButton bankBtn=new JButton("银行");
    JButton logoutBtn=new JButton("登出");
    SummaryHandler summaryHandler;
    public SummaryUI()
    {
        super("综合服务大厅");
        summaryHandler=new SummaryHandler(this);

        Container contentPane=getContentPane();//获取控制面板

        URL resource =this.getClass().getClassLoader().getResource("SEU.png");
        Image image=new ImageIcon(resource).getImage();
        setIconImage(image);

        Font buttonFont=new Font("楷体",Font.PLAIN,20);//设置按钮的文字大小、字体
        studentstatusmanagementBtn.setFont(buttonFont);
        courseselectionBtn.setFont(buttonFont);
        labraryBtn.setFont(buttonFont);
        shopBtn.setFont(buttonFont);
        hospitalBtn.setFont(buttonFont);
        bankBtn.setFont(buttonFont);
        logoutBtn.setFont(buttonFont);

        //将按钮添加到面板上
        centerPanel.add(studentstatusmanagementBtn);
        centerPanel.add(courseselectionBtn);
        centerPanel.add(labraryBtn);
        centerPanel.add(shopBtn);
        centerPanel.add(hospitalBtn);
        centerPanel.add(bankBtn);
        centerPanel.add(logoutBtn);
        studentstatusmanagementBtn.addActionListener(summaryHandler);
        courseselectionBtn.addActionListener(summaryHandler);
        labraryBtn.addActionListener(summaryHandler);
        shopBtn.addActionListener(summaryHandler);
        hospitalBtn.addActionListener(summaryHandler);
        bankBtn.addActionListener(summaryHandler);
        logoutBtn.addActionListener(summaryHandler);

        //调整位置
        int y=2*(Spring.height(centerPanel).getValue()-Spring.height(shopBtn).getValue());
        int x=Spring.width(centerPanel).getValue()/3-Spring.width(studentstatusmanagementBtn).getValue()/2;
        springLayout.putConstraint(SpringLayout.WEST,studentstatusmanagementBtn,-x,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,studentstatusmanagementBtn,-y,SpringLayout.NORTH,centerPanel);
        int x1=Spring.width(centerPanel).getValue()/3-Spring.width(courseselectionBtn).getValue();
        springLayout.putConstraint(SpringLayout.EAST,courseselectionBtn,x1,SpringLayout.EAST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,courseselectionBtn,0,SpringLayout.NORTH,studentstatusmanagementBtn);
        int x2= (Spring.width(studentstatusmanagementBtn).getValue()-Spring.width(labraryBtn).getValue())/2;
        springLayout.putConstraint(SpringLayout.EAST,labraryBtn,-x2,SpringLayout.EAST,studentstatusmanagementBtn);
        springLayout.putConstraint(SpringLayout.NORTH,labraryBtn,-y,SpringLayout.SOUTH,studentstatusmanagementBtn);
        int x3= (Spring.width(courseselectionBtn).getValue()-Spring.width(shopBtn).getValue())/2;
        springLayout.putConstraint(SpringLayout.WEST,shopBtn,x3,SpringLayout.WEST,courseselectionBtn);
        springLayout.putConstraint(SpringLayout.NORTH,shopBtn,0,SpringLayout.NORTH,labraryBtn);
        int x4= (Spring.width(studentstatusmanagementBtn).getValue()-Spring.width(hospitalBtn).getValue())/2;
        springLayout.putConstraint(SpringLayout.WEST,hospitalBtn,x4,SpringLayout.WEST,studentstatusmanagementBtn);
        springLayout.putConstraint(SpringLayout.NORTH,hospitalBtn,-y,SpringLayout.SOUTH,labraryBtn);
        int x5=(Spring.width(courseselectionBtn).getValue()-Spring.width(bankBtn).getValue())/2;
        springLayout.putConstraint(SpringLayout.WEST,bankBtn,x5,SpringLayout.WEST,courseselectionBtn);
        springLayout.putConstraint(SpringLayout.NORTH,bankBtn,-y,SpringLayout.SOUTH,shopBtn);
        springLayout.putConstraint(SpringLayout.EAST,logoutBtn,-30,SpringLayout.EAST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,logoutBtn,20,SpringLayout.NORTH,centerPanel);
        contentPane.add(centerPanel,BorderLayout.CENTER);

        //设置学生学籍管理为默认按钮
        getRootPane().setDefaultButton(studentstatusmanagementBtn);

        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SummaryUI();
    }
}
