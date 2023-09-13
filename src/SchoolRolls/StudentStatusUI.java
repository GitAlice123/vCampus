package view.SchoolRolls;

import view.CourseSelection.Course;
import view.Global.GlobalData;
import view.Global.*;
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
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentStatusUI extends JFrame {
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
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
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
    String card_id;

    public void setId(String id) {
        this.id = id;
    }

    String id= GlobalData.getUID();

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
    JPanel TopPanel=new JPanel(){
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
    };;//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel(){
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
    };;//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel InfoPanel=new JPanel(springLayout){
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
    };;//学生查看个人信息的面板
    JPanel CoursePanel=new JPanel(springLayout){
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
    };;//学生查看成绩的面板

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
    JButton FindBtn=new JButton("查询");

    public JTextField getFindTex() {
        return FindTex;
    }

    JTextField FindTex=new JTextField();//查询课程编号或名称输入框


    public void setSpringLayout(SpringLayout springLayout) {
        this.springLayout = springLayout;
    }
    public String[][] coursestostring (Course[] courses){
            String[][] scourse=new String[courses.length][6];
        for(int i=0;i<courses.length;i++){
            scourse[i][0]=courses[i].getCourseID();
            scourse[i][1]=courses[i].getCourseName();
            scourse[i][2]=courses[i].getCourseType().name();
            scourse[i][3]=Double.toString(courses[i].getCourseTime());
            scourse[i][4]=Double.toString(courses[i].getCredit());
            scourse[i][5]=Double.toString(courses[i].getGrades());
        }
        return scourse;
    }
    public StudentStatusUI() throws IOException {
        super("学生学籍系统");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });
        StudentInfo info=new StudentInfo("15177","54321","male","jacky",new Date(0),21,"CS");
        InfoClientAPI infoClientAPI1=new InfoClientAPIImp("localhost", 8888);
        boolean result=infoClientAPI1.AddStuInfo(info);
        InfoClientAPI infoClientAPI2=new InfoClientAPIImp("localhost", 8888);
        StudentInfo tar=infoClientAPI2.SearchStuInfoByID(GlobalData.getUID());
        if(tar!=null) {
            setCard_id(tar.getCardID());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String BirthString = sdf.format(tar.getBirth());
            setBirth(BirthString);
            setCollege(tar.getCollege());
            setId(tar.getID());
            setName(tar.getName());
            setSex(tar.getSex());
            setGrade(Integer.toString(tar.getGrade()));
        }
        else {
            setCard_id("未录入");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            setBirth("未录入");
            setCollege("未录入");
            setId("未录入");
            setName("未录入");
            setSex("未录入");
            setGrade("未录入");
        }
        // 创建一个二维数组作为表格数据
        JLabel Card_idLabelOut=new JLabel(card_id);
        JLabel IdLabelOut=new JLabel(id);
        JLabel NameLabelOut=new JLabel(name);
        JLabel SexLabelOut=new JLabel(sex);
        JLabel BirthLabelOut=new JLabel(birth);
        JLabel GradeLabelOut=new JLabel(grade);
        JLabel CollegeLabelOut=new JLabel(college);
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
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model){ // 设置jtable的单元格为透明的

            public Component prepareRenderer(TableCellRenderer renderer,

                                             int row, int column) {

                Component c = super.prepareRenderer(renderer, row, column);

                if (c instanceof JComponent) {

                    ((JComponent) c).setOpaque(false);

                }

                return c;

            }

        };;
        table.setRowHeight(30);
        table.setOpaque(false);
        table.setDefaultRenderer(Object.class, renderer);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        // 创建一个JScrollPane来包装表格
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JScrollPane scrollPaneChosen = new JScrollPane(table);
        scrollPaneChosen.setOpaque(false);

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
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        table.setFont(centerFont);
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
        InfoBtn.setFont(centerFont);
        CourseBtn.setFont(centerFont);
        backBtn.setFont(centerFont);
        FindBtn.setFont(centerFont);
        FindTex.setFont(centerFont);

        InfoBtn.setPreferredSize(new Dimension(200,40));//设置按钮大小
        CourseBtn.setPreferredSize(new Dimension(200,40));
        backBtn.setPreferredSize(new Dimension(100,40));
        FindBtn.setPreferredSize(new Dimension(100,40));
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

        FindTex.setPreferredSize(new Dimension(200,40));//设置输入框大小
        //springLayout.putConstraint(SpringLayout.EAST,backBtn,10,SpringLayout.EAST,BottomPanel);
        springLayout.putConstraint(SpringLayout.NORTH,FindBtn,40,SpringLayout.NORTH,CoursePanel);
        springLayout.putConstraint(SpringLayout.WEST,FindTex,100,SpringLayout.WEST,CoursePanel);
        springLayout.putConstraint(SpringLayout.NORTH,FindTex,40,SpringLayout.NORTH,CoursePanel);
        springLayout.putConstraint(SpringLayout.WEST,FindBtn,10,SpringLayout.EAST,FindTex);
