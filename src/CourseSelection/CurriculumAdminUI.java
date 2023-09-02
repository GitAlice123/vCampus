package view.CourseSelection;

import view.Library.LibraryAdminUI;

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

public class CurriculumAdminUI extends JFrame {
    class AdminDeleteClassesTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class AdminDeleteClassesTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public AdminDeleteClassesTableCellEditorButton() {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    //此处要加删除该行课程班
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
    class AdminChangeClassesTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("修改");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class AdminChangeClassesTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public AdminChangeClassesTableCellEditorButton() {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("修改");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    AdminAddOrChangeClassesUI adminAddOrChangeClassesUI=new AdminAddOrChangeClassesUI();
                    adminAddOrChangeClassesUI.setVisible(true);
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
    class AdminShowClassesStuTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("展开");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class AdminShowClassesStuTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public AdminShowClassesStuTableCellEditorButton() {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("展开");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    AdminShowClassesStuUI adminShowClassesStuUI=new AdminShowClassesStuUI();
                    adminShowClassesStuUI.setVisible(true);
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
    JPanel TopPanel=new JPanel(springLayout);
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel(springLayout);

    JButton AddNewClassBtn=new JButton("新增课程班");
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JLabel title=new JLabel("课程班");
    String[][] data = {
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"},
            {"1", "1", "1", "1", "1", "1", "1","1","1"}

    };
    JButton backBtn=new JButton("退出");
    public CurriculumAdminUI(){
        super("选课系统");
        JLabel imageLabel = new JLabel();
        try {
            // 加载图片
            int newWidth = 90;  // 新的宽度
            int newHeight = 100; // 新的高度

            Image pkqIm = ImageIO.read(new File("Images/sdz1.jpeg"));  // 请将 "image.png" 替换为实际的图片路径

            Image scaledImage = pkqIm.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));


        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] columnNames ={"班级编号","课程名称","上课地点","最大人数","上课时间","任课教师","修改","删除","本班学生"};
        //loginHandler=new logInHandler(this);
        model.setDataVector(data, columnNames);
        table.setModel(model);//table是展示课程班的表格
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        table.getColumnModel().getColumn(6).setCellRenderer(new AdminChangeClassesTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new AdminChangeClassesTableCellEditorButton());
        table.getColumnModel().getColumn(7).setCellRenderer(new AdminDeleteClassesTableCellRendererButton());
        table.getColumnModel().getColumn(7).setCellEditor(new AdminDeleteClassesTableCellEditorButton());
        table.getColumnModel().getColumn(8).setCellRenderer(new AdminShowClassesStuTableCellRendererButton());
        table.getColumnModel().getColumn(8).setCellEditor(new AdminShowClassesStuTableCellEditorButton());
        //loginHandler=new logInHandler(this);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());

        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);
        title.setFont(new Font("楷体",Font.PLAIN,40));
        panel1.add(scrollPane);
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        backBtn.setFont(centerFont);
        AddNewClassBtn.setFont(centerFont);
        table.setFont(centerFont);
        AddNewClassBtn.setPreferredSize(new Dimension(200,40));
        backBtn.setPreferredSize(new Dimension(100,40));

        TopPanel.add(title);
        panel1.add(AddNewClassBtn);
        panel1.add(imageLabel);
        BottomPanel.add(backBtn);

        springLayout.putConstraint(SpringLayout.SOUTH,TopPanel,50,SpringLayout.NORTH,panel1);

        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,30,SpringLayout.SOUTH,AddNewClassBtn);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,panel1);
        springLayout.putConstraint(SpringLayout.EAST,AddNewClassBtn,-30,SpringLayout.EAST,panel1);
        springLayout.putConstraint(SpringLayout.NORTH,AddNewClassBtn,15,SpringLayout.NORTH,panel1);
        springLayout.putConstraint(SpringLayout.WEST,title,-20,SpringLayout.HORIZONTAL_CENTER,TopPanel);
        springLayout.putConstraint(SpringLayout.NORTH,title,15,SpringLayout.NORTH,TopPanel);
        AddNewClassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAddOrChangeClassesUI adminAddOrChangeClassesUI=new AdminAddOrChangeClassesUI();
                adminAddOrChangeClassesUI.setVisible(true);

            }
        });






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
            if (isSelected) {
                setForeground(Color.BLACK);
            } else {
                // 设置单元格背景颜色
                if (row % 2 == 0) {
                    Color customColor = new Color(240, 255, 255);
                    cellComponent.setBackground(customColor);
                } else {
                    Color customColor2 = new Color(224, 255, 255);
                    cellComponent.setBackground(customColor2);
                }
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
            UIManager.put("nimbusBlueGrey", new Color(173, 216, 230)); // 按钮
            UIManager.put("control", new Color(240, 248, 255)); // 背景



        } catch (Exception e) {
            e.printStackTrace();
        }
        new CurriculumAdminUI();
    }
}
class AdminAddOrChangeClassesUI extends JFrame{

