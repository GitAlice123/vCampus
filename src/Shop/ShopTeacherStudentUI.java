
//package view.Shop;
//
//import view.Bank.IBankClientAPI;
//import view.Bank.IBankClientAPIImpl;
//import view.Bank.bankAccount;
//import view.Bank.bankBill;
//import view.Global.GlobalData;
//import view.Global.SummaryStudentTeacherUI;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.table.JTableHeader;
//import javax.swing.table.TableCellRenderer;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.Random;
//import java.util.Vector;
//import java.util.Set;
//import java.util.HashSet;
//
//public class ShopTeacherStudentUI extends JFrame implements ItemListener {
//    Object[][] purchaseCar = null;
//
//    String[][] AllGoods = null;
//
//    Double purchaseCarAmount = 0.0;//购物车内的总金额
//
//    //导航栏
//    JButton purchasegoodBtn = new JButton("购买商品");
//    JButton purchasehistoryBtn = new JButton("购买记录");
//    JButton shoppingcartBtn = new JButton("购物车");
//    JButton backBtn = new JButton("退出");
//
//    //购买商品
//    DefaultTableModel model = new DefaultTableModel();
//    JButton searchgoodBtn = new JButton("查询商品");
//    JTextField searchgoodField = new JTextField();
//    JTable goodtable = new JTable();
//    //购买记录
//    DefaultTableModel model2 = new DefaultTableModel();
//    JLabel historyLabel = new JLabel("购买记录");
//    JTable historytable = new JTable();
//    //购物车
//    DefaultTableModel model3 = new DefaultTableModel();
//    JTable carttable = new JTable();
//    JLabel shoppingcartLabel = new JLabel("购物车");
//    JButton purchaseBtn = new JButton("支付");
//    JButton deleteBtn = new JButton("删除");
//    JButton OKBtn = new JButton("选好了");
//    JLabel totalamountLabel = new JLabel("总金额");
//    JLabel totalamount = new JLabel("￥0.00");
//
//
//    JPanel backPanel = new JPanel();
//    CardLayout cardLayout = new CardLayout();
//    SpringLayout springLayout = new SpringLayout();
//    JPanel cardPanel = new JPanel(cardLayout);
//    JPanel shopcard = new JPanel();
//    JPanel blankPanel = new JPanel();
//    JPanel purchasegoodPanel = new JPanel(springLayout);
//    JPanel purchasehistoryPanel = new JPanel(springLayout);
//    JPanel shoppingcartPanel = new JPanel(springLayout);
//
//    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
//    Font titleFont = new Font("楷体", Font.PLAIN, 50);
//    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体
//    Vector<Double> selectedGoods = new Vector<Double>();
//
//    Boolean selectedFlag;
//    Boolean deselectedFlag;
//    int lastSelectedRow;
//    int lastDeselectedRow;
//    Set<Integer> selectedRows = new HashSet<>();
//
//    public ShopTeacherStudentUI() {
//        super("商店");
//
//        selectedFlag = false;
//        deselectedFlag = false;
//
//
//        //更新现在有的商品列表
//        getAllGoods();
//
//
//        //导航栏
//        purchasegoodBtn.setFont(buttonFont);
//        purchasehistoryBtn.setFont(buttonFont);
//        shoppingcartBtn.setFont(buttonFont);
//        backBtn.setFont(buttonFont);
//        purchasegoodBtn.setPreferredSize(new Dimension(150, 40));
//        purchasehistoryBtn.setPreferredSize(new Dimension(150, 40));
//        shoppingcartBtn.setPreferredSize(new Dimension(150, 40));
//        backBtn.setPreferredSize(new Dimension(100, 40));
//
//        shopcard.add(purchasegoodBtn);
//        shopcard.add(purchasehistoryBtn);
//        shopcard.add(shoppingcartBtn);
//        backPanel.add(backBtn);
//
//        //购买商品
//        searchgoodBtn.setFont(buttonFont);
//        searchgoodField.setPreferredSize(new Dimension(200, 30));
//        searchgoodField.setFont(centerFont);
//        goodtable.setFont(new Font("楷体", Font.PLAIN, 20));
//
//        String[] goodheader = {"序号", "商品名称", "价格", "库存", "操作"};
//        Object[][] gooddata = {
//                {"1", null, null, null, null},
//                {"2", null, null, null, null},
//                {"3", null, null, null, null},
//                {"4", null, null, null, null},
//                {"5", null, null, null, null},
//                {"6", null, null, null, null},
//                {"7", null, null, null, null},
//                {"8", null, null, null, null},
//                {"9", null, null, null, null},
//                {"10", null, null, null, null}
//        };
//        model.setDataVector(gooddata, goodheader);
//        goodtable.setModel(model);
//        goodtable.getColumnModel().getColumn(4).setCellRenderer(new TableCellRendererButton());
//        goodtable.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorButton());
//        JScrollPane scrollPane = new JScrollPane(goodtable);
//        goodtable.setRowHeight(30);
//        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
//        JTableHeader tab_header = goodtable.getTableHeader();                    //获取表头
//        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
//        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));
//
//        purchasegoodPanel.add(searchgoodBtn);
//        purchasegoodPanel.add(searchgoodField);
//        purchasegoodPanel.add(scrollPane);
//
//        springLayout.putConstraint(SpringLayout.WEST, searchgoodBtn, 80, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, searchgoodBtn, 80, SpringLayout.NORTH, cardPanel);
//        springLayout.putConstraint(SpringLayout.WEST, searchgoodField, 60, SpringLayout.EAST, searchgoodBtn);
//        springLayout.putConstraint(SpringLayout.NORTH, searchgoodField, 0, SpringLayout.NORTH, searchgoodBtn);
//        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 40, SpringLayout.SOUTH, searchgoodBtn);
//
//        //购买记录
//        historyLabel.setFont(titleFont);
//        historytable.setFont(new Font("楷体", Font.PLAIN, 20));
//
//        String[] historyheader = {"商品名称", "购买数量", "支付金额", "支付时间"};
//        Object[][] historydata = {
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null},
//                {null, null, null, null}
//        };
//        model2.setDataVector(historydata, historyheader);
//        historytable.setModel(model2);
//        JScrollPane historyscrollPane = new JScrollPane(historytable);
//        historytable.setRowHeight(30);
//        historyscrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
//        JTableHeader tab_header2 = historytable.getTableHeader();                    //获取表头
//        tab_header2.setFont(new Font("楷体", Font.PLAIN, 25));
//        tab_header2.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));
//
//
//        purchasehistoryPanel.add(historyscrollPane);
//        purchasehistoryPanel.add(historyLabel);
//
//        springLayout.putConstraint(SpringLayout.NORTH, historyLabel, 40, SpringLayout.NORTH, cardPanel);
//        springLayout.putConstraint(SpringLayout.WEST, historyLabel, 500, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, historyscrollPane, 40, SpringLayout.SOUTH, historyLabel);
//        springLayout.putConstraint(SpringLayout.WEST, historyscrollPane, 100, SpringLayout.WEST, cardPanel);
//
//        //购物车
//        carttable.setFont(new Font("楷体", Font.PLAIN, 20));
//        Object[][] good = {
//                {null, null, null, false},
//                {null, null, null, false},
//                {null, null, null, false},
//                {null, null, null, false},
//                {null, null, null, false}
//        };
//        String[] cartheader = {"商品名称", "商品数量", "金额", "操作"};
//        model3.setDataVector(good, cartheader);
//        carttable = new JTable(model3) {
//            @Override
//            public Class<?> getColumnClass(int column) {
//                if (column == 3) { // 最后一列是复选框列
//                    return Boolean.class;
//                }
//                return super.getColumnClass(column);
//            }
//        };
//
//        // 设置复选框渲染器和编辑器
//        carttable.getColumnModel().getColumn(3).setCellRenderer(carttable.getDefaultRenderer(Boolean.class));
//        carttable.getColumnModel().getColumn(3).setCellEditor(carttable.getDefaultEditor(Boolean.class));
//        // 添加复选框状态变化监听器
//        JCheckBox checkBox = (JCheckBox) carttable.getDefaultEditor(Boolean.class).getTableCellEditorComponent(carttable, null, false, 0, 3);
//        checkBox.addItemListener(this);
//
//        carttable.setRowHeight(30);
//        JScrollPane cartpane = new JScrollPane(carttable);
//
//        // 创建带复选框的表格
//
//        cartpane.setPreferredSize(new Dimension(1000, 300));
//        JTableHeader tab_header3 = carttable.getTableHeader();                    //获取表头
//        tab_header3.setFont(new Font("楷体", Font.PLAIN, 25));
//        tab_header3.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));
//        shoppingcartLabel.setFont(titleFont);
//        deleteBtn.setFont(buttonFont);
//        purchaseBtn.setFont(buttonFont);
//        totalamountLabel.setFont(centerFont);
//        totalamount.setFont(centerFont);
//        OKBtn.setFont(buttonFont);
//
//        shoppingcartPanel.add(shoppingcartLabel);
//        shoppingcartPanel.add(cartpane);
//        shoppingcartPanel.add(deleteBtn);
//        shoppingcartPanel.add(purchaseBtn);
//        shoppingcartPanel.add(OKBtn);
//        shoppingcartPanel.add(totalamountLabel);
//        shoppingcartPanel.add(totalamount);
//
//        springLayout.putConstraint(SpringLayout.NORTH, shoppingcartLabel, 40, SpringLayout.NORTH, cardPanel);
//        springLayout.putConstraint(SpringLayout.WEST, shoppingcartLabel, 520, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, cartpane, 40, SpringLayout.SOUTH, shoppingcartLabel);
//        springLayout.putConstraint(SpringLayout.WEST, cartpane, 100, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, deleteBtn, 80, SpringLayout.SOUTH, cartpane);
//        springLayout.putConstraint(SpringLayout.WEST, deleteBtn, 550, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, purchaseBtn, 0, SpringLayout.NORTH, deleteBtn);
//        springLayout.putConstraint(SpringLayout.WEST, purchaseBtn, 100, SpringLayout.EAST, deleteBtn);
//        springLayout.putConstraint(SpringLayout.NORTH, OKBtn, 0, SpringLayout.NORTH, deleteBtn);
//        springLayout.putConstraint(SpringLayout.EAST, OKBtn, -100, SpringLayout.WEST, deleteBtn);
//        springLayout.putConstraint(SpringLayout.NORTH, totalamountLabel, 40, SpringLayout.SOUTH, cartpane);
//        springLayout.putConstraint(SpringLayout.WEST, totalamountLabel, 800, SpringLayout.WEST, cardPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, totalamount, 0, SpringLayout.NORTH, totalamountLabel);
//        springLayout.putConstraint(SpringLayout.WEST, totalamount, 40, SpringLayout.EAST, totalamountLabel);
//
//        //购物车页面删除和支付按钮
//        deleteBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DefaultTableModel model4 = (DefaultTableModel) carttable.getModel();
//                //int rowCount = model4.getRowCount();
//                int rowCount = purchaseCar.length;
//                ArrayList<Integer> selectedRows = new ArrayList<>();
//                //删除购物车里被选中的商品
//                for (int row = 0; row < rowCount; row++) {
//                    Boolean isSelected = (Boolean) model4.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
//                    System.out.println("第" + row + "行的名称是:" + (String) purchaseCar[row][0]);
//                    System.out.println("内容为：" + isSelected);
//                    if (isSelected) {
//                        selectedRows.add(row); // 记录选中的行索引
//                        System.out.println("选中了行数：" + row);
//                    }
//                }
//                if (selectedRows.size() > 0) {
//                    //若有行被选中
//                    for (Integer rowIndex : selectedRows) {
//                        //System.out.println(rowIndex);
//                        //后端依次删除
//                        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                        iShopClientAPI.removeSelectedGood((String) purchaseCar[rowIndex][0]);
//
//                    }
//                    for (Integer rowIndex : selectedRows) {
//                        //前端依次删除
////                        purchaseCarAmount=purchaseCarAmount+Double.parseDouble((String)purchaseCar[rowIndex][2]);
////                        String formattedAmount = String.format("%.2f", purchaseCarAmount);
////                        totalamount.setText("￥"+formattedAmount);
//                        updatePurchaseCarAmount(rowIndex);
//                        purchaseCar = deleteRow(purchaseCar, rowIndex);
//                        ShowTableDataModel3(purchaseCar);
//
//                    }
//                }
//
//            }
//        });
//        purchaseBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                //支付选中的商品
//                new purchasewindow();
//            }
//        });
//        purchasegoodBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(cardPanel, "购买商品");
//                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
////                String[][] data=iShopClientAPI.getAllGoodsST();
////                for (int i = 0; i < data.length; i++) {
////                    for (int j = 0; j < data[i].length; j++) {
////                        System.out.print(data[i][j] + " ");
////                    }
////                    System.out.println();
////                }
//
//                //显示所有商品
//                AllGoods = iShopClientAPI.getAllGoodsST();
//                ShowTableDataModel(AllGoods);
//            }
//        });
//        purchasehistoryBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                cardLayout.show(cardPanel, "购买记录");
//                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//
//                //显示当前账户所有的购买记录
//                ShowTableDataModel2(iShopClientAPI.getPurchaseRecordById(GlobalData.getUID()));
//
//            }
//        });
//        shoppingcartBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(!selectedGoods.isEmpty())
//                    selectedGoods.clear();
//                cardLayout.show(cardPanel, "购物车");
//
//                // TODO 加上下面这一行，购物车的初始显示就不对
//                //getPurchaseCar();
//                if (purchaseCar != null) {
//                    ShowTableDataModel3(purchaseCar);
//                }
//            }
//        });
//        backBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dispose();
//                new SummaryStudentTeacherUI();
//            }
//        });
//        searchgoodBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //查询商品按钮功能,可用商品号和商品名称查询
//
//                String query = searchgoodField.getText();
//                if (query.equals("")) {//没有字串输入时，显示所有商品
//                    getAllGoods();
//                    ShowTableDataModel(AllGoods);
//                } else {
//                    IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                    String[][] result = iShopClientAPI.findGoodST(query);
//                    if (result == null) {
//                        ShowTableDataModel(null);
//                        JOptionPane.showMessageDialog(purchasegoodPanel, "查询结果为空");
//                    } else {
//                        AllGoods = result;
//                        ShowTableDataModel(result);
//                    }
//                }
//
//            }
//        });
//        OKBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                double sum = 0.0;
////                for (int i = 0; i < carttable.getRowCount(); i++) {
////                    boolean isChecked = (Boolean) carttable.getValueAt(i, 3);
////                    if (isChecked) {
////                        Object dat = carttable.getValueAt(i, 2);
////                        if (dat != null) {
////                            String data = (String) dat;
////                            Double money = Double.parseDouble(data);
////                            sum += money;
////                        }
////                    }
////                }
//                for (Double num : selectedGoods) {
//                    sum += num;
//                }
//                totalamount.setText("￥"+ String.format("%.2f", sum));
//                //processSelectedRows();
//            }
//        });
//
//
//        cardPanel.add(blankPanel);
//        cardPanel.add(purchasegoodPanel, "购买商品");
//        cardPanel.add(purchasehistoryPanel, "购买记录");
//        cardPanel.add(shoppingcartPanel, "购物车");
//
//        Container contentPane = getContentPane();//获取控制面板
//        contentPane.add(cardPanel, BorderLayout.CENTER);
//        contentPane.add(shopcard, BorderLayout.NORTH);
//        contentPane.add(backPanel, BorderLayout.SOUTH);
//        setSize(1200, 800);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
//        setVisible(true);
//
//
//    }
//
//    /**
//     * item改动时的响应函数
//     * @param e the event to be processed
//     */
//    public void itemStateChanged(ItemEvent e) {
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//            if(!selectedFlag){
//                selectedFlag = true;
//                lastSelectedRow = carttable.getSelectedRow();
//                selectedRows.add(lastSelectedRow);
//                updateTotalAmount();
//                return;
//            }
//            // 持续选中
//            if(selectedFlag){
//                int selectedRow = carttable.getSelectedRow();
//                selectedRows.add(lastDeselectedRow);
//                selectedRows.add(selectedRow);
//                lastSelectedRow = selectedRow;
//                updateTotalAmount();
//            }
//
//            // 在这里处理复选框选中时的逻辑
//        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
//
//            // 复选框被取消选中
//            int selectedRow = carttable.getSelectedRow();
//            selectedRows.remove(selectedRow);
//            updateTotalAmount();
//        }
//    }
//
//    /**
//     * 更新下面总价格图标
//     */
//    private void updateTotalAmount() {
//        double total = 0.0;
//        for (int selectedRow : selectedRows) {
//            Object dat = carttable.getValueAt(selectedRow, 2);
//            if (dat != null) {
//                String data = (String) dat;
//                double money = Double.parseDouble(data);
//                total += money;
//            }
//        }
//
//        totalamount.setText("￥" + Double.toString(total));
//    }
//    /**
//     * 用于购物车增加商品
//     */
//    public static Object[][] addRowToEnd(Object[][] array, SelectedGood s) {
//        String goodName = s.getGoodName();
//        int goodNums = s.getGoodNums();
//        double totalPrice = s.getGoodNums() * s.getGoodPrice();
//
//        if (array == null) {
//            // 如果原始数组为 null，则创建一个新的二维数组并将新行作为唯一行添加到其中
//            return new Object[][]{{goodName, Integer.toString(goodNums), Double.toString(totalPrice), false}};
//        } else {
//            int numRows = array.length;//name nums 金额
//            int numCols = array[0].length;
//
//            // 遍历数组，检查是否存在相同商品
//            for (int i = 0; i < numRows; i++) {
//                String existingGoodName = (String) array[i][0];
//                if (existingGoodName.equals(goodName)) {
//                    // 商品已存在，增加对应数量
//                    int existingGoodNums = Integer.parseInt(String.valueOf(array[i][1]));
//                    double existingTotalPrice = Double.parseDouble((String) array[i][2]);
//                    array[i][1] = Integer.toString(existingGoodNums + goodNums);
//                    array[i][2] = Double.toString(existingTotalPrice + totalPrice);
//                    return array;
//                }
//            }
//
//            // 购物车中不存在相同商品，创建新的行并添加到数组末尾
//            int numColsNew = numCols;
//            Object[][] newArray = new Object[numRows + 1][numColsNew];
//            for (int i = 0; i < numRows; i++) {
//                System.arraycopy(array[i], 0, newArray[i], 0, numCols);
//            }
//
//            Object[] newRow = new Object[numColsNew];
//            newRow[0] = goodName;
//            newRow[1] = s.getGoodNums();
//            newRow[2] = Double.toString(totalPrice);
//            newRow[3] = false;
//
//            newArray[numRows] = newRow;
//            return newArray;
//        }
//    }
//
//    /**
//     * 可用于前端删除购物车物品
//     */
//    public static Object[][] deleteRow(Object[][] array, int rowIndex) {
//        if (array == null || array.length == 0) {
//            // 如果原始数组为 null 或者为空数组，则直接返回原始数组
//            return array;
//        }
//
//        int numRows = array.length;
//        int numCols = array[0].length;
//
//        if (rowIndex < 0 || rowIndex >= numRows) {
//            // 如果指定的行索引超出范围，则直接返回原始数组
//            return array;
//        }
//
//        // 创建新数组，长度比原数组少 1
//        Object[][] newArray = new Object[numRows - 1][numCols];
//
//        int destRow = 0;
//        for (int srcRow = 0; srcRow < numRows; srcRow++) {
//            if (srcRow != rowIndex) {
//                // 复制除要删除的行外的所有行到新数组中
//                System.arraycopy(array[srcRow], 0, newArray[destRow], 0, numCols);
//                destRow++;
//            }
//        }
//
//        return newArray;
//    }
//
//    public static void main(String[] args) {
//        new ShopTeacherStudentUI();
//    }
//
//    /**
//     * 随机生成LENGTH位数字的String类型数据
//     */
//    public String generateRandomString(int LENGTH) {
//        Random random = new Random();
//        String DIGITS = "0123456789";
//        StringBuilder stringBuilder = new StringBuilder(LENGTH);
//
//        for (int i = 0; i < LENGTH; i++) {
//            int randomIndex = random.nextInt(DIGITS.length());
//            char randomChar = DIGITS.charAt(randomIndex);
//            stringBuilder.append(randomChar);
//        }
//
//        return stringBuilder.toString();
//    }
//
//    /**
//     * 更新购物车界面选中商品的总金额
//     */
//    private void updatePurchaseCarAmount(int selectedRow) {
//        int rowCount = model3.getRowCount();
//        ArrayList<Integer> selectedRows = new ArrayList<>();
//        purchaseCarAmount = 0.0;
//        //删除购物车里被选中的商品
//        for (int row = 0; row < rowCount; row++) {
//            Boolean isSelected = (Boolean) model3.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
//            if (row == selectedRow) {
//                if (isSelected == true)
//                    isSelected = false;
//                else
//                    isSelected = true;
//            }
//            System.out.println("内容为2222：" + isSelected);
//            if (isSelected) {
//                selectedRows.add(row); // 记录选中的行索引
//                purchaseCarAmount = purchaseCarAmount + Double.parseDouble((String) purchaseCar[row][2]);
//                System.out.println("选中了行数：" + row);
//            }
//        }
//        String formattedAmount = String.format("%.2f", purchaseCarAmount);
//        totalamount.setText("￥" + formattedAmount);
//    }
//
//    /**
//     * 显示商品的表格信息
//     */
//    private void ShowTableDataModel(String[][] data) {
//        //若查询结果为空
//        if (data == null) {
//
//            System.out.println("查询结果为空************");
//            model.setRowCount(0);
//            return;
//        }
//
//        // 清空表格原有的数据
//        model.setRowCount(0);
//
//        // 将新数据添加到表格模型
//        for (String[] row : data) {
//            model.addRow(row);
//        }
//        // 通知表格模型数据发生变化，刷新表格显示
//        model.fireTableDataChanged();
//        goodtable.getColumnModel().getColumn(4).setCellRenderer(new TableCellRendererButton());
//        goodtable.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorButton());
//    }
//
//    /**
//     * 显示购买记录的表格信息
//     */
//    private void ShowTableDataModel2(String[][] data) {
//        //若查询结果为空
//        if (data == null) {
//            System.out.println("查询结果为空");
//            model2.setRowCount(0);
//            return;
//        }
//
//        // 清空表格原有的数据
//        model2.setRowCount(0);
//
//        // 将新数据添加到表格模型
//        for (String[] row : data) {
//            model2.addRow(row);
//        }
//        // 通知表格模型数据发生变化，刷新表格显示
//        model2.fireTableDataChanged();
//    }
//
//    /**
//     * 显示购物车的表格信息
//     */
//    private void ShowTableDataModel3(Object[][] data) {
//        //若查询结果为空
//        if (data == null) {
//            System.out.println("查询结果为空");
//            model3.setRowCount(0);
//            return;
//        }
//
//        // 清空表格原有的数据
//        model3.setRowCount(0);
//
//        // 将新数据添加到表格模型
//        for (Object[] row : data) {
//            model3.addRow(row);
//        }
//
//        // 通知表格模型数据发生变化，刷新表格显示
//        model3.fireTableDataChanged();
////        carttable.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());
////        carttable.getColumnModel().getColumn(3).setCellEditor(new CheckBoxEditor());
//        carttable.getColumnModel().getColumn(3).setCellRenderer(carttable.getDefaultRenderer(Boolean.class));
//        carttable.getColumnModel().getColumn(3).setCellEditor(carttable.getDefaultEditor(Boolean.class));
//    }
//
//    /**
//     * 前端获取后端的购物车数组数据
//     */
//    public void getPurchaseCar() {
//        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//        purchaseCar = iShopClientAPI.getSelectedGoods();
//    }
//
//    /**
//     * 获取所有的商品
//     */
//    public void getAllGoods() {
//        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//        AllGoods = iShopClientAPI.getAllGoodsST();
//    }
//
//    private void processSelectedRows() {
//        DefaultTableModel model = (DefaultTableModel) carttable.getModel();
//        int rowCount = model.getRowCount();
//        int columnCount = model.getColumnCount();
//        int lastColumnIndex = columnCount - 1;
//        int thirdColumnIndex = 2;
//
//        double sum = 0.0;
//        for (int row = 0; row < rowCount; row++) {
//            boolean isChecked = (boolean) model.getValueAt(row, lastColumnIndex);
//            if (isChecked) {
//                double value = (double) model.getValueAt(row, thirdColumnIndex);
//                sum += value;
//            }
//        }
//
//        JOptionPane.showMessageDialog(this, "Sum of selected rows' values: " + sum);
//    }
//
//    // 商品页面”加入购物车“的按钮相关类
//    class TableCellRendererButton implements TableCellRenderer {
//        JButton button = new JButton("加入购物车");
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
//                                                       int row, int column) {
//            button.setFont(new Font("楷体", Font.PLAIN, 25));
//            return button;
//        }
//
//    }
//
//    class TableCellEditorButton extends DefaultCellEditor {
//        JButton btn = new JButton();
//        private int clickedRow;
//
//
//        public TableCellEditorButton() {
//            super(new JTextField());
//            //设置点击一次就激活，否则默认好像是点击2次激活。
//            this.setClickCountToStart(1);
//            btn = new JButton("加入购物车");
//
//
//            btn.setFont(new Font("楷体", Font.PLAIN, 25));
//            btn.addActionListener(new ActionListener() {
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // 获取所在行的索引
//                    JButton clickedButton = (JButton) e.getSource();
//                    //int clickedRow;
//                    clickedRow = (int) clickedButton.getClientProperty("row");
//                    new addtocartwindow(clickedRow);
//
//                }
//            });
//        }
//
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//            clickedRow = row;
//            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性
//            return btn;
//        }
//
//    }
//
//    class addtocartwindow extends JFrame {
//        JPanel window = new JPanel(springLayout);
//        JLabel goodnameLabel = new JLabel("商品名称");
//        JLabel goodname = new JLabel("XXX");
//        JLabel countLabel = new JLabel("购买数量");
//        JButton addtoshoppingcartBtn = new JButton("加入购物车");
//        JButton cancelBtn = new JButton("取消");
//        JComboBox<String> count = new JComboBox<String>();
//
//        public addtocartwindow(int row) {
//
//            goodname.setFont(centerFont);
//            goodnameLabel.setFont(centerFont);
//            countLabel.setFont(centerFont);
//            count.setFont(new Font("楷体", Font.PLAIN, 20));
//            addtoshoppingcartBtn.setFont(buttonFont);
//            cancelBtn.setFont(buttonFont);
//
//            count.addItem("1");
//            count.addItem("2");
//            count.addItem("3");
//            count.addItem("4");
//            count.addItem("5");
//            count.addItem("6");
//            count.addItem("7");
//            count.addItem("8");
//            count.addItem("9");
//            count.addItem("10");
//
//            window.add(goodnameLabel);
//            window.add(goodname);
//            window.add(count);
//            window.add(countLabel);
//            window.add(addtoshoppingcartBtn);
//            window.add(cancelBtn);
//
//            // 显示当前商品名称
//            goodname.setText(AllGoods[row][1]);
//
//            addtoshoppingcartBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    //获取购买数量
//                    String selectedCount = (String) count.getSelectedItem();
//                    //通过商品名称查找到原商品
//                    IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                    String[][] g = iShopClientAPI.findGoodST(AllGoods[row][1]);
//
////                    GoodStrings[i][0] = good.getGoodId();      // 商品编号
////                    GoodStrings[i][1] = good.getGoodName();    // 商品名称
////                    GoodStrings[i][2] = Double.toString(good.getGoodPrice());   // 商品价格
////                    GoodStrings[i][3] = Integer.toString(good.getGoodStock());  // 商品库存
////                    GoodStrings[i][4] = "";
//                    //新建一个购物车商品条目
//                    SelectedGood s = new SelectedGood(g[0][0], g[0][1], Double.parseDouble(g[0][2]), Integer.parseInt(selectedCount));
//                    //判断库存判断库存等限制信息
//                    if (Integer.parseInt(selectedCount) <= Integer.parseInt(g[0][3])) {//库存够
//                        //前端购物车数据添加
//                        purchaseCar = addRowToEnd(purchaseCar, s);
////                        String[][] data=purchaseCar;
////                        for (int i = 0; i < data.length; i++) {
////                            for (int j = 0; j < data[i].length; j++) {
////                                System.out.print(data[i][j] + " ");
////                            }
////
////                        }
//
//                        //后端购物车数据添加(注意，要重开一个API)
//                        IShopClientAPI iShopClientAPI2 = new IShopClientAPIImpl("localhost", 8888);
//                        iShopClientAPI2.addSelectedGood(g[0][1], Integer.parseInt(selectedCount));
//                        JOptionPane.showMessageDialog(window, "加入购物车成功！");
//                    } else {
//                        JOptionPane.showMessageDialog(window, "抱歉！库存不足！");
//                    }
//                    dispose();
//                }
//            });
//
//            cancelBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    dispose();
//                }
//            });
//
//            springLayout.putConstraint(SpringLayout.WEST, goodnameLabel, 180, SpringLayout.WEST, window);
//            springLayout.putConstraint(SpringLayout.NORTH, goodnameLabel, 80, SpringLayout.NORTH, window);
//            springLayout.putConstraint(SpringLayout.WEST, goodname, 40, SpringLayout.EAST, goodnameLabel);
//            springLayout.putConstraint(SpringLayout.NORTH, goodname, 0, SpringLayout.NORTH, goodnameLabel);
//            springLayout.putConstraint(SpringLayout.WEST, countLabel, 0, SpringLayout.WEST, goodnameLabel);
//            springLayout.putConstraint(SpringLayout.NORTH, countLabel, 60, SpringLayout.SOUTH, goodnameLabel);
//            springLayout.putConstraint(SpringLayout.WEST, count, 0, SpringLayout.WEST, goodname);
//            springLayout.putConstraint(SpringLayout.NORTH, count, 0, SpringLayout.NORTH, countLabel);
//            springLayout.putConstraint(SpringLayout.WEST, addtoshoppingcartBtn, 125, SpringLayout.WEST, window);
//            springLayout.putConstraint(SpringLayout.SOUTH, addtoshoppingcartBtn, -40, SpringLayout.SOUTH, window);
//            springLayout.putConstraint(SpringLayout.WEST, cancelBtn, 80, SpringLayout.EAST, addtoshoppingcartBtn);
//            springLayout.putConstraint(SpringLayout.SOUTH, cancelBtn, 0, SpringLayout.SOUTH, addtoshoppingcartBtn);
//
//            Container pane = getContentPane();
//            pane.add(window);
//            setSize(600, 400);
//            setLocationRelativeTo(null);
//            setResizable(false);
//            setVisible(true);
//        }
//    }
//
//    class purchasewindow extends JFrame {
//        //导航栏
//        JPanel modeofpaymentPanel = new JPanel();
//        JPanel paymentPanel = new JPanel(cardLayout);
//        JPanel cardpaymentPanel = new JPanel(springLayout);
//        JPanel WeChatpaymentPanel = new JPanel(springLayout);
//        JPanel cancelPanel = new JPanel();
//        JButton cancelBtn = new JButton("取消");
//        JButton cardpaymentBtn = new JButton("一卡通支付");
//        JButton WeChatpaymentBtn = new JButton("微信支付");
//
//        //一卡通支付
//        JButton confirmpurchaseBtn = new JButton("确认支付");
//        JLabel pwdLabel = new JLabel("密码");
//        JPasswordField pwdField = new JPasswordField();
//
//        public purchasewindow() {
//            super("请选择支付方式（推荐使用微信支付）");
//            //导航栏
//            cardpaymentBtn.setFont(buttonFont);
//            WeChatpaymentBtn.setFont(buttonFont);
//            cancelBtn.setFont(buttonFont);
//
//            modeofpaymentPanel.add(WeChatpaymentBtn);
//            modeofpaymentPanel.add(cardpaymentBtn);
//            cancelPanel.add(cancelBtn);
//
//            cardpaymentBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    cardLayout.show(paymentPanel, "一卡通支付");
//                }
//            });
//            WeChatpaymentBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    cardLayout.show(paymentPanel, "微信支付");
//                }
//            });
//            cancelBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    dispose();
//                }
//            });
//
//            //微信支付
//            JLabel picture = new JLabel();
//            ImageIcon img = new ImageIcon("./src/WeChatpayment.png");
//            picture.setIcon(img);
//            WeChatpaymentPanel.add(picture);
//
//            springLayout.putConstraint(SpringLayout.WEST, picture, 170, SpringLayout.WEST, WeChatpaymentPanel);
//            springLayout.putConstraint(SpringLayout.NORTH, picture, 0, SpringLayout.NORTH, WeChatpaymentPanel);
//
//            //一卡通支付
//            confirmpurchaseBtn.setFont(buttonFont);
//            pwdLabel.setFont(centerFont);
//            pwdField.setFont(centerFont);
//            pwdField.setPreferredSize(new Dimension(200, 30));
//
//            cardpaymentPanel.add(confirmpurchaseBtn);
//            cardpaymentPanel.add(pwdLabel);
//            cardpaymentPanel.add(pwdField);
//
//            springLayout.putConstraint(SpringLayout.WEST, pwdLabel, 150, SpringLayout.WEST, cardpaymentPanel);
//            springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 80, SpringLayout.NORTH, cardpaymentPanel);
//            springLayout.putConstraint(SpringLayout.WEST, pwdField, 40, SpringLayout.EAST, pwdLabel);
//            springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, pwdLabel);
//            springLayout.putConstraint(SpringLayout.WEST, confirmpurchaseBtn, 225, SpringLayout.WEST, cardpaymentPanel);
//            springLayout.putConstraint(SpringLayout.NORTH, confirmpurchaseBtn, 60, SpringLayout.SOUTH, pwdLabel);
//
//            confirmpurchaseBtn.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    //DefaultTableModel model4 = (DefaultTableModel) carttable.getModel();
//                    //int rowCount = model4.getRowCount();
//                    int rowCount = purchaseCar.length;
//                    ArrayList<Integer> selectedRows = new ArrayList<>();
//                    //支付购物车里被选中的商品
//                    for (int row = 0; row < rowCount; row++) {
//                        Boolean isSelected = (Boolean) model3.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
//                        System.out.println("(支付)第" + row + "行的名称是:" + (String) purchaseCar[row][0]);
//                        System.out.println("(支付)内容为：" + isSelected);
//                        if (isSelected) {
//                            selectedRows.add(row); // 记录选中的行索引
//                            System.out.println("(支付)选中了行数：" + row);
//                        }
//                    }
//                    double flag = -4.00;
//                    IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
//                    if (selectedRows.size() > 0) {
//                        //若有行被选中
//                        // 付钱
//
//                        bankAccount account = iBankClientAPI.findBankAccountById(GlobalData.getUID());
//
//                        bankBill bill = new bankBill("商店", generateRandomString(20), account.getCardId(), GlobalData.getUID(), new Date(), true, purchaseCarAmount);
//
//                        iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
//                        String enterPwd = pwdField.getText();
//                        try {
//                            //后端消费函数
//                            flag = iBankClientAPI.bankConsume(GlobalData.getUID(), bill, enterPwd,false);
//
//                        } catch (IOException ex) {
//                            throw new RuntimeException(ex);
//                        }
//                        if (flag == -400000.00) {
//                            JOptionPane.showMessageDialog(cardpaymentPanel, "系统出错！");
//                        } else if (flag == -300000.00) {
//                            JOptionPane.showMessageDialog(cardpaymentPanel, "密码错误，请重新输入");
//                        } else if (flag == -200000.00) {
//                            JOptionPane.showMessageDialog(cardpaymentPanel, "卡已挂失");
//                        } else if (flag == -100000.00) {
//                            JOptionPane.showMessageDialog(cardpaymentPanel, "余额不足！请充值");
//                        } else if(flag>=0){
//                            //后端依次增加消费记录
//                            for (Integer rowIndex : selectedRows) {
//                                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                                String[][] good = iShopClientAPI.findGoodST((String) purchaseCar[rowIndex][0]);
//                                System.out.println("(String)purchaseCar[rowIndex][1]:" + purchaseCar[rowIndex][1]);
//                                Class<?> objClass = purchaseCar[rowIndex][1].getClass();
//                                PurchaseRecord record;
//                                if (objClass.getSimpleName().equals("String")) {
//                                    record = new PurchaseRecord(generateRandomString(20), good[0][0], Integer.parseInt((String) purchaseCar[rowIndex][1]), GlobalData.getUID(), new Date());
//                                } else {
//                                    //PurchaseRecord record=new PurchaseRecord(generateRandomString(20),good[0][0],Integer.parseInt((String)purchaseCar[rowIndex][1]),GlobalData.getUID(),new Date());
//                                    record = new PurchaseRecord(generateRandomString(20), good[0][0], (int) purchaseCar[rowIndex][1], GlobalData.getUID(), new Date());
//                                }
//                                iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                                iShopClientAPI.addPurchaseRecord(record);
//
//                            }
//
//
//                            //从购物车里删除已支付的商品
//                            for (Integer rowIndex : selectedRows) {
//                                //System.out.println(rowIndex);
//                                //后端依次删除
//                                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                                iShopClientAPI.removeSelectedGood((String) purchaseCar[rowIndex][0]);
//
//                            }
//
//                            for (Integer rowIndex : selectedRows) {
//
//                                purchaseCar = deleteRow(purchaseCar, rowIndex);
//                                ShowTableDataModel3(purchaseCar);
//
//                            }
//                            totalamount.setText("￥0.00");
//                            JOptionPane.showMessageDialog(cardpaymentPanel, "充值成功，余额￥" + Double.toString(flag));
//                        }
//
//                    }
//                    dispose();
//
//                }
//            });
//
//
//            paymentPanel.add(WeChatpaymentPanel, "微信支付");
//            paymentPanel.add(cardpaymentPanel, "一卡通支付");
//
//            Container pane = getContentPane();
//            pane.add(modeofpaymentPanel, BorderLayout.NORTH);
//            pane.add(paymentPanel, BorderLayout.CENTER);
//            pane.add(cancelPanel, BorderLayout.SOUTH);
//            setSize(600, 400);
//            setLocationRelativeTo(null);
//            setResizable(false);
//            setVisible(true);
//        }
//    }
//}

