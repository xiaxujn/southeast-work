package virtualschoolClient.widgt;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyButtonRender implements TableCellRenderer
{
    private JPanel panel;

    private JButton btnsel;
    private JButton btndrop;
    
    public MyButtonRender()
    {
        this.initButton();

        this.initPanel();

        // 添加按钮。
        this.panel.add(this.btnsel);
        this.panel.add(this.btndrop);
    }

    private void initButton()
    {
        this.btnsel = new JButton();
        this.btndrop = new JButton();
    }

    private void initPanel()
    {
        this.panel = new JPanel();

        // panel使用绝对定位，这样button就不会充满整个单元格。
        this.panel.setLayout(new GridLayout(1,2));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
    {
        // 只为按钮赋值即可。也可以作其它操作，如绘背景等。
        this.btnsel.setText("选课");
        this.btndrop.setText("退课");
        return this.panel;
    }

}