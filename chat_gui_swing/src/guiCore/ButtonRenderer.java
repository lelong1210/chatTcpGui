package guiCore;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

//CONSTRUCTOR
	public ButtonRenderer() {
		// SET BUTTON PROPERTIES
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row,
			int col) {

		// SET PASSED OBJECT AS BUTTON TEXT
		if (obj != null) {
			setText(obj.toString());
		}

		return this;
	}

}