package view.Library;

import view.connect.LibraryClientAPI;
import view.connect.LibraryClientAPIImpl;
import view.message.BookISBNMessage;
import view.message.RegisterReqMessage;
import view.message.SearchBookNameMessage;
import view.message.UniqueMessage;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;

import javax.swing.table.TableCellRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;

import javax.swing.table.JTableHeader;


public class LibraryAdminUI extends JFrame {
    private int changeBtnRow;

    class DeleteBookTableCellRendererButton implements TableCellRenderer {


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("删除");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            button.setBackground(customColor);
            return button;
        }

    }

    class DeleteBookTableCellEditorButton extends DefaultCellEditor {

        private JButton btn;
        private int clickedRow;

        public DeleteBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("删除");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            btn.setBackground(customColor);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);

                    try {
                        deleteBtnClicked(e);
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

    class ChangeBookTableCellRendererButton implements TableCellRenderer {


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("修改");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            button.setBackground(customColor);
            return button;
        }

    }

    class ChangeBookTableCellEditorButton extends DefaultCellEditor {

        private JButton btn;

        public ChangeBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。

            this.setClickCountToStart(1);
            btn = new JButton("修改");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            btn.setBackground(customColor);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    changeBtnRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + changeBtnRow);

                    ChangeBooksUI changeBooksUI = new ChangeBooksUI();
                    changeBooksUI.setVisible(true);
                }
            });


        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            changeBtnRow = row;
            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性

            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }


    }

    /* 初始化内容*/
    SpringLayout springLayout;
    DefaultTableModel model;
    DefaultTableModel modelFind;

    JTable table;

    JTable tableFindStuBorrowed;
    //下面是表格初始化的两个String数组
    String[][] data = {//书籍列表，表格数据均传入该数组
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"},
            {"1", "1", "1", "1", "1", "1", "1", "1"}
    };

    String[][] dataFindStuBorrowed = {//已借阅书籍列表
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
    JPanel TopPanel;
    JPanel BottomPanel;
    JPanel panel1;
    JPanel BooksPanel;
    JPanel ReportPanel;

    JPanel FindStuBorrowedPanel;
    JButton BooksBtn;

    JButton ReportBtn;
    JButton FindStuBorrowedTopBtn;
    JButton AddBooksBtn;

    JTextField FindBookTex;
    JButton FindBookBtn;
    JTextField FindStuBorrowedTex;
    JButton FindStuBorrowedBtn;
    JLabel NumOfAllBooksLabel;
    JLabel NumOfBookInTheLibraryLabel;
    JLabel NumOfBorrowedBooksLabel;
    JLabel NumOfReadersLabel;
    String NumOfAllBooks;
    String NumOfBookInTheLibrary;
    String NumOfBorrowedBooks;
    String NumOfReaders;
    JLabel NumOfAllBooksLabelOut;
    JLabel NumOfBookInTheLibraryLabelOut;
    JLabel NumOfBorrowedBooksLabelOut;
    JLabel NumOfReadersLabelOut;
    String BookNum;
    JLabel NumOfBookOut;
    JButton backBtn;

    public LibraryAdminUI() throws IOException {
        initComponent();
    }

    private void initComponent() throws IOException {

        /* 初始化内容*/
        springLayout = new SpringLayout();
        model = new DefaultTableModel();
        modelFind = new DefaultTableModel();

        table = new JTable();

        tableFindStuBorrowed = new JTable();
        /* 界面布局内容 */
        // super("图书馆系统");
        TopPanel = new JPanel();//顶部放置按钮的面板
        BottomPanel = new JPanel();//底部放置按钮的面板
        panel1 = new JPanel();//中间卡片布局的面板
        BooksPanel = new JPanel(springLayout);
        ReportPanel = new JPanel(springLayout);

        FindStuBorrowedPanel = new JPanel(springLayout);
        BooksBtn = new JButton("查看书籍列表");

        ReportBtn = new JButton("查看报告");
        FindStuBorrowedTopBtn = new JButton("学生记录查询");
        AddBooksBtn = new JButton("增加书籍");

        FindBookTex = new JTextField();//查找图书的输入框
        FindBookBtn = new JButton("查找");//查找按钮
        FindStuBorrowedTex = new JTextField();//查找学生借书记录输入框
        FindStuBorrowedBtn = new JButton("查找");//查找按钮

        NumOfAllBooksLabel = new JLabel("馆藏图书数量");
        NumOfBookInTheLibraryLabel = new JLabel("在馆图书数量");
        NumOfBorrowedBooksLabel = new JLabel("借出图书数量");
        NumOfReadersLabel = new JLabel("读者数量");

        int totalNum = 0;
        int freeNum = 0;
        int borrowedNum = 0;
        NumOfAllBooksLabelOut = new JLabel(Integer.toString(totalNum));
        NumOfBookInTheLibraryLabelOut = new JLabel(Integer.toString(freeNum));
        NumOfBorrowedBooksLabelOut = new JLabel(Integer.toString(borrowedNum));


        NumOfReadersLabelOut = new JLabel("当前还未知");
        NumOfBookOut = new JLabel(BookNum);
        backBtn = new JButton("退出");

// 折叠的展示图片的，和实现无关
        JLabel imageLabel = new JLabel();
        try {
            // 加载图片
            int newWidth = 120;  // 新的宽度
            int newHeight = 120; // 新的高度

            Image pkqIm = ImageIO.read(new File("Images/pkq4.jpeg"));  // 请将 "image.png" 替换为实际的图片路径

            Image scaledImage = pkqIm.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));


        } catch (IOException e) {
            e.printStackTrace();
        }


        // 初始显示所有馆藏书籍
        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost", 8888);
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks = libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);
        // 结束

        // 以下折叠的是表格表头样式信息
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());


        String[] FindStuBorrowedColumnNames = {"操作序列号", "学号", "索书号", "操作时间", "操作类型", "备注"};//索书号是一本书一个

        modelFind.setDataVector(dataFindStuBorrowed, FindStuBorrowedColumnNames);
        tableFindStuBorrowed.setModel(modelFind);

        tableFindStuBorrowed.setRowHeight(30);
        JTableHeader tab_headerFindStuBorrowed = tableFindStuBorrowed.getTableHeader();                    //获取表头
        tab_headerFindStuBorrowed.setFont(new Font("楷体", Font.PLAIN, 25));
        tab_headerFindStuBorrowed.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度


        JScrollPane scrollPaneFindStuBorrowed = new JScrollPane(tableFindStuBorrowed);
        scrollPaneFindStuBorrowed.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        tableFindStuBorrowed.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());

        Container contentPane = getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(TopPanel, BorderLayout.NORTH);
        contentPane.add(BottomPanel, BorderLayout.SOUTH);
        contentPane.add(panel1, BorderLayout.CENTER);
        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(BooksPanel, "BooksPanel");
        panel1.add(ReportPanel, "ReportPanel");
        panel1.add(FindStuBorrowedPanel, "FindStuBorrowedPanel");
        BooksPanel.add(FindBookTex);
        BooksPanel.add(FindBookBtn);
