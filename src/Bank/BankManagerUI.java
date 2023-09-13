package view.Bank;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import view.Global.SummaryStudentTeacherUI;

public class BankManagerUI extends JFrame {
    //导航栏
    JButton informationBtn =new JButton("信息查询");
    JButton feesBtn=new JButton("学杂费");

    //信息查询
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

    //学杂费
    JLabel feesLabel=new JLabel("学杂费");
    JLabel reasonLabel=new JLabel("扣费缘由");
    JLabel feeacountLabel=new JLabel("扣费金额");
    JComboBox<String>type=new JComboBox<String>();;
    JTextField feeacountField=new JTextField();
    JButton confirmBtn=new JButton("一键扣费");


    CardLayout cardLayout=new CardLayout();
    SpringLayout springLayout=new SpringLayout();

    JPanel blank=new JPanel();
    JPanel cardPanel=new JPanel(cardLayout);
    JPanel feesPanel=new JPanel(springLayout);
    JPanel topPanel=new JPanel();
    JPanel centerPanel=new JPanel(springLayout);
    JButton backBtn=new JButton("退出");
    JPanel BottomPanel=new JPanel();


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

        Font buttonFont=new Font("楷体",Font.PLAIN,25);//设置按钮的文字大小、字体
        Font titleFont=new Font("楷体",Font.PLAIN,50);
        Font centerFont=new Font("楷体",Font.PLAIN,30);//设置中间组件的文字大小、字体

        // TODO  图片路径问题
//        URL resource =this.getClass().getClassLoader().getResource("Images/SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //导航栏
        informationBtn.setFont(buttonFont);
        feesBtn.setFont(buttonFont);

        informationBtn.setPreferredSize(new Dimension(150,40));
        feesBtn.setPreferredSize(new Dimension(150,40));

        topPanel.add(informationBtn);
        topPanel.add(feesBtn);

        informationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"信息查询");
                getAccount("");
                ShowTableData(accounts);
            }
        });
        feesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel,"学杂费");
            }
        });


        //信息查询
        model.setDataVector(data, header);
        table.setModel(model);
        table.setDefaultRenderer(Object.class, new view.Bank.BankManagerUI.TableBackgroundColorRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new view.Bank.BankManagerUI.TableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new view.Bank.BankManagerUI.TableCellEditorButton());
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
        searchField.setFont(new Font("楷体",Font.PLAIN,20));
        table.setFont(new Font("楷体",Font.PLAIN,20));

        centerPanel.add(searchBtn);
        centerPanel.add(searchField);
        centerPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.WEST,searchBtn,80,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,searchBtn,80,SpringLayout.NORTH,centerPanel);
        springLayout.putConstraint(SpringLayout.WEST,searchField,60,SpringLayout.EAST,searchBtn);
        springLayout.putConstraint(SpringLayout.NORTH,searchField,0,SpringLayout.NORTH,searchBtn);
        //表格位置
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,40,SpringLayout.SOUTH,searchBtn);

        //学杂费
        feesLabel.setFont(titleFont);
        reasonLabel.setFont(centerFont);
        feeacountLabel.setFont(centerFont);
        type.setFont(new Font("楷体",Font.PLAIN,25));
        feeacountField.setFont(new Font("楷体",Font.PLAIN,20));
        feeacountField.setPreferredSize(new Dimension(200,30));
        confirmBtn.setFont(buttonFont);

        feesPanel.add(feesLabel);
        feesPanel.add(reasonLabel);
        feesPanel.add(feeacountLabel);
        feesPanel.add(type);
        feesPanel.add(feeacountField);
        feesPanel.add(confirmBtn);

        type.addItem("");
        type.addItem("网费");
        type.addItem("水电费");

        springLayout.putConstraint(SpringLayout.WEST,feesLabel,525,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,feesLabel,40,SpringLayout.NORTH,cardPanel);
        springLayout.putConstraint(SpringLayout.WEST,reasonLabel,400,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,reasonLabel,80,SpringLayout.SOUTH,feesLabel);
        springLayout.putConstraint(SpringLayout.WEST,type,80,SpringLayout.EAST,reasonLabel);
        springLayout.putConstraint(SpringLayout.NORTH,type,0,SpringLayout.NORTH,reasonLabel);
        springLayout.putConstraint(SpringLayout.WEST,feeacountLabel,0,SpringLayout.WEST,reasonLabel);
        springLayout.putConstraint(SpringLayout.NORTH,feeacountLabel,60,SpringLayout.SOUTH,reasonLabel);
        springLayout.putConstraint(SpringLayout.WEST,feeacountField,0,SpringLayout.WEST,type);
        springLayout.putConstraint(SpringLayout.NORTH,feeacountField,0,SpringLayout.NORTH,feeacountLabel);
        springLayout.putConstraint(SpringLayout.WEST,confirmBtn,525,SpringLayout.WEST,cardPanel);
        springLayout.putConstraint(SpringLayout.NORTH,confirmBtn,120,SpringLayout.SOUTH,feeacountLabel);

        //退出
        backBtn.setFont(centerFont);
        backBtn.setBackground(customColor);
        BottomPanel.add(backBtn);

        cardPanel.add(blank);
        cardPanel.add(centerPanel,"信息查询");
        cardPanel.add(feesPanel,"学杂费");

        //表格显示初始化
        getAccount("");
        ShowTableData(accounts);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
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

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputType=(String)type.getSelectedItem();
                String inputAmount=feeacountField.getText();

                //获取所有的学生账户
                IBankClientAPI iBankClientAPI=new IBankClientAPIImpl("localhost", 8888);
                String[][] ManagerConsumeAccounts= iBankClientAPI.findBankAccounts("");

                // 循环遍历每一个账户
                for (String[] row : ManagerConsumeAccounts) {
                    // 检查数组的长度是否大于等于3，以确保第三列存在
                    if (row.length >= 3) {
                        // 获取第三列的值
                        String uID = row[2];
                        bankBill bill=new bankBill(inputType,generateRandomString(20),row[0],row[2],new Date(),true,Double.parseDouble(inputAmount));
                        iBankClientAPI=new IBankClientAPIImpl("localhost", 8888);
                        bankAccount bankA=iBankClientAPI.findBankAccountById(row[2]);
                        iBankClientAPI=new IBankClientAPIImpl("localhost", 8888);
                        try {
                            iBankClientAPI.bankConsume(row[2],bill,bankA.getPaymentPwd(),true);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                }
                refreshPage();
                JOptionPane.showMessageDialog(feesPanel, "扣款成功！");

            }
        });


        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(cardPanel,BorderLayout.CENTER);
        contentPane.add(topPanel,BorderLayout.NORTH);

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
        int result= iBankClientAPI.changeLoss(accounts[clickedRow][2], null);
        if(result==-1){
            JOptionPane.showMessageDialog(centerPanel, "操作失败");
        } else if(result==1){
            JOptionPane.showMessageDialog(centerPanel, "成功挂失");
        } else if (result==2) {
            JOptionPane.showMessageDialog(centerPanel, "成功解挂");
        }

        //修改当前显示的accounts的卡片挂失的状态的显示
        accounts[clickedRow][4]=(accounts[clickedRow][4].equals("正常")?"已挂失":"正常");

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
        type.setSelectedIndex(0);
        feeacountField.setText("");
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
        new view.Bank.BankManagerUI();
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