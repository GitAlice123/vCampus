package view.Library;

import view.Global.GlobalData;
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
import java.text.SimpleDateFormat;
import java.util.Date;


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
                    /* 下面重新显示用户借书表格 */
                    LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl("localhost",8888);
                    String uID = "213213000";
                    // TODO：全局用户ID还未设置
                    RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

                    BookHold[] AllHoldBooks= new BookHold[0];
                    try {
                        AllHoldBooks = libraryClientAPI_4.getBorrowedBooks(registerReqMessage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        ShowBorrowedTableData(AllHoldBooks);
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

    class RenewBookTableCellEditorButton extends DefaultCellEditor {

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
                    try {
                        RenewBtnClicked(e);
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
    JButton ReturnToAllBookBtn;

    public LibraryUI() throws IOException {
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
        initComponents();
    }

    private void initComponents() throws IOException {
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
        this.ReturnToAllBookBtn=new JButton("显示所有书籍");//显示所有书籍按钮
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
        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost",8888);
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks=libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl("localhost",8888);
        String uID = "213213000";
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

        BookHold[] AllHoldBooks=libraryClientAPI_3.getBorrowedBooks(registerReqMessage);

        ShowBorrowedTableData(AllHoldBooks);


        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        tableChosen.setRowHeight(30);
        JTableHeader tab_headerChosen = tableChosen.getTableHeader();                    //获取表头
        tab_headerChosen.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));    //修改表头的高度
        tableChosen.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());

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
        BookPanel.add(ReturnToAllBookBtn);
        BookPanel.add(NumOfBook);
        BookPanel.add(NumOfBookOut);
        BookPanel.add(imageLabel);
        backBtn.setPreferredSize(new Dimension(100, 40));
        BookBtn.setPreferredSize(new Dimension(250, 40));
        ChosenBtn.setPreferredSize(new Dimension(250, 40));
        FindBookTex.setPreferredSize(new Dimension(150, 40));
        FindBookBtn.setPreferredSize(new Dimension(150, 40));
        ReturnToAllBookBtn.setPreferredSize(new Dimension(220, 40));
        Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
        table.setFont(centerFont);
        tableChosen.setFont(centerFont);
        backBtn.setFont(centerFont);
        BookBtn.setFont(centerFont);
        ChosenBtn.setFont(centerFont);
        FindBookTex.setFont(centerFont);
        FindBookBtn.setFont(centerFont);
        ReturnToAllBookBtn.setFont(centerFont);
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
        springLayout.putConstraint(SpringLayout.WEST, ReturnToAllBookBtn, 10, SpringLayout.EAST, FindBookBtn);
        springLayout.putConstraint(SpringLayout.NORTH, ReturnToAllBookBtn, 0, SpringLayout.NORTH, FindBookTex);
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
        ReturnToAllBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReturnToAllBookBtnClicked(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });//TODO:加按钮响应
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

    private void ShowBorrowedTableData(BookHold[] bookArray) throws IOException {
        // 把得到的书籍列表放入表格
        String[][] data;
        String[] columnNamesChosen;
        if(bookArray==null){
            int columnCount = 6;
            columnNamesChosen = new String[]{"书名", "索书号", "借阅时间", "过期时间", "还书", "续借"};
            data = new String[][]{null,null,null,null,null,null,null,null,null,null,null,
            null,null,null,null};
            modelChosen.setDataVector(data, columnNamesChosen);
            tableChosen.setModel(modelChosen);
        }
        else {
            int rowCount = bookArray.length;
            int columnCount = 6;

            columnNamesChosen = new String[]{"书名", "索书号", "借阅时间", "过期时间", "还书", "续借"};
            // 创建二维字符串数组用于存储表格数据
            data = new String[rowCount][columnCount];

            // 将书籍信息转换为对象数组并存储在data数组中
            for (int i = 0; i < rowCount; i++) {
                BookHold book = bookArray[i];
                Date dayBorrow = book.getBorrowTime();
                Date dayOutdate = book.getOutdateTime();
                SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

                data[i] = new String[]{
                        GetBookNameByISBN(book.getBookISBN()),
                        book.getBookISBN(),
                        ft.format(dayBorrow),
                        ft.format(dayOutdate),
                };
            }
            modelChosen.setDataVector(data, columnNamesChosen);
            tableChosen.setModel(modelChosen);

            ReturnBookTableCellEditorButton editor;
            ReturnBookTableCellRendererButton renderer;

            renderer = new ReturnBookTableCellRendererButton();
            editor = new ReturnBookTableCellEditorButton();

            RenewBookTableCellEditorButton renewEditor;
            RenewBookTableCellRendererButton renewRenderer;

            renewRenderer = new RenewBookTableCellRendererButton();
            renewEditor = new RenewBookTableCellEditorButton();

            tableChosen.getColumnModel().getColumn(4).setCellRenderer(renderer);
            tableChosen.getColumnModel().getColumn(4).setCellEditor(editor);

            tableChosen.getColumnModel().getColumn(5).setCellRenderer(renewRenderer);
            tableChosen.getColumnModel().getColumn(5).setCellEditor(renewEditor);

        }





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

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl("localhost", 8888);
        SearchBookNameMessage searchBookNameMessage = new SearchBookNameMessage(searchText);
        Book[] bookArray;
        try {
            bookArray = libraryClientAPI_3.getBooksBySearchBookName(searchBookNameMessage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if(bookArray==null)
        {
            JOptionPane.showMessageDialog(this,"没有该书籍库存！");
            FindBookTex.setText("");
            return;
        }
        ShowTableData(bookArray);
    }

    private void BorrowBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);

        Object data = table.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;


        // 点击显示在馆数量
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
        BookISBNMessage bookISBNMessage = new BookISBNMessage(ISBNchosen);
        Book bookGet = libraryClientAPI.getBookByISBN(bookISBNMessage);
        NumOfBook.setText("在馆数量：" + Integer.toString(bookGet.freeNum)); // 使用 Integer.toString

        Date currentDate = new Date();
        // 实施借书操作

        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl("localhost", 8888);
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_4.getNextOPRId(uniqueMessage);


        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl("localhost", 8888);
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, "213213000",
                ISBNchosen,currentDate,"BOR",null);
        // TODO:全局用户ID还未设置，现在是假的
        Boolean flag = libraryClientAPI_1.BorrowBook(bookOperationRecord);
        if (flag) {
            JOptionPane.showMessageDialog(this,"借阅成功！");
        }
        else{
            JOptionPane.showMessageDialog(this,"无法借阅！");
            return;
        }
        LibraryClientAPI libraryClientAPI_5 = new LibraryClientAPIImpl("localhost",8888);
        String uID = "213213000";
        // TODO：全局用户ID还未设置
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

        BookHold[] AllHoldBooks= new BookHold[0];
        try {
            AllHoldBooks = libraryClientAPI_5.getBorrowedBooks(registerReqMessage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            ShowBorrowedTableData(AllHoldBooks);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        // 点击显示在馆数量
        LibraryClientAPI libraryClientAPI5 = new LibraryClientAPIImpl("localhost", 8888);
        BookISBNMessage bookISBNMessage2 = new BookISBNMessage(ISBNchosen);
        Book bookGet3 = libraryClientAPI5.getBookByISBN(bookISBNMessage2);
        NumOfBook.setText("在馆数量：" + Integer.toString(bookGet3.freeNum)); // 使用 Integer.toString

    }

    private void BackBtnClicked(ActionEvent e) throws IOException {
        System.exit(0);
    }

    private void ReturnBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引

        // ISBN号在第1列
        Object data = tableChosen.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;
        System.out.println("Going to return "+ISBNchosen);

        Date currentDate = new Date();
        // 实施借书操作
        // TODO:全局用户ID还未设置，现在是假的

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl("localhost", 8888);
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_3.getNextOPRId(uniqueMessage);

        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost", 8888);
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, "213213000",
                ISBNchosen,currentDate,"RET",null);
        Boolean flag = libraryClientAPI_2.ReturnBook(bookOperationRecord);

        if (flag) {
            System.out.println("Return Successfully");
        }

        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl("localhost",8888);
        String uID = "213213000";
        // TODO：全局用户ID还未设置
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

        BookHold[] AllHoldBooks= new BookHold[0];
        try {
            AllHoldBooks = libraryClientAPI_4.getBorrowedBooks(registerReqMessage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            ShowBorrowedTableData(AllHoldBooks);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String GetBookNameByISBN(String ISBN)
            throws IOException{
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost",8888);
        BookISBNMessage bookISBNMessage = new BookISBNMessage(ISBN);

        Book book = libraryClientAPI.getBookByISBN(bookISBNMessage);
        return book.getBookName();
    }

    private void ReturnToAllBookBtnClicked(ActionEvent e)
            throws IOException{
        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost",8888);
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks=libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);
    }

    private void RenewBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);


        // 点击显示在馆数量
        Object data = table.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;


        Date currentDate = new Date();
        // 实施续借操作
        // TODO:全局用户ID还未设置，现在是假的
        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl("localhost", 8888);
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_4.getNextOPRId(uniqueMessage);


        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl("localhost", 8888);
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, "213213000",
                ISBNchosen,currentDate,"REN",null);
        Boolean flag = libraryClientAPI_1.renewBook(bookOperationRecord);
        if (flag) {
            System.out.println("Renew Successfully");
        }

        LibraryClientAPI libraryClientAPI_5 = new LibraryClientAPIImpl("localhost",8888);
        String uID = "213213000";
        // TODO：全局用户ID还未设置
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

        BookHold[] AllHoldBooks= new BookHold[0];
        try {
            AllHoldBooks = libraryClientAPI_5.getBorrowedBooks(registerReqMessage);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            ShowBorrowedTableData(AllHoldBooks);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
    public static void main(String[] args) throws IOException {
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