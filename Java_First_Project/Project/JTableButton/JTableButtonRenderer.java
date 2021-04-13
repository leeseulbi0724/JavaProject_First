package JTableButton;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
public class JTableButtonRenderer implements TableCellRenderer {
    public JTableButtonRenderer() {}
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rows, int columns) {
        JButton button = (JButton)value;
        return button;
    }
}