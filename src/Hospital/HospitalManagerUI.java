package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class HospitalManagerUI extends JFrame {
    //导航栏
    JButton informationBtn;
    JButton adddeleteBtn;
    JButton historyBtn;
    JButton backBtn;
    //科室信息
    DefaultTableModel model;
    String[] informationheader;
    Object[][] informationdata;
    JTable informationtable;
    JButton searchBtn;
    JTextField searchField;
    //挂号记录
    DefaultTableModel model2;
    JLabel historyLabel;
    JTable historytable;
    //增删科室
    JLabel addLabel;
    JLabel IDLabel;
    JLabel typeLabel;
    JLabel nameLabel;
    JLabel doctortypeLabel;
    JComboBox<String>doctortype;
    JLabel phonenumberLabel;
    JLabel addressLabel;
    JTextField IDField;
    JTextField typeField;
    JTextField nameField;
    JTextField phonenumberField;
    JTextField addressField;
    JButton confirmaddBtn;
    JLabel deleteLabel;
    JLabel IDLabel2;
    JTextField IDField2;
    JButton confirmdeleteBtn;

    JPanel backPanel=new JPanel();
    CardLayout cardLayout=new CardLayout();
    SpringLayout springLayout=new SpringLayout();
    JPanel cardPanel=new JPanel(cardLayout);
    JPanel hospitalcard=new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel informationPanel = new JPanel(springLayout);
    JPanel registerhistoryPanel = new JPanel(springLayout);
    JPanel adddeletePanel=new JPanel(springLayout);

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体
    public HospitalManagerUI(){
        super("医院");

        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
        Image image = new ImageIcon(resource).getImage();
        setIconImage(image);

        informationBtn=new JButton("科室信息");
        adddeleteBtn=new JButton("增删科室");
        historyBtn=new JButton("挂号记录");
        backBtn=new JButton("退出");

        informationBtn.setFont(buttonFont);
        historyBtn.setFont(buttonFont);
        adddeleteBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);
        informationBtn.setPreferredSize(new Dimension(150, 40));
        adddeleteBtn.setPreferredSize(new Dimension(150, 40));
        historyBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setPreferredSize(new Dimension(100, 40));

        hospitalcard.add(informationBtn);
        hospitalcard.add(historyBtn);
        hospitalcard.add(adddeleteBtn);
        backPanel.add(backBtn);

        informationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "科室信息");
            }
        });
        adddeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "增删科室");
            }
        });
        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "挂号记录");
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //科室信息
        model = new DefaultTableModel();
        informationheader = new String[]{"科室编号","科室类型", "科室主任", "科室电话", "科室地址","医生类型"};
        informationdata = new Object[][]{
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
        };
        informationtable=new JTable();
        searchBtn=new JButton("查询");
        searchField=new JTextField();

        informationtable.setFont(new Font("楷体",Font.PLAIN,20));
        model.setDataVector(informationdata, informationheader);
        informationtable.setModel(model);
        JScrollPane informationPane = new JScrollPane(informationtable);
        informationtable.setRowHeight(30);
        informationPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = informationtable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        searchField.setPreferredSize(new Dimension(200,30));
        searchField.setFont(centerFont);
        searchBtn.setFont(buttonFont);

        informationPanel.add(searchField);
        informationPanel.add(searchBtn);
        informationPanel.add(informationPane);

        springLayout.putConstraint(SpringLayout.WEST,searchField,120,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,searchField,80,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,searchBtn,40,SpringLayout.EAST,searchField);
        springLayout.putConstraint(SpringLayout.NORTH,searchBtn,0,SpringLayout.NORTH,searchField);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST,informationPane,100,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,informationPane,40,SpringLayout.SOUTH,searchField);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据文本框中输入的科室类型更新表格
            }
        });

        //挂号记录
        model2 = new DefaultTableModel();
        historyLabel=new JLabel("挂号记录");
        historytable=new JTable();

        historyLabel.setFont(titleFont);
        historytable.setFont(new Font("楷体",Font.PLAIN,20));

        String[] historyheader = {"挂号编号","学生姓名","科室编号","挂号时间", "总金额" ,"状态"};
        Object[][] historydata = {
                {null, null, null, null, null, null,null,null},
                {null, null, null, null, null, null,null,null}
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

        //增删科室
        addLabel=new JLabel("添加科室");
        IDLabel=new JLabel("科室编号");
        typeLabel=new JLabel("科室类型");
        nameLabel=new JLabel("科室主任");
        phonenumberLabel=new JLabel("科室电话");
        addressLabel=new JLabel("科室地址");
        doctortypeLabel=new JLabel("医生类型");
        doctortype=new JComboBox<String>();
        IDField=new JTextField();
        typeField=new JTextField();
        nameField=new JTextField();
        phonenumberField=new JTextField();
        addressField=new JTextField();
        confirmaddBtn=new JButton("确认添加");
        deleteLabel=new JLabel("删除科室");
        IDLabel2=new JLabel("科室编号");
        IDField2=new JTextField();
        confirmdeleteBtn=new JButton("确认删除");

        addLabel.setFont(titleFont);
        IDLabel.setFont(centerFont);
        typeLabel.setFont(centerFont);
        nameLabel.setFont(centerFont);
        phonenumberLabel.setFont(centerFont);
        addressLabel.setFont(centerFont);
        doctortypeLabel.setFont(centerFont);
        doctortype.setFont(new Font("楷体",Font.PLAIN,20));
        IDField.setFont(centerFont);
        typeField.setFont(centerFont);
        nameField.setFont(centerFont);
        phonenumberField.setFont(centerFont);
        addressField.setFont(centerFont);
        confirmaddBtn.setFont(buttonFont);
        deleteLabel.setFont(titleFont);
        IDLabel2.setFont(centerFont);
        IDField2.setFont(centerFont);
        confirmdeleteBtn.setFont(buttonFont);

        doctortype.addItem("");
        doctortype.addItem("专家");
        doctortype.addItem("普通");

        IDField.setPreferredSize(new Dimension(200,30));
        typeField.setPreferredSize(new Dimension(200,30));
        nameField.setPreferredSize(new Dimension(200,30));
        phonenumberField.setPreferredSize(new Dimension(200,30));
        addressField.setPreferredSize(new Dimension(200,30));
        IDField2.setPreferredSize(new Dimension(200,30));
        doctortype.setPreferredSize(new Dimension(80,40));

        adddeletePanel.add(addLabel);
        adddeletePanel.add(IDLabel);
        adddeletePanel.add(typeLabel);
        adddeletePanel.add(nameLabel);
        adddeletePanel.add(phonenumberLabel);
        adddeletePanel.add(addressLabel);
        adddeletePanel.add(doctortypeLabel);
        adddeletePanel.add(doctortype);
        adddeletePanel.add(IDField);
        adddeletePanel.add(typeField);
        adddeletePanel.add(nameField);
        adddeletePanel.add(phonenumberField);
        adddeletePanel.add(addressField);
        adddeletePanel.add(confirmaddBtn);
        adddeletePanel.add(deleteLabel);
        adddeletePanel.add(IDLabel2);
        adddeletePanel.add(IDField2);
        adddeletePanel.add(confirmdeleteBtn);

        springLayout.putConstraint(SpringLayout.NORTH,addLabel,40,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,addLabel,240,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,IDLabel,40,SpringLayout.SOUTH,addLabel);
        springLayout.putConstraint(SpringLayout.WEST,IDLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,IDField,0,SpringLayout.NORTH,IDLabel);
        springLayout.putConstraint(SpringLayout.WEST,IDField,60,SpringLayout.EAST,IDLabel);
        springLayout.putConstraint(SpringLayout.NORTH,typeLabel,40,SpringLayout.SOUTH,IDLabel);
        springLayout.putConstraint(SpringLayout.WEST,typeLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,typeField,0,SpringLayout.NORTH,typeLabel);
        springLayout.putConstraint(SpringLayout.WEST,typeField,60,SpringLayout.EAST,typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,nameLabel,40,SpringLayout.SOUTH,typeLabel);
        springLayout.putConstraint(SpringLayout.WEST,nameLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,nameField,0,SpringLayout.NORTH,nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,nameField,60,SpringLayout.EAST,nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,phonenumberLabel,40,SpringLayout.SOUTH,nameLabel);
        springLayout.putConstraint(SpringLayout.WEST,phonenumberLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,phonenumberField,0,SpringLayout.NORTH,phonenumberLabel);
        springLayout.putConstraint(SpringLayout.WEST,phonenumberField,60,SpringLayout.EAST,phonenumberLabel);
        springLayout.putConstraint(SpringLayout.NORTH,addressLabel,40,SpringLayout.SOUTH,phonenumberLabel);
        springLayout.putConstraint(SpringLayout.WEST,addressLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,addressField,0,SpringLayout.NORTH,addressLabel);
        springLayout.putConstraint(SpringLayout.WEST,addressField,60,SpringLayout.EAST,addressLabel);
        springLayout.putConstraint(SpringLayout.NORTH,doctortypeLabel,40,SpringLayout.SOUTH,addressLabel);
        springLayout.putConstraint(SpringLayout.WEST,doctortypeLabel,150,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,doctortype,0,SpringLayout.NORTH,doctortypeLabel);
        springLayout.putConstraint(SpringLayout.WEST,doctortype,60,SpringLayout.EAST,doctortypeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,confirmaddBtn,40,SpringLayout.SOUTH,doctortypeLabel);
        springLayout.putConstraint(SpringLayout.WEST,confirmaddBtn,270,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,deleteLabel,40,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,deleteLabel,750,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,IDLabel2,0,SpringLayout.NORTH,IDLabel);
        springLayout.putConstraint(SpringLayout.WEST,IDLabel2,650,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,IDField2,0,SpringLayout.NORTH,IDLabel2);
        springLayout.putConstraint(SpringLayout.WEST,IDField2,60,SpringLayout.EAST,IDLabel2);
        springLayout.putConstraint(SpringLayout.NORTH,confirmdeleteBtn,60,SpringLayout.SOUTH,IDLabel2);
        springLayout.putConstraint(SpringLayout.WEST,confirmdeleteBtn,780,SpringLayout.WEST,cardPanel);

        confirmaddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加科室
            }
        });
        confirmdeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除科室
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(informationPanel, "科室信息");
        cardPanel.add(registerhistoryPanel, "挂号记录");
        cardPanel.add(adddeletePanel,"增删科室");

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


    public static void main(String[] args) {
        new HospitalManagerUI();
    }
}
