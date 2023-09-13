package view.CourseSelection;

import view.Global.*;
import view.Login.User;
import view.SchoolRolls.StudentInfo;
import view.connect.CourseSelectClientAPI;
import view.connect.CourseSelectClientAPIImp;
import view.connect.InfoClientAPI;
import view.connect.InfoClientAPIImp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *学生选课模块的管理员界面
 */
public class CurriculumAdminUI extends JFrame {
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        /**
         * 自定义表格单元格渲染器，用于控制表格单元格的外观。
         * 继承自 DefaultTableCellRenderer 类。
         * 在渲染过程中，根据选择状态为表格单元格设置不同的外观样式。
         * 如果单元格被选中，则设置背景颜色为表格的默认背景色，文字颜色为黑色。
         * 如果单元格未被选中，则恢复为默认的背景颜色和文字颜色。
         * 在渲染过程中，调用了父类的 getTableCellRendererComponent 方法以获取默认的单元格渲染器组件，并在其基础上进行了定制。
         * 注意：该渲染器并未修改单元格的编辑性，仅控制外观样式。
         *
         * @param table 表格组件，用于获取默认的背景颜色和前景颜色以及其他相关信息。
         * @param value 单元格的值对象。
         * @param isSelected 当前单元格是否被选中。
         * @param hasFocus 当前单元格是否具有焦点。
         * @param row 单元格所在的行。
         * @param column 单元格所在的列。
         * @return 返回已定制外观样式的单元格渲染器组件。
         */
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
    /**
     * 选中的StudentID
     */
    String studentid;
    /**
     * 选中的ClassID
     */
    String classid;

    /**
     * 选中的CourseID
     */
    String courseid;
    SpringLayout springLayout=new SpringLayout();
    /**
     * 自定义的 JPanel，用于显示顶部面板。
     * 在 paintComponent 方法中绘制带透明度的背景图片。
     */
    JPanel TopPanel=new JPanel(springLayout){
        /**
         * @param g 绘图上下文对象，用于绘制组件的内容。
         */
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
    };;
    /**
     * 自定义的 JPanel，用于显示底部面板。
     * 在 paintComponent 方法中绘制带透明度的背景图片。
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
    /**
     * 自定义的 JPanel，用于显示中间面板。
     * 在 paintComponent 方法中绘制带透明度的背景图片。
     */
    JPanel panel1=new JPanel(springLayout){
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
    };;

