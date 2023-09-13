package view.CourseSelection;



import view.Global.GlobalData;
import view.Global.SummaryStudentTeacherUI;
import view.SchoolRolls.StudentInfo;
import view.connect.InfoClientAPI;
import view.connect.InfoClientAPIImp;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CurriculumTeacherUI extends JFrame {
    String id= GlobalData.getUID();//未完成
    String classid;
    String[][] studentdata = {
            {" ", " ", " ", " "},
    };
    public String[][] classtostring(CourseClass[] classes){
        String[][] scourse=new String[classes.length][6];
        for(int i=0;i<classes.length;i++){
            scourse[i][0]=classes[i].getClassID();
            scourse[i][1]=classes[i].getClassTeacher();
            scourse[i][2]=classes[i].getClassPlace();
            scourse[i][3]=classes[i].getClassTime();
            scourse[i][4]=Integer.toString(classes[i].getClassTemp());
            scourse[i][5]=Integer.toString(classes[i].getClassMax());
        }
        return scourse;
    }
    class TeacherTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("查看");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class TeacherTableCellEditorButton extends DefaultCellEditor {
        private JButton btn;
        private int clickedRow;

        public TeacherTableCellEditorButton() {
            super(new JTextField());
            this.setClickCountToStart(1);
            btn = new JButton("查看");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    classid= classdata[clickedRow][0];
                    TeacherClassStudentsUI teacherClassStudentsUI = new TeacherClassStudentsUI();
                    teacherClassStudentsUI.setVisible(true);
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
    JPanel TopPanel=new JPanel();
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel(springLayout);

    DefaultTableModel model = new DefaultTableModel();
    JTable tableOfClasses = new JTable();//显示课程班的表格
    JLabel title=new JLabel("教学班");
    String[][] classdata = {};
    JButton backBtn=new JButton("退出");
    public CurriculumTeacherUI() throws IOException {
        super("选课系统");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });
        String[] columnNames ={"课程班编号","课程名称","上课地点","当前班级人数","上课时间","本班学生"};
        InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
        CourseClass[] classes=infoClientAPI.SearchCourseClassByTeacherID(id);
        if(classes[0]!=null)
        classdata =classtostring(classes);
        model.setDataVector(classdata, columnNames);
        tableOfClasses.setModel(model);
        tableOfClasses.setRowHeight(30);
        JTableHeader tab_header = tableOfClasses.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        tableOfClasses.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        tableOfClasses.getColumnModel().getColumn(5).setCellRenderer(new TeacherTableCellRendererButton());
        tableOfClasses.getColumnModel().getColumn(5).setCellEditor(new TeacherTableCellEditorButton());
        //table.setEnabled(false);

        // 设置特定单元格不可编辑
        //tableModel.setCellEditable(1, 2, false);
        //loginHandler=new logInHandler(this);
        JScrollPane scrollPane = new JScrollPane(tableOfClasses);
        scrollPane.setPreferredSize(new Dimension(1000, 600)); // 设置滚动面板的大小
        Container contentPane=getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());

        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);
        title.setFont(new Font("楷体",Font.PLAIN,40));
        panel1.add(scrollPane);

        tableOfClasses.setFont(new Font("楷体",Font.PLAIN,25));
        backBtn.setPreferredSize(new Dimension(100,40));
        backBtn.setFont(new Font("楷体",Font.PLAIN,25));
        TopPanel.add(title);
        BottomPanel.add(backBtn);


        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,30,SpringLayout.NORTH,panel1);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,panel1);





        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
    class TeacherClassStudentsUI extends JFrame {//显示本班学生界面
        SpringLayout springLayout = new SpringLayout();
        JPanel ClassStudentsTopPanel = new JPanel();
        JPanel ClassStudentsBottomPanel = new JPanel();//底部放置按钮的面板
        JPanel ClassStudentsPanel1 = new JPanel();//中间卡片布局的面板
        JPanel ClassStudentsPanel = new JPanel(springLayout);//老师查看班级学生的面板
        DefaultTableModel model = new DefaultTableModel();
        JTable tableOfStudents = new JTable();//显示本班学生的表格
        JLabel ClassLabel = new JLabel("本班学生");

        JButton backBtn = new JButton("退出");

        public TeacherClassStudentsUI() {
            super("选课系统");

            String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名"};
            InfoClientAPI infoClientAPI3=new InfoClientAPIImp("localhost",8888);
            StudentInfo[] studentInfos=null;
            try {
                studentInfos=infoClientAPI3.SearchStudentByClassID(classid);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            if(studentInfos!=null) {
                String[][] src = new String[studentInfos.length][4];
                for (int i = 0; i < studentInfos.length; i++) {
                    src[i][0] = classid;
                    src[i][1] = studentInfos[i].getID();
                    src[i][2] = studentInfos[i].getCardID();
                    src[i][3] = studentInfos[i].getName();
                }
                studentdata = src;
            }
            model.setDataVector(studentdata, columnNames);
            tableOfStudents.setModel(model);
            tableOfStudents.setRowHeight(30);
            JTableHeader tab_header = tableOfStudents.getTableHeader();					//获取表头
            tab_header.setFont(new Font("楷体",Font.PLAIN,25));
            tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
            tableOfStudents.setFont(new Font("楷体",Font.PLAIN,25));
            tableOfStudents.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
            JScrollPane scrollPane = new JScrollPane(tableOfStudents);
            scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
            tableOfStudents.setEnabled(false);
            Container contentPane = getContentPane();//获取控制面板

            contentPane.setLayout(new BorderLayout());
            CardLayout cardLayout = new CardLayout();
            contentPane.add(ClassStudentsTopPanel, BorderLayout.NORTH);
            contentPane.add(ClassStudentsBottomPanel, BorderLayout.SOUTH);
            contentPane.add(ClassStudentsPanel1, BorderLayout.CENTER);

            ClassStudentsPanel1.setLayout(cardLayout);//卡片式布局
            ClassStudentsPanel1.add(ClassStudentsPanel, "ClassPanel");
            Font centerFont = new Font("楷体", Font.PLAIN, 40);//设置中间组件的文字大小、字体

            ClassLabel.setFont(new Font("楷体",Font.PLAIN,40));
            backBtn.setPreferredSize(new Dimension(100, 40));
            backBtn.setFont(new Font("楷体",Font.PLAIN,25));
            ClassStudentsTopPanel.add(ClassLabel);
            ClassStudentsBottomPanel.add(backBtn);
            ClassStudentsPanel.add(scrollPane);

            springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.NORTH, ClassStudentsPanel);
            springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, ClassStudentsPanel);

            backBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    dispose();

                }
            });
            setSize(1200, 800);
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

    public static void main(String[] args) throws IOException {
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
        new CurriculumTeacherUI();
    }
}

