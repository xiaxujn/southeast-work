package virtualschoolClient.widgt;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class NewTableRender extends DefaultTableCellRenderer{
	
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, 
			boolean hasFocused, int row, int column) {
		if (row < 0 || column != 8) {
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocused, row, column);
		} else {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,3));
			Font f = new Font("Dialog",Font.ROMAN_BASELINE,10);
			
			JButton buttonUpdate = new JButton("²é¿´");
			buttonUpdate.setSize(30, 20);
			buttonUpdate.setFont(f);
			JButton buttonDelete = new JButton("É¾³ý");
			buttonDelete.setSize(30,20);
			buttonDelete.setFont(f);
			
			panel.add(buttonUpdate,BorderLayout.WEST);
			panel.add(buttonDelete,BorderLayout.EAST);
			
			table.getColumnModel().getColumn(1).setMinWidth(110);
			table.getTableHeader().setReorderingAllowed(false);
			table.getTableHeader().setResizingAllowed(false);
			
			return panel;
		}
	}
}
