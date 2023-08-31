package src.LibraryUI;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryAdminUI extends JFrame {
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();

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
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel BooksPanel=new JPanel(springLayout);
    JPanel ReportPanel=new JPanel(springLayout);
    JButton BooksBtn=new JButton("查看书籍列表");

    JButton ReportBtn=new JButton("查看报告");
    JButton AddBooksBtn=new JButton("增加书籍");
    public JTextField getFindBookTex() {
        return FindBookTex;
    }

    JTextField FindBookTex=new JTextField();//查找图书的输入框
    JButton FindBookBtn=new JButton("查找");//查找按钮
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
    JLabel NumOfBookOut=new JLabel(BookNum);//此处需要添加，显示所查找书籍的在馆数量，利用上述SET函数写入

    JButton backBtn=new JButton("退出");
    public LibraryAdminUI(){
        super("图书馆系统");

        String[] columnNames ={"书名","索书号","作者","类型","出版社","位置","修改","删除"};//索书号是一本书一个

        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(6).setCellRenderer(new ChangeBookTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new ChangeBookTableCellEditorButton());
        table.getColumnModel().getColumn(7).setCellRenderer(new DeleteBookTableCellRendererButton());
        table.getColumnModel().getColumn(7).setCellEditor(new DeleteBookTableCellEditorButton());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250)); // 设置滚动面板的大小


        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(TopPanel, BorderLayout.NORTH);
        contentPane.add(BottomPanel, BorderLayout.SOUTH);
        contentPane.add(panel1, BorderLayout.CENTER);
        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(BooksPanel, "BooksPanel");
        panel1.add(ReportPanel, "ReportPanel");
        BooksPanel.add(FindBookTex);
        BooksPanel.add(FindBookBtn);
        BooksPanel.add(NumOfBook);
        BooksPanel.add(NumOfBookOut);
        BooksPanel.add(AddBooksBtn);
        backBtn.setPreferredSize(new Dimension(100, 30));
        BooksBtn.setPreferredSize(new Dimension(150, 30));
        ReportBtn.setPreferredSize(new Dimension(150, 30));
        FindBookTex.setPreferredSize(new Dimension(150, 20));
        FindBookBtn.setPreferredSize(new Dimension(80, 20));
        AddBooksBtn.setPreferredSize(new Dimension(100, 20));
        Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
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

        BottomPanel.add(backBtn);
        BooksPanel.add(scrollPane);
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

                //添加点击查找以后，表格中显示所查书名或索书号的书
            }
        });
        springLayout.putConstraint(SpringLayout.WEST, FindBookTex, 20, SpringLayout.WEST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookTex, 10, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBook, -100, SpringLayout.EAST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBook, 10, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.EAST, NumOfBookOut, -50, SpringLayout.EAST, BooksPanel);
        springLayout.putConstraint(SpringLayout.NORTH, NumOfBookOut, 10, SpringLayout.NORTH, BooksPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindBookBtn, 10, SpringLayout.EAST, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, FindBookBtn, 0, SpringLayout.NORTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, FindBookTex);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, panel1);
        springLayout.putConstraint(SpringLayout.WEST, AddBooksBtn, 30, SpringLayout.EAST, FindBookBtn);
        springLayout.putConstraint(SpringLayout.NORTH, AddBooksBtn, 0, SpringLayout.NORTH, FindBookBtn);

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(NumOfAllBooksLabel),Spring.width(NumOfAllBooksLabelOut)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,NumOfAllBooksLabel,30,SpringLayout.NORTH,ReportPanel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfAllBooksLabelOut,30,SpringLayout.NORTH,ReportPanel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfAllBooksLabel,-offsetX+40,SpringLayout.HORIZONTAL_CENTER,ReportPanel);
        springLayout.putConstraint(SpringLayout.WEST,NumOfAllBooksLabelOut,offsetX-160,SpringLayout.HORIZONTAL_CENTER,ReportPanel);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfBookInTheLibraryLabel,30,SpringLayout.SOUTH,NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfBookInTheLibraryLabel,0,SpringLayout.EAST,NumOfAllBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfBookInTheLibraryLabelOut,30,SpringLayout.SOUTH,NumOfAllBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfBookInTheLibraryLabelOut,0,SpringLayout.WEST,NumOfAllBooksLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfBorrowedBooksLabel,30,SpringLayout.SOUTH,NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfBorrowedBooksLabel,0,SpringLayout.EAST,NumOfBookInTheLibraryLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfBorrowedBooksLabelOut,30,SpringLayout.SOUTH,NumOfBookInTheLibraryLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfBorrowedBooksLabelOut,0,SpringLayout.WEST,NumOfBookInTheLibraryLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NumOfReadersLabel,30,SpringLayout.SOUTH,NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.EAST,NumOfReadersLabel,0,SpringLayout.EAST,NumOfBorrowedBooksLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NumOfReadersLabelOut,30,SpringLayout.SOUTH,NumOfBorrowedBooksLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NumOfReadersLabelOut,0,SpringLayout.WEST,NumOfBorrowedBooksLabelOut);




        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }



    public static void main(String[] args){

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
        Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
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

                //新增则新增图书到表格和数据库
                //修改则在数据库和表格中修改图书信息
            }
        });
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
}
class DeleteBookTableCellRendererButton implements TableCellRenderer {



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton("删除");
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
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);

                //此处要添加删除操作，将该行对应的书从表格和数据库中删除
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
