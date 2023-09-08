package view.Bank;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import view.Global.GlobalData;
import view.Global.SummaryUI;
import view.Library.LibraryAdminUI;

public class BankManagerUI extends JFrame {

    String[][] accounts=null;

    String[] header = {"校园卡账户", "姓名", "学工号", "账户余额", "挂失状态", "操作"};
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
            {null, null, null, null, null, null}
    };
    JButton searchBtn = new JButton("查询");
    JTextField searchField = new JTextField();

    //    MyTableModel model = new MyTableModel(header,data);
//    JTable table = new JTable(model);
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    JButton backBtn=new JButton("退出");
    JPanel BottomPanel=new JPanel();
    JPanel centerPanel=new JPanel();

    class TableCellRendererButton extends JButton implements TableCellRenderer {

        public TableCellRendererButton() {
//            setOpaque(true);
//            setFont(new Font("楷体", Font.PLAIN, 25));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("挂失/解挂");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            Color customColor = new Color(180, 218, 192);
            button.setBackground(customColor);
            return button;
        }
    }

    class TableCellEditorButton extends DefaultCellEditor {

        private JButton btn;
        private int clickedRow;

        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("挂失/解挂");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            Color customColor = new Color(180, 218, 192);
            btn.setBackground(customColor);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    confirmfreezeManager(e);

                    ShowTableData(accounts);
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedRow = row;
            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性
            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

    }
    public BankManagerUI() {
        super("银行");

        Font centerFont=new Font("楷体",Font.PLAIN,30);//设置中间组件的文字大小、字体

        // TODO  图片路径问题
//        URL resource =this.getClass().getClassLoader().getResource("Images/SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //主界面

        model.setDataVector(data, header);
        table.setModel(model);
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new TableCellEditorButton());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度

        searchBtn.setFont(centerFont);
        Color customColor = new Color(180, 218, 192);
        searchBtn.setBackground(customColor);
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.setFont(centerFont);
        table.setFont(new Font("楷体",Font.PLAIN,20));

        centerPanel.add(searchBtn);
        centerPanel.add(searchField);
        centerPanel.add(scrollPane);

        SpringLayout springLayout=new SpringLayout();
        centerPanel.setLayout(springLayout);
        springLayout.putConstraint(SpringLayout.WEST,searchBtn,80,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,searchBtn,80,SpringLayout.NORTH,centerPanel);
        springLayout.putConstraint(SpringLayout.WEST,searchField,60,SpringLayout.EAST,searchBtn);
        springLayout.putConstraint(SpringLayout.NORTH,searchField,0,SpringLayout.NORTH,searchBtn);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,40,SpringLayout.SOUTH,searchBtn);



        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(centerPanel);

        //退出
        backBtn.setFont(centerFont);
        backBtn.setBackground(customColor);
        BottomPanel.add(backBtn);

        //表格显示初始化
        getAccount("");
        ShowTableData(accounts);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uID=searchField.getText();
                getAccount(uID);
                ShowTableData(accounts);
            }
        });


        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }




    /**
     * 管理员改变账户的挂失状态
     * */
    private void confirmfreezeManager(ActionEvent e){
        IBankClientAPI iBankClientAPI=new IBankClientAPIImpl("localhost", 8888);
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);
        //修改后端数据库中的卡片挂失状态
        iBankClientAPI.changeLoss(accounts[clickedRow][2], null);
        //修改当前显示的accounts的卡片挂失的状态的显示
        accounts[clickedRow][4]=(accounts[clickedRow][4].equals("正常")?"已挂失":"正常");

        // 修改对应按钮单元格的值
        //String buttonText = clickedButton.getText();
        //String newButtonText = buttonText.equals("挂失") ? "解挂" : "挂失";

        //model.setValueAt(newButtonText, clickedRow, 5); // 修改第 5 列的值
        // TODO 这里按钮的值不变！！！
    }

    /**
     * 管理员获取所有账户或者查询一个账户，若要获取所有账户，传入id为null即可
     * */
    public void getAccount(String id){
        IBankClientAPI iBankClientAPI=new IBankClientAPIImpl("localhost", 8888);
        accounts= iBankClientAPI.findBankAccounts(id);
    }

    /**
     * 显示所有账户的表格信息
     * */
    private void ShowTableData(String[][] accounts) {
        //若查询结果为空
        if(accounts==null){
            System.out.println("查询结果为空");
            model.setRowCount(0);
            return;
        }

        // 清空表格原有的数据
        model.setRowCount(0);

        // 将新数据添加到表格模型
        for (String[] row : accounts) {
            model.addRow(row);
        }
        // 通知表格模型数据发生变化，刷新表格显示
        model.fireTableDataChanged();
    }

    public void refreshPage(){
        searchField.setText("");
    }


    public static void main(String[] args)
    {
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
        new BankManagerUI();
    }
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