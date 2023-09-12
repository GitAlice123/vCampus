package view.Shop;

import view.Global.SummaryUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopManagerUI extends JFrame {
    String[][] AllGoods = null;

    //导航栏
    JButton searchgoodBtn = new JButton("查询商品");
    JButton purchasehistoryBtn = new JButton("购买记录");
    JButton stockBtn = new JButton("商品进货");
    JButton returnBtn = new JButton("商品退货");
    JButton backBtn = new JButton("退出");
    //查询商品
    DefaultTableModel model = new DefaultTableModel();
    JButton searchBtn = new JButton("查询商品");
    JTextField searchgoodField = new JTextField();
    JTable goodtable = new JTable();
    //购买记录
    DefaultTableModel model2 = new DefaultTableModel();
    JLabel historyLabel = new JLabel("购买记录");
    JTable historytable = new JTable();
    //商品进货
    JLabel stockLabel = new JLabel("商品进货");
    JLabel goodIDLabel = new JLabel("商品编号");
    JLabel goodnameLabel = new JLabel("商品名称");
    JLabel goodpriceLabel = new JLabel("商品价格");
    JLabel goodcategoryLabel = new JLabel("商品类别");
    JLabel supplierLabel = new JLabel("供应商");
    JLabel countLabel = new JLabel("进货数量");
    JTextField goodIDField = new JTextField();
    JTextField goodnameField = new JTextField();
    JTextField goodpriceField = new JTextField();
    JTextField goodcategoryField = new JTextField();
    JTextField supplierField = new JTextField();
    JTextField countField = new JTextField();
    JButton searchBtn2 = new JButton("查询");
    JButton confirmBtn = new JButton("确认");
    //商品退货
    JLabel returnLabel = new JLabel("商品退货");
    JLabel goodIDLabel2 = new JLabel("商品编号");
    JLabel countLabel2 = new JLabel("退货数量");
    JTextField goodIDField2 = new JTextField();
    JTextField countField2 = new JTextField();
    JButton confirmBtn2 = new JButton("确认");


    JPanel backPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    SpringLayout springLayout = new SpringLayout();
    JPanel cardPanel = new JPanel(cardLayout);
    JPanel shopcard = new JPanel();
    JPanel blankPanel = new JPanel();
    JPanel searchgoodPanel = new JPanel(springLayout);
    JPanel purchasehistoryPanel = new JPanel(springLayout);
    JPanel stockPanel = new JPanel(springLayout);
    JPanel returnPanel = new JPanel(springLayout);


    Font buttonFont = new Font("楷体", Font.PLAIN, 25);//设置按钮的文字大小、字体
    Font titleFont = new Font("楷体", Font.PLAIN, 50);
    Font centerFont = new Font("楷体", Font.PLAIN, 30);//设置中间组件的文字大小、字体

    public ShopManagerUI() {
        super("商店");
        //获取所有商品
        getAllGoods();
//        URL resource = this.getClass().getClassLoader().getResource("SEU.png");
//        Image image = new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        searchgoodBtn.setFont(buttonFont);
        purchasehistoryBtn.setFont(buttonFont);
        stockBtn.setFont(buttonFont);
        returnBtn.setFont(buttonFont);
        backBtn.setFont(buttonFont);
        searchgoodBtn.setPreferredSize(new Dimension(150, 40));
        purchasehistoryBtn.setPreferredSize(new Dimension(150, 40));
        stockBtn.setPreferredSize(new Dimension(150, 40));
        returnBtn.setPreferredSize(new Dimension(150, 40));
        backBtn.setPreferredSize(new Dimension(100, 40));

        shopcard.add(searchgoodBtn);
        shopcard.add(purchasehistoryBtn);
        shopcard.add(stockBtn);
        shopcard.add(returnBtn);
        backPanel.add(backBtn);

        searchgoodBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "查询商品");
                getAllGoods();
                ShowTableDataModel(AllGoods);
            }
        });
        purchasehistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "购买记录");
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                String[][] result = iShopClientAPI.getAllPurchaseRecord();
                ShowTableDataModel2(result);
            }
        });
        stockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPage();
                cardLayout.show(cardPanel, "商品进货");

            }
        });
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "商品退货");
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        //查询商品
        searchBtn.setFont(buttonFont);
        searchgoodField.setPreferredSize(new Dimension(200, 30));
        searchgoodField.setFont(centerFont);
        goodtable.setFont(new Font("楷体", Font.PLAIN, 20));

        String[] goodheader = {"序号", "商品名称", "价格", "库存"};
        Object[][] gooddata = {
                {"1", null, null, null},
                {"2", null, null, null},
                {"3", null, null, null},
                {"4", null, null, null},
                {"5", null, null, null},
                {"6", null, null, null},
                {"7", null, null, null},
                {"8", null, null, null},
                {"9", null, null, null},
                {"10", null, null, null}
        };
        model.setDataVector(gooddata, goodheader);
        goodtable.setModel(model);
        JScrollPane goodscrollPane = new JScrollPane(goodtable);
        goodscrollPane.setFont(centerFont);
        goodtable.setRowHeight(30);
        goodscrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = goodtable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        searchgoodPanel.add(goodscrollPane);
        searchgoodPanel.add(searchBtn);
        searchgoodPanel.add(searchgoodField);

        springLayout.putConstraint(SpringLayout.WEST, searchBtn, 80, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, searchBtn, 80, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, searchgoodField, 60, SpringLayout.EAST, searchBtn);
        springLayout.putConstraint(SpringLayout.NORTH, searchgoodField, 0, SpringLayout.NORTH, searchBtn);
        springLayout.putConstraint(SpringLayout.WEST, goodscrollPane, 100, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodscrollPane, 40, SpringLayout.SOUTH, searchBtn);

        //查询商品页面的查询按钮
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查询商品按钮功能
                //查询商品按钮功能,可用商品号和商品名称查询
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                String query = searchgoodField.getText();
                if (query.equals("")) {//没有字串输入时，显示所有商品
                    getAllGoods();
                    ShowTableDataModel(AllGoods);
                } else {
                    String[][] result = iShopClientAPI.findGoodM(query);
                    if (result == null) {
                        ShowTableDataModel(null);
                        JOptionPane.showMessageDialog(searchgoodPanel, "查询结果为空");
                    } else {
                        ShowTableDataModel(result);
                    }
                }
            }
        });

        //购买记录
        historyLabel.setFont(titleFont);
        historytable.setFont(new Font("楷体", Font.PLAIN, 20));

        String[] historyheader = {"购买订单号", "一卡通号", "商品名称", "购买数量", "支付金额", "支付时间"};
        Object[][] historydata = {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
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

        purchasehistoryPanel.add(historyscrollPane);
        purchasehistoryPanel.add(historyLabel);

        springLayout.putConstraint(SpringLayout.NORTH, historyLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, historyLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, historyscrollPane, 40, SpringLayout.SOUTH, historyLabel);
        springLayout.putConstraint(SpringLayout.WEST, historyscrollPane, 100, SpringLayout.WEST, cardPanel);

        //商品进货
        stockLabel.setFont(titleFont);
        goodIDLabel.setFont(centerFont);
        goodnameLabel.setFont(centerFont);
        goodpriceLabel.setFont(centerFont);
        goodcategoryLabel.setFont(centerFont);
        supplierLabel.setFont(centerFont);
        countLabel.setFont(centerFont);
        goodIDField.setFont(centerFont);
        goodnameField.setFont(centerFont);
        goodpriceField.setFont(centerFont);
        goodcategoryField.setFont(centerFont);
        supplierField.setFont(centerFont);
        countField.setFont(centerFont);
        searchBtn2.setFont(buttonFont);
        confirmBtn.setFont(buttonFont);
        goodIDField.setPreferredSize(new Dimension(200, 30));
        goodnameField.setPreferredSize(new Dimension(200, 30));
        goodpriceField.setPreferredSize(new Dimension(200, 30));
        goodcategoryField.setPreferredSize(new Dimension(200, 30));
        supplierField.setPreferredSize(new Dimension(200, 30));
        countField.setPreferredSize(new Dimension(200, 30));

        stockPanel.add(stockLabel);
        stockPanel.add(goodIDLabel);
        stockPanel.add(goodnameLabel);
        stockPanel.add(goodpriceLabel);
        stockPanel.add(goodcategoryLabel);
        stockPanel.add(supplierLabel);
        stockPanel.add(countLabel);
        stockPanel.add(goodIDField);
        stockPanel.add(goodnameField);
        stockPanel.add(goodpriceField);
        stockPanel.add(goodcategoryField);
        stockPanel.add(supplierField);
        stockPanel.add(countField);
        stockPanel.add(searchBtn2);
        stockPanel.add(confirmBtn);

        springLayout.putConstraint(SpringLayout.NORTH, stockLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, stockLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodIDLabel, 40, SpringLayout.SOUTH, stockLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodIDLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodnameLabel, 40, SpringLayout.SOUTH, goodIDLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodnameLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodpriceLabel, 40, SpringLayout.SOUTH, goodnameLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodpriceLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodcategoryLabel, 40, SpringLayout.SOUTH, goodpriceLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodcategoryLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, supplierLabel, 40, SpringLayout.SOUTH, goodcategoryLabel);
        springLayout.putConstraint(SpringLayout.WEST, supplierLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, countLabel, 40, SpringLayout.SOUTH, supplierLabel);
        springLayout.putConstraint(SpringLayout.WEST, countLabel, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodIDField, 0, SpringLayout.NORTH, goodIDLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodIDField, 100, SpringLayout.EAST, goodIDLabel);
        springLayout.putConstraint(SpringLayout.NORTH, goodnameField, 0, SpringLayout.NORTH, goodnameLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodnameField, 0, SpringLayout.WEST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, goodpriceField, 0, SpringLayout.NORTH, goodpriceLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodpriceField, 0, SpringLayout.WEST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, goodcategoryField, 0, SpringLayout.NORTH, goodcategoryLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodcategoryField, 0, SpringLayout.WEST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, supplierField, 0, SpringLayout.NORTH, supplierLabel);
        springLayout.putConstraint(SpringLayout.WEST, supplierField, 0, SpringLayout.WEST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, countField, 0, SpringLayout.NORTH, countLabel);
        springLayout.putConstraint(SpringLayout.WEST, countField, 0, SpringLayout.WEST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, searchBtn2, 0, SpringLayout.NORTH, goodIDLabel);
        springLayout.putConstraint(SpringLayout.WEST, searchBtn2, 40, SpringLayout.EAST, goodIDField);
        springLayout.putConstraint(SpringLayout.NORTH, confirmBtn, 40, SpringLayout.SOUTH, countLabel);
        springLayout.putConstraint(SpringLayout.WEST, confirmBtn, 550, SpringLayout.WEST, cardPanel);

        //商品进货页面的查询按钮
        searchBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //判断商店中是否已有该编号的商品，若有，显示出所有剩余信息，若没有，则不显示其他信息，需手动录入新商品
                String goodID = goodIDField.getText();//获取输入的商品编号
                if (goodID.equals("")) {//如果输入为空
                    JOptionPane.showMessageDialog(stockPanel, "输入为空！查询失败");
                } else {
                    //根据商品编号查询商品，自动填充其他的field
                    IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                    String[][] g = iShopClientAPI.findGoodAllInfo(goodID);
                    if (g == null) {//若查询不成功,商店里现在没有这种商品,就要新建商品
                        JOptionPane.showMessageDialog(stockPanel, "商品不存在!请输入其他信息");

                    } else {//查询成功就自动填充field
                        goodnameField.setText(g[0][1]);
                        goodpriceField.setText(g[0][2]);
                        goodcategoryField.setText(g[0][3]);
                        supplierField.setText(g[0][4]);
                    }
                }

            }
        });
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //确认添加商品
                String goodID = goodIDField.getText();
                String goodName = goodnameField.getText();
                String goodPrice = goodpriceField.getText();
                String goodcategory = goodcategoryField.getText();
                String supplier = supplierField.getText();
                String count = countField.getText();
                //创建要进货的good
                Good g = new Good(goodID, goodName, Double.parseDouble(goodPrice), goodcategory, supplier, Integer.parseInt(count));
                //后端进货
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);

                boolean result = iShopClientAPI.ManagerAddGood(g);

                if (result) {
                    JOptionPane.showMessageDialog(stockPanel, "进货成功");
                    refreshPage();
                    ;
                } else {
                    JOptionPane.showMessageDialog(stockPanel, "进货失败");
                }
            }
        });

        //商品退货
        returnLabel.setFont(titleFont);
        goodIDLabel2.setFont(centerFont);
        countLabel2.setFont(centerFont);
        goodIDField2.setFont(centerFont);
        countField2.setFont(centerFont);
        confirmBtn2.setFont(buttonFont);
        goodIDField2.setPreferredSize(new Dimension(200, 30));
        countField2.setPreferredSize(new Dimension(200, 30));

        returnPanel.add(returnLabel);
        returnPanel.add(goodIDLabel2);
        returnPanel.add(countLabel2);
        returnPanel.add(goodIDField2);
        returnPanel.add(countField2);
        returnPanel.add(confirmBtn2);

        springLayout.putConstraint(SpringLayout.NORTH, returnLabel, 40, SpringLayout.NORTH, cardPanel);
        springLayout.putConstraint(SpringLayout.WEST, returnLabel, 500, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodIDLabel2, 40, SpringLayout.SOUTH, returnLabel);
        springLayout.putConstraint(SpringLayout.WEST, goodIDLabel2, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, countLabel2, 40, SpringLayout.SOUTH, goodIDLabel2);
        springLayout.putConstraint(SpringLayout.WEST, countLabel2, 300, SpringLayout.WEST, cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH, goodIDField2, 0, SpringLayout.NORTH, goodIDLabel2);
        springLayout.putConstraint(SpringLayout.WEST, goodIDField2, 100, SpringLayout.EAST, goodIDLabel2);
        springLayout.putConstraint(SpringLayout.NORTH, countField2, 0, SpringLayout.NORTH, countLabel2);
        springLayout.putConstraint(SpringLayout.WEST, countField2, 0, SpringLayout.WEST, goodIDField2);
        springLayout.putConstraint(SpringLayout.NORTH, confirmBtn2, 80, SpringLayout.SOUTH, countLabel2);
        springLayout.putConstraint(SpringLayout.WEST, confirmBtn2, 550, SpringLayout.WEST, cardPanel);

        //退货按钮
        confirmBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //确认删除商品，若商店中没有该编号的商品，或输入的数量大于该商品的库存数量，则弹出弹窗提示管理员
                String goodID = goodIDField2.getText();
                String count = countField2.getText();
                IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
                if (iShopClientAPI.ManagerReduceGood(goodID, Integer.parseInt(count))) {
                    JOptionPane.showMessageDialog(returnPanel, "退货成功");
                    refreshPage();
                } else {
                    JOptionPane.showMessageDialog(returnPanel, "退货失败");
                }
            }
        });


        cardPanel.add(blankPanel);
        cardPanel.add(searchgoodPanel, "查询商品");
        cardPanel.add(purchasehistoryPanel, "购买记录");
        cardPanel.add(stockPanel, "商品进货");
        cardPanel.add(returnPanel, "商品退货");

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

    public static void main(String[] args) {
        new ShopManagerUI();
    }

    /**
     * 显示商品的表格信息
     */
    private void ShowTableDataModel(String[][] data) {
        //若查询结果为空
        if (data == null) {
            System.out.println("查询结果为空");
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
     * 获取所有的商品
     */
    public void getAllGoods() {
        IShopClientAPI iShopClientAPI = new IShopClientAPIImpl("localhost", 8888);
        AllGoods = iShopClientAPI.getAllGoodsM();
    }

    /**
     * 刷新页面
     */
    public void refreshPage() {
        searchgoodField.setText("");
        goodIDField.setText("");
        goodnameField.setText("");
        goodpriceField.setText("");
        goodcategoryField.setText("");
        supplierField.setText("");
        countField.setText("");
        goodIDField2.setText("");
        countField2.setText("");

    }
}
