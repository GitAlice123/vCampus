package view.Hospital;

import view.Bank.bankAccount;
import view.DAO.bankAccountDao;
import view.Global.SummaryUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HospitalManagerUI extends JFrame {
    String[][] registers = null;//显示的购买记录
    String[][] departments = null;//显示的医生信息

    //导航栏
    JButton informationBtn;
    JButton adddeleteBtn;
    JButton historyBtn;
    JButton backBtn;
    //医生信息
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
    //医院编号
    JLabel addLabel;
    JLabel IDLabel;
    JLabel typeLabel;
    JLabel nameLabel;
    JLabel doctortypeLabel;
    JComboBox<String> doctortype;
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

    JPanel backPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    SpringLayout springLayout = new SpringLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel hospitalcard = new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel informationPanel = new JPanel(springLayout);
    JPanel registerhistoryPanel = new JPanel(springLayout);
    JPanel adddeletePanel = new JPanel(springLayout);

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体

    public HospitalManagerUI() {
        super("医院");

//        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
//        Image image = new ImageIcon(resource).getImage();
//        setIconImage(image);

        informationBtn = new JButton("医生信息");
        adddeleteBtn = new JButton("增删医生");
        historyBtn = new JButton("挂号记录");
        backBtn = new JButton("退出");

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
                cardLayout.show(cardPanel, "医生信息");
                try {
                    getAllDepartments();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel(departments);
            }
        });
        adddeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "增删医生");

            }
        });
        historyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "挂号记录");
                try {
                    getAllRegisterRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel2(registers);
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //医生信息
        model = new DefaultTableModel();
        informationheader = new String[]{"医生编号", "科室类型", "挂号医生", "科室电话", "科室地址", "医生类型"};
        informationdata = new Object[][]{
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
        };
        informationtable = new JTable();
        searchBtn = new JButton("查询");
        searchField = new JTextField();

        informationtable.setFont(new Font("楷体", Font.PLAIN, 20));
        model.setDataVector(informationdata, informationheader);
        informationtable.setModel(model);
        JScrollPane informationPane = new JScrollPane(informationtable);
        informationtable.setRowHeight(30);
        informationPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = informationtable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setFont(centerFont);
        searchBtn.setFont(buttonFont);

        informationPanel.add(searchField);
        informationPanel.add(searchBtn);
        informationPanel.add(informationPane);

        springLayout.putConstraint(SpringLayout.WEST, searchField, 120, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, searchField, 80, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, searchBtn, 40, SpringLayout.EAST, searchField);
        springLayout.putConstraint(SpringLayout.NORTH, searchBtn, 0, SpringLayout.NORTH, searchField);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST, informationPane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, informationPane, 40, SpringLayout.SOUTH, searchField);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据文本框中输入的科室ID查找科室
                String dID = searchField.getText();
                HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
                try {
                    Department[] departments1 = new Department[1];
                    departments1[0] = hospitalClientAPI.GetDepartmentByID(dID);
                    departments = convertDepartmentToStringArray(departments1);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel(departments);

            }
        });

        //挂号记录
        model2 = new DefaultTableModel();
        historyLabel = new JLabel("挂号记录");
        historytable = new JTable();

        historyLabel.setFont(titleFont);
        historytable.setFont(new Font("楷体", Font.PLAIN, 20));

        String[] historyheader = {"挂号编号", "学生姓名", "科室名称", "挂号时间", "总金额", "状态"};
        Object[][] historydata = {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
        };
        model2.setDataVector(historydata, historyheader);
        historytable.setModel(model2);
        JScrollPane historyscrollPane = new JScrollPane(historytable);
        historytable.setRowHeight(30);
        historyscrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header2 = historytable.getTableHeader();                    //获取表头
        tab_header2.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header2.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));

        registerhistoryPanel.add(historyscrollPane);
        registerhistoryPanel.add(historyLabel);

        springLayout.putConstraint(SpringLayout.NORTH, historyLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, historyLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, historyscrollPane, 40, SpringLayout.SOUTH, historyLabel);
        springLayout.putConstraint(SpringLayout.WEST, historyscrollPane, 100, SpringLayout.WEST, cardPanel);

        //增删医生
        addLabel = new JLabel("添加医生");
        IDLabel = new JLabel("医生编号");
        typeLabel = new JLabel("科室类型");
        nameLabel = new JLabel("挂号医生");
        phonenumberLabel = new JLabel("科室电话");
        addressLabel = new JLabel("科室地址");
        doctortypeLabel = new JLabel("医生类型");
        doctortype = new JComboBox<String>();
        IDField = new JTextField();
        typeField = new JTextField();
        nameField = new JTextField();
        phonenumberField = new JTextField();
        addressField = new JTextField();
        confirmaddBtn = new JButton("确认添加");
        deleteLabel = new JLabel("删除医生");
        IDLabel2 = new JLabel("医生编号");
        IDField2 = new JTextField();
        confirmdeleteBtn = new JButton("确认删除");

        addLabel.setFont(titleFont);
        IDLabel.setFont(centerFont);
        typeLabel.setFont(centerFont);
        nameLabel.setFont(centerFont);
        phonenumberLabel.setFont(centerFont);
        addressLabel.setFont(centerFont);
        doctortypeLabel.setFont(centerFont);
        doctortype.setFont(new Font("楷体", Font.PLAIN, 20));
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

        //doctortype.addItem("");
        doctortype.addItem("专家");
        doctortype.addItem("普通");

        IDField.setPreferredSize(new Dimension(200, 30));
        typeField.setPreferredSize(new Dimension(200, 30));
        nameField.setPreferredSize(new Dimension(200, 30));
        phonenumberField.setPreferredSize(new Dimension(200, 30));
        addressField.setPreferredSize(new Dimension(200, 30));
        IDField2.setPreferredSize(new Dimension(200, 30));
        doctortype.setPreferredSize(new Dimension(80, 40));

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

        springLayout.putConstraint(SpringLayout.NORTH, addLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, addLabel, 240, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, IDLabel, 40, SpringLayout.SOUTH, addLabel);
        springLayout.putConstraint(SpringLayout.WEST, IDLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, IDField, 0, SpringLayout.NORTH, IDLabel);
        springLayout.putConstraint(SpringLayout.WEST, IDField, 60, SpringLayout.EAST, IDLabel);
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 40, SpringLayout.SOUTH, IDLabel);
        springLayout.putConstraint(SpringLayout.WEST, typeLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, typeField, 0, SpringLayout.NORTH, typeLabel);
        springLayout.putConstraint(SpringLayout.WEST, typeField, 60, SpringLayout.EAST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.SOUTH, typeLabel);
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLabel);
        springLayout.putConstraint(SpringLayout.WEST, nameField, 60, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, phonenumberLabel, 40, SpringLayout.SOUTH, nameLabel);
        springLayout.putConstraint(SpringLayout.WEST, phonenumberLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, phonenumberField, 0, SpringLayout.NORTH, phonenumberLabel);
        springLayout.putConstraint(SpringLayout.WEST, phonenumberField, 60, SpringLayout.EAST, phonenumberLabel);
        springLayout.putConstraint(SpringLayout.NORTH, addressLabel, 40, SpringLayout.SOUTH, phonenumberLabel);
        springLayout.putConstraint(SpringLayout.WEST, addressLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, addressField, 0, SpringLayout.NORTH, addressLabel);
        springLayout.putConstraint(SpringLayout.WEST, addressField, 60, SpringLayout.EAST, addressLabel);
        springLayout.putConstraint(SpringLayout.NORTH, doctortypeLabel, 40, SpringLayout.SOUTH, addressLabel);
        springLayout.putConstraint(SpringLayout.WEST, doctortypeLabel, 150, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, doctortype, 0, SpringLayout.NORTH, doctortypeLabel);
        springLayout.putConstraint(SpringLayout.WEST, doctortype, 60, SpringLayout.EAST, doctortypeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, confirmaddBtn, 40, SpringLayout.SOUTH, doctortypeLabel);
        springLayout.putConstraint(SpringLayout.WEST, confirmaddBtn, 270, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, deleteLabel, 750, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, IDLabel2, 0, SpringLayout.NORTH, IDLabel);
        springLayout.putConstraint(SpringLayout.WEST, IDLabel2, 650, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, IDField2, 0, SpringLayout.NORTH, IDLabel2);
        springLayout.putConstraint(SpringLayout.WEST, IDField2, 60, SpringLayout.EAST, IDLabel2);
        springLayout.putConstraint(SpringLayout.NORTH, confirmdeleteBtn, 60, SpringLayout.SOUTH, IDLabel2);
        springLayout.putConstraint(SpringLayout.WEST, confirmdeleteBtn, 780, SpringLayout.WEST, cardPanel);

        confirmaddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //添加医生
                String ID = IDField.getText();
                String type = typeField.getText();
                String DorName = nameField.getText();
                String phoneNumber = phonenumberField.getText();
                String address = addressField.getText();
                boolean DorType = false;
                String selectedValue = (String) doctortype.getSelectedItem();
                if (selectedValue.equals("专家")) {
                    DorType = true;
                } else {
                    DorType = false;
                }

                Department d = new Department(type, ID, DorName, DorType, phoneNumber, address);
                HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);

                try {
                    if (hospitalClientAPI.AddDepartmentByInfo(d)) {
                        JOptionPane.showMessageDialog(adddeletePanel, "添加成功！");
                    } else {
                        JOptionPane.showMessageDialog(adddeletePanel, "添加失败！");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                refreshpage();
            }
        });
        confirmdeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //删除医生
                HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
                String uID = IDField2.getText();
                //修改后端数据
                try {
                    if (hospitalClientAPI.DeleteDepartmentByID(uID)) {
                        JOptionPane.showMessageDialog(adddeletePanel, "成功删除！");
                    } else {
                        JOptionPane.showMessageDialog(adddeletePanel, "删除失败！");
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                refreshpage();
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(informationPanel, "医生信息");
        cardPanel.add(registerhistoryPanel, "挂号记录");
        cardPanel.add(adddeletePanel, "增删医生");

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

    //前端获取所有的医生信息
    public void getAllDepartments() throws IOException {
        HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
        departments = convertDepartmentToStringArray(hospitalClientAPI.GetAllDepartments());
    }

    //前端获取所有的挂号记录
    public void getAllRegisterRecord() throws IOException {
        HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
        registers = convertRegisterToStringArray(hospitalClientAPI.GetAllRegisters());
    }

    /**
     * 将Department[]类数据转换为String[][]。
     *
     * @param departments Department对象数组
     * @return 转换后的String二维数组
     */
    public String[][] convertDepartmentToStringArray(Department[] departments) {
        if (departments == null) {
            return null;
        }
        String[][] departmentStringArray = new String[departments.length][6];

        for (int i = 0; i < departments.length; i++) {
            Department department = departments[i];
            departmentStringArray[i][0] = department.Department_ID;//医院编号
            departmentStringArray[i][1] = department.Department_name;//科室名称
            departmentStringArray[i][2] = department.Department_dir;//挂号医生
            departmentStringArray[i][3] = department.Department_phone;//科室电话
            departmentStringArray[i][4] = department.Department_addr;//科室地址
            departmentStringArray[i][5] = department.Department_level ? "专家" : "普通";
        }

        return departmentStringArray;
    }

    /**
     * 将Register对象数组转换为String二维数组。
     *
     * @param registers Register对象数组
     * @return 转换后的String二维数组
     */
    public String[][] convertRegisterToStringArray(Register[] registers) {
        if (registers == null) {
            return null;
        }
        String[][] registerStringArray = new String[registers.length][6];
        bankAccountDao bA = new bankAccountDao();
        for (int i = 0; i < registers.length; i++) {
            Register register = registers[i];
            bankAccount bankA = bA.findBankAccountById(register.Patient_ID);

            registerStringArray[i][0] = register.getRegister_ID();//挂号编号
            registerStringArray[i][1] = bankA.getName();//学生姓名
            registerStringArray[i][2] = register.getRegister_depart();//挂号科室
            registerStringArray[i][3] = formatDate(register.getRegister_date());//挂号时间
            registerStringArray[i][4] = String.valueOf(register.getRegister_amount());//总金额
            registerStringArray[i][5] = register.Register_Ifpaid ? "已缴费" : "未缴费";
        }

        return registerStringArray;
    }

    /**
     * 格式化日期对象为"yyyy-MM-dd"的字符串表示形式。
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 显示医生信息的表格信息
     */
    private void ShowTableDataModel(String[][] data) {
        //若查询结果为空
        if (data == null) {

            System.out.println("查询结果为空************");
            model.setRowCount(0);
            return;
        }

        // 清空表格原有的数据
        model.setRowCount(0);

        // 将新数据添加到表格模型
        for (String[] row : data) {
            model.addRow(row);
        }
        // 通知表格模型数据发生变化，刷新表格显示
        model.fireTableDataChanged();
    }

    /**
     * 显示挂号记录的表格信息
     */
    private void ShowTableDataModel2(String[][] data) {
        //若查询结果为空
        if (data == null) {
            System.out.println("查询结果为空");
            model2.setRowCount(0);
            return;
        }

        // 清空表格原有的数据
        model2.setRowCount(0);

        // 将新数据添加到表格模型
        for (String[] row : data) {
            model2.addRow(row);
        }
        // 通知表格模型数据发生变化，刷新表格显示
        model2.fireTableDataChanged();
    }

    /**
     * 清空文字框
     */
    public void refreshpage() {
        searchField.setText("");
        IDField.setText("");
        typeField.setText("");
        nameField.setText("");
        phonenumberField.setText("");
        addressField.setText("");
        IDField2.setText("");
    }
}
