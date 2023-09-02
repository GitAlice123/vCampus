package view.Library;

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
import javax.swing.JTable;

import javax.swing.table.JTableHeader;


public class LibraryAdminUI extends JFrame {
    class DeleteBookTableCellRendererButton implements TableCellRenderer {



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            button.setBackground(customColor);
            return button;
        }

    }
    class DeleteBookTableCellEditorButton extends DefaultCellEditor{

        private JButton btn;
        private int clickedRow;
        public DeleteBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
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

                    //TODO:此处要添加删除操作，将该行对应的书从表格和数据库中删除
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
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            Color customColor = new Color(255, 255, 192);
            button.setBackground(customColor);
            return button;
        }

    }
    class ChangeBookTableCellEditorButton extends DefaultCellEditor{

        private JButton btn;
        private int clickedRow;
        public ChangeBookTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("修改");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
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

                    AddOrChangeBooksUI addOrChangeBooksUI=new AddOrChangeBooksUI();
                    addOrChangeBooksUI.setVisible(true);
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
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelFind = new DefaultTableModel();

    JTable table = new JTable();

    String[][] data = {//书籍列表，表格数据均传入该数组
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1","1"}


    };
    JTable tableFindStuBorrowed = new JTable();
    String[][] dataFindStuBorrowed = {//已借阅书籍列表
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
    JPanel BooksPanel=new JPanel(springLayout);
    JPanel ReportPanel=new JPanel(springLayout);

    JPanel FindStuBorrowedPanel=new JPanel(springLayout);
    JButton BooksBtn=new JButton("查看书籍列表");

    JButton ReportBtn=new JButton("查看报告");
    JButton FindStuBorrowedTopBtn=new JButton("学生记录查询");
    JButton AddBooksBtn=new JButton("增加书籍");
    public JTextField getFindBookTex() {
        return FindBookTex;
    }

    JTextField FindBookTex=new JTextField();//查找图书的输入框
    JButton FindBookBtn=new JButton("查找");//查找按钮

    public JTextField getFindStuBorrowedTex() {
        return FindStuBorrowedTex;
    }

    JTextField FindStuBorrowedTex=new JTextField();//查找学生借书记录输入框
    JButton FindStuBorrowedBtn=new JButton("查找");//查找按钮
    JLabel NumOfBook=new JLabel("在馆数量:");
    JLabel NumOfAllBooksLabel=new JLabel("馆藏图书数量");
    JLabel NumOfBookInTheLibraryLabel=new JLabel("在馆图书数量");
    JLabel NumOfBorrowedBooksLabel=new JLabel("借出图书数量");
    JLabel NumOfReadersLabel=new JLabel("读者数量");

    public void setNumOfAllBooks(String numOfAllBooks) {
        NumOfAllBooks = numOfAllBooks;
    }

    String NumOfAllBooks;

    public void setNumOfBookInTheLibrary(String numOfBookInTheLibrary) {
        NumOfBookInTheLibrary = numOfBookInTheLibrary;
    }

    String NumOfBookInTheLibrary;

    public void setNumOfBorrowedBooks(String numOfBorrowedBooks) {
        NumOfBorrowedBooks = numOfBorrowedBooks;
    }

    String NumOfBorrowedBooks;

    public void setNumOfReaders(String numOfReaders) {
        NumOfReaders = numOfReaders;
    }

    String NumOfReaders;
    JLabel NumOfAllBooksLabelOut=new JLabel(NumOfAllBooks);//用上述SET函数导入数据，需要强制转换成String
    JLabel NumOfBookInTheLibraryLabelOut=new JLabel(NumOfBookInTheLibrary);
    JLabel NumOfBorrowedBooksLabelOut=new JLabel(NumOfBorrowedBooks);
    JLabel NumOfReadersLabelOut=new JLabel(NumOfReaders);




    public void setBookNum(String bookNum) {
        BookNum = bookNum;
    }

    String BookNum;
    JLabel NumOfBookOut=new JLabel(BookNum);//TODO:此处需要添加，显示所查找书籍的在馆数量，利用上述SET函数写入

    JButton backBtn=new JButton("退出");
    public LibraryAdminUI(){initComponent();}
    private void initComponent(){

       // super("图书馆系统");

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

        String[] columnNames ={"书名","索书号","作者","类型","出版社","位置","修改","删除"};//索书号是一本书一个

        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度


        table.getColumnModel().getColumn(6).setCellRenderer(new ChangeBookTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new ChangeBookTableCellEditorButton());
        table.getColumnModel().getColumn(7).setCellRenderer(new DeleteBookTableCellRendererButton());
        table.getColumnModel().getColumn(7).setCellEditor(new DeleteBookTableCellEditorButton());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());


        String[] FindStuBorrowedColumnNames ={"操作序列号","学号","索书号","操作时间","操作类型","备注"};//索书号是一本书一个

        modelFind.setDataVector(dataFindStuBorrowed, FindStuBorrowedColumnNames);
        tableFindStuBorrowed.setModel(modelFind);

        tableFindStuBorrowed.setRowHeight(30);
        JTableHeader tab_headerFindStuBorrowed = tableFindStuBorrowed.getTableHeader();					//获取表头
        tab_headerFindStuBorrowed.setFont(new Font("楷体",Font.PLAIN,25));
        tab_headerFindStuBorrowed.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度


        JScrollPane scrollPaneFindStuBorrowed = new JScrollPane(tableFindStuBorrowed);
        scrollPaneFindStuBorrowed.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        tableFindStuBorrowed.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());

        Container contentPane=getContentPane();//获取控制面板
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
        BooksPanel.add(NumOfBook);
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
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        NumOfBook.setFont(centerFont);
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
        //ReportPanel.add(scrollPaneChosen);
        BooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"BooksPanel");
            }
        });
        ReportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"ReportPanel");
            }
        });
        FindStuBorrowedTopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"FindStuBorrowedPanel");
            }
        });
        AddBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddOrChangeBooksUI addOrChangeBooksUI=new AddOrChangeBooksUI();
                addOrChangeBooksUI.setVisible(true);
            }
        });
        FindBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO:添加点击查找以后，表格中显示所查书名或索书号的书
            }
        });
        FindStuBorrowedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO:添加点击查找以后，表格中显示所查学生借的书
            }
        });
        springLayout.putConstraint(SpringLayout.WEST, FindBookTex, 150, SpringLayout.WEST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookTex, 30, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBook, -150, SpringLayout.EAST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBook, 30, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBookOut, -100, SpringLayout.EAST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookOut, 30, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindBookBtn, 10, SpringLayout.EAST, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookBtn, 0, SpringLayout.NORTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 50, SpringLayout.SOUTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, panel1);
        springLayout.putConstraint(SpringLayout.WEST, AddBooksBtn, 30, SpringLayout.EAST, FindBookBtn);
        springLayout.putConstraint(SpringLayout.NORTH, AddBooksBtn, 0, SpringLayout.NORTH, FindBookBtn);

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(NumOfAllBooksLabel),Spring.width(NumOfAllBooksLabelOut)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,NumOfAllBooksLabel,60,SpringLayout.NORTH,ReportPanel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfAllBooksLabelOut,60,SpringLayout.NORTH,ReportPanel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfAllBooksLabel,-offsetX+40,SpringLayout.HORIZONTAL_CENTER,ReportPanel);
        springLayout.putConstraint(SpringLayout.WEST,NumOfAllBooksLabelOut,offsetX-160,SpringLayout.HORIZONTAL_CENTER,ReportPanel);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfBookInTheLibraryLabel,60,SpringLayout.SOUTH,NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfBookInTheLibraryLabel,0,SpringLayout.EAST,NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfBookInTheLibraryLabelOut,60,SpringLayout.SOUTH,NumOfAllBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfBookInTheLibraryLabelOut,0,SpringLayout.WEST,NumOfAllBooksLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfBorrowedBooksLabel,60,SpringLayout.SOUTH,NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfBorrowedBooksLabel,0,SpringLayout.EAST,NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfBorrowedBooksLabelOut,60,SpringLayout.SOUTH,NumOfBookInTheLibraryLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfBorrowedBooksLabelOut,0,SpringLayout.WEST,NumOfBookInTheLibraryLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfReadersLabel,60,SpringLayout.SOUTH,NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfReadersLabel,0,SpringLayout.EAST,NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfReadersLabelOut,60,SpringLayout.SOUTH,NumOfBorrowedBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfReadersLabelOut,0,SpringLayout.WEST,NumOfBorrowedBooksLabelOut);


        springLayout.putConstraint(SpringLayout.WEST, FindStuBorrowedTex, 150, SpringLayout.WEST, FindStuBorrowedPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindStuBorrowedTex, 30, SpringLayout.NORTH, FindStuBorrowedPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindStuBorrowedBtn, 10, SpringLayout.EAST, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindStuBorrowedBtn, 0, SpringLayout.NORTH, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPaneFindStuBorrowed, 50, SpringLayout.SOUTH, FindStuBorrowedTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPaneFindStuBorrowed, 100, SpringLayout.WEST, panel1);

        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

    static class TableBackgroundColorRenderer extends DefaultTableCellRenderer {
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
}
class AddOrChangeBooksUI extends JFrame{
    SpringLayout springLayout=new SpringLayout();
    JLabel BookIdLabel=new JLabel("书名");
    JLabel BookISBNLabel=new JLabel("索书号");
    JLabel AuthorLabel=new JLabel("作者");
    JLabel BookTypeLabel=new JLabel("类型");
    JLabel PublisherLabel=new JLabel("出版社");
    JLabel BookPosLabel=new JLabel("位置");

