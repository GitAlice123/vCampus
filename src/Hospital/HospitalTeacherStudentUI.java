package view.Hospital;

import view.Bank.IBankClientAPI;
import view.Bank.IBankClientAPIImpl;
import view.Bank.bankAccount;
import view.Bank.bankBill;
import view.DAO.bankAccountDao;
import view.Global.GlobalData;
import view.Global.SummaryStudentTeacherUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class HospitalTeacherStudentUI extends JFrame {
    String[][] registers = null;//显示用户自己的挂号记录
    String[][] departments = null;//显示的科室信息
    Object[][] unpaidreg = null;//未缴费的记录
    Double purchaseAmount = 0.0;//选中的缴费总金额
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
    JComboBox<String> department;
    JComboBox<String> type;
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
    JButton OKBtn;
    JLabel totalamountLabel;
    JLabel totalamount;
    JScrollPane purchasepane;
    String[] purchaseheader;


    JPanel backPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    SpringLayout springLayout = new SpringLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel hospitalcard = new JPanel();
    JPanel blankPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Hospital1.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, (int)(getWidth()*0.5), (int) (getHeight()*0.6), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 300,100 , null);

            g2d.dispose();
        }
    };
    JPanel registerPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Hospital2.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };
    JPanel registerhistoryPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Hospital2.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };
    JPanel payPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Hospital2.jpg");
            Image originalImage = originalImageIcon.getImage();

            // 创建与面板尺寸相同的缓冲图像
            BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedImage.createGraphics();

            // 设置透明度
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
            g2d.setComposite(alphaComposite);

            // 绘制背景图片到缓冲图像
            g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

            // 绘制缓冲图像到面板
            g.drawImage(bufferedImage, 0, 0, null);

            g2d.dispose();
        }
    };

    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体

    /**
     * HospitalTeacherStudentUI构造函数
     */
    public HospitalTeacherStudentUI() {
        super("医院");

//        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
//        Image image = new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        registerBtn = new JButton("预约挂号");
        registerhistoryBtn = new JButton("挂号记录");
        payBtn = new JButton("缴费");
        backBtn = new JButton("退出");

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
                try {
                    getAllDepartments();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel(departments);
            }
        });
        registerhistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "挂号记录");
                try {
                    getOwnRegisterRecord();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel2(registers);
            }
        });
        payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "缴费");
                //Object[][] unpaid=null;
                try {
                    unpaidreg = getAllUnpaidRegister();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                ShowTableDataModel3(unpaidreg);
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });

        //预约挂号
        model = new DefaultTableModel();
        registerheader = new String[]{"医生编号", "科室类型", "挂号医生", "科室电话", "科室地址", "医生类型", "操作"};
        registerdata = new Object[][]{
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
        };
        doctortable = new JTable();
        department = new JComboBox<String>();
        type = new JComboBox<String>();
        searchBtn = new JButton("查询");
        departmentLabel = new JLabel("科室类型");
        typeLabel = new JLabel("医生类型");

        doctortable.setFont(new Font("楷体", Font.PLAIN, 20));
        model.setDataVector(registerdata, registerheader);
        doctortable.setModel(model);
        doctortable.getColumnModel().getColumn(6).setCellRenderer(new TableCellRendererButton());
        doctortable.getColumnModel().getColumn(6).setCellEditor(new TableCellEditorButton());
        JScrollPane doctorPane = new JScrollPane(doctortable);
        doctortable.setRowHeight(30);
        doctorPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = doctortable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        doctorPane.setOpaque(false);
        doctorPane.getViewport().setBackground(new Color(255,255,255,150));

        searchBtn.setFont(buttonFont);
        type.setFont(new Font("楷体", Font.PLAIN, 20));
        department.setFont(new Font("楷体", Font.PLAIN, 20));
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

        department.setPreferredSize(new Dimension(120, 40));
        type.setPreferredSize(new Dimension(120, 40));

        registerPanel.add(doctorPane);
        registerPanel.add(department);
        registerPanel.add(type);
        registerPanel.add(searchBtn);
        registerPanel.add(departmentLabel);
        registerPanel.add(typeLabel);

        springLayout.putConstraint(SpringLayout.WEST, departmentLabel, 80, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, departmentLabel, 80, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, department, 20, SpringLayout.EAST, departmentLabel);
        springLayout.putConstraint(SpringLayout.NORTH, department, 0, SpringLayout.NORTH, departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST, typeLabel, 20, SpringLayout.EAST, department);
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 0, SpringLayout.NORTH, departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST, type, 20, SpringLayout.EAST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, type, 0, SpringLayout.NORTH, departmentLabel);
        springLayout.putConstraint(SpringLayout.WEST, searchBtn, 40, SpringLayout.EAST, type);
        springLayout.putConstraint(SpringLayout.NORTH, searchBtn, 0, SpringLayout.NORTH, departmentLabel);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST, doctorPane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, doctorPane, 40, SpringLayout.SOUTH, departmentLabel);


        // TODO
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据下拉框中的信息更新表格
                String selectedDepartment = (String) department.getSelectedItem();
                String selectedType = (String) type.getSelectedItem();
                System.out.println("科室类型：" + selectedDepartment);
                System.out.println("医生类型：" + selectedType);
                if (selectedType.equals("") && selectedDepartment.equals("")) {//没有输入的查询信息
                    //JOptionPane.showMessageDialog(registerPanel, "请选择查询信息！");
                    try {
                        getAllDepartments();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
                    try {
                        departments = convertDepartmentToStringArray(hospitalClientAPI.GetDepartmentByinfo(selectedDepartment, selectedType));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

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

        String[] historyheader = {"挂号编号", "科室类型", "挂号医生", "挂号时间", "总金额", "状态"};
        Object[][] historydata = {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
        };
        model2.setDataVector(historydata, historyheader);
        historytable.setModel(model2);
        JScrollPane historyscrollPane = new JScrollPane(historytable);
        historytable.setRowHeight(30);
        historyscrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header2 = historytable.getTableHeader();                    //获取表头
        tab_header2.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header2.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));

        historyscrollPane.setOpaque(false);
        historyscrollPane.getViewport().setBackground(new Color(255,255,255,150));

        registerhistoryPanel.add(historyscrollPane);
        registerhistoryPanel.add(historyLabel);

        springLayout.putConstraint(SpringLayout.NORTH, historyLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, historyLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, historyscrollPane, 40, SpringLayout.SOUTH, historyLabel);
        springLayout.putConstraint(SpringLayout.WEST, historyscrollPane, 100, SpringLayout.WEST, cardPanel);

        //缴费
        model3 = new DefaultTableModel();
        purchasetable = new JTable();
        purchaseLabel = new JLabel("缴费");
        purchaseBtn = new JButton("支付");
        OKBtn = new JButton("选好了");
        totalamountLabel = new JLabel("总金额");
        totalamount = new JLabel("￥0.00");

        purchasetable.setFont(new Font("楷体", Font.PLAIN, 20));
        Object[][] good = {
                {null, null, null, null, false},
                {null, null, null, null, false}
        };
        purchaseheader = new String[]{"挂号编号", "科室类型", "挂号时间", "金额", "操作"};
        model3.setDataVector(good, purchaseheader);
        purchasetable.setModel(model3);
        purchasetable.getColumnModel().getColumn(4).setCellRenderer(new CheckBoxRenderer());
        purchasetable.getColumnModel().getColumn(4).setCellEditor(new CheckBoxEditor());

        purchasetable.setRowHeight(30);
        purchasepane = new JScrollPane(purchasetable);
        purchasepane.setPreferredSize(new Dimension(1000, 300));
        JTableHeader tab_header3 = purchasetable.getTableHeader();                    //获取表头
        tab_header3.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header3.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));

        purchasepane.setOpaque(false);
        purchasepane.getViewport().setBackground(new Color(255,255,255,150));

        purchaseLabel.setFont(titleFont);
        purchaseBtn.setFont(buttonFont);
        OKBtn.setFont(buttonFont);
        totalamountLabel.setFont(centerFont);
        totalamount.setFont(centerFont);

        payPanel.add(purchaseLabel);
        payPanel.add(purchaseBtn);
        payPanel.add(totalamountLabel);
        payPanel.add(totalamount);
        payPanel.add(purchasepane);
        payPanel.add(OKBtn);

        springLayout.putConstraint(SpringLayout.NORTH, purchaseLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, purchaseLabel, 540, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, purchasepane, 40, SpringLayout.SOUTH, purchaseLabel);
        springLayout.putConstraint(SpringLayout.WEST, purchasepane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, OKBtn, 0, SpringLayout.NORTH, purchaseBtn);
        springLayout.putConstraint(SpringLayout.WEST, OKBtn, 100, SpringLayout.EAST, purchaseBtn);
        springLayout.putConstraint(SpringLayout.NORTH, purchaseBtn, 80, SpringLayout.SOUTH, purchasepane);
        springLayout.putConstraint(SpringLayout.WEST, purchaseBtn, 450, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, totalamountLabel, 40, SpringLayout.SOUTH, purchasepane);
        springLayout.putConstraint(SpringLayout.WEST, totalamountLabel, 800, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, totalamount, 0, SpringLayout.NORTH, totalamountLabel);
        springLayout.putConstraint(SpringLayout.WEST, totalamount, 40, SpringLayout.EAST, totalamountLabel);

        purchaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //支付选中的商品
                new purchasewindow();
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(registerPanel, "预约挂号");
        cardPanel.add(registerhistoryPanel, "挂号记录");
        cardPanel.add(payPanel, "缴费");

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

    /**
     * 可用于前端删除购物车物品
     */
    public static Object[][] deleteRow(Object[][] array, int rowIndex) {
        if (array == null || array.length == 0) {
            // 如果原始数组为 null 或者为空数组，则直接返回原始数组
            return array;
        }

        int numRows = array.length;
        int numCols = array[0].length;

        if (rowIndex < 0 || rowIndex >= numRows) {
            // 如果指定的行索引超出范围，则直接返回原始数组
            return array;
        }

        // 创建新数组，长度比原数组少 1
        Object[][] newArray = new Object[numRows - 1][numCols];

        int destRow = 0;
        for (int srcRow = 0; srcRow < numRows; srcRow++) {
            if (srcRow != rowIndex) {
                // 复制除要删除的行外的所有行到新数组中
                System.arraycopy(array[srcRow], 0, newArray[destRow], 0, numCols);
                destRow++;
            }
        }

        return newArray;
    }

    /**
     * @throws IOException
     * 前端获取所有的科室信息
     */
    //前端获取所有的科室信息
    public void getAllDepartments() throws IOException {
        HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
        departments = convertDepartmentToStringArray(hospitalClientAPI.GetAllDepartments());
    }

    /**
     * @throws IOException
     * 前端获取所有的挂号记录
     */
    //前端获取所有的挂号记录
    public void getOwnRegisterRecord() throws IOException {
        HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
        registers = convertRegisterToStringArray(hospitalClientAPI.GetRegisterByID(GlobalData.getUID()));
    }

    /**
     * @return
     * @throws IOException
     * 获取所有未缴费的挂号记录
     */
    //获取所有未缴费的挂号记录
    public Object[][] getAllUnpaidRegister() throws IOException {
        HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
        Register[] rs = hospitalClientAPI.GetPaymentByID(GlobalData.getUID());
        if (rs == null) {
            System.out.println("rs为空！！！！！！！");
        }
        String[][] unpaidRows = convertRegisterToStringArray(rs);
        if (unpaidRows == null) {
            System.out.println("unpaidRows为空！！！！！！！");
        }
        System.out.println("unpaidRows长度为：" + unpaidRows.length);
        if (unpaidRows == null) {
            return null;
        }
        // 统计未缴费行数
        int count = unpaidRows.length;

        // 创建新的二维数组，减去两列后的列数为 numCols - 2
        Object[][] result = new Object[count][5];
        for (int i = 0; i < count; i++) {
            result[i][4] = false;
        }

        // 复制数组元素到新数组，跳过第三列和最后一列
        for (int i = 0; i < count; i++) {
            for (int j = 0, k = 0; j < 6; j++) {
                if (j != 2 && j != 5) {
                    result[i][k] = unpaidRows[i][j];
                    k++;
                }
            }
        }
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(result[i][j] + " ");

            }
        }

        return result;

    }

    /**
     * 更新购物车界面选中商品的总金额
     */
    private void updatepurchaseAmount() {
        //DefaultTableModel model4 = (DefaultTableModel) purchasetable.getModel();
        int rowCount = model3.getRowCount();
        ArrayList<Integer> selectedRows = new ArrayList<>();
        purchaseAmount = 0.0;
        //删除购物车里被选中的商品
        for (int row = 0; row < rowCount; row++) {
            Boolean isSelected = (Boolean) model3.getValueAt(row, 4); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中

            System.out.println("内容为2222：" + isSelected);
            if (isSelected) {
                selectedRows.add(row); // 记录选中的行索引
                purchaseAmount = purchaseAmount + Double.parseDouble((String) unpaidreg[row][3]);
                System.out.println("选中了行数：" + row);
            }
        }
        String formattedAmount = String.format("%.2f", purchaseAmount);
        totalamount.setText("￥" + formattedAmount);
    }

    /**
     * 将Department[]类数据转换为String[][]。
     *
     * @param departments Department对象数组
     * @return 转换后的String二维数组
     */
    public String[][] convertDepartmentToStringArray(Department[] departments) {
        if (departments == null) {
            System.out.println("departments为空或-----------");
            return null;
        }
        String[][] departmentStringArray = new String[departments.length][6];

        for (int i = 0; i < departments.length; i++) {
            Department department = departments[i];
            departmentStringArray[i][0] = department.Department_ID;//医生编号
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
     * @param registers2 Register对象数组
     * @return 转换后的String二维数组
     */
    public String[][] convertRegisterToStringArray(Register[] registers2) throws IOException {
        if (registers2 == null) {
            return null;
        }
        String[][] registerStringArray = new String[registers2.length][6];
        bankAccountDao bA = new bankAccountDao();
        for (int i = 0; i < registers2.length; i++) {
            Register register = registers2[i];
            if (register == null) {
                return null;
            }
            bankAccount bankA = bA.findBankAccountById(register.Patient_ID);
            HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
            Department d = hospitalClientAPI.GetDepartmentByID(register.Register_depart);
            registerStringArray[i][0] = register.getRegister_ID();//挂号编号
            registerStringArray[i][1] = d.getDepartment_name();//科室名称
            registerStringArray[i][2] = d.getDepartment_dir();//挂号医生
            registerStringArray[i][3] = formatDate2(register.getRegister_date());//挂号时间
            //String formattedAmount = String.format("%.2f", purchaseAmount);
            registerStringArray[i][4] = String.format("%.2f", register.getRegister_amount());//总金额
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
     * 格式化日期对象为"yyyy/MM/dd HH:mm:ss"的字符串表示形式。
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    private String formatDate2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 显示商品的表格信息
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
        doctortable.getColumnModel().getColumn(6).setCellRenderer(new TableCellRendererButton());
        doctortable.getColumnModel().getColumn(6).setCellEditor(new TableCellEditorButton());
    }

    /**
     * 显示购买记录的表格信息
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
     * 显示购物车的表格信息
     */
    private void ShowTableDataModel3(Object[][] data) {
        //若查询结果为空
        if (data == null) {
            System.out.println("查询结果为空333333");
            model3.setRowCount(0);
            return;
        }

        // 清空表格原有的数据
        model3.setRowCount(0);

        // 将新数据添加到表格模型
        for (Object[] row : data) {
            model3.addRow(row);
        }

        // 通知表格模型数据发生变化，刷新表格显示
        model3.fireTableDataChanged();

        purchasetable.getColumnModel().getColumn(4).setCellRenderer(new CheckBoxRenderer());
        purchasetable.getColumnModel().getColumn(4).setCellEditor(new CheckBoxEditor());
    }

    /**
     * 获取当前日期和用户输入的整点时间拼接的精确到毫秒的日期时间。
     *
     * @param hour 用户输入的小时数（0-23）。
     * @return 拼接后的精确到毫秒的日期时间。
     */
    public Date getDateTimeWithUserTime(String hour) {
        String hour2 = hour.substring(0, 5);
        String[] parts = hour2.split(":");
        int intHour = Integer.parseInt(parts[0]);


        Calendar calendar = Calendar.getInstance(); // 获取当前日期时间
        calendar.set(Calendar.HOUR_OF_DAY, intHour); // 设置小时数
        calendar.set(Calendar.MINUTE, 0); // 设置分钟数
        calendar.set(Calendar.SECOND, 0); // 设置秒数
        calendar.set(Calendar.MILLISECOND, 0); // 毫秒数设为0，因为java.util.Date只精确到秒

        return calendar.getTime();
    }

    /**
     * 随机生成LENGTH位数字的String类型数据
     */
    public String generateRandomString(int LENGTH) {
        Random random = new Random();
        String DIGITS = "0123456789";
        StringBuilder stringBuilder = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(DIGITS.length());
            char randomChar = DIGITS.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
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
        private int clickedRow;

        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("挂号");


            btn.setFont(new Font("楷体", Font.PLAIN, 25));
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 获取所在行的索引
                    JButton clickedButton = (JButton) e.getSource();
                    //int clickedRow;

                    clickedRow = (int) clickedButton.getClientProperty("row");

                    new choosetimewindow(clickedRow);

                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedRow = row;
            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性
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

            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox checkBox = (JCheckBox) e.getSource();
                    if (checkBox.isSelected()) {
                        // 选中状态改变为已选中
                        int selectedRow = purchasetable.getSelectedRow();
                        if (selectedRow != -1) {
                            updatepurchaseAmount();
                        }
                    } else {
                        // 选中状态改变为未选中
                        int selectedRow = purchasetable.getSelectedRow();
                        if (selectedRow != -1) {
                            updatepurchaseAmount();
                        }
                    }
                }
            });
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

    class choosetimewindow extends JFrame {
        JComboBox<String> time;
        JLabel timeLabel;
        JButton confirmBtn;
        JButton cancelBtn;
        JPanel timePanel;

        public choosetimewindow(int row) {
            super("请选择时间段");

            timePanel = new JPanel(springLayout);
            time = new JComboBox<String>();
            timeLabel = new JLabel("时间段");
            confirmBtn = new JButton("确定");
            cancelBtn = new JButton("取消");

            time.addItem("");
            time.addItem("8:00-9:00");
            time.addItem("9:00-10:00");
            time.addItem("10:00-11:00");
            time.addItem("11:00-12:00");
            time.addItem("12:00-13:00");
            time.addItem("13:00-14:00");
            time.addItem("14:00-15:00");
            time.addItem("15:00-16:00");
            time.addItem("16:00-17:00");

            time.setPreferredSize(new Dimension(150, 40));
            time.setFont(new Font("楷体", Font.PLAIN, 20));
            timeLabel.setFont(centerFont);
            confirmBtn.setFont(buttonFont);
            cancelBtn.setFont(buttonFont);

            timePanel.add(time);
            timePanel.add(timeLabel);
            timePanel.add(confirmBtn);
            timePanel.add(cancelBtn);

            springLayout.putConstraint(SpringLayout.WEST, timeLabel, 60, SpringLayout.WEST, timePanel);
            springLayout.putConstraint(SpringLayout.NORTH, timeLabel, 20, SpringLayout.NORTH, timePanel);
            springLayout.putConstraint(SpringLayout.WEST, time, 20, SpringLayout.EAST, timeLabel);
            springLayout.putConstraint(SpringLayout.NORTH, time, 0, SpringLayout.NORTH, timeLabel);
            springLayout.putConstraint(SpringLayout.WEST, confirmBtn, 90, SpringLayout.WEST, timePanel);
            springLayout.putConstraint(SpringLayout.NORTH, confirmBtn, 40, SpringLayout.SOUTH, timeLabel);
            springLayout.putConstraint(SpringLayout.WEST, cancelBtn, 40, SpringLayout.EAST, confirmBtn);
            springLayout.putConstraint(SpringLayout.NORTH, cancelBtn, 0, SpringLayout.NORTH, confirmBtn);

            confirmBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //添加挂号信息
                    // 创建新的挂号信息
                    String uID = GlobalData.getUID();
                    String inputDate = (String) time.getSelectedItem();
                    Date date = getDateTimeWithUserTime(inputDate);

                    String d = departments[row][0];
                    String registerID = generateRandomString(20);
                    Boolean ifPaid = false;//未缴费
                    double amount = 8.00;
                    Register newR = new Register(uID, date, d, registerID, ifPaid, amount);

                    HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
                    try {
                        if (hospitalClientAPI.CreaatPaymentByInfo(newR)) {
                            JOptionPane.showMessageDialog(timePanel, "挂号成功！");
                        } else {
                            JOptionPane.showMessageDialog(timePanel, "挂号失败！");
                        }

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

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

    class purchasewindow extends JFrame {
        //导航栏
        JPanel modeofpaymentPanel = new JPanel();
        JPanel paymentPanel = new JPanel(cardLayout);
        JPanel cardpaymentPanel = new JPanel(springLayout);
        JPanel WeChatpaymentPanel = new JPanel(springLayout);
        JPanel cancelPanel = new JPanel();
        JButton cancelBtn = new JButton("取消");
        JButton cardpaymentBtn = new JButton("一卡通支付");
        JButton WeChatpaymentBtn = new JButton("微信支付");

        //一卡通支付
        JButton confirmpurchaseBtn = new JButton("确认支付");
        JLabel pwdLabel = new JLabel("密码");
        JPasswordField pwdField = new JPasswordField();

        public purchasewindow() {
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
            JLabel picture = new JLabel();
            ImageIcon img = new ImageIcon("./src/WeChatpayment.png");
            picture.setIcon(img);
            WeChatpaymentPanel.add(picture);

            springLayout.putConstraint(SpringLayout.WEST, picture, 170, SpringLayout.WEST, WeChatpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH, picture, 0, SpringLayout.NORTH, WeChatpaymentPanel);

            //一卡通支付
            confirmpurchaseBtn.setFont(buttonFont);
            pwdLabel.setFont(centerFont);
            pwdField.setFont(centerFont);
            pwdField.setPreferredSize(new Dimension(200, 30));

            cardpaymentPanel.add(confirmpurchaseBtn);
            cardpaymentPanel.add(pwdLabel);
            cardpaymentPanel.add(pwdField);

            springLayout.putConstraint(SpringLayout.WEST, pwdLabel, 150, SpringLayout.WEST, cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 80, SpringLayout.NORTH, cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.WEST, pwdField, 40, SpringLayout.EAST, pwdLabel);
            springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
            springLayout.putConstraint(SpringLayout.WEST, confirmpurchaseBtn, 225, SpringLayout.WEST, cardpaymentPanel);
            springLayout.putConstraint(SpringLayout.NORTH, confirmpurchaseBtn, 60, SpringLayout.SOUTH, pwdLabel);

            confirmpurchaseBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowCount = unpaidreg.length;
                    ArrayList<Integer> selectedRows = new ArrayList<>();
                    //查找表格被选中的项
                    for (int row = 0; row < rowCount; row++) {
                        Boolean isSelected = (Boolean) model3.getValueAt(row, 4); // 获取第 4列（操作列）的值，即 JCheckBox 是否选中
                        //System.out.println("(支付)第"+row+"行的名称是:"+(String)purchaseCar[row][0]);
                        //System.out.println("(支付)内容为："+isSelected);
                        if (isSelected) {
                            selectedRows.add(row); // 记录选中的行索引
                            System.out.println("(支付)选中了行数：" + row);
                        }
                    }
                    double flag = -4.00;
                    //HospitalClientAPI hospitalClientAPI=new HospitalClientAPIImp("localhost", 8888);
                    IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
                    if (selectedRows.size() > 0) {
                        //若有行被选中
                        // 付钱
                        bankAccount account = iBankClientAPI.findBankAccountById(GlobalData.getUID());

                        bankBill bill = new bankBill("医院", generateRandomString(20), account.getCardId(), GlobalData.getUID(), new Date(), true, purchaseAmount);

                        iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
                        String enterPwd = pwdField.getText();
                        try {
                            //后端消费函数
                            flag = iBankClientAPI.bankConsume(GlobalData.getUID(), bill, enterPwd,false);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        if (flag == -400000.00) {
                            JOptionPane.showMessageDialog(cardpaymentPanel, "系统出错！");
                        } else if (flag == -300000.00) {
                            JOptionPane.showMessageDialog(cardpaymentPanel, "密码错误，请重新输入");
                        } else if (flag == -200000.00) {
                            JOptionPane.showMessageDialog(cardpaymentPanel, "卡已挂失");
                        } else if (flag == -100000.00) {
                            JOptionPane.showMessageDialog(cardpaymentPanel, "余额不足！请充值");
                        } else if (flag>=0){
                            //从未支付表格里删除已支付的挂号记录

                            for (Integer rowIndex : selectedRows) {
                                //后端挂号记录的是否缴费状态

                                HospitalClientAPI hospitalClientAPI = new HospitalClientAPIImp("localhost", 8888);
                                try {
                                    hospitalClientAPI.PayAllPayment((String) unpaidreg[rowIndex][0]);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                try {
                                    unpaidreg = getAllUnpaidRegister();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                ShowTableDataModel3(unpaidreg);
                                totalamount.setText("￥0.00");
                                JOptionPane.showMessageDialog(cardpaymentPanel, "充值成功，余额￥" + Double.toString(flag));
                            }
                        }

                    }


                    dispose();

                }
            });


            paymentPanel.add(WeChatpaymentPanel, "微信支付");
            paymentPanel.add(cardpaymentPanel, "一卡通支付");

            Container pane = getContentPane();
            pane.add(modeofpaymentPanel, BorderLayout.NORTH);
            pane.add(paymentPanel, BorderLayout.CENTER);
            pane.add(cancelPanel, BorderLayout.SOUTH);
            setSize(600, 400);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
    }


//    public static void main(String[] args) {
//        new HospitalTeacherStudentUI();
//    }
}
