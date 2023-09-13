package view.SchoolRolls;

import view.CourseSelection.CourseClass;
import view.Global.GlobalData;
import view.Global.SummaryUI;
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
public class StudentStatusTeacherUI extends JFrame {//老师登录进学生学籍管理系统，看见自己的所有教学班，可以点击每行最后的按钮，显示本教学班学生，并登记成绩
    // 课程班编号
    private String[] classIDs;
    private String[] classNums;     // 课程编号
    private String[] classTeachers;   // 任课教师ID
    private String[] classPlaces;     // 上课地点
    private String[] classMaxs;          // 最大人数
    private String[] classTemps;         // 当前人数
    private String[] classTimes;      // 上课时间
    private String[][] classStudents; // 选本班级的学生ID
    private String selectedstuID;
    private String selectcourseID;
    private String selectclassID;
    public String[] getClassIDs() {
        return classIDs;
    }

    public void setClassIDs(String[] classIDs) {
        this.classIDs = classIDs;
    }

    public String[] getClassNums() {
        return classNums;
    }

    public void setClassNums(String[] classNums) {
        this.classNums = classNums;
    }

    public String[] getClassTeachers() {
        return classTeachers;
    }

    public void setClassTeachers(String[] classTeachers) {
        this.classTeachers = classTeachers;
    }

    public String[] getClassPlaces() {
        return classPlaces;
    }

    public void setClassPlaces(String[] classPlaces) {
        this.classPlaces = classPlaces;
    }

    public String[] getClassMaxs() {
        return classMaxs;
    }

    public void setClassMaxs(String[] classMaxs) {
        this.classMaxs = classMaxs;
    }

    public String[] getClassTemps() {
        return classTemps;
    }

    public void setClassTemps(String[] classTemps) {
        this.classTemps = classTemps;
    }

    public String[] getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(String[] classTimes) {
        this.classTimes = classTimes;
    }

    public String[][] getClassStudents() {
        return classStudents;
    }

    public void setClassStudents(String[][] classStudents) {
        this.classStudents = classStudents;
    }

    String[][] coursedata = {};
    String[][] studentdata = {};

    SpringLayout springLayout=new SpringLayout();
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel ClassPanel=new JPanel(springLayout);//老师查看所教班级的面板
    DefaultTableModel model = new DefaultTableModel();
    JTable coursetable = new JTable();
    JTable studentable = new JTable();
    JButton ClassBtn=new JButton("查看教学班");

    JButton backBtn=new JButton("退出");
    class ClassStudentsUI extends JFrame {//显示本班学生界面
        SpringLayout springLayout = new SpringLayout();
        JPanel ClassStudentsTopPanel = new JPanel();
        JPanel ClassStudentsBottomPanel = new JPanel();//底部放置按钮的面板
        JPanel ClassStudentsPanel1 = new JPanel();//中间卡片布局的面板
        JPanel ClassStudentsPanel = new JPanel(springLayout);//老师查看班级学生的面板
        DefaultTableModel model = new DefaultTableModel();
        JLabel ClassLabel = new JLabel("本班学生");

        JButton backBtn = new JButton("退出");

        public ClassStudentsUI(StudentInfo[] studentInfos) throws IOException {
            super("学生学籍系统");

            String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名", "成绩", "登记或修改成绩"};
            //studentinfo转字符串
            String[][] data=new String[studentInfos.length][5];
            for(int i=0;i<studentInfos.length;i++){
                data[i][0]=selectclassID;
                data[i][1]=studentInfos[i].getID();
                data[i][2]=studentInfos[i].getCardID();
                data[i][3]=studentInfos[i].getName();
                InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                double temp=infoClientAPI.FindGradeByInfo(studentInfos[i].getCardID(),selectcourseID);
                data[i][4]=Double.toString(temp);
            }
            studentdata=data;
            model.setDataVector(studentdata, columnNames);
            studentable.setModel(model);
            studentable.setModel(model);
            studentable.setRowHeight(30);
            JTableHeader tab_header = studentable.getTableHeader();					//获取表头
            tab_header.setFont(new Font("楷体",Font.PLAIN,25));
            tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
            studentable.setFont(new Font("楷体",Font.PLAIN,25));
            studentable.getColumnModel().getColumn(5).setCellRenderer(new ClassTableCellRendererButton());
            studentable.getColumnModel().getColumn(5).setCellEditor(new ClassTableCellEditorButton());

            //       JScrollPane scrollPane = new JScrollPane(table);
//        ClassPanel.add(scrollPane);
//        ClassPanel.setBounds(100, 100, 300, 200);
//        ClassPanel.setVisible(true);
            JScrollPane scrollPane = new JScrollPane(studentable);
            scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

            Container contentPane = getContentPane();//获取控制面板

            contentPane.setLayout(new BorderLayout());
            CardLayout cardLayout = new CardLayout();
            contentPane.add(ClassStudentsTopPanel, BorderLayout.NORTH);
            contentPane.add(ClassStudentsBottomPanel, BorderLayout.SOUTH);
            contentPane.add(ClassStudentsPanel1, BorderLayout.CENTER);

            ClassStudentsPanel1.setLayout(cardLayout);//卡片式布局
            ClassStudentsPanel1.add(ClassStudentsPanel, "ClassPanel");
            Font centerFont = new Font("楷体", Font.PLAIN, 40);//设置中间组件的文字大小、字体

            ClassLabel.setFont(new Font("楷体",Font.PLAIN,30));
            backBtn.setPreferredSize(new Dimension(100, 40));
            backBtn.setFont(new Font("楷体",Font.PLAIN,25));
            ClassStudentsTopPanel.add(ClassLabel);
            ClassStudentsBottomPanel.add(backBtn);
            ClassStudentsPanel.add(scrollPane);

            springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, ClassStudentsPanel);
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
    }
    class AddScore extends JFrame{//登记成绩界面
        SpringLayout springLayout=new SpringLayout();
        JPanel centerPanel=new JPanel(springLayout);
        JPanel southPanel=new JPanel();
        JLabel ScoreLabel=new JLabel("成绩");
        JButton EnsureBtn=new JButton("确定");
        public JTextField getScoreTxt() {
            return ScoreTxt;
        }

