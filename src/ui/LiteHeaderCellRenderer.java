package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
class LiteHeaderCellRenderer extends JLabel implements TableCellRenderer {

    private Color headerForeColor = new Color(30, 30, 30);
    private Color headerBackColor = new Color(255, 255, 255);
    private Color borderColor = new Color(240, 240, 240);

    // Implementation of TableCellRenderer interface
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            setText(value.toString());
        } else {
            setText("");
        }

        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), BorderFactory.createEmptyBorder(8, 4, 8, 4)));
        this.setFont(this.getFont().deriveFont(Font.BOLD));

        setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

        setOpaque(true);
        setBackground(headerBackColor);
        setForeground(headerForeColor);

        return this;
    }
}