    SpringLayout springLayout=new SpringLayout();
    JLabel ClassIdLabel=new JLabel("课程班编号");
    JLabel CourseNameLabel=new JLabel("课程名称");
    JLabel ClassPlaceLabel=new JLabel("上课地点");
    JLabel ClassMaxLabel=new JLabel("最大人数");
    JLabel ClassTimeLabel=new JLabel("上课时间");
    JLabel ClassTeacherLabel=new JLabel("任课教师");

    public JTextField getClassIdTex() {
        return ClassIdTex;
    }

    JTextField ClassIdTex=new JTextField();

    public JTextField getCourseNameTex() {
        return CourseNameTex;
    }

    JTextField CourseNameTex=new JTextField();

    public JTextField getClassPlaceTex() {
        return ClassPlaceTex;
    }

    JTextField ClassPlaceTex=new JTextField();

    public JTextField getClassMaxTex() {
        return ClassMaxTex;
    }

    JTextField ClassMaxTex=new JTextField();

    public JTextField getClassTimeTex() {
        return ClassTimeTex;
    }

    JTextField ClassTimeTex=new JTextField();

    public JTextField getClassTeacherTex() {
        return ClassTeacherTex;
    }

    JTextField ClassTeacherTex=new JTextField();
    JButton EnsureBtn=new JButton("确认");

    JButton ExitBtn=new JButton("取消");
    JPanel panel=new JPanel(springLayout);

    public AdminAddOrChangeClassesUI() {
        super("选课系统");
        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());

        contentPane.add(panel,BorderLayout.CENTER);
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        ClassIdLabel.setFont(centerFont);

        CourseNameLabel.setFont(centerFont);

        ClassPlaceLabel.setFont(centerFont);

        ClassMaxLabel.setFont(centerFont);

        ClassTimeLabel.setFont(centerFont);

        ClassTeacherLabel.setFont(centerFont);

        ClassIdTex.setFont(centerFont);

        CourseNameTex.setFont(centerFont);

        ClassPlaceTex.setFont(centerFont);

        ClassMaxTex.setFont(centerFont);

        ClassTimeTex.setFont(centerFont);

        ClassTeacherTex.setFont(centerFont);



        EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
        ExitBtn.setPreferredSize(new Dimension(150,30));
        panel.add(EnsureBtn);
        panel.add(ExitBtn);