package view.Shop;

import view.Bank.IBankClientAPI;
import view.Bank.IBankClientAPIImpl;
import view.Bank.bankAccount;
import view.Bank.bankBill;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.Set;
import java.util.HashSet;

public class ShopTeacherStudentUI extends JFrame implements ItemListener {
    Object[][] purchaseCar = null;

    Vector<Double> selectedGoods = new Vector<Double>();

    Boolean selectedFlag;
    Boolean deselectedFlag;
    int lastSelectedRow;
    int lastDeselectedRow;
    Set<Integer> selectedRows = new HashSet<>();
    String[][] AllGoods = null;

    Double purchaseCarAmount = 0.0;//购物车内的总金额

    //导航栏
    JButton purchasegoodBtn = new JButton("购买商品");
    JButton purchasehistoryBtn = new JButton("购买记录");
    JButton shoppingcartBtn = new JButton("购物车");
    JButton backBtn = new JButton("退出");

    //购买商品
    DefaultTableModel model = new DefaultTableModel();
    JButton searchgoodBtn = new JButton("查询商品");
    JTextField searchgoodField = new JTextField();
    JTable goodtable = new JTable();
    //购买记录
    DefaultTableModel model2 = new DefaultTableModel();
    JLabel historyLabel = new JLabel("购买记录");
    JTable historytable = new JTable();
    //购物车
    DefaultTableModel model3 = new DefaultTableModel();
    JTable carttable = new JTable();
    JLabel shoppingcartLabel = new JLabel("购物车");
    JButton purchaseBtn = new JButton("支付");
    JButton deleteBtn = new JButton("删除");
    JButton OKBtn = new JButton("选好了");
    JLabel totalamountLabel = new JLabel("总金额");
    JLabel totalamount = new JLabel("￥0.00");