//        BooksPanel.add(NumOfBook);
        BooksPanel.add(NumOfBookOut);
        BooksPanel.add(AddBooksBtn);
        BooksPanel.add(imageLabel);

        FindStuBorrowedPanel.add(FindStuBorrowedTex);
        FindStuBorrowedPanel.add(FindStuBorrowedBtn);

        backBtn.setPreferredSize(new Dimension(100, 40));
        BooksBtn.setPreferredSize(new Dimension(200, 40));
        ReportBtn.setPreferredSize(new Dimension(200, 40));
        FindStuBorrowedTopBtn.setPreferredSize(new Dimension(200, 40));
        FindBookTex.setPreferredSize(new Dimension(150, 40));
        FindBookBtn.setPreferredSize(new Dimension(150, 40));
        FindStuBorrowedBtn.setPreferredSize(new Dimension(150, 40));
        FindStuBorrowedTex.setPreferredSize(new Dimension(150, 40));
        AddBooksBtn.setPreferredSize(new Dimension(150, 40));
        Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体

        backBtn.setFont(centerFont);
        ReportBtn.setFont(centerFont);
        FindStuBorrowedTopBtn.setFont(centerFont);
        BooksBtn.setFont(centerFont);
        FindBookBtn.setFont(centerFont);
        FindStuBorrowedBtn.setFont(centerFont);
        AddBooksBtn.setFont(centerFont);
        table.setFont(centerFont);
        tableFindStuBorrowed.setFont(centerFont);

        NumOfAllBooksLabel.setFont(centerFont);
        NumOfBookInTheLibraryLabel.setFont(centerFont);
        NumOfBorrowedBooksLabel.setFont(centerFont);
        NumOfReadersLabel.setFont(centerFont);
        NumOfAllBooksLabelOut.setFont(centerFont);
        NumOfBookInTheLibraryLabelOut.setFont(centerFont);
        NumOfBorrowedBooksLabelOut.setFont(centerFont);
        NumOfReadersLabelOut.setFont(centerFont);

        ReportPanel.add(NumOfAllBooksLabel);
        ReportPanel.add(NumOfBookInTheLibraryLabel);
        ReportPanel.add(NumOfBorrowedBooksLabel);
        ReportPanel.add(NumOfReadersLabel);
        ReportPanel.add(NumOfAllBooksLabelOut);
        ReportPanel.add(NumOfBookInTheLibraryLabelOut);
        ReportPanel.add(NumOfBorrowedBooksLabelOut);
        ReportPanel.add(NumOfReadersLabelOut);


        TopPanel.add(BooksBtn);
        TopPanel.add(ReportBtn);
        TopPanel.add(FindStuBorrowedTopBtn);

        BottomPanel.add(backBtn);
        BooksPanel.add(scrollPane);
        FindStuBorrowedPanel.add(scrollPaneFindStuBorrowed);


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
        springLayout.putConstraint(SpringLayout.WEST, FindBookTex, 150, SpringLayout.WEST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookTex, 30, SpringLayout.NORTH, BooksPanel);
