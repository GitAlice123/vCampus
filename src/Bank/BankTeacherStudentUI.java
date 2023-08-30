package view.Bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.Global.SummaryUI;

import java.net.URL;
import java.util.Calendar;

public class BankTeacherStudentUI extends JFrame {
    //导航栏
    JButton rechargeBtn=new JButton("充值");
    JButton billBtn=new JButton("账单");
    JButton changepwdBtn=new JButton("密码修改");
    JButton cardimageBtn=new JButton("卡片信息");
    JButton freezeBtn=new JButton("挂失/解挂");
    JButton backBtn=new JButton("退出");
    //充值
    JLabel rechargeLabel=new JLabel("卡片充值",JLabel.CENTER);
    JLabel balanceLabel=new JLabel("校园卡余额");
    JLabel balance=new JLabel("￥0.00");
    JLabel amountLabel=new JLabel("充值金额");
    JTextField amountField=new JTextField();
    JButton confirmrechargeBtn=new JButton("确认充值");
    //修改密码
    JLabel changepwdLabel=new JLabel("修改密码");
    JLabel idLabel=new JLabel("学工号");
    JLabel id=new JLabel("XXXXXXXXX");
    JLabel oldpwdLabel=new JLabel("原密码");
    JLabel newpwdLabel=new JLabel("新密码");
    JLabel ensurepwdLabel=new JLabel("确认新密码");
    JTextField oldpwdField=new JTextField();
    JTextField newpwdField=new JTextField();
    JTextField ensurepwdField=new JTextField();
    JButton confirmchangepwdBtn=new JButton("确认修改");
    //账单
    JButton searchBtn=new JButton("查询");
    JTextField searchField=new JTextField();
    JLabel yearLabel=new JLabel("年");
    JLabel monthLabel=new JLabel("月");
    //卡片信息
    JLabel cardimageLabel=new JLabel("卡片信息");
    JLabel accontLabel=new JLabel("校园卡账户");
    JLabel nameLabel=new JLabel("姓名");
    JLabel idLabel2=new JLabel("学工号");
    JLabel balanceLabel2=new JLabel("账户余额");
    JLabel statusLabel=new JLabel("挂失状态");
    JLabel account=new JLabel("XXXXXX");
    JLabel name=new JLabel("XXX");
    JLabel id2=new JLabel("XXXXXXXXX");
    JLabel balance2=new JLabel("￥0.00");
    JLabel status=new JLabel("正常");
    //挂失/解挂
    JLabel reportlossLabel=new JLabel("卡片挂失");
    JLabel cardstatusLabel=new JLabel("卡片状态");
    JLabel cardstatus=new JLabel("正常");
    JLabel cardpwdLabel=new JLabel("卡片密码");
    JTextField cardpwdField=new JTextField();
    JButton confirmfreezeBtn=new JButton("确认挂失");



    JPanel backPanel=new JPanel();
    CardLayout cardLayout=new CardLayout();
    SpringLayout springLayout=new SpringLayout();
    JPanel cardPanel=new JPanel(cardLayout);
    JPanel campuscard=new JPanel();
    JPanel blank=new JPanel();
    JPanel recharge=new JPanel(springLayout);
    JPanel changepwd=new JPanel(springLayout);
    JPanel cardimage=new JPanel(springLayout);
    JPanel bill=new JPanel(springLayout);

    JPanel reportloss=new JPanel(springLayout);

    public BankTeacherStudentUI()
    {
        super("银行");

        Font buttonFont=new Font("楷体",Font.PLAIN,20);//设置按钮的文字大小、字体
        Font titleFont=new Font("楷体",Font.PLAIN,40);
        Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体

//        URL resource =this.getClass().getClassLoader().getResource("SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        rechargeBtn.setFont(buttonFont);
        billBtn.setFont(buttonFont);
        cardimageBtn.setFont(buttonFont);
        changepwdBtn.setFont(buttonFont);
        freezeBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);

        campuscard.add(rechargeBtn);
        campuscard.add(billBtn);
        campuscard.add(changepwdBtn);
        campuscard.add(cardimageBtn);
        campuscard.add(freezeBtn);
        backPanel.add(backBtn);

        rechargeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"充值");
            }
        });
        billBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"账单");
            }
        });
        changepwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"密码修改");
            }
        });
        cardimageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"卡片状态");
            }
        });
        freezeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"挂失/解挂");
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //充值
        rechargeLabel.setFont(titleFont);
        balanceLabel.setFont(centerFont);
        balance.setFont(centerFont);
        amountLabel.setFont(centerFont);
        amountField.setPreferredSize(new Dimension(200,30));//设置输入框大小
        confirmrechargeBtn.setFont(buttonFont);

        recharge.add(rechargeLabel);
        recharge.add(balanceLabel);
        recharge.add(balance);
        recharge.add(amountLabel);
        recharge.add(amountField);
        recharge.add(confirmrechargeBtn);

        springLayout.putConstraint(SpringLayout.NORTH,rechargeLabel,0,SpringLayout.NORTH,cardPanel);
        int x=(int)(1.3*(Spring.width(cardPanel).getValue()-Spring.width(rechargeLabel).getValue()));
        springLayout.putConstraint(SpringLayout.WEST,rechargeLabel,-x,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,balanceLabel,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,balanceLabel,20,SpringLayout.SOUTH,rechargeLabel);
        springLayout.putConstraint(SpringLayout.WEST,balance,40,SpringLayout.EAST,balanceLabel);
        springLayout.putConstraint(SpringLayout.NORTH,balance,0,SpringLayout.NORTH,balanceLabel);
        springLayout.putConstraint(SpringLayout.WEST,amountLabel,0,SpringLayout.WEST,balanceLabel);
        springLayout.putConstraint(SpringLayout.NORTH,amountLabel,40,SpringLayout.SOUTH,balanceLabel);
        springLayout.putConstraint(SpringLayout.WEST,amountField,0,SpringLayout.WEST,balance);
        springLayout.putConstraint(SpringLayout.NORTH,amountField,0,SpringLayout.NORTH,amountLabel);
        springLayout.putConstraint(SpringLayout.WEST,confirmrechargeBtn,235,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,confirmrechargeBtn,40,SpringLayout.SOUTH,amountLabel);

        //修改密码
        changepwdLabel.setFont(titleFont);
        idLabel.setFont(centerFont);
        newpwdLabel.setFont(centerFont);
        oldpwdLabel.setFont(centerFont);
        ensurepwdLabel.setFont(centerFont);
        confirmchangepwdBtn.setFont(buttonFont);
        oldpwdField.setPreferredSize(new Dimension(200,30));
        newpwdField.setPreferredSize(new Dimension(200,30));
        ensurepwdField.setPreferredSize(new Dimension(200,30));

        changepwd.add(changepwdLabel);
        changepwd.add(idLabel);
        changepwd.add(id);
        changepwd.add(oldpwdLabel);
        changepwd.add(oldpwdField);
        changepwd.add(newpwdLabel);
        changepwd.add(newpwdField);
        changepwd.add(ensurepwdLabel);
        changepwd.add(ensurepwdField);
        changepwd.add(confirmchangepwdBtn);

        springLayout.putConstraint(SpringLayout.WEST,changepwdLabel,-x,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,idLabel,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,idLabel,0,SpringLayout.SOUTH,changepwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,id,60,SpringLayout.EAST,idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,id,0,SpringLayout.NORTH,idLabel);
        springLayout.putConstraint(SpringLayout.WEST,oldpwdLabel,0,SpringLayout.WEST,idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,oldpwdLabel,20,SpringLayout.SOUTH,idLabel);
        springLayout.putConstraint(SpringLayout.WEST,oldpwdField,0,SpringLayout.WEST,id);
        springLayout.putConstraint(SpringLayout.NORTH,oldpwdField,0,SpringLayout.NORTH,oldpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,newpwdLabel,0,SpringLayout.WEST,idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,newpwdLabel,20,SpringLayout.SOUTH,oldpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,newpwdField,0,SpringLayout.WEST,id);
        springLayout.putConstraint(SpringLayout.NORTH,newpwdField,0,SpringLayout.NORTH,newpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,ensurepwdLabel,0,SpringLayout.WEST,idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ensurepwdLabel,20,SpringLayout.SOUTH,newpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,ensurepwdField,0,SpringLayout.WEST,id);
        springLayout.putConstraint(SpringLayout.NORTH,ensurepwdField,0,SpringLayout.NORTH,ensurepwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,confirmchangepwdBtn,235,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,confirmchangepwdBtn,20,SpringLayout.SOUTH,ensurepwdLabel);

        //账单
        String[] header={"商品说明","订单号","付款账户","交易时间","账单分类","金额"};
        String[][] data={
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
                {null,null,null,null,null,null},
        };
        JTable billtable=new JTable(data,header);
        JScrollPane billpane=new JScrollPane(billtable);
        billpane.setPreferredSize(new Dimension(400,200));

        JComboBox<String>year=new JComboBox<String>();
        JComboBox<String>month=new JComboBox<String>();
        month.addItem("");
        year.addItem("2023");
        year.addItem("2022");
        year.addItem("2021");

        month.addItem("");
        month.addItem("1");
        month.addItem("2");
        month.addItem("3");
        month.addItem("4");
        month.addItem("5");
        month.addItem("6");
        month.addItem("7");
        month.addItem("8");
        month.addItem("9");
        month.addItem("10");
        month.addItem("11");
        month.addItem("12");


        searchField.setPreferredSize(new Dimension(200,30));
        searchBtn.setFont(buttonFont);
        yearLabel.setFont(centerFont);
        monthLabel.setFont(centerFont);

        bill.add(searchBtn);
        bill.add(searchField);
        bill.add(billpane);
        bill.add(yearLabel);
        bill.add(monthLabel);
        bill.add(year);
        bill.add(month);

        int x1=(Spring.width(cardPanel).getValue()-Spring.width(billpane).getValue())/4;
        springLayout.putConstraint(SpringLayout.WEST,billpane,-x1,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,billpane,60,SpringLayout.SOUTH,cardPanel);
        springLayout.putConstraint(SpringLayout.EAST,searchField,-10,SpringLayout.EAST,bill);
        springLayout.putConstraint(SpringLayout.NORTH,searchField,10,SpringLayout.NORTH,bill);
        springLayout.putConstraint(SpringLayout.EAST,searchBtn,-10,SpringLayout.WEST,searchField);
        springLayout.putConstraint(SpringLayout.NORTH,searchBtn,0,SpringLayout.NORTH,searchField);
        springLayout.putConstraint(SpringLayout.WEST,yearLabel,30,SpringLayout.WEST,bill);
        springLayout.putConstraint(SpringLayout.NORTH,yearLabel,0,SpringLayout.NORTH,searchField);
        springLayout.putConstraint(SpringLayout.WEST,year,10,SpringLayout.EAST,yearLabel);
        springLayout.putConstraint(SpringLayout.NORTH,year,0,SpringLayout.NORTH,searchField);
        springLayout.putConstraint(SpringLayout.WEST,monthLabel,10,SpringLayout.EAST,year);
        springLayout.putConstraint(SpringLayout.NORTH,monthLabel,0,SpringLayout.NORTH,searchField);
        springLayout.putConstraint(SpringLayout.WEST,month,10,SpringLayout.EAST,monthLabel);
        springLayout.putConstraint(SpringLayout.NORTH,month,0,SpringLayout.NORTH,searchField);
        //卡片信息
        cardimageLabel.setFont(titleFont);
        accontLabel.setFont(centerFont);
        account.setFont(centerFont);
        nameLabel.setFont(centerFont);
        name.setFont(centerFont);
        statusLabel.setFont(centerFont);
        status.setFont(centerFont);
        idLabel2.setFont(centerFont);
        id2.setFont(centerFont);
        balanceLabel2.setFont(centerFont);
        balance2.setFont(centerFont);

        cardimage.add(cardimageLabel);
        cardimage.add(accontLabel);
        cardimage.add(account);
        cardimage.add(nameLabel);
        cardimage.add(name);
        cardimage.add(statusLabel);
        cardimage.add(status);
        cardimage.add(id2);
        cardimage.add(idLabel2);
        cardimage.add(balanceLabel2);
        cardimage.add(balance2);

        springLayout.putConstraint(SpringLayout.WEST,cardimageLabel,-x,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,accontLabel,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,accontLabel,0,SpringLayout.SOUTH,cardimageLabel);
        springLayout.putConstraint(SpringLayout.WEST,account,60,SpringLayout.EAST,accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH,account,0,SpringLayout.NORTH,accontLabel);
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,0,SpringLayout.WEST,accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,20,SpringLayout.SOUTH,accontLabel);
        springLayout.putConstraint(SpringLayout.WEST,name,0,SpringLayout.WEST,account);
        springLayout.putConstraint(SpringLayout.NORTH,name,0,SpringLayout.NORTH,nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,idLabel2,0,SpringLayout.WEST,accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH,idLabel2,20,SpringLayout.SOUTH,nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,id2,0,SpringLayout.WEST,account);
        springLayout.putConstraint(SpringLayout.NORTH,id2,0,SpringLayout.NORTH,idLabel2);
        springLayout.putConstraint(SpringLayout.WEST,balanceLabel2,0,SpringLayout.WEST,accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH,balanceLabel2,20,SpringLayout.SOUTH,idLabel2);
        springLayout.putConstraint(SpringLayout.WEST,balance2,0,SpringLayout.WEST,account);
        springLayout.putConstraint(SpringLayout.NORTH,balance2,0,SpringLayout.NORTH,balanceLabel2);
        springLayout.putConstraint(SpringLayout.WEST,statusLabel,0,SpringLayout.WEST,accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH,statusLabel,20,SpringLayout.SOUTH,balanceLabel2);
        springLayout.putConstraint(SpringLayout.WEST,status,0,SpringLayout.WEST,account);
        springLayout.putConstraint(SpringLayout.NORTH,status,0,SpringLayout.NORTH,statusLabel);

        //挂失/解挂
        reportlossLabel.setFont(titleFont);
        cardstatusLabel.setFont(centerFont);
        cardstatus.setFont(centerFont);
        cardpwdLabel.setFont(centerFont);
        cardpwdField.setPreferredSize(new Dimension(200,30));
        confirmfreezeBtn.setFont(buttonFont);

        reportloss.add(reportlossLabel);
        reportloss.add(cardstatusLabel);
        reportloss.add(cardstatus);
        reportloss.add(cardpwdLabel);
        reportloss.add(cardpwdField);
        reportloss.add(confirmfreezeBtn);

        springLayout.putConstraint(SpringLayout.WEST,reportlossLabel,-x,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,cardstatusLabel,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,cardstatusLabel,20,SpringLayout.SOUTH,reportlossLabel);
        springLayout.putConstraint(SpringLayout.WEST,cardstatus,40,SpringLayout.EAST,cardstatusLabel);
        springLayout.putConstraint(SpringLayout.NORTH,cardstatus,0,SpringLayout.NORTH,cardstatusLabel);
        springLayout.putConstraint(SpringLayout.WEST,cardpwdLabel,0,SpringLayout.WEST,cardstatusLabel);
        springLayout.putConstraint(SpringLayout.NORTH,cardpwdLabel,40,SpringLayout.SOUTH,cardstatusLabel);
        springLayout.putConstraint(SpringLayout.WEST,cardpwdField,0,SpringLayout.WEST,cardstatus);
        springLayout.putConstraint(SpringLayout.NORTH,cardpwdField,0,SpringLayout.NORTH,cardpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST,confirmfreezeBtn,235,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,confirmfreezeBtn,40,SpringLayout.SOUTH,cardpwdLabel);


        cardPanel.add(blank);
        cardPanel.add(recharge,"充值");
        cardPanel.add(changepwd,"密码修改");
        cardPanel.add(cardimage,"卡片状态");
        cardPanel.add(bill,"账单");
        cardPanel.add(reportloss,"挂失/解挂");

        Container contentPane=getContentPane();//获取控制面板
        contentPane.add(cardPanel,BorderLayout.CENTER);
        contentPane.add(campuscard,BorderLayout.NORTH);
        contentPane.add(backPanel,BorderLayout.SOUTH);
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }






    public static void main(String[] args)
    {
        new BankTeacherStudentUI();
    }
}