    JPanel backPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    SpringLayout springLayout = new SpringLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel shopcard = new JPanel();
    JPanel blankPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Shop1.jpg");
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
    JPanel purchasegoodPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Shop1.jpg");
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
    JPanel purchasehistoryPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Shop1.jpg");
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
    JPanel shoppingcartPanel = new JPanel(springLayout){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // 加载原始尺寸的背景图片
            ImageIcon originalImageIcon = new ImageIcon("Images/Shop1.jpg");
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
     * ShopTeacherStudentUI的构造函数
     */
    public ShopTeacherStudentUI() {
        super("商店");
        selectedFlag = false;
        deselectedFlag = false;

//        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
//        Image image = new ImageIcon(resource).getImage();
//        setIconImage(image);

        //更新现在有的商品列表
        getAllGoods();

        OKBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double sum = 0.0;
                for (int i = 0; i < carttable.getRowCount(); i++) {
                    boolean isChecked = (Boolean) carttable.getValueAt(i, 3);
                    //boolean isChecked = (boolean) model.getValueAt(i, 3);
                    if (isChecked) {
                        Object dat = carttable.getValueAt(i, 2);
                        if (dat != null) {
                            String data = (String) dat;
                            Double money = Double.parseDouble(data);
                            sum += money;
                        }
                    }
                }
//                for (Double num : selectedGoods) {
//                    sum += num;
//                }
                totalamount.setText("￥"+ String.format("%.2f", sum));
            }
        });
        //导航栏
        purchasegoodBtn.setFont(buttonFont);
        purchasehistoryBtn.setFont(buttonFont);
        shoppingcartBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);
        purchasegoodBtn.setPreferredSize(new Dimension(150, 40));
        purchasehistoryBtn.setPreferredSize(new Dimension(150, 40));
        shoppingcartBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setPreferredSize(new Dimension(100, 40));

        shopcard.add(purchasegoodBtn);
        shopcard.add(purchasehistoryBtn);
        shopcard.add(shoppingcartBtn);
        backPanel.add(backBtn);

        //导航栏
        purchasegoodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "购买商品");
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
//                String[][] data=iShopClientAPI.getAllGoodsST();
//                for (int i = 0; i < data.length; i++) {
//                    for (int j = 0; j < data[i].length; j++) {
//                        System.out.print(data[i][j] + " ");
//                    }
//                    System.out.println();
//                }

                //显示所有商品
                AllGoods = iShopClientAPI.getAllGoodsST();
                ShowTableDataModel(AllGoods);
            }
        });
        purchasehistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "购买记录");
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);

                //显示当前账户所有的购买记录
                ShowTableDataModel2(iShopClientAPI.getPurchaseRecordById(GlobalData.getUID()));

            }
        });
        shoppingcartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!selectedGoods.isEmpty())
                    selectedGoods.clear();
                cardLayout.show(cardPanel, "购物车");

                // TODO 加上下面这一行，购物车的初始显示就不对
                //getPurchaseCar();
                if (purchaseCar != null) {
                    ShowTableDataModel3(purchaseCar);
                }

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });


        //购买商品
        searchgoodBtn.setFont(buttonFont);
        searchgoodField.setPreferredSize(new Dimension(200, 30));
        searchgoodField.setFont(centerFont);
        goodtable.setFont(new Font("楷体", Font.PLAIN, 20));

        String[] goodheader = {"序号", "商品名称", "价格", "库存", "操作"};
        Object[][] gooddata = {
                {"1", null, null, null, null},
                {"2", null, null, null, null},
                {"3", null, null, null, null},
                {"4", null, null, null, null},
                {"5", null, null, null, null},
                {"6", null, null, null, null},
                {"7", null, null, null, null},
                {"8", null, null, null, null},
                {"9", null, null, null, null},
                {"10", null, null, null, null}
        };
        model.setDataVector(gooddata, goodheader);
        goodtable.setModel(model);
        goodtable.getColumnModel().getColumn(4).setCellRenderer(new TableCellRendererButton());
        goodtable.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorButton());
        JScrollPane scrollPane = new JScrollPane(goodtable);
        goodtable.setRowHeight(30);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = goodtable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));

        purchasegoodPanel.add(searchgoodBtn);
        purchasegoodPanel.add(searchgoodField);
        purchasegoodPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.WEST, searchgoodBtn, 80, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, searchgoodBtn, 80, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, searchgoodField, 60, SpringLayout.EAST, searchgoodBtn);
        springLayout.putConstraint(SpringLayout.NORTH, searchgoodField, 0, SpringLayout.NORTH, searchgoodBtn);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 40, SpringLayout.SOUTH, searchgoodBtn);

        /**
         * 商品页面查询按钮
         * */
        searchgoodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查询商品按钮功能,可用商品号和商品名称查询

                String query = searchgoodField.getText();
                if (query.equals("")) {//没有字串输入时，显示所有商品
                    getAllGoods();
                    ShowTableDataModel(AllGoods);
                } else {
                    IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                    String[][] result = iShopClientAPI.findGoodST(query);
                    if (result == null) {
                        ShowTableDataModel(null);
                        JOptionPane.showMessageDialog(purchasegoodPanel, "查询结果为空");
                    } else {
                        AllGoods = result;
                        ShowTableDataModel(result);
                    }
                }

            }
        });


        //购买记录
        historyLabel.setFont(titleFont);
        historytable.setFont(new Font("楷体", Font.PLAIN, 20));

        String[] historyheader = {"商品名称", "购买数量", "支付金额", "支付时间"};
        Object[][] historydata = {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
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

        purchasehistoryPanel.add(historyscrollPane);
        purchasehistoryPanel.add(historyLabel);

        springLayout.putConstraint(SpringLayout.NORTH, historyLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, historyLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, historyscrollPane, 40, SpringLayout.SOUTH, historyLabel);
        springLayout.putConstraint(SpringLayout.WEST, historyscrollPane, 100, SpringLayout.WEST, cardPanel);

        //购物车
        carttable.setFont(new Font("楷体", Font.PLAIN, 20));
        Object[][] good = {};
        String[] cartheader = {"商品名称", "商品数量", "金额", "操作"};
        model3.setDataVector(good, cartheader);
        carttable = new JTable(model3) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) { // 最后一列是复选框列
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

        // 设置复选框渲染器和编辑器
        carttable.getColumnModel().getColumn(3).setCellRenderer(carttable.getDefaultRenderer(Boolean.class));
        carttable.getColumnModel().getColumn(3).setCellEditor(carttable.getDefaultEditor(Boolean.class));
        // 添加复选框状态变化监听器
        JCheckBox checkBox = (JCheckBox) carttable.getDefaultEditor(Boolean.class).getTableCellEditorComponent(carttable, null, false, 0, 3);
        checkBox.addItemListener(this);


        carttable.setRowHeight(30);
        JScrollPane cartpane = new JScrollPane(carttable);
        cartpane.setPreferredSize(new Dimension(1000, 300));
        JTableHeader tab_header3 = carttable.getTableHeader();                    //获取表头
        tab_header3.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header3.setPreferredSize(new Dimension(tab_header2.getWidth(), 30));

        cartpane.setOpaque(false);
        cartpane.getViewport().setBackground(new Color(255,255,255,150));

        shoppingcartLabel.setFont(titleFont);
        deleteBtn.setFont(buttonFont);
        purchaseBtn.setFont(buttonFont);
        totalamountLabel.setFont(centerFont);
        totalamount.setFont(centerFont);
        OKBtn.setFont(buttonFont);

        shoppingcartPanel.add(shoppingcartLabel);
        shoppingcartPanel.add(cartpane);
        shoppingcartPanel.add(deleteBtn);
        shoppingcartPanel.add(purchaseBtn);
        shoppingcartPanel.add(OKBtn);
        shoppingcartPanel.add(totalamountLabel);
        shoppingcartPanel.add(totalamount);

        springLayout.putConstraint(SpringLayout.NORTH, shoppingcartLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, shoppingcartLabel, 520, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cartpane, 40, SpringLayout.SOUTH, shoppingcartLabel);
        springLayout.putConstraint(SpringLayout.WEST, cartpane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteBtn, 80, SpringLayout.SOUTH, cartpane);
        springLayout.putConstraint(SpringLayout.WEST, deleteBtn, 550, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, purchaseBtn, 0, SpringLayout.NORTH, deleteBtn);
        springLayout.putConstraint(SpringLayout.WEST, purchaseBtn, 100, SpringLayout.EAST, deleteBtn);
        springLayout.putConstraint(SpringLayout.NORTH, OKBtn, 0, SpringLayout.NORTH, deleteBtn);
        springLayout.putConstraint(SpringLayout.EAST, OKBtn, -100, SpringLayout.WEST, deleteBtn);
        springLayout.putConstraint(SpringLayout.NORTH, totalamountLabel, 40, SpringLayout.SOUTH, cartpane);
        springLayout.putConstraint(SpringLayout.WEST, totalamountLabel, 800, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, totalamount, 0, SpringLayout.NORTH, totalamountLabel);
        springLayout.putConstraint(SpringLayout.WEST, totalamount, 40, SpringLayout.EAST, totalamountLabel);


        /**
         * 购物车页面删除和支付按钮
         * */
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model4 = (DefaultTableModel) carttable.getModel();
                //int rowCount = model4.getRowCount();
                int rowCount = purchaseCar.length;
                ArrayList<Integer> selectedRows = new ArrayList<>();
                //删除购物车里被选中的商品
                for (int row = 0; row < rowCount; row++) {
                    Boolean isSelected = (Boolean) model4.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
                    System.out.println("第" + row + "行的名称是:" + (String) purchaseCar[row][0]);
                    System.out.println("内容为：" + isSelected);
                    if (isSelected) {
                        selectedRows.add(row); // 记录选中的行索引
                        System.out.println("选中了行数：" + row);
                    }
                }
                if (selectedRows.size() > 0) {
                    //若有行被选中
                    for (Integer rowIndex : selectedRows) {
                        //System.out.println(rowIndex);
                        //后端依次删除
                        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                        iShopClientAPI.removeSelectedGood((String) purchaseCar[rowIndex][0]);

                    }
                    for (Integer rowIndex : selectedRows) {
                        //前端依次删除
//                        purchaseCarAmount=purchaseCarAmount+Double.parseDouble((String)purchaseCar[rowIndex][2]);
//                        String formattedAmount = String.format("%.2f", purchaseCarAmount);
//                        totalamount.setText("￥"+formattedAmount);
                        updatePurchaseCarAmount(rowIndex);
                        purchaseCar = deleteRow(purchaseCar, rowIndex);
                        ShowTableDataModel3(purchaseCar);

                    }
                }

            }
        });
        purchaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //支付选中的商品
                new purchasewindow();
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(purchasegoodPanel, "购买商品");
        cardPanel.add(purchasehistoryPanel, "购买记录");
        cardPanel.add(shoppingcartPanel, "购物车");

        Container contentPane = getContentPane();//获取控制面板
        contentPane.add(cardPanel, BorderLayout.CENTER);
        contentPane.add(shopcard, BorderLayout.NORTH);
        contentPane.add(backPanel, BorderLayout.SOUTH);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


    }

    /**
     * 向二维数组末尾增加一行数据，用于购物车增加商品。
     *
     * @param array 要添加行的二维数组
     * @param s     要添加的商品信息
     * @return 添加新行后的二维数组
     */
    public static Object[][] addRowToEnd(Object[][] array, SelectedGood s) {
        String goodName = s.getGoodName();
        int goodNums = s.getGoodNums();
        double totalPrice = s.getGoodNums() * s.getGoodPrice();

        if (array == null) {
            // 如果原始数组为 null，则创建一个新的二维数组并将新行作为唯一行添加到其中
            return new Object[][]{{goodName, Integer.toString(goodNums), Double.toString(totalPrice), false}};
        } else {
            int numRows = array.length;//name nums 金额
            int numCols = array[0].length;

            // 遍历数组，检查是否存在相同商品
            for (int i = 0; i < numRows; i++) {
                String existingGoodName = (String) array[i][0];
                if (existingGoodName.equals(goodName)) {
                    // 商品已存在，增加对应数量
                    int existingGoodNums = Integer.parseInt(String.valueOf(array[i][1]));
                    double existingTotalPrice = Double.parseDouble((String) array[i][2]);
                    array[i][1] = Integer.toString(existingGoodNums + goodNums);
                    array[i][2] = Double.toString(existingTotalPrice + totalPrice);
                    return array;
                }
            }

            // 购物车中不存在相同商品，创建新的行并添加到数组末尾
            int numColsNew = numCols;
            Object[][] newArray = new Object[numRows + 1][numColsNew];
            for (int i = 0; i < numRows; i++) {
                System.arraycopy(array[i], 0, newArray[i], 0, numCols);
            }

            Object[] newRow = new Object[numColsNew];
            newRow[0] = goodName;
            newRow[1] = s.getGoodNums();
            newRow[2] = Double.toString(totalPrice);
            newRow[3] = false;

            newArray[numRows] = newRow;
            return newArray;
        }
    }

    /**
     * 从二维数组中删除指定行，可用于前端删除购物车物品。
     *
     * @param array    要删除行的二维数组
     * @param rowIndex 要删除的行索引
     * @return 删除指定行后的二维数组
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
     * @param args
     * 主函数
     */
    public static void main(String[] args) {
        new ShopTeacherStudentUI();
    }

    /**
     * 随机生成LENGTH位数字的String类型数据
     *
     * @param LENGTH 要生成的String类型的长度
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

    /**
     * 更新购物车界面选中商品的总金额
     *
     * @param selectedRow 当前被选中的行数
     */
    private void updatePurchaseCarAmount(int selectedRow) {
        int rowCount = model3.getRowCount();
        ArrayList<Integer> selectedRows = new ArrayList<>();
        purchaseCarAmount = 0.0;
        //删除购物车里被选中的商品
        for (int row = 0; row < rowCount; row++) {
            Boolean isSelected = (Boolean) model3.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
            if (row == selectedRow) {
                if (isSelected == true)
                    isSelected = false;
                else
                    isSelected = true;
            }
            System.out.println("内容为2222：" + isSelected);
            if (isSelected) {
                selectedRows.add(row); // 记录选中的行索引
                purchaseCarAmount = purchaseCarAmount + Double.parseDouble((String) purchaseCar[row][2]);
                System.out.println("选中了行数：" + row);
            }
        }
        String formattedAmount = String.format("%.2f", purchaseCarAmount);
        totalamount.setText("￥" + formattedAmount);
    }

    /**
     * 显示商品的表格信息
     *
     * @param data 购买记录数据，二维数组，每行代表一个商品
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
        goodtable.getColumnModel().getColumn(4).setCellRenderer(new TableCellRendererButton());
        goodtable.getColumnModel().getColumn(4).setCellEditor(new TableCellEditorButton());
    }

    /**
     * 显示购买记录的表格信息
     *
     * @param data 购买记录数据，二维数组，每行代表一个购买记录
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
     *
     * @param data 购买记录数据，二维数组，每行代表一个购物车商品信息
     */
    private void ShowTableDataModel3(Object[][] data) {
        //若查询结果为空
        if (data == null) {
            System.out.println("查询结果为空");
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
        // 设置复选框渲染器和编辑器
        carttable.getColumnModel().getColumn(3).setCellRenderer(carttable.getDefaultRenderer(Boolean.class));
        carttable.getColumnModel().getColumn(3).setCellEditor(carttable.getDefaultEditor(Boolean.class));
    }

    /**
     * 前端获取后端的购物车数组数据
     */
    public void getPurchaseCar() {
        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
        purchaseCar = iShopClientAPI.getSelectedGoods();
    }

    /**
     * item改动时的响应函数
     * @param e the event to be processed
     */
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if(!selectedFlag){
                selectedFlag = true;
                lastSelectedRow = carttable.getSelectedRow();
                selectedRows.add(lastSelectedRow);
                updateTotalAmount();
                return;
            }
            // 持续选中
            if(selectedFlag){
                int selectedRow = carttable.getSelectedRow();
                selectedRows.add(lastDeselectedRow);
                selectedRows.add(selectedRow);
                lastSelectedRow = selectedRow;
                updateTotalAmount();
            }

            // 在这里处理复选框选中时的逻辑
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {

            // 复选框被取消选中
            int selectedRow = carttable.getSelectedRow();
            selectedRows.remove(selectedRow);
            updateTotalAmount();
        }
    }

    /**
     * 更新下面总价格图标
     */
    private void updateTotalAmount() {
        double total = 0.0;
        for (int selectedRow : selectedRows) {
            Object dat = carttable.getValueAt(selectedRow, 2);
            if (dat != null) {
                String data = (String) dat;
                double money = Double.parseDouble(data);
                total += money;
            }
        }

        totalamount.setText("￥" + Double.toString(total));
    }
    /**
     * 获取所有的商品
     */
    public void getAllGoods() {
        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
        AllGoods = iShopClientAPI.getAllGoodsST();
    }

    /**
     * 商品页面”加入购物车“的按钮相关类
     * */
    class TableCellRendererButton implements TableCellRenderer {
        JButton button = new JButton("加入购物车");

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


        /**
         * 在表格中生成按钮
         */
        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("加入购物车");


            btn.setFont(new Font("楷体", Font.PLAIN, 25));
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 获取所在行的索引
                    JButton clickedButton = (JButton) e.getSource();
                    //int clickedRow;
                    clickedRow = (int) clickedButton.getClientProperty("row");
                    new addtocartwindow(clickedRow);

                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedRow = row;
            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性
            return btn;
        }

    }


    class addtocartwindow extends JFrame {
        JPanel window = new JPanel(springLayout);
        JLabel goodnameLabel = new JLabel("商品名称");
        JLabel goodname = new JLabel("XXX");
        JLabel countLabel = new JLabel("购买数量");
        JButton addtoshoppingcartBtn = new JButton("加入购物车");
        JButton cancelBtn = new JButton("取消");
        JComboBox<String> count = new JComboBox<String>();

        /**
         * @param row
         * 加入购物车的弹窗
         */
        public addtocartwindow(int row) {

            goodname.setFont(centerFont);
            goodnameLabel.setFont(centerFont);
            countLabel.setFont(centerFont);
            count.setFont(new Font("楷体", Font.PLAIN, 20));
            addtoshoppingcartBtn.setFont(buttonFont);
            cancelBtn.setFont(buttonFont);

            count.addItem("1");
            count.addItem("2");
            count.addItem("3");
            count.addItem("4");
            count.addItem("5");
            count.addItem("6");
            count.addItem("7");
            count.addItem("8");
            count.addItem("9");
            count.addItem("10");

            window.add(goodnameLabel);
            window.add(goodname);
            window.add(count);
            window.add(countLabel);
            window.add(addtoshoppingcartBtn);
            window.add(cancelBtn);

            // 显示当前商品名称
            goodname.setText(AllGoods[row][1]);

            addtoshoppingcartBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //获取购买数量
                    String selectedCount = (String) count.getSelectedItem();
                    //通过商品名称查找到原商品
                    IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                    String[][] g = iShopClientAPI.findGoodST(AllGoods[row][1]);

//                    GoodStrings[i][0] = good.getGoodId();      // 商品编号
//                    GoodStrings[i][1] = good.getGoodName();    // 商品名称
//                    GoodStrings[i][2] = Double.toString(good.getGoodPrice());   // 商品价格
//                    GoodStrings[i][3] = Integer.toString(good.getGoodStock());  // 商品库存
//                    GoodStrings[i][4] = "";
                    //新建一个购物车商品条目
                    SelectedGood s = new SelectedGood(g[0][0], g[0][1], Double.parseDouble(g[0][2]), Integer.parseInt(selectedCount));
                    //判断库存判断库存等限制信息
                    if (Integer.parseInt(selectedCount) <= Integer.parseInt(g[0][3])) {//库存够
                        //前端购物车数据添加
                        purchaseCar = addRowToEnd(purchaseCar, s);
//                        String[][] data=purchaseCar;
//                        for (int i = 0; i < data.length; i++) {
//                            for (int j = 0; j < data[i].length; j++) {
//                                System.out.print(data[i][j] + " ");
//                            }
//
//                        }

                        //后端购物车数据添加(注意，要重开一个API)
                        IShopClientAPI iShopClientAPI2 = new IShopClientAPIImpl("localhost", 8888);
                        iShopClientAPI2.addSelectedGood(g[0][1], Integer.parseInt(selectedCount));
                        JOptionPane.showMessageDialog(window, "加入购物车成功！");
                    } else {
                        JOptionPane.showMessageDialog(window, "抱歉！库存不足！");
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

            springLayout.putConstraint(SpringLayout.WEST, goodnameLabel, 180, SpringLayout.WEST, window);
            springLayout.putConstraint(SpringLayout.NORTH, goodnameLabel, 80, SpringLayout.NORTH, window);
            springLayout.putConstraint(SpringLayout.WEST, goodname, 40, SpringLayout.EAST, goodnameLabel);
            springLayout.putConstraint(SpringLayout.NORTH, goodname, 0, SpringLayout.NORTH, goodnameLabel);
            springLayout.putConstraint(SpringLayout.WEST, countLabel, 0, SpringLayout.WEST, goodnameLabel);
            springLayout.putConstraint(SpringLayout.NORTH, countLabel, 60, SpringLayout.SOUTH, goodnameLabel);
            springLayout.putConstraint(SpringLayout.WEST, count, 0, SpringLayout.WEST, goodname);
            springLayout.putConstraint(SpringLayout.NORTH, count, 0, SpringLayout.NORTH, countLabel);
            springLayout.putConstraint(SpringLayout.WEST, addtoshoppingcartBtn, 125, SpringLayout.WEST, window);
            springLayout.putConstraint(SpringLayout.SOUTH, addtoshoppingcartBtn, -40, SpringLayout.SOUTH, window);
            springLayout.putConstraint(SpringLayout.WEST, cancelBtn, 80, SpringLayout.EAST, addtoshoppingcartBtn);
            springLayout.putConstraint(SpringLayout.SOUTH, cancelBtn, 0, SpringLayout.SOUTH, addtoshoppingcartBtn);

            Container pane = getContentPane();
            pane.add(window);
            setSize(600, 400);
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

        /**
         * 支付弹窗
         */
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

            /**
             * 支付按钮
             * */
            confirmpurchaseBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //DefaultTableModel model4 = (DefaultTableModel) carttable.getModel();
                    //int rowCount = model4.getRowCount();
                    int rowCount = purchaseCar.length;
                    ArrayList<Integer> selectedRows = new ArrayList<>();
                    //支付购物车里被选中的商品
                    for (int row = 0; row < rowCount; row++) {
                        Boolean isSelected = (Boolean) model3.getValueAt(row, 3); // 获取第 3 列（操作列）的值，即 JCheckBox 是否选中
                        System.out.println("(支付)第" + row + "行的名称是:" + (String) purchaseCar[row][0]);
                        System.out.println("(支付)内容为：" + isSelected);
                        if (isSelected) {
                            selectedRows.add(row); // 记录选中的行索引
                            System.out.println("(支付)选中了行数：" + row);
                        }
                    }
                    double flag = -4.00;
                    IBankClientAPI iBankClientAPI = new IBankClientAPIImpl("localhost", 8888);
                    if (selectedRows.size() > 0) {
                        //若有行被选中
                        // 付钱

                        bankAccount account = iBankClientAPI.findBankAccountById(GlobalData.getUID());

                        bankBill bill = new bankBill("商店", generateRandomString(20), account.getCardId(), GlobalData.getUID(), new Date(), true, purchaseCarAmount);

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
                        } else if(flag>=0){
                            //后端依次增加消费记录
                            for (Integer rowIndex : selectedRows) {
                                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                                String[][] good = iShopClientAPI.findGoodST((String) purchaseCar[rowIndex][0]);
                                System.out.println("(String)purchaseCar[rowIndex][1]:" + purchaseCar[rowIndex][1]);
                                Class<?> objClass = purchaseCar[rowIndex][1].getClass();
                                PurchaseRecord record;
                                if (objClass.getSimpleName().equals("String")) {
                                    record = new PurchaseRecord(generateRandomString(20), good[0][0], Integer.parseInt((String) purchaseCar[rowIndex][1]), GlobalData.getUID(), new Date());
                                } else {
                                    //PurchaseRecord record=new PurchaseRecord(generateRandomString(20),good[0][0],Integer.parseInt((String)purchaseCar[rowIndex][1]),GlobalData.getUID(),new Date());
                                    record = new PurchaseRecord(generateRandomString(20), good[0][0], (int) purchaseCar[rowIndex][1], GlobalData.getUID(), new Date());
                                }
                                iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                                iShopClientAPI.addPurchaseRecord(record);

                            }


                            //从购物车里删除已支付的商品
                            for (Integer rowIndex : selectedRows) {
                                //System.out.println(rowIndex);
                                //后端依次删除
                                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                                iShopClientAPI.removeSelectedGood((String) purchaseCar[rowIndex][0]);

                            }

                            for (Integer rowIndex : selectedRows) {

                                purchaseCar = deleteRow(purchaseCar, rowIndex);
                                ShowTableDataModel3(purchaseCar);

                            }
                            totalamount.setText("￥0.00");
                            JOptionPane.showMessageDialog(cardpaymentPanel, "充值成功，余额￥" + Double.toString(flag));
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
}