//        springLayout.putConstraint(SpringLayout.EAST, NumOfBook, -150, SpringLayout.EAST, BooksPanel);
//        springLayout.putConstraint(SpringLayout.NORTH, NumOfBook, 30, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBookOut, -100, SpringLayout.EAST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookOut, 30, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindBookBtn, 10, SpringLayout.EAST, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookBtn, 0, SpringLayout.NORTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 50, SpringLayout.SOUTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, panel1);
        springLayout.putConstraint(SpringLayout.WEST, AddBooksBtn, 30, SpringLayout.EAST, FindBookBtn);
        springLayout.putConstraint(SpringLayout.NORTH, AddBooksBtn, 0, SpringLayout.NORTH, FindBookBtn);

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(NumOfAllBooksLabel), Spring.width(NumOfAllBooksLabelOut)),
                Spring.constant(0));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.NORTH, NumOfAllBooksLabel, 60, SpringLayout.NORTH, ReportPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfAllBooksLabelOut, 60, SpringLayout.NORTH, ReportPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfAllBooksLabel, -offsetX + 40, SpringLayout.HORIZONTAL_CENTER, ReportPanel);
        springLayout.putConstraint(SpringLayout.WEST, NumOfAllBooksLabelOut, 60, SpringLayout.HORIZONTAL_CENTER, ReportPanel);

        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookInTheLibraryLabel, 60, SpringLayout.SOUTH, NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBookInTheLibraryLabel, 0, SpringLayout.EAST, NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookInTheLibraryLabelOut, 60, SpringLayout.SOUTH, NumOfAllBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST, NumOfBookInTheLibraryLabelOut, 0, SpringLayout.WEST, NumOfAllBooksLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH, NumOfBorrowedBooksLabel, 60, SpringLayout.SOUTH, NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBorrowedBooksLabel, 0, SpringLayout.EAST, NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBorrowedBooksLabelOut, 60, SpringLayout.SOUTH, NumOfBookInTheLibraryLabelOut);
        springLayout.putConstraint(SpringLayout.WEST, NumOfBorrowedBooksLabelOut, 0, SpringLayout.WEST, NumOfBookInTheLibraryLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH, NumOfReadersLabel, 60, SpringLayout.SOUTH, NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfReadersLabel, 0, SpringLayout.EAST, NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfReadersLabelOut, 60, SpringLayout.SOUTH, NumOfBorrowedBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST, NumOfReadersLabelOut, 0, SpringLayout.WEST, NumOfBorrowedBooksLabelOut);


        springLayout.putConstraint(SpringLayout.WEST, FindStuBorrowedTex, 150, SpringLayout.WEST, FindStuBorrowedPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindStuBorrowedTex, 30, SpringLayout.NORTH, FindStuBorrowedPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindStuBorrowedBtn, 10, SpringLayout.EAST, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindStuBorrowedBtn, 0, SpringLayout.NORTH, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPaneFindStuBorrowed, 50, SpringLayout.SOUTH, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPaneFindStuBorrowed, 100, SpringLayout.WEST, panel1);

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);
        setResizable(true);
        setVisible((true));




        /* 监听函数 */
        BooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1, "BooksPanel");
            }
        });
        ReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalNum = 0;
                int freeNum = 0;
                int borrowedNum = 0;
                LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
                UniqueMessage uniqueMessage = new UniqueMessage("yes");
                try {
                    totalNum = libraryClientAPI.getTotalBooksNum(uniqueMessage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LibraryClientAPI libraryClientAPI1 = new LibraryClientAPIImpl("localhost", 8888);
                LibraryClientAPI libraryClientAPI2 = new LibraryClientAPIImpl("localhost", 8888);
                try {
                    freeNum = libraryClientAPI1.getFreeBooksNum(uniqueMessage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    borrowedNum = libraryClientAPI2.getBorrowedBooksNum(uniqueMessage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                LibraryClientAPI libraryClientAPI6 = new LibraryClientAPIImpl("localhost", 8888);
                int[] report;
                try {
                    report = libraryClientAPI6.getBookReport(uniqueMessage);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                NumOfBookInTheLibraryLabelOut.setText(Integer.toString(totalNum));
                NumOfBookInTheLibraryLabelOut.setText(Integer.toString(freeNum));
                NumOfBorrowedBooksLabelOut.setText(Integer.toString(borrowedNum));
                if (report == null)
                    NumOfReadersLabelOut.setText(Integer.toString(0));
                else
                    NumOfReadersLabelOut.setText(Integer.toString(report[3]));
                cardLayout.show(panel1, "ReportPanel");

            }
        });
        FindStuBorrowedTopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ShowOprTable();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                cardLayout.show(panel1, "FindStuBorrowedPanel");
            }
        });
        AddBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddBooksUI addOrChangeBooksUI = new AddBooksUI();
                addOrChangeBooksUI.setVisible(true);
            }
        });
        FindBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchBtnClicked();
            }
        });
        FindStuBorrowedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Pressed");
                String searchText = FindStuBorrowedTex.getText(); // 获取文本框内容作为搜索文本
                try {
                    ShowSearchOprTable(searchText);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //TODO:添加点击查找以后，表格中显示所查学生借的书

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
//        try
//        {
//            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
//        }
//        catch(Exception e)
//        {
//
//
//        }

        new LibraryAdminUI();
    }

    private void deleteBtnClicked(ActionEvent e) throws IOException {
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
        Boolean flag = libraryClientAPI_1.deleteBook(bookISBNMessage_1);

        if (flag) {
            System.out.println("Delete Successfully");
        }
        LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost", 8888);
        String a = "yes";
        UniqueMessage noDataReqMessage = new UniqueMessage(a);

        Book[] AllBooks = libraryClientAPI_2.getStoredBookList(noDataReqMessage);
        ShowTableData(AllBooks);
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
        ShowTableData(bookArray);
    }

    private void BackBtnClicked(ActionEvent e) throws IOException {
        System.exit(0);
    }

    private void ShowTableData(Book[] bookArray) {
        // 把得到的书籍列表放入表格
        String[][] data;
        String[] columnNamesChosen;
        if (bookArray == null) {
            int columnCount = 8;
            columnNamesChosen = new String[]{"书名", "索书号", "作者", "类型", "出版社", "位置", "修改", "删除"};
            data = new String[][]{null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null};
            model.setDataVector(data, columnNamesChosen);
            table.setModel(model);
        } else {
            int rowCount = bookArray.length;
            int columnCount = 8;

            columnNamesChosen = new String[]{"书名", "索书号", "作者", "类型", "出版社", "位置", "修改", "删除"};
            // 创建二维字符串数组用于存储表格数据
            data = new String[rowCount][columnCount];

            // 将书籍信息转换为对象数组并存储在data数组中
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
            model.setDataVector(data, columnNamesChosen);
            table.setModel(model);

            LibraryAdminUI.ChangeBookTableCellEditorButton changeEditor = new ChangeBookTableCellEditorButton();
            LibraryAdminUI.ChangeBookTableCellRendererButton changeRenderer = new ChangeBookTableCellRendererButton();
            LibraryAdminUI.DeleteBookTableCellEditorButton deleteEditor = new DeleteBookTableCellEditorButton();
            LibraryAdminUI.DeleteBookTableCellRendererButton deleteRenderer = new DeleteBookTableCellRendererButton();

            table.getColumnModel().getColumn(6).setCellRenderer(changeRenderer);
            table.getColumnModel().getColumn(6).setCellEditor(changeEditor);

            table.getColumnModel().getColumn(7).setCellRenderer(deleteRenderer);
            table.getColumnModel().getColumn(7).setCellEditor(deleteEditor);
        }

    }

    private void ShowOprTable() throws IOException {
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
        BookOperationRecord[] bookArray;
        UniqueMessage uniqueMessage = new UniqueMessage("yes");
        bookArray = libraryClientAPI.getBookAllOperationRecord(uniqueMessage);
        // 把得到的书籍列表放入表格
        String[][] data;
        String[] columnNamesChosen;
        if (bookArray == null) {
            int columnCount = 6;

            String[] FindStuBorrowedColumnNames = {"操作序列号", "学号", "索书号", "操作时间", "操作类型", "备注"};//索书号是一本书一个

            data = new String[][]{null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, null};
            modelFind.setDataVector(data, FindStuBorrowedColumnNames);
            tableFindStuBorrowed.setModel(modelFind);
        } else {
            int rowCount = bookArray.length;
            int columnCount = 6;

            columnNamesChosen = new String[]{"操作序列号", "学号", "索书号", "操作时间", "操作类型", "备注"};
            // 创建二维字符串数组用于存储表格数据
            data = new String[rowCount][columnCount];

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            // 将书籍信息转换为对象数组并存储在data数组中
            // 将书籍信息转换为对象数组并存储在data数组中
            for (int i = 0; i < rowCount; i++) {
                BookOperationRecord book = bookArray[i];
                data[i] = new String[]{
                        book.getOprId(),
                        book.getuId(),
                        book.getISBN(),
                        ft.format(book.getOprTime()),
                        book.getOprtype(),
                        book.getOprMark(),
                };
            }
            modelFind.setDataVector(data, columnNamesChosen);
            tableFindStuBorrowed.setModel(modelFind);
        }
    }

    private void ShowSearchOprTable(String searchText) throws IOException {
        LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
        RegisterReqMessage registerReqMessage = new RegisterReqMessage(searchText);
        BookOperationRecord[] bookArray;
        bookArray = libraryClientAPI.getBookOprRecordByUid(registerReqMessage);

        // 把得到的书籍列表放入表格
        String[][] data;
        String[] columnNamesChosen;
        if (bookArray == null) {
            JOptionPane.showMessageDialog(this, "搜索不到该学生记录！");
            FindStuBorrowedTex.setText("");
            ShowOprTable();
        } else {
            int rowCount = bookArray.length;
            int columnCount = 6;

            columnNamesChosen = new String[]{"操作序列号", "学号", "索书号", "操作时间", "操作类型", "备注"};
            // 创建二维字符串数组用于存储表格数据
            data = new String[rowCount][columnCount];

            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            // 将书籍信息转换为对象数组并存储在data数组中
            // 将书籍信息转换为对象数组并存储在data数组中
            for (int i = 0; i < rowCount; i++) {
                BookOperationRecord book = bookArray[i];
                data[i] = new String[]{
                        book.getOprId(),
                        book.getuId(),
                        book.getISBN(),
                        ft.format(book.getOprTime()),
                        book.getOprtype(),
                        book.getOprMark(),
                };
            }
            modelFind.setDataVector(data, columnNamesChosen);
            tableFindStuBorrowed.setModel(modelFind);
        }
    }

    class AddBooksUI extends JFrame {
        SpringLayout springLayout;
        JLabel BookIdLabel;
        JLabel BookISBNLabel;
        JLabel AuthorLabel;
        JLabel BookTypeLabel;
        JLabel PublisherLabel;
        JLabel BookPosLabel;
        JTextField BookIdTex;
        JTextField BookISBNTex;
        JTextField AuthorTex;
        JTextField BookTypeTex;
        JTextField PublisherTex;
        JTextField BookPosTex;
        JButton EnsureAddBtn;
        JButton ExitBtn;
        JPanel panel;


        public AddBooksUI() {
            setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            springLayout = new SpringLayout();
            BookIdLabel = new JLabel("书名");
            BookISBNLabel = new JLabel("索书号");
            AuthorLabel = new JLabel("作者");
            BookTypeLabel = new JLabel("类型");
            PublisherLabel = new JLabel("出版社");
            BookPosLabel = new JLabel("位置");
            BookIdTex = new JTextField();
            BookISBNTex = new JTextField();
            AuthorTex = new JTextField();
            BookTypeTex = new JTextField();
            PublisherTex = new JTextField();
            BookPosTex = new JTextField();
            EnsureAddBtn = new JButton("确认");
            ExitBtn = new JButton("取消");
            panel = new JPanel(springLayout);


            Container contentPane = getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());

            contentPane.add(panel, BorderLayout.CENTER);
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            BookIdLabel.setFont(centerFont);

            BookISBNLabel.setFont(centerFont);

            AuthorLabel.setFont(centerFont);

            BookTypeLabel.setFont(centerFont);

            PublisherLabel.setFont(centerFont);

            BookPosLabel.setFont(centerFont);


            EnsureAddBtn.setPreferredSize(new Dimension(150, 30));//设置按钮大小
            ExitBtn.setPreferredSize(new Dimension(150, 30));
            panel.add(EnsureAddBtn);
            panel.add(ExitBtn);

            BookIdTex.setPreferredSize(new Dimension(200, 25));
            BookISBNTex.setPreferredSize(new Dimension(200, 25));
            AuthorTex.setPreferredSize(new Dimension(200, 25));
            BookTypeTex.setPreferredSize(new Dimension(200, 25));
            PublisherTex.setPreferredSize(new Dimension(200, 25));
            BookPosTex.setPreferredSize(new Dimension(200, 25));
            panel.add(BookIdLabel);
            panel.add(BookISBNLabel);
            panel.add(AuthorLabel);
            panel.add(BookTypeLabel);
            panel.add(PublisherLabel);
            panel.add(BookPosLabel);
            panel.add(BookIdTex);
            panel.add(BookISBNTex);
            panel.add(AuthorTex);
            panel.add(BookTypeTex);
            panel.add(PublisherTex);
            panel.add(BookPosTex);
            Spring childWidth = Spring.sum(Spring.sum(Spring.width(BookIdLabel), Spring.width(BookIdTex)),
                    Spring.constant(0));
            int offsetX = childWidth.getValue() / 2;
            springLayout.putConstraint(SpringLayout.NORTH, BookIdLabel, 20, SpringLayout.NORTH, panel);
            springLayout.putConstraint(SpringLayout.NORTH, BookIdTex, 20, SpringLayout.NORTH, panel);
            springLayout.putConstraint(SpringLayout.EAST, BookIdLabel, -offsetX + 40, SpringLayout.HORIZONTAL_CENTER, panel);
            springLayout.putConstraint(SpringLayout.WEST, BookIdTex, offsetX - 160, SpringLayout.HORIZONTAL_CENTER, panel);

            springLayout.putConstraint(SpringLayout.NORTH, BookISBNLabel, 20, SpringLayout.SOUTH, BookIdLabel);
            springLayout.putConstraint(SpringLayout.EAST, BookISBNLabel, 0, SpringLayout.EAST, BookIdLabel);
            springLayout.putConstraint(SpringLayout.NORTH, BookISBNTex, 20, SpringLayout.SOUTH, BookIdTex);
            springLayout.putConstraint(SpringLayout.WEST, BookISBNTex, 0, SpringLayout.WEST, BookIdTex);

            springLayout.putConstraint(SpringLayout.NORTH, AuthorLabel, 20, SpringLayout.SOUTH, BookISBNLabel);
            springLayout.putConstraint(SpringLayout.EAST, AuthorLabel, 0, SpringLayout.EAST, BookISBNLabel);
            springLayout.putConstraint(SpringLayout.NORTH, AuthorTex, 20, SpringLayout.SOUTH, BookISBNTex);
            springLayout.putConstraint(SpringLayout.WEST, AuthorTex, 0, SpringLayout.WEST, BookISBNTex);

            springLayout.putConstraint(SpringLayout.NORTH, BookTypeLabel, 20, SpringLayout.SOUTH, AuthorLabel);
            springLayout.putConstraint(SpringLayout.EAST, BookTypeLabel, 0, SpringLayout.EAST, AuthorLabel);
            springLayout.putConstraint(SpringLayout.NORTH, BookTypeTex, 20, SpringLayout.SOUTH, AuthorTex);
            springLayout.putConstraint(SpringLayout.WEST, BookTypeTex, 0, SpringLayout.WEST, AuthorTex);

            springLayout.putConstraint(SpringLayout.NORTH, PublisherLabel, 20, SpringLayout.SOUTH, BookTypeLabel);
            springLayout.putConstraint(SpringLayout.EAST, PublisherLabel, 0, SpringLayout.EAST, BookTypeLabel);
            springLayout.putConstraint(SpringLayout.NORTH, PublisherTex, 20, SpringLayout.SOUTH, BookTypeTex);
            springLayout.putConstraint(SpringLayout.WEST, PublisherTex, 0, SpringLayout.WEST, BookTypeTex);

            springLayout.putConstraint(SpringLayout.NORTH, BookPosLabel, 20, SpringLayout.SOUTH, PublisherLabel);
            springLayout.putConstraint(SpringLayout.EAST, BookPosLabel, 0, SpringLayout.EAST, PublisherLabel);
            springLayout.putConstraint(SpringLayout.NORTH, BookPosTex, 20, SpringLayout.SOUTH, PublisherTex);
            springLayout.putConstraint(SpringLayout.WEST, BookPosTex, 0, SpringLayout.WEST, PublisherTex);

            springLayout.putConstraint(SpringLayout.NORTH, EnsureAddBtn, 30, SpringLayout.SOUTH, BookPosTex);
            springLayout.putConstraint(SpringLayout.EAST, EnsureAddBtn, 70, SpringLayout.EAST, BookPosLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ExitBtn, 0, SpringLayout.NORTH, EnsureAddBtn);
            springLayout.putConstraint(SpringLayout.WEST, ExitBtn, 20, SpringLayout.EAST, EnsureAddBtn);

            ExitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            EnsureAddBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookNameGet = BookIdTex.getText();
                    String bookISBNGet = BookISBNTex.getText();
                    String bookAuthorGet = AuthorTex.getText();
                    String bookTypeGet = BookTypeTex.getText();
                    String bookPublishGet = PublisherTex.getText();
                    String bookPosGet = BookPosTex.getText();
                    Book addBook = new Book(bookISBNGet, bookNameGet, bookAuthorGet,
                            bookTypeGet, 100.0, bookPublishGet, null, 100, 50, bookPosGet, 3
                    );
                    LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
                    try {
                        libraryClientAPI.AddBook(addBook);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    //TODO:新增图书到表格
                    LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost", 8888);
                    String a = "yes";
                    UniqueMessage noDataReqMessage = new UniqueMessage(a);

                    Book[] AllBooks = new Book[0];
                    try {
                        AllBooks = libraryClientAPI_2.getStoredBookList(noDataReqMessage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    ShowTableData(AllBooks);
                }
            });

            setSize(400, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
//            setResizable(false);
            setResizable(true);
            setVisible((true));
        }


    }

    class ChangeBooksUI extends JFrame {
        SpringLayout springLayout = new SpringLayout();
        JLabel ChangeBookIdLabel = new JLabel("书名");
        JLabel ChangeAuthorLabel = new JLabel("作者");
        JLabel ChangeBookTypeLabel = new JLabel("类型");
        JLabel ChangePublisherLabel = new JLabel("出版社");
        JLabel ChangeBookPosLabel = new JLabel("位置");


        JTextField ChangeBookIdTex = new JTextField();


        JTextField ChangeAuthorTex = new JTextField();


        JTextField ChangeBookTypeTex = new JTextField();

        JTextField ChangePublisherTex = new JTextField();

        JTextField ChangeBookPosTex = new JTextField();
        JButton ChangeEnsureBtn = new JButton("确认");

        JButton ChangeExitBtn = new JButton("取消");
        JPanel panel = new JPanel(springLayout);

        public ChangeBooksUI() {
            Container contentPane = getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());

            contentPane.add(panel, BorderLayout.CENTER);
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            ChangeBookIdLabel.setFont(centerFont);

            ChangeAuthorLabel.setFont(centerFont);

            ChangeBookTypeLabel.setFont(centerFont);

            ChangePublisherLabel.setFont(centerFont);

            ChangeBookPosLabel.setFont(centerFont);


            ChangeEnsureBtn.setPreferredSize(new Dimension(150, 30));//设置按钮大小
            ChangeExitBtn.setPreferredSize(new Dimension(150, 30));
            panel.add(ChangeEnsureBtn);
            panel.add(ChangeExitBtn);

            ChangeBookIdTex.setPreferredSize(new Dimension(200, 25));
            ChangeAuthorTex.setPreferredSize(new Dimension(200, 25));
            ChangeBookTypeTex.setPreferredSize(new Dimension(200, 25));
            ChangePublisherTex.setPreferredSize(new Dimension(200, 25));
            ChangeBookPosTex.setPreferredSize(new Dimension(200, 25));
            panel.add(ChangeBookIdLabel);
            panel.add(ChangeAuthorLabel);
            panel.add(ChangeBookTypeLabel);
            panel.add(ChangePublisherLabel);
            panel.add(ChangeBookPosLabel);
            panel.add(ChangeBookIdTex);
            panel.add(ChangeAuthorTex);
            panel.add(ChangeBookTypeTex);
            panel.add(ChangePublisherTex);
            panel.add(ChangeBookPosTex);
            Spring childWidth = Spring.sum(Spring.sum(Spring.width(ChangeBookIdLabel), Spring.width(ChangeBookIdTex)),
                    Spring.constant(0));
            int offsetX = childWidth.getValue() / 2;
            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookIdLabel, 40, SpringLayout.NORTH, panel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookIdTex, 40, SpringLayout.NORTH, panel);
            springLayout.putConstraint(SpringLayout.EAST, ChangeBookIdLabel, -offsetX + 40, SpringLayout.HORIZONTAL_CENTER, panel);
            springLayout.putConstraint(SpringLayout.WEST, ChangeBookIdTex, offsetX - 160, SpringLayout.HORIZONTAL_CENTER, panel);


            springLayout.putConstraint(SpringLayout.NORTH, ChangeAuthorLabel, 20, SpringLayout.SOUTH, ChangeBookIdLabel);
            springLayout.putConstraint(SpringLayout.EAST, ChangeAuthorLabel, 0, SpringLayout.EAST, ChangeBookIdLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangeAuthorTex, 20, SpringLayout.SOUTH, ChangeBookIdTex);
            springLayout.putConstraint(SpringLayout.WEST, ChangeAuthorTex, 0, SpringLayout.WEST, ChangeBookIdTex);

            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookTypeLabel, 20, SpringLayout.SOUTH, ChangeAuthorLabel);
            springLayout.putConstraint(SpringLayout.EAST, ChangeBookTypeLabel, 0, SpringLayout.EAST, ChangeAuthorLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookTypeTex, 20, SpringLayout.SOUTH, ChangeAuthorTex);
            springLayout.putConstraint(SpringLayout.WEST, ChangeBookTypeTex, 0, SpringLayout.WEST, ChangeAuthorTex);

            springLayout.putConstraint(SpringLayout.NORTH, ChangePublisherLabel, 20, SpringLayout.SOUTH, ChangeBookTypeLabel);
            springLayout.putConstraint(SpringLayout.EAST, ChangePublisherLabel, 0, SpringLayout.EAST, ChangeBookTypeLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangePublisherTex, 20, SpringLayout.SOUTH, ChangeBookTypeTex);
            springLayout.putConstraint(SpringLayout.WEST, ChangePublisherTex, 0, SpringLayout.WEST, ChangeBookTypeTex);

            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookPosLabel, 20, SpringLayout.SOUTH, ChangePublisherLabel);
            springLayout.putConstraint(SpringLayout.EAST, ChangeBookPosLabel, 0, SpringLayout.EAST, ChangePublisherLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangeBookPosTex, 20, SpringLayout.SOUTH, ChangePublisherTex);
            springLayout.putConstraint(SpringLayout.WEST, ChangeBookPosTex, 0, SpringLayout.WEST, ChangePublisherTex);

            springLayout.putConstraint(SpringLayout.NORTH, ChangeEnsureBtn, 30, SpringLayout.SOUTH, ChangeBookPosTex);
            springLayout.putConstraint(SpringLayout.EAST, ChangeEnsureBtn, 70, SpringLayout.EAST, ChangeBookPosLabel);
            springLayout.putConstraint(SpringLayout.NORTH, ChangeExitBtn, 0, SpringLayout.NORTH, ChangeEnsureBtn);
            springLayout.putConstraint(SpringLayout.WEST, ChangeExitBtn, 20, SpringLayout.EAST, ChangeEnsureBtn);


            ChangeExitBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            ChangeEnsureBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    System.out.println("点击的行索引：" + changeBtnRow);
                    String bookNameGet = ChangeBookIdTex.getText();
