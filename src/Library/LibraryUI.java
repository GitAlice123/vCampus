package view.Library;

import view.Global.GlobalData;
import view.Global.SummaryStudentTeacherUI;
import view.connect.*;
import view.message.*;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;import java.awt.image.BufferedImage;


/**
 * 学生和老师看到的图书馆界面
 */
public class LibraryUI extends JFrame {
    /**
     * 借阅按钮渲染器
     */
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

    /**
     * 借阅按钮编辑器
     */
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

    /**
     * 还书按钮渲染器
     */
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

    /**
     * 还书按钮编辑器
     */
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
                    LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
                    String uID = GlobalData.getUID();
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

    /**
     * 续借按钮渲染器
     */
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

    /**
     * 自定义表格单元格渲染器，用于设置选中行和非选中行的外观。
     */
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            /**
             * 获取表格单元格的渲染组件。
             *
             * @param table      表格
             * @param value      单元格值
             * @param isSelected 单元格是否选中
             * @param hasFocus   单元格是否具有焦点
             * @param row        行索引
             * @param column     列索引
             * @return 渲染组件
             */
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                // 设置选中行的外观
                component.setBackground(table.getBackground()); // 设置选中行的背景颜色
                component.setForeground(Color.BLACK); // 设置选中行的文字颜色
            } else {
                // 设置非选中行的外观
                component.setBackground(table.getBackground()); // 恢复默认的背景颜色
                component.setForeground(table.getForeground()); // 恢复默认的文字颜色
            }
            return component;
        }
    };

    /**
     * 续借按钮编辑器
     */
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

    /**
     * 创建一个图书馆老师、学生用户界面。
     *
     * @throws IOException 如果发生输入/输出错误时抛出该异常
     */
    public LibraryUI() throws IOException {
        try {
            // 设置外观为Windows外观
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            UIManager.put("nimbusBase", new Color(173, 230, 230)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(173, 216, 230)); // 按钮
            UIManager.put("control", new Color(240, 248, 255)); // 背景


        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
    }

    /**
     * 初始化组件
     * @throws IOException
     */
    private void initComponents() throws IOException {
        this.springLayout = new SpringLayout();
        this.model = new DefaultTableModel();
        this.modelChosen = new DefaultTableModel();
        this.table = new JTable();;
        this.tableChosen = new JTable();
        this.TopPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // 加载原始尺寸的背景图片
                ImageIcon originalImageIcon = new ImageIcon("Images/topPicture.png");
                Image originalImage = originalImageIcon.getImage();

                // 创建与面板尺寸相同的缓冲图像
                BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedImage.createGraphics();

                // 设置透明度
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
                g2d.setComposite(alphaComposite);

                // 绘制背景图片到缓冲图像
                g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

                // 绘制缓冲图像到面板
                g.drawImage(bufferedImage, 0, 0, null);

                g2d.dispose();
            }
        };//顶部放置按钮的面板
        this.BottomPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // 加载原始尺寸的背景图片
                ImageIcon originalImageIcon = new ImageIcon("Images/topPicture.png");
                Image originalImage = originalImageIcon.getImage();

                // 创建与面板尺寸相同的缓冲图像
                BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedImage.createGraphics();

                // 设置透明度
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
                g2d.setComposite(alphaComposite);

                // 绘制背景图片到缓冲图像
                g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

                // 绘制缓冲图像到面板
                g.drawImage(bufferedImage, 0, 0, null);

                g2d.dispose();
            }
        };//底部放置按钮的面板
        this.panel1 = new JPanel();//中间卡片布局的面板
        this.BookPanel = new JPanel(springLayout){
            /**
             * 绘制面板的组件。
             *
             * @param g 绘图对象
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // 加载原始尺寸的背景图片
                ImageIcon originalImageIcon = new ImageIcon("Images/BJ.jpg");
                Image originalImage = originalImageIcon.getImage();

                // 创建与面板尺寸相同的缓冲图像
                BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedImage.createGraphics();

                // 设置透明度
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
                g2d.setComposite(alphaComposite);

                // 绘制背景图片到缓冲图像
                g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

                // 绘制缓冲图像到面板
                g.drawImage(bufferedImage, 0, 0, null);

                g2d.dispose();
            }
        };//学生查看课程列表的面板
        this.ChosenPanel = new JPanel(springLayout){
            /**
             * 绘制面板的组件。
             *
             * @param g 绘图对象
             */
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // 加载原始尺寸的背景图片
                ImageIcon originalImageIcon = new ImageIcon("Images/BJ.jpg");
                Image originalImage = originalImageIcon.getImage();

                // 创建与面板尺寸相同的缓冲图像
                BufferedImage bufferedImage = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = bufferedImage.createGraphics();

                // 设置透明度
                AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
                g2d.setComposite(alphaComposite);

                // 绘制背景图片到缓冲图像
                g2d.drawImage(originalImage, 0, 0, getWidth(), getHeight(), this);

                // 绘制缓冲图像到面板
                g.drawImage(bufferedImage, 0, 0, null);

                g2d.dispose();
            }
        };;//学生查看已选课程的面板
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

        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks=libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String uID = GlobalData.getUID();
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(uID);

        BookHold[] AllHoldBooks=libraryClientAPI_3.getBorrowedBooks(registerReqMessage);

        ShowBorrowedTableData(AllHoldBooks);



        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        table.setOpaque(false);
        table.setDefaultRenderer(Object.class, renderer);
        //table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        tableChosen.setDefaultRenderer(Object.class, renderer);
        tableChosen.setRowHeight(30);
        JTableHeader tab_headerChosen = tableChosen.getTableHeader();                    //获取表头
        tab_headerChosen.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));    //修改表头的高度
        //tableChosen.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());

        tableChosen.setOpaque(false);
        JScrollPane scrollPaneChosen = new JScrollPane(tableChosen);
        scrollPaneChosen.setOpaque(false);
        scrollPaneChosen.getViewport().setBackground(new Color(255,255,255,150));
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
        /**
         * 借书界面按钮监听函数
         */
        BookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1, "BookPanel");
            }
        });
        /**
         * 查看已借阅书籍监听函数
         */
        ChosenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1, "ChosenPanel");
            }
        });
        /**
         * 查询书籍按钮监听函数
         */
        FindBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchBtnClicked();
            }
        });
        /**
         * 返回所有书籍列表监听函数
         */
        ReturnToAllBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ReturnToAllBookBtnClicked(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * 返回监听函数
         */
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

    /**
     * 展示所有借阅图书
     * @param bookArray 借阅记录
     * @throws IOException
     */
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

    /**
     * 展示所有图书
     * @param bookArray 所有书籍
     */
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

    /**
     * 查询按钮点击后
     */
    private void SearchBtnClicked() {
        System.out.println("Search Pressed");
        String searchText = FindBookTex.getText(); // 获取文本框内容作为搜索文本

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
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

    /**
     * 借书按钮点击后
     * @param e
     * @throws IOException
     */
    private void BorrowBtnClicked(ActionEvent e) throws IOException {
        //System.out.println("按钮事件触发----");
        JButton clickedButton = (JButton) e.getSource();
        int clickedRow;
        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
        System.out.println("点击的行索引：" + clickedRow);

        Object data = table.getValueAt(clickedRow, 1);
        String ISBNchosen = (String) data;


        // 点击显示在馆数量
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        BookISBNMessage bookISBNMessage = new BookISBNMessage(ISBNchosen);
        Book bookGet = libraryClientAPI.getBookByISBN(bookISBNMessage);
        NumOfBook.setText("在馆数量：" + Integer.toString(bookGet.freeNum)); // 使用 Integer.toString

        Date currentDate = new Date();
        // 实施借书操作

        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_4.getNextOPRId(uniqueMessage);


        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, GlobalData.getUID(),
                ISBNchosen,currentDate,"BOR",null);
        Boolean flag = libraryClientAPI_1.BorrowBook(bookOperationRecord);
        if (flag) {
            JOptionPane.showMessageDialog(this,"借阅成功！");
        }
        else{
            JOptionPane.showMessageDialog(this,"无法借阅！");
            return;
        }
        LibraryClientAPI libraryClientAPI_5 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String uID = GlobalData.getUID();
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
        LibraryClientAPI libraryClientAPI5 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        BookISBNMessage bookISBNMessage2 = new BookISBNMessage(ISBNchosen);
        Book bookGet3 = libraryClientAPI5.getBookByISBN(bookISBNMessage2);
        NumOfBook.setText("在馆数量：" + Integer.toString(bookGet3.freeNum)); // 使用 Integer.toString

    }

    /**
     * 返回按钮点击后
     * @param e
     * @throws IOException
     */
    private void BackBtnClicked(ActionEvent e) throws IOException {
        this.dispose();
        new SummaryStudentTeacherUI();
    }

    /**
     * 还书按钮点击后
     * @param e
     * @throws IOException
     */
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

        LibraryClientAPI libraryClientAPI_3 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_3.getNextOPRId(uniqueMessage);

        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, GlobalData.getUID(),
                ISBNchosen,currentDate,"RET",null);
        Boolean flag = libraryClientAPI_2.ReturnBook(bookOperationRecord);

        if (flag) {
            System.out.println("Return Successfully");
        }

        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String uID = GlobalData.getUID();
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

    /**
     * 用ISBN找书
     * @param ISBN
     * @return
     * @throws IOException
     */
    private String GetBookNameByISBN(String ISBN)
            throws IOException{
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        BookISBNMessage bookISBNMessage = new BookISBNMessage(ISBN);

        Book book = libraryClientAPI.getBookByISBN(bookISBNMessage);
        return book.getBookName();
    }

    /**
     * 回到所有书籍列表响应函数
     * @param e
     * @throws IOException
     */
    private void ReturnToAllBookBtnClicked(ActionEvent e)
            throws IOException{
        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks=libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);
    }

    /**
     * 续借按钮点击后
     * @param e
     * @throws IOException
     */
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
        LibraryClientAPI libraryClientAPI_4 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        String nextOPRid = libraryClientAPI_4.getNextOPRId(uniqueMessage);


        LibraryClientAPI libraryClientAPI_1 = new LibraryClientAPIImpl(GlobalData.getIpAddress(), Integer.parseInt(GlobalData.getPortName()));
        BookOperationRecord bookOperationRecord = new BookOperationRecord(nextOPRid, GlobalData.getUID(),
                ISBNchosen,currentDate,"REN",null);
        Boolean flag = libraryClientAPI_1.renewBook(bookOperationRecord);
        if (flag) {
            System.out.println("Renew Successfully");
        }

        LibraryClientAPI libraryClientAPI_5 = new LibraryClientAPIImpl(GlobalData.getIpAddress(),Integer.parseInt(GlobalData.getPortName()));
        String uID = GlobalData.getUID();
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
            UIManager.put("nimbusBase", new Color(173, 230, 230)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(173, 216, 230)); // 按钮
            UIManager.put("control", new Color(240, 248, 255)); // 背景


        } catch (Exception e) {
            e.printStackTrace();
        }

        new LibraryUI();
    }
}