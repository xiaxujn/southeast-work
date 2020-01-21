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

        // ��Ӱ�ť��
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

        // panelʹ�þ��Զ�λ������button�Ͳ������������Ԫ��
        this.panel.setLayout(new GridLayout(1,2));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
            int column)
    {
        // ֻΪ��ť��ֵ���ɡ�Ҳ������������������汳���ȡ�
        this.btnsel.setText("ѡ��");
        this.btndrop.setText("�˿�");
        return this.panel;
    }

}