    JButton AddNewClassBtn=new JButton("新增课程班");
    DefaultTableModel model = new DefaultTableModel();
    /**
     * 自定义的 JTable，用于设置单元格为透明的。
     * 在 prepareRenderer 方法中重写默认的单元格渲染器，使单元格的背景透明。
     */
    JTable classtable = new JTable(){ // 设置jtable的单元格为透明的
        /**
         * @param renderer 单元格渲染器对象，用于绘制单元格的内容。
         * @param row 单元格所在的行。
         * @param column 单元格所在的列。
         * @return 返回被渲染的组件。
         */
        public Component prepareRenderer(TableCellRenderer renderer,

                                         int row, int column) {

            Component c = super.prepareRenderer(renderer, row, column);

            if (c instanceof JComponent) {

                ((JComponent) c).setOpaque(false);

            }

            return c;

        }

    };;
    JTable studenttable = new JTable(){ // 设置jtable的单元格为透明的

        public Component prepareRenderer(TableCellRenderer renderer,

                                         int row, int column) {

            Component c = super.prepareRenderer(renderer, row, column);

            if (c instanceof JComponent) {

                ((JComponent) c).setOpaque(false);

            }

            return c;

        }

    };;
    JLabel title=new JLabel("课程班");
    String[][] studentdata = {
    };
    String[][] classdata = {
    };
    /**
     * 更新课程班表格。
     *
     * @throws IOException 如果在更新过程中发生 I/O 错误。
     */
    void UpdateClassTable() throws IOException {
        String[] columnNames ={"课程班编号","课程名称","上课地点","最大人数","上课时间","任课教师ID","修改","删除","本班学生"};
        //loginHandler=new logInHandler(this);
        CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
        CourseClass[] courseClasses=clientAPI.GetAllClass();
        classdata=ClasstoString(courseClasses);
        DefaultTableModel model=new DefaultTableModel(classdata,columnNames);
        classtable.setModel(model);
        classtable.setRowHeight(30);
        JTableHeader tab_header = classtable.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        classtable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        classtable.getColumnModel().getColumn(6).setCellRenderer(new AdminChangeClassesTableCellRendererButton());
        classtable.getColumnModel().getColumn(6).setCellEditor(new AdminChangeClassesTableCellEditorButton());
        classtable.getColumnModel().getColumn(7).setCellRenderer(new AdminDeleteClassesTableCellRendererButton());
        classtable.getColumnModel().getColumn(7).setCellEditor(new AdminDeleteClassesTableCellEditorButton());
        classtable.getColumnModel().getColumn(8).setCellRenderer(new AdminShowClassesStuTableCellRendererButton());
        classtable.getColumnModel().getColumn(8).setCellEditor(new AdminShowClassesStuTableCellEditorButton());
    }
    JButton backBtn=new JButton("退出");
    public String[][] StudentInfotoString(StudentInfo[] studentInfos){
        String[][] src=new String[studentInfos.length][4];
        //classid id card_id name
        for(int i=0;i<studentInfos.length;i++){
            src[i][0]=classid;
            src[i][1]=studentInfos[i].getID();
            src[i][2]=studentInfos[i].getCardID();
            src[i][3]=studentInfos[i].getName();
        }
        return src;
    }
    public String[][] ClasstoString(CourseClass[] classes){
        String[][] src=new String[classes.length][6];
        //classid coursenum place max time teacher
        for(int i=0;i<classes.length;i++){
            src[i][0]=classes[i].getClassID();
            src[i][1]=classes[i].getCourseID();
            src[i][2]=classes[i].getClassPlace();
            src[i][3]=Integer.toString(classes[i].getClassMax());
            src[i][4]=classes[i].getClassTime();
            src[i][5]=classes[i].getClassTeacher();
        }
        return src;
    }
    /**
     * 构造选课管理员界面对象。
     *
     * @throws IOException 如果在构造过程中发生 I/O 错误。
     */
    public CurriculumAdminUI() throws IOException {
        super("选课系统");
        JLabel imageLabel = new JLabel();
//        try {
//            // 加载图片
//            int newWidth = 90;  // 新的宽度
//            int newHeight = 100; // 新的高度
//
//            Image pkqIm = ImageIO.read(new File("Images/sdz1.jpeg"));  // 请将 "image.png" 替换为实际的图片路径
//
//            Image scaledImage = pkqIm.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//            imageLabel.setIcon(new ImageIcon(scaledImage));
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String[] columnNames ={"课程班编号","课程名称","上课地点","最大人数","上课时间","任课教师ID","修改","删除","本班学生"};
        //loginHandler=new logInHandler(this);
        CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
        CourseClass[] courseClasses=clientAPI.GetAllClass();
        if(courseClasses!=null)
        classdata=ClasstoString(courseClasses);
        DefaultTableModel model=new DefaultTableModel(classdata,columnNames);
        classtable.setModel(model);
        classtable.setRowHeight(30);
        classtable.setOpaque(false);
        classtable.setDefaultRenderer(Object.class, renderer);
        JTableHeader tab_header = classtable.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        classtable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        classtable.getColumnModel().getColumn(6).setCellRenderer(new AdminChangeClassesTableCellRendererButton());
        classtable.getColumnModel().getColumn(6).setCellEditor(new AdminChangeClassesTableCellEditorButton());
        classtable.getColumnModel().getColumn(7).setCellRenderer(new AdminDeleteClassesTableCellRendererButton());
        classtable.getColumnModel().getColumn(7).setCellEditor(new AdminDeleteClassesTableCellEditorButton());
        classtable.getColumnModel().getColumn(8).setCellRenderer(new AdminShowClassesStuTableCellRendererButton());
        classtable.getColumnModel().getColumn(8).setCellEditor(new AdminShowClassesStuTableCellEditorButton());
        //loginHandler=new logInHandler(this);
        JScrollPane scrollPane = new JScrollPane(classtable);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));
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
        classtable.setFont(centerFont);
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
                AdminAddClassesUI adminAddClassesUI= null;
                try {
                    adminAddClassesUI = new AdminAddClassesUI();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                adminAddClassesUI.setVisible(true);

            }
        });
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
    /**
     * TableCellRenderer 的实现类，用于在表格中渲染 "删除" 按钮。
     * 该按钮用于删除课程班级。
     */
    class AdminDeleteClassesTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类


        /**
         * 返回一个用于绘制表格单元格的组件，该组件为一个 "删除" 按钮。
         *
         * @param table      表格对象。
         * @param value      单元格的值对象。
         * @param isSelected 表示单元格是否为选中状态。
         * @param hasFocus   表示单元格是否拥有焦点。
         * @param row        单元格所在的行索引。
         * @param column     单元格所在的列索引。
         * @return 渲染的组件，此处为一个 "删除" 按钮。
         */
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
                /**
                 * 处理按钮点击事件的方法。当删除按钮被点击时，执行删除课程班的操作，并更新表格和界面显示。
                 *
                 * @param e 按钮点击事件对象。
                 */
                public void actionPerformed(ActionEvent e) {
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    classid=classdata[clickedRow][0];
                    courseid=classdata[clickedRow][1];
                    CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
                    try {
                        clientAPI.DeleteClassByClassid(classid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    int tar = 0;
                    for(int i=0;i<classdata.length;i++){
                        if(classdata[i][0]==classid){
                            tar=i;
                            break;
                        }
                    }
                    String[][] newselectclass = new String[classdata.length - 1][];
                    for (int i = 0, j = 0; i < classdata.length; i++) {
                        if (i != tar) {
                            newselectclass[j++] = classdata[i];
                        }
                    }
                    System.out.println("点击的行索引：" + clickedRow);
                    classdata=newselectclass;
                    String[] columnNames ={"课程班编号","课程名称","上课地点","最大人数","上课时间","任课教师ID","修改","删除","本班学生"};
                    DefaultTableModel model=new DefaultTableModel(classdata,columnNames);
                    classtable.setModel(model);
                    classtable.setRowHeight(30);
                    JTableHeader tab_header = classtable.getTableHeader();					//获取表头
                    tab_header.setFont(new Font("楷体",Font.PLAIN,25));
                    tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
                    classtable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
                    classtable.getColumnModel().getColumn(6).setCellRenderer(new AdminChangeClassesTableCellRendererButton());
                    classtable.getColumnModel().getColumn(6).setCellEditor(new AdminChangeClassesTableCellEditorButton());
                    classtable.getColumnModel().getColumn(7).setCellRenderer(new AdminDeleteClassesTableCellRendererButton());
                    classtable.getColumnModel().getColumn(7).setCellEditor(new AdminDeleteClassesTableCellEditorButton());
                    classtable.getColumnModel().getColumn(8).setCellRenderer(new AdminShowClassesStuTableCellRendererButton());
                    classtable.getColumnModel().getColumn(8).setCellEditor(new AdminShowClassesStuTableCellEditorButton());
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
                    classid=classdata[clickedRow][0];
                    courseid=classdata[clickedRow][1];
                    //CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
                    //clientAPI.ModifyClassByinfo()
                    AdminChangeClassesUI adminChangeClassesUI= null;
                    try {
                        adminChangeClassesUI = new AdminChangeClassesUI();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    adminChangeClassesUI.setVisible(true);
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
                    classid=classdata[clickedRow][0];
                    InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                    StudentInfo[] studentInfos=null;
                    try {
                        studentInfos=infoClientAPI.SearchStudentByClassID(classid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    String[][] src=null;
                    if(studentInfos!=null){
                        src=StudentInfotoString(studentInfos);
                    }
                    studentdata=src;
                    String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名","删除学生"};
                    DefaultTableModel newmodel=new DefaultTableModel(studentdata,columnNames);
                    studenttable.setModel(newmodel);
                    studenttable.getColumnModel().getColumn(4).setCellRenderer(new AdminDeleteStuTableCellRendererButton());
                    studenttable.getColumnModel().getColumn(4).setCellEditor(new AdminDeleteStuTableCellEditorButton());
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
    class AdminShowClassesStuUI extends JFrame{
        SpringLayout springLayout = new SpringLayout();
        JPanel ClassStudentsTopPanel = new JPanel(){
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
        };;
        JPanel ClassStudentsBottomPanel = new JPanel(){
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
        JPanel ClassStudentsPanel1 = new JPanel();//中间卡片布局的面板
        JPanel ClassStudentsPanel = new JPanel(springLayout){
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
        };;//老师查看班级学生的面板
        JLabel ClassLabel = new JLabel("本班学生");

        JButton backBtn = new JButton("退出");

        //studentdata
        public AdminShowClassesStuUI() {
            super("选课系统");
            backBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new SummaryManagerUI();
                }
            });
            JLabel imageLabel = new JLabel();
//            try {
//                // 加载图片
//                int newWidth = 90;  // 新的宽度
//                int newHeight = 100; // 新的高度
//
//                Image pkqIm = ImageIO.read(new File("Images/sdz1.jpeg"));  // 请将 "image.png" 替换为实际的图片路径
//
//                Image scaledImage = pkqIm.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
//                imageLabel.setIcon(new ImageIcon(scaledImage));
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名","删除学生"};
            DefaultTableModel model=new DefaultTableModel(studentdata,columnNames);
            studenttable.setModel(model);
            studenttable.setRowHeight(30);
            studenttable.setOpaque(false);
            studenttable.setDefaultRenderer(Object.class, renderer);
            JTableHeader tab_header = studenttable.getTableHeader();					//获取表头
            tab_header.setFont(new Font("楷体",Font.PLAIN,25));
            tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
            studenttable.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
            studenttable.getColumnModel().getColumn(4).setCellRenderer(new AdminDeleteStuTableCellRendererButton());
            studenttable.getColumnModel().getColumn(4).setCellEditor(new AdminDeleteStuTableCellEditorButton());
            JScrollPane scrollPane = new JScrollPane(studenttable);
            scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
            scrollPane.setOpaque(false);
            scrollPane.getViewport().setBackground(new Color(255,255,255,150));
            Container contentPane = getContentPane();//获取控制面板

            contentPane.setLayout(new BorderLayout());
            CardLayout cardLayout = new CardLayout();
            contentPane.add(ClassStudentsTopPanel, BorderLayout.NORTH);
            contentPane.add(ClassStudentsBottomPanel, BorderLayout.SOUTH);
            contentPane.add(ClassStudentsPanel1, BorderLayout.CENTER);

            ClassStudentsPanel1.setLayout(cardLayout);//卡片式布局
            ClassStudentsPanel1.add(ClassStudentsPanel, "ClassPanel");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体

            studenttable.setFont(centerFont);
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
                    studentid=studentdata[clickedRow][2];
                    CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
                    try {
                        clientAPI.QuitClassByinfo(studentid,classid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    int tar = 0;
                    for(int i=0;i<studentdata.length;i++){
                        if(studentdata[i][2]==studentid){
                            tar=i;
                            break;
                        }
                    }
                    String[][] newstudent = new String[studentdata.length - 1][];
                    for (int i = 0, j = 0; i < studentdata.length; i++) {
                        if (i != tar) {
                            newstudent[j++] = studentdata[i];
                        }
                    }
                    String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名","删除学生"};
                    DefaultTableModel model=new DefaultTableModel(studentdata,columnNames);
                    studenttable.setModel(model);
                    studenttable.getColumnModel().getColumn(4).setCellRenderer(new AdminDeleteStuTableCellRendererButton());
                    studenttable.getColumnModel().getColumn(4).setCellEditor(new AdminDeleteStuTableCellEditorButton());
                    JScrollPane scrollPane = new JScrollPane(studenttable);
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
    class AdminAddClassesUI extends JFrame{
        SpringLayout springLayout=new SpringLayout();
        JLabel ClassIdLabel=new JLabel("课程班编号");
        JLabel CourseNameLabel=new JLabel("课程名称");
        JLabel ClassPlaceLabel=new JLabel("上课地点");
        JLabel ClassMaxLabel=new JLabel("最大人数");
        JLabel ClassTimeLabel=new JLabel("上课时间");
        JLabel CourseIdLabel=new JLabel("课程编号");
        JLabel TeacherIdLabel=new JLabel("任课教师ID");

        public JTextField getClassIdTex() {
            return ClassIdTex;
        }

        JTextField ClassIdTex=new JTextField();

        public JTextField getCourseNameTex() {
            return CourseNameTex;
        }


//        public JComboBox<String> getComboBox() {
//            return ClassIdcomboBox;
//        }

        //JComboBox<String> ClassIdcomboBox = new JComboBox<>();

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

        public JComboBox<String> getCourseIdcomboBox() {
            return CourseIdcomboBox;
        }

        JComboBox<String> CourseIdcomboBox = new JComboBox<>();


        public JComboBox<String> getTeacherIdcomboBox() {
            return TeacherIdcomboBox;
        }

        JComboBox<String> TeacherIdcomboBox = new JComboBox<>();
        JButton EnsureBtn=new JButton("确认");

        JButton ExitBtn=new JButton("取消");
        JPanel panel=new JPanel(springLayout);

        public AdminAddClassesUI() throws IOException {
            super("选课系统");
            CourseSelectClientAPI courseSelectClientAPI=new CourseSelectClientAPIImp("localhost",8888);
            User[] users=courseSelectClientAPI.GetAllTeacher();
            for(int i=0;i<users.length;i++){
                TeacherIdcomboBox.addItem(users[i].getuId());
            }
            CourseSelectClientAPI courseSelectClientAPI1=new CourseSelectClientAPIImp("localhost",8888);
            CourseClass[] classes=courseSelectClientAPI1.GetAllClass();
            if(classes!=null) {
                CourseSelectClientAPI courseSelectClientAPI2 = new CourseSelectClientAPIImp("localhost", 8888);
                Course[] courses = courseSelectClientAPI2.GetAllCourse();
                for (int i = 0; i < courses.length; i++) {
                    CourseIdcomboBox.addItem(courses[i].getCourseID());
                }
            }
            Container contentPane=getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());
            contentPane.add(panel,BorderLayout.CENTER);
            Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
            ClassIdLabel.setFont(centerFont);

            CourseNameLabel.setFont(centerFont);

            ClassPlaceLabel.setFont(centerFont);

            ClassMaxLabel.setFont(centerFont);

            ClassTimeLabel.setFont(centerFont);

            CourseIdLabel.setFont(centerFont);
            TeacherIdLabel.setFont(centerFont);


            EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
            ExitBtn.setPreferredSize(new Dimension(150,30));
            panel.add(EnsureBtn);
            panel.add(ExitBtn);

            ClassIdTex.setPreferredSize(new Dimension(200,25));
            CourseNameTex.setPreferredSize(new Dimension(200,25));
            ClassPlaceTex.setPreferredSize(new Dimension(200,25));
            ClassMaxTex.setPreferredSize(new Dimension(200,25));
            ClassTimeTex.setPreferredSize(new Dimension(200,25));
            CourseIdcomboBox.setPreferredSize(new Dimension(200,25));
            TeacherIdcomboBox.setPreferredSize(new Dimension(200,25));
            panel.add(ClassIdLabel);
            panel.add(CourseNameLabel);
            panel.add(ClassPlaceLabel);
            panel.add(ClassMaxLabel);
            panel.add(ClassTimeLabel);
            panel.add(CourseIdLabel);
            panel.add(ClassIdTex);
            panel.add(CourseNameTex);
            panel.add(ClassPlaceTex);
            panel.add(ClassMaxTex);
            panel.add(ClassTimeTex);
            panel.add(CourseIdcomboBox);
            panel.add(TeacherIdLabel);
            panel.add(TeacherIdcomboBox);

            Spring childWidth=Spring.sum(Spring.sum(Spring.width(ClassIdLabel),Spring.width(ClassIdTex)),
                    Spring.constant(0));
            int offsetX=childWidth.getValue()/2;
            springLayout.putConstraint(SpringLayout.NORTH,ClassIdLabel,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassIdTex,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.EAST,ClassIdLabel,-offsetX+80,SpringLayout.HORIZONTAL_CENTER,panel);
            springLayout.putConstraint(SpringLayout.WEST,ClassIdTex,offsetX-120,SpringLayout.HORIZONTAL_CENTER,panel);

            springLayout.putConstraint(SpringLayout.NORTH,CourseNameLabel,20,SpringLayout.SOUTH,CourseIdLabel);
            springLayout.putConstraint(SpringLayout.EAST,CourseNameLabel,0,SpringLayout.EAST,CourseIdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,CourseNameTex,20,SpringLayout.SOUTH,CourseIdcomboBox);
            springLayout.putConstraint(SpringLayout.WEST,CourseNameTex,0,SpringLayout.WEST,CourseIdcomboBox);

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

            springLayout.putConstraint(SpringLayout.NORTH,CourseIdLabel,20,SpringLayout.SOUTH,ClassIdLabel);
            springLayout.putConstraint(SpringLayout.EAST,CourseIdLabel,0,SpringLayout.EAST,ClassIdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,CourseIdcomboBox,20,SpringLayout.SOUTH,ClassIdTex);
            springLayout.putConstraint(SpringLayout.WEST,CourseIdcomboBox,0,SpringLayout.WEST,ClassIdTex);

            springLayout.putConstraint(SpringLayout.NORTH,TeacherIdLabel,20,SpringLayout.SOUTH,ClassTimeLabel);
            springLayout.putConstraint(SpringLayout.EAST,TeacherIdLabel,0,SpringLayout.EAST,ClassTimeLabel);
            springLayout.putConstraint(SpringLayout.NORTH,TeacherIdcomboBox,20,SpringLayout.SOUTH,ClassTimeTex);
            springLayout.putConstraint(SpringLayout.WEST,TeacherIdcomboBox,0,SpringLayout.WEST,ClassTimeTex);

            springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,15,SpringLayout.SOUTH,TeacherIdcomboBox);
            springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,60,SpringLayout.EAST,ClassTimeLabel);
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
                    //classid courseid place max time teacher
                    String classid=getClassIdTex().getText();
                    String courseid=CourseIdcomboBox.getSelectedItem().toString();
                    String place=getClassPlaceTex().getText();
                    int max=Integer.parseInt(getClassMaxTex().getText());
                    String time=getClassTimeTex().getText();
                    String teacherid=TeacherIdcomboBox.getSelectedItem().toString();
                    CourseClass courseClass=new CourseClass(classid,courseid,teacherid,place,max,0,time,null);
                    CourseSelectClientAPI clientAPI2=new CourseSelectClientAPIImp("localhost",8888);
                    try {
                        clientAPI2.AddClass(courseClass);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        UpdateClassTable();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    //新增则新增课程班信息到数据库
                    //修改则在数据库中修改课程班信息
                    dispose();
                }
            });
            setSize(600,400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setVisible((true));
        }
    }

    /**
     * 选课管理员界面查看班级界面
     */
    class AdminChangeClassesUI extends JFrame{
        SpringLayout springLayout=new SpringLayout();
        JLabel ClassTempLabel=new JLabel("当前人数");
        JLabel CourseNameLabel=new JLabel("课程名称");
        JLabel ClassPlaceLabel=new JLabel("上课地点");
        JLabel ClassMaxLabel=new JLabel("最大人数");
        JLabel ClassTimeLabel=new JLabel("上课时间");
        JLabel ClassTeacherLabel=new JLabel("任课教师ID");

        public JTextField getClassIdTex() {
            return ClassTempTex;
        }

        public JTextField getClassTempTex() {
            return ClassTempTex;
        }

        JTextField ClassTempTex=new JTextField();

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



        public JComboBox<String> getClassTeachercomboBox() {
            return ClassTeachercomboBox;
        }

        JComboBox<String> ClassTeachercomboBox = new JComboBox<>();

        JButton EnsureBtn=new JButton("确认");

        JButton ExitBtn=new JButton("取消");
        JPanel panel=new JPanel(springLayout);
        /**

         创建 AdminChangeClassesUI 类的新实例。
         @throws IOException 如果发生 I/O 错误。 */
        public AdminChangeClassesUI() throws IOException {
            super("选课系统");
            Container contentPane=getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());
            CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
            User users[]=clientAPI.GetAllTeacher();
            for(int i=0;i<users.length;i++){
                ClassTeachercomboBox.addItem(users[i].getuId());
            }
            //CourseSelectClientAPI clientAPI=new CourseSelectClientAPIImp("localhost",8888);
            //CourseclientAPI.GetAllCourse();
            contentPane.add(panel,BorderLayout.CENTER);
            Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
            ClassTempLabel.setFont(centerFont);

            CourseNameLabel.setFont(centerFont);

            ClassPlaceLabel.setFont(centerFont);

            ClassMaxLabel.setFont(centerFont);

            ClassTimeLabel.setFont(centerFont);

            ClassTeacherLabel.setFont(centerFont);


            EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
            ExitBtn.setPreferredSize(new Dimension(150,30));
            panel.add(EnsureBtn);
            panel.add(ExitBtn);

            ClassTempTex.setPreferredSize(new Dimension(200,25));
            CourseNameTex.setPreferredSize(new Dimension(200,25));
            ClassPlaceTex.setPreferredSize(new Dimension(200,25));
            ClassMaxTex.setPreferredSize(new Dimension(200,25));
            ClassTimeTex.setPreferredSize(new Dimension(200,25));
            ClassTeachercomboBox.setPreferredSize(new Dimension(200,25));
            panel.add(ClassTempLabel);
            panel.add(CourseNameLabel);
            panel.add(ClassPlaceLabel);
            panel.add(ClassMaxLabel);
            panel.add(ClassTimeLabel);
            panel.add(ClassTeacherLabel);
            panel.add(ClassTempTex);
            panel.add(CourseNameTex);
            panel.add(ClassPlaceTex);
            panel.add(ClassMaxTex);
            panel.add(ClassTimeTex);
            panel.add(ClassTeachercomboBox);

            Spring childWidth=Spring.sum(Spring.sum(Spring.width(ClassTempLabel),Spring.width(ClassTempTex)),
                    Spring.constant(0));
            int offsetX=childWidth.getValue()/2;
            springLayout.putConstraint(SpringLayout.NORTH,CourseNameLabel,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.NORTH,CourseNameTex,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.EAST,CourseNameLabel,-offsetX+80,SpringLayout.HORIZONTAL_CENTER,panel);
            springLayout.putConstraint(SpringLayout.WEST,CourseNameTex,offsetX-120,SpringLayout.HORIZONTAL_CENTER,panel);

            springLayout.putConstraint(SpringLayout.NORTH,ClassPlaceLabel,20,SpringLayout.SOUTH,CourseNameLabel);
            springLayout.putConstraint(SpringLayout.EAST,ClassPlaceLabel,0,SpringLayout.EAST,CourseNameLabel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassPlaceTex,20,SpringLayout.SOUTH,CourseNameTex);
            springLayout.putConstraint(SpringLayout.WEST,ClassPlaceTex,0,SpringLayout.WEST,CourseNameTex);

            springLayout.putConstraint(SpringLayout.NORTH,ClassMaxLabel,20,SpringLayout.SOUTH,ClassPlaceLabel);
            springLayout.putConstraint(SpringLayout.EAST,ClassMaxLabel,0,SpringLayout.EAST,ClassPlaceLabel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassMaxTex,20,SpringLayout.SOUTH,ClassPlaceTex);
            springLayout.putConstraint(SpringLayout.WEST,ClassMaxTex,0,SpringLayout.WEST,ClassPlaceTex);

            springLayout.putConstraint(SpringLayout.NORTH,ClassTempLabel,20,SpringLayout.SOUTH,ClassMaxLabel);
            springLayout.putConstraint(SpringLayout.EAST,ClassTempLabel,0,SpringLayout.EAST,ClassMaxLabel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassTempTex,20,SpringLayout.SOUTH,ClassMaxTex);
            springLayout.putConstraint(SpringLayout.WEST,ClassTempTex,0,SpringLayout.WEST,ClassMaxTex);

            springLayout.putConstraint(SpringLayout.NORTH,ClassTimeLabel,20,SpringLayout.SOUTH,ClassTempLabel);
            springLayout.putConstraint(SpringLayout.EAST,ClassTimeLabel,0,SpringLayout.EAST,ClassTempLabel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassTimeTex,20,SpringLayout.SOUTH,ClassTempTex);
            springLayout.putConstraint(SpringLayout.WEST,ClassTimeTex,0,SpringLayout.WEST,ClassTempTex);

            springLayout.putConstraint(SpringLayout.NORTH,ClassTeacherLabel,20,SpringLayout.SOUTH,ClassTimeLabel);
            springLayout.putConstraint(SpringLayout.EAST,ClassTeacherLabel,0,SpringLayout.EAST,ClassTimeLabel);
            springLayout.putConstraint(SpringLayout.NORTH,ClassTeachercomboBox,20,SpringLayout.SOUTH,ClassTimeTex);
            springLayout.putConstraint(SpringLayout.WEST,ClassTeachercomboBox,0,SpringLayout.WEST,ClassTimeTex);



            springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,30,SpringLayout.SOUTH,ClassTeachercomboBox);
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
                    //classid courseid place max time teacher
                    String place=getClassPlaceTex().getText();
                    int max=Integer.parseInt(getClassMaxTex().getText());
                    String time=getClassTimeTex().getText();
                    String teacher=ClassTeachercomboBox.getSelectedItem().toString();
                    int temp= Integer.parseInt(getClassTempTex().getText());
                    InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                    StudentInfo[] infos=null;
                    try {
                        infos=infoClientAPI.SearchStudentByClassID(classid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    String[] ids=null;
                    if(infos!=null) {
                        ids=new String[infos.length];
                        for (int i = 0; i < infos.length; i++) {
                            ids[i] = infos[i].getID();
                        }
                    }
                    CourseClass courseClass=new CourseClass(classid,courseid,teacher,place,max,temp,time,ids);
                    CourseSelectClientAPI clientAPI2=new CourseSelectClientAPIImp("localhost",8888);
                    try {
                        clientAPI2.ModifyClassByinfo(courseClass);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        UpdateClassTable();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    /*String[] columnNames ={"课程班编号","课程名称","上课地点","最大人数","上课时间","任课教师ID","修改","删除","本班学生"};
                    DefaultTableModel model=new DefaultTableModel(classdata,columnNames);
                    classtable.setModel(model);
                    classtable.getColumnModel().getColumn(6).setCellRenderer(new AdminChangeClassesTableCellRendererButton());
                    classtable.getColumnModel().getColumn(6).setCellEditor(new AdminChangeClassesTableCellEditorButton());
                    classtable.getColumnModel().getColumn(7).setCellRenderer(new AdminDeleteClassesTableCellRendererButton());
                    classtable.getColumnModel().getColumn(7).setCellEditor(new AdminDeleteClassesTableCellEditorButton());
                    classtable.getColumnModel().getColumn(8).setCellRenderer(new AdminShowClassesStuTableCellRendererButton());
                    classtable.getColumnModel().getColumn(8).setCellEditor(new AdminShowClassesStuTableCellEditorButton());*/
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
    /**

     自定义的表格单元格背景颜色渲染器类。
     */
    private class TableBackgroundColorRenderer extends DefaultTableCellRenderer {
        /**

         获取表格单元格的渲染组件。

         @param table 当前的表格对象。

         @param value 单元格的值。

         @param isSelected 指示单元格是否被选中。

         @param hasFocus 指示单元格是否拥有焦点。

         @param row 单元格所在的行数。

         @param column 单元格所在的列数。

         @return 渲染后的单元格组件。
         */
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
    /**

     程序的入口方法，负责启动选课系统的管理员界面。

     @param args 命令行参数（未使用）。

     @throws IOException 如果发生 I/O 错误。
     */
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
        new CurriculumAdminUI();
    }
}