        public void setScoreTxt(JTextField scoreTxt) {
            ScoreTxt = scoreTxt;
        }

        JTextField ScoreTxt=new JTextField();

        //第二个ui
        public AddScore(){
            super("登记或修改学生成绩");
            Container contentPane=getContentPane();//获取控制面板
            contentPane.add(southPanel,BorderLayout.SOUTH);
            contentPane.add(centerPanel,BorderLayout.CENTER);
            ScoreLabel.setFont(new Font("楷体",Font.PLAIN,20));//设置标题大小、字体
            ScoreTxt.setPreferredSize(new Dimension(200,25));
            EnsureBtn.setPreferredSize(new Dimension(100,30));
            EnsureBtn.setFont(new Font("楷体",Font.PLAIN,20));
            southPanel.add(EnsureBtn);
            centerPanel.add(ScoreLabel);
            centerPanel.add(ScoreTxt);
            Spring childWidth3=Spring.sum(Spring.sum(Spring.width(ScoreLabel),Spring.width(ScoreTxt)),Spring.constant(0));
            //  userNameLabel长度+userNameTxt长度+20
            int offsetX3=childWidth3.getValue()/2;
            springLayout.putConstraint(SpringLayout.WEST,ScoreLabel,-offsetX3,SpringLayout.HORIZONTAL_CENTER,centerPanel);
            springLayout.putConstraint(SpringLayout.NORTH,ScoreLabel,20,SpringLayout.NORTH,centerPanel);
            springLayout.putConstraint(SpringLayout.EAST,ScoreTxt,offsetX3,SpringLayout.HORIZONTAL_CENTER,centerPanel);
            springLayout.putConstraint(SpringLayout.NORTH,ScoreTxt,20,SpringLayout.NORTH,centerPanel);
            //springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,0,SpringLayout.HORIZONTAL_CENTER,southPanel);

            //确定按钮
            EnsureBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {//此处要增加点击确定以后，在表格对应处显示已经修改的学生成绩
                    //System.out.println("按钮事件触发----");
                    String Spoint=getScoreTxt().getText();
                    double point=Double.parseDouble(Spoint);
                    Grade grade=new Grade(selectedstuID,selectcourseID,point);
                    InfoClientAPI infoClientAPI6=new InfoClientAPIImp("localhost",8888);
                    try {
                        infoClientAPI6.ModifyGrade(grade);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    for(int i = 0; i< studentdata.length; i++){
                        if(studentdata[i][2]==selectedstuID){
                            studentdata[i][4]=Double.toString(point);
                        }
                    }
                    String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名", "成绩","登记或修改成绩"};
                    DefaultTableModel newmodel =new DefaultTableModel(studentdata,columnNames);
                    studentable.setModel(newmodel);
                    studentable.getColumnModel().getColumn(5).setCellRenderer(new ClassTableCellRendererButton());
                    studentable.getColumnModel().getColumn(5).setCellEditor(new ClassTableCellEditorButton());
                    //修改成绩按钮，未完成
                    dispose();

                }
            });

