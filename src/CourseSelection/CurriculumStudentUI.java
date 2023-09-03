package view.CourseSelection;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CurriculumStudentUI extends JFrame {
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();
    DefaultTableModel modelChosen = new DefaultTableModel();
    JTable table = new JTable();
    String[][] data = {
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"}

    };
    JTable tableChosen = new JTable();
    String[][] dataChosen = {
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"},
            {"1","1","1","1","1"}

    };
    JPanel TopPanel=new JPanel();//顶部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间卡片布局的面板
    JPanel CoursesPanel=new JPanel(springLayout);//学生查看课程列表的面板
    JPanel ChosenPanel=new JPanel(springLayout);//学生查看已选课程的面板
    JButton CoursesBtn=new JButton("查看课程列表");

    JButton ChosenBtn=new JButton("查看已选课程");//同上

    JButton backBtn=new JButton("退出");//同上

    public CurriculumStudentUI(){
        super("选课系统");
        String[] columnNames ={"课程编号","课程名称","教学班个数","班级人数","课程类型","学分","显示教学班"};
        String[] columnNamesChosen ={"课程班编号","任课教师","上课地点","上课时间","退选"};

        model.setDataVector(data, columnNames);
        table.setModel(model);
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        table.getColumnModel().getColumn(6).setCellRenderer(new ShowCoursesTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new ShowCoursesTableCellEditorButton());
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());


        modelChosen.setDataVector(dataChosen, columnNamesChosen);
        tableChosen.setModel(modelChosen);
        tableChosen.setRowHeight(30);
        JTableHeader tab_headerChosen = tableChosen.getTableHeader();					//获取表头
        tab_headerChosen.setFont(new Font("楷体",Font.PLAIN,25));
        tab_headerChosen.setPreferredSize(new Dimension(tab_headerChosen.getWidth(), 30));	//修改表头的高度
        tableChosen.getColumnModel().getColumn(4).setCellRenderer(new DeleteClassTableCellRendererButton());
        tableChosen.getColumnModel().getColumn(4).setCellEditor(new DeleteClassTableCellEditorButton());
        tableChosen.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 500)); // 设置滚动面板的大小
        JScrollPane scrollPaneChosen = new JScrollPane(tableChosen);
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
        table.setFont(centerFont);
        tableChosen.setFont(centerFont);

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

    public static void main(String[] args){
        try {
            // 设置外观为Windows外观
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            UIManager.put("nimbusBase", new Color(210, 210, 250)); // 边框
            UIManager.put("nimbusBlueGrey", new Color(173, 216, 230)); // 按钮
            UIManager.put("control", new Color(240, 248, 255)); // 背景



        } catch (Exception e) {
            e.printStackTrace();
        }
        new CurriculumStudentUI();
    }
}

class ShowClassUI extends JFrame{//显示教学班界面
    SpringLayout springLayout=new SpringLayout();
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable();
    JButton backBtn=new JButton("退出");//同上

    String[][] data = {
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"},
            {"1","1","1","1","1","1","1"}

    };
    //JPanel TopPanel=new JPanel();//顶部放置标题的面板
    JPanel TopPanel=new JPanel();//底部放置按钮的面板
    JPanel BottomPanel=new JPanel();//底部放置按钮的面板
    JPanel panel1=new JPanel();//中间的面板
    JLabel title=new JLabel("教学班");
    public ShowClassUI(){
        super("学生学籍系统");

        String[] columnNames ={"课程班编号","任课教师","上课地点","上课时间","班级人数","课程容量","选课"};
        model.setDataVector(data, columnNames);
        table.setModel(model);
        table.setRowHeight(30);
        JTableHeader tab_header = table.getTableHeader();					//获取表头
        tab_header.setFont(new Font("楷体",Font.PLAIN,25));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));	//修改表头的高度
        table.getColumnModel().getColumn(6).setCellRenderer(new ChooseClassTableCellRendererButton());
        table.getColumnModel().getColumn(6).setCellEditor(new ChooseClassTableCellEditorButton());
        table.setDefaultRenderer(Object.class, new TableBackgroundColorRenderer());
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 600)); // 设置滚动面板的大小

        Container contentPane=getContentPane();//获取控制面板

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
        table.setFont(centerFont2);
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
class DeleteClassTableCellRendererButton implements TableCellRenderer {//查看班级界面辅助类



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
class DeleteClassTableCellEditorButton extends DefaultCellEditor{//查看班级界面辅助类，按钮事件触发在此类中

    private JButton btn;
    private int clickedRow;
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

                clickedRow = (int) clickedButton.getClientProperty("row"); // 获取客户端属性中保存的行索引
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


