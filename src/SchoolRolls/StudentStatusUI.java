package view.SchoolRolls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentStatusUI extends JFrame {
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    String card_id;

    public void setId(String id) {
        this.id = id;
    }

    String id;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;

    public void setSex(String sex) {
        this.sex = sex;
    }

    String sex;

    public void setBirth(String birth) {
        this.birth = birth;
    }

    String birth;

    public void setGrade(String grade) {
        this.grade = grade;
    }

    String grade;

    public void setCollege(String college) {
        this.college = college;
    }

    String college;

    SpringLayout springLayout=new SpringLayout();
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel InfoPanel=new JPanel(springLayout);//学生查看个人信息的面板
    JPanel CoursePanel=new JPanel(springLayout);//学生查看成绩的面板

    //以下为按钮
    JButton InfoBtn=new JButton("查看个人信息");//学生登录后进入学生学籍系统看到的界面里，点击此按钮，中间出现个人信息（已经实现）

    JButton CourseBtn=new JButton("查看成绩");//同上

    JButton backBtn=new JButton("退出");//同上

    JLabel Card_idLabel=new JLabel("一卡通号");
    JLabel IdLabel=new JLabel("学号");
    JLabel NameLabel=new JLabel("姓名");
    JLabel SexLabel=new JLabel("性别");
    JLabel BirthLabel=new JLabel("出身年月");
    JLabel GradeLabel=new JLabel("入学年份");
    JLabel CollegeLabel=new JLabel("学院");

    //以下label中需要显示从数据库调出的信息
    JLabel Card_idLabelOut=new JLabel("card_id");
    JLabel IdLabelOut=new JLabel("id");
    JLabel NameLabelOut=new JLabel("name");
    JLabel SexLabelOut=new JLabel("sex");
    JLabel BirthLabelOut=new JLabel("birth");
    JLabel GradeLabelOut=new JLabel("grade");
    JLabel CollegeLabelOut=new JLabel("college");


    JButton FindBtn=new JButton("查询");

    public JTextField getFindTex() {
        return FindTex;
    }

    JTextField FindTex=new JTextField();//查询课程编号或名称输入框


    public void setSpringLayout(SpringLayout springLayout) {
        this.springLayout = springLayout;
    }

    public StudentStatusUI(){
        super("学生学籍系统");

        // 创建一个二维数组作为表格数据
        //从数据库中读出数据
        String[][] data = {
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
                {"1","1","1","1","1","1"},
                {"1","1","1","1","1","1"},
                {"1","1","1","1","1","1"},
                {"1","1","1","1","1","1"}

        };

        // 创建表格的列名数组
        String[] columnNames = {"课程代码", "课程名称", "课程类型","课程学时","学分","总成绩"};

        // 创建JTable对象并传入数据和列名
        JTable table = new JTable(data, columnNames);

        // 创建一个JScrollPane来包装表格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250)); // 设置滚动面板的大小


        //loginHandler=new logInHandler(this);

        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout=new CardLayout();
        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);

        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(InfoPanel,"InfoPanel");
        panel1.add(CoursePanel,"CoursePanel");
        //TopPanel.setLayout(FlowLayout);//流式布局
        Font centerFont=new Font("楷体",Font.PLAIN,15);//设置中间组件的文字大小、字体
        Card_idLabel.setFont(centerFont);
        Card_idLabelOut.setFont(centerFont);
        IdLabel.setFont(centerFont);
        IdLabelOut.setFont(centerFont);
        NameLabel.setFont(centerFont);
        NameLabelOut.setFont(centerFont);
        SexLabel.setFont(centerFont);
        SexLabelOut.setFont(centerFont);
        BirthLabel.setFont(centerFont);
        BirthLabelOut.setFont(centerFont);
        GradeLabel.setFont(centerFont);
        GradeLabelOut.setFont(centerFont);
        CollegeLabel.setFont(centerFont);
        CollegeLabelOut.setFont(centerFont);

        InfoBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
        CourseBtn.setPreferredSize(new Dimension(150,30));
        backBtn.setPreferredSize(new Dimension(100,30));
//        Card_idLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        Card_idLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        IdLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        IdLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        NameLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        NameLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        SexLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        SexLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        BirthLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        BirthLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        GradeLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        GradeLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        CollegeLabel.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));
//        CollegeLabelOut.setPreferredSize(new Dimension(150, Card_idLabel.getPreferredSize().height));

        TopPanel.add(InfoBtn);
        TopPanel.add(CourseBtn);
        BottomPanel.add(backBtn);
        InfoPanel.add(Card_idLabel);
        InfoPanel.add(Card_idLabelOut);
        InfoPanel.add(IdLabel);
        InfoPanel.add(IdLabelOut);
        InfoPanel.add(NameLabel);
        InfoPanel.add(NameLabelOut);
        InfoPanel.add(SexLabel);
        InfoPanel.add(SexLabelOut);
        InfoPanel.add(BirthLabel);
        InfoPanel.add(BirthLabelOut);
        InfoPanel.add(GradeLabel);
        InfoPanel.add(GradeLabelOut);
        InfoPanel.add(CollegeLabel);
        InfoPanel.add(CollegeLabelOut);
        CoursePanel.add(FindBtn);
        CoursePanel.add(FindTex);
        CoursePanel.add(scrollPane); // 将滚动面板添加到面板中

        FindTex.setPreferredSize(new Dimension(200,20));//设置输入框大小
        //springLayout.putConstraint(SpringLayout.EAST,backBtn,10,SpringLayout.EAST,BottomPanel);
        springLayout.putConstraint(SpringLayout.NORTH,FindBtn,10,SpringLayout.NORTH,CoursePanel);
        springLayout.putConstraint(SpringLayout.WEST,FindTex,10,SpringLayout.WEST,CoursePanel);
        springLayout.putConstraint(SpringLayout.NORTH,FindTex,10,SpringLayout.NORTH,CoursePanel);
        springLayout.putConstraint(SpringLayout.WEST,FindBtn,10,SpringLayout.EAST,FindTex);
//        Spring childWidth0=Spring.sum(Spring.width(CoursePanel),Spring.minus(Spring.width(scrollPane)));
//        int offsetX0=childWidth0.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,10,SpringLayout.SOUTH,FindTex);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,25,SpringLayout.WEST,CoursePanel);

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(Card_idLabel),Spring.width(Card_idLabelOut)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,Card_idLabel,20,SpringLayout.NORTH,InfoPanel);
        springLayout.putConstraint(SpringLayout.NORTH,Card_idLabelOut,20,SpringLayout.NORTH,InfoPanel);
        springLayout.putConstraint(SpringLayout.EAST,Card_idLabel,-offsetX+10,SpringLayout.HORIZONTAL_CENTER,InfoPanel);
        springLayout.putConstraint(SpringLayout.WEST,Card_idLabelOut,offsetX-10,SpringLayout.HORIZONTAL_CENTER,InfoPanel);

        springLayout.putConstraint(SpringLayout.NORTH,IdLabel,20,SpringLayout.SOUTH,Card_idLabel);
        springLayout.putConstraint(SpringLayout.EAST,IdLabel,0,SpringLayout.EAST,Card_idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,IdLabelOut,20,SpringLayout.SOUTH,Card_idLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,IdLabelOut,0,SpringLayout.WEST,Card_idLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NameLabel,20,SpringLayout.SOUTH,IdLabel);
        springLayout.putConstraint(SpringLayout.EAST,NameLabel,0,SpringLayout.EAST,IdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NameLabelOut,20,SpringLayout.SOUTH,IdLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NameLabelOut,0,SpringLayout.WEST,IdLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,SexLabel,20,SpringLayout.SOUTH,NameLabel);
        springLayout.putConstraint(SpringLayout.EAST,SexLabel,0,SpringLayout.EAST,NameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,SexLabelOut,20,SpringLayout.SOUTH,NameLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,SexLabelOut,0,SpringLayout.WEST,NameLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,BirthLabel,20,SpringLayout.SOUTH,SexLabel);
        springLayout.putConstraint(SpringLayout.EAST,BirthLabel,0,SpringLayout.EAST,SexLabel);
        springLayout.putConstraint(SpringLayout.NORTH,BirthLabelOut,20,SpringLayout.SOUTH,SexLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,BirthLabelOut,0,SpringLayout.WEST,SexLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,GradeLabel,20,SpringLayout.SOUTH,BirthLabel);
        springLayout.putConstraint(SpringLayout.EAST,GradeLabel,0,SpringLayout.EAST,BirthLabel);
        springLayout.putConstraint(SpringLayout.NORTH,GradeLabelOut,20,SpringLayout.SOUTH,BirthLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,GradeLabelOut,0,SpringLayout.WEST,BirthLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,CollegeLabel,20,SpringLayout.SOUTH,GradeLabel);
        springLayout.putConstraint(SpringLayout.EAST,CollegeLabel,0,SpringLayout.EAST,GradeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,CollegeLabelOut,20,SpringLayout.SOUTH,GradeLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,CollegeLabelOut,0,SpringLayout.WEST,GradeLabelOut);
        InfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"InfoPanel");
            }
        });
        CourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"CoursePanel");
            }
        });










        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }



    public static void main(String[] args){

        new StudentStatusUI();
    }
}