            setSize(400,150);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setVisible((true));
        }
    }
    class TableCellRendererButton implements TableCellRenderer{//查看班级界面辅助类
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("查看");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }

    //第一个table 查看
    class TableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        //查看
        public TableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("查看");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    selectclassID= coursetable.getValueAt(clickedRow,1).toString();
                    selectcourseID =coursetable.getValueAt(clickedRow,0).toString();
                    //第二个请求
                    InfoClientAPI infoClientAPI4=new InfoClientAPIImp("localhost",8888);
                    StudentInfo[] studentInfos= null;
                    try {
                        studentInfos = infoClientAPI4.SearchStudentByClassID(selectclassID);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("点击的行索引：" + clickedRow);
                    if(studentInfos==null) {
                        JOptionPane.showMessageDialog(panel1, "该班级为空");
                    }
                    else{
                        ClassStudentsUI classStudentsUI = null;
                        try {
                            classStudentsUI = new ClassStudentsUI(studentInfos);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        classStudentsUI.setVisible(true);
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
    class ClassTableCellRendererButton implements TableCellRenderer{//查看学生界面辅助类
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("修改成绩");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }

    //第二个table 修改成绩
    class ClassTableCellEditorButton extends DefaultCellEditor{//查看学生界面辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        public ClassTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("修改成绩");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    //修改成绩，未完成
                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    String stuID= studentable.getValueAt(clickedRow,2).toString();
                    selectedstuID=stuID;
                    System.out.println("点击的行索引：" + clickedRow);
                    AddScore addScore=new AddScore();
                    addScore.setVisible(true);

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

    //第一个ui
    public StudentStatusTeacherUI() throws IOException {
        super("学生学籍系统");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryUI();
            }
        });
        InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost", 8888);
        //第一个请求
        String[] columnNames ={"课程编号","课程班编号","上课地点","当前班级人数","上课时间","本班学生"};
        String ID= GlobalData.getUID();//全局变量，未完成
        CourseClass[] classes=infoClientAPI.SearchCourseClassByTeacherID(ID);
        /*
        String[] IDs = {"123", "321"};
        CourseClass[] classes = new CourseClass[2];
        classes[0] = new CourseClass("123", "123", "123", "Room101", 40, 32, "10:00AM-12:00PM", IDs);
        classes[1] = classes[0];*/
        classNums =new String[classes.length];
        classIDs=new String[classes.length];
        classStudents=new String[classes.length][100];
        classMaxs=new String[classes.length];
        classPlaces=new String[classes.length];
        classTemps=new String[classes.length];
        classTeachers=new String[classes.length];
        classTimes=new String[classes.length];
        //
        for(int i=0;i<classes.length;i++){
            classTeachers[i]=classes[i].getClassTeacher();
            classPlaces[i]=classes[i].getClassPlace();
            classMaxs[i]=Integer.toString(classes[i].getClassMax());
            classTimes[i]=classes[i].getClassTime();
            classTemps[i]=Integer.toString(classes[i].getClassTemp());
            classIDs[i]=classes[i].getClassID();
            classNums[i]=classes[i].getCourseID();
            classStudents[i]=classes[i].getClassStudent();
        }
        coursedata =new String[classes.length][5];
        for (int i = 0; i < classes.length; i++) {
            coursedata[i][0]=classNums[i];
            coursedata[i][1]=classIDs[i];
            coursedata[i][2]=classPlaces[i];
            coursedata[i][3]=classTemps[i];
            coursedata[i][4]=classTimes[i];
        }
        model.setDataVector(coursedata, columnNames);
        coursetable.setModel(model);
        coursetable.setModel(model);
        coursetable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        coursetable.setRowHeight(30);
        JTableHeader tab_header = coursetable.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        coursetable.getColumnModel().getColumn(5).setCellRenderer(new TableCellRendererButton());
        coursetable.getColumnModel().getColumn(5).setCellEditor(new TableCellEditorButton());

        //       JScrollPane scrollPane = new JScrollPane(table);
//        ClassPanel.add(scrollPane);
//        ClassPanel.setBounds(100, 100, 300, 200);
//        ClassPanel.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(coursetable);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        Container contentPane=getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout=new CardLayout();
        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);

        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(ClassPanel,"ClassPanel");
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        coursetable.setFont(centerFont);
        ClassBtn.setFont(centerFont);
        backBtn.setFont(centerFont);
        ClassBtn.setPreferredSize(new Dimension(200,40));
        backBtn.setPreferredSize(new Dimension(100,40));

        TopPanel.add(ClassBtn);
        BottomPanel.add(backBtn);
        ClassPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,60,SpringLayout.NORTH,ClassPanel);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,ClassPanel);
        ClassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(panel1,"ClassPanel");
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
                    Color customColor = new Color(255, 255, 240);
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
            UIManager.put("nimbusBase", new Color(255, 218, 185)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(255, 228, 181)); // 按钮
            UIManager.put("control", new Color(255, 248, 220)); // 背景



        } catch (Exception e) {
            e.printStackTrace();
        }
        new StudentStatusTeacherUI();
    }
}
