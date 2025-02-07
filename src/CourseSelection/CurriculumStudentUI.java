package view.CourseSelection;

import view.Global.GlobalData;
import view.Global.*;
import view.connect.CourseSelectClientAPI;
import view.connect.CourseSelectClientAPIImp;

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


/**
 * 选课系统学生界面
 */
public class CurriculumStudentUI extends JFrame {
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
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelChosen = new DefaultTableModel();
    /**

     创建了一个定制的 JTable 子类对象 Allcoursetable，用于显示课程信息，并将单元格设置为透明。 / JTable Allcoursetable = new JTable() { /*
     准备渲染器来渲染指定的单元格。


     */
    JTable Allcoursetable = new JTable();//展示课程列表的表格
    String[][] allcoursedata;
    JTable Chosentable = new JTable();//展示已选课程的表格
    String[][] courseclassdata = {
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
    };
    String[][] selectclassdata={
            {"2","2","2","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
    };
    /**

     创建了一个定制的 JPanel 子类对象 TopPanel，用于显示带有背景图片的面板。
     */
    JPanel TopPanel=new JPanel(){
        /**
        绘制组件的背景图片和透明度。

        @param g 绘图上下文。
        */
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
    /**

     创建了一个定制的 JPanel 子类对象 BottomPanel，用于显示带有背景图片的面板。
     */
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
    /**

     创建了一个定制的 JPanel 子类对象 CoursesPanel，用于显示带有背景图片的面板。
     */
    JPanel CoursesPanel=new JPanel(springLayout){
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
    };;//学生查看课程列表的面板
    /**

     创建了一个定制的 JPanel 子类对象 ChosenPanel，用于显示带有背景图片的面板。
     */
    JPanel ChosenPanel=new JPanel(springLayout){
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
    JButton CoursesBtn=new JButton("查看课程列表");

    JButton ChosenBtn=new JButton("查看已选课程");//同上

    JButton backBtn=new JButton("退出");//同上

    public String id;
    public String selectedcourseid; //被选中课程的id
    /**

     将 Course 对象数组转换为二维字符串数组的方法。
     @param courses Course 对象数组。
     @return 转换后的二维字符串数组，其中每一行对应一个 Course 对象的属性信息。 */
    public String[][] coursestostring (Course[] courses) throws IOException {
        String[][] scourse=new String[courses.length][6];
        for(int i=0;i<courses.length;i++){
            scourse[i][0]=courses[i].getCourseID();
            scourse[i][1]=courses[i].getCourseName();
            CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
            int num=clientAPI.GetClassNumByCourseID(courses[i].getCourseID());
            scourse[i][2]=Integer.toString(num);
            scourse[i][3]=Double.toString(courses[i].getCourseTime());
            scourse[i][4]=courses[i].getCourseType().name();
            scourse[i][5]=Double.toString(courses[i].getCredit());
        }
        return scourse;
    }
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
    /**

     自定义的 TableCellRenderer 接口实现类 DeleteClassTableCellRendererButton，

     用于在查看班级界面中显示 "退选" 按钮的渲染器。
     */
    class DeleteClassTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类


        /**

         获取用于渲染单元格的组件。
         @param table 目标 JTable 对象。
         @param value 单元格的值。
         @param isSelected 单元格是否被选中。
         @param hasFocus 单元格是否获得焦点。
         @param row 单元格所在的行数。
         @param column 单元格所在的列数。
         @return 用于渲染单元格的组件。 */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("退选");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            //button.setForeground(table.getSelectionForeground());

            return button;
        }

    }
    /**

     DeleteClassTableCellEditorButton 是一个辅助类，用于处理查看班级界面中的按钮事件。

     它继承自 DefaultCellEditor，用于处理单元格编辑器的行为。
     */
    class DeleteClassTableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        /**

         创建一个 DeleteClassTableCellEditorButton 实例。
         在构造函数中设置按钮的属性和事件监听器。 */
        public DeleteClassTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("退选");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);

            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    //这里要从该学生的选课列表中删除该课程
                    JButton clickedButton = (JButton) e.getSource();
                    CourseSelectClientAPI courseSelectClientAPI9=new CourseSelectClientAPIImp("localhost",8888);
                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    String classid=selectclassdata[clickedRow][0];
                    try {
                        courseSelectClientAPI9.QuitClassByinfo(id,classid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    int tar = 0;
                    for(int i=0;i<selectclassdata.length;i++){
                        if(selectclassdata[i][0]==classid){
                            tar=i;
                            break;
                        }
                    }
                    String[][] newselectclass = new String[selectclassdata.length - 1][];
                    for (int i = 0, j = 0; i < selectclassdata.length; i++) {
                        if (i != tar) {
                            newselectclass[j++] = selectclassdata[i];
                        }
                    }
                    String[] columnNamesChosen ={"课程班编号","教师编号","上课地点","上课时间","退选"};
                    selectclassdata=newselectclass;
                    DefaultTableModel newmodel=new DefaultTableModel (newselectclass,columnNamesChosen);
                    Chosentable.setModel(newmodel);
                    Chosentable.getColumnModel().getColumn(4).setCellRenderer(new DeleteClassTableCellRendererButton());
                    Chosentable.getColumnModel().getColumn(4).setCellEditor(new DeleteClassTableCellEditorButton());
                    System.out.println("点击的行索引：" + clickedRow);

                }
            });
        }
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            clickedRow = row;
            btn.putClientProperty("row", row); // 将行索引保存为按钮的客户端属性
            //btn.setForeground(table.getSelectionForeground());

            return btn;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }


    }
    class ShowCoursesTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("展开");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }
    class ShowCoursesTableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        public ShowCoursesTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("展开");
            Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    System.out.println("点击的行索引：" + clickedRow);
                    selectedcourseid= allcoursedata[clickedRow][0];
                    String couID= allcoursedata[clickedRow][0];
                    CourseSelectClientAPI clientAPI2=new CourseSelectClientAPIImp("localhost",8888);
                    CourseClass[] classes=null;
                    try {
                        classes=clientAPI2.SearchClassByCourseID(couID);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    /*Class[] classes=new Class[2];
                    String[] testid={"555","888"};
                    CourseClass[] courseClass=new CourseClass[2];
                    courseClass[0]=new CourseClass("1","321","陈","J1-101",45,40,"8:00",testid);
                    courseClass[1]=new CourseClass("2","321","陈","J1-101",45,40,"8:00",testid);*/
                    String[][] src=null;
                    if(classes!=null) {
                        src = classtostring(classes);
                    }
                    courseclassdata =src;
                    ShowClassUI showClassUI=new ShowClassUI();
                    showClassUI.setVisible(true);

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
     * 选课系统学生界面
     * @throws IOException
     */
    public CurriculumStudentUI() throws IOException {
        super("选课系统");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryStudentTeacherUI();
            }
        });
        String[] columnNames ={"课程编号","课程号","教学班个数","课程学时","课程类型","学分","显示教学班"};
        String[] columnNamesChosen ={"课程班编号","教师编号","上课地点","上课时间","退选"};
        //String ID=GlobalData.getUID();//未完成
        id= GlobalData.getUID();//未完成
        CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost", 8888);
        Course[] courses=clientAPI.GetAllCourse();
        /*Course[] courses=new Course[2];
        courses[0]=new Course("123","123", Course.CourseType.Optional,3.0,4,99);
        courses[1]=new Course("321","321", Course.CourseType.Optional,3.0,4,99);
        */
        String[][] src=coursestostring(courses);
        CourseSelectClientAPI clientAPI1=new CourseSelectClientAPIImp("localhost",8888);
        CourseClass[] classes=clientAPI1.SearchSelectClassByID(id);
        if(classes==null)
            selectclassdata= new String[][]{
                    {"", "", "", "", "", "",""}
            };
        else selectclassdata=classtostring(classes);
        allcoursedata =src;
        model.setDataVector(allcoursedata, columnNames);
        Allcoursetable.setModel(model);
        Allcoursetable.setRowHeight(30);
        Allcoursetable.setOpaque(false);
        Allcoursetable.setDefaultRenderer(Object.class, renderer);
        JTableHeader tab_header = Allcoursetable.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        Allcoursetable.getColumnModel().getColumn(6).setCellRenderer(new ShowCoursesTableCellRendererButton());
        Allcoursetable.getColumnModel().getColumn(6).setCellEditor(new ShowCoursesTableCellEditorButton());
        Allcoursetable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());

        modelChosen.setDataVector(selectclassdata, columnNamesChosen);
        Chosentable.setModel(modelChosen);
        Chosentable.setRowHeight(30);
        Chosentable.setOpaque(false);
        Chosentable.setDefaultRenderer(Object.class, renderer);
        JTableHeader tab_headerChosen = Chosentable.getTableHeader();					//获取表头
        tab_headerChosen.setFont(new Font("楷体",Font.PLAIN,25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));	//修改表头的高度
        Chosentable.getColumnModel().getColumn(4).setCellRenderer(new DeleteClassTableCellRendererButton());
        Chosentable.getColumnModel().getColumn(4).setCellEditor(new DeleteClassTableCellEditorButton());
        Chosentable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        JScrollPane scrollPane = new JScrollPane(Allcoursetable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JScrollPane scrollPaneChosen = new JScrollPane(Chosentable);
        scrollPaneChosen.setOpaque(false);
        scrollPaneChosen.getViewport().setBackground(new Color(255,255,255,150));
        scrollPaneChosen.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        //loginHandler=new logInHandler(this);

        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(TopPanel, BorderLayout.NORTH);
        contentPane.add(BottomPanel, BorderLayout.SOUTH);
        contentPane.add(panel1, BorderLayout.CENTER);
        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(CoursesPanel, "CoursesPanel");
        panel1.add(ChosenPanel, "ChosenPanel");
        backBtn.setPreferredSize(new Dimension(100, 40));
        CoursesBtn.setPreferredSize(new Dimension(200, 40));
        ChosenBtn.setPreferredSize(new Dimension(200, 40));
        TopPanel.add(CoursesBtn);
        TopPanel.add(ChosenBtn);
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        backBtn.setFont(centerFont);
        CoursesBtn.setFont(centerFont);
        ChosenBtn.setFont(centerFont);
        Allcoursetable.setFont(centerFont);
        Chosentable.setFont(centerFont);

        BottomPanel.add(backBtn);
        CoursesPanel.add(scrollPane);
        ChosenPanel.add(scrollPaneChosen);
        CoursesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"CoursesPanel");
            }
        });
        ChosenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseSelectClientAPI courseSelectClientAPI=new CourseSelectClientAPIImp("localhost",8888);
                CourseClass[] courseClasses=null;
                try {
                    courseClasses=courseSelectClientAPI.SearchSelectClassByID(id);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if(courseClasses!=null)
                    selectclassdata= classtostring(courseClasses);
                String[] columnNamesChosen ={"课程班编号","教师编号","上课地点","上课时间","退选"};
                DefaultTableModel newmodel=new DefaultTableModel(selectclassdata,columnNamesChosen);
                Chosentable.setModel(newmodel);//未完成，按钮
                Chosentable.getColumnModel().getColumn(4).setCellRenderer(new DeleteClassTableCellRendererButton());
                Chosentable.getColumnModel().getColumn(4).setCellEditor(new DeleteClassTableCellEditorButton());
                cardLayout.show(panel1,"ChosenPanel");
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, panel1);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, panel1);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPaneChosen, 60, SpringLayout.NORTH, panel1);
        springLayout.putConstraint(SpringLayout.WEST, scrollPaneChosen, 100, SpringLayout.WEST, panel1);




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
        new CurriculumStudentUI();
    }

    /**
     * 显示教学班的界面
     */
    class ShowClassUI extends JFrame{//显示教学班界面
        class ChooseClassTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                JButton button = new JButton("选课");
                Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
                button.setFont(centerFont);
                return button;
            }

        }

        //选课
        class ChooseClassTableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

            private JButton btn;
            private int clickedRow;
            public ChooseClassTableCellEditorButton() {
                super(new JTextField());
                //设置点击一次就激活，否则默认好像是点击2次激活。
                this.setClickCountToStart(1);
                btn = new JButton("选课");
                Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
                btn.setFont(centerFont);
                btn.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //System.out.println("按钮事件触发----");
                        //这里要从该学生的选课列表中添加该课程，注意课程时间不能冲突，否则应该弹出提示框
                        JButton clickedButton = (JButton) e.getSource();

                        clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                        CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
                        String classid=courseclassdata[clickedRow][0];
                        try {
                            clientAPI.AddClassByinfo(id,classid);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        System.out.println("点击的行索引：" + clickedRow);

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
        JTable tableOfSelectedCourse = new JTable(){ // 设置jtable的单元格为透明的

            public Component prepareRenderer(TableCellRenderer renderer,

                                             int row, int column) {

                Component c = super.prepareRenderer(renderer, row, column);

                if (c instanceof JComponent) {

                    ((JComponent) c).setOpaque(false);

                }

                return c;

            }

        };;//展示选定课程课程班的表格
        JButton backBtn=new JButton("退出");//同上

        //JPanel TopPanel=new JPanel();//顶部放置标题的面板
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
        };;//底部放置按钮的面板
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
        JPanel panel1=new JPanel(){
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
        };;//中间的面板
        JLabel title=new JLabel("教学班");
        public ShowClassUI(){
            super("学生学籍系统");

            String[] columnNames ={"课程班编号","教师编号","上课地点","上课时间","班级人数","课程容量","选课"};
            model.setDataVector(courseclassdata, columnNames);
            tableOfSelectedCourse.setModel(model);
            tableOfSelectedCourse.setRowHeight(30);
            tableOfSelectedCourse.setOpaque(false);
            tableOfSelectedCourse.setDefaultRenderer(Object.class, renderer);
            JTableHeader tab_header = tableOfSelectedCourse.getTableHeader();					//获取表头
            tab_header.setFont(new Font("楷体",Font.PLAIN,25));
            tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
            tableOfSelectedCourse.getColumnModel().getColumn(6).setCellRenderer(new ChooseClassTableCellRendererButton());
            tableOfSelectedCourse.getColumnModel().getColumn(6).setCellEditor(new ChooseClassTableCellEditorButton());
            tableOfSelectedCourse.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
            JScrollPane scrollPane = new JScrollPane(tableOfSelectedCourse);
            scrollPane.setPreferredSize(new Dimension(1000, 600)); // 设置滚动面板的大小

            Container contentPane=getContentPane();//获取控制面板
            scrollPane.setOpaque(false);
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setBackground(new Color(255,255,255,150));
            contentPane.setLayout(new BorderLayout());
            contentPane.setLayout(new BorderLayout());
            CardLayout cardLayout=new CardLayout();
            backBtn.setPreferredSize(new Dimension(100, 40));
            contentPane.add(TopPanel,BorderLayout.NORTH);
            contentPane.add(BottomPanel,BorderLayout.SOUTH);
            contentPane.add(panel1,BorderLayout.CENTER);
            TopPanel.add(title);
            BottomPanel.add(backBtn);
            panel1.add(scrollPane);
            springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 60, SpringLayout.NORTH, panel1);
            springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, panel1);
            Font centerFont=new Font("楷体",Font.PLAIN,40);//设置中间组件的文字大小、字体
            title.setFont(centerFont);
            Font centerFont2=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
            tableOfSelectedCourse.setFont(centerFont2);
            backBtn.setFont(centerFont2);
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
                        Color customColor = new Color(230, 255, 255);
                        cellComponent.setBackground(customColor);
                    } else {
                        Color customColor2 = new Color(240, 248, 255);
                        cellComponent.setBackground(customColor2);
                    }
                }
                return cellComponent;
            }
        }

    }

}