//                    String bookISBNGet = BookISBNTex.getText();
                    Object data = table.getValueAt(changeBtnRow, 1);
                    String bookISBNGet = (String) data;
                    String bookAuthorGet = ChangeAuthorTex.getText();
                    String bookTypeGet = ChangeBookTypeTex.getText();
                    String bookPublishGet = ChangePublisherTex.getText();
                    String bookPosGet = ChangeBookPosTex.getText();
                    Book addBook = new Book(bookISBNGet, bookNameGet, bookAuthorGet,
                            bookTypeGet, 100.0, bookPublishGet, null, 1, 1, bookPosGet, 0
                    );
                    Boolean flag = false;
                    LibraryClientAPI libraryClientAPI = new LibraryClientAPIImpl("localhost", 8888);
                    try {
                        flag = libraryClientAPI.ChangeBook(addBook);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    //TODO:修改则在数据库和表格中修改图书信息
                    LibraryClientAPI libraryClientAPI_2 = new LibraryClientAPIImpl("localhost", 8888);
                    String a = "yes";
                    UniqueMessage noDataReqMessage = new UniqueMessage(a);

                    Book[] AllBooks = new Book[0];
                    try {
                        AllBooks = libraryClientAPI_2.getStoredBookList(noDataReqMessage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    ShowTableData(AllBooks);
                }
            });

            setSize(400, 400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
//            setResizable(false);
            setResizable(true);
            setVisible((true));
        }
    }
}