        ClassIdTex.setPreferredSize(new Dimension(200,29));
        CourseNameTex.setPreferredSize(new Dimension(200,29));
        ClassPlaceTex.setPreferredSize(new Dimension(200,29));
        ClassMaxTex.setPreferredSize(new Dimension(200,29));
        ClassTimeTex.setPreferredSize(new Dimension(200,29));
        ClassTeacherTex.setPreferredSize(new Dimension(200,29));
        panel.add(ClassIdLabel);
        panel.add(CourseNameLabel);
        panel.add(ClassPlaceLabel);
        panel.add(ClassMaxLabel);
        panel.add(ClassTimeLabel);
        panel.add(ClassTeacherLabel);
        panel.add(ClassIdTex);
        panel.add(CourseNameTex);
        panel.add(ClassPlaceTex);
        panel.add(ClassMaxTex);
        panel.add(ClassTimeTex);
        panel.add(ClassTeacherTex);

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(ClassIdLabel),Spring.width(ClassIdTex)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,ClassIdLabel,20,SpringLayout.NORTH,panel);
        springLayout.putConstraint(SpringLayout.NORTH,ClassIdTex,20,SpringLayout.NORTH,panel);
        springLayout.putConstraint(SpringLayout.EAST,ClassIdLabel,-offsetX+80,SpringLayout.HORIZONTAL_CENTER,panel);
        springLayout.putConstraint(SpringLayout.WEST,ClassIdTex,offsetX-120,SpringLayout.HORIZONTAL_CENTER,panel);

        springLayout.putConstraint(SpringLayout.NORTH,CourseNameLabel,20,SpringLayout.SOUTH,ClassIdLabel);
        springLayout.putConstraint(SpringLayout.EAST,CourseNameLabel,0,SpringLayout.EAST,ClassIdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,CourseNameTex,20,SpringLayout.SOUTH,ClassIdTex);
        springLayout.putConstraint(SpringLayout.WEST,CourseNameTex,0,SpringLayout.WEST,ClassIdTex);

        springLayout.putConstraint(SpringLayout.NORTH,ClassPlaceLabel,20,SpringLayout.SOUTH,CourseNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,ClassPlaceLabel,0,SpringLayout.EAST,CourseNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ClassPlaceTex,20,SpringLayout.SOUTH,CourseNameTex);
        springLayout.putConstraint(SpringLayout.WEST,ClassPlaceTex,0,SpringLayout.WEST,CourseNameTex);

        springLayout.putConstraint(SpringLayout.NORTH,ClassMaxLabel,20,SpringLayout.SOUTH,ClassPlaceLabel);
        springLayout.putConstraint(SpringLayout.EAST,ClassMaxLabel,0,SpringLayout.EAST,ClassPlaceLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ClassMaxTex,20,SpringLayout.SOUTH,ClassPlaceTex);
        springLayout.putConstraint(SpringLayout.WEST,ClassMaxTex,0,SpringLayout.WEST,ClassPlaceTex);

        springLayout.putConstraint(SpringLayout.NORTH,ClassTimeLabel,20,SpringLayout.SOUTH,ClassMaxLabel);
        springLayout.putConstraint(SpringLayout.EAST,ClassTimeLabel,0,SpringLayout.EAST,ClassMaxLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ClassTimeTex,20,SpringLayout.SOUTH,ClassMaxTex);
        springLayout.putConstraint(SpringLayout.WEST,ClassTimeTex,0,SpringLayout.WEST,ClassMaxTex);

        springLayout.putConstraint(SpringLayout.NORTH,ClassTeacherLabel,20,SpringLayout.SOUTH,ClassTimeLabel);
        springLayout.putConstraint(SpringLayout.EAST,ClassTeacherLabel,0,SpringLayout.EAST,ClassTimeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,ClassTeacherTex,20,SpringLayout.SOUTH,ClassTimeTex);
        springLayout.putConstraint(SpringLayout.WEST,ClassTeacherTex,0,SpringLayout.WEST,ClassTimeTex);



        springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,30,SpringLayout.SOUTH,ClassTeacherTex);
        springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,60,SpringLayout.EAST,ClassTeacherLabel);
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

                //新增则新增课程班信息到数据库
                //修改则在数据库中修改课程班信息
            }
        });


        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
}
class AdminShowClassesStuUI extends JFrame{
    class AdminDeleteStuTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class AdminDeleteStuTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public AdminDeleteStuTableCellEditorButton() {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("删除");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    //此处要从该课程班中删除该行学生
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
    private class TableBackgroundColorRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                setForeground(Color.BLACK);
            } else {
                // 设置单元格背景颜色
                if (row % 2 == 0) {
                    Color customColor = new Color(240, 255, 255);
                    cellComponent.setBackground(customColor);
                } else {
                    Color customColor2 = new Color(224, 255, 255);
                    cellComponent.setBackground(customColor2);
                }
            }
            return cellComponent;
        }
    }
    String[][] data = {
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"},
            {"1", "1", "1", "1","1"}


    };
    SpringLayout springLayout = new SpringLayout();
    JPanel ClassStudentsTopPanel = new JPanel();
    JPanel ClassStudentsBottomPanel = new JPanel();//底部放置按钮的面板
    JPanel ClassStudentsPanel1 = new JPanel();//中间卡片布局的面板
    JPanel ClassStudentsPanel = new JPanel(springLayout);//老师查看班级学生的面板
    DefaultTableModel model = new DefaultTableModel();
    JTable tableOfStudents = new JTable();//tableOfStudents是展示本班学生的表格
    JLabel ClassLabel = new JLabel("本班学生");

    JButton backBtn = new JButton("退出");

    public AdminShowClassesStuUI() {
        super("选课系统");
        String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名","删除学生"};

        model.setDataVector(data, columnNames);
        tableOfStudents.setModel(model);

        tableOfStudents.setRowHeight(30);
        JTableHeader tab_header = tableOfStudents.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        tableOfStudents.getColumnModel().getColumn(4).setCellRenderer(new AdminDeleteStuTableCellRendererButton());
        tableOfStudents.getColumnModel().getColumn(4).setCellEditor(new AdminDeleteStuTableCellEditorButton());
        JScrollPane scrollPane = new JScrollPane(tableOfStudents);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        tableOfStudents.setDefaultRenderer(Object.class, new AdminShowClassesStuUI.TableBackgroundColorRenderer());
        Container contentPane = getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(ClassStudentsTopPanel, BorderLayout.NORTH);
        contentPane.add(ClassStudentsBottomPanel, BorderLayout.SOUTH);
        contentPane.add(ClassStudentsPanel1, BorderLayout.CENTER);

        ClassStudentsPanel1.setLayout(cardLayout);//卡片式布局
        ClassStudentsPanel1.add(ClassStudentsPanel, "ClassPanel");
        Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体

        tableOfStudents.setFont(centerFont);
        ClassLabel.setFont(new Font("楷体",Font.PLAIN,40));
        backBtn.setPreferredSize(new Dimension(100, 40));
        backBtn.setFont(centerFont);
        ClassStudentsTopPanel.add(ClassLabel);
        ClassStudentsBottomPanel.add(backBtn);
        ClassStudentsPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 70, SpringLayout.NORTH, ClassStudentsPanel);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, ClassStudentsPanel);

        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }

}
