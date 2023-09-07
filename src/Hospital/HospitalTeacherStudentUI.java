package view.Hospital;

import view.Global.SummaryUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.html.Option;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class HospitalTeacherStudentUI extends JFrame {
    //导航栏
    JButton registerBtn;
    JButton registerhistoryBtn;
    JButton payBtn;
    JButton backBtn;

    //预约挂号
    DefaultTableModel model;
    String[] registerheader;
    Object[][] registerdata;
    JTable doctortable;
    JComboBox<String>department;
    JComboBox<String>type;
    JButton searchBtn;
    JLabel departmentLabel;
    JLabel typeLabel;

    //挂号记录
    DefaultTableModel model2;
    JLabel historyLabel;
    JTable historytable;

    //缴费
    DefaultTableModel model3;
    JTable purchasetable;
    JLabel purchaseLabel;
    JButton purchaseBtn;
    JLabel totalamountLabel;
    JLabel totalamount;
    JScrollPane purchasepane;
    String[] purchaseheader;


    JPanel backPanel=new JPanel();
    CardLayout cardLayout=new CardLayout();
    SpringLayout springLayout=new SpringLayout();
    JPanel cardPanel=new JPanel(cardLayout);
    JPanel hospitalcard=new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel registerPanel = new JPanel(springLayout);
    JPanel registerhistoryPanel = new JPanel(springLayout);
    JPanel payPanel=new JPanel(springLayout);

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体

    public HospitalTeacherStudentUI(){
        super("医院");

//        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
//        Image image = new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        registerBtn=new JButton("预约挂号");
        registerhistoryBtn=new JButton("挂号记录");
        payBtn=new JButton("缴费");
        backBtn=new JButton("退出");

        registerhistoryBtn.setFont(buttonFont);
        registerBtn.setFont(buttonFont);
        payBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);
        registerhistoryBtn.setPreferredSize(new Dimension(150, 40));
        registerBtn.setPreferredSize(new Dimension(150, 40));
        payBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setPreferredSize(new Dimension(100, 40));

        hospitalcard.add(registerBtn);
        hospitalcard.add(registerhistoryBtn);
        hospitalcard.add(payBtn);
        backPanel.add(backBtn);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "预约挂号");
            }
        });
        registerhistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "挂号记录");
            }
        });
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "缴费");
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //预约挂号
        model = new DefaultTableModel();
        registerheader = new String[]{"科室编号","科室类型", "挂号医生", "科室电话", "科室地址","医生类型", "操作"};
        registerdata = new Object[][]{
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
        };
        doctortable=new JTable();
        department=new JComboBox<String>();
        type=new JComboBox<String>();
        searchBtn=new JButton("查询");
        departmentLabel=new JLabel("科室类型");
        typeLabel=new JLabel("医生类型");

        doctortable.setFont(new Font("楷体",Font.PLAIN,20));
        model.setDataVector(registerdata, registerheader);
        doctortable.setModel(model);
        doctortable.getColumnModel().getColumn(6).setCellRenderer(new HospitalTeacherStudentUI.TableCellRendererButton());
        doctortable.getColumnModel().getColumn(6).setCellEditor(new HospitalTeacherStudentUI.TableCellEditorButton());
        JScrollPane doctorPane = new JScrollPane(doctortable);
        doctortable.setRowHeight(30);
        doctorPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = doctortable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        searchBtn.setFont(buttonFont);
        type.setFont(new Font("楷体",Font.PLAIN,20));
        department.setFont(new Font("楷体",Font.PLAIN,20));
        departmentLabel.setFont(centerFont);
        typeLabel.setFont(centerFont);

        department.addItem("");
        department.addItem("内科");
        department.addItem("外科");
        department.addItem("儿科");
        department.addItem("口腔科");
        department.addItem("耳鼻口科");
        department.addItem("骨科");
        department.addItem("脑科");
        department.addItem("皮肤科");
        type.addItem("");
        type.addItem("专家");
        type.addItem("普通");

        department.setPreferredSize(new Dimension(120,40));
        type.setPreferredSize(new Dimension(120,40));

        registerPanel.add(doctorPane);
        registerPanel.add(department);
        registerPanel.add(type);
        registerPanel.add(searchBtn);
        registerPanel.add(departmentLabel);
        registerPanel.add(typeLabel);

        springLayout.putConstraint(SpringLayout.WEST,departmentLabel,80,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,departmentLabel,80,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,department,20,SpringLayout.EAST,departmentLabel);
        springLayout.putConstraint(SpringLayout.NORTH,department,0,SpringLayout.NORTH,departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST,typeLabel,20,SpringLayout.EAST,department);
        springLayout.putConstraint(SpringLayout.NORTH,typeLabel,0,SpringLayout.NORTH,departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST,type,20,SpringLayout.EAST,typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,type,0,SpringLayout.NORTH,departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST,searchBtn,40,SpringLayout.EAST,type);
        springLayout.putConstraint(SpringLayout.NORTH,searchBtn,0,SpringLayout.NORTH,departmentLabel);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST,doctorPane,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,doctorPane,40,SpringLayout.SOUTH,departmentLabel);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据下拉框中的信息更新表格
            }
        });

        //挂号记录
        model2 = new DefaultTableModel();
        historyLabel=new JLabel("挂号记录");
        historytable=new JTable();

        historyLabel.setFont(titleFont);
        historytable.setFont(new Font("楷体",Font.PLAIN,20));

        String[] historyheader = {"挂号编号","科室编号", "挂号医生", "挂号时间", "总金额" ,"状态"};
        Object[][] historydata = {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
        };
        model2.setDataVector(historydata,historyheader);
        historytable.setModel(model2);
        JScrollPane historyscrollPane = new JScrollPane(historytable);
        historytable.setRowHeight(30);
        historyscrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header2 = historytable.getTableHeader();                    //获取表头
        tab_header2.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header2.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));

        registerhistoryPanel.add(historyscrollPane);
        registerhistoryPanel.add(historyLabel);

        springLayout.putConstraint(SpringLayout.NORTH,historyLabel,40,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,historyLabel,500,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,historyscrollPane,40,SpringLayout.SOUTH,historyLabel);
        springLayout.putConstraint(SpringLayout.WEST,historyscrollPane,100,SpringLayout.WEST,cardPanel);

        //缴费
        model3 = new DefaultTableModel();
        purchasetable=new JTable();
        purchaseLabel=new JLabel("缴费");
        purchaseBtn=new JButton("支付");
        totalamountLabel=new JLabel("总金额");
        totalamount=new JLabel("￥0.00");

        purchasetable.setFont(new Font("楷体",Font.PLAIN,20));
        Object[][] good={
                {null,null,null,null,false},
                {null,null,null,null,false}
        };
        purchaseheader= new String[]{"挂号编号","科室编号", "挂号时间", "金额", "操作"};
        model3.setDataVector(good,purchaseheader);
        purchasetable.setModel(model3);
        purchasetable.getColumnModel().getColumn(4).setCellRenderer(new HospitalTeacherStudentUI.CheckBoxRenderer());
        purchasetable.getColumnModel().getColumn(4).setCellEditor(new HospitalTeacherStudentUI.CheckBoxEditor());

        purchasetable.setRowHeight(30);
        purchasepane=new JScrollPane(purchasetable);
        purchasepane.setPreferredSize(new Dimension(1000, 300));
        JTableHeader tab_header3 = purchasetable.getTableHeader();                    //获取表头
        tab_header3.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header3.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));
        purchaseLabel.setFont(titleFont);
        purchaseBtn.setFont(buttonFont);
        totalamountLabel.setFont(centerFont);
        totalamount.setFont(centerFont);

        payPanel.add(purchaseLabel);
        payPanel.add(purchaseBtn);
        payPanel.add(totalamountLabel);
        payPanel.add(totalamount);
        payPanel.add(purchasepane);

        springLayout.putConstraint(SpringLayout.NORTH,purchaseLabel,40,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,purchaseLabel,540,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,purchasepane,40,SpringLayout.SOUTH,purchaseLabel);
        springLayout.putConstraint(SpringLayout.WEST,purchasepane,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,purchaseBtn,80,SpringLayout.SOUTH,purchasepane);
        springLayout.putConstraint(SpringLayout.WEST,purchaseBtn,550,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,totalamountLabel,40,SpringLayout.SOUTH,purchasepane);
        springLayout.putConstraint(SpringLayout.WEST,totalamountLabel,800,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,totalamount,0,SpringLayout.NORTH,totalamountLabel);
        springLayout.putConstraint(SpringLayout.WEST,totalamount,40,SpringLayout.EAST,totalamountLabel);

        purchaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //支付选中的商品
                new HospitalTeacherStudentUI.purchasewindow();
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(registerPanel, "预约挂号");
        cardPanel.add(registerhistoryPanel, "挂号记录");
        cardPanel.add(payPanel,"缴费");

        Container contentPane = getContentPane();//获取控制面板
        contentPane.add(cardPanel, BorderLayout.CENTER);
        contentPane.add(hospitalcard, BorderLayout.NORTH);
        contentPane.add(backPanel, BorderLayout.SOUTH);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    class TableCellRendererButton implements TableCellRenderer {
        JButton button = new JButton("挂号");

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            button.setFont(new Font("楷体", Font.PLAIN, 25));
            return button;
        }

    }

    class TableCellEditorButton extends DefaultCellEditor {
        JButton btn = new JButton();
        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("挂号");


            btn.setFont(new Font("楷体", Font.PLAIN, 25));
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    new HospitalTeacherStudentUI.choosetimewindow();

                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            return btn;
        }
    }

    class CheckBoxRenderer extends DefaultTableCellRenderer {
        private JCheckBox checkBox = new JCheckBox();

        public CheckBoxRenderer() {
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
                return checkBox;
            } else {
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        }
    }

    class CheckBoxEditor extends DefaultCellEditor {
        private JCheckBox checkBox = new JCheckBox();

        public CheckBoxEditor() {
            super(new JCheckBox());
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            }
            return checkBox;
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }
    }

    class choosetimewindow extends JFrame{
        JComboBox<String>time;
        JLabel timeLabel;
        JButton confirmBtn;
        JButton cancelBtn;
        JPanel timePanel;

        public choosetimewindow(){
            super("请选择时间段");

            timePanel=new JPanel(springLayout);
            time=new JComboBox<String>();
            timeLabel=new JLabel("时间段");
            confirmBtn=new JButton("确定");
            cancelBtn=new JButton("取消");

            time.addItem("");
            time.addItem("8:00-10:00");
            time.addItem("10:00-12:00");
            time.addItem("14:00-16:00");
            time.addItem("16:00-18:00");
            time.addItem("19:00-21:00");
            time.addItem("21:00-23:00");

            time.setPreferredSize(new Dimension(150,40));
            time.setFont(new Font("楷体",Font.PLAIN,20));
            timeLabel.setFont(centerFont);
            confirmBtn.setFont(buttonFont);
            cancelBtn.setFont(buttonFont);

            timePanel.add(time);
            timePanel.add(timeLabel);
            timePanel.add(confirmBtn);
            timePanel.add(cancelBtn);

            springLayout.putConstraint(SpringLayout.WEST,timeLabel,60,SpringLayout.WEST,timePanel);
            springLayout.putConstraint(SpringLayout.NORTH,timeLabel,20,SpringLayout.NORTH,timePanel);
            springLayout.putConstraint(SpringLayout.WEST,time,20,SpringLayout.EAST,timeLabel);
            springLayout.putConstraint(SpringLayout.NORTH,time,0,SpringLayout.NORTH,timeLabel);
            springLayout.putConstraint(SpringLayout.WEST,confirmBtn,90,SpringLayout.WEST,timePanel);
            springLayout.putConstraint(SpringLayout.NORTH,confirmBtn,40,SpringLayout.SOUTH,timeLabel);
            springLayout.putConstraint(SpringLayout.WEST,cancelBtn,40,SpringLayout.EAST,confirmBtn);
            springLayout.putConstraint(SpringLayout.NORTH,cancelBtn,0,SpringLayout.NORTH,confirmBtn);

            confirmBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //添加挂号信息
                    JOptionPane.showMessageDialog(timePanel,"挂号成功！");
                    dispose();
                }
            });
            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            Container contentPane = getContentPane();//获取控制面板
            contentPane.add(timePanel);
            setSize(400, 200);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
    }

    class purchasewindow extends JFrame{
        //导航栏
        JPanel modeofpaymentPanel=new JPanel();
        JPanel paymentPanel=new JPanel(cardLayout);
        JPanel cardpaymentPanel=new JPanel(springLayout);
        JPanel WeChatpaymentPanel=new JPanel(springLayout);
        JPanel cancelPanel=new JPanel();
        JButton cancelBtn=new JButton("取消");
        JButton cardpaymentBtn=new JButton("一卡通支付");
        JButton WeChatpaymentBtn=new JButton("微信支付");

        //一卡通支付
        JButton confirmpurchaseBtn=new JButton("确认支付");
        JLabel pwdLabel=new JLabel("密码");
        JPasswordField pwdField=new JPasswordField();

        public purchasewindow()
        {
            super("请选择支付方式（推荐使用微信支付）");
            //导航栏
            cardpaymentBtn.setFont(buttonFont);
            WeChatpaymentBtn.setFont(buttonFont);
            cancelBtn.setFont(buttonFont);

            modeofpaymentPanel.add(WeChatpaymentBtn);
            modeofpaymentPanel.add(cardpaymentBtn);
            cancelPanel.add(cancelBtn);

            cardpaymentBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(paymentPanel, "一卡通支付");
                }
            });
            WeChatpaymentBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(paymentPanel, "微信支付");
                }
            });
            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            //微信支付
            JLabel picture=new JLabel();
            ImageIcon img=new ImageIcon("./src/WeChatpayment.png");
            picture.setIcon(img);
            WeChatpaymentPanel.add(picture);

            springLayout.putConstraint(SpringLayout.WEST,picture,170,SpringLayout.WEST,WeChatpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH,picture,0,SpringLayout.NORTH,WeChatpaymentPanel);

            //一卡通支付
            confirmpurchaseBtn.setFont(buttonFont);
            pwdLabel.setFont(centerFont);
            pwdField.setFont(centerFont);
            pwdField.setPreferredSize(new Dimension(200, 30));

            cardpaymentPanel.add(confirmpurchaseBtn);
            cardpaymentPanel.add(pwdLabel);
            cardpaymentPanel.add(pwdField);

            springLayout.putConstraint(SpringLayout.WEST,pwdLabel,150,SpringLayout.WEST,cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,80,SpringLayout.NORTH,cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.WEST,pwdField,40,SpringLayout.EAST,pwdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
            springLayout.putConstraint(SpringLayout.WEST,confirmpurchaseBtn,225,SpringLayout.WEST,cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH,confirmpurchaseBtn,60,SpringLayout.SOUTH,pwdLabel);

            confirmpurchaseBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //判断密码是否正确（跳弹窗提示密码错误）
                    //判断余额，确认支付（跳弹窗提示余额不足）
                    //账户减去支付的金额
                    JOptionPane.showMessageDialog(cardpaymentPanel, "支付成功！");
                    dispose();
                    //从购物车里删除已支付的商品
                }
            });


            paymentPanel.add(WeChatpaymentPanel,"微信支付");
            paymentPanel.add(cardpaymentPanel,"一卡通支付");

            Container pane=getContentPane();
            pane.add(modeofpaymentPanel,BorderLayout.NORTH);
            pane.add(paymentPanel,BorderLayout.CENTER);
            pane.add(cancelPanel,BorderLayout.SOUTH);
            setSize(600,400);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
    }

    public static void main(String[] args) {
        new HospitalTeacherStudentUI();
    }
}
