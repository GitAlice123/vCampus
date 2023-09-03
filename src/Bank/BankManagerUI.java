package view.Bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import view.Global.SummaryUI;

public class BankManagerUI extends JFrame {
    JButton searchBtn = new JButton("查询");
    JTextField searchField = new JTextField();
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();

    JButton backBtn=new JButton("退出");
    JPanel BottomPanel=new JPanel();
    JPanel centerPanel=new JPanel();
    public BankManagerUI() {
        super("银行");
        Font centerFont=new Font("楷体",Font.PLAIN,30);//设置中间组件的文字大小、字体

        // TODO  图片路径问题
//        URL resource =this.getClass().getClassLoader().getResource(".\\SEU.png");
//        Image image=new ImageIcon(resource).getImage();
//        setIconImage(image);

        //主界面
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
        model.setDataVector(data, header);
        table.setModel(model);
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new TableCellEditorButton());
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度

        searchBtn.setFont(centerFont);
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
        BottomPanel.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });


        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }


    /*public JButton getbutton(){

        return button;
    }*/
    class TableCellRendererButton implements TableCellRenderer {

        //判断user银行卡的状态，若正常，则初始化为“挂失”，若已挂失，则初始化为“解挂”
        JButton button = new JButton("挂失");
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            button.setFont(new Font("楷体",Font.PLAIN,25));
            return button;
        }

    }

    class TableCellEditorButton extends DefaultCellEditor {
        JButton btn=new JButton();


        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);

            //若卡的状态被挂失，显示“已挂失”，若被解挂，显示“已解挂”
            btn = new JButton("已挂失");


            btn.setFont(new Font("楷体",Font.PLAIN,25));
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("已挂失");


                }
            });

        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            return btn;
        }
    }

    public static void main(String[] args)
    {
        new BankManagerUI();
    }
}