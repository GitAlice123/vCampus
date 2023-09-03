package view.Library;

import view.connect.*;
import view.message.*;


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
    static class BorrowBookTableCellRendererButton implements TableCellRenderer {
        public BorrowBookTableCellRendererButton() {
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("借阅");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }

    class BorrowBookTableCellEditorButton extends DefaultCellEditor {//查看班级界面辅助类，按钮事件触发在此类中
        private JButton btn;
        private int clickedRow;

        public BorrowBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("借阅");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        BorrowBtnClicked(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
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

    static class ReturnBookTableCellRendererButton implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("还书");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }

    class ReturnBookTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public ReturnBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("还书");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();
                    try {
                        ReturnBtnClicked(e);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    // TODO:此处要添加还书操作，将该行对应的书从该学生已借的书的数据库和表格中删除

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

    static class RenewBookTableCellRendererButton implements TableCellRenderer {


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("续借");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

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
                    Color customColor = new Color(255, 255, 224);
                    cellComponent.setBackground(customColor);
                } else {
                    Color customColor2 = new Color(255, 250, 205);
                    cellComponent.setBackground(customColor2);
                }
            }
            return cellComponent;
        }
    }

    static class RenewBookTableCellEditorButton extends DefaultCellEditor {

        private JButton btn;
        private int clickedRow;

        public RenewBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("续借");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);

                    // TODO:此处要添加续借操作，将该行对应的书在该学生的已借书的表格和数据库中将过期时间后延
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

    SpringLayout springLayout;
    DefaultTableModel model;
    DefaultTableModel modelChosen;
    JTable table;
    JTable tableChosen;
    JPanel TopPanel;
    JPanel BottomPanel;
    JPanel panel1;
    JPanel BookPanel;
    JPanel ChosenPanel;
    JButton BookBtn;
    JButton ChosenBtn;
    JTextField FindBookTex;
    JLabel NumOfBook;
    String BookNum;
    JLabel NumOfBookOut;
    JButton backBtn;
    JLabel imageLabel;
    JButton FindBookBtn;

    public LibraryUI() {
        initComponents();
    }

    private void initComponents() {
        this.springLayout = new SpringLayout();
        this.model = new DefaultTableModel();
        this.modelChosen = new DefaultTableModel();
        this.table = new JTable();
        this.tableChosen = new JTable();
        this.TopPanel = new JPanel();//顶部放置按钮的面板
        this.BottomPanel = new JPanel();//底部放置按钮的面板
        this.panel1 = new JPanel();//中间卡片布局的面板
        this.BookPanel = new JPanel(springLayout);//学生查看课程列表的面板
        this.ChosenPanel = new JPanel(springLayout);//学生查看已选课程的面板
        this.BookBtn = new JButton("查看书籍列表");
        this.ChosenBtn = new JButton("查看已借阅书籍");
        this.FindBookTex = new JTextField();//查找图书的输入框
        this.FindBookBtn = new JButton("查找");//查找按钮
        this.NumOfBook = new JLabel("在馆数量:");
        this.BookNum = null;
        this.NumOfBookOut = new JLabel(BookNum);
        this.backBtn = new JButton("退出");//同上
        this.imageLabel = new JLabel();
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
        String[][] data = {//书籍列表，表格数据均传入该数组
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1", "1"}

        };
        String[][] dataChosen = {//已借阅书籍列表
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"},
                {"1", "1", "1", "1", "1", "1"}
        };
        // TODO:刚打开表格应显示所有馆藏书籍
        // TODO:刚打开借阅信息表格应显示所有借阅书籍信息
        String[] columnNames = {"书名", "索书号", "作者", "类型", "出版社", "位置", "借阅"};//索书号是一本书一个
        String[] columnNamesChosen = {"书名", "索书号", "借阅时间", "过期时间", "还书", "续借"};
        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(6).setCellRenderer(new BorrowBookTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new BorrowBookTableCellEditorButton());
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        modelChosen.setDataVector(dataChosen, columnNamesChosen);
        tableChosen.setModel(modelChosen);

        tableChosen.getColumnModel().getColumn(4).setCellRenderer(new ReturnBookTableCellRendererButton());
        tableChosen.getColumnModel().getColumn(4).setCellEditor(new ReturnBookTableCellEditorButton());
        tableChosen.getColumnModel().getColumn(5).setCellRenderer(new RenewBookTableCellRendererButton());
        tableChosen.getColumnModel().getColumn(5).setCellEditor(new RenewBookTableCellEditorButton());
        tableChosen.setRowHeight(30);
        JTableHeader tab_headerChosen = tableChosen.getTableHeader();                    //获取表头
        tab_headerChosen.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));    //修改表头的高度
        tableChosen.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JScrollPane scrollPaneChosen = new JScrollPane(tableChosen);
        scrollPaneChosen.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        Container contentPane = getContentPane();//获取控制面板
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
        Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
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


        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));


        /* 下面是监听函数 */
        BookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1, "BookPanel");
            }
        });
        ChosenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1, "ChosenPanel");
            }
        });
        FindBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchBtnClicked();
            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BackBtnClicked(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    private void ShowTableData(Book[] bookArray) {
        // 把得到的书籍列表放入表格
        int rowCount = bookArray.length;
        int columnCount = 7;

        String[] columnNames = {"书名", "索书号", "作者", "类型", "出版社", "位置", "借阅"};
        // 创建二维字符串数组用于存储表格数据
        String[][] data = new String[rowCount][columnCount];

        // 将书籍信息转换为对象数组并存储在data数组中
        for (int i = 0; i < rowCount; i++) {
            Book book = bookArray[i];
            data[i] = new String[]{
                    book.getBookName(),
                    book.getBookISBN(),
                    book.getAuthor(),
                    book.getBookType(),
                    book.getPublisher(),
                    book.getBookPos()
            };
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // 设置最后一列的单元格渲染器为按钮渲染器
        table.setModel(model);
        // 创建自定义的渲染器和编辑器，并将表格对象传递给它们
        BorrowBookTableCellRendererButton renderer;
        renderer = new BorrowBookTableCellRendererButton();
        BorrowBookTableCellEditorButton editor;
        editor = new BorrowBookTableCellEditorButton();
        table.getColumnModel().getColumn(6).setCellRenderer(renderer);
        table.getColumnModel().getColumn(6).setCellEditor(editor);
    }

    private void SearchBtnClicked() {
        System.out.println("Search Pressed");
        String searchText = FindBookTex.getText(); // 获取文本框内容作为搜索文本

        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
        SearchBookNameMessage searchBookNameMessage = new SearchBookNameMessage(searchText);
        Book[] bookArray;
        try {
            bookArray = libraryClientAPI.getBooksBySearchBookName(searchBookNameMessage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        ShowTableData(bookArray);
    }

    private void BorrowBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);
        // ISBN号在第1列
        Object data = table.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
        BookISBNMessage bookISBNMessage = new BookISBNMessage(ISBNchosen);
        Book bookGet = libraryClientAPI.getBookByISBN(bookISBNMessage);
        NumOfBook.setText("在馆数量：" + Integer.toString(bookGet.freeNum)); // 使用 Integer.toString
        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl("localhost", 8888);
        BookISBNMessage bookISBNMessage_1 = new BookISBNMessage(ISBNchosen);
        Boolean flag = libraryClientAPI_1.BorrowBook(bookISBNMessage_1);
        if (flag) {
            System.out.println("Borrow Successfully");
        }
        // TODO:这里可以运行但是无法写入数据库

    }

    private void BackBtnClicked(ActionEvent e) throws IOException {
        System.exit(0);
    }

    private void ReturnBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);
        // ISBN号在第1列
        Object data = table.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;

        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl("localhost", 8888);
        BookISBNMessage bookISBNMessage_1 = new BookISBNMessage(ISBNchosen);
        Boolean flag = libraryClientAPI_1.ReturnBook(bookISBNMessage_1);
        if (flag) {
            System.out.println("Return Successfully");
        }
    }

    public static void main(String[] args) {
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