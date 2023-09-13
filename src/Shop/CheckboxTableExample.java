package view.Shop;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CheckboxTableExample extends JFrame implements ItemListener {
    private JTable table;

    public CheckboxTableExample() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Checkbox Table Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建表格数据
        Object[][] data = {
                {1, "John Doe", 10.5, false},
                {2, "Jane Smith", 20.3, false},
                {3, "Bob Johnson", 15.2, false},
                {4, "Alice Williams", 5.8, false}
        };
        String[] columnNames = {"ID", "Name", "Value", "Select"};

        // 创建默认的表格模型，并设置数据和列名
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // 创建带复选框的表格
        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) { // 最后一列是复选框列
                    return Boolean.class;
                }
                return super.getColumnClass(column);
            }
        };

        // 设置复选框渲染器和编辑器
        table.getColumnModel().getColumn(3).setCellRenderer(table.getDefaultRenderer(Boolean.class));
        table.getColumnModel().getColumn(3).setCellEditor(table.getDefaultEditor(Boolean.class));

        // 添加复选框状态变化监听器
        JCheckBox checkBox = (JCheckBox) table.getDefaultEditor(Boolean.class).getTableCellEditorComponent(table, null, false, 0, 3);
        checkBox.addItemListener(this);

        // 将表格放置在滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 复选框被选中
            System.out.println("Checkbox selected");
            // 在这里处理复选框选中时的逻辑
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
            // 复选框被取消选中
            System.out.println("Checkbox deselected");
            // 在这里处理复选框取消选中时的逻辑
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CheckboxTableExample().setVisible(true);
            }
        });
    }
}