//        Spring childWidth0=Spring.sum(Spring.width(CoursePanel),Spring.minus(Spring.width(scrollPane)));
//        int offsetX0=childWidth0.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,30,SpringLayout.SOUTH,FindTex);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,100,SpringLayout.WEST,CoursePanel);

        Spring childWidth=Spring.sum(Spring.sum(Spring.width(Card_idLabel),Spring.width(Card_idLabelOut)),
                Spring.constant(0));
        int offsetX=childWidth.getValue()/2;
        springLayout.putConstraint(SpringLayout.NORTH,Card_idLabel,110,SpringLayout.NORTH,InfoPanel);
        springLayout.putConstraint(SpringLayout.NORTH,Card_idLabelOut,110,SpringLayout.NORTH,InfoPanel);
        springLayout.putConstraint(SpringLayout.EAST,Card_idLabel,-offsetX+10,SpringLayout.HORIZONTAL_CENTER,InfoPanel);
        springLayout.putConstraint(SpringLayout.WEST,Card_idLabelOut,offsetX-10,SpringLayout.HORIZONTAL_CENTER,InfoPanel);

        springLayout.putConstraint(SpringLayout.NORTH,IdLabel,30,SpringLayout.SOUTH,Card_idLabel);
        springLayout.putConstraint(SpringLayout.EAST,IdLabel,0,SpringLayout.EAST,Card_idLabel);
        springLayout.putConstraint(SpringLayout.NORTH,IdLabelOut,30,SpringLayout.SOUTH,Card_idLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,IdLabelOut,0,SpringLayout.WEST,Card_idLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,NameLabel,30,SpringLayout.SOUTH,IdLabel);
        springLayout.putConstraint(SpringLayout.EAST,NameLabel,0,SpringLayout.EAST,IdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,NameLabelOut,30,SpringLayout.SOUTH,IdLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,NameLabelOut,0,SpringLayout.WEST,IdLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,SexLabel,30,SpringLayout.SOUTH,NameLabel);
        springLayout.putConstraint(SpringLayout.EAST,SexLabel,0,SpringLayout.EAST,NameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,SexLabelOut,30,SpringLayout.SOUTH,NameLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,SexLabelOut,0,SpringLayout.WEST,NameLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,BirthLabel,30,SpringLayout.SOUTH,SexLabel);
        springLayout.putConstraint(SpringLayout.EAST,BirthLabel,0,SpringLayout.EAST,SexLabel);
        springLayout.putConstraint(SpringLayout.NORTH,BirthLabelOut,30,SpringLayout.SOUTH,SexLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,BirthLabelOut,0,SpringLayout.WEST,SexLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,GradeLabel,30,SpringLayout.SOUTH,BirthLabel);
        springLayout.putConstraint(SpringLayout.EAST,GradeLabel,0,SpringLayout.EAST,BirthLabel);
        springLayout.putConstraint(SpringLayout.NORTH,GradeLabelOut,30,SpringLayout.SOUTH,BirthLabelOut);
        springLayout.putConstraint(SpringLayout.WEST,GradeLabelOut,0,SpringLayout.WEST,BirthLabelOut);

        springLayout.putConstraint(SpringLayout.NORTH,CollegeLabel,30,SpringLayout.SOUTH,GradeLabel);
        springLayout.putConstraint(SpringLayout.EAST,CollegeLabel,0,SpringLayout.EAST,GradeLabel);
        springLayout.putConstraint(SpringLayout.NORTH,CollegeLabelOut,30,SpringLayout.SOUTH,GradeLabelOut);
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
                String ID=GlobalData.getUID();//全局变量，未测试
                InfoClientAPI infoClientAPI3=new InfoClientAPIImp("localhost",8888);
                Course[] courses=null;
                try {
                    courses=infoClientAPI3.SearchCourseByID(ID);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //从数据库检索，未完成（ID检索Grade[]，Grade中的CourseID检索Course
                //数据库连接
                //courses[0]=new Course("123","123", Course.CourseType.Optional,3.0,4,99);
                //courses[1]=new Course("123","123", Course.CourseType.Optional,3.0,4,99);
                String[][] scourse={{" "},{" "},{" "},{" "},{" "}};
                if(courses!=null)
                scourse=coursestostring(courses);
                DefaultTableModel newModel = new DefaultTableModel(scourse, columnNames);
                table.setModel(newModel);
                //显示个人信息
                cardLayout.show(panel1,"CoursePanel");
            }
        });
        FindBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String CourseID=FindTex.getText();
                Course[] tarcourse=new Course[1];
                InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                try {
                    tarcourse[0]=infoClientAPI.SearchCourseByinfo(CourseID,GlobalData.getUID());//未完成 “213213000”为globalID
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String src[][];
                if(tarcourse[0]==null){
                    JOptionPane.showMessageDialog(CoursePanel,"该课程不存在");
                }
                //target[0]=infoClientAPI.SearchCourseByCourseID(CourseID);
                //target[0]=new Course("123","123", Course.CourseType.Optional,3.0,4,100);
                src=coursestostring(tarcourse);
                DefaultTableModel newModel = new DefaultTableModel(src, columnNames);
                table.setModel(newModel);
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
        new StudentStatusUI();
    }
}
