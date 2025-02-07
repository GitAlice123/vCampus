package view.Bank;

import view.Global.GlobalData;
import view.Global.SummaryStudentTeacherUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;


import java.awt.image.BufferedImage;

import java.net.URL;

import java.util.Calendar;
import java.util.Date;


public class BankTeacherStudentUI extends JFrame {

    //找出当前用户的银行账户
    bankAccount thisAccount = new bankAccount();

    //导航栏
    JButton rechargeBtn = new JButton("充值");
    JButton billBtn = new JButton("账单");
    JButton changepwdBtn = new JButton("密码修改");
    JButton cardimageBtn = new JButton("卡片信息");
    JButton freezeBtn = new JButton("挂失/解挂");
    JButton backBtn = new JButton("退出");


    //充值
    JLabel rechargeLabel = new JLabel("卡片充值", JLabel.CENTER);
    JLabel balanceLabel = new JLabel("校园卡余额");
    JLabel balance = new JLabel("￥" + Double.toString(thisAccount.getBalance()));
    JLabel amountLabel = new JLabel("充值金额");
    JTextField amountField = new JTextField();//用户输入的充值金额
    JLabel rechargepwdLabel = new JLabel("密码");
    JPasswordField rechargepwdField = new JPasswordField();//用户输入的密码
    JButton confirmrechargeBtn = new JButton("确认充值");//用户点击确认充值按钮


    //修改密码
    JLabel changepwdLabel = new JLabel("修改密码");
    JLabel idLabel = new JLabel("学工号");
    JLabel id = new JLabel(thisAccount.getId());
    JLabel oldpwdLabel = new JLabel("原密码");
    JLabel newpwdLabel = new JLabel("新密码");
    JLabel ensurepwdLabel = new JLabel("确认新密码");
    JPasswordField oldpwdField = new JPasswordField();
    JPasswordField newpwdField = new JPasswordField();
    JPasswordField ensurepwdField = new JPasswordField();
    JButton confirmchangepwdBtn = new JButton("确认修改");
    //账单
    JButton searchBtn = new JButton("查询");
    JTextField searchField = new JTextField();
    JLabel yearLabel = new JLabel("年");
    JLabel monthLabel = new JLabel("月");
    JComboBox<String> year = new JComboBox<String>();
    JComboBox<String> month = new JComboBox<String>();

    String[] header = {"商品说明", "订单号", "付款账户", "交易时间", "账单分类", "金额"};
    String[][] data = {
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
            {null, null, null, null, null, null},
    };
    DefaultTableModel tableModel = new DefaultTableModel(data, header);
    //JTable billtable=new JTable(data,header);
    JTable billtable = new JTable(tableModel);
    JScrollPane billpane = new JScrollPane(billtable);


    //卡片信息
    JLabel cardimageLabel = new JLabel("卡片信息");
    JLabel accontLabel = new JLabel("校园卡账户");
    JLabel nameLabel = new JLabel("姓名");
    JLabel idLabel2 = new JLabel("学工号");
    JLabel balanceLabel2 = new JLabel("账户余额");
    JLabel statusLabel = new JLabel("挂失状态");
    JLabel account = new JLabel(thisAccount.getCardId());
    JLabel name = new JLabel(thisAccount.getName());
    JLabel id2 = new JLabel(thisAccount.getId());
    JLabel balance2 = new JLabel("￥" + thisAccount.getBalance());
    JLabel status = new JLabel(thisAccount.isLoss() ? "正常" : "已挂失");


    //挂失/解挂
    JLabel reportlossLabel = new JLabel("卡片挂失");
    JLabel cardstatusLabel = new JLabel("卡片状态");
    JLabel cardstatus = new JLabel(thisAccount.isLoss() ? "正常" : "已挂失");
    JLabel cardpwdLabel = new JLabel("卡片密码");
    JPasswordField cardpwdField = new JPasswordField();
    //JButton confirmfreezeBtn=new JButton(thisAccount.isLoss()?"确认挂失":"确认解挂");
    JButton confirmfreezeBtn = new JButton("确认挂失");


