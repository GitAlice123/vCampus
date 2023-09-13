package view.SchoolRolls;

import view.Global.*;
import view.Login.User;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学生学籍模块管理员的界面
 */
public class StudentStatusAdminUI extends JFrame {
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
    JTable table=new JTable() ;
    boolean add;
    // 创建一个JScrollPane来包装表格
    String selectid;
    String[][] data = {};
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
    };;//学生信息管理的面板
    JButton InfoBtn=new JButton("学生信息管理");
    JButton FindBtn=new JButton("查询");
    DefaultTableModel model = new DefaultTableModel();

    public JTextField getFindTex() {
        return FindTex;
    }

    JTextField FindTex=new JTextField();
    JButton AddStudentBtn=new JButton("添加学生");

    JButton backBtn=new JButton("退出");
    /**
     * 创建一个学生学籍管理员用户界面。
     *
     * @throws IOException 如果发生输入/输出错误时抛出该异常
     */
    public StudentStatusAdminUI() throws IOException {
        super("学生学籍系统");

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryManagerUI() ;
            }
        });
        //loginHandler=new logInHandler(this);
        // 创建表格的列名数组
        String[] columnNames = {"一卡通号", "学号", "姓名","性别","出生年月","入学年份","学院","删除","修改"};
        InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
        StudentInfo[] studentInfos=infoClientAPI.GetAllInfo();
        data=new String[studentInfos.length][7];
        for(int i=0;i<studentInfos.length;i++){
            SimpleDateFormat ft= new SimpleDateFormat("yyyy-MM-dd");
            data[i][0]=studentInfos[i].getCardID();
            data[i][1]=studentInfos[i].getID();
            data[i][2]=studentInfos[i].getName();
            data[i][3]=studentInfos[i].getSex();
            data[i][4]= ft.format(studentInfos[i].getBirth());
            data[i][5]= String.valueOf(studentInfos[i].getGrade());
            data[i][6]= studentInfos[i].getCollege();
        }
        // 创建JTable对象并传入数据和列名
        JScrollPane scrollPane = new JScrollPane(table);
        model.setDataVector(data, columnNames);
        table.setRowHeight(30);
        table.setModel(model);
        table.setOpaque(false);
        table.setDefaultRenderer(Object.class, renderer);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        //table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        table.getColumnModel().getColumn(7).setCellRenderer(new InfoDeleteTableCellRendererButton());
        table.getColumnModel().getColumn(7).setCellEditor(new InfoDeleteTableCellEditorButton());
        table.getColumnModel().getColumn(8).setCellRenderer(new InfoChangeTableCellRendererButton());
        table.getColumnModel().getColumn(8).setCellEditor(new InfoChangeTableCellEditorButton());
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小

        scrollPane.setOpaque(false);
        scrollPane.getViewport().setBackground(new Color(255,255,255,150));

        //loginHandler=new logInHandler(this);

        Container contentPane=getContentPane();//获取控制面板
        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout=new CardLayout();
        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);

        TopPanel.add(InfoBtn);

        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(InfoPanel,"InfoPanel");
        InfoPanel.add(FindBtn);
        InfoPanel.add(FindTex);
        InfoPanel.add(AddStudentBtn);
        InfoPanel.add(scrollPane);
        BottomPanel.add(backBtn);
        Font centerFont=new Font("楷体",Font.PLAIN,25);//设置中间组件的文字大小、字体
        table.setFont(centerFont);
        backBtn.setFont(centerFont);
        InfoBtn.setFont(centerFont);
        FindBtn.setFont(centerFont);
        FindTex.setFont(centerFont);
        AddStudentBtn.setFont(centerFont);
        InfoBtn.setPreferredSize(new Dimension(200,40));
        FindBtn.setPreferredSize(new Dimension(100,40));

        backBtn.setPreferredSize(new Dimension(100,40));
        FindTex.setPreferredSize(new Dimension(200,40));
        AddStudentBtn.setPreferredSize(new Dimension(200,40));
        springLayout.putConstraint(SpringLayout.NORTH, FindTex, 10, SpringLayout.NORTH, InfoPanel);
        springLayout.putConstraint(SpringLayout.WEST, FindTex, 20, SpringLayout.WEST, InfoPanel);
        springLayout.putConstraint(SpringLayout.NORTH, FindBtn, 0, SpringLayout.NORTH, FindTex);
        springLayout.putConstraint(SpringLayout.WEST, FindBtn, 20, SpringLayout.EAST, FindTex);
        springLayout.putConstraint(SpringLayout.NORTH, AddStudentBtn, 0, SpringLayout.NORTH, FindTex);
        springLayout.putConstraint(SpringLayout.EAST, AddStudentBtn, -20, SpringLayout.EAST, InfoPanel);

        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 100, SpringLayout.WEST, InfoPanel);
        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 30, SpringLayout.SOUTH, FindTex);



        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SummaryManagerUI();
            }
        });
        FindBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InfoClientAPI clientAPI=new InfoClientAPIImp("localhost",8888);

                StudentInfo info=null;
                String id=getFindTex().getText();
                try {
                    info=clientAPI.SearchStuInfoByID(id);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String[][] src=new String[1][7];
                SimpleDateFormat ft= new SimpleDateFormat("yyyy-MM-dd");
                    src[0][0]=info.getCardID();
                    src[0][1]=info.getID();
                    src[0][2]=info.getName();
                    src[0][3]=info.getSex();
                    src[0][4]= ft.format(info.getBirth());
                    src[0][5]= String.valueOf(info.getGrade());
                    src[0][6]= info.getCollege();
                // 创建JTable对象并传入数据和列名
                data=src;
                String[] columnNames = {"一卡通号", "学号", "姓名","性别","出生年月","入学年份","学院","删除","修改"};
                model.setDataVector(data, columnNames);
                table.setModel(model);
                table.getColumnModel().getColumn(7).setCellRenderer(new InfoDeleteTableCellRendererButton());
                table.getColumnModel().getColumn(7).setCellEditor(new InfoDeleteTableCellEditorButton());
                table.getColumnModel().getColumn(8).setCellRenderer(new InfoChangeTableCellRendererButton());
                table.getColumnModel().getColumn(8).setCellEditor(new InfoChangeTableCellEditorButton());
                scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
            }
        });
        AddStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add=true;
                AddStudentUI addStudentUI= null;
                try {
                    addStudentUI = new AddStudentUI();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                addStudentUI.setVisible(true);
            }
        });
        InfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UpdateData();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
    void UpdateData() throws IOException {
        String[] columnNames = {"一卡通号", "学号", "姓名","性别","出生年月","入学年份","学院","删除","修改"};
        InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
        StudentInfo[] studentInfos=infoClientAPI.GetAllInfo();
        data=new String[studentInfos.length][7];
        for(int i=0;i<studentInfos.length;i++){
            SimpleDateFormat ft= new SimpleDateFormat("yyyy-MM-dd");
            data[i][0]=studentInfos[i].getCardID();
            data[i][1]=studentInfos[i].getID();
            data[i][2]=studentInfos[i].getName();
            data[i][3]=studentInfos[i].getSex();
            data[i][4]= ft.format(studentInfos[i].getBirth());
            data[i][5]= String.valueOf(studentInfos[i].getGrade());
            data[i][6]= studentInfos[i].getCollege();
        }
        // 创建一个JScrollPane来包装表格
        DefaultTableModel newmodel=new DefaultTableModel(data,columnNames);
        table.setModel(newmodel);
        table.getColumnModel().getColumn(7).setCellRenderer(new InfoDeleteTableCellRendererButton());
        table.getColumnModel().getColumn(7).setCellEditor(new InfoDeleteTableCellEditorButton());
        table.getColumnModel().getColumn(8).setCellRenderer(new InfoChangeTableCellRendererButton());
        table.getColumnModel().getColumn(8).setCellEditor(new InfoChangeTableCellEditorButton());
    }

    /**
     * 修改学生信息界面
     */
    class ChangeStudentUI extends JFrame {
        SpringLayout springLayout=new SpringLayout();
       // JLabel Card_idLabel=new JLabel("一卡通号");
        JLabel IdLabel=new JLabel("学号");
        JLabel NameLabel=new JLabel("姓名");
        JLabel SexLabel=new JLabel("性别");
        JLabel BirthLabel=new JLabel("出身年月");
        JLabel GradeLabel=new JLabel("入学年份");
        JLabel CollegeLabel=new JLabel("学院");

//        public JTextField getCard_idTex() {
//            return Card_idTex;
//        }

        //JTextField Card_idTex=new JTextField();

        public JTextField getIdTex() {
            return IdTex;
        }

        JTextField IdTex=new JTextField();

        public JTextField getNameTex() {
            return NameTex;
        }

        JTextField NameTex=new JTextField();
        String[] options = {"M", "F"};

        public JComboBox<String> getComboBox() {
            return comboBox;
        }

        JComboBox<String> comboBox = new JComboBox<>(options);

        public JTextField getBirthTex() {
            return BirthTex;
        }

        JTextField BirthTex=new JTextField();

        public JTextField getGradeTex() {
            return GradeTex;
        }

        JTextField GradeTex=new JTextField();

        public JTextField getCollegeTex() {
            return CollegeTex;
        }

        JTextField CollegeTex=new JTextField();
        JButton EnsureBtn=new JButton("确认");

        JButton ExitBtn=new JButton("取消");
        JPanel panel=new JPanel(springLayout);、
        /**
         * 创建一个修改学生信息界面。
         *
         * @throws IOException 如果发生输入/输出错误时抛出该异常
         */
        public ChangeStudentUI(){
            Container contentPane=getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());

            contentPane.add(panel,BorderLayout.CENTER);
            Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
            //Card_idLabel.setFont(centerFont);

            IdLabel.setFont(centerFont);

            NameLabel.setFont(centerFont);

            SexLabel.setFont(centerFont);

            BirthLabel.setFont(centerFont);

            GradeLabel.setFont(centerFont);

            CollegeLabel.setFont(centerFont);
            EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
            ExitBtn.setPreferredSize(new Dimension(150,30));
            panel.add(EnsureBtn);
            panel.add(ExitBtn);

            //Card_idTex.setPreferredSize(new Dimension(200,25));
            IdTex.setPreferredSize(new Dimension(200,25));
            NameTex.setPreferredSize(new Dimension(200,25));
            comboBox.setPreferredSize(new Dimension(200,25));
            BirthTex.setPreferredSize(new Dimension(200,25));
            GradeTex.setPreferredSize(new Dimension(200,25));
            CollegeTex.setPreferredSize(new Dimension(200,25));
            //panel.add(Card_idTex);
            panel.add(IdTex);
            panel.add(NameTex);
            panel.add(comboBox);
            panel.add(BirthTex);
            panel.add(GradeTex);
            panel.add(CollegeTex);
            //panel.add(Card_idLabel);
            panel.add(IdLabel);
            panel.add(NameLabel);
            panel.add(SexLabel);
            panel.add(BirthLabel);
            panel.add(GradeLabel);
            panel.add(CollegeLabel);

            Spring childWidth=Spring.sum(Spring.sum(Spring.width(IdLabel),Spring.width(IdTex)),
                    Spring.constant(0));
            int offsetX=childWidth.getValue()/2;
            springLayout.putConstraint(SpringLayout.NORTH,IdLabel,30,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.NORTH,IdTex,30,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.EAST,IdLabel,-offsetX+80,SpringLayout.HORIZONTAL_CENTER,panel);
            springLayout.putConstraint(SpringLayout.WEST,IdTex,offsetX-120,SpringLayout.HORIZONTAL_CENTER,panel);



            springLayout.putConstraint(SpringLayout.NORTH,NameLabel,20,SpringLayout.SOUTH,IdLabel);
            springLayout.putConstraint(SpringLayout.EAST,NameLabel,0,SpringLayout.EAST,IdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,NameTex,20,SpringLayout.SOUTH,IdTex);
            springLayout.putConstraint(SpringLayout.WEST,NameTex,0,SpringLayout.WEST,IdTex);

            springLayout.putConstraint(SpringLayout.NORTH,SexLabel,20,SpringLayout.SOUTH,NameLabel);
            springLayout.putConstraint(SpringLayout.EAST,SexLabel,0,SpringLayout.EAST,NameLabel);
            springLayout.putConstraint(SpringLayout.NORTH,comboBox,20,SpringLayout.SOUTH,NameTex);
            springLayout.putConstraint(SpringLayout.WEST,comboBox,0,SpringLayout.WEST,NameTex);

            springLayout.putConstraint(SpringLayout.NORTH,BirthLabel,20,SpringLayout.SOUTH,SexLabel);
            springLayout.putConstraint(SpringLayout.EAST,BirthLabel,0,SpringLayout.EAST,SexLabel);
            springLayout.putConstraint(SpringLayout.NORTH,BirthTex,20,SpringLayout.SOUTH,comboBox);
            springLayout.putConstraint(SpringLayout.WEST,BirthTex,0,SpringLayout.WEST,comboBox);

            springLayout.putConstraint(SpringLayout.NORTH,GradeLabel,20,SpringLayout.SOUTH,BirthLabel);
            springLayout.putConstraint(SpringLayout.EAST,GradeLabel,0,SpringLayout.EAST,BirthLabel);
            springLayout.putConstraint(SpringLayout.NORTH,GradeTex,20,SpringLayout.SOUTH,BirthTex);
            springLayout.putConstraint(SpringLayout.WEST,GradeTex,0,SpringLayout.WEST,BirthTex);

            springLayout.putConstraint(SpringLayout.NORTH,CollegeLabel,20,SpringLayout.SOUTH,GradeLabel);
            springLayout.putConstraint(SpringLayout.EAST,CollegeLabel,0,SpringLayout.EAST,GradeLabel);
            springLayout.putConstraint(SpringLayout.NORTH,CollegeTex,20,SpringLayout.SOUTH,GradeTex);
            springLayout.putConstraint(SpringLayout.WEST,CollegeTex,0,SpringLayout.WEST,GradeTex);

            springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,10,SpringLayout.SOUTH,CollegeTex);
            springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,60,SpringLayout.EAST,CollegeLabel);
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
                    String id = getIdTex().getText();
                    String cardid = selectid;
                    String name = getNameTex().getText();
                    String sex = comboBox.getSelectedItem().toString();
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    // 使用 SimpleDateFormat 格式化 Date 对象为字符串
                    //String dateString = dateFormat.format(getBirthTex().getText());
                    //Date brith = null;
                    //try {
                    //    brith = dateFormat.parse(getBirthTex().getText());
                    //} catch (ParseException ex) {
                    //    throw new RuntimeException(ex);
                    //}
                    Date birth = new Date(getBirthTex().getText());
                    String collage = getCollegeTex().getText();
                    int grade = Integer.parseInt(getGradeTex().getText());
                    StudentInfo info = new StudentInfo(cardid, id, sex, name, birth, grade, collage);
                    InfoClientAPI infoClientAPI = new InfoClientAPIImp("localhost", 8888);
                    try {
                            infoClientAPI.ModifyInfo(info);
                        }
                    catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    try {
                        UpdateData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                    //新增则新增学生信息到数据库
                    //修改则在数据库中修改学生信息
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
     * 增加学生信息界面
     */
    class AddStudentUI extends JFrame {
        SpringLayout springLayout=new SpringLayout();
        JLabel Card_idLabel=new JLabel("一卡通号");
        JLabel IdLabel=new JLabel("学号");
        JLabel NameLabel=new JLabel("姓名");
        JLabel SexLabel=new JLabel("性别");
        JLabel BirthLabel=new JLabel("出身年月");
        JLabel GradeLabel=new JLabel("入学年份");
        JLabel CollegeLabel=new JLabel("学院");



        public JComboBox<String> getCardIdcomboBox() {
            return CardIdcomboBox;
        }

        JComboBox<String>CardIdcomboBox = new JComboBox<>();

        public JTextField getIdTex() {
            return IdTex;
        }

        JTextField IdTex=new JTextField();

        public JTextField getNameTex() {
            return NameTex;
        }

        JTextField NameTex=new JTextField();
        String[] options = {"M", "F"};

        public JComboBox<String> getComboBox() {
            return comboBox;
        }

        JComboBox<String> comboBox = new JComboBox<>(options);

        public JTextField getBirthTex() {
            return BirthTex;
        }

        JTextField BirthTex=new JTextField();

        public JTextField getGradeTex() {
            return GradeTex;
        }

        JTextField GradeTex=new JTextField();

        public JTextField getCollegeTex() {
            return CollegeTex;
        }

        JTextField CollegeTex=new JTextField();
        JButton EnsureBtn=new JButton("确认");

        JButton ExitBtn=new JButton("取消");
        JPanel panel=new JPanel(springLayout);
        /**
         * 创建一个增加学生信息界面。
         *
         * @throws IOException 如果发生输入/输出错误时抛出该异常
         */
        public AddStudentUI() throws IOException {
            Container contentPane=getContentPane();//获取控制面板
            contentPane.setLayout(new BorderLayout());
            InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
            User[] users=infoClientAPI.GetAllCard();
            String[] ids=new String[users.length];
            for(int i=0;i<users.length;i++){
                InfoClientAPI infoClientAPI1=new InfoClientAPIImp("localhost",8888);
                if(infoClientAPI1.SearchStuInfoByID(users[i].getuId())==null){
                    ids[i]=users[i].getuId();
                }
            }
            if(ids!=null)
            for(int i=0;i<ids.length;i++){
                if(ids[i]!=null)
                    CardIdcomboBox.addItem(ids[i]);
            }
            contentPane.add(panel,BorderLayout.CENTER);
            Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体
            Card_idLabel.setFont(centerFont);

            IdLabel.setFont(centerFont);

            NameLabel.setFont(centerFont);

            SexLabel.setFont(centerFont);

            BirthLabel.setFont(centerFont);

            GradeLabel.setFont(centerFont);

            CollegeLabel.setFont(centerFont);
            EnsureBtn.setPreferredSize(new Dimension(150,30));//设置按钮大小
            ExitBtn.setPreferredSize(new Dimension(150,30));
            panel.add(EnsureBtn);
            panel.add(ExitBtn);

            CardIdcomboBox.setPreferredSize(new Dimension(200,25));
            IdTex.setPreferredSize(new Dimension(200,25));
            NameTex.setPreferredSize(new Dimension(200,25));
            comboBox.setPreferredSize(new Dimension(200,25));
            BirthTex.setPreferredSize(new Dimension(200,25));
            GradeTex.setPreferredSize(new Dimension(200,25));
            CollegeTex.setPreferredSize(new Dimension(200,25));
            panel.add(CardIdcomboBox);
            panel.add(IdTex);
            panel.add(NameTex);
            panel.add(comboBox);
            panel.add(BirthTex);
            panel.add(GradeTex);
            panel.add(CollegeTex);
            panel.add(Card_idLabel);
            panel.add(IdLabel);
            panel.add(NameLabel);
            panel.add(SexLabel);
            panel.add(BirthLabel);
            panel.add(GradeLabel);
            panel.add(CollegeLabel);

            Spring childWidth=Spring.sum(Spring.sum(Spring.width(Card_idLabel),Spring.width(CardIdcomboBox)),
                    Spring.constant(0));
            int offsetX=childWidth.getValue()/2;
            springLayout.putConstraint(SpringLayout.NORTH,Card_idLabel,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.NORTH,CardIdcomboBox,20,SpringLayout.NORTH,panel);
            springLayout.putConstraint(SpringLayout.EAST,Card_idLabel,-offsetX+80,SpringLayout.HORIZONTAL_CENTER,panel);
            springLayout.putConstraint(SpringLayout.WEST,CardIdcomboBox,offsetX-120,SpringLayout.HORIZONTAL_CENTER,panel);

            springLayout.putConstraint(SpringLayout.NORTH,IdLabel,20,SpringLayout.SOUTH,Card_idLabel);
            springLayout.putConstraint(SpringLayout.EAST,IdLabel,0,SpringLayout.EAST,Card_idLabel);
            springLayout.putConstraint(SpringLayout.NORTH,IdTex,20,SpringLayout.SOUTH,CardIdcomboBox);
            springLayout.putConstraint(SpringLayout.WEST,IdTex,0,SpringLayout.WEST,CardIdcomboBox);

            springLayout.putConstraint(SpringLayout.NORTH,NameLabel,20,SpringLayout.SOUTH,IdLabel);
            springLayout.putConstraint(SpringLayout.EAST,NameLabel,0,SpringLayout.EAST,IdLabel);
            springLayout.putConstraint(SpringLayout.NORTH,NameTex,20,SpringLayout.SOUTH,IdTex);
            springLayout.putConstraint(SpringLayout.WEST,NameTex,0,SpringLayout.WEST,IdTex);

            springLayout.putConstraint(SpringLayout.NORTH,SexLabel,20,SpringLayout.SOUTH,NameLabel);
            springLayout.putConstraint(SpringLayout.EAST,SexLabel,0,SpringLayout.EAST,NameLabel);
            springLayout.putConstraint(SpringLayout.NORTH,comboBox,20,SpringLayout.SOUTH,NameTex);
            springLayout.putConstraint(SpringLayout.WEST,comboBox,0,SpringLayout.WEST,NameTex);

            springLayout.putConstraint(SpringLayout.NORTH,BirthLabel,20,SpringLayout.SOUTH,SexLabel);
            springLayout.putConstraint(SpringLayout.EAST,BirthLabel,0,SpringLayout.EAST,SexLabel);
            springLayout.putConstraint(SpringLayout.NORTH,BirthTex,20,SpringLayout.SOUTH,comboBox);
            springLayout.putConstraint(SpringLayout.WEST,BirthTex,0,SpringLayout.WEST,comboBox);

            springLayout.putConstraint(SpringLayout.NORTH,GradeLabel,20,SpringLayout.SOUTH,BirthLabel);
            springLayout.putConstraint(SpringLayout.EAST,GradeLabel,0,SpringLayout.EAST,BirthLabel);
            springLayout.putConstraint(SpringLayout.NORTH,GradeTex,20,SpringLayout.SOUTH,BirthTex);
            springLayout.putConstraint(SpringLayout.WEST,GradeTex,0,SpringLayout.WEST,BirthTex);

            springLayout.putConstraint(SpringLayout.NORTH,CollegeLabel,20,SpringLayout.SOUTH,GradeLabel);
            springLayout.putConstraint(SpringLayout.EAST,CollegeLabel,0,SpringLayout.EAST,GradeLabel);
            springLayout.putConstraint(SpringLayout.NORTH,CollegeTex,20,SpringLayout.SOUTH,GradeTex);
            springLayout.putConstraint(SpringLayout.WEST,CollegeTex,0,SpringLayout.WEST,GradeTex);

            springLayout.putConstraint(SpringLayout.NORTH,EnsureBtn,10,SpringLayout.SOUTH,CollegeTex);
            springLayout.putConstraint(SpringLayout.EAST,EnsureBtn,60,SpringLayout.EAST,CollegeLabel);
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
                    String id = getIdTex().getText();
                    String cardid = CardIdcomboBox.getSelectedItem().toString();
                    String name = getNameTex().getText();
                    String sex = comboBox.getSelectedItem().toString();
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    // 使用 SimpleDateFormat 格式化 Date 对象为字符串
                    //String dateString = dateFormat.format(getBirthTex().getText());
                    //Date brith = null;
                    //try {
                    //    brith = dateFormat.parse(getBirthTex().getText());
                    //} catch (ParseException ex) {
                    //    throw new RuntimeException(ex);
                    //}

                    Date birth = new Date(getBirthTex().getText());

                    String collage = getCollegeTex().getText();
                    int grade = Integer.parseInt(getGradeTex().getText());
                    StudentInfo info = new StudentInfo(cardid, id, sex, name, birth, grade, collage);
                    InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                    try {
                        infoClientAPI.AddStuInfo(info);
                    }
                    catch (IOException ex) {
                            throw new RuntimeException(ex);
                    }
                    try {
                        UpdateData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    dispose();
                    //新增则新增学生信息到数据库
                    //修改则在数据库中修改学生信息
                }
            });



            setSize(600,400);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(false);
            setVisible((true));

        }

    }
    class InfoDeleteTableCellRendererButton implements TableCellRenderer {//删除学生辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("删除");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }


    class InfoDeleteTableCellEditorButton extends DefaultCellEditor{//删除学生辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        public InfoDeleteTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("删除");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {//此处应该删除该行学生信息
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();

                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    selectid=data[clickedRow][0];
                    InfoClientAPI infoClientAPI=new InfoClientAPIImp("localhost",8888);
                    boolean test;
                    try {
                        test=infoClientAPI.DeleteStuInfoByID(selectid);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        UpdateData();
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
    class InfoChangeTableCellRendererButton implements TableCellRenderer {//修改学生辅助类



        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            JButton button = new JButton("修改");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            button.setFont(centerFont);
            return button;
        }

    }


    class InfoChangeTableCellEditorButton extends DefaultCellEditor{//修改学生辅助类，按钮事件触发在此类中

        private JButton btn;
        private int clickedRow;
        public InfoChangeTableCellEditorButton() {
            super(new JTextField());
            //设置点击一次就激活，否则默认好像是点击2次激活。
            this.setClickCountToStart(1);
            btn = new JButton("修改");
            Font centerFont = new Font("楷体", Font.PLAIN, 25);//设置中间组件的文字大小、字体
            btn.setFont(centerFont);
            btn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {//此处应该修改该行学生信息
                    //System.out.println("按钮事件触发----");
                    JButton clickedButton = (JButton) e.getSource();
                    add=false;
                    clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                    selectid=data[clickedRow][0];
                    System.out.println("点击的行索引：" + clickedRow);
                    ChangeStudentUI changeStudentUI=new ChangeStudentUI();
                    changeStudentUI.setVisible(true);

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

        new StudentStatusAdminUI();
    }
}


