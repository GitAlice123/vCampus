package view.CourseSelection;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurriculumTeacherUI extends JFrame {
    SpringLayout springLayout=new SpringLayout();
    JPanel TopPanel=new JPanel();
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel(springLayout);

    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JLabel title=new JLabel("教学班");
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
    JButton backBtn=new JButton("退出");
    public CurriculumTeacherUI(){
        super("选课系统");
        String[] columnNames ={"课程班编号","课程名称","上课地点","当前班级人数","上课时间","本班学生"};

        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(5).setCellRenderer(new TeacherTableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new TeacherTableCellEditorButton());

        //loginHandler=new logInHandler(this);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250)); // 设置滚动面板的大小
        Container contentPane=getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());

        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);
        title.setFont(new Font("楷体",Font.PLAIN,20));
        panel1.add(scrollPane);

        backBtn.setPreferredSize(new Dimension(100,30));

        TopPanel.add(title);
        BottomPanel.add(backBtn);


        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,20,SpringLayout.NORTH,panel1);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,25,SpringLayout.WEST,panel1);





        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }



    public static void main(String[] args){

        new CurriculumTeacherUI();
    }
}

class TeacherTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        JButton button = new JButton("查看");
        return button;
    }

}

class TeacherClassStudentsUI extends JFrame {//显示本班学生界面
    String[][] data = {
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},
            {"1", "1", "1", "1"},


    };
    SpringLayout springLayout = new SpringLayout();
    JPanel ClassStudentsTopPanel = new JPanel();
    JPanel ClassStudentsBottomPanel = new JPanel();//底部放置按钮的面板
    JPanel ClassStudentsPanel1 = new JPanel();//中间卡片布局的面板
    JPanel ClassStudentsPanel = new JPanel(springLayout);//老师查看班级学生的面板
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JLabel ClassLabel = new JLabel("本班学生");

    JButton backBtn = new JButton("退出");

    public TeacherClassStudentsUI() {
        super("选课系统");

        String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名"};

        model.setDataVector(data, columnNames);
        table.setModel(model);



        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250)); // 设置滚动面板的大小

        Container contentPane = getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        contentPane.add(ClassStudentsTopPanel, BorderLayout.NORTH);
        contentPane.add(ClassStudentsBottomPanel, BorderLayout.SOUTH);
        contentPane.add(ClassStudentsPanel1, BorderLayout.CENTER);

        ClassStudentsPanel1.setLayout(cardLayout);//卡片式布局
        ClassStudentsPanel1.add(ClassStudentsPanel, "ClassPanel");
        Font centerFont = new Font("楷体", Font.PLAIN, 40);//设置中间组件的文字大小、字体

        ClassLabel.setFont(new Font("楷体",Font.PLAIN,20));
        backBtn.setPreferredSize(new Dimension(100, 30));

        ClassStudentsTopPanel.add(ClassLabel);
        ClassStudentsBottomPanel.add(backBtn);
        ClassStudentsPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, ClassStudentsPanel);
        springLayout.putConstraint(SpringLayout.WEST, scrollPane, 25, SpringLayout.WEST, ClassStudentsPanel);

        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }
}
class TeacherTableCellEditorButton extends DefaultCellEditor {
    private JButton btn;
    private int clickedRow;

    public TeacherTableCellEditorButton() {
        super(new JTextField());
        this.setClickCountToStart(1);
        btn = new JButton("查看");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);
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