    JPanel backPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    SpringLayout springLayout = new SpringLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel campuscard = new JPanel();
    JPanel blank = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/SEU_finance.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), (int)(getHeight()*0.8), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 50, null);

            g2d.dispose();
        }
    };
    JPanel recharge = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Bank1.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };
    JPanel changepwd = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Bank2.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };
    JPanel cardimage = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Bank4.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };
    JPanel bill = new JPanel(springLayout);

    JPanel reportloss = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Bank3.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };

    public BankTeacherStudentUI() {
        super("银行");

        getAccount();

        Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
        Font titleFont = new Font("楷体", Font.PLAIN, 50);
        Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体
        Font fieldFont=new Font("楷体",Font.PLAIN,20);

//        URL resource =this.getClass().getClassLoader().getResource("SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        Color customColor = new Color(180, 218, 192);
        rechargeBtn.setBackground(customColor);
        rechargeBtn.setFont(buttonFont);
        billBtn.setBackground(customColor);
        billBtn.setFont(buttonFont);
        cardimageBtn.setBackground(customColor);
        cardimageBtn.setFont(buttonFont);
        changepwdBtn.setBackground(customColor);
        changepwdBtn.setFont(buttonFont);
        freezeBtn.setBackground(customColor);
        freezeBtn.setFont(buttonFont);
        backBtn.setBackground(customColor);
        backBtn.setFont(buttonFont);
        rechargeBtn.setPreferredSize(new Dimension(150, 40));
        cardimageBtn.setPreferredSize(new Dimension(150, 40));
        billBtn.setPreferredSize(new Dimension(150, 40));
        changepwdBtn.setPreferredSize(new Dimension(150, 40));
        freezeBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setPreferredSize(new Dimension(100, 40));


        campuscard.add(rechargeBtn);
        campuscard.add(billBtn);
        campuscard.add(changepwdBtn);
        campuscard.add(cardimageBtn);
        campuscard.add(freezeBtn);
        backPanel.add(backBtn);


        //充值
        rechargeLabel.setFont(titleFont);
        balanceLabel.setFont(centerFont);
        balance.setFont(centerFont);
        amountLabel.setFont(centerFont);
        amountField.setPreferredSize(new Dimension(200, 30));//设置输入框大小
        amountField.setFont(fieldFont);
        confirmrechargeBtn.setFont(buttonFont);
        confirmrechargeBtn.setBackground(customColor);
        rechargepwdLabel.setFont(centerFont);
        rechargepwdField.setPreferredSize(new Dimension(200, 30));
        rechargepwdField.setFont(fieldFont);


        recharge.add(rechargeLabel);
        recharge.add(balanceLabel);
        recharge.add(balance);
        recharge.add(amountLabel);
        recharge.add(amountField);
        recharge.add(confirmrechargeBtn);
        recharge.add(rechargepwdLabel);
        recharge.add(rechargepwdField);

        springLayout.putConstraint(SpringLayout.NORTH, rechargeLabel, 40, SpringLayout.NORTH, cardPanel);
        int x = (int) (2.45 * (Spring.width(cardPanel).getValue() - Spring.width(rechargeLabel).getValue()));
        springLayout.putConstraint(SpringLayout.WEST, rechargeLabel, -x, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, balanceLabel, 400, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, balanceLabel, 80, SpringLayout.SOUTH, rechargeLabel);
        springLayout.putConstraint(SpringLayout.WEST, balance, 80, SpringLayout.EAST, balanceLabel);
        springLayout.putConstraint(SpringLayout.NORTH, balance, 0, SpringLayout.NORTH, balanceLabel);
        springLayout.putConstraint(SpringLayout.WEST, amountLabel, 0, SpringLayout.WEST, balanceLabel);
        springLayout.putConstraint(SpringLayout.NORTH, amountLabel, 60, SpringLayout.SOUTH, balanceLabel);
        springLayout.putConstraint(SpringLayout.WEST, amountField, 0, SpringLayout.WEST, balance);
        springLayout.putConstraint(SpringLayout.NORTH, amountField, 0, SpringLayout.NORTH, amountLabel);
        springLayout.putConstraint(SpringLayout.WEST, rechargepwdLabel, 0, SpringLayout.WEST, balanceLabel);
        springLayout.putConstraint(SpringLayout.NORTH, rechargepwdLabel, 60, SpringLayout.SOUTH, amountLabel);
        springLayout.putConstraint(SpringLayout.WEST, rechargepwdField, 0, SpringLayout.WEST, balance);
        springLayout.putConstraint(SpringLayout.NORTH, rechargepwdField, 0, SpringLayout.NORTH, rechargepwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, confirmrechargeBtn, 525, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, confirmrechargeBtn, 80, SpringLayout.SOUTH, rechargepwdLabel);


        //修改密码
        changepwdLabel.setFont(titleFont);
        idLabel.setFont(centerFont);
        id.setFont(centerFont);
        newpwdLabel.setFont(centerFont);
        oldpwdLabel.setFont(centerFont);
        ensurepwdLabel.setFont(centerFont);
        confirmchangepwdBtn.setFont(buttonFont);
        confirmchangepwdBtn.setBackground(customColor);
        oldpwdField.setPreferredSize(new Dimension(200, 30));
        newpwdField.setPreferredSize(new Dimension(200, 30));
        ensurepwdField.setPreferredSize(new Dimension(200, 30));
        oldpwdField.setFont(fieldFont);
        newpwdField.setFont(fieldFont);
        ensurepwdField.setFont(fieldFont);


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

        springLayout.putConstraint(SpringLayout.WEST, changepwdLabel, -x, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, changepwdLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, idLabel, 400, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel, 60, SpringLayout.SOUTH, changepwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, id, 80, SpringLayout.EAST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, id, 0, SpringLayout.NORTH, idLabel);
        springLayout.putConstraint(SpringLayout.WEST, oldpwdLabel, 0, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, oldpwdLabel, 60, SpringLayout.SOUTH, idLabel);
        springLayout.putConstraint(SpringLayout.WEST, oldpwdField, 0, SpringLayout.WEST, id);
        springLayout.putConstraint(SpringLayout.NORTH, oldpwdField, 0, SpringLayout.NORTH, oldpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, newpwdLabel, 0, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, newpwdLabel, 60, SpringLayout.SOUTH, oldpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, newpwdField, 0, SpringLayout.WEST, id);
        springLayout.putConstraint(SpringLayout.NORTH, newpwdField, 0, SpringLayout.NORTH, newpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, ensurepwdLabel, 0, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, ensurepwdLabel, 60, SpringLayout.SOUTH, newpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, ensurepwdField, 0, SpringLayout.WEST, id);
        springLayout.putConstraint(SpringLayout.NORTH, ensurepwdField, 0, SpringLayout.NORTH, ensurepwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, confirmchangepwdBtn, 525, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, confirmchangepwdBtn, 80, SpringLayout.SOUTH, ensurepwdLabel);


        //账单

        billpane.setPreferredSize(new Dimension(400, 200));
        billtable.setRowHeight(30);
        billpane.setPreferredSize(new Dimension(1000, 500));
        JTableHeader tab_header = billtable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度


        year.addItem("");
        year.addItem("2023");
        year.addItem("2022");
        year.addItem("2021");
        year.setFont(new Font("楷体", Font.PLAIN, 20));


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
        month.setFont(new Font("楷体", Font.PLAIN, 20));


        year.setPreferredSize(new Dimension(80, 40));
        month.setPreferredSize(new Dimension(80, 40));

        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setFont(fieldFont);
        searchBtn.setFont(buttonFont);
        searchBtn.setBackground(customColor);
        yearLabel.setFont(centerFont);
        monthLabel.setFont(centerFont);
        billtable.setFont(new Font("楷体", Font.PLAIN, 20));
        billtable.setDefaultRenderer(Object.class, new BankManagerUI.TableBackgroundColorRenderer());
        bill.add(searchBtn);
        bill.add(searchField);
        bill.add(billpane);
        bill.add(yearLabel);
        bill.add(monthLabel);
        bill.add(year);
        bill.add(month);

        springLayout.putConstraint(SpringLayout.WEST, billpane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, billpane, 80, SpringLayout.SOUTH, cardPanel);
        springLayout.putConstraint(SpringLayout.EAST, searchField, -40, SpringLayout.EAST, bill);
        springLayout.putConstraint(SpringLayout.NORTH, searchField, 40, SpringLayout.NORTH, bill);
        springLayout.putConstraint(SpringLayout.EAST, searchBtn, -40, SpringLayout.WEST, searchField);
        springLayout.putConstraint(SpringLayout.NORTH, searchBtn, 0, SpringLayout.NORTH, searchField);
        springLayout.putConstraint(SpringLayout.WEST, yearLabel, 80, SpringLayout.WEST, bill);
        springLayout.putConstraint(SpringLayout.NORTH, yearLabel, 0, SpringLayout.NORTH, searchField);
        springLayout.putConstraint(SpringLayout.WEST, year, 20, SpringLayout.EAST, yearLabel);
        springLayout.putConstraint(SpringLayout.NORTH, year, 0, SpringLayout.NORTH, searchField);
        springLayout.putConstraint(SpringLayout.WEST, monthLabel, 20, SpringLayout.EAST, year);
        springLayout.putConstraint(SpringLayout.NORTH, monthLabel, 0, SpringLayout.NORTH, searchField);
        springLayout.putConstraint(SpringLayout.WEST, month, 20, SpringLayout.EAST, monthLabel);
        springLayout.putConstraint(SpringLayout.NORTH, month, 0, SpringLayout.NORTH, searchField);
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

        springLayout.putConstraint(SpringLayout.WEST, cardimageLabel, -x, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cardimageLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, accontLabel, 400, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, accontLabel, 40, SpringLayout.SOUTH, cardimageLabel);
        springLayout.putConstraint(SpringLayout.WEST, account, 80, SpringLayout.EAST, accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH, account, 0, SpringLayout.NORTH, accontLabel);
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 0, SpringLayout.WEST, accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.SOUTH, accontLabel);
        springLayout.putConstraint(SpringLayout.WEST, name, 0, SpringLayout.WEST, account);
        springLayout.putConstraint(SpringLayout.NORTH, name, 0, SpringLayout.NORTH, nameLabel);
        springLayout.putConstraint(SpringLayout.WEST, idLabel2, 0, SpringLayout.WEST, accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel2, 40, SpringLayout.SOUTH, nameLabel);
        springLayout.putConstraint(SpringLayout.WEST, id2, 0, SpringLayout.WEST, account);
        springLayout.putConstraint(SpringLayout.NORTH, id2, 0, SpringLayout.NORTH, idLabel2);
        springLayout.putConstraint(SpringLayout.WEST, balanceLabel2, 0, SpringLayout.WEST, accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH, balanceLabel2, 40, SpringLayout.SOUTH, idLabel2);
        springLayout.putConstraint(SpringLayout.WEST, balance2, 0, SpringLayout.WEST, account);
        springLayout.putConstraint(SpringLayout.NORTH, balance2, 0, SpringLayout.NORTH, balanceLabel2);
        springLayout.putConstraint(SpringLayout.WEST, statusLabel, 0, SpringLayout.WEST, accontLabel);
        springLayout.putConstraint(SpringLayout.NORTH, statusLabel, 40, SpringLayout.SOUTH, balanceLabel2);
        springLayout.putConstraint(SpringLayout.WEST, status, 0, SpringLayout.WEST, account);
        springLayout.putConstraint(SpringLayout.NORTH, status, 0, SpringLayout.NORTH, statusLabel);

        //挂失/解挂
        reportlossLabel.setFont(titleFont);
        cardstatusLabel.setFont(centerFont);
        cardstatus.setFont(centerFont);
        cardpwdLabel.setFont(centerFont);
        cardpwdField.setPreferredSize(new Dimension(200, 30));
        cardpwdField.setFont(fieldFont);
        confirmfreezeBtn.setFont(buttonFont);
        confirmfreezeBtn.setBackground(customColor);


        reportloss.add(reportlossLabel);
        reportloss.add(cardstatusLabel);
        reportloss.add(cardstatus);
        reportloss.add(cardpwdLabel);
        reportloss.add(cardpwdField);
        reportloss.add(confirmfreezeBtn);

        springLayout.putConstraint(SpringLayout.WEST, reportlossLabel, -x, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, reportlossLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, cardstatusLabel, 400, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cardstatusLabel, 80, SpringLayout.SOUTH, reportlossLabel);
        springLayout.putConstraint(SpringLayout.WEST, cardstatus, 80, SpringLayout.EAST, cardstatusLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardstatus, 0, SpringLayout.NORTH, cardstatusLabel);
        springLayout.putConstraint(SpringLayout.WEST, cardpwdLabel, 0, SpringLayout.WEST, cardstatusLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardpwdLabel, 60, SpringLayout.SOUTH, cardstatusLabel);
        springLayout.putConstraint(SpringLayout.WEST, cardpwdField, 0, SpringLayout.WEST, cardstatus);
        springLayout.putConstraint(SpringLayout.NORTH, cardpwdField, 0, SpringLayout.NORTH, cardpwdLabel);
        springLayout.putConstraint(SpringLayout.WEST, confirmfreezeBtn, 525, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, confirmfreezeBtn, 120, SpringLayout.SOUTH, cardpwdLabel);


        cardPanel.add(blank);
        cardPanel.add(recharge, "充值");
        cardPanel.add(changepwd, "密码修改");
        cardPanel.add(cardimage, "卡片状态");
        cardPanel.add(bill, "账单");
        cardPanel.add(reportloss, "挂失/解挂");

        Container contentPane = getContentPane();//获取控制面板
        contentPane.add(cardPanel, BorderLayout.CENTER);
        contentPane.add(campuscard, BorderLayout.NORTH);
        contentPane.add(backPanel, BorderLayout.SOUTH);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        /**
         * 导航栏：充值
         * */
        rechargeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "充值");
                refreshPage();
            }
        });

        /**
         * 导航栏：账单
         * */
        billBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "账单");
                //显示账单信息
                IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
                // 直接调用billForSometime函数，时间范围设很大，query为空串""即可
                ShowTableData(iBankClientAPI.billForSometime(thisAccount.getId(), new Date("1/1/2000"), new Date("1/1/2050"), ""));
                refreshPage();
            }
        });

        /**
         * 导航栏：修改密码
         * */
        changepwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "密码修改");
                refreshPage();
            }
        });

        /**
         * 导航栏：卡片信息
         * */
        cardimageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPage();
                cardLayout.show(cardPanel, "卡片状态");

            }
        });

        /**
         * 导航栏：挂失/解挂
         * */
        freezeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPage();
                cardLayout.show(cardPanel, "挂失/解挂");
            }
        });

        /**
         * 退出按钮
         * */
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });

        /**
         * 用户点击确认充值按钮
         * */
        confirmrechargeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmRecharge();
                refreshPage();
            }
        });

        /**
         * 用户点击确认修改密码按钮
         * */
        confirmchangepwdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmchange();
                refreshPage();
            }
        });

        /**
         * 用户选好时间的月份之后
         * */
        month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billSearch();
            }
        });

        /**
         * 用户点击查询按钮之后
         * */
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billSearch();
                // 这里不用刷新页面，因为billSearch()函数里用showTable函数直接刷新了表格内容
                //refreshPage();
            }
        });


        /**
         * 用户确认改变挂失状态
         * */
        confirmfreezeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmfreeze();
                refreshPage();
            }
        });


    }

    public static void main(String[] args) {
//        try
//        {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        }
//        catch(Exception e)
//        {
//            //TODO exception
//        }
        try {
            // 设置外观为Windows外观
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            UIManager.put("nimbusBase", new Color(118, 218, 198)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(240, 255, 240)); // 按钮
            UIManager.put("control", new Color(240, 248, 240)); // 背景


        } catch (Exception e) {
            e.printStackTrace();
        }
        new BankTeacherStudentUI();
    }

    /**
     * 用户点击确认充值之后的操作
     */
    private void confirmRecharge() {
        String inputAmount = amountField.getText();
        double dInputAmount = Double.parseDouble(inputAmount);
        String inputPwd = rechargepwdField.getText();
        IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
        double result = iBankClientAPI.recharge(thisAccount.getId(), dInputAmount, inputPwd);
        if (result == -3.00) {
            JOptionPane.showMessageDialog(cardPanel, "充值失败");
        } else if (result == -2.00) {
            JOptionPane.showMessageDialog(cardPanel, "密码错误，请重新输入");
        } else if (result == -1.00) {
            JOptionPane.showMessageDialog(cardPanel, "卡已挂失");
        } else {
            JOptionPane.showMessageDialog(cardPanel, "充值成功，余额￥" + Double.toString(result));
        }
    }

    /**
     * 用户点击确认修改密码按钮之后的操作
     */
    private void confirmchange() {
        String oldPwd = oldpwdField.getText();
        String newPwd = newpwdField.getText();
        String newNewPwd = ensurepwdField.getText();
        IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
        int result = iBankClientAPI.changePwd(thisAccount.getId(), oldPwd, newPwd, newNewPwd);
        if (result == -1) {
            JOptionPane.showMessageDialog(changepwd, "系统错误！");
        } else if (result == 0) {
            JOptionPane.showMessageDialog(changepwd, "新密码两次输入不同");
        } else if (result == 1) {
            JOptionPane.showMessageDialog(changepwd, "修改成功");
        } else if (result == 2) {
            JOptionPane.showMessageDialog(changepwd, "原密码输入错误");
        }
    }

    /**
     * 用户通过时间和关键字查询账单
     */
    private void billSearch() {
        String selectedMonth = (String) month.getSelectedItem();
        String selectedYear = (String) year.getSelectedItem();
        String query = searchField.getText();
        String[][] bills = null;


        if (!selectedMonth.equals("") && !selectedYear.equals("")) {
            System.out.println("输入的年：" + selectedYear + "，输入的月：" + selectedMonth);
            IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);

            // 解析年份和月份
            int year = Integer.parseInt(selectedYear);
            int month = Integer.parseInt(selectedMonth);

            // 获取指定年份和月份的起始时间
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1); // 月份从0开始，所以需要减1
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date firstSecondOfMonth = calendar.getTime();
            System.out.println("第一秒：" + firstSecondOfMonth);

            // 设置为下个月的第一天
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.MILLISECOND, -1);

            // 获取指定年份和月份的结束时间
            Date lastSecondOfMonth = calendar.getTime();
            System.out.println("最后一秒：" + lastSecondOfMonth);

            bills = iBankClientAPI.billForSometime(thisAccount.getId(), firstSecondOfMonth, lastSecondOfMonth, query);

        }

        if (selectedMonth.equals("") || selectedYear.equals("")) {//如果年月没有选择，把时间范围设大
            System.out.println("输入的年:" + selectedYear + ",输入的月：" + selectedMonth);
            IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
            bills = iBankClientAPI.billForSometime(thisAccount.getId(), new Date("1/1/2000"), new Date("1/1/2050"), query);
        }
        ShowTableData(bills);
    }

    /**
     * 用户确认改变挂失状态
     */
    private void confirmfreeze() {
        String Pwd = cardpwdField.getText();
        System.out.println("改变挂失输入的密码为：" + Pwd);
        IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
        int result = iBankClientAPI.changeLoss(thisAccount.getId(), Pwd);
        if (result == -1) {
            JOptionPane.showMessageDialog(reportloss, "操作失败");
        } else if (result == 0) {
            JOptionPane.showMessageDialog(reportloss, "密码输入错误");
        } else if (result == 1) {
            JOptionPane.showMessageDialog(reportloss, "成功挂失");
        } else if (result == 2) {
            JOptionPane.showMessageDialog(reportloss, "成功解挂");
        }

    }

    /**
     * 显示账单表格信息。
     *
     * @param bills 账单数据，二维数组，每行代表一个账单记录
     */
    private void ShowTableData(String[][] bills) {
        //若查询结果为空
        if (bills == null) {
            System.out.println("查询结果为空");
            tableModel.setRowCount(0);
            return;
        }

        // 清空表格原有的数据
        tableModel.setRowCount(0);

        // 将新数据添加到表格模型
        for (String[] row : bills) {
            tableModel.addRow(row);
        }
        // 通知表格模型数据发生变化，刷新表格显示
        tableModel.fireTableDataChanged();
    }

    /**
     * 从后端获取account
     */
    public void getAccount() {
        IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
        thisAccount = iBankClientAPI.findBankAccountById(GlobalData.getUID());
    }

    /**
     * 刷新页面上的信息，包括余额、状态、输入框内容的清空等操作。
     */
    public void refreshPage() {
        getAccount();
        if(thisAccount!=null){
            // 刷新余额显示
            balance.setText("￥" + Double.toString(thisAccount.getBalance()));
            account.setText(thisAccount.getCardId());
            name.setText(thisAccount.getName());
            id.setText(thisAccount.getId());
            id2.setText(thisAccount.getId());
            balance2.setText("￥" + thisAccount.getBalance());
            status.setText(thisAccount.isLoss() ? "正常" : "已挂失");
            System.out.println("--------------");
            System.out.println(Boolean.toString(thisAccount.isLoss()));
        }

        // 清空输入框内容
        amountField.setText("");
        rechargepwdField.setText("");
        oldpwdField.setText("");
        newpwdField.setText("");
        ensurepwdField.setText("");
        searchField.setText("");
        cardpwdField.setText("");

        // 更新余额、状态、卡状态的显示
        balance2.setText("￥" + thisAccount.getBalance());
        status.setText(thisAccount.isLoss() ? "正常" : "已挂失");
        cardstatus.setText(thisAccount.isLoss() ? "正常" : "已挂失");

        // 更新确认挂失按钮的文本和可用状态
        confirmfreezeBtn.setText(thisAccount.isLoss() ? "确认挂失" : "确认解挂");

    }


    /**
     * 自定义表格单元格背景颜色渲染器。
     */
    static class TableBackgroundColorRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                setForeground(Color.BLACK);
            } else {
                // 设置单元格背景颜色
                if (row % 2 == 0) {
                    Color customColor = new Color(225, 235, 155);
                    cellComponent.setBackground(customColor);
                } else {
                    Color customColor2 = new Color(225, 235, 205);
                    cellComponent.setBackground(customColor2);
                }
            }
            return cellComponent;
        }
    }
}
