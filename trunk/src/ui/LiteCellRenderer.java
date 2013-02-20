package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
class LiteCellRenderer extends JLabel implements TableCellRenderer {

    private Color oddColor = new Color(255, 255, 255);
    private Color evenColor = new Color(250, 250, 250);
    private Color borderColor = new Color(240, 240, 240);

    // Implementation of TableCellRenderer interface
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value != null) {
            setText(value.toString());
        } else {
            setText("");
        }

        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        this.setFont(this.getFont().deriveFont(Font.PLAIN));

        this.setOpaque(true);
        this.setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            if (row % 2 == 0) {
                setBackground(evenColor);
            } else {
                setBackground(oddColor);
            }

            setForeground(table.getForeground());
        }

        return this;
    }
}
