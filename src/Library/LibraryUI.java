package view.Library;



import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LibraryUI extends JFrame {
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelChosen = new DefaultTableModel();
    JTable table = new JTable();
    String[][] data = {//书籍列表，表格数据均传入该数组
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"}

    };
    JTable tableChosen = new JTable();
    String[][] dataChosen = {//已借阅书籍列表
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"},
            {"1","1","1","1","1","1"}


    };
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel BookPanel=new JPanel(springLayout);//学生查看课程列表的面板
    JPanel ChosenPanel=new JPanel(springLayout);//学生查看已选课程的面板
    JButton BookBtn=new JButton("查看书籍列表");

    JButton ChosenBtn=new JButton("查看已借阅书籍");

    public JTextField getFindBookTex() {
        return FindBookTex;
    }

    JTextField FindBookTex=new JTextField();//查找图书的输入框
    JButton FindBookBtn=new JButton("查找");//查找按钮
    JLabel NumOfBook=new JLabel("在馆数量:");

    public void setBookNum(String bookNum) {
        BookNum = bookNum;
    }

    String BookNum;
    JLabel NumOfBookOut=new JLabel(BookNum);//此处需要添加，显示所查找书籍的在馆数量，利用上述SET函数写入

    JButton backBtn=new JButton("退出");//同上

    public LibraryUI(){
        super("图书馆系统");
        JLabel imageLabel = new JLabel();
        try {
            // 加载图片
            int newWidth = 120;  // 新的宽度
            int newHeight = 120; // 新的高度

            Image pkqIm = ImageIO.read(new File("Images/pkq8.jpeg"));  // 请将 "image.png" 替换为实际的图片路径

            Image scaledImage = pkqIm.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));


        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] columnNames ={"书名","索书号","作者","类型","出版社","位置","借阅"};//索书号是一本书一个
        String[] columnNamesChosen ={"书名","索书号","借阅时间","过期时间","还书","续借"};
        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(6).setCellRenderer(new BorrowBookTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new BorrowBookTableCellEditorButton());
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        modelChosen.setDataVector(dataChosen, columnNamesChosen);
        tableChosen.setModel(modelChosen);

        tableChosen.getColumnModel().getColumn(4).setCellRenderer(new ReturnBookTableCellRendererButton());
        tableChosen.getColumnModel().getColumn(4).setCellEditor(new ReturnBookTableCellEditorButton());
        tableChosen.getColumnModel().getColumn(5).setCellRenderer(new RenewBookTableCellRendererButton());
        tableChosen.getColumnModel().getColumn(5).setCellEditor(new RenewBookTableCellEditorButton());
        tableChosen.setRowHeight(30);
        JTableHeader tab_headerChosen = tableChosen.getTableHeader();					//获取表头
        tab_headerChosen.setFont(new Font("楷体",Font.PLAIN,25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));	//修改表头的高度
        tableChosen.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JScrollPane scrollPaneChosen = new JScrollPane(tableChosen);
        scrollPaneChosen.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        //loginHandler=new logInHandler(this);

        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(TopPanel, BorderLayout.NORTH);
        contentPane.add(BottomPanel, BorderLayout.SOUTH);
        contentPane.add(panel1, BorderLayout.CENTER);
        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(BookPanel, "BookPanel");
        panel1.add(ChosenPanel, "ChosenPanel");
        BookPanel.add(FindBookTex);
        BookPanel.add(FindBookBtn);
        BookPanel.add(NumOfBook);
        BookPanel.add(NumOfBookOut);
        BookPanel.add(imageLabel);
        backBtn.setPreferredSize(new Dimension(100, 40));
        BookBtn.setPreferredSize(new Dimension(250, 40));
        ChosenBtn.setPreferredSize(new Dimension(250, 40));
        FindBookTex.setPreferredSize(new Dimension(150, 40));
        FindBookBtn.setPreferredSize(new Dimension(150, 40));
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        table.setFont(centerFont);
        tableChosen.setFont(centerFont);
        backBtn.setFont(centerFont);
        BookBtn.setFont(centerFont);
        ChosenBtn.setFont(centerFont);
        FindBookTex.setFont(centerFont);
        FindBookBtn.setFont(centerFont);
        NumOfBook.setFont(centerFont);
        TopPanel.add(BookBtn);
        TopPanel.add(ChosenBtn);

        BottomPanel.add(backBtn);
        BookPanel.add(scrollPane);
        ChosenPanel.add(scrollPaneChosen);
        BookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"BookPanel");
            }
        });
        ChosenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"ChosenPanel");
            }
        });
        FindBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //添加点击查找以后，表格中显示所查书名或索书号的书
            }
        });

        springLayout.putConstraint(SpringLayout.WEST, FindBookTex, 130, SpringLayout.WEST, BookPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookTex, 30, SpringLayout.NORTH, BookPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBook, -200, SpringLayout.EAST, BookPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBook, 40, SpringLayout.NORTH, BookPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBookOut, -120, SpringLayout.EAST, BookPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookOut, 40, SpringLayout.NORTH, BookPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindBookBtn, 10, SpringLayout.EAST, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookBtn, 0, SpringLayout.NORTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 50, SpringLayout.SOUTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, panel1);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPaneChosen, 100, SpringLayout.NORTH, panel1);
        springLayout.putConstraint(SpringLayout.WEST, scrollPaneChosen, 100, SpringLayout.WEST, panel1);




        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

    private class TableBackgroundColorRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // 设置单元格背景颜色
            if (row % 2 == 0) {
                Color customColor = new Color(255, 255, 224);
                cellComponent.setBackground(customColor);
            } else {
                Color customColor2 = new Color(255, 250, 205);
                cellComponent.setBackground(customColor2);
            }

            return cellComponent;
        }
    }

    public static void main(String[] args){
        try {
            // 设置外观为Windows外观
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            UIManager.put("nimbusBase", new Color(255, 255, 50)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(255, 255, 210)); // 按钮
            UIManager.put("control", new Color(248, 248, 230)); // 背景



        } catch (Exception e) {
            e.printStackTrace();
        }
        new LibraryUI();
    }
}
class BorrowBookTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton("借阅");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        button.setFont(centerFont);
        return button;
    }

}
class BorrowBookTableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

    private JButton btn;
    private int clickedRow;
    public BorrowBookTableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("借阅");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        btn.setFont(centerFont);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);
                //此处要操作该学生借了该行的书，添加到数据库和学生已经借阅的表格里，行数由clickedRow给出，从0开始

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
class ReturnBookTableCellRendererButton implements TableCellRenderer {



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton("还书");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        button.setFont(centerFont);
        return button;
    }

}
class ReturnBookTableCellEditorButton extends DefaultCellEditor{

    private JButton btn;
    private int clickedRow;
    public ReturnBookTableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("还书");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        btn.setFont(centerFont);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);

                //此处要添加还书操作，将该行对应的书从该学生已借的书的数据库和表格中删除
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
class RenewBookTableCellRendererButton implements TableCellRenderer {



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton("续借");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        button.setFont(centerFont);
        return button;
    }

}
class RenewBookTableCellEditorButton extends DefaultCellEditor{

    private JButton btn;
    private int clickedRow;
    public RenewBookTableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("续借");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        btn.setFont(centerFont);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);

                //此处要添加续借操作，将该行对应的书在该学生的已借书的表格和数据库中将过期时间后延
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