    public JTextField getBookIdTex() {
        return BookIdTex;
    }

    JTextField BookIdTex=new JTextField();

    public JTextField getBookISBNTex() {
        return BookISBNTex;
    }

    JTextField BookISBNTex=new JTextField();

    public JTextField getAuthorTex() {
        return AuthorTex;
    }

    JTextField AuthorTex=new JTextField();

    public JTextField getBookTypeTex() {
        return BookTypeTex;
    }

    JTextField BookTypeTex=new JTextField();

    public JTextField getPublisherTex() {
        return PublisherTex;
    }

    JTextField PublisherTex=new JTextField();

    public JTextField getBookPosTex() {
        return BookPosTex;
    }

    JTextField BookPosTex=new JTextField();
    JButton EnsureBtn=new JButton("确认");

    JButton ExitBtn=new JButton("取消");
    JPanel panel=new JPanel(springLayout);

    public AddOrChangeBooksUI(){
        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());

        contentPane.add(panel,BorderLayout.CENTER);
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        BookIdLabel.setFont(centerFont);

        BookISBNLabel.setFont(centerFont);

        AuthorLabel.setFont(centerFont);

        BookTypeLabel.setFont(centerFont);

        PublisherLabel.setFont(centerFont);

        BookPosLabel.setFont(centerFont);


        EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
        ExitBtn.setPreferredSize(new Dimension(150,30));
        panel.add(EnsureBtn);
        panel.add(ExitBtn);

        BookIdTex.setPreferredSize(new Dimension(200,25));
        BookISBNTex.setPreferredSize(new Dimension(200,25));
        AuthorTex.setPreferredSize(new Dimension(200,25));
        BookTypeTex.setPreferredSize(new Dimension(200,25));
        PublisherTex.setPreferredSize(new Dimension(200,25));
        BookPosTex.setPreferredSize(new Dimension(200,25));
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
        Spring childWidth=Spring.sum(Spring.sum(Spring.width(BookIdLabel),Spring.width(BookIdTex)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,BookIdLabel,20,SpringLayout.NORTH,panel);
        springLayout.putConstraint(SpringLayout.NORTH,BookIdTex,20,SpringLayout.NORTH,panel);
        springLayout.putConstraint(SpringLayout.EAST,BookIdLabel,-offsetX+40,SpringLayout.HORIZONTAL_CENTER,panel);
        springLayout.putConstraint(SpringLayout.WEST,BookIdTex,offsetX-160,SpringLayout.HORIZONTAL_CENTER,panel);

        springLayout.putConstraint(SpringLayout.NORTH,BookISBNLabel,20,SpringLayout.SOUTH,BookIdLabel);
        springLayout.putConstraint(SpringLayout.EAST,BookISBNLabel,0,SpringLayout.EAST,BookIdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,BookISBNTex,20,SpringLayout.SOUTH,BookIdTex);
        springLayout.putConstraint(SpringLayout.WEST,BookISBNTex,0,SpringLayout.WEST,BookIdTex);

        springLayout.putConstraint(SpringLayout.NORTH,AuthorLabel,20,SpringLayout.SOUTH,BookISBNLabel);
        springLayout.putConstraint(SpringLayout.EAST,AuthorLabel,0,SpringLayout.EAST,BookISBNLabel);
        springLayout.putConstraint(SpringLayout.NORTH,AuthorTex,20,SpringLayout.SOUTH,BookISBNTex);
        springLayout.putConstraint(SpringLayout.WEST,AuthorTex,0,SpringLayout.WEST,BookISBNTex);

        springLayout.putConstraint(SpringLayout.NORTH,BookTypeLabel,20,SpringLayout.SOUTH,AuthorLabel);
        springLayout.putConstraint(SpringLayout.EAST,BookTypeLabel,0,SpringLayout.EAST,AuthorLabel);
        springLayout.putConstraint(SpringLayout.NORTH,BookTypeTex,20,SpringLayout.SOUTH,AuthorTex);
        springLayout.putConstraint(SpringLayout.WEST,BookTypeTex,0,SpringLayout.WEST,AuthorTex);

        springLayout.putConstraint(SpringLayout.NORTH,PublisherLabel,20,SpringLayout.SOUTH,BookTypeLabel);
        springLayout.putConstraint(SpringLayout.EAST,PublisherLabel,0,SpringLayout.EAST,BookTypeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,PublisherTex,20,SpringLayout.SOUTH,BookTypeTex);
        springLayout.putConstraint(SpringLayout.WEST,PublisherTex,0,SpringLayout.WEST,BookTypeTex);

        springLayout.putConstraint(SpringLayout.NORTH,BookPosLabel,20,SpringLayout.SOUTH,PublisherLabel);
        springLayout.putConstraint(SpringLayout.EAST,BookPosLabel,0,SpringLayout.EAST,PublisherLabel);
        springLayout.putConstraint(SpringLayout.NORTH,BookPosTex,20,SpringLayout.SOUTH,PublisherTex);
        springLayout.putConstraint(SpringLayout.WEST,BookPosTex,0,SpringLayout.WEST,PublisherTex);

        springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,30,SpringLayout.SOUTH,BookPosTex);
        springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,70,SpringLayout.EAST,BookPosLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ExitBtn,0,SpringLayout.NORTH,EnsureBtn);
        springLayout.putConstraint(SpringLayout.WEST,ExitBtn,20,SpringLayout.EAST,EnsureBtn);






        ExitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        EnsureBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //TODO:新增则新增图书到表格和数据库
                //TODO:修改则在数据库和表格中修改图书信息
            }
        });
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
}

