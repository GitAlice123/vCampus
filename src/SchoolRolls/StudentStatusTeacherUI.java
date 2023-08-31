package view.SchoolRolls;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class StudentStatusTeacherUI extends JFrame {//老师登录进学生学籍管理系统，看见自己的所有教学班，可以点击每行最后的按钮，显示本教学班学生，并登记成绩
    SpringLayout springLayout=new SpringLayout();
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel ClassPanel=new JPanel(springLayout);//老师查看所教班级的面板
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JButton ClassBtn=new JButton("查看教学班");

    JButton backBtn=new JButton("退出");
    public StudentStatusTeacherUI(){
        super("学生学籍系统");

        String[] columnNames ={"课程班编号","课程编号","上课地点","当前班级人数","上课时间","本班学生"};
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
        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new TableCellEditorButton());

 //       JScrollPane scrollPane = new JScrollPane(table);
//        ClassPanel.add(scrollPane);
//        ClassPanel.setBounds(100, 100, 300, 200);
//        ClassPanel.setVisible(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 250)); // 设置滚动面板的大小

        Container contentPane=getContentPane();//获取控制面板

        contentPane.setLayout(new BorderLayout());
        CardLayout cardLayout=new CardLayout();
        contentPane.add(TopPanel,BorderLayout.NORTH);
        contentPane.add(BottomPanel,BorderLayout.SOUTH);
        contentPane.add(panel1,BorderLayout.CENTER);

        panel1.setLayout(cardLayout);//卡片式布局
        panel1.add(ClassPanel,"ClassPanel");
        Font centerFont=new Font("楷体",Font.PLAIN,20);//设置中间组件的文字大小、字体

        ClassBtn.setPreferredSize(new Dimension(150,30));
        backBtn.setPreferredSize(new Dimension(100,30));

        TopPanel.add(ClassBtn);
        BottomPanel.add(backBtn);
        ClassPanel.add(scrollPane);

        springLayout.putConstraint(SpringLayout.NORTH,scrollPane,20,SpringLayout.NORTH,ClassPanel);
        springLayout.putConstraint(SpringLayout.WEST,scrollPane,25,SpringLayout.WEST,ClassPanel);
        ClassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panel1,"ClassPanel");
            }
        });


        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible((true));
    }




    public static void main(String[] args){

        new StudentStatusTeacherUI();
    }
}
 class ClassStudentsUI extends JFrame {//显示本班学生界面
     String[][] data = {
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"},
             {"1", "1", "1", "1", "1", "1"}

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

    public ClassStudentsUI() {
        super("学生学籍系统");

        String[] columnNames = {"课程班编号", "学号", "一卡通号", "姓名", "成绩", "登记或修改成绩"};

        model.setDataVector(data, columnNames);
        table.setModel(model);

        table.getColumnModel().getColumn(5).setCellRenderer(new ClassTableCellRendererButton());
        table.getColumnModel().getColumn(5).setCellEditor(new ClassTableCellEditorButton());

        //       JScrollPane scrollPane = new JScrollPane(table);
//        ClassPanel.add(scrollPane);
//        ClassPanel.setBounds(100, 100, 300, 200);
//        ClassPanel.setVisible(true);
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

        ClassLabel.setFont(new Font("楷体",Font.PLAIN,30));
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
    public AddScore(){
        super("登记或修改学生成绩");
        Container contentPane=getContentPane();//获取控制面板
        contentPane.add(southPanel,BorderLayout.SOUTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        ScoreLabel.setFont(new Font("楷体",Font.PLAIN,20));//设置标题大小、字体
        ScoreTxt.setPreferredSize(new Dimension(200,25));
        EnsureBtn.setPreferredSize(new Dimension(100,20));
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

        EnsureBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {//此处要增加点击确定以后，在表格对应处显示已经修改的学生成绩
                //System.out.println("按钮事件触发----");
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
        return button;
    }

}


class TableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

    private JButton btn;
    private int clickedRow;
    public TableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("查看");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
                System.out.println("点击的行索引：" + clickedRow);
                ClassStudentsUI classStudentsUI=new ClassStudentsUI();
                classStudentsUI.setVisible(true);

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
        return button;
    }

}

class ClassTableCellEditorButton extends DefaultCellEditor{//查看学生界面辅助类，按钮事件触发在此类中

    private JButton btn;
    private int clickedRow;
    public ClassTableCellEditorButton() {
        super(new JTextField());
        //设置点击一次就激活，否则默认好像是点击2次激活。
        this.setClickCountToStart(1);
        btn = new JButton("修改成绩");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("按钮事件触发----");
                JButton clickedButton = (JButton) e.getSource();

